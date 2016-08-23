package org.light4j.utils.random;

import java.util.Random;
import java.util.UUID;

/**
 * @description:随机数生成工具类
 *
 * @author longjiazuo
 */
public class RandomUtil {
	/**
	 * 生成32位uuid
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 
	 * @Description 生成4位数字随机码
	 * @author longjiazuo
	 * @createtime 2016年4月13日下午12:33:07
	 */
	public static String getRandomVerifyCode() {
		return getRandomVerifyCode(4);
	}

	/**
	 * 
	 * @Description 生成count位数字随机码
	 * @author longjiazuo
	 * @createtime 2016年4月13日下午12:33:07
	 */
	public static String getRandomVerifyCode(int count) {
		StringBuffer sb = new StringBuffer();
		String str = "0123456789";
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			int num = r.nextInt(str.length());
			sb.append(str.charAt(num));
			str = str.replace((str.charAt(num) + ""), "");
		}
		return sb.toString();
	}
}
