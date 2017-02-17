package com.lc.zy.common.easemobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EaseMobUtil {
	
	private static Logger logger = LoggerFactory.getLogger(EaseMobUtil.class);
	
	//218环信URL
    private static final String URL = "http://a1.easemob.com/qiuyouquan/chatwithyou";
    
	//217环信URL
    //private static final String URL = "http://a1.easemob.com/qiuyouquanzone/huanxinlogin";
    
    //218环信CLIENT_ID
    private static final String CLIENT_ID = "YXA6RqnK4KtVEeSD1b3i6Ypp9Q";
    
    //217环信CLIENT_ID
    //private static final String CLIENT_ID = "YXA6l4ek0CClEeW_BrW4wZ4Akg";
    
    //218环信CLIENT_SECRET
    private static final String CLIENT_SECRET = "YXA61NayIBlztew9iP0veAq28B1b5tg";
    
    /**
     * 环信默认密码
     */
    public static final String DEFAULT_PASSWORD = "qiuyouzone";
    
    //217环信CLIENT_SECRET
	//private static final String CLIENT_SECRET = "YXA6z03uVFq6bu3HAzCp8vCv5UMVthg";
    
    private static String token(Map<String, Object> map) throws Exception {
        JSONObject params = new JSONObject();
        params.put("grant_type", "client_credentials");
        params.put("client_id", CLIENT_ID);
        params.put("client_secret", CLIENT_SECRET);

        String rspString = HttpUtil.sendJson(URL + "/token", new HashMap<String, Object>(), params, map);
        if (rspString == null) {
            return null;
        }

        Map<String, Object> rspData = JsonUtil.objFromString(rspString, new TypeReference<Map<String, Object>>() {
        });
        return (String) rspData.get("access_token");
    }

    public static Map<String, Object> addUser(String username, String password, String nickname, Map<String, Object> map)
            throws Exception {
        String token = token(map);
        if (token == null) {
            return null;
        }

        JSONObject params = new JSONObject();
        params.put("username", username);
        params.put("password", password);
        params.put("nickname", nickname);

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Authorization", "Bearer " + token);

        String rspString = HttpUtil.sendJson(URL + "/users", header, params, map);
        logger.info(rspString);
        if (rspString == null) {
            return null;
        }
        Map<String, Object> rspData = JsonUtil.objFromString(rspString, new TypeReference<Map<String, Object>>() {
        });
        return rspData;
    }

    public static Map<String, Object> changeUserPassword(String username, String newPassword, Map<String, Object> map)
            throws Exception {
        String token = token(map);
        if (token == null) {
            return null;
        }

        JSONObject params = new JSONObject();
        params.put("newpassword", newPassword);

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Authorization", "Bearer " + token);

        String rspString = HttpUtil.sendJson(URL + "/users/" + username + "/password", header, params, map);
        if (rspString == null) {
            return null;
        }

        Map<String, Object> rspData = JsonUtil.objFromString(rspString, new TypeReference<Map<String, Object>>() {
        });
        return rspData;
    }

    public static Map<String, Object> deleteUser(String username, Map<String, Object> map) throws Exception {
        String token = token(map);
        if (token == null) {
            return null;
        }

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Authorization", "Bearer " + token);

        String rspString = HttpUtil.delete(URL + "/users/" + username, header, map);
        logger.info(rspString);
        if (rspString == null) {
            return null;
        }

        Map<String, Object> rspData = JsonUtil.objFromString(rspString, new TypeReference<Map<String, Object>>() {
        });
        return rspData;
    }

    public static Map<String, Object> addFriend(String username, String friend, Map<String, Object> map)
            throws Exception {
        String token = token(map);
        if (token == null) {
            return null;
        }

        JSONObject params = new JSONObject();

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Authorization", "Bearer " + token);

        String rspString = HttpUtil.sendJson(URL + "/users/" + username + "/contacts/users/" + friend, header, params,
                map);
        if (rspString == null) {
            return null;
        }

        Map<String, Object> rspData = JsonUtil.objFromString(rspString, new TypeReference<Map<String, Object>>() {
        });
        return rspData;
    }

    public static Map<String, Object> deleteFriend(String username, String friend, Map<String, Object> map)
            throws Exception {
        String token = token(map);
        if (token == null) {
            return null;
        }

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Authorization", "Bearer " + token);

        String rspString = HttpUtil.delete(URL + "/users/" + username + "/contacts/users/" + friend, header, map);
        if (rspString == null) {
            return null;
        }

        Map<String, Object> rspData = JsonUtil.objFromString(rspString, new TypeReference<Map<String, Object>>() {
        });
        return rspData;
    }

    public static String createGroup(String name, String desc, String owner, List<String> memberList,
            Map<String, Object> map) throws Exception {
        String token = token(map);
        if (token == null) {
            return null;
        }

        JSONObject params = new JSONObject();
        params.put("groupname", name);
        params.put("desc", desc);
        // 是否是公开群, 此属性为必须的,为false时为私有群
        Boolean publicGroup = true;
        params.put("public", publicGroup);

        params.put("owner", owner);
        JSONArray memberArray = new JSONArray();

        if (memberList != null && memberList.size() > 0) {
            for (String member : memberList) {
            	memberArray.put(member);
            }
            params.put("members", memberArray);
        }

        // needApprovalRequired:如果创建的公开群用需要户自由加入，就传false。否则需要申请，等群主批准后才能加入，传true
        // Boolean needApprovalRequired=false;
        // params.put("needApprovalRequired", needApprovalRequired);
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Authorization", "Bearer " + token);

        String rspString = HttpUtil.sendJson(URL + "/chatgroups", header, params, map);
        if (rspString == null) {
            return null;
        }

        Map<String, Object> rspData = JsonUtil.objFromString(rspString, new TypeReference<Map<String, Object>>() {
        });
        return getAttribute(rspData, "groupid", String.class);
    }

    public static boolean deleteGroup(String groupId, Map<String, Object> map) throws Exception {
        String token = token(map);
        if (token == null) {
            return false;
        }

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Authorization", "Bearer " + token);

        String rspString = HttpUtil.delete(URL + "/chatgroups/" + groupId, header, map);
        if (rspString == null) {
            return false;
        }

        Map<String, Object> rspData = JsonUtil.objFromString(rspString, new TypeReference<Map<String, Object>>() {
        });
        return getAttribute(rspData, "success", Boolean.class);
    }

    public static boolean groupAddMember(String groupId, String username, Map<String, Object> map) throws Exception {
        String token = token(map);
        if (token == null) {
            return false;
        }

        JSONObject params = new JSONObject();

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Authorization", "Bearer " + token);

        String rspString = HttpUtil
                .sendJson(URL + "/chatgroups/" + groupId + "/users/" + username, header, params, map);
        if (rspString == null) {
            return false;
        }

        Map<String, Object> rspData = JsonUtil.objFromString(rspString, new TypeReference<Map<String, Object>>() {
        });
        return getAttribute(rspData, "result", Boolean.class);
    }

    public static boolean groupDeleteMember(String groupId, String username, Map<String, Object> map) throws Exception {
        String token = token(map);
        if (token == null) {
            return false;
        }

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Authorization", "Bearer " + token);

        String rspString = HttpUtil.delete(URL + "/chatgroups/" + groupId + "/users/" + username, header, map);
        if (rspString == null) {
            return false;
        }

        Map<String, Object> rspData = JsonUtil.objFromString(rspString, new TypeReference<Map<String, Object>>() {
        });

        return getAttribute(rspData, "result", Boolean.class);
    }

    private static <T> T getAttribute(Map<String, Object> rspData, String key, Class<T> cls) {

        Map<String, Object> data = (Map<String, Object>) rspData.get("data");
        if (data == null) {
            return null;
        }

        return (T) data.get(key);
    }

    public static void main(String[] args) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        // String token = EaseMobUtil.token(map);
        // Map<String, Object> rsp = EaseMobUtil.addUser("15268298956", "youqiuzone",null,map);
        // "YWMtv-IMzqxUEeSMBfFCdjGeKQAAAUyJBuP9k4w3YIsyX5iIZuRZRZtByAeXyMA",
        // map);
        // Map<String, Object> rsp = EaseMobUtil.deleteUser("test3", token,
        // map);
        // Map<String, Object> rsp = EaseMobUtil.addFriend("test1", "test2",
        // token, map);
        // Map<String, Object> rsp = EaseMobUtil.deleteFriend("test1", "test2",
        // token, map);
        // List<String> memberList = new ArrayList<String>();
        // memberList.add("test2");
        // Map<String, Object> rsp = EaseMobUtil.createGroup("test-group",
        // "test-desc", "test1", memberList, token, map);
        // Map<String, Object> rsp = EaseMobUtil.deleteGroup("142342053787944",
        // token, map);
        // Map<String, Object> rsp =
        // EaseMobUtil.groupAddMember("142342483986528", "test3" , token, map);
        // Map<String, Object> rsp =
        // EaseMobUtil.groupDeleteMember("142342483986528", "test2" , token,
        // map);

        // if(rsp == null) {
        // System.out.println(map.toString());
        // }else {
        // System.out.println(rsp.toString());
        // }

        System.out.println((String) null);
    }
}
