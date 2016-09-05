package org.light4j.utils.base;

import java.util.regex.Pattern;

/**
 * 手机号校验
 * 
 * @author longjiazuo
 * 
 */
public class MobileValidator {
	public static void main(String[] args) throws Exception {
		String mobile1 = "17001098461";
		String mobile2 = "17001098461,15201392949";
		boolean b = isMobile(mobile1);
		boolean b2 = isMobile(mobile2);
		System.out.println(b + "--" + b2);
	}
	
	/**
	 * 三大运营商最新号段 合作版
	 * 移动号段：134 135 136 137 138 139 147 150 151 152 157 158 159 178 182 183 184 187 188
	 * 联通号段：
	 * 130 131 132 145 155 156 171 175 176 185 186
	 * 电信号段：
	 * 133 149 153 173 177 180 181 189
	 * 虚拟运营商：
	 * 170
	 * 支持验证多个手机号，用逗号隔开
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		boolean flag = false;
		if (mobile.length() == 0) {
			return false;
		}
		String[] mobiles = mobile.split(",");
		int len = mobiles.length;
		if (len == 1) {
			return Pattern.matches("^((13[0-9])|(14[5,7,9])|(15[^4,\\D])|(17[0,1,3,5-8])|(18[0-9]))\\d{8}$",mobile);
		} else {
			for (int i = 0; i < len; i++) {
				if (isMobile(mobiles[i])) {
					flag = true;
				} else {
					flag = false;
				}
			}
		}
		return flag;
	}
}
