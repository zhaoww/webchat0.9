/**
 * @author CTRL 
 * Classname : MD5Utils 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public MD5Utils() {
	}

	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	/*
	 * private static String byteToNum(byte bByte) { int iRet = bByte;
	 * System.out.println("iRet1=" + iRet); if (iRet < 0) { iRet += 256; }
	 * return String.valueOf(iRet); }
	 */

	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String GetMD5Code(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	/*
	 * public static void main(String[] args) { MD5Utils getMD5 = new
	 * MD5Utils(); System.out.println(getMD5.GetMD5Code("123")); }
	 */
}