package com.lc.zy.ball.boss.framework.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.BadWordsMapper;
import com.lc.zy.ball.domain.oa.po.BadWords;
import com.lc.zy.ball.domain.oa.po.BadWordsCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.SynBadWordsUtil;
import com.lc.zy.common.util.UUID;

@Component
@Transactional(readOnly = true)
public class BadWordsService extends AbstractCacheService {
	private static Logger logger = LoggerFactory.getLogger(BadWordsService.class);

	@Autowired
	private BadWordsMapper badWordsMapper;
	
	@Autowired
	private SynBadWordsUtil synBadWordsUtil;

	/**
	 * 
	 * <禁词列表><功能具体实现>
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 * @author liangsh
	 * @date 2016年1月12日 下午6:58:54
	 */
	public Page<BadWords> find(PageRequest pageRequest, Map<String, Object> searchParams){
		List<BadWords> list = new ArrayList<BadWords>();
		int total = 0;
		try {
			BadWordsCriteria criteria = new BadWordsCriteria();
			BadWordsCriteria.Criteria cri = criteria.createCriteria();
			criteria.setMysqlLength(pageRequest.getPageSize());
			criteria.setMysqlOffset(pageRequest.getOffset());
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
			criteria.setOrderByClause("ct desc");
			total = badWordsMapper.countByExample(criteria);
			list = badWordsMapper.selectByExample(criteria);
			if(CollectionUtils.isEmpty(list)){
				list = new ArrayList<BadWords>();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取禁词list(BadWordsService--findBadWordsList)" + e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}
	
	/**
	 * 
	 * <新增禁词><功能具体实现>
	 * @param info
	 * @author liangsh
	 * @date 2016年1月12日 下午7:16:10
	 */
	@Transactional(readOnly = false)
	public void add(BadWords info){
		try {
			if(StringUtils.isNotBlank(info.getBadWord())){
				String words = info.getBadWord();
				if(words.contains("，")){
					String[] wordsArray = words.split("，");
					for (String word : wordsArray) {
						if(StringUtils.isNotBlank(word)){
							BadWords badWords = new BadWords();
							badWords.setBadWord(word.trim());
							badWords.setId(UUID.get());
							badWords.setCt(new Date());
							badWords.setLevel(info.getLevel());
							badWords.setCb(SessionUtil.currentUserId());
							try {
								this.insertSelective(badWords,badWords.getId());
								synBadWordsUtil.synBadWords(word.trim());
							} catch (Exception e) {
								logger.info(info.getBadWord() +"已存在，不可重复录入");
								continue;
							}
						}
					}
				}else{
					if(StringUtils.isNotBlank(info.getBadWord())){
						info.setId(UUID.get());
						info.setCt(new Date());
						info.setLevel(info.getLevel());
						info.setCb(SessionUtil.currentUserId());
						info.setBadWord(info.getBadWord().trim());
						try {
							this.insertSelective(info,info.getId());
							synBadWordsUtil.synBadWords(info.getBadWord().trim());
						} catch (Exception e) {
							logger.info(info.getBadWord() +"已存在，不可重复录入");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取添加(BadWordsService--add)" + e.getMessage());
		}
	}
}
