package com.lc.zy.ball.boss.common.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.lc.zy.common.util.Globals;

@SuppressWarnings("serial")
public class TokenTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		String token = (String)this.pageContext.getSession().getAttribute(Globals.TOKEN_SESSION_KEY);
		if (StringUtils.isNotBlank(token)) {
			JspWriter out = this.pageContext.getOut();
			try {
				out.write(String.format("<input type='hidden' name='%s' value='%s' />", Globals.TOKEN_KEY, token));
			} catch (IOException e) {
				throw new JspException(e);
			}
		}
		return SKIP_BODY;
		
	}
	
	

}
