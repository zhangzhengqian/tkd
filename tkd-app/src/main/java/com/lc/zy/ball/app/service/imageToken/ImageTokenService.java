package com.lc.zy.ball.app.service.imageToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.common.bean.Auth;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.bean.KeyValueEntity;
import com.lc.zy.common.bean.Reason;
import com.lc.zy.common.bean.Success;

/**
 * 
 * <app上传图片token接口>
 * 
 * @author sl
 *
 */
@Service("image")
@Auth
public class ImageTokenService {

	private static Logger logger = LoggerFactory.getLogger(ImageTokenService.class);

	@Autowired
	private ImageTokenRepository imageTokenRepository;

	@Auth(false)
	public Success getToken(ClientRequest request) {
		String sn = request.getSn();
		try {
			String imageToken = imageTokenRepository.getImageToken();
			return new Success(sn, true, new KeyValueEntity("imageToken", imageToken));
		} catch (Exception e) {
			logger.error("imageToken_exception:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}

}
