package com.lc.zy.ball.crm.framework.system.studyFile.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.crm.common.SessionUtil;
import com.lc.zy.ball.crm.common.service.AbstractCacheService;
import com.lc.zy.ball.domain.oa.mapper.CrmUserFileMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.CrmUserFile;
import com.lc.zy.ball.domain.oa.po.CrmUserFileCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.ball.domain.oa.po.StatiumInfosCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.UUID;

@Service
@Transactional(readOnly=true)
public class StudyFileService extends AbstractCacheService{
	private static Logger logger = LoggerFactory.getLogger(StudyFileService.class);
	
	@Autowired
	private CrmUserFileMapper crmUserFileMapper;
	@Autowired
	private StatiumInfosMapper statiumInfosMapper;
	/**
	 * 
	 * <获取学习资料><功能具体实现>
	 *
	 * @create：2017年4月10日 上午9:55:17
	 * @author：ywl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<CrmUserFile> list(PageRequest pageRequest,Map<String, Object> searchParams,String statiumId){
		int total = 0;
		List<CrmUserFile> list = null;
		try {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			CrmUserFileCriteria crmUserFileCriteria = new CrmUserFileCriteria();
			CrmUserFileCriteria.Criteria cri = crmUserFileCriteria.createCriteria();
			cri.andStatiumIdEqualTo(statiumId);
			crmUserFileCriteria.setMysqlOffset(pageRequest.getOffset());
			crmUserFileCriteria.setMysqlLength(pageRequest.getPageSize());
			crmUserFileCriteria.setOrderByClause("ct desc");
			Criterias.bySearchFilter(cri, filters.values());
			total = crmUserFileMapper.countByExample(crmUserFileCriteria);
			list  = crmUserFileMapper.selectByExample(crmUserFileCriteria);
		} catch (Exception e) {
			logger.debug("获取学习资料List"+e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}
	/**
	 * 
	 * <保存学习资料><功能具体实现>
	 *
	 * @create：2017年4月10日 上午10:53:08
	 * @author：ywl
	 * @param crmUserFile
	 */
	@Transactional(readOnly=false)
	public void save(CrmUserFile crmUserFile){
		String fileUrls[] = crmUserFile.getFileUrl().split(",");
		String fileNames[] = crmUserFile.getFileName().split(",");
		String fileSuffix[] = crmUserFile.getFileSuffix().split(",");
		String fileSize[] = crmUserFile.getFileSize().split(",");
		String statiumId = SessionUtil.currentStatium();
		StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
		StatiumInfosCriteria.Criteria cri = statiumInfosCriteria.createCriteria();
		cri.andDgIdEqualTo(Integer.parseInt(statiumId));
		List<StatiumInfos> list = statiumInfosMapper.selectByExample(statiumInfosCriteria);
		if(list != null){
			String statiumName = list.get(0).getDgName();
			for(int i=0;i<fileUrls.length;i++){
				CrmUserFile user = new CrmUserFile();
				try {
					user.setId(UUID.get());
					user.setStatiumId(statiumId);
					user.setStatiumName(statiumName);
					user.setFileUrl(fileUrls[i]);
					user.setFileName(fileNames[i]);
					user.setFileSuffix(fileSuffix[i]);
					user.setFileSize(fileSize[i]);
					user.setCt(new Date());
					user.setCb(statiumId);
					crmUserFileMapper.insertSelective(user);
				} catch (Exception e) {
					logger.debug("保存学习资料"+e.getMessage());
				}
			}
		}
	}
	/**
	 * 
	 * <删除文件><功能具体实现>
	 *
	 * @create：2017年4月10日 下午12:56:32
	 * @author：ywl
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void delFile(String id){
		try {
			crmUserFileMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug("删除学习资料"+e.getMessage());
		}
	}
}
