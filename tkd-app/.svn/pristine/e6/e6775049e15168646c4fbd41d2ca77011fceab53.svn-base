package com.lc.zy.ball.app.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <将emoji表情替换成*>
 *
 * @create：2015年9月9日 下午6:49:36
 * @author： sl
 * @param source
 * @return
 */
public class EmojiFilterUtils {
	public static String filterEmoji(String source) {  
		if(StringUtils.isNotBlank(source)){  
			return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");  
		}else{  
			return source;  
		}  
	}  
}
