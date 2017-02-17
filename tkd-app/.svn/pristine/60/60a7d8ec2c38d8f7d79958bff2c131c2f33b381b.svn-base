package com.lc.zy.ball.app.service.index;

import com.lc.zy.ball.app.common.AbstractCacheService;
import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.domain.oa.mapper.CarouselMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.mapper.TrainVideoMapper;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.bean.CacheKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.Collator;
import java.util.*;

@Repository
public class IndexRepository extends AbstractCacheService implements CacheKeys {

	private static Logger logger = LoggerFactory.getLogger(IndexRepository.class);

	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CarouselMapper carouselMapper;

    @Autowired
    private StatiumInfosMapper statiumInfosMapper;
    
    @Autowired
    private TrainVideoMapper trainVideoMapper;

	/**
	 * 
	 * <获取视频集><功能具体实现>
	 *
	 * @create：2016年4月21日 下午3:27:21 
	 * @author： sl
	 * @return
	 */
	public List<TrainVideo> getVideoList(String parentId,int begin,int size) {
		try {
			TrainVideoCriteria cri = new TrainVideoCriteria();
			TrainVideoCriteria.Criteria criteria = cri.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			cri.setMysqlLength(size);
			cri.setMysqlOffset(begin);
			cri.setOrderByClause("sort asc");
			List<TrainVideo> videos = trainVideoMapper.selectByExample(cri);
			return videos;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * <获取轮播图><功能具体实现>
	 *
	 * @create：2016年4月21日 下午3:27:21 
	 * @author： sl
	 * @return
	 */
	public List<Carousel> getCarousel(String carouselType) {
		try {
			CarouselCriteria cri = new CarouselCriteria();
            CarouselCriteria.Criteria criteria = cri.createCriteria();
            if (carouselType.equals("video")) {
                criteria.andTypeEqualTo(carouselType);
            }
			cri.setMysqlLength(Constants.carouselNum.NUM);
			cri.setMysqlOffset(0);
			cri.setOrderByClause("sort asc");
			List<Carousel> carousels = carouselMapper.selectByExample(cri);
			return carousels;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	
	
    /**
     *
     * <获取道馆城市><功能具体实现>
     *
     * @create：16/9/12 16:36
     * @author：sl
     * @param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public List<Map<String, Object>> getCitySigned() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Set<String> set = new HashSet<String>();
        try {
            StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
            List<StatiumInfos> statiumInfoses = statiumInfosMapper.selectByExample(statiumInfosCriteria);

            for (StatiumInfos detail : statiumInfoses) {
                if (detail.getCity() != null && !"".equals(detail.getCity())) {
                    set.add(detail.getProvince() + "," + detail.getCity() + "," + detail.getArea());
                }
            }

            List<String> citylist = new ArrayList<String>(set);
            String province = null;
            String city = null;
            String area = null;
            for (String addr : citylist) {
                if (addr == null)
                    continue;
                province = addr.split(",")[0];
                city = addr.split(",")[1];
                area = addr.split(",")[2];

                if ("市辖区".equals(city) || "县".equals(city)) {
                    if (map.get(province) == null) {
                        List<String> list1 = new ArrayList<String>();
                        list1.add(area);
                        map.put(province, list1);
                    } else {
                        map.get(province).add(area);
                    }
                } else if ("省直辖县级行政区划(*)".equals(city)) {
                    if (map.get(area) == null) {
                        List<String> list1 = new ArrayList<String>();
                        list1.add(area);
                        map.put(area, list1);
                    } else {
                        map.get(area).add(area);
                    }
                /*} else if ("昌吉回族自治州".equals(city)){
                        if (map.get(area) == null) {
                            List<String> list1 = new ArrayList<String>();
                            list1.add(area);
                            map.put(area, list1);
                        } else {
                            map.get(area).add(area);
                        }
                } else if ("红河哈尼族彝族自治州".equals(city)) {
                    if (map.get(area) == null) {
                        List<String> list1 = new ArrayList<String>();
                        list1.add(area);
                        map.put(area, list1);
                    } else {
                        map.get(area).add(area);
                    }*/
                }
                else if(city.length() > 5){
                    if (map.get(area) == null) {
                        List<String> list1 = new ArrayList<String>();
                        list1.add(area);
                        map.put(area, list1);
                    } else {
                        map.get(area).add(area);
                    }
                } else {
                    if (map.get(city) == null) {
                        List<String> list1 = new ArrayList<String>();
                        list1.add(area);
                        map.put(city, list1);
                    } else {
                        map.get(city).add(area);
                    }
                }
            }

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (String cityName : map.keySet()) {
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("name", cityName);
                map2.put("area", map.get(cityName));
                list.add(map2);
            }

            // 排序返回
            ComparatorCityList comparator = new ComparatorCityList();
            return comparator.sort(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * <获取视频集子集视频信息><功能具体实现>
     *
     * @create：2016/12/22 下午5:09
     * @author：sl
     * @param page
     * @param size
     * @param parentId
     * @return java.util.List<com.lc.zy.ball.domain.oa.po.TrainVideo>
     */
    public List<TrainVideo> trainVideos(int page, int size, String parentId) {
        List<TrainVideo> list = new ArrayList<TrainVideo>();
        try {
            TrainVideoCriteria trainVideoCriteria = new TrainVideoCriteria();
            TrainVideoCriteria.Criteria cri = trainVideoCriteria.createCriteria();
            cri.andParentIdEqualTo(parentId);
            trainVideoCriteria.setOrderByClause("sort asc");
            trainVideoCriteria.setMysqlLength(size);
            trainVideoCriteria.setMysqlOffset(page);
            list = trainVideoMapper.selectByExample(trainVideoCriteria);
        } catch (Exception e) {
            logger.debug("获取视频集子集视频信息 {}", e.getMessage());
        }
        return list;
    }

    // 对cityList进行排序
    public class ComparatorCityList implements Comparator<Map<String, Object>> {
        public List<Map<String, Object>> sort(List<Map<String, Object>> strList) {
            ComparatorCityList comp = new ComparatorCityList();
            Collections.sort(strList, comp);
            return strList;
        }

        public int compare(Map<String, Object> value1, Map<String, Object> value2) {
            String s1 = value1.get("name").toString();
            String s2 = (String) value2.get("name").toString();
            return Collator.getInstance(Locale.CHINESE).compare(s1, s2);
        }
    }

    public static void main(String[] args) {
        String ss = "呼和浩特市";
        System.out.println(ss.length());
    }
}
