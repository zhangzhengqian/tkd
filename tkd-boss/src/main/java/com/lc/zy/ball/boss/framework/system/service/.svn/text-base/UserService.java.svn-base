package com.lc.zy.ball.boss.framework.system.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.Constants.UserStatus;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.UserContext;
import com.lc.zy.ball.boss.common.security.ShiroCacheManager;
import com.lc.zy.ball.boss.common.security.ShiroDbRealm.ShiroUser;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.UserMapper;
import com.lc.zy.ball.domain.oa.mapper.UserRoleMapper;
import com.lc.zy.ball.domain.oa.mapper.ex.UserMapperEx;
import com.lc.zy.ball.domain.oa.po.Role;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.domain.oa.po.UserCriteria;
import com.lc.zy.ball.domain.oa.po.UserCriteria.Criteria;
import com.lc.zy.ball.domain.oa.po.UserRoleCriteria;
import com.lc.zy.ball.domain.oa.po.UserRoleKey;
import com.lc.zy.ball.domain.oa.po.ex.UserEx;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.exception.ServiceException;
import com.lc.zy.common.util.Encrypts;
import com.lc.zy.common.util.UUID;

/**
 * 用户管理业务类.
 * 
 */
@Component
//@Transactional(value="core", readOnly = true)
@Transactional(readOnly = true)
public class UserService extends AbstractCacheService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserMapperEx userMapperEx;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Autowired
    private ShiroCacheManager shiroCacheManager;
    
    @Autowired
    private SysConfigService configService;
    
    @Autowired
    private RoleService roleService;

    /**
     * 获取我管理的用户id列表
     * @param cb
     * @return
     */
    public List<String> findUsersByCb(String cb){
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.createCriteria().andCbEqualTo(cb);
        List<User> list = userMapper.selectByExample(userCriteria);
        List<String> ids = null;//new ArrayList<String>();
        try {
            ids = new ArrayList<String>();
            for(User u : list){
                ids.add(u.getUserId());
            }
        }catch(Exception e){}
        return ids;
    }
    
    
//  @Transactional(value="core", readOnly = false)
    @Transactional(readOnly = false)
    public String saveUser(User user) throws Exception {
        String currentUserName = getCurrentUserName();
        if (isSupervisor(user)) {
            logger.warn("操作员'{}'尝试修改超级管理员用户", getCurrentUserName());
            if (currentUserName!=null && currentUserName.equals(user.getLoginName())) {
                //超级用户才能修改自己
            } else {
                throw new ServiceException("不能修改超级管理员用户");
            }
        }
        if (StringUtils.isNotBlank(user.getUserId())){
            super.clean(User.class, user.getUserId());
            userMapper.updateByPrimaryKeySelective(user);
        } else {
            if (StringUtils.isNotBlank(user.getPassword())) {
                //创建用户时需要加密明文密码，修改用户信息时不修改密码
                entryptPassword(user);
            }
            user.setUserId(UUID.get());
            user.setCreateTime(new Date());
            userMapper.insertSelective(user);
        }
        
        saveUserRole(user);
        
        shiroCacheManager.clear();
        return user.getUserId();
    }

    /**
     * 保存用户角色关系.
     * 
     * @param user
     */
    private void saveUserRole(User user) {
        deleteUserRole(user);
        UserEx ue = (UserEx)user;
        //modify by liangc 150731 : 将角色与用户修改成多对多关系
        for(String roleId : ue.getRoleIds()){
            UserRoleKey ur = new UserRoleKey();
            ur.setRoleId(roleId);
            ur.setUserId(user.getUserId());
            userRoleMapper.insert(ur);
        }
    }

    private void deleteUserRole(User user) {
        UserRoleCriteria urc = new UserRoleCriteria();
        UserRoleCriteria.Criteria cri = urc.createCriteria();
        cri.andUserIdEqualTo(user.getUserId());
        userRoleMapper.deleteByExample(urc);
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user) {
        if (user.getPassword() == null)
            return;
        
        String[] hash = Encrypts.hashPassword(user.getPassword());
        user.setPassword(hash[0]);
        user.setSalt(hash[1]);
    }

    public List<User> searchUser(Map<String, Object> searchMap) {
        UserCriteria criteria = parseSearchParams(searchMap);
        return userMapper.selectByExample(criteria);
    }

    private UserCriteria parseSearchParams(Map<String, Object> searchMap) {
        if (searchMap == null)
            return null;
        
        UserCriteria userCriteria = new UserCriteria();
        UserCriteria.Criteria cri = userCriteria.createCriteria();
        if (searchMap.get("loginName") != null) {
            cri.andLoginNameLike(searchMap.get("loginName").toString());
        }
        
        return userCriteria;
    }


    /**
     * 判断是否超级管理员.
     */
    private boolean isSupervisor(User user) {
        return user.getUserId() != null && "1".equals(user.getUserId());
    }

    /**
     * 按主键查询用户
     * 
     * @param id
     * @return
     */
    public User getUser(String id) {
        try {
            return selectByPrimaryKey(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前用户数量.
     */
    public int getUserCount() {
        return userMapper.countByExample(null);
    }

    /**
     * 按账号查询用户
     * 
     * @param loginName
     * @return
     * @throws Exception
     */
    public UserEx getUserExByLoginName(String loginName) throws Exception {
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("EQ_loginName", loginName);
        return userMapperEx.getUserExByFilter(SearchFilter.parse(searchParams).values());
    }

    /**
     * 取出Shiro中的当前用户LoginName.
     */
    private String getCurrentUserName() {
        try{
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.getLoginName();
        }catch(Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 删除用户
     * 
     * @param id
     */
    @Transactional(readOnly = false)
    public void deleteUser(String id) {
        User user = getUser(id);
        if (isSupervisor(user))
            throw new ServiceException("不能删除超级管理员用户");
        
        //1、删除角色关系
        deleteUserRole(user);
        
        //2、删除用户
        try {
            deleteByPrimaryKey(User.class,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 保存用户角色关系.
     * 
     * @param uid 用户ID
     * @param rids 角色ID集合
     */
    @Transactional(readOnly = false)
    public void saveUserRole(String uid, Collection<String> rids) {
        //先删除已有关系
        UserRoleCriteria urc = new UserRoleCriteria();
        UserRoleCriteria.Criteria cri = urc.createCriteria();
        cri.andUserIdEqualTo(uid);
        userRoleMapper.deleteByExample(urc);
        
        for (String rid : rids) {
            UserRoleKey ur = new UserRoleKey();
            ur.setUserId(uid);
            ur.setRoleId(rid);
            
            userRoleMapper.insert(ur);
        }
        
        shiroCacheManager.clear();
    }

    /**
     * 更新用户的最后登录IP和时间.
     * 
     * @param userId
     * @param ip
     */
//  @Transactional(value="core", readOnly = false)
    @Transactional(readOnly = false)
    public void updateUserLoginInfo(String userId, String ip) {
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            User user = new User();
            user.setUserId(userId);
            user.setLastIp(user.getLatestIp());
            user.setLastTime(user.getLatestTime());
            user.setLatestIp(ip);
            user.setLatestTime(new Date());
            updateByPrimaryKeySelective(user,user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 分页查询用户
     * 
     * @param searchParams
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public Page<User> find(Map<String, Object> searchParams, int page, int size) throws Exception {
        PageRequest pageable = new PageRequest(page, size);
        
        UserCriteria rc = new UserCriteria();
        rc.setMysqlOffset(pageable.getOffset());
        rc.setMysqlLength(pageable.getPageSize());
        UserCriteria.Criteria cri = rc.createCriteria();
        cri.andDeleteFlagIsNull();
        if (searchParams != null) {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Criterias.bySearchFilter(cri, filters.values());
        }
        
        rc.setOrderByClause("create_time desc");
        
        int total = userMapper.countByExample(rc);
        List<User> list = userMapper.selectByExample(rc);
        
        return new PageImpl<>(list, pageable, total);
    }
    
    
/*  public Page<User> find(Map<String,Object> searchParams,int page, int size) throws Exception {
        PageRequest pageable = new PageRequest(page, size);
        
        UserCriteria rc = new UserCriteria();
        rc.setMysqlOffset(pageable.getOffset());
        rc.setMysqlLength(pageable.getPageSize());
        UserCriteria.Criteria cri = rc.createCriteria();
        cri.deleteFlagIsNull();
        if (searchParams != null) {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Criterias.bySearchFilter(cri, filters.values());
        }
        
        rc.setOrderByClause("create_time desc");
        
        int total = userMapper.countByExample(rc);
        List<User> list = userMapper.selectByExample(rc);
        
        return new PageImpl<>(list, pageable, total);
    }*/
    
    /**
     * 根据userid查询删除标志位是否为null
     */
    public void isDeleteUser(String loginName)throws AuthenticationException{
        UserCriteria userCriteria = new UserCriteria();
        Criteria criteria = userCriteria.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
//      int userCont = userMapper.selectByLoginName(loginName);
        int userCont = userMapper.countByExample(userCriteria);
        if(userCont == 0){
            throw new AuthenticationException();
        }
    }
    
    /**
     * 分页查询用户
     * 
     * @param searchParams
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public Page<User> findByKeFu(Map<String,Object> searchParams,int page, int size) throws Exception {
        PageRequest pageable = new PageRequest(page, size);
        
        UserCriteria rc = new UserCriteria();
        rc.setMysqlOffset(pageable.getOffset());
        rc.setMysqlLength(pageable.getPageSize());
        UserCriteria.Criteria cri = rc.createCriteria();
        cri.andDeleteFlagIsNull();
        cri.andCbIsNotNull();
        if (searchParams != null) {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Criterias.bySearchFilter(cri, filters.values());
        }
        
        rc.setOrderByClause("create_time desc");
        
        int total = userMapper.countByExample(rc);
        List<User> list = userMapper.selectByExample(rc);
        
        return new PageImpl<>(list, pageable, total);
    }

    public Page<User> find(PageRequest pageable, Map<String, Object> searchParams,int type) throws Exception {
        //如果有角色这个条件，则先查出对应角色
        List<String> userIdList = new ArrayList<String>();
        if(type == 1){
            //渠道用户列表不查询
            Role role = roleService.findByRoleCode("channel");
            if(role != null){
                String roleId = role.getRoleId();
                UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
                userRoleCriteria.createCriteria().andRoleIdEqualTo(roleId);
                List<UserRoleKey> ur = userRoleMapper.selectByExample(userRoleCriteria);
                //searchParams.remove("EQ_roleId");
                for(UserRoleKey k : ur){
                    userIdList.add(k.getUserId());
                }
            }
        }else{
            if(searchParams.containsKey("EQ_roleId")){
                String roleId = searchParams.get("EQ_roleId").toString();
                if(StringUtils.isNotBlank(roleId)){
                    UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
                    userRoleCriteria.createCriteria().andRoleIdEqualTo(roleId);
                    List<UserRoleKey> ur = userRoleMapper.selectByExample(userRoleCriteria);
                    //searchParams.remove("EQ_roleId");
                    for(UserRoleKey k : ur){
                        userIdList.add(k.getUserId());
                    }
                }else{
                    //员工列表不查询渠道用户
                    Role role = roleService.findByRoleCode("channel");
                    if(role != null){
                        roleId = role.getRoleId();
                        UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
                        userRoleCriteria.createCriteria().andRoleIdNotEqualTo(roleId);
                        List<UserRoleKey> ur = userRoleMapper.selectByExample(userRoleCriteria);
                        //searchParams.remove("EQ_roleId");
                        for(UserRoleKey k : ur){
                            userIdList.add(k.getUserId());
                        }
                    }
                }
            }else{
                //员工列表不查询渠道用户
                Role role = roleService.findByRoleCode("channel");
                if(role != null){
                    String roleId = role.getRoleId();
                    UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
                    userRoleCriteria.createCriteria().andRoleIdNotEqualTo(roleId);
                    List<UserRoleKey> ur = userRoleMapper.selectByExample(userRoleCriteria);
                    //searchParams.remove("EQ_roleId");
                    for(UserRoleKey k : ur){
                        userIdList.add(k.getUserId());
                    }
                }
            }
        }
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        //Role role = roleService.findByRoleCode(Constants.Role.SUPPORTER);
        //Role customerRole = roleService.findByRoleCode(Constants.Role.CUSTOMER);
        //filters.put("EQ_statiumId", new SearchFilter("roleId", Operator.EQ, role.getRoleId()));
        //filters.put("OR_statiumId", new SearchFilter("orRoleId", Operator.EQ, customerRole.getRoleId()));
        //int total = userMapperEx.countUserEx(pageRequest, filters.values());
        //List<UserEx> list = userMapperEx.findUserEx(pageRequest, filters.values(), "create_time desc");
        
        // modify by liangc 150731 : 这个地方取用户列表, 无固定角色
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.setMysqlLength(pageable.getPageSize());
        userCriteria.setMysqlOffset(pageable.getOffset());
        UserCriteria.Criteria cri = userCriteria.createCriteria();
        System.out.println(searchParams);
        Criterias.bySearchFilter(cri, filters.values());
        cri.andOrgCodeEqualTo("master");
        if(!userIdList.isEmpty()){
            cri.andUserIdIn(userIdList);
        }
        userCriteria.setOrderByClause("create_time desc");
        int total = userMapper.countByExample(userCriteria);
        List<User> list = userMapper.selectByExample(userCriteria);
        return new PageImpl<>(list, pageable, total);
    }

    public UserEx getUserEx(String userId) {
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("EQ_userId", userId);
        
        return userMapperEx.getUserExByFilter(SearchFilter.parse(searchParams).values());
    }

    public User getByLoginName(String name) {
        UserCriteria rc = new UserCriteria();
        UserCriteria.Criteria cri = rc.createCriteria();
        cri.andLoginNameEqualTo(name);
        List<User> users = userMapper.selectByExample(rc);
        
        return users.size() > 0 ? users.get(0) : null;
    }

    public User getByNickname(String name) {
        UserCriteria rc = new UserCriteria();
        UserCriteria.Criteria cri = rc.createCriteria();
        cri.andNicknameEqualTo(name);
        List<User> users = userMapper.selectByExample(rc);
        
        return users.size() > 0 ? users.get(0) : null;
    }

    
    @Transactional(readOnly=false)
    public void resetPassword(String userId) throws Exception {
        User user = getUser(userId);
        String[] hash = Encrypts.hashPassword(user.getLoginName());
        user.setPassword(hash[0]);
        user.setSalt(hash[1]);
        updateByPrimaryKeySelective(user,user.getUserId());
    }

    
    
    
    /**
     * 修改用户的密码为新密码.
     * 
     * @param uid 用户ID.
     * @param password 新密码.
     * @return 被修改密码的用户.
     * @throws Exception 
     */
    @Transactional(readOnly=false)
    public User changePassword(String uid, String password) throws Exception {
        User user = getUser(uid);
        String[] hash = Encrypts.hashPassword(password);
        
        user.setPassword(hash[0]);
        user.setSalt(hash[1]);
        
        updateByPrimaryKeySelective(user,user.getUserId());
        
        if (user.getLoginName().equals("root")) {
            String value = Encrypts.encryptAES(password, UserContext.ENCODE_KEY);
            configService.set(UserContext.SYSTEM_PASSWORD_KEY, value);
        }
        
        return user;
    }
    
    @Transactional(readOnly=false)
    public User changePassword(String uid, String old, String password) throws Exception {
        User user = getUser(uid);
        String hash = Encrypts.hashPassword(old, user.getSalt());
        if (!hash.equals(user.getPassword())) {
            throw new Exception("旧密码不正确!");
        }
        
        return changePassword(uid, password);
    }
    
    public List<Role> getUserRoles(String userId) {
        return userMapperEx.selectRoleByUserId(userId);
    }
    
    /**
     * 根据客户ID查找用户
     * @MethodName getUserByCustId
     * @Description 
     * @Author Li.XiaoChao
     * @param custId
     * @return
     * @CreatDate 2014年12月17日
     */
    public User getUserByCustId(String custId) {
        UserCriteria rc = new UserCriteria();
        UserCriteria.Criteria cri = rc.createCriteria();
        cri.andCustIdEqualTo(custId);
        List<User> users = userMapper.selectByExample(rc);
        
        return users.size() > 0 ? users.get(0) : null;
    }

    
    /**
     * @return
     */
    public List<User> findAllSupport() {
        Role role = roleService.findByRoleCode(Constants.Role.SUPPORTER);
        UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
        userRoleCriteria.createCriteria().andRoleIdEqualTo(role.getRoleId());
        List<UserRoleKey> userRoleList = userRoleMapper.selectByExample(userRoleCriteria);
        List<String> userIdList = new ArrayList<String>();
        for(UserRoleKey ur : userRoleList){
            userIdList.add(ur.getUserId());
        }
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.createCriteria().andUserIdIn(userIdList).andDeleteFlagIsNull();
        return userMapper.selectByExample(userCriteria);
    }
    


    
    /**
     * 更新当前用户个人信息
     * 
     * @param user
     */
//  @Transactional(value="core", readOnly = false)
    @Transactional(readOnly = false)
    public void updateCurrentUserInfo(User user) throws Exception {
        User current = SessionUtil.currentUser();
        user.setUserId(current.getUserId());
        updateByPrimaryKeySelective(user,user.getUserId());
        current.setNickname(user.getNickname());
        current.setSecMobile(user.getSecMobile());
    }
    

    


    /**
     * 创建运营人员
     * 
     * @param user
     */
//  @Transactional(value="core", readOnly = false)
    @Transactional( readOnly = false)
    public void createSupporter(UserEx user) throws Exception {
        user.setStatus(Constants.UserStatus.ENABLE);
        //modify by liangc 150731 : 角色与用户改成 多对多 关系，此处逻辑不成立了
        //Role role = roleService.findByRoleCode(Constants.Role.SUPPORTER);
        //user.setRoleId(role.getRoleId());
        saveUser(user);
    }
    
    /**
     * 创建客服人员
     * @param user
     * @throws Exception
     */
    @Transactional(value="core", readOnly = false)
    public void createCustomer(UserEx user) throws Exception {
        user.setStatus(Constants.UserStatus.ENABLE);
        //modify by liangc 150731 : 角色与用户改成 多对多 关系，此处逻辑不成立了
        //Role role = roleService.findByRoleCode(Constants.Role.CUSTOMER);
        //user.setRoleId(role.getRoleId());
        saveUser(user);
    }

    
    /**
     * 更新运营人员
     * @param user
     */
//  @Transactional(value="core", readOnly = false)
    @Transactional(readOnly = false)
    public void updateSupporter(User user) throws Exception {
        saveUser(user);
    }
    
    
    /**
     * 删除运营人员
     * 
     * @param userId
     * @throws Exception
     */
//  @Transactional(value="core", readOnly = false)
    @Transactional( readOnly = false)
    public void deleteSupporter(String userId) throws Exception {
        deleteUser(userId);
    }   
    
    
    /**
     * 场馆入驻审核
     * 
     * @param userId
     * @throws Exception
     */
//  @Transactional(value="core", readOnly = false)
    @Transactional( readOnly = false)
    public void audit(String userId, String audit) throws Exception {
        User u = getUser(userId);
        if("审核通过".equals(audit)){
            u.setStatus(UserStatus.ENABLE);
        }else{
            u.setStatus(UserStatus.UNPASS);
        }
        updateByPrimaryKeySelective(u,u.getUserId());
    }   
    
    /**
     * 根据用户id查找用户名称
     * @MethodName getUserByCustId
     * @Description 
     * @Author Li.XiaoChao
     * @param custId
     * @return
     * @CreatDate 2014年12月17日
     */
    public User findLoginName(String id) {
        User user;
        try {
            user = selectByPrimaryKey(User.class,id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
//      User user = userMapper.findLoginName(id);
    }
}
