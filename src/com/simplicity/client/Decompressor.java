package com.simplicity.client;


import java.io.*;
import java.util.zip.GZIPOutputStream;

import com.simplicity.util.GZIPUtil;

public final class Decompressor {

	public Decompressor(RandomAccessFile randomaccessfile, RandomAccessFile randomaccessfile1, int j) {
		anInt311 = j;
		dataFile = randomaccessfile;
		indexFile = randomaccessfile1;
	}

	/**
	 * Packs a file into this archive.
	 * 
	 * @param index The index.
	 * @param file  The file to pack.
	 * @return <code>true</code> if the file was packed.
	 */
	public boolean pack(int index, File file) {
		try {
			byte[] data;

			if (!GZIPUtil.isGzipped(file)) {
				data = GZIPUtil.toGzip(file);
			} else {
				data = fileToByteArray(file);
			}

			if (data == null || data.length == 0) {
				return false;
			}

			put((int) data.length, data, index);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Dumps a file from this archive.
	 * 
	 * @param index The index.
	 * @param path  The path to dump this file to.
	 * @return <code>true</code> if the file was dumped.
	 */
	public boolean dump(int index, String path) {
		try {
			byte[] indexByteArray = get(index);

			if (indexByteArray == null || indexByteArray.length == 0) {
				return false;
			}

			if (GZIPUtil.isGzipped(indexByteArray)) {
				BufferedOutputStream gzip = new BufferedOutputStream(
						new GZIPOutputStream(new FileOutputStream(path)));

				gzip.write(indexByteArray);
				gzip.close();
			} else {
				DataOutputStream stream = new DataOutputStream(new FileOutputStream(path));
				stream.write(indexByteArray);
				stream.close();
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Returns the number of files in the cache index.
	 * @return
	 */
	public long getFileCount() {
		try {
			if (indexFile != null) {
				return (indexFile.length() / 6);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	public byte[] decompress(int fileId) {
		synchronized (dataFile) {
			byte[] is;
			try {
				if (indexFile.length() < fileId * 6 + 6) {
					byte[] is_2_ = null;
					byte[] is_3_ = is_2_;
					return is_3_;
				}
				indexFile.seek(fileId * 6);
				indexFile.read(buffer, 0, 6);
				int i_4_ = (buffer[2] & 0xff) + ((buffer[1] & 0xff) << 8) + ((buffer[0] & 0xff) << 16);
				int i_5_ = (buffer[5] & 0xff) + ((buffer[4] & 0xff) << 8) + ((buffer[3] & 0xff) << 16);
				if (i_4_ < 0) {
					byte[] is_6_ = null;
					byte[] is_7_ = is_6_;
					return is_7_;
				}
				if (i_5_ <= 0 || i_5_ > dataFile.length() / 520L) {
					byte[] is_8_ = null;
					byte[] is_9_ = is_8_;
					return is_9_;
				}
				byte[] is_10_ = new byte[i_4_];
				int i_11_ = 0;
				int i_12_ = 0;
				while (i_11_ < i_4_) {
					if (i_5_ == 0) {
						byte[] is_13_ = null;
						byte[] is_14_ = is_13_;
						return is_14_;
					}
					dataFile.seek(i_5_ * 520L);
					int i_15_ = i_4_ - i_11_;
					int i_16_;
					int i_17_;
					int i_18_;
					int i_19_;
					int i_20_;
					if (fileId > 65535) {
						if (i_15_ > 510)
							i_15_ = 510;
						i_16_ = 10;
						dataFile.read(buffer, 0, i_16_ + i_15_);
						i_17_ = (buffer[3] & 0xff) + ((buffer[2] & 0xff) << 8) + ((buffer[0] & 0xff) << 24) + ((buffer[1] & 0xff) << 16);
						i_18_ = (buffer[5] & 0xff) + ((buffer[4] & 0xff) << 8);
						i_19_ = ((buffer[7] & 0xff) << 8) + ((buffer[6] & 0xff) << 16) + (buffer[8] & 0xff);
						i_20_ = buffer[9] & 0xff;
					}
					else {
						if (i_15_ > 512)
							i_15_ = 512;
						i_16_ = 8;
						dataFile.read(buffer, 0, i_16_ + i_15_);
						i_17_ = (buffer[1] & 0xff) + ((buffer[0] & 0xff) << 8);
						i_18_ = ((buffer[2] & 0xff) << 8) + (buffer[3] & 0xff);
						i_19_ = ((buffer[5] & 0xff) << 8) + ((buffer[4] & 0xff) << 16) + (buffer[6] & 0xff);
						i_20_ = buffer[7] & 0xff;
					}
					if (i_17_ != fileId || i_18_ != i_12_ || i_20_ != anInt311) {
						byte[] is_21_ = null;
						byte[] is_22_ = is_21_;
						return is_22_;
					}
					if (i_19_ < 0 || i_19_ > dataFile.length() / 520L) {
						byte[] is_23_ = null;
						byte[] is_24_ = is_23_;
						return is_24_;
					}
					int i_25_ = i_16_ + i_15_;
					for (int i_26_ = i_16_; i_26_ < i_25_; i_26_++)
						is_10_[i_11_++] = buffer[i_26_];
					i_5_ = i_19_;
					i_12_++;
				}
				is = is_10_;
			}
			catch (java.io.IOException ioexception) {
				byte[] is_27_ = null;
				byte[] is_28_ = is_27_;
				return is_28_;
			}
			byte[] is_29_ = is;
			return is_29_;
		}
	}
	public synchronized byte[] get(int i) {
		try {
			seekTo(indexFile, i * 6);
			int l;
			for(int j = 0; j < 6; j += l)
			{
				l = indexFile.read(buffer, j, 6 - j);
				if(l == -1)
					return null;
			}
			int i1 = ((buffer[0] & 0xff) << 16) + ((buffer[1] & 0xff) << 8) + (buffer[2] & 0xff);
			int j1 = ((buffer[3] & 0xff) << 16) + ((buffer[4] & 0xff) << 8) + (buffer[5] & 0xff);
			if(j1 <= 0)
				return null;
			byte abyte0[] = new byte[i1];
			int k1 = 0;
			for(int l1 = 0; k1 < i1; l1++) {
				if(j1 == 0)
					return null;
				seekTo(dataFile, j1 * 520);
				int k = 0;
				int i2 = i1 - k1;
				if(i2 > 512)
					i2 = 512;
				int j2;
				for(; k < i2 + 8; k += j2) {
					j2 = dataFile.read(buffer, k, (i2 + 8) - k);
					if(j2 == -1)
						return null;
				}
				int k2 = ((buffer[0] & 0xff) << 8) + (buffer[1] & 0xff);
				int l2 = ((buffer[2] & 0xff) << 8) + (buffer[3] & 0xff);
				int i3 = ((buffer[4] & 0xff) << 16) + ((buffer[5] & 0xff) << 8) + (buffer[6] & 0xff);
				int j3 = buffer[7] & 0xff;
				if(k2 != i || l2 != l1 || j3 != anInt311)
					return null;
				if(i3 < 0)
					return null;
				for(int k3 = 0; k3 < i2; k3++)
					abyte0[k1++] = buffer[k3 + 8];

				j1 = i3;
			}

			return abyte0;
		} catch(IOException _ex) {
			return null;
		}
	}

	public synchronized boolean put(int length, byte data[], int index) {
		boolean entered = enterData(true, index, length, data);
		if(!entered)
			entered = enterData(false, index, length, data);
		return entered;
	}

	private synchronized boolean enterData(boolean exists, int index, int length, byte data[]) {
		try {
			int l;
			if(exists) {
				seekTo(indexFile, index * 6);
				int k1;
				for(int i1 = 0; i1 < 6; i1 += k1) {
					k1 = indexFile.read(buffer, i1, 6 - i1);
					if(k1 == -1)
						return false;
				}
				l = ((buffer[3] & 0xff) << 16) + ((buffer[4] & 0xff) << 8) + (buffer[5] & 0xff);
				if(l <= 0)
					return false;
			} else {
				l = (int)((dataFile.length() + 519L) / 520L);
				if(l == 0)
					l = 1;
			}
			buffer[0] = (byte)(length >> 16);
			buffer[1] = (byte)(length >> 8);
			buffer[2] = (byte)length;
			buffer[3] = (byte)(l >> 16);
			buffer[4] = (byte)(l >> 8);
			buffer[5] = (byte)l;
			seekTo(indexFile, index * 6);
			indexFile.write(buffer, 0, 6);
			int j1 = 0;
			for(int l1 = 0; j1 < length; l1++) {
				int i2 = 0;
				if(exists) 	{
					seekTo(dataFile, l * 520);
					int j2;
					int l2;
					for(j2 = 0; j2 < 8; j2 += l2) {
						l2 = dataFile.read(buffer, j2, 8 - j2);
						if(l2 == -1)
							break;
					}
					if(j2 == 8) {
						int i3 = ((buffer[0] & 0xff) << 8) + (buffer[1] & 0xff);
						int j3 = ((buffer[2] & 0xff) << 8) + (buffer[3] & 0xff);
						i2 = ((buffer[4] & 0xff) << 16) + ((buffer[5] & 0xff) << 8) + (buffer[6] & 0xff);
						int k3 = buffer[7] & 0xff;
						if(i3 != index || j3 != l1 || k3 != anInt311)
							return false;
						if(i2 < 0)
							return false;
					}
				}
				if(i2 == 0) {
					exists = false;
					i2 = (int)((dataFile.length() + 519L) / 520L);
					if(i2 == 0)
						i2++;
					if(i2 == l)
						i2++;
				}
				if(length - j1 <= 512)
					i2 = 0;
				buffer[0] = (byte)(index >> 8);
				buffer[1] = (byte)index;
				buffer[2] = (byte)(l1 >> 8);
				buffer[3] = (byte)l1;
				buffer[4] = (byte)(i2 >> 16);
				buffer[5] = (byte)(i2 >> 8);
				buffer[6] = (byte)i2;
				buffer[7] = (byte)anInt311;
				seekTo(dataFile, l * 520);
				dataFile.write(buffer, 0, 8);
				int k2 = length - j1;
				if(k2 > 512)
					k2 = 512;
				dataFile.write(data, j1, k2);
				j1 += k2;
				l = i2;
			}

			return true;
		} catch(IOException _ex) {
			return false;
		}
	}

	private synchronized void seekTo(RandomAccessFile randomaccessfile, int j) throws IOException {
		try {
			/*if (j < 0 || j > 0x3c00000) {
				System.out.println("Badseek - pos:" + j + " len:" + randomaccessfile.length());
				j = 0x3c00000;
				try {
					Thread.sleep(1000L);
				} catch (Exception _ex) {
				}
			}*/
			randomaccessfile.seek(j);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private byte[] fileToByteArray(File file) {
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

	private static final byte[] buffer = new byte[520];
	private final RandomAccessFile dataFile;
	private final RandomAccessFile indexFile;
	private final int anInt311;

}
