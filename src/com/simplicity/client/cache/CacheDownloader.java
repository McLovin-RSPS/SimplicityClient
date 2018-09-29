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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

	public static void init() {

		if(Configuration.LOCALHOST) {
			return;
		}

		try {
			//DebuggingRunnables.getMessageThread("cachestart").start();;
			
			for(CACHE_DOWNLOAD_FILES cacheFile : CACHE_DOWNLOAD_FILES.values()) {
				
				    boolean exists = getLocalVersion(cacheFile.identifier) != -1;
				//boolean exists = new File(signlink.findcachedir() + cacheFile.identifier).exists();
				
					long ImagesLocal = getLocalVersion(CACHE_DOWNLOAD_FILES.IMAGES.identifier);
					long CacheLocal = getLocalVersion(CACHE_DOWNLOAD_FILES.CACHE.identifier);
					long DataLocal = getLocalVersion(CACHE_DOWNLOAD_FILES.DATA.identifier);
					
					long ImagesRemote = getRemoteVersion(CACHE_DOWNLOAD_FILES.IMAGES.link);
					long CacheRemote = getRemoteVersion(CACHE_DOWNLOAD_FILES.CACHE.link);
					long DataRemote = getRemoteVersion(CACHE_DOWNLOAD_FILES.DATA.link);
					
					if (ImagesLocal != ImagesRemote && ImagesRemote != -1) {
						updateFiles(CACHE_DOWNLOAD_FILES.IMAGES);
						writeVersions(CacheRemote, ImagesRemote, DataRemote);
					}
					if(CacheLocal != CacheRemote && CacheRemote != -1){
						updateFiles(CACHE_DOWNLOAD_FILES.CACHE);
						writeVersions(CacheRemote, ImagesRemote, DataRemote);
					}
					if(DataLocal != DataRemote && DataRemote != -1){
						updateFiles(CACHE_DOWNLOAD_FILES.DATA);
						writeVersions(CacheRemote, ImagesRemote, DataRemote);
					}
				    if (!exists) {
					updateFiles(cacheFile);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateFiles(CACHE_DOWNLOAD_FILES cacheFile) throws Exception {
		int total = cacheFile.file.length;
		
		int current = 1;
		
		for (String file : cacheFile.file) {
			downloadFile(cacheFile, file, current, total);
			if (file.endsWith(".zip")) {
				unzip(new File(signlink.findcachedir() + file));
			}
			current++;
		}
		
		if (!cacheFile.equals(CACHE_DOWNLOAD_FILES.IMAGES)) {
			new File(signlink.findcachedir() + cacheFile.identifier).createNewFile();
		}
		if (!cacheFile.equals(CACHE_DOWNLOAD_FILES.CACHE)) {
			new File(signlink.findcachedir() + cacheFile.identifier).createNewFile();
		}
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
		BufferedReader br = null;
		
		try {
			File versionDir = new File(VERSION_FILE);

			if (!versionDir.exists()) {
				versionDir.createNewFile();
				return -1;
			}
			
			if (name.equals("SPRITE_VER")) {
				if (!new File(CACHE_PATH + "sprites.dat").exists() || !new File(CACHE_PATH + "sprites.idx").exists()) {
					return -1;
				}
			}
	
			br = new BufferedReader(new FileReader(VERSION_FILE));

			String line;

			while ((line = br.readLine()) != null) {
				if (line.startsWith(name + " = ")) {
					long size = Long.parseLong(line.replace(name + " = ", ""));
					br.close();
					return size;
				}
			}
			
			br.close();
			return -1;
		} catch (Exception e) {
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
	private static void writeVersions(long cacheVersion, long spritesVersion, long dataVersion) {
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(VERSION_FILE));
			writer.write("CACHE_VER = " + cacheVersion);
			writer.newLine();
			writer.write("SPRITE_VER = " + spritesVersion);
			writer.newLine();
			writer.write("DATA_VER = " + dataVersion);
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
			file.delete();
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

	enum CACHE_DOWNLOAD_FILES {

		IMAGES(new String[]{"images.zip"}, "SPRITE_VER", "https://dl.dropboxusercontent.com/s/8lfoih1c2a09ru0/images.zip"),
		CACHE(new String[]{"cache.zip"}, "CACHE_VER", "https://dl.dropboxusercontent.com/s/4smm3le4iqfjn58/cache.zip"),
		DATA(new String[]{"data.zip"}, "DATA_VER", "https://dl.dropboxusercontent.com/s/7009eoyow8gb5sb/data.zip"),
		;

		CACHE_DOWNLOAD_FILES(String[] file, String identifier, String link) {
			this.file = file;
			this.identifier = identifier;
			this.link = link;
		}

		private String[] file;
		private String identifier;
		private String link;
	}
}