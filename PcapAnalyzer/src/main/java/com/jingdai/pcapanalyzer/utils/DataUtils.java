package com.jingdai.pcapanalyzer.utils;

public class DataUtils {

	/**
	 * 将一维的字节数组逆序
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
	 * byte 转 int
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
			//由高位到低位
			for (int i = 0; i < 4; i++) {
				int shift= (4 - 1 - i) * 8;
				value +=(array[i] & 0x000000FF) << shift;//往高位游
			}

			return value;
		}
		return -1;
	}

	/**
	 * byte 转为 16 进制字符串
	 * @param b
	 * @return
	 */
	public static String byteToHexString (byte b) {
		return intToHexString(byteToInt(b));
	}

	/**
	 * short 转 16 进制字符串
	 * @param s
	 * @return
	 */
	public static String shortToHexString (short s) {
		String hex = intToHexString(s);
		int len = hex.length();
		if (len > 4) {	// 此时 short 值为负值，高位会补 1，变成 ffffed5c，因此截去符号位
			hex = hex.substring(4);
		} 

		len = hex.length();
		if (len < 4) {	// 若小于 4，则高位补 0
			int n = 4 - len;
			for (int i = 0; i < n; i ++) {
				hex = "0" + hex;
			}
		}

		return "0x" + hex;
	}

	/**
	 * 将 int 转为 16 进制字符串
	 * @param data
	 * @return
	 */
	public static String intToHexString (int data) {
		return Integer.toHexString(data);
	}

}