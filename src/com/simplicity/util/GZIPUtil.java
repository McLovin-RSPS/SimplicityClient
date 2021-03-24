package com.simplicity.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipException;

import org.apache.commons.io.output.ByteArrayOutputStream;

/**
 * An utility class for gzip related operations.
 * 
 * @author Blake
 *
 */
public class GZIPUtil {

	/**
	 * Returns if the specified file is compressed using gzip.
	 * 
	 * @param file The file.
	 * @return <code>true</code> if the file is compressed using gzip.
	 */
	public static boolean isGzipped(File file) {
		try {
			byte[] data = fileToByteArray(file);

			if (data == null) {
				return false;
			}

			new GZIPInputStream(new ByteArrayInputStream(data));
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Returns a compressed gzip byte array of the the specified file.
	 * 
	 * @param file The file.
	 * @return The compressed data.
	 */
	public static byte[] toGzip(File file) {
		try {
			byte[] data = fileToByteArray(file);

			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(data.length);

			try {
				GZIPOutputStream zipStream = new GZIPOutputStream(byteStream);
				try {
					zipStream.write(data);
				} finally {
					zipStream.close();
				}
			} finally {
				byteStream.close();
			}

			return byteStream.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}

	private static byte[] fileToByteArray(File file) {
		try {
			byte[] fileData = new byte[(int) file.length()];
			FileInputStream fis = new FileInputStream(file);
			fis.read(fileData);
			fis.close();
			return fileData;
		} catch (Exception e) {
			return null;
		}
	}

}
