package com.simplicity.client.cache;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.DebuggingRunnables;
import com.simplicity.client.signlink;


/**
 * Enchanced cache downloader
 * Handles cache downloading & unzipping
 * @author Gabriel Hannason
 */
public class CacheDownloader {
	
	private static final String CACHE_PATH = signlink.findcachedir();
	
	private static final String VERSION_FILE = CACHE_PATH + "versions.dat";
	
	public static final String CACHE_URL = "https://storage.googleapis.com/simplicityps/";

	public static void init() {

		if(Configuration.LOCALHOST) {
			return;
		}

		try {
			//DebuggingRunnables.getMessageThread("cachestart").start();;
			
			HashMap<String, Long> version_map = new HashMap <> ();
			
			for(CACHE_DOWNLOAD_FILES cacheFile : CACHE_DOWNLOAD_FILES.values()) {
				
					boolean exists = new File(signlink.findcachedir() + cacheFile.file).exists();
				    
					long CacheRemote = getRemoteVersion(cacheFile.link);
					
				    if (!exists) {
				    	System.out.println("Cache doesn't exist.");
				    	updateFiles(cacheFile);
				    	continue;
				    }
				    
					long CacheLocal = getLocalVersion(cacheFile.identifier);
					
					System.out.println(cacheFile.identifier + " Sizes: Local - " + CacheLocal + " Remote - " + CacheRemote);
					
					if(CacheLocal != CacheRemote && CacheRemote != -1){
						System.out.println("Cache is not up tp date.");
						updateFiles(cacheFile);
						//writeVersions(CacheRemote);
					}
					version_map.put(cacheFile.identifier, CacheRemote);
				}
			writeVersions(version_map);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateFiles(CACHE_DOWNLOAD_FILES cacheFile) throws Exception {
		int total = 1;
		
		int current = 1;
		
		String file = cacheFile.file;
			downloadFile(cacheFile, file, current, total);
			if (file.endsWith(".zip")) {
				unzip(new File(signlink.findcachedir() + file));
			}
			current++;

	}

	public static void downloadFile(CACHE_DOWNLOAD_FILES cacheFile, String file, int current, int total) throws IOException {
		String fileURL = cacheFile.link;
		String downloadingText = Client.optimizeText(cacheFile.toString().toLowerCase());
		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		int responseCode = httpConn.getResponseCode();

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			// opens input stream from the HTTP connection
			InputStream inputStream = httpConn.getInputStream();
			String saveFilePath = signlink.findcachedir() + file;
			// opens an output stream to save into file
			FileOutputStream outputStream = new FileOutputStream(saveFilePath);

			int bytesRead = -1;
			byte[] buffer = new byte[4096];
			long startTime = System.currentTimeMillis();
			int downloaded = 0;
			long numWritten = 0;
			int length = httpConn.getContentLength();
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
				numWritten += bytesRead;
				downloaded += bytesRead;
				int percentage = (int)(((double)numWritten / (double)length) * 100D);
				int downloadSpeed = (int) ((downloaded / 1024) / (1 + ((System.currentTimeMillis() - startTime) / 1000)));
				String s = total > 1 ? "("+current+"/"+total+")" : "";
				drawLoadingText(percentage, (new StringBuilder()).append("Downloading "+downloadingText+""+s+": "+percentage+"%").toString());
			}
			
			//DebuggingRunnables.getMessageThread("cacheend").start();
			outputStream.close();
			inputStream.close();

		} else {
			System.out.println("download link replied HTTP code: " + responseCode);
		}
		httpConn.disconnect();
	}
	
	/**
	 * Gets the current version for the file.
	 * 
	 * @param name
	 *            The file version string.
	 * @return The version.
	 */
	private static long getLocalVersion(String name) {
		try {
			File versionDir = new File(VERSION_FILE);

			if (!versionDir.exists()) {
				versionDir.createNewFile();
				return -1;
			}
			
			 String text = new String(Files.readAllBytes(Paths.get(VERSION_FILE)), StandardCharsets.UTF_8);
			 
			 if(text == null)
					 return -1;
			 if(text.length() <= 3)
				 return -1;
			
			HashMap<String, Long> version_map = new Gson().fromJson(text, new TypeToken<HashMap<String, Long>>(){}.getType());
			
			if(version_map.get(name) != null) {
				return version_map.get(name);
			}
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * Writes the versions into the version file.
	 * 
	 * @param cacheVersion
	 *            The version of the cache.
	 * @param spritesVersion
	 *            The version of the sprites.
	 */
	private static void writeVersions(HashMap<String, Long> version_map) {
		BufferedWriter writer = null;
		try {
			Gson gson = new Gson(); 
			writer = new BufferedWriter(new FileWriter(VERSION_FILE));
			writer.write(gson.toJson(version_map).toString());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static long getRemoteVersion(String url) {
		try {
			return ((HttpURLConnection) new URL(url).openConnection()).getContentLength();
		} catch (Exception e) {
			return -1;
		}
	}

	private static String antiCache() {
		return RandomStringUtils.randomAlphabetic(10);
	}
	private static void drawLoadingText(int amount, String text) {
		Client.getClient().drawLoadingText(amount, text);
	}

	private static void unzip(final File file) {
		//DebuggingRunnables.getMessageThread("zipstart").start();
		try {
			InputStream in =  new BufferedInputStream(new FileInputStream(file));
			ZipInputStream zin = new ZipInputStream(in);
			ZipEntry e;
			while((e=zin.getNextEntry()) != null) {
				if(e.isDirectory()) {
					(new File(signlink.findcachedir() + e.getName())).mkdir();
				} else {
					if (e.getName().equals(file.getName())) {
						unzipPartlyArchive(zin, file.getName());
						break;
					}
					unzipPartlyArchive(zin, signlink.findcachedir() + e.getName());
				}
			}
			zin.close();
			//DebuggingRunnables.getMessageThread("zipend").start();
		} catch(Exception e) {}
	}

	/**
	 * Unzips a partly archive
	 * @param zin	The zip inputstream
	 * @param s		The location of the zip file
	 * @throws IOException	The method can throw an IOException.
	 */
	private static void unzipPartlyArchive(ZipInputStream zin, String s) throws Exception {
		FileOutputStream out = new FileOutputStream(s);
		drawLoadingText(100, "Unpacking data..");
		byte [] b = new byte[1024];
		int len = 0;

		while ((len = zin.read(b)) != -1) {
			out.write(b,0,len);
		}
		out.close();
	}

	public static String getDownloadLink(String name) {
		return CACHE_URL + name + "?" + antiCache();
		/*try {
		  String webLink = "https://simplicityps.org/resources/cache_link.php?type=" + name;
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(webLink);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	    	 if (line.contains("url:")) {
	    		 return line.replace("url:", "").trim();
	    	 }
	      }
	      rd.close();
	      return result.toString();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "ERROR_IN_URL";*/
	}
	
	enum CACHE_DOWNLOAD_FILES {

		MAIN_FILE_CACHE("main_file_cache.zip", "MAIN_FILE_CACHE_VER", getDownloadLink("main_file_cache.zip")),
		SPRITES_DAT("sprites.dat", "SPRITES_DAT_VER", getDownloadLink("sprites.dat")),
		SPRITES_IDX("sprites.idx", "SPRITES_IDX_VER", getDownloadLink("sprites.idx")),
		LOC_DAT("loc.dat", "LOC_DAT_VER", getDownloadLink("loc.dat")),
		LOC_IDX("loc.idx", "LOC_IDX_VER", getDownloadLink("loc.idx")),
		DATA("data.zip", "DATA_VER", getDownloadLink("data.zip")),

		;

		CACHE_DOWNLOAD_FILES(String file, String identifier, String link) {
			this.file = file;
			this.identifier = identifier;
			this.link = link;
		}

		private String file;
		private String identifier;
		private String link;
	}
}