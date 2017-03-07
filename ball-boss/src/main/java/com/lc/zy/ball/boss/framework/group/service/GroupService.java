package com.lc.zy.ball.boss.framework.group.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.emsg.client.beans.DefPacket;
import com.emsg.client.beans.DefPayload;
import com.emsg.client.beans.Envelope;
import com.emsg.client.beans.IEnvelope;
import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.group.vo.GroupUserVo;
import com.lc.zy.ball.boss.framework.group.vo.GroupVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.EmsgGroupUserMapper;
import com.lc.zy.ball.domain.oa.mapper.EmsgGroupsMapper;
import com.lc.zy.ball.domain.oa.po.EmsgGroupUser;
import com.lc.zy.ball.domain.oa.po.EmsgGroupUserCriteria;
import com.lc.zy.ball.domain.oa.po.EmsgGroups;
import com.lc.zy.ball.domain.oa.po.EmsgGroupsCriteria;
import com.lc.zy.ball.domain.oa.po.EmsgServer;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.cache.RedisPool;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.emsgclient.EmsgPushClient;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;

@Service
@Transactional(readOnly = true)
public class GroupService extends AbstractCacheService {
    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private EmsgGroupsMapper emsgGroupsMapper;

    @Autowired
    private EmsgGroupUserMapper emsgGroupUserMapper;

    @Autowired
    private RedisPool redisPool;

    @Autowired
    private EmsgPushClient emsgPushClient;

    /**
     * 
     * <群组管理列表><功能具体实现>
     *
     * @create：2016年1月21日 下午4:50:45
     * @author： CYY
     * @param pageable
     * @param searchParams
     * @param isPage
     * @return
     * @throws Exception
     */
    public Page<GroupVo> find(PageRequest pageable, Map<String, Object> searchParams, boolean isPage) throws Exception {
        logger.debug(searchParams.toString());
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        EmsgGroupsCriteria emsgGroupsCriteria = new EmsgGroupsCriteria();
        EmsgGroupsCriteria.Criteria cri = emsgGroupsCriteria.createCriteria();
        if (isPage) {
            emsgGroupsCriteria.setMysqlLength(pageable.getPageSize());
            emsgGroupsCriteria.setMysqlOffset(pageable.getOffset());
        }
        Criterias.bySearchFilter(cri, filters.values());
        emsgGroupsCriteria.setOrderByClause("et desc");
        int total = emsgGroupsMapper.countByExample(emsgGroupsCriteria);
        List<EmsgGroups> groupList = emsgGroupsMapper.selectByExample(emsgGroupsCriteria);
        GroupVo groupVo = null;
        List<GroupVo> groupVoList = new ArrayList<GroupVo>();
        EmsgGroupUserCriteria emsgGroupUserCriteria = null;
        EmsgGroupUserCriteria.Criteria c = null;
        SsoUser user = null;
        for (EmsgGroups groups : groupList) {
            groupVo = new GroupVo();
            BeanUtils.copyProperties(groups, groupVo);
            emsgGroupUserCriteria = new EmsgGroupUserCriteria();
            c = emsgGroupUserCriteria.createCriteria();
            c.andGroupIdEqualTo(groups.getId());
            user = this.selectByPrimaryKey(SsoUser.class, groups.getUserId());
            groupVo.setNickname(user.getNickName());
            groupVo.setUserId(user.getId());
            groupVo.setMemberTotal(emsgGroupUserMapper.countByExample(emsgGroupUserCriteria));
            groupVoList.add(groupVo);
        }
        return new PageImpl<>(groupVoList, pageable, total);
    }

    /**
     * 
     * <详情><功能具体实现>
     *
     * @create：2016年1月21日 下午5:56:49
     * @author： CYY
     * @param id
     * @return
     * @throws Exception
     */
    public GroupVo detail(String id) throws Exception {
        EmsgGroups groups = this.selectByPrimaryKey(EmsgGroups.class, id);
        GroupVo groupVo = new GroupVo();
        BeanUtils.copyProperties(groups, groupVo);
        EmsgGroupUserCriteria emsgGroupUserCriteria = new EmsgGroupUserCriteria();
        EmsgGroupUserCriteria.Criteria c = emsgGroupUserCriteria.createCriteria();
        c.andGroupIdEqualTo(groups.getId());
        SsoUser user = this.selectByPrimaryKey(SsoUser.class, groups.getUserId());
        groupVo.setNickname(user.getNickName());
        groupVo.setUserId(user.getId());
        groupVo.setMemberTotal(emsgGroupUserMapper.countByExample(emsgGroupUserCriteria));
        return groupVo;
    }

    /**
     * 
     * <群组管理列表><功能具体实现>
     *
     * @create：2016年1月21日 下午4:50:45
     * @author： CYY
     * @param pageable
     * @param searchParams
     * @param isPage
     * @return
     * @throws Exception
     */
    public Page<GroupUserVo> userList(PageRequest pageable, Map<String, Object> searchParams, boolean isPage)
            throws Exception {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        EmsgGroupUserCriteria emsgGroupUserCriteria = new EmsgGroupUserCriteria();
        EmsgGroupUserCriteria.Criteria cri = emsgGroupUserCriteria.createCriteria();
        if (isPage) {
            emsgGroupUserCriteria.setMysqlLength(pageable.getPageSize());
            emsgGroupUserCriteria.setMysqlOffset(pageable.getOffset());
        }
        Criterias.bySearchFilter(cri, filters.values());
        emsgGroupUserCriteria.setOrderByClause("et desc");
        int total = emsgGroupUserMapper.countByExample(emsgGroupUserCriteria);
        List<EmsgGroupUser> groupUserList = emsgGroupUserMapper.selectByExample(emsgGroupUserCriteria);
        GroupUserVo groupUserVo = null;
        List<GroupUserVo> groupUserVoList = new ArrayList<GroupUserVo>();
        SsoUser user = null;
        for (EmsgGroupUser u : groupUserList) {
            groupUserVo = new GroupUserVo();
            user = this.selectByPrimaryKey(SsoUser.class, u.getUserId());
            BeanUtils.copyProperties(user, groupUserVo);
            groupUserVo.setCt(u.getCt());
            groupUserVo.setType(u.getType());
            groupUserVoList.add(groupUserVo);
        }
        return new PageImpl<>(groupUserVoList, pageable, total);
    }

    /**
     * 
     * <管理员解散群><功能具体实现>
     *
     * @create：2016年1月29日 上午10:09:53
     * @author： CYY
     * @param groupId
     */
    @Transactional(readOnly = false)
    public void disband(String groupId) {
        try {
            this.deleteByPrimaryKey(EmsgGroups.class, groupId);
            // 解散群的同时删除成员列表信息
            EmsgGroupUserCriteria emsgGroupUserCriteria = new EmsgGroupUserCriteria();
            EmsgGroupUserCriteria.Criteria cri = emsgGroupUserCriteria.createCriteria();
            cri.andGroupIdEqualTo(groupId);
            emsgGroupUserMapper.deleteByExample(emsgGroupUserCriteria);
            /** 解散群广播该群的消息 */
            Type typeToken = new TypeToken<EmsgServer>() {
            }.getType();
            String server = redisPool.getStr(com.lc.zy.common.Constants.EMSG_SERVER);
            EmsgServer emsgServer = MyGson.getInstance().fromJson(server, typeToken);
            DefPacket packet = new DefPacket();
            IEnvelope envelope = new Envelope();
            envelope.setAck(1);
            envelope.setId(UUID.get());
            String from = "boss@" + emsgServer.getDomain();
            envelope.setFrom(from);
            envelope.setType(2);
            DefPayload payload = new DefPayload();
            Map<String, String> attrs = new HashMap<>();
            envelope.setGid(groupId);
            packet.setEnvelope(envelope);
            attrs.put(com.lc.zy.common.Constants.Emsg.MESSAGE_TYPE, com.lc.zy.common.Constants.Emsg.DISBAND_MEMBER + "");
            payload.setAttrs(attrs);
            payload.setContent("您的群被管理员解散");
            packet.setPayload(payload);
            try {
                emsgPushClient.send(emsgServer.getLicense(), UUID.get(), packet);
            } catch (Exception e) {
                logger.error("GroupService disband push " + e.getMessage(), e);
            }
            /** 广播结束 */
        } catch (Exception e) {
            logger.error("GroupService disband: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
