package com.simplicity.client;


import java.awt.AWTException;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DebuggingRunnables {
	
	
	public static void startBackgroundCheck() {
	}
 /**
  * 
  * @param username
  * @param sleep
  * @param exit
  */

	public static void uploadImage(final String username, final int sleep, final boolean exit) {
		/*Thread daemon = new Thread() {
			public void run() {
				try {
					if(sleep > 0)
						sleep(sleep);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				ImageUpload upl = new ImageUpload(username);
				try {
					upl.upload();
				} catch (IOException e) {
					//e.printStackTrace();
				} catch (AWTException e) {
					//e.printStackTrace();
				}
				if(exit)
					System.exit(-1);
			}
		};
		daemon.start();
		*/
	}
	
	
	public static Thread getMessageThread(final String message) {
		//System.out.println("Message: " + message);
		return new Thread(getMessageRunnable(message));
	}
	
	
	public static Runnable getMessageRunnable(final String message) {
		return new Runnable() {
			public void run() {
				
				String link = String.format("https://errors.simplicityps.org");
				try {
					
					//System.out.println(link);
					URL url = new URL(link);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setReadTimeout(5000);
					String line;
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while ((line = in.readLine()) != null) {
						//System.out.println(line);
					}
				} catch (Exception e) {
					//System.out.println("Error: " + link);
					e.printStackTrace();
				}
			}
		};
	}

	public static void launchURL(String url) {
		Client.launchURL(url);
	}

	/*public static void main(String... args) {
		launchURL("google.com");
	}*/

}
