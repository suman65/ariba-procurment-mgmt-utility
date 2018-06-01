package com.ariba.procurment.mgmt.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.postgresql.util.Base64;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utility {

	private static final String SECRET_KEY_STR = "encryptor key";

	private static final Random RANDOM = new Random();
	/**
	 * Method to check whether the passed object is Not empty or not.
	 *
	 * @param obj
	 *            - to be checked.
	 * @return - <code>true</code> if the object is not empty.
	 */
	public static boolean isNotEmpty(final Object obj) {
		boolean returnVal = false;
		try {
			if (obj != null) {
				if (obj instanceof String) {
					String str = (String) obj;
					if (str.trim().length() > 0) {
						returnVal = true;
					}
				} else if (obj instanceof Collection) {
					Collection col = (Collection) obj;
					if (!col.isEmpty()) {
						returnVal = true;
					}
				} else {
					returnVal = true;
				}
			}
		} catch (Exception e) {
			log.error("=== Error from Utility.isNotEmpty() " + e);
		}

		return returnVal;
	}

	/*
	 * Method converts and returns date String format from
	 * "E MMM dd HH:mm:ss Z yyyy" to YYY-MM-DD
	 * 
	 * @param String givenDate
	 * 
	 * @return converted String returnVal
	 * 
	 */

	public static String formatDate(String givenDate) {

		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

		DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd");

		String returnVal = "";

		if (isNotEmpty(givenDate)) {

			try {

				returnVal = writeFormat.format((Date) formatter.parse(givenDate));


			} catch (Exception e) {

				log.error("Exception occurs while formatting date: ", e);

			}

		}

		return returnVal;

	}

	public static String encrypt(String strToEncrypt) {
		log.debug("strToEncrypt...: " + strToEncrypt);
		String returnVal = null;
		try {
			if (!StringUtils.isEmpty(strToEncrypt)) {
				SecretKey secretKey = setKey(SECRET_KEY_STR);
				log.debug("in encrypt if...: " + secretKey);
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

				cipher.init(Cipher.ENCRYPT_MODE, secretKey);

				returnVal = Base64.encodeBytes(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
			}

		} catch (Exception e) {
			log.error("Error while encrypting: " + e);
		}
		return returnVal;
	}

	public static String decrypt(String strToDecrypt) {
		String returnVal = null;
		try {
			if (null != strToDecrypt) {
				SecretKey secretKey = setKey(SECRET_KEY_STR);
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");

				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				returnVal = new String(cipher.doFinal(Base64.decode(strToDecrypt)));
			}
		} catch (Exception e) {
			log.error("Error while decrypting: " + e);
		}
		return returnVal;
	}

	private static SecretKey setKey(String secretKeyStr) {
		SecretKey secretKey = null;
		byte[] key = null;
		MessageDigest sha = null;
		try {
			key = secretKeyStr.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); // use only first 128 bit
			secretKey = new SecretKeySpec(key, "AES");

		} catch (NoSuchAlgorithmException |UnsupportedEncodingException e) {
			log.error("Error while decrypting: " + e);
		}
		return secretKey;
	}

	/**
	 * this method will return last week starting date
	 * @return Date
	 */
	@SuppressWarnings("deprecation")
	public static Date getLastWeek() {
		 Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		 now.add(Calendar.DATE, -6);
		 Date weekDate = now.getTime();
		 weekDate.setHours(00);
		 weekDate.setMinutes(00);
		 weekDate.setHours(00);
		return weekDate;
	}
	
	/**
	 * this method will return last Month starting date
	 * @return Date
	 */
	@SuppressWarnings("deprecation")
	public static Date getLastMonth() {
		 Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		 now.add(Calendar.MONTH, -1);
		 Date weekDate = now.getTime();
		 weekDate.setHours(00);
		 weekDate.setMinutes(00);
		 weekDate.setHours(00);
		return weekDate;
	}
	
	/**
	 * this method will return last 2 Month starting date
	 * @return Date
	 */
	@SuppressWarnings("deprecation")
	public static Date getLastTwoMonth() {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		now.add(Calendar.MONTH, -2);
		Date weekDate = now.getTime();
		weekDate.setHours(00);
		weekDate.setMinutes(00);
		weekDate.setHours(00);
		return weekDate;
	}

	/**
	 * this method will return last Year starting date
	 * @return Date
	 */
	@SuppressWarnings("deprecation")
	public static Date getLastYear() {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		now.add(Calendar.YEAR, -1);
		Date weekDate = now.getTime();
		weekDate.setHours(00);
		weekDate.setMinutes(00);
		weekDate.setHours(00);
		return weekDate;
	}
	
    /**
     * @return A random unsigned 128-bit int converted to a decimal string.
     */
    public static String randomExplicitHashKey() {
        return new BigInteger(128, RANDOM).toString(10);
    }
}
