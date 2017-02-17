package com.lc.zy.ball.app.service.imageToken;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lc.zy.ball.app.common.AbstractCacheService;
import com.lc.zy.common.bean.CacheKeys;
import com.qiniu.util.Auth;

@Repository
public class ImageTokenRepository extends AbstractCacheService implements CacheKeys {

	private static Logger logger = LoggerFactory.getLogger(ImageTokenRepository.class);

	@Resource(name = "configs")
	private Map<String, Object> configs = null;

	/**
	 * 
	 * <获取上传图片token><功能具体实现>
	 *
	 * @create：2016年5月13日 上午11:16:35
	 * @author：sl
	 * @return
	 */
	public String getImageToken() {
		// 密钥配置
		Auth auth = Auth.create((String) configs.get("fileserver.access_key"),
				(String) configs.get("fileserver.secret_key"));
		return auth.uploadToken((String) configs.get("fileserver.bucketname"));
	}
}
