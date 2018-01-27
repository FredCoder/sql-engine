package com.k.engine.utils;

import java.nio.charset.Charset;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Hello world!
 *
 */
public class Des {
	/** SecretKey 负责保存对称密钥 */
	private SecretKey secretKey;
	/** Cipher负责完成加密或解密工作 */
	private Cipher clipher;
	/** 加密解密参数 */
	private AlgorithmParameterSpec params;
	private Lock lock = new ReentrantLock();
	
	private static final String DES = "DES";
	
	public Des(String key){
		this(DES, key);
	}

	
	
	private Des(String algorithm, String key) {
		this(algorithm, Des.generateKey(algorithm, key.getBytes()), null);
	}

	/**
	 * @param secretKey
	 * @param clipher
	 * @param params
	 */
	private Des(String algorithm, SecretKey key, AlgorithmParameterSpec paramsSpec) {
		this.secretKey = key;
		this.params = paramsSpec;

		try {
			clipher = Cipher.getInstance(algorithm);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 用于建立十六进制字符的输出的大写字符数组
	 */
	private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	/**
	 * 生成 {@link SecretKey}，仅用于对称加密和摘要算法密钥生成
	 * 
	 * @param algorithm
	 *            算法
	 * @param key
	 *            密钥，如果为{@code null} 自动生成随机密钥
	 * @return {@link SecretKey}
	 */
	public static SecretKey generateKey(String algorithm, byte[] key) {
		SecretKey secretKey = null;
		KeySpec keySpec;
		try {
			keySpec = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
			secretKey = keyFactory.generateSecret(keySpec);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return secretKey;
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            被加密的bytes
	 * @return 加密后的bytes
	 */
	private byte[] encrypt(byte[] data) {
		lock.lock();
		try {
			if (null == this.params) {
				clipher.init(Cipher.ENCRYPT_MODE, secretKey);
			} else {
				clipher.init(Cipher.ENCRYPT_MODE, secretKey, params);
			}
			return clipher.doFinal(data);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 解密
	 * 
	 * @param bytes
	 *            被解密的bytes
	 * @return 解密后的bytes
	 */
	private byte[] decrypt(byte[] bytes) {
		lock.lock();
		try {
			if (null == this.params) {
				clipher.init(Cipher.DECRYPT_MODE, secretKey);
			} else {
				clipher.init(Cipher.DECRYPT_MODE, secretKey, params);
			}
			return clipher.doFinal(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            数据
	 * @return 加密后的Hex
	 */
	public String encryptHex(String data) {
		return new String(encodeHex(encrypt(data.getBytes(Charset.forName("utf-8"))), DIGITS_UPPER));
	}

	/**
	 * 解密Hex表示的字符串
	 * 
	 * @param data
	 *            被解密的String
	 * @return 解密后的String
	 */
	public String decryptStr(String data) {
		return new String(decrypt(decodeHex(data.toCharArray())), Charset.forName("utf-8"));
	}

	/**
	 * 将字节数组转换为十六进制字符数组
	 *
	 * @param data
	 *            byte[]
	 * @param toDigits
	 *            用于控制输出的char[]
	 * @return 十六进制char[]
	 */
	private static char[] encodeHex(byte[] data, char[] toDigits) {
		int l = data.length;
		char[] out = new char[l << 1];
		// two characters from the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
			out[j++] = toDigits[0x0F & data[i]];
		}
		return out;
	}

	/**
	 * 将十六进制字符数组转换为字节数组
	 *
	 * @param hexData
	 *            十六进制char[]
	 * @return byte[]
	 * @throws RuntimeException
	 *             如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
	 */
	private static byte[] decodeHex(char[] hexData) {

		int len = hexData.length;

		if ((len & 0x01) != 0) {
			throw new RuntimeException("Odd number of characters.");
		}

		byte[] out = new byte[len >> 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; j < len; i++) {
			int f = toDigit(hexData[j], j) << 4;
			j++;
			f = f | toDigit(hexData[j], j);
			j++;
			out[i] = (byte) (f & 0xFF);
		}

		return out;
	}

	/**
	 * 将十六进制字符转换成一个整数
	 *
	 * @param ch
	 *            十六进制char
	 * @param index
	 *            十六进制字符在字符数组中的位置
	 * @return 一个整数
	 * @throws RuntimeException
	 *             当ch不是一个合法的十六进制字符时，抛出运行时异常
	 */
	private static int toDigit(char ch, int index) {
		int digit = Character.digit(ch, 16);
		if (digit == -1) {
			throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
		}
		return digit;
	}

}
