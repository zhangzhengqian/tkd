package com.lc.zy.ball.boss.common.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;

import com.lc.zy.ball.boss.common.security.ShiroDbRealm.ShiroUser;
import com.lc.zy.ball.boss.common.service.DicService;
import com.lc.zy.ball.boss.framework.orders.service.OrderService;
import com.lc.zy.ball.boss.framework.orders.vo.OrderItemVo;
import com.lc.zy.ball.boss.framework.ssouser.service.SsoUserService;
import com.lc.zy.ball.boss.framework.statistic.service.StatisticRegisterUserService;
import com.lc.zy.ball.boss.framework.statium.service.StatiumPriceTmplService;
import com.lc.zy.ball.boss.framework.system.service.UserService;
import com.lc.zy.ball.domain.oa.po.DicItem;
import com.lc.zy.ball.domain.oa.po.Role;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.util.SpringUtils;

public class FunctionTag {

    private static UserService userService;
	private static StatiumPriceTmplService statiumPriceTmplService;
	
	
	/**
	 * 
	 * <根据订单获取订单的总补贴><功能具体实现>
	 *
	 * @create：2015年10月22日 下午3:55:22
	 * @author： liangsh
	 * @param orderId
	 * @return
	 */
	public static Integer getSubsidiesByOrderId(String orderId){
			statiumPriceTmplService = SpringUtils.getBean(StatiumPriceTmplService.class);
			 try {
				return statiumPriceTmplService.getSubsidiesByOrderId(orderId);
			} catch (Exception e) {
				return null;
			}
	}
	
	/**
	 * 
	 * <根据订单id获取订单总成本><功能具体实现>
	 *
	 * @create：2015年10月22日 下午5:36:47
	 * @author： liangsh
	 * @param orderId
	 * @return
	 */
	public static Integer getCostPriceByOrderId(String orderId){
		statiumPriceTmplService = SpringUtils.getBean(StatiumPriceTmplService.class);
		 try {
			return statiumPriceTmplService.getCostPriceByOrderId(orderId);
		} catch (Exception e) {
			return null;
		}
	}

    /**
     * @MethodName getCurrentUser
     * @Description 获取当前用户
     * @Author wang.haibin
     * @return
     * @CreatDate 2014年12月22日
     */
    public static User getCurrentUser() {
        try {
            return ((ShiroUser) SecurityUtils.getSubject().getPrincipal()).getUser();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @MethodName getUser
     * @Description 根据id获取用户信息
     * @Author wang.haibin
     * @param userId
     * @return
     * @CreatDate 2014年12月29日
     */
    public static User getUser(String userId) {
        userService = SpringUtils.getBean(UserService.class);
        return userService.getUser(userId);
    }

    /**
     * @return
     */
    public static List<Role> getCurrentUserRoles() {
        return SpringUtils.getBean(UserService.class).getUserRoles(getCurrentUser().getUserId());
    }

    /**
     * @param timestamp
     * @return
     */
    public static int getMinutesOfDay(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp * 1000L);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return hour * 60 + minute;
    }

    /**
     * 格式化金额
     * 
     * @param amount
     * @return
     */
    public static String formatMoney(int amount) {
        if (amount % 100 == 0) {
            return String.valueOf(amount / 100);
        } else {
            return String.format("%.2f", amount / 100f);
        }
    }

    /**
     * 格式化时间为: xx小时xx分钟,不足一小时:xx分钟
     * 
     * @param seconds
     * @return
     */
    public static String formatTimeSpan(int seconds) {
        if (seconds % 3600 == 0) {
            return seconds / 3600 + "小时";
        } else if (seconds < 3600) {
            return seconds / 60 + "分钟";
        } else {
            return String.format("%d小时%d分钟", seconds / 3600, (seconds % 3600) / 60);
        }
    }

    /**
     * 获取时间列表
     * 
     * @param start
     * @param end
     * @return
     */
    public static List<String> getHours(int start, int end) {
        List<String> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(String.format("%02d", i));
        }

        return list;
    }

    /**
     * 按字典类型获取所有字典
     * 
     * @param dicCode
     * @return
     */
    public static List<DicItem> getDicItems(String dicCode) {
        return SpringUtils.getBean(DicService.class).findItemByCodeType(dicCode);
    }

    /**
     * 按字典编码获取字典
     * 
     * @param dicItemCode
     * @return
     */
    public static DicItem getDicItem(String dicItemCode) {
        return SpringUtils.getBean(DicService.class).findItemByCode(dicItemCode);
    }

    /**
     * 获取场地预定情况
     * 
     * @param date
     * @param spaceId
     * @return
     */
    public static List<OrderItemVo> getOrderItems(String date, String spaceId) {
        try {
            return SpringUtils.getBean(OrderService.class).find(date, spaceId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 获取场地预定情况
     * 
     * @param date
     * @param spaceId
     * @return
     */
    public static List<Map<String,Object>> orderDay(String date) {
        try {
            return SpringUtils.getBean(OrderService.class).orderDay(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
     public static Integer uvCount(String date) {
         try {
             return SpringUtils.getBean(StatisticRegisterUserService.class).uvCount(date);
         } catch (Exception e) {
             e.printStackTrace();
             return 0;
         }
     }
     
     public static Integer pvCount(String date) {
         try {
             return SpringUtils.getBean(StatisticRegisterUserService.class).pvCount(date);
         } catch (Exception e) {
             e.printStackTrace();
             return 0;
         }
     }
     
     public static Integer  uvCountChannel(String date) {
         try {
        	 int count = SpringUtils.getBean(StatisticRegisterUserService.class).uvCount(date);
        	 return  (int)Math.floor(count * 0.9);
         } catch (Exception e) {
             e.printStackTrace();
             return 0;
         }
     }
     
     /**
      * 
      * <获取用户昵称><功能具体实现>
      *
      * @create：2015年12月7日 下午1:07:39
      * @author： liangsh
      * @param ssoUserId
      * @return
      */
     public static String getSsouserNameById(String ssoUserId){
    	try {
			SsoUser user = SpringUtils.getBean(SsoUserService.class).viewSsoUser(ssoUserId);
			if(user != null){
				return user.getNickName();
			}else{
				return "";
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
             return "";
		} 
     }
     
     /**
      * 获取用户球友号、昵称
      * @param userId
      * @return
      */
     public static Map<String,String> getSsouserNameAndQiuyouNo(String userId){
    	 SsoUser user = SpringUtils.getBean(SsoUserService.class).viewSsoUser(userId);
    	 Map<String,String> result = new HashMap<String, String>();
    	 if(user!=null){
    		 result.put("qiuyouNo", user.getQiuyouno());
    		 result.put("name", user.getNickName());
    		 result.put("phone", user.getPhone());
    	 }
    	 return result;
     }
     
     /**
      * 查看用户是否绑定为营销账号
      * @param id
      * @return
      */
     public static boolean checkMarkeUser(String id){
    	 return SpringUtils.getBean(SsoUserService.class).checkMarketUser(id);
     }
     
     
     public static String[] getStatusNames(int type,int flow,int status,int flowsState) {
//		 0 活动
//		 1 包场
//		 2 充值
//		 3 提现
    	 // flowsState  1 销售主管待审  2 客服   3 财务
    	 //status  0待审核  1审核通过   2审核不通过   3取消   4结束
    	 String[] states = new String[]{"","",""};
    	 if(status == 3){
			 states[0] = "已取消"; 
			 if(flow == 0 || flow == 1){
				if(type == 1){
					 if(flowsState == 3){
						 states[2] = "待确认";
					 }
				}
			 }else if(flow == 3){
				 if(flowsState == 2){
					 states[1] = "待审核";
				 }else if(flowsState == 3){
					 states[2] = "待确认";
				 }
			 }
			 return states;
		 }
    	 
    	 if(flow == 0 || flow == 1){ //1或0：销售->销售主管1-->财务3
    		 if(type == 0){//线上
    			 if(flowsState == 1){
        			 if(status == 0){ //销售主管待审
        				 states[0] = "待审核";
        			 }else if(status == 4){
        				 states[0] = "已完成";
        			 }else if(status == 2){
        				 states[0] = "审核不通过";
        			 }
        		 }
    		 }else if(type == 1){ //线下
    			 if(flowsState == 1){
        			 if(status == 0){ //销售主管待审
        				 states[0] = "待审核";
        			 }else if(status == 2){
        				 states[0] = "审核不通过";
        			 }                  
        		 }else if(flowsState == 3){
        			 states[0] = "审核通过";
        			 if(status == 0){ //财务
        				 states[2] = "待确认";
        			 }else if(status == 4){
        				 states[2] = "已完成"; 
        			 }
        		 }
    			 
    		 }
    	 }else if(flow == 2){//2充值：销售->-->财务3
    		 if(flowsState == 3){
    			 if(status == 0){ //财务
    				 states[2] = "待确认";
    			 }else if(status == 4){
    				 states[2] = "已完成"; 
    			 }
    		 }
    	 }else{ //3提现：销售->销售主管1-->客服-->财务3
    		 if(flowsState == 1){//在销售主管
    			 //销售主管处理
    			 if(status == 0){ 
    				 states[0] = "待审核";
    			 }else if(status == 2){
    				 states[0] = "审核不通过";
    			 }
    		 }else if(flowsState == 2){//在客服
    			 states[0] = "审核通过";
    			 //客服处理
    			 if(status == 0){ 
    				 states[1] = "待审核";
    			 }else if(status == 2){
    				 states[1] = "审核不通过"; 
    			 }
    		 }else if(flowsState == 3){ //在财务
    			 states[0] = "审核通过";
    			 states[1] = "审核通过";
    			 //财务处理
    			 if(status == 0){ 
    				 states[2] = "待确认";
    			 }else if(status == 4){
    				 states[2] = "已完成"; 
    			 }
    		 }
    	 }
    	 return states;
	}
}
