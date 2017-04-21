package com.lc.zy.ball.boss.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.Base64Uploader;
import com.baidu.ueditor.upload.Uploader;

public class UeditorCustomUploader extends Uploader{
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;
	
	public UeditorCustomUploader(HttpServletRequest request,
			Map<String, Object> conf) {
		super(request, conf);
		this.request = request;
		this.conf = conf;
	}

	public State doExec2() {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;
	
		if ("true".equals(this.conf.get("isBase64"))) {
			state = Base64Uploader.save(this.request.getParameter(filedName),
					this.conf);
		} else {
			state = UeditorCustomBinaryUploader.save(this.request, this.conf);
		}
	
		return state;
	}
}
