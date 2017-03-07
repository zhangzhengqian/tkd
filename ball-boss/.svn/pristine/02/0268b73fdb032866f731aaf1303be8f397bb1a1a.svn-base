package com.lc.zy.ball.boss.framework.system.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.EnjoyMember;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;
import com.google.gson.Gson;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.security.ShiroDbRealm.ShiroUser;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.system.service.CphService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.Cph;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/cph")
public class CphController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(CphController.class);
	Gson g = new Gson();

	@Autowired
	private CphService cphService;

	@Autowired
	private SsoUserMapper ssoUserMapper;

	@RequestMapping(value = "/callPhone/{phone}", method = RequestMethod.POST)
	@ResponseBody
	public String callPhone(@PathVariable String phone) throws Exception {
		// http://119.254.80.102/app?Action=Dialout&ActionID=1234567890&Account=N00000003412&Exten=13716895499&FromExten=8000&PBX=dh.pbx.4.5&ExtenType=Local
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String agent = ((ShiroUser) SecurityUtils.getSubject().getPrincipal()).getAgent().split("@")[0];
			logger.info("呼叫电话={}", phone);
			if(StringUtils.isBlank(agent)){
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "坐席未登录，呼叫失败");
			}else if(StringUtils.isNotEmpty(phone)) {
				StringBuilder url = new StringBuilder(Constants.SevenMoor.SEVENMOORIP);
				url.append("?Action=").append(Constants.SevenMoor.DIALOUT).append("&ActionID=")
						.append(agent).append(DateUtils.currentTimeMillis()).append("&Account=").append(Constants.SevenMoor.ACCOUNT)
						.append("&Exten=").append(phone).append("&FromExten=").append(agent).append("&PBX=")
						.append(Constants.SevenMoor.PBX).append("&ExtenType=").append(Constants.SevenMoor.EXTENTYPE);
				HttpClient httpClient = new HttpClient();
				GetMethod get = new GetMethod(url.toString());
				get.getParams().setContentCharset("UTF-8");
				httpClient.executeMethod(get);
				String data = get.getResponseBodyAsString();
				result.put(Constants.Result.RESULT, true);
				result.put(Constants.Result.DATA, data);
				logger.info("呼叫返回值={}", data.toString());
			} else {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "呼叫失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "呼叫失败");
		}
		return MyGson.getInstance().toJson(result);
	}

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/hangup")
	@ResponseBody
	public String hangup(HttpServletRequest request) throws Exception {
		try {
			logger.info("hangup推送成功");
			Map<String, String[]> params = request.getParameterMap();
			Map<String, String> map = new HashMap<String, String>();
			for (String key : params.keySet()) {
				String[] values = params.get(key);
				for (int i = 0; i < values.length; i++) {
					String value = values[i];
					map.put(key, value);
				}
			}
			logger.info("hangup推送返回信息={}", map.toString());

			// 必须存储的信息
			// ActionID 通过外呼接口调用时,该字段会保存请求的actionID,其它情况下该字段为空
			// CallNo 主叫号码
			// CalledNo 被叫号码
			// CallID : 通话ID,通话连接的在系统中的唯一标识。CallID
			// 是在通话进行中channel的id，可以用这个id来挂断通话之类的操作。一个call有一个CallID，但一个call可能会出现在多个通话中，比如转接。
			// Province 来电的省，例如北京市
			// District 来电的市，例如北京市
			// FileServer 通过FileServer中指定的地址加上RecordFile的值可以获取录音
			// RecordFile 通话录音文件名：用户要访问录音时，在该文件名前面加上服务路径即可，如：FileServer/RecordFile
			// Exten 处理坐席的工号
			// hangup推送返回信息={End=2015-11-30 17:03:44, AgentName=8000, Exten=8000, Ring=2015-11-30 17:03:31,
			// FileServer=http://119.254.80.102:8000, CallType=dialout, Agent=8000,
			// MonitorFilename=http://119.254.80.102:8000/monitor/dh.pbx.4.5/20151130/20151130-170331_N00000003412__913311112021_dh.pbx.4.5-1448874211.502661.mp3,
			// CallSheetID=c6313aa0-4f09-4ad1-b1ce-5d1fbd869db7, Province=北京市, CalledNo=13311112021,
			// RecordFile=monitor/dh.pbx.4.5/20151130/20151130-170331_N00000003412__913311112021_dh.pbx.4.5-1448874211.502661.mp3,
			// State=dealing, District=北京市, ActionID=Dialout1765670492421082489, CallNo=01056332165, RealState=Ringing,
			// Queue=, QueueTime=, CallID=dh.pbx.4.5-1448874211.502661, Begin=2015-11-30 17:03:41, CallState=Unlink}

			String fileServer = map.get("FileServer");
			String recordFile = map.get("RecordFile");
			File filePath = new File(fileServer + "/" + recordFile);
			logger.info("filePath={}", fileServer + "/" + recordFile);
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(fileServer + "/" + recordFile);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			InputStream in = entity.getContent();
			File file = new File("/home/appusr/var/callcenter/" + filePath.getName());
			try {
				FileOutputStream fout = new FileOutputStream(file);
				int l = -1;
				byte[] tmp = new byte[1024];
				while ((l = in.read(tmp)) != -1) {
					fout.write(tmp, 0, l);
				}
				fout.flush();
				fout.close();
			} finally {
				in.close();
			}
			httpclient.close();
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/link")
	@ResponseBody
	public String link(HttpServletRequest request) throws Exception {
		try {
			logger.info("link推送成功");
			Map<String, String[]> params = request.getParameterMap();
			Map<String, String> map = new HashMap<String, String>();
			for (String key : params.keySet()) {
				String[] values = params.get(key);
				for (int i = 0; i < values.length; i++) {
					String value = values[i];
					map.put(key, value);
				}
			}
			logger.info("link推送返回信息={}", map.toString());

			// 必须存储的信息
			// ActionID 通过外呼接口调用时,该字段会保存请求的actionID,其它情况下该字段为空
			// CallNo 主叫号码
			// CalledNo 被叫号码
			// CallID : 通话ID,通话连接的在系统中的唯一标识。CallID
			// 是在通话进行中channel的id，可以用这个id来挂断通话之类的操作。一个call有一个CallID，但一个call可能会出现在多个通话中，比如转接。
			// Province 来电的省，例如北京市
			// District 来电的市，例如北京市
			// FileServer 通过FileServer中指定的地址加上RecordFile的值可以获取录音
			// RecordFile 通话录音文件名：用户要访问录音时，在该文件名前面加上服务路径即可，如：FileServer/RecordFile
			// Exten 处理坐席的工号
			// 返回的数据={AgentName=8000, End=, Exten=8000, FileServer=http://119.254.80.102:8000, Ring=2015-11-27 15:38:21,
			// CallType=dialout, Agent=8000, CallSheetID=bf44fb6d-59ee-4d99-95f4-893d9b2b6033, CalledNo=13311112021,
			// RecordFile=monitor/dh.pbx.4.5/20151127/20151127-153821_N00000003412__913311112021_dh.pbx.4.5-1448609901.472986.mp3,
			// State=dealing, ActionID=Dialout1180533296851586576, CallNo=01056332165, RealState=Ringing, Queue=,
			// QueueTime=, CallID=dh.pbx.4.5-1448609901.472986, Begin=2015-11-27 15:38:28, CallState=Link}

			Cph cph = new Cph();
			cph.setId(UUID.get());
			cph.setCallno(map.get("CallNo"));
			cph.setCalledno(map.get("CalledNo"));
			cph.setCallid(map.get("CallID"));
			cph.setExten(map.get("Exten"));
			cph.setFileserver(map.get("FileServer"));
			cph.setRecordfile(map.get("RecordFile"));
			cph.setCb(SessionUtil.currentUserId());
			cph.setCt(new Date());
			cph.setActionid(map.get("ActionID"));
			cph.setState(map.get("State"));
			cphService.saveLinkRecord(cph);
			logger.info("cph={}", cph.toString());
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	@RequestMapping(value= "/callRecord")
	public String callRecord(Model model, ServletRequest request) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//XXX 将日期字符串转换成对象
		parseDate(searchParams, "GTE_createTime");
		parseDate(searchParams, "LTE_createTime");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request,20);
		logger.debug("user_list_search_params={}",searchParams);
		Page<Cph> onePage = cphService.find(new PageRequest(page, size), searchParams);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		model.addAttribute("data", onePage);
		return "admin/callRecord";
	}
	
	@RequestMapping(value = "/hangup/{phone}/{actionId}", method = RequestMethod.POST)
	@ResponseBody
	public String hangup(@PathVariable String phone, @PathVariable String actionId) throws Exception {
		// http://119.254.80.102/app?Action=Hangup&ActionID=1234567890&Account=N00000003412&CallID=0125369874&PBX=dh.pbx.4.5
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			logger.info("待挂机电话={}", phone);
			String agent = ((ShiroUser) SecurityUtils.getSubject().getPrincipal()).getAgent();
			if(StringUtils.isBlank(agent)){
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "坐席未登录，挂机失败");
			}else if (StringUtils.isNotEmpty(phone)) {
				// 根据手机号获取最后通话记录 并且是一接听状态的
				Cph cph = cphService.getCphByPhone(phone, actionId);
				if (cph != null && cph.getCallid() != null) {
					 StringBuilder url = new StringBuilder(Constants.SevenMoor.SEVENMOORIP);
					 url.append("?Action=").append(Constants.SevenMoor.HANGUP).append("&ActionID")
					 .append(cph.getActionid()).append("&Account=").append(Constants.SevenMoor.ACCOUNT)
					 .append("&CallID=").append(cph.getCallid()).append("&PBX=").append(Constants.SevenMoor.PBX);
					 HttpClient httpClient = new HttpClient();
					 GetMethod get = new GetMethod(url.toString());
					 get.getParams().setContentCharset("UTF-8");
					 httpClient.executeMethod(get);
					 String data = get.getResponseBodyAsString();
					 result.put(Constants.Result.RESULT, true);
					 result.put(Constants.Result.DATA, data);
					 logger.info("挂机成功，返回值={}", data.toString());
					//挂机陈功后，根据挂机推送事件获取 FileServer、RecordFile 访问 FileServer/RecordFile即可获得
					// 将录音文件直接存储到服务器，名字应该是唯一的不用改 获取文件存储到/home/appusr/var/callcenter 目录下
					String fileServer = cph.getFileserver();
					String recordFile = cph.getRecordfile();
					File filePath = new File(fileServer + "/" + recordFile);
					// wget -c fileServer +"/"+ recordFile
					// String chmod = "chmod 777 /home/appusr/var/callcenter/appdownloadMp3.sh";
					// Process proc = Runtime.getRuntime().exec(chmod);
					// proc.waitFor();
					// proc = Runtime.getRuntime().exec("/home/appusr/var/callcenter/appdownloadMp3.sh "+fileServer
					// +"/"+ recordFile);
					// int extValue = proc.waitFor();
					// proc.destroy();
					// if(extValue == 0){
					// logger.info("通话录音下载成功");
					// }else{
					// logger.info("通话录音下载失败");
					// }
					CloseableHttpClient httpclient = HttpClients.createDefault();
					HttpGet httpget = new HttpGet(fileServer + "/" + recordFile);
					HttpResponse response = httpclient.execute(httpget);
					HttpEntity entity = response.getEntity();
					InputStream in = entity.getContent();
					File file = new File("/home/appusr/var/callcenter/" + filePath.getName());
					try {
						FileOutputStream fout = new FileOutputStream(file);
						int l = -1;
						byte[] tmp = new byte[1024];
						while ((l = in.read(tmp)) != -1) {
							fout.write(tmp, 0, l);
						}
						fout.flush();
						fout.close();
					} finally {
						in.close();
					}
					httpclient.close();
				} else {
					logger.debug("挂机失败，没获取到{}接通信息", phone);
					result.put(Constants.Result.RESULT, false);
					result.put(Constants.Result.REASON, "挂机失败，没获取到接通信息");
				}
			} else {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "挂机失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "挂机失败");
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * <根据呼入人手机号获取相关信息></>
	 * @param request
	 * @return
     */
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getUserInfo(HttpServletRequest request) {
		String phone = request.getParameter("phone");
		logger.debug("呼入电话是：{}",phone);
		try {
			SsoUserCriteria c = new SsoUserCriteria();
			SsoUserCriteria.Criteria cri = c.createCriteria();
			cri.andPhoneEqualTo(phone);
			if(ssoUserMapper.countByExample(c) > 0){
				String nuomiId = ssoUserMapper.selectByExample(c).get(0).getNuomiId();
				logger.debug("呼入电话的糯米ID是{}",nuomiId);
				if(org.apache.commons.lang.StringUtils.isNotEmpty(nuomiId)){
					return nuomiId;
				}else{
					return "";
				}
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return "";
		}
	}
}
