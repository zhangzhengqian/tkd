package com.lc.zy.ball.boss.framework.carousel.service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.CarouselMapper;
import com.lc.zy.ball.domain.oa.po.Carousel;
import com.lc.zy.ball.domain.oa.po.CarouselCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.MyGson;

@Service
@Transactional(readOnly = true)
public class CarouselService extends AbstractCacheService {
	private static final Logger logger = LoggerFactory.getLogger(CarouselService.class);

	@Autowired
	private CarouselMapper carouselMapper;
	
	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	/**
	 * 
	 * <获取轮播图信息(app)>
	 *
	 * @create：2015年6月26日 下午12:15:30
	 * @author： sl
	 * @return
	 */
	public List<Carousel> latest() {
		CarouselCriteria criteria = new CarouselCriteria();
		// 正序排序（从小到大）
		criteria.setOrderByClause("sort asc");
		// 轮播图数量
		if ("num".equals(Constants.carousel.CAROUSEL_TYPE)) {
			criteria.setMysqlOffset(0);
			criteria.setMysqlLength(Constants.carousel.CAROUSEL_NUM);
		}
		List<Carousel> list = new ArrayList<Carousel>();
		try {
			list = carouselMapper.selectByExample(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取轮播图信息（app）(CarouselManagerService--latest)" + e.getMessage());
		}
		return list;

	}

	/**
	 * 
	 * <获取轮播信息(微信公众号)>
	 *
	 * @create：2015年7月13日 下午18:45:43
	 * @author： yankefei
	 * @return
	 */
	public List<Carousel> wxMessageList() {
		List<Carousel> list = new ArrayList<Carousel>();
		try {
			CarouselCriteria carouselCriteria = new CarouselCriteria();
			CarouselCriteria.Criteria criteria = carouselCriteria.createCriteria();
			criteria.andPositionEqualTo(2);
			list = carouselMapper.selectByExample(carouselCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取轮播信息(微信公众号)(CarouselManagerService--latest)" + e.getMessage());
		}
		return list;

	}

	/**
	 * 
	 * <轮播图添加>
	 *
	 * @create：2015年6月26日 下午3:56:52
	 * @author： sl
	 * @param record
	 */
	@Transactional(readOnly = false)
	public void insertCarousel(Carousel record) {
		try {
			logger.info(record.toString());
			this.insertSelective(record, record.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("轮播图添加(CarouselManagerService--insertCarousel)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <轮播图更新>
	 *
	 * @create：2015年6月30日 下午4:39:30
	 * @author： sl
	 * @param record
	 */
	@Transactional(readOnly = false)
	public void updateCarousel(Carousel record) {
		try {
			this.updateByPrimaryKey(record, record.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("轮播图更新(CarouselManagerService--updateCarousel)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <获取轮播图list>
	 *
	 * @create：2015年6月26日 下午4:16:31
	 * @author： sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<Carousel> findCarouselList(PageRequest pageRequest, Map<String, Object> searchParams) {
		int total = 0;
		List<Carousel> list = new ArrayList<Carousel>();
		try {
			CarouselCriteria carouselCriteria = new CarouselCriteria();
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			carouselCriteria.setMysqlLength(pageRequest.getPageSize());
			carouselCriteria.setMysqlOffset(pageRequest.getOffset());
			CarouselCriteria.Criteria criteria = carouselCriteria.createCriteria();
			carouselCriteria.setOrderByClause("sort asc");
			Criterias.bySearchFilter(criteria, filters.values());
			total = carouselMapper.countByExample(carouselCriteria);
			list = carouselMapper.selectByExample(carouselCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取轮播图list(CarouselManagerService--findCarouselList)" + e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}

	/**
	 * 
	 * <轮播图删除>
	 *
	 * @create：2015年6月26日 下午4:39:14
	 * @author： sl
	 * @param id
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void deleteCarouselById(String id) throws Exception {
		if (id != null && !id.equals("")) {
			try {
				this.deleteByPrimaryKey(Carousel.class, id);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("轮播图删除(CarouselManagerService--deleteCarouselById)" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * <轮播图修改初始化>
	 *
	 * @create：2015年6月26日 下午5:04:08
	 * @author： sl
	 * @param id
	 * @return
	 */
	public Carousel selectCarouselById(String id) {
		Carousel carousel = new Carousel();
		try {
			carousel = this.selectByPrimaryKey(Carousel.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("轮播图修改初始化(CarouselManagerService--selectCarouselById)" + e.getMessage());
		}
		return carousel;
	}

	/**
	 * 
	 * <根据sort获取轮播图前的记录>
	 *
	 * @create：2015年6月30日 上午10:52:27
	 * @author： sl
	 * @param sort
	 * @return
	 */
	public List<Carousel> selectCarouselsBySort(Integer sort,String areaCode) {
		List<Carousel> carousels = new ArrayList<Carousel>();
		try {
			CarouselCriteria carouselCriteria = new CarouselCriteria();
			CarouselCriteria.Criteria criteria = carouselCriteria.createCriteria();
			criteria.andSortLessThanOrEqualTo(sort);
			if(StringUtils.isBlank(areaCode)){
				criteria.andAreaCodeIsNull();
			}else{
				criteria.andAreaCodeEqualTo(areaCode);
			}
			carouselCriteria.setOrderByClause("sort asc");
			carousels = carouselMapper.selectByExample(carouselCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据sort获取轮播图前的记录(CarouselManagerService--selectCarouselsBySort)" + e.getMessage());
		}
		return carousels;
	}

	/**
	 * 
	 * <更新轮播图sort>
	 *
	 * @create：2015年6月30日 上午10:55:32
	 * @author： sl
	 * @param carousels
	 */
	@Transactional(readOnly = false)
	public void updateCarousels(List<Carousel> carousels) {
		try {
			for (Carousel record : carousels) {
				this.updateByPrimaryKey(record, record.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新轮播图sort(CarouselManagerService--updateCarousels)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <获取大于等于轮播图的记录>
	 *
	 * @create：2015年6月30日 下午12:13:15
	 * @author： sl
	 * @param sort
	 * @return
	 */
	public List<Carousel> selectGteCarouselsBySort(Integer sort,String areaCode) {

		List<Carousel> carousels = new ArrayList<Carousel>();
		try {
			CarouselCriteria carouselCriteria = new CarouselCriteria();
			CarouselCriteria.Criteria criteria = carouselCriteria.createCriteria();
			criteria.andSortGreaterThanOrEqualTo(sort);
			if(StringUtils.isBlank(areaCode)){
				criteria.andAreaCodeIsNull();
			}else{
				criteria.andAreaCodeEqualTo(areaCode);
			}
			carouselCriteria.setOrderByClause("sort asc");
			carousels = carouselMapper.selectByExample(carouselCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据sort获取轮播图前的记录(CarouselManagerService--selectCarouselsBySort)" + e.getMessage());
		}
		return carousels;
	}

	/**
	 * 
	 * <根据sort查询记录是否存在>
	 *
	 * @create：2015年6月30日 下午3:37:23
	 * @author： sl
	 * @param sort
	 * @return
	 */
	public Carousel selectCarouselExistBySort(Integer sort,String areaCode) {
		List<Carousel> carousels = new ArrayList<Carousel>();
		try {
			CarouselCriteria carouselCriteria = new CarouselCriteria();
			CarouselCriteria.Criteria criteria = carouselCriteria.createCriteria();
			criteria.andSortEqualTo(sort);
			if(StringUtils.isBlank(areaCode)){
				criteria.andAreaCodeIsNull();
			}else{
				criteria.andAreaCodeEqualTo(areaCode);
			}
			carouselCriteria.setOrderByClause("sort asc");
			carousels = carouselMapper.selectByExample(carouselCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据sort查询记录是否存在(CarouselManagerService--selectCarouselsBySort)" + e.getMessage());
		}
		if (carousels.isEmpty()){
			return null;
		} else {
			return carousels.get(0);
		}
	}

	/**
	 * 
	 * <获取轮播图数量>
	 *
	 * @create：2015年7月1日 下午6:37:35
	 * @author： sl
	 * @return
	 */
	public Integer selectCarouselsCounts(String areaCode) {
		int carouselNum = 0;
		try {
			CarouselCriteria carouselCriteria = new CarouselCriteria();
			CarouselCriteria.Criteria criteria = carouselCriteria.createCriteria();
			if(StringUtils.isNotBlank(areaCode)){
				criteria.andAreaCodeEqualTo(areaCode);
			}else{
				criteria.andAreaCodeIsNull();
			}
			carouselNum = carouselMapper.countByExample(null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取轮播图数量(CarouselManagerService--selectCarouselsCounts)" + e.getMessage());
		}
		return carouselNum;
	}

	/**
	 * 
	 * <获取小于sort数据>
	 *
	 * @create：2015年7月7日 下午6:22:22
	 * @author： sl
	 * @param sort
	 * @return
	 */
	public Integer selectLtCarouselsBySort(Integer sort,String areaCode) {
		int ltNum = 0;
		try {
			CarouselCriteria carouselCriteria = new CarouselCriteria();
			CarouselCriteria.Criteria criteria = carouselCriteria.createCriteria();
			criteria.andSortLessThan(sort);
			if(StringUtils.isBlank(areaCode)){
				criteria.andAreaCodeIsNull();
			}else{
				criteria.andAreaCodeEqualTo(areaCode);
			}
			ltNum = carouselMapper.countByExample(carouselCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取小于sort数据(CarouselManagerService--selectLtCarouselsBySort)" + e.getMessage());
		}
		return ltNum;
	}

	/**
	 * 
	 * <获取区间sort,轮播图,sort改大时>
	 *
	 * @create：2015年7月7日 下午8:41:22
	 * @author： sl
	 * @param oldSort
	 * @param newSort
	 * @return
	 */
	public List<Carousel> selectCarouselsBySorts(Integer oldSort, Integer newSort,String areaCode) {
		List<Carousel> carousels = new ArrayList<Carousel>();
		try {
			CarouselCriteria carouselCriteria = new CarouselCriteria();
			CarouselCriteria.Criteria criteria = carouselCriteria.createCriteria();
			criteria.andSortGreaterThan(oldSort);
			criteria.andSortLessThanOrEqualTo(newSort);
			if(StringUtils.isBlank(areaCode)){
				criteria.andAreaCodeIsNull();
			}else{
				criteria.andAreaCodeEqualTo(areaCode);
			}
			carouselCriteria.setOrderByClause("sort asc");
			carousels = carouselMapper.selectByExample(carouselCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据sort获取轮播图前的记录(CarouselManagerService--selectCarouselsBySorts)" + e.getMessage());
		}
		return carousels;
	}

	/**
	 * 
	 * <获取区间sort,轮播图,sort改小时>
	 *
	 * @create：2015年7月7日 下午8:39:21
	 * @author： sl
	 * @param oldSort
	 * @param newSort
	 * @return
	 */
	public List<Carousel> selectLtCarouselsBySorts(Integer oldSort, Integer newSort,String areaCode) {
		List<Carousel> carousels = new ArrayList<Carousel>();
		try {
			CarouselCriteria carouselCriteria = new CarouselCriteria();
			CarouselCriteria.Criteria criteria = carouselCriteria.createCriteria();
			criteria.andSortGreaterThanOrEqualTo(newSort);
			criteria.andSortLessThan(oldSort);
			if(StringUtils.isBlank(areaCode)){
				criteria.andAreaCodeIsNull();
			}else{
				criteria.andAreaCodeEqualTo(areaCode);
			}
			carouselCriteria.setOrderByClause("sort asc");
			carousels = carouselMapper.selectByExample(carouselCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据sort获取轮播图前的记录(CarouselManagerService--selectLtCarouselsBySorts)" + e.getMessage());
		}
		return carousels;
	}

	/**
	 * 
	 * <获取轮播图><功能具体实现>
	 *
	 * @create：2015年7月9日 下午7:25:07
	 * @author： lsh
	 * @param example
	 * @return
	 */
	public List<Carousel> selectByExample() {
		CarouselCriteria carouselCriteria = new CarouselCriteria();
		CarouselCriteria.Criteria criteria = carouselCriteria.createCriteria();
		criteria.andTypeEqualTo("url");
		carouselCriteria.setOrderByClause("sort asc");
		List<Carousel> list = new ArrayList<Carousel>();
		try {
			list = carouselMapper.selectByExample(carouselCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取轮播图信息（app）(CarouselManagerService--selectByExample)" + e.getMessage());
		}
		return list;
	}

	/**
	 * 
	 * <优惠券><微信公众平台>
	 *
	 * @create：2015年7月20日 下午6:02:19
	 * @author： zw
	 * @return
	 */
	public List<Carousel> selectWinxinUrl() {
		CarouselCriteria carouselCriteria = new CarouselCriteria();
		CarouselCriteria.Criteria criteria = carouselCriteria.createCriteria();
		criteria.andTypeEqualTo("url");
		criteria.andPositionEqualTo(2);
		carouselCriteria.setOrderByClause("sort asc");
		List<Carousel> list = new ArrayList<Carousel>();
		try {
			list = carouselMapper.selectByExample(carouselCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取微信公众平台优惠券信息(CarouselManagerService--selectWinxinUrl)" + e.getMessage());
		}
		return list;
	}
}
