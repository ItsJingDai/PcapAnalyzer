package com.jingdai.pcapanalyzer.utils;

public class DataUtils {

	/**
	 * reverse byte array
	 * @param arr
	 */
	public static void reverseByteArray(byte[] arr){
		byte temp;
		int n = arr.length;
		for(int i = 0; i < n / 2; i++){
			temp = arr[i];
			arr[i] = arr[n - 1 - i];
			arr[n - 1 - i] = temp;
		}
	}

	/**
	 * convert byte to int
	 * @param b
	 * @return
	 */
	public static int byteToInt (byte b) {
		return (b & 0xff);
	}

	public static int byteArray2Int(byte[] array, int length) {
		if (length == 2) {
			return (array[0] & 0xff) * 256 + (array[1] & 0xff);
		} else if (length == 4) {
			int value= 0;
			for (int i = 0; i < 4; i++) {
				int shift= (4 - 1 - i) * 8;
				value +=(array[i] & 0x000000FF) << shift;
			}

			return value;
		}
		return -1;
	}

	/**
	 * Parse Byte to Hex String
	 * @param b
	 * @return
	 */
	public static String byteToHexString (byte b) {
		return intToHexString(byteToInt(b));
	}

	/**
	 * Parse short to Hex String
	 * @param s
	 * @return
	 */
	public static String shortToHexString (short s) {
		String hex = intToHexString(s);
		int len = hex.length();
		if (len > 4) {
			hex = hex.substring(4);
		} 

		len = hex.length();
		if (len < 4) {
			int n = 4 - len;
			for (int i = 0; i < n; i ++) {
				hex = "0" + hex;
			}
		}

		return "0x" + hex;
	}

	/**
	 * Parse Int to Hex String
	 * @param data
	 * @return
	 */
	public static String intToHexString (int data) {
		return Integer.toHexString(data);
	}

}