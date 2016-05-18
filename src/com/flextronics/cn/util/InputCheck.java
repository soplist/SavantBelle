package com.flextronics.cn.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputCheck {

	/**
	 * check whether the email address is correct 
	 * @param emailAddress
	 * @return
	 */
	public static boolean isEmail(String emailAddress){

		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\." +
				"[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(emailAddress);
		return matcher.matches();
	}
	
	/**
	 * check whether the passwords which you provide are consistent
	 * @param passwd1
	 * @param passwd2
	 * @return
	 */
	public static boolean isPasswdConsistent(String passwd1, String passwd2){

		if (passwd1==null || passwd2==null) {
			return false;
		}
		
		if (passwd1.endsWith(passwd2)) {
			return true;
		}
		return false;
	}
}
