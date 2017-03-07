package com.lc.zy.ball.boss.framework.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.orders.vo.OrderVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.BookBallMapper;
import com.lc.zy.ball.domain.oa.mapper.BookUserMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumDetailMapper;
import com.lc.zy.ball.domain.oa.po.BookBall;
import com.lc.zy.ball.domain.oa.po.BookBallCriteria;
import com.lc.zy.ball.domain.oa.po.BookUser;
import com.lc.zy.ball.domain.oa.po.BookUserCriteria;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.ball.domain.oa.po.StatiumDetailCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.DateUtils;

@Service
@Transactional(readOnly = true)
public class BookBallService extends AbstractCacheService {
	private static Logger logger = LoggerFactory.getLogger(BookBallService.class);

	@Autowired
	private BookBallMapper bookBallMapper;

	@Autowired
	private BookUserMapper bookUserMapper;

	@Autowired
	private StatiumDetailMapper statiumDetailMapper;

	@Autowired
	private SsoUserMapper ssoUserMapper;

	@Autowired
	private OrderMapper orderMapper;

	/**
	 * 
	 * <获取约球列表><功能具体实现>
	 *
	 * @create：2015年10月23日 下午2:27:35
	 * @author： liangsh
	 * @param pageable
	 * @param searchParams
	 * @return
	 */
	public Page<OrderVo> find(Map<String, Object> searchParams, int page, int size, boolean isPage, boolean isHasCount)
			throws Exception {
		logger.debug(searchParams.toString());
		// 场馆名称
		String statiumId = null;
		if (searchParams.get("EQ_statiumName") != null
				&& CommonUtils.isNotEmpty((String) searchParams.get("EQ_statiumName"))) {
			String EQ_statiumName = (String) searchParams.get("EQ_statiumName");
			if (StringUtils.isNotEmpty(EQ_statiumName)) {
				StatiumDetailCriteria statiumDetailCriteria = new StatiumDetailCriteria();
				statiumDetailCriteria.createCriteria().andNameEqualTo(EQ_statiumName).andDeleteFlagIsNull()
						.andStatusEqualTo(1);
				List<StatiumDetail> statiumDetailList = statiumDetailMapper.selectByExample(statiumDetailCriteria);
				if (statiumDetailList != null)
					for (StatiumDetail statiumDetail : statiumDetailList) {
						statiumId = statiumDetail.getId();
						break;
					}
			}
		}
		PageRequest pageable = new PageRequest(page, size);
		BookBallCriteria c_book = new BookBallCriteria();
		if (isPage) {
			c_book.setMysqlOffset(pageable.getOffset());
			c_book.setMysqlLength(pageable.getPageSize());
		}

		// 手机号
		if (searchParams.get("EQ_userPhone") != null
				&& CommonUtils.isNotEmpty((String) searchParams.get("EQ_userPhone"))) {
			String phone = (String) searchParams.get("EQ_userPhone");
			searchParams.remove("EQ_userPhone");
			SsoUserCriteria criteria = new SsoUserCriteria();
			SsoUserCriteria.Criteria c = criteria.createCriteria();
			c.andPhoneEqualTo(phone);
			List<SsoUser> users = ssoUserMapper.selectByExample(criteria);
			if (users.size() == 0) {
				return new PageImpl<>(new ArrayList<OrderVo>(), pageable, 0);
			} else {
				searchParams.put("EQ_userId", users.get(0).getId());
			}
		}

		// 昵称
		if (searchParams.get("EQ_userNickname") != null
				&& CommonUtils.isNotEmpty((String) searchParams.get("EQ_userNickname"))) {
			String nickName = (String) searchParams.get("EQ_userNickname");
			searchParams.remove("EQ_userNickname");
			SsoUserCriteria criteria = new SsoUserCriteria();
			SsoUserCriteria.Criteria c = criteria.createCriteria();
			c.andNickNameLike(nickName);
			List<SsoUser> users = ssoUserMapper.selectByExample(criteria);
			if (users.size() == 0) {
				return new PageImpl<>(new ArrayList<OrderVo>(), pageable, 0);
			} else {
				searchParams.put("EQ_userId", users.get(0).getId());
			}
		}
		
		BookBallCriteria.Criteria cri_book = c_book.createCriteria();
		Map<String, SearchFilter> filters = null;
		if (searchParams != null) {
			filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri_book, filters.values());
		}
		
		// 发布时间：全部、一周内7、三天内3、今天0
		if (searchParams.get("EQ_time") != null && CommonUtils.isNotEmpty((String) searchParams.get("EQ_time"))) {
			Integer time = (Integer) searchParams.get("EQ_time");
			searchParams.remove("EQ_time");
			cri_book.andCtGreaterThanOrEqualTo(DateUtils.minusDaysToday(time));
		}
		
		// 如果场馆id不是空，则按照场馆id过滤结果
		if (statiumId != null) {
			cri_book.andStatiumIdEqualTo(statiumId);
		}
		
		int total = 0;
		if (isHasCount) {
			total = bookBallMapper.countByExample(c_book);
		}
		c_book.setOrderByClause("ct desc");

		//符合条件的约球信息
		List<BookBall> bookList = bookBallMapper.selectByExample(c_book);
		ArrayList<OrderVo> arrayList = new ArrayList<OrderVo>();
		if(CollectionUtils.isNotEmpty(bookList)){
			for (BookBall bookBall : bookList) {
				OrderVo vo = new OrderVo();
				if(null != bookBall.getOrderId()){
					//根据约球id获取约球相关的订单信息
					Order order = this.selectByPrimaryKey(Order.class,bookBall.getOrderId());
					BeanUtils.copyProperties(order, vo);
				}
				//获取发起人信息
				if (CommonUtils.isNotEmpty(bookBall.getUserId())) {
					SsoUser user = this.selectByPrimaryKey(SsoUser.class, bookBall.getUserId());
					if (user != null) {
						vo.setUserPhone(user.getPhone() != null ? user.getPhone() : "");
						vo.setUsername(user.getNickName() != null ? user.getNickName() : "");
					}
				}
				vo.setStatiumName(bookBall.getStatiumName());
				vo.setCt(bookBall.getCt());
				vo.setEt(bookBall.getEt());
				vo.setSportType(bookBall.getSportType());
				vo.setStatiumId(bookBall.getId());
				vo.setCustomerId(bookBall.getUserId());
				arrayList.add(vo);
			}
		}
		return new PageImpl<>(arrayList, pageable, total);
	}

	/**
	 * 
	 * <根据约球id获取参与成员><功能具体实现>
	 *
	 * @create：2015年10月23日 下午2:29:41
	 * @author： liangsh
	 * @param bookId
	 * @return
	 * @throws Exception
	 */
	public List<BookUser> getUsersByBookId(String bookId) throws Exception {
		BookUserCriteria c = new BookUserCriteria();
		BookUserCriteria.Criteria cri = c.createCriteria();
		cri.andBookIdEqualTo(bookId);
		cri.andIsCreateEqualTo(0);
		return bookUserMapper.selectByExample(c);
	}
}
