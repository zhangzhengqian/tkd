package com.lc.zy.ball.app.service.crmUser;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.crmUser.bean.BillDetailVo;
import com.lc.zy.ball.app.service.crmUser.bean.CrmBillVo;
import com.lc.zy.ball.app.service.order.bean.OrderListVo;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.CrmUser;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.ball.domain.oa.po.StatiumInfosCriteria;
import com.lc.zy.common.bean.Auth;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.bean.KeyValueEntity;
import com.lc.zy.common.bean.Reason;
import com.lc.zy.common.bean.Success;
import com.lc.zy.common.util.AppRequestUtil;

/**
 * 
 * <crm用户模块><功能具体实现>
 *
 * @create：2016年11月14日 下午3:37:41
 * @author：zzq
 */
@Service("crmUser")
public class CrmUserService {
	@Autowired
	private CrmUserRepository crmUserRepository;
	
	@Autowired
	private StatiumInfosMapper statiuminfoMapper;
	
	private static Logger logger = LoggerFactory.getLogger(CrmUserRepository.class);
	/**
	 * 
	 * <crm用户登录><功能具体实现>
	 *
	 * @create：2016年11月14日 下午4:42:53
	 * @author：zzq
	 * @param request
	 * @return
	 */
	@Auth(false)
	public Success login(ClientRequest request){
		String sn = request.getSn();
		String loginName = AppRequestUtil.getParameter(request, "loginName");
		String passWord = AppRequestUtil.getParameter(request, "passWord");
		
		int begin = AppRequestUtil.getParameterInteger(request, "begin");
		int size = AppRequestUtil.getParameterInteger(request, "size");
		if(StringUtils.isEmpty(loginName)){
			return new Success(sn,false,new Reason("loginNameNull_error", "注册名称不能为空"));
		}
		else if(StringUtils.isEmpty(passWord)){
			return new Success(sn,false,new Reason("passWordNull_error","密码不能为空"));
		}
		else if(crmUserRepository.hasLoginName(loginName)){
			return new Success(sn,false,new Reason("loginNameNoExist","用户名不存在"));
		}
		else if(crmUserRepository.checkPassword(loginName, passWord)){
			return new Success(sn,false,new Reason("passWord_error","密码不正确"));
		}
		else{
			CrmUser crmUser = crmUserRepository.login(loginName);
			//返回app下的订单集合
			Integer statiumId = Integer.valueOf(crmUser.getStatiumId());
			List<OrderListVo> vos = crmUserRepository.getOrderList(statiumId,begin,size);
			//如果馆长登陆后该道馆没有订单 则需要返回一个包在外边的 道馆id
			return new Success(sn, true,new KeyValueEntity("statiumId",statiumId).append("orderList", vos));
		}
	}
	
	/**
	 * 
	 * <馆长登录账单列表><功能具体实现>
	 *
	 * @create：2016年11月23日 下午5:18:29
	 * @author：zzq
	 * @param request
	 * @return
	 */
	@Auth(false)
	public Success getBillList(ClientRequest request){
		String sn = request.getSn();
		Integer dgId = AppRequestUtil.getParameterInteger(request, "statiumId");
		Integer begin = AppRequestUtil.getParameterInteger(request, "begin");
		Integer size = AppRequestUtil.getParameterInteger(request, "size");
		if(dgId!=0){
			List<CrmBillVo> billList = crmUserRepository.getCrmBillList(dgId,begin,size);
			return new Success(sn,true,new KeyValueEntity("billList",billList));
		}else{
			return new Success(sn, false, new Reason("STATIUM_NOTFOUND", "无法查找有效道馆"));
		}
		
	}
	
	/**
	 * 
	 * <账单结算详情><功能具体实现>
	 *
	 * @create：2016年11月24日 下午4:05:58
	 * @author：zzq
	 * @param request
	 * @return
	 */
	@Auth(false)
	public Success getBillDetail(ClientRequest request){
		String sn = request.getSn();
		
		try {
			String billId = AppRequestUtil.getParameter(request, "billId");
			Integer begin = AppRequestUtil.getParameterInteger(request, "begin");
			Integer size = AppRequestUtil.getParameterInteger(request, "size");
			if(!"".equals(billId)){
				List<BillDetailVo> detailList = crmUserRepository.getBillDetail(billId,begin,size);
				return new Success(sn,true,new KeyValueEntity("detailList", detailList));
			}else{
				return new Success(sn,false,new Reason("BILLID_NOEXIST", "请选择有效账单"));
			}
		} catch (RuntimeException e) {
				return new Success(sn,false,new Reason(Constants.SYSTEM_KEY,e.getMessage()));
		}
	}
}
