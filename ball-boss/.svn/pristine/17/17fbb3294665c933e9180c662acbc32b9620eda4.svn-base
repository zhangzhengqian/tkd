package com.lc.zy.ball.boss.framework.ssouser.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.pay.wxap.util.MD5Util;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.ssouser.vo.CtaUserVo;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.Pageable;
import com.lc.zy.ball.domain.oa.mapper.CtaUserMapper;
import com.lc.zy.ball.domain.oa.po.CtaUser;
import com.lc.zy.ball.domain.oa.po.CtaUserCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.Encrypts;
import com.lc.zy.common.util.UUID;

@Component
@Transactional(readOnly = true)
public class CtaService extends AbstractCacheService {

private static final Logger logger = LoggerFactory.getLogger( CtaService.class);
	
	@Autowired
	private CtaUserMapper ctaUserMapper;
	
	/**
	 * <获取网协账户列表><功能具体实现>
	 * @Description: TODO
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @throws Exception   
	 * @return PageImpl<CatUser>  
	 * @throws
	 * @author zhaoxing
	 * @date 2017年1月10日 上午10:58:00
	 */
	public PageImpl<CtaUser> list(Pageable pageable, Map<String, Object> searchParams) throws Exception {
		logger.debug(searchParams.toString());
		CtaUserCriteria criteria = new CtaUserCriteria();
		CtaUserCriteria.Criteria cri = criteria.createCriteria();
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		criteria.setMysqlLength(pageable.getPageSize());
		criteria.setMysqlOffset(pageable.getOffset());
		 List<CtaUser> list = ctaUserMapper.selectByExample(criteria);
		int total = ctaUserMapper.countByExample(criteria);
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<CtaUser>();
		}else{
		}
		return new PageImpl<>(list, pageable, total);
	}
	
	 /**
	  * 
	  * 修改网协账户
	  * @Description: TODO
	  * @param ctaUser   
	  * @return void  
	  * @throws
	  * @author zhaoxing
	  * @date 2017年1月10日 下午4:33:12
	  */
	 @Transactional(readOnly = false)
	public void updateCtaUser(CtaUser ctaUser) {
		try {
			this.updateByPrimaryKeySelective(ctaUser, ctaUser.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("网协账户添加(CtaUserService--ctaForm)" + e.getMessage());
		}
	}
	/**
	 * 根据Id删除账户
	 * @Description: TODO
	 * @param id   
	 * @return void  
	 * @throws
	 * @author zhaoxing
	 * @date 2017年1月10日 下午4:33:23
	 */
	 @Transactional(readOnly=false)
	public void deleteCtaUserById(String id) {
		try {
			CtaUser ctaUser = this.selectByPrimaryKey(CtaUser.class, id);
			// 删除用户
			if(ctaUser!=null){
				this.deleteByPrimaryKey(CtaUser.class,ctaUser.getUserId());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除网协账户(CtaUserService--deleteCtaUser)" + e.getMessage());
		}
	}
	
	@Transactional(readOnly = false)
	public void save(CtaUser ctaUser) {
		 try {
			 if(StringUtils.isEmpty(ctaUser.getUserId())){
				 	ctaUser.setUserId(UUID.get());
	            	ctaUser.setCb(SessionUtil.currentUserId());
	            	ctaUser.setCreateTime(new Date());
	            	String[] hash = Encrypts.hashPassword(ctaUser.getPassword());
	            	ctaUser.setPassword(hash[0]);
	            	ctaUser.setSalt(hash[1]);
	            	ctaUser.setStatus("0");
	            	ctaUserMapper.insertSelective(ctaUser);
			 }else{
				 
				 this.updateByPrimaryKeySelective(ctaUser, ctaUser.getUserId());
			 }
			 
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	            throw new RuntimeException(e);
	        }
		
	}

	public CtaUser getCtaUserVo(String id) throws Exception {
		CtaUserVo vo = new CtaUserVo();
        if (CommonUtils.isNotEmpty(id)) {
        	CtaUser ctaUser = this.getCtaUser(id);
            if (ctaUser == null) {
                return null;
            }
            BeanUtils.copyProperties(ctaUser, vo);
          
          //设置图片
            String logo=null;
          vo.setImageUrl(logo);
        String techArea = vo.getAreaStr();
        String areaName = "";
        if(StringUtils.isNotEmpty(techArea)){
        	for(String area:techArea.split(";;")){
        		areaName+=Zonemap.split(area).get("area")+",";
        	}
        	vo.setAreaStr(Zonemap.split(techArea.split(";;")[0]).get("province")+" "+Zonemap.split(techArea.split(";;")[0]).get("city")+":"+areaName.substring(0,areaName.length()-1));
        }
    	vo.setLoginName(ctaUser.getLoginName());
    	vo.setSecMobile(ctaUser.getSecMobile());
    	vo.setProvince(ctaUser.getProvince());
    	vo.setCity(ctaUser.getCity());
    	ctaUser.setStatus("0");
       
	}
        return vo;
	}

	private CtaUser getCtaUser(String id) throws Exception {
		
		 return this.selectByPrimaryKey(CtaUser.class, id);
		
	}
}


