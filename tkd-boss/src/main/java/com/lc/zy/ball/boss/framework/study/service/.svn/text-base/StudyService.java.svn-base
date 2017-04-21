package com.lc.zy.ball.boss.framework.study.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.common.web.UploaderController;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.UserFileMapper;
import com.lc.zy.ball.domain.oa.mapper.UserMapper;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.domain.oa.po.UserFile;
import com.lc.zy.ball.domain.oa.po.UserFileCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.UUID;

@Service
public class StudyService extends AbstractCacheService{
	private static Logger logger = LoggerFactory.getLogger(UploaderController.class);
	@Autowired
	private UserFileMapper userFileMapper;
	
	@Autowired
	private UserMapper userMapper;
	/**
	 * 
	 * <文件列表><功能具体实现>
	 *
	 * @create：2017年4月7日 上午11:40:35
	 * @author：ywl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<UserFile> list(PageRequest pageRequest,Map<String, Object> searchParams){
		int total = 0;
		List<UserFile> list = null;
		try {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			UserFileCriteria userFileCriteria = new UserFileCriteria();
			userFileCriteria.setMysqlOffset(pageRequest.getOffset());
			userFileCriteria.setMysqlLength(pageRequest.getPageSize());
			userFileCriteria.setOrderByClause("ct desc");
			UserFileCriteria.Criteria cri = userFileCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			total = userFileMapper.countByExample(userFileCriteria);
			list = userFileMapper.selectByExample(userFileCriteria);
		} catch (Exception e) {
			logger.debug("获取学习资料List"+e.getMessage());
			e.printStackTrace();
		}
		return new PageImpl<>(list, pageRequest, total);
	}
	@Transactional(readOnly=false)
	public void save(UserFile userFile){
		String fileUrls[] = userFile.getFileUrl().split(",");
		String fileNames[] = userFile.getFileName().split(",");
		String fileSize[] = userFile.getFileSize().split(",");
		String fileSuffix[] = userFile.getFileSuffix().split(",");
		User users = getUser();
		for(int i=0;i<fileUrls.length;i++){
			UserFile user = new UserFile();
			try {
				user.setId(UUID.get());
				user.setUserId(users.getUserId());
				user.setUserName(users.getNickname());
				user.setFileUrl(fileUrls[i]);
				user.setFileName(fileNames[i]);
				user.setFileSize(fileSize[i]);
				user.setFileSuffix(fileSuffix[i]);
				user.setCt(new Date());
				user.setCb(users.getUserId());
				userFileMapper.insertSelective(user);
			} catch (Exception e) {
				logger.debug("添加学习资料：{}"+e.getMessage());
			}
		}
		
	}
	/**
	 * 
	 * <获取登录用户><功能具体实现>
	 *
	 * @create：2017年4月7日 下午2:31:26
	 * @author：ywl
	 * @return
	 */
	public User getUser(){
		User user = SessionUtil.currentUser();
		return user;
	}
	
	/**
	 * 
	 * <主键删除文件><功能具体实现>
	 *
	 * @create：2017年4月8日 下午5:15:01
	 * @author：zzq
	 * @param id
	 */
	public void deleteFile(String id){
		try {
			this.deleteByPrimaryKey(UserFile.class, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
