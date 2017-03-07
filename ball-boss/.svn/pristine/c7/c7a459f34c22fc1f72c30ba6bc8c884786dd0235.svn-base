package com.lc.zy.ball.boss.framework.carousel.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.framework.carousel.service.CarouselService;
import com.lc.zy.ball.boss.framework.carousel.vo.CarouselVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.Carousel;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/carousel")
public class CarouselController {

	private static final Logger logger = LoggerFactory.getLogger(CarouselController.class);

	@Autowired
	private CarouselService carouselService;

	/**
	 * 
	 * 轮播图添加页面初始化
	 *
	 * @create：2015年6月26日 上午11:38:01
	 * @author： sl
	 * @return
	 */
	@RequestMapping(value = "sign", method = RequestMethod.GET)
	public String sign() {
		return "/carousel/carouselForm";
	}

	/**
	 * 
	 * <轮播图添加/更新>
	 *
	 * @create：2015年6月26日 下午4:09:33
	 * @author： sl
	 * @param myForm
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "carousalForm", method = RequestMethod.POST)
	public String carousalForm(CarouselVo myForm, Model model, HttpServletRequest request) {
		try {
			Carousel carousel = new Carousel();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date time = sdf.parse(sdf.format(new Date()));
			if (StringUtils.isNotEmpty(myForm.getId())) {// 更新
				// 优惠券id
				carousel.setId(myForm.getId());
				// 更新时间
				carousel.setUpdateTime(time);
				// 创建时间
				Carousel carouselTime = new Carousel();
				carousel = carouselService.selectCarouselById(myForm.getId());
				carousel.setCreateTime(carouselTime.getCreateTime());
			} else {// 新增
				// 优惠券id
				String id = UUID.get();
				carousel.setId(id);
				// 创建时间
				carousel.setCreateTime(time);
				// 更新时间
				carousel.setUpdateTime(time);
			}
			/*String imagePath = "";
			if(myForm.getmImage()!=null){
				if (myForm.getmImage().getSize() != 0) {
					// 图片处理
					imagePath = savePhoto(myForm.getmImage());
					myForm.setImage(imagePath);
					// 图片链接
					carousel.setImage(imagePath);
				} else {
					carousel.setImage(myForm.getImage());
				}
			}*/
			
			if (StringUtils.isNotEmpty(myForm.getAreaCode())) {
				Map<String,String> areaMap = Zonemap.split(myForm.getAreaCode());
				if ("市辖区".equals(areaMap.get("city")) || areaMap.get("city") == null) {
					carousel.setAreaCode(myForm.getAreaCode().substring(0,2)+"0000");
					carousel.setCity(areaMap.get("province"));
				} else {
					carousel.setAreaCode(myForm.getAreaCode());
					carousel.setCity(areaMap.get("city"));
				}
			} else {
				
			}
			// 名称
			carousel.setName(myForm.getName());
			// 位置
			carousel.setPosition(myForm.getPosition());
			// 类型
			carousel.setType(myForm.getType());
			//微信公众号内容
			carousel.setContentWx(myForm.getContentWx());
			// 资源主键
			carousel.setResourceId(myForm.getResourceId());
			// 轮播图图片
			carousel.setImage(myForm.getImage());
			carousel.setImageNew(myForm.getImageNew());//新版轮播图
			// 排序
			// 获取轮播图数量
			int carouselNum = carouselService.selectCarouselsCounts(carousel.getAreaCode());
			if (myForm.getSort() <= carouselNum) {
				if (StringUtils.isNotEmpty(myForm.getId())) {
					if (myForm.getSort() >= carousel.getSort()) {
						updateLtCarousels(carousel.getSort(), myForm.getSort(), "gt",carousel.getAreaCode());
					} else {
						updateLtCarousels(carousel.getSort(), myForm.getSort(), "lt",carousel.getAreaCode());
					}
				}
				carousel.setSort(myForm.getSort());
			} else {
				if (StringUtils.isNotEmpty(myForm.getId())) {// 修改
					updateLtCarousels(carousel.getSort(), myForm.getSort(), "gt",carousel.getAreaCode());
					carousel.setSort(carouselNum);
				} else {// 新增
					carousel.setSort(carouselNum + 1);
				}
			}
			// 更新原数据sort(若输入sort，在轮播图中存在的情况)
			Carousel carouselExist = new Carousel();
			carouselExist = carouselService.selectCarouselExistBySort(myForm.getSort(),carousel.getAreaCode());
			if (carouselExist != null) {
				if (!StringUtils.isNotEmpty(myForm.getId())) {
					updateSort(myForm.getSort(), "add",carousel.getAreaCode());
				}
			}

			// 插入数据
			if (StringUtils.isNotEmpty(myForm.getId())) {
				carouselService.updateCarousel(carousel);
			} else {
				carouselService.insertCarousel(carousel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("轮播图添加/更新(carousalForm)" + e.getMessage());
		}
		return "redirect:/carousel/list";
	}

	/**
	 * 
	 * <图片处理>
	 *
	 * @create：2015年6月26日 下午3:57:55
	 * @author： sl
	 * @param photo
	 * @return
	 * @throws Exception
	 */
	private String savePhoto(MultipartFile photo) throws Exception {
//		byte[] b = photo.getBytes();
//		String name = photo.getName();
//		String uuid = UUID.get();
//		String path = FileUtil
//				.save(uuid + FileUtil.originalPicture, name + FileUtil.originalPicture, b, false, "image");
//		photo.getInputStream().close();
//		return FileUtil.getFileUrl(path);
		return null; 
	}

	/**
	 * 
	 * <获取轮播图list>
	 *
	* @create：2015年6月26日 下午4:23:44
	 * @author： sl
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "list")
	public String carousalList(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<Carousel> coachPage = null;
		try {
			coachPage = carouselService.findCarouselList(new PageRequest(page, size), searchParams);
		} catch (Exception e) {
			logger.error("carousalList error:" + e.getMessage());
		}
		model.addAttribute("position");
		model.addAttribute("data", coachPage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "/carousel/carouselList";
	}

	/**
	 * 
	 * <轮播图删除>
	 *
	 * @create：2015年6月26日 下午4:54:02
	 * @author： sl
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteCarousel/{id}/{areaCode}", method = RequestMethod.POST)
	public String deleteCarousel(@PathVariable String id,@PathVariable String areaCode, Model model) {
		if (id != null && !"".equals(id)) {
			try {
				// sort
				Integer sort = carouselService.selectCarouselById(id).getSort();
				// 删除
				carouselService.deleteCarouselById(id);
				// 删除轮播图，后面提前,更新原数据sort
				if("0".equals(areaCode)){
					areaCode = null;
				}else{
					
				}
				updateSort(sort, "delete",areaCode);
			} catch (Exception e) {
				logger.error("deleteCarousel error:" + e.getMessage());
				return "redirect:/carousel/list";
			}
		}
		return "redirect:/carousel/list";
	}

	/**
	 * 
	 * <轮播图修改初始化>
	 *
	 * @create：2015年6月26日 下午5:17:52
	 * @author： sl
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateCarousel/{id}", method = RequestMethod.GET)
	public String updateCarousel(@PathVariable String id, Model model) {
		if (id != null && !id.equals("")) {
			try {
				Carousel carousel = new Carousel();
				carousel = carouselService.selectCarouselById(id);
				model.addAttribute("carousel", carousel);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("updateCarousel error:" + e.getMessage());
				return "redirect:/carousel/list";
			}
		}
		return "/carousel/carouselForm";
	}

	/**
	 * 
	 * <轮播图置顶>
	 *
	 * @create：2015年6月30日 上午10:57:10
	 * @author： sl
	 * @param sort
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/setTop/{sort}/{areaCode}", method = RequestMethod.POST)
	public String setTop(@PathVariable String sort,@PathVariable String areaCode, Model model) {
		if (sort != null && !"".equals(sort)) {
			try {
				List<Carousel> carouselsNew = new ArrayList<Carousel>();
				// 获取sort以前的记录
				List<Carousel> carousels = new ArrayList<Carousel>();
				if("0".equals(areaCode)){
					areaCode = null;
				}else{
					
				}
				carousels = carouselService.selectCarouselsBySort(Integer.valueOf(sort),areaCode);
				if (carousels.size() > 0) {
					for (Carousel carousel : carousels) {
						// 判断是否是置顶记录
						if (carousel.getSort() == Integer.valueOf(sort)) {
							carousel.setSort(1);
						} else {
							carousel.setSort(carousel.getSort() + 1);
						}
						carouselsNew.add(carousel);
					}
					// 更新最新轮播图排序
					carouselService.updateCarousels(carouselsNew);
				}

			} catch (Exception e) {
				logger.error("setTop error:" + e.getMessage());
				return "redirect:/carousel/list";
			}
		}

		return "redirect:/carousel/list";
	}
	
	/**
	 * 
	 * <轮播图上移>
	 *
	 * @create：2015年7月1日 下午6:43:50
	 * @author： sl
	 * @param sort
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/setUp/{sort}/{areaCode}", method = RequestMethod.POST)
	public String setUp(@PathVariable String sort,@PathVariable String areaCode, Model model) {
		if (sort != null && !"".equals(sort)) {
			try {
				List<Carousel> carouselsNew = new ArrayList<Carousel>();
				List<Carousel> carousels = new ArrayList<Carousel>();
				// 获取轮播图
				if("0".equals(areaCode)){
					areaCode = null;
				}else{
					
				}
				carousels = carouselService.selectCarouselsBySort(Integer.valueOf(sort),areaCode);
				// 获取轮播图数量
				int j = carousels.size();
				// 后一轮播图
				Carousel carousel1 = new Carousel();
				carousel1 = carousels.get(j-1);
				// 前一轮播图
				Carousel carousel2 = new Carousel();
				carousel2 = carousels.get(j-2);
				// 前一轮播图sort
				int startSort = carousel2.getSort();
				// 后一轮播图sort
				int endSort = carousel1.getSort();
				// 后一轮播图sort设置为前一轮播图sort
				carousel1.setSort(startSort);
				carouselsNew.add(carousel1);
				// 前一轮播图sort设置为后一轮播图sort
				carousel2.setSort(endSort);
				carouselsNew.add(carousel2);
				// 更新最新轮播图排序
				carouselService.updateCarousels(carouselsNew);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("setTop error:" + e.getMessage());
				return "redirect:/carousel/list";
			}
		}
		return "redirect:/carousel/list";
	}

	/**
	 * 
	 * <轮播图下移>
	 *
	 * @create：2015年7月1日 下午6:44:10
	 * @author： sl
	 * @param sort
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/setDown/{sort}/{areaCode}", method = RequestMethod.POST)
	public String setDown(@PathVariable String sort,@PathVariable String areaCode, Model model) {
		if (sort != null && !"".equals(sort)) {
			try {
				List<Carousel> carouselsNew = new ArrayList<Carousel>();
				List<Carousel> carousels = new ArrayList<Carousel>();
				// 获取轮播图
				if("0".equals(areaCode)){
					areaCode = null;
				}else{
					
				}
				carousels = carouselService.selectGteCarouselsBySort(Integer.valueOf(sort),areaCode);
				// 后一轮播图
				Carousel carousel1 = new Carousel();
				carousel1 = carousels.get(1);
				// 前一轮播图
				Carousel carousel2 = new Carousel();
				carousel2 = carousels.get(0);
				// 前一轮播图sort
				int startSort = carousel2.getSort();
				// 后一轮播图sort
				int endSort = carousel1.getSort();
				// 后一轮播图sort设置为前一轮播图sort
				carousel1.setSort(startSort);
				carouselsNew.add(carousel1);
				// 前一轮播图sort设置为后一轮播图sort
				carousel2.setSort(endSort);
				carouselsNew.add(carousel2);
				// 更新最新轮播图排序
				carouselService.updateCarousels(carouselsNew);
			} catch (Exception e) {
				logger.error("setTop error:" + e.getMessage());
				return "redirect:/carousel/list";
			}
		}
		return "redirect:/carousel/list";
	}
	
	/**
	 * 
	 * <保存/更新数据时，根据sort修改原数据sort>
	 *
	 * @create：2015年6月30日 下午12:15:41
	 * @author： sl
	 * @param sort
	 */
	public void updateSort(Integer sort, String type,String areaCode) {
		if (sort != null && !"".equals(sort)) {
			try {
				List<Carousel> carouselsNew = new ArrayList<Carousel>();
				// 获取sort以前的记录
				List<Carousel> carousels = new ArrayList<Carousel>();
				carousels = carouselService.selectGteCarouselsBySort(sort,areaCode);
				if (carousels.size() > 0) {
					for (Carousel carousel : carousels) {
						if (type.equals("add")) {// 增加/更新
							carousel.setSort(carousel.getSort() + 1);
						} else if (type.equals("delete")) {// 删除
							carousel.setSort(carousel.getSort() - 1);
						} else if (type.equals("update")) {
							carousel.setSort(carousel.getSort() - 1);
						}
						carouselsNew.add(carousel);
					}
					// 更新最新轮播图排序
					carouselService.updateCarousels(carousels);
				}

			} catch (Exception e) {
				logger.error("updateSort error:" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * <获取区间sort，轮播图>
	 *
	 * @create：2015年7月7日 下午8:38:32
	 * @author： sl
	 * @param oldSort
	 * @param newSort
	 * @param type
	 */
	public void updateLtCarousels(Integer oldSort, Integer newSort, String type,String areaCode){
		try {
			List<Carousel> carouselsNew = new ArrayList<Carousel>();
			// 获取sort以前的记录
			List<Carousel> carousels = new ArrayList<Carousel>();
			if ("gt".equals(type)){// sort改大时
				carousels = carouselService.selectCarouselsBySorts(oldSort, newSort,areaCode);
				if (carousels.size() > 0) {
					for (Carousel carousel : carousels) {
						carousel.setSort(carousel.getSort() - 1);
						carouselsNew.add(carousel);
					}
					// 更新最新轮播图排序
					carouselService.updateCarousels(carousels);
				}
			} else if ("lt".equals(type)){// sort改小时
				carousels = carouselService.selectLtCarouselsBySorts(oldSort, newSort,areaCode);
				if (carousels.size() > 0) {
					for (Carousel carousel : carousels) {
						carousel.setSort(carousel.getSort() + 1);
						carouselsNew.add(carousel);
					}
					// 更新最新轮播图排序
					carouselService.updateCarousels(carousels);
				}
			}
		} catch (Exception e) {
			logger.error("updateLtCarousels error:" + e.getMessage());
		}
	}

}