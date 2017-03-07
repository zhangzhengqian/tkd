package com.lc.zy.ball.boss.framework.book.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.book.service.BookBallService;
import com.lc.zy.ball.boss.framework.book.vo.BookBallVo;
import com.lc.zy.ball.boss.framework.orders.service.OrderService;
import com.lc.zy.ball.boss.framework.orders.vo.OrderLogVo;
import com.lc.zy.ball.boss.framework.orders.vo.OrderVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.domain.oa.po.BookBall;
import com.lc.zy.ball.domain.oa.po.BookUser;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.web.WebUtils;

/**
 * 约球
 * 
 * @author Liangsh
 *
 */
@Controller
@RequestMapping(value = "/book")
public class BookBallController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(BookBallController.class);

	@Autowired
	private BookBallService bookBallService;
	
	@Autowired
	OrderService orderService;

	/**
	 * 
	 * <约球列表><功能具体实现>
	 *
	 * @create：2015年10月23日 下午2:46:29
	 * @author： liangsh
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list")
	public String index(HttpServletRequest request, Model model)throws Exception {
		logger.info("BookBallController index method execute!");
		//根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		// 分页
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);//参数去空
		logger.debug(searchParams.toString());
		Page<OrderVo> onePage = bookBallService.find(searchParams, page, size,true,true);
		model.addAttribute("data", onePage);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "/book/bookList";
	}

	@RequestMapping(value = "view/{id}")
	public String bookDetail(Model model, @PathVariable String id) {
		logger.debug("id={}", id); //bookId
		OrderVo orderVo = new OrderVo();
		List<OrderLogVo> orderLogVoList = new ArrayList<OrderLogVo>(); 
		try {
			BookBall book = bookBallService.selectByPrimaryKey(BookBall.class, id);
			BookBallVo bookVo = new BookBallVo();
			BeanUtils.copyProperties(book,bookVo);
			List<OrderVo> memberOrderList = new ArrayList<OrderVo>();
			if(StringUtils.isNotEmpty(book.getOrderId())){
			    orderVo = orderService.getOrderVo(book.getOrderId());
			    orderLogVoList = orderService.getOrderLogVoList(book.getOrderId());
			}
			//获取约球所有的报名人数
			List<BookUser> list  = bookBallService.getUsersByBookId(book.getId());
			logger.info("参与人员个数={}",list.size());
			if(CollectionUtils.isNotEmpty(list)){
				for (BookUser info : list) {
					OrderVo vo = new OrderVo();
					if(book.getAvgPrice() > 0){
						logger.info("参与人订单id={}",info.getOrderId());
						Order userOrder = orderService.getOrder(info.getOrderId());
						BeanUtils.copyProperties(userOrder, vo);
					}
					SsoUser user = bookBallService.selectByPrimaryKey(SsoUser.class, book.getUserId());
					if (user != null) {
						vo.setUserPhone(user.getPhone() != null ? user.getPhone() : "");
						vo.setUserName(user.getNickName() != null ? user.getNickName() : "");
						//用customerId暂存球友号
						vo.setCustomerId(user.getQiuyouno() != null ? user.getQiuyouno(): "");
					}
					logger.info("成员信息：{}",vo.toString());
					memberOrderList.add(vo);
				}
			}
			
			//获取发起人信息
			if (CommonUtils.isNotEmpty(book.getUserId())) {
				SsoUser user = bookBallService.selectByPrimaryKey(SsoUser.class, book.getUserId());
				if (user != null) {
					bookVo.setUserPhone(user.getPhone() != null ? user.getPhone() : "");
					bookVo.setUserName(user.getNickName() != null ? user.getNickName() : "");
					bookVo.setQiuyouno(user.getQiuyouno() != null ? user.getQiuyouno(): "");
				}
			}
			model.addAttribute("data", bookVo);
			model.addAttribute("order", orderVo);
			model.addAttribute("memberList", memberOrderList);
			model.addAttribute("orderLogVoList", orderLogVoList);
			
		} catch (Exception e) {
			logger.error("查看约球详情失败", e);
		}
		
		return "/book/bookView";
	}

	/**
	 * 
	 * <参与成员列表><功能具体实现>
	 *
	 * @create：2015年10月23日 下午2:46:41
	 * @author： liangsh
	 * @param model
	 * @param bookId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "memberList/{bookId}")
	public String memberList(Model model, @PathVariable String bookId) throws Exception {
		try {
			model.addAttribute("data", bookBallService.getUsersByBookId(bookId));
		} catch (Exception e) {
			logger.error("查看成员失败", e);
		}
		return "/book/memberList";
	}
}
