package com.lc.zy.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class ConfigFile {
	
	private Resource resource;
	
	private File configFile;
	
	private long timestamp;
	
	private boolean refresh;
	
	private Properties props;
	
	/**
	 * @param location 配置文件的位置，支持spring资源文件的配置模式.
	 */
	public ConfigFile(String location) {
		this(location, false);
	}
	
	/**
	 * @param location 配置文件的位置，支持spring资源文件的配置模式.
	 * @param refresh 配置文件修改后自动刷新.
	 */
	public ConfigFile(String location, boolean refresh) {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			resource = resolver.getResource(location);
			configFile = resource.getFile();
			timestamp = configFile.lastModified();
			this.refresh = refresh;
			
			loadConfig();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private void loadConfig() throws IOException {
		if (props != null) {
			props.clear();
		} else {
			props = new Properties();
		}
		
		if (resource != null) {
			props.load(resource.getInputStream());
		}
	}


	private synchronized void refresh() {
		if (configFile.lastModified() > timestamp && refresh) {
			timestamp = configFile.lastModified();
			try {
				loadConfig();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getProperty(String key) {
		refresh();
		return props.getProperty(key);
	}


	public String getProperty(String key, String defaultValue) {
		refresh();
		return props.getProperty(key, defaultValue);
	}

}
