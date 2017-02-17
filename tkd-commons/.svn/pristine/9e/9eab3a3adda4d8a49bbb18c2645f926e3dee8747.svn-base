package com.lc.zy.common.bean;

import java.util.List;

public class Reason {
	
	public Reason(String errorCode, String reason) {
		super();
		this.errorCode = errorCode;
		this.reason = reason;
	}

	

	public Reason(String errorCode, String reason, String sourceVersion) {
		super();
		this.errorCode = errorCode;
		this.reason = reason;
		this.sourceVersion = sourceVersion;
	}


	public Reason(String errorCode, String reason, String sourceVersion, List<String> localIps) {
		this.errorCode = errorCode;
		this.reason = reason;
		this.sourceVersion = sourceVersion;
		this.localIps = localIps;
	}

	private String errorCode = null;
	private String reason = null;
	private String sourceVersion = null;
	private List<String> localIps = null;

	public List<String> getLocalIps() {
		return localIps;
	}

	public void setLocalIps(List<String> localIps) {
		this.localIps = localIps;
	}

	public String getSourceVersion() {
		return sourceVersion;
	}
	public void setSourceVersion(String sourceVersion) {
		this.sourceVersion = sourceVersion;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
