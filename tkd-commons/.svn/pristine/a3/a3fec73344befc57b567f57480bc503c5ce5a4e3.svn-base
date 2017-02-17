package com.lc.zy.common.util;

import org.apache.commons.lang3.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


public abstract class Pinyins {
	
	/**
	 * 默认的分隔符.
	 */
	public static final String DEFAULT_SEPARATOR = " ";
	
	/**
	 * 把汉字字符串转换成拼音.
	 * @param src 原始字符串
	 * @return 以空格分隔的拼音字符, 汉字外的字符原样返回.
	 */
	public static String convert(String src) {
		return convert(src, null);
	}

	/**
	 * 把汉字字符串转换成拼音.
	 * @param src 原始字符串
	 * @param sep 拼音间的分隔符
	 * @return 以sep分隔的拼音字符, 汉字外的字符原样返回.
	 */
	public static String convert(String src, String sep) {
		HanyuPinyinOutputFormat hpof = new HanyuPinyinOutputFormat();
		hpof.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		hpof.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		hpof.setVCharType(HanyuPinyinVCharType.WITH_V);
		
		sep = sep == null ? DEFAULT_SEPARATOR : sep;
		
		boolean ns = true;//是否需要添加分隔符
		
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < src.length(); i++) {
			String[] py = null;
			try {
				py = PinyinHelper.toHanyuPinyinStringArray(src.charAt(i), hpof);
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}
			if (py == null) {
				if(ns) {
					result.append(sep);
				}
				result.append(src.charAt(i));
				ns = false;
			} else {
				if (result.length() > 0)
					result.append(sep);
				result.append(StringUtils.capitalize(py[0]));
				ns = true;
			}
		}
		
		return result.toString();
	}

}
