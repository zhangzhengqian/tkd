package com.lc.zy.ball.boss.framework.corps.service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.corps.vo.CorpsVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.CorpsInfoMapper;
import com.lc.zy.ball.domain.oa.mapper.CorpsMemberMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.CorpsInfo;
import com.lc.zy.ball.domain.oa.po.CorpsInfoCriteria;
import com.lc.zy.ball.domain.oa.po.CorpsMember;
import com.lc.zy.ball.domain.oa.po.CorpsMemberCriteria;
import com.lc.zy.ball.domain.oa.po.Games;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;

@Service
@Transactional(readOnly = true)
public class CorpsService extends AbstractCacheService {

    private static Logger logger = LoggerFactory.getLogger(CorpsService.class);
    
    @Autowired
    private CorpsInfoMapper corpsInfoMapper;
    
    @Autowired
    private SsoUserMapper ssoUserMapper;
    
    @Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private CorpsMemberMapper corpsMemberMapper;

    /**
     * 战队积分列表 返回战队ID
     * @param request
     * @return
     * @throws Exception 
     */
    public Page<CorpsVo> listCorps(Map<String, Object> searchParams,Integer page,Integer size) throws Exception {
    	CorpsInfoCriteria criteria = new CorpsInfoCriteria();
    	CorpsInfoCriteria.Criteria cri = criteria.createCriteria();
    	cri.andStatusEqualTo(1);
    	logger.debug(searchParams.toString());
		int total = 0;
		// search_EQ_statiumName
		// 根据场馆名称检索场馆内的订单，那么先要得到场馆的id，再 equals 订单表
		PageRequest pageable = new PageRequest(page, size);
		
		String EQ_sportType = (String)searchParams.get("EQ_sportType");
		if(StringUtils.isNotBlank(EQ_sportType)){
			cri.andSportTypeEqualTo(EQ_sportType);
		}
		
		String EQ_name = (String)searchParams.get("EQ_name");
		if(StringUtils.isNotBlank(EQ_name)){
			cri.andNameLike("%"+EQ_name+"%");
		}
		
		String EQ_username = (String)searchParams.get("EQ_username");
		if(StringUtils.isNotBlank(EQ_username)){
			SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
			SsoUserCriteria.Criteria ssoCri = ssoUserCriteria.createCriteria();
			ssoCri.andNickNameEqualTo(EQ_username);
			List<SsoUser> users = ssoUserMapper.selectByExample(ssoUserCriteria);
			if(CollectionUtils.isEmpty(users)){
				return new PageImpl<>(new ArrayList<CorpsVo>(), pageable, 0);
			}
			SsoUser user = users.get(0);
			String userid = user.getId();
			cri.andCaptainEqualTo(userid);
		}
		
		int count = corpsInfoMapper.countByExample(criteria);
		if(count==0){
			return new PageImpl<>(new ArrayList<CorpsVo>(),pageable,0);
		}
		total = count;
		if(searchParams.get("EQ_sortBy")!=null&&StringUtils.isNotBlank((String)searchParams.get("EQ_sortBy"))){
			Integer EQ_sortBy = Integer.parseInt((String)searchParams.get("EQ_sortBy"));
			//当前人数排序
			if(EQ_sortBy==0){
				criteria.setOrderByClause("current_number desc");
			}else if(EQ_sortBy==1){//积分升序
				criteria.setOrderByClause("integral asc");
			}else if(EQ_sortBy==2){//积分降序
				criteria.setOrderByClause("integral desc");
			}
		}else{
			criteria.setOrderByClause("integral desc");
		}
		
    	criteria.setMysqlLength(pageable.getPageSize());
    	criteria.setMysqlOffset(pageable.getOffset());
    	List<CorpsInfo> corpsInfos = corpsInfoMapper.selectByExample(criteria);
    	List<CorpsVo> vos = new ArrayList<CorpsVo>();
    	CorpsVo vo = null;
    	for(CorpsInfo info:corpsInfos){
    		vo = new CorpsVo();
    		vo.setId(info.getId());
    		vo.setName(info.getName());
    		vo.setCurrentNumber(info.getCurrentNumber());
    		vo.setSportType(CommonOAUtils.sportsEToC(info.getSportType()));
    		SsoUser captain = this.selectByPrimaryKey(SsoUser.class, info.getCaptain());
    		vo.setCaptainStr(captain.getNickName());
    		vo.setIntegral(info.getIntegral());
    		vo.setPhone(info.getPhone());
    		vo.setArea(info.getArea());
    		vo.setActivityArea(info.getActivityArea());
    		vo.setCt(info.getCt());
    		Map<String, Object> paras = new HashMap<String, Object>();
    		paras.put("corpsId", info.getId());
    		/*String sqlCount = FreeMarkerUtils.format("/template/corps/corps_game_count.ftl", paras);
    		List<Map<String,Object>> gameCounts = jdbcTemplate.queryForList(sqlCount);
    		int gameCount = new BigDecimal((long)gameCounts.get(0).get("cont")).intValue();*/
    		vo.setGameCount(info.getTotalNumber());
    		paras.clear();
    		paras.put("area", info.getArea());
    		paras.put("corpsId", info.getId());
    		String sqlCount = FreeMarkerUtils.format("/template/corps/corps_integral_rank.ftl", paras);
            List<Map<String,Object>> integralNumMap = jdbcTemplate.queryForList(sqlCount);
            Object integralNum = integralNumMap.get(0).get("rank");
            vo.setIntegralNum(String.valueOf(integralNum));
            vo.setLogo(info.getLogo());
    		vos.add(vo);
    	}
    	return new PageImpl<>(vos, pageable, total);
    }
    
    @Transactional(readOnly = false)
    public void save(CorpsInfo info,String memberList) throws Exception{
    	Map<String,String> zoneMap = Zonemap.split(info.getArea());
    	String area = "";
    	if("市辖区".equals(zoneMap.get("city"))){
    		area = zoneMap.get("province");
    	}else{
    		area = zoneMap.get("city");
    	}
    	if(!this.checkCorpsByArea(area, info.getName())){
    		throw new RuntimeException("战队名称已被占用");
    	}
    	String userId = SessionUtil.currentUserId();
    	info.setId(UUID.get());
		info.setArea(area);
		info.setCt(new Date());
		info.setEt(new Date());
		info.setCb(userId);
		info.setEb(userId);
		info.setStatus(1);
		info.setWinCount(0);
		info.setDrawCount(0);
		info.setLooseCount(0);
		info.setTotalNumber(0);
		info.setIntegral(0);
		if(StringUtils.isNotBlank(memberList)){
			Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
			List<Map<String,String>> members = MyGson.getInstance().fromJson(memberList, type);
			if(CollectionUtils.isNotEmpty(members)){
				info.setCurrentNumber(1+members.size());
				CorpsMember corpsMember = null;
				for(Map<String,String> member:members){
					String logo = member.get("logo");
					String name = member.get("name");
					String sex = member.get("sex");
					String age = member.get("age");
					String phone = member.get("phone");
					corpsMember = new CorpsMember();
					corpsMember.setId(UUID.get());
					corpsMember.setAge(Integer.parseInt(age));
					corpsMember.setPhone(phone);
					corpsMember.setName(name);
					corpsMember.setSex(sex.equals("男")?1:0);
					corpsMember.setLogo(logo);
	                corpsMember.setCb(userId);
	                corpsMember.setCt(new Date());
	                corpsMember.setCorpsId(info.getId());
	                corpsMember.setQyFlag(0);  // 是否球友圈用户0为否
	                corpsMember.setStatus(1);  // 是否同意加入针对1同意
	                try {
	                	corpsMemberMapper.insertSelective(corpsMember);
	        		} catch (Exception e) {
	        			logger.error(e.getMessage(),e);
	        			throw new RuntimeException();
	        		}
				}
			}else{
				info.setCurrentNumber(1);
			}
		}else{
			info.setCurrentNumber(1);
		}
		SsoUser user = this.selectByPrimaryKey(SsoUser.class, info.getCaptain());
		CorpsMember member = new CorpsMember();
    	member.setId(UUID.get());
    	member.setAge(user.getAge());
    	
    	member.setCorpsId(info.getId());
    	member.setName(user.getNickName());
    	member.setPhone(user.getPhone());
    	member.setQyFlag(1);
    	member.setStatus(1);
    	member.setUserId(user.getId());
    	member.setLogo(user.getPhoto());
    	member.setCt(new Date());
        member.setCb(user.getId());
        try {
			this.insertSelective(member, member.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException();
		}
		try {
			this.insertSelective(info, info.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException();
		}
		
    }
    
    /**
     * 检查同一地区战队名称不能相同
     * 
     * @param area
     * @param name
     * @return
     */
    public boolean checkCorpsByArea(String area, String name) {
        CorpsInfoCriteria criteria = new CorpsInfoCriteria();
        CorpsInfoCriteria.Criteria cri = criteria.createCriteria();
        cri.andAreaEqualTo(area);
        cri.andNameEqualTo(name);
        cri.andStatusEqualTo(1);
        int count = corpsInfoMapper.countByExample(criteria);
        if (count == 0) {
            return true;
        }
        return false;
    }
    
    @Transactional(readOnly = false)
    public void update(CorpsInfo info,String memberList) throws Exception{
    	Map<String,String> zoneMap = Zonemap.split(info.getArea());
    	String area = "";
    	if("市辖区".equals(zoneMap.get("city"))){
    		area = zoneMap.get("province");
    	}else{
    		area = zoneMap.get("city");
    	}
    	if(!this.checkCorpsByArea(area, info.getName())){
    		throw new RuntimeException("战队名称已被占用");
    	}
    	try {
    		CorpsMemberCriteria criteria = new CorpsMemberCriteria();
    		CorpsMemberCriteria.Criteria cri = criteria.createCriteria();
    		cri.andCorpsIdEqualTo(info.getId());
			corpsMemberMapper.deleteByExample(criteria);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException();
		}
    	String userId = SessionUtil.currentUserId();
		info.setArea(area);
		info.setEt(new Date());
		info.setEb(userId);
		if(StringUtils.isNotBlank(memberList)){
			Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
			List<Map<String,String>> members = MyGson.getInstance().fromJson(memberList, type);
			if(CollectionUtils.isNotEmpty(members)){
				info.setCurrentNumber(1+members.size());
				CorpsMember corpsMember = null;
				for(Map<String,String> member:members){
					String logo = member.get("logo");
					String name = member.get("name");
					String sex = member.get("sex");
					String age = member.get("age");
					String phone = member.get("phone");
					corpsMember = new CorpsMember();
					corpsMember.setId(UUID.get());
					corpsMember.setAge(Integer.parseInt(age));
					corpsMember.setPhone(phone);
					corpsMember.setName(name);
					corpsMember.setSex(sex.equals("男")?1:0);
					corpsMember.setLogo(logo);
	                corpsMember.setCb(userId);
	                corpsMember.setCt(new Date());
	                corpsMember.setCorpsId(info.getId());
	                corpsMember.setQyFlag(0);  // 是否球友圈用户0为否
	                corpsMember.setStatus(1);  // 是否同意加入针对1同意
	                try {
	                	corpsMemberMapper.insertSelective(corpsMember);
	        		} catch (Exception e) {
	        			logger.error(e.getMessage(),e);
	        			throw new RuntimeException();
	        		}
				}
			}else{
				info.setCurrentNumber(1);
			}
		}else{
			info.setCurrentNumber(1);
		}
		SsoUser user = this.selectByPrimaryKey(SsoUser.class, info.getCaptain());
		CorpsMember member = new CorpsMember();
    	member.setId(UUID.get());
    	member.setAge(user.getAge());
    	member.setLogo(user.getPhoto());
    	member.setCorpsId(info.getId());
    	member.setName(user.getNickName());
    	member.setPhone(user.getPhone());
    	member.setQyFlag(1);
    	member.setStatus(1);
    	member.setUserId(user.getId());
    	member.setCt(new Date());
        member.setCb(user.getId());
        try {
			this.insertSelective(member, member.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException();
		}
		try {
			this.updateByPrimaryKeySelective(info, info.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException();
		}
		
    }
    
    @Transactional(readOnly = false)
    public void disband(String corpsId){
    	try {
    		CorpsInfo corps = new CorpsInfo();
            corps.setId(corpsId);
            corps.setStatus(0);
            this.updateByPrimaryKeySelective(corps, corpsId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException();
		}
    	try {
    		CorpsMemberCriteria criteria = new CorpsMemberCriteria();
    		CorpsMemberCriteria.Criteria cri = criteria.createCriteria();
    		cri.andCorpsIdEqualTo(corpsId);
			corpsMemberMapper.deleteByExample(criteria);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException();
		}
    }
    
    public Map<String,Object> view(String corpsId){
    	
    	Map<String,Object> result = new HashMap<String, Object>();
		//战队信息
    	CorpsInfo info = corpsInfoMapper.selectByPrimaryKey(corpsId);
    	String captainId = info.getCaptain();
    	SsoUser user;
		try {
			user = this.selectByPrimaryKey(SsoUser.class, captainId);
			info.setCaptain(user.getNickName());
			info.setSportType(CommonOAUtils.sportsEToC(info.getSportType()));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		result.put("info", info);
		//成员信息
		CorpsMemberCriteria criteria = new CorpsMemberCriteria();
		CorpsMemberCriteria.Criteria cri = criteria.createCriteria();
		cri.andCorpsIdEqualTo(corpsId);
		List<CorpsMember> members = corpsMemberMapper.selectByExample(criteria);
		result.put("mem", members);
		
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("corpsId", info.getId());
		/*String sqlCount = FreeMarkerUtils.format("/template/corps/corps_game_count.ftl", paras);
		List<Map<String,Object>> gameCounts = jdbcTemplate.queryForList(sqlCount);
		int gameCount = new BigDecimal((long)gameCounts.get(0).get("cont")).intValue();*/
		result.put("gameCount", info.getTotalNumber());
		
		paras.clear();
		paras.put("area", info.getArea());
		paras.put("corpsId", info.getId());
		String sqlCount = FreeMarkerUtils.format("/template/corps/corps_integral_rank.ftl", paras);
        List<Map<String,Object>> integralNumMap = jdbcTemplate.queryForList(sqlCount);
        Object integralNum = integralNumMap.get(0).get("rank");
        result.put("integralNum", integralNum);
		return result;
    }
    
    public Map<String,Object> get(String corpsId){
    	
    	Map<String,Object> result = new HashMap<String, Object>();
		//战队信息
    	CorpsInfo info = corpsInfoMapper.selectByPrimaryKey(corpsId);
    	String captainId = info.getCaptain();
    	SsoUser user;
		try {
			user = this.selectByPrimaryKey(SsoUser.class, captainId);
			info.setCaptain(user.getNickName());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		result.put("info", info);
		//成员信息
		CorpsMemberCriteria criteria = new CorpsMemberCriteria();
		CorpsMemberCriteria.Criteria cri = criteria.createCriteria();
		cri.andCorpsIdEqualTo(corpsId);
		List<CorpsMember> members = corpsMemberMapper.selectByExample(criteria);
		result.put("mem", members);
		result.put("captainId", captainId);
		result.put("mem_json", MyGson.getInstance().toJson(members));
		
		return result;
    }
    
    /**
     * 检查同一地区战队名称不能相同
     * 
     * @param area
     * @param name
     * @return
     * @throws Exception
     */
    public Map<String,List<Map<String,Object>>> getVs(String corpsId) throws Exception {
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("corpsId", corpsId);
        String sqlList = FreeMarkerUtils.format("/template/corps/corps_games.ftl", paras);
        logger.debug(sqlList);
        List<Map<String, Object>> gameMaps = jdbcTemplate.queryForList(sqlList);
        Map<String, Object> result;
        Map<String,List<Map<String,Object>>> result_ = new HashMap<String, List<Map<String,Object>>>();
        for (Map<String, Object> gameInfo : gameMaps) {
            result = new HashMap<String, Object>();
            String gamesId = (String) gameInfo.get("gameId");
            Games game = this.selectByPrimaryKey(Games.class, gamesId);
            String gameName = game.getName();
            List<Map<String,Object>> inners = null;
            if(result_.containsKey(gameName)){
            	inners = result_.get(gameName);
            }else{
            	inners = new ArrayList<Map<String,Object>>();
            }
            String a = (String) gameInfo.get("a");
            CorpsInfo info1 = this.selectByPrimaryKey(CorpsInfo.class, a);
            String name1 = info1.getName();
            String b = (String) gameInfo.get("b");
            CorpsInfo info2 = this.selectByPrimaryKey(CorpsInfo.class, b);
            String name2 = info2.getName();
            Integer score_a = (Integer) gameInfo.get("score_a");
            Integer score_b = (Integer) gameInfo.get("score_b");
            Integer integration_a = (Integer) gameInfo.get("integration_a");
            Integer integration_b = (Integer) gameInfo.get("integration_b");
            Integer victory_a = (Integer) gameInfo.get("victory_a");
            Integer victory_b = (Integer) gameInfo.get("victory_b");
            String game_time = (String) gameInfo.get("game_time");
            result.put("name1", name1);
            result.put("name2", name2);
            result.put("gameName", gameName);
            result.put("gameTime", game_time);
            result.put("score1", score_a);
            result.put("score2", score_b);
            if(corpsId.equals(a)){
            	result.put("integration", integration_a);
            }
            if(corpsId.equals(b)){
            	result.put("integration", integration_b);
            }
            String victory = "";
            if(corpsId.equals(a)){
            	if(victory_a==1){
            		victory = "胜";
            	}else if(victory_a==0){
            		victory = "平";
            	}else{
            		victory = "负";
            	}
            	result.put("victory", victory);
            }
            if(corpsId.equals(b)){
            	if(victory_b==1){
            		victory = "胜";
            	}else if(victory_b==0){
            		victory = "平";
            	}else{
            		victory = "负";
            	}
            	result.put("victory", victory);
            }
            inners.add(result);
            result_.put(gameName, inners);
        }

        return result_;
    }
}
