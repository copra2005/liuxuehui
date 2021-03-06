package com.liuxue.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.liuxue.base.Constants;
import com.liuxue.base.util.SpercialCharUtil;
import com.liuxue.dao.Page;
import com.liuxue.entity.AdminUser;
import com.liuxue.entity.WenAn;
import com.liuxue.entity.WenAnContent;
import com.liuxue.service.WenAnContentService;
import com.liuxue.service.WenAnService;

@Controller
@RequestMapping("/wenan")
public class WenAnController extends BaseController{

	@Autowired
	private WenAnService wenAnService;
	
	@Autowired
	private WenAnContentService wenAnContentService;
	
	//分页进入列表页面
	@RequestMapping(value="/more",method=RequestMethod.GET)
	public ModelAndView lieBiao(@RequestParam("pageNo")String pageNo,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		AdminUser adminUser = (AdminUser)request.getSession().getAttribute("adminUserSession");
		int pageNoInt = Integer.parseInt(pageNo); //页数 页码
		int pageSize = 5;//每页记录数
		try {
			Page page = wenAnService.page(pageNoInt, pageSize);
			List<WenAn> wenAnList = (List<WenAn>)page.getResult();
			mav.addObject("wenAnList", wenAnList);
			mav.addObject("page", page);
			mav.addObject("pageUrl","wenan/more.shtml");
			if (adminUser==null) {
				mav.setViewName("forward:/view/jinPaiWenAn.jsp");
			}else{
				mav.setViewName("forward:/lxhAdmin/wenan/wenAnManage.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("errorMsg", "数据库访问出错");
			mav.setViewName("forward:/view/fail.jsp");
		}
		return mav;
	}
	
	//进入内容页面
	@RequestMapping(value="/content",method=RequestMethod.GET)
	public ModelAndView content(@RequestParam("wenAnGuid")String wenAnGuid,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		AdminUser adminUser = (AdminUser)request.getSession().getAttribute("adminUserSession");
		try {
			WenAn wenAn = wenAnService.getWenAnByGuid(wenAnGuid);
			if (wenAn.getRenQi()==null) {
				wenAn.setRenQi(1);
			}else {
				wenAn.setRenQi(wenAn.getRenQi()+1);
			}
			
			WenAnContent wenAnContent = wenAnContentService.findWenAnContentByWenAnGuid(wenAnGuid);
			mav.addObject("wenAn",wenAn);
			mav.addObject("wenAnContent",wenAnContent);
			if (adminUser==null) {
				mav.setViewName("forward:/view/jinPaiWenAnContent.jsp");
			}else{
				mav.setViewName("forward:/lxhAdmin/wenan/wenAnContent.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("errorMsg", "数据库访问出错");
			mav.setViewName("forward:/view/fail.jsp");
		}
		return mav;
	}
	
	
	//新增 包括内容
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView add(@RequestParam("name")String name,@RequestParam("photo")MultipartFile photo,
			@RequestParam("indexPhoto")MultipartFile indexPhoto,@RequestParam("indexXyBj")String indexXyBj,
			@RequestParam("shengFeng")String shengFeng,@RequestParam("ruHangShiJian")String ruHangShiJian,
			@RequestParam("zixunxingshi")String zixunxingshi,@RequestParam("shanchangguojia")String shanchangguojia,
			@RequestParam("shanChangZhuanYe")String shanChangZhuanYe,@RequestParam("zhiYeXingTiao")String zhiYeXingTiao,
			@RequestParam("xueYuanPingJia")String xueYuanPingJia,@RequestParam("renqi")String renqi,
			@RequestParam("xinJiPingJia")String xinJiPingJia,@RequestParam("weekQianDanLiang")String weekQianDanLiang,
			@RequestParam("monthQianDanLiang")String monthQianDanLiang,@RequestParam("yearQianDanLiang")String yearQianDanLiang,
			@RequestParam("dianhua")String dianhua,@RequestParam("QQ")String QQ,@RequestParam("weiXin")String weiXin,
			@RequestParam("picture")MultipartFile picture,@RequestParam("boKeUrl")String boKeUrl,@RequestParam("indexMiaoShu")String indexMiaoShu,
			@RequestParam("jieshao")String jieshao,@RequestParam("indexWeiZhi")String indexWeiZhi,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		AdminUser adminUser = (AdminUser)request.getSession().getAttribute("adminUserSession");
		try {
			if (adminUser==null) {
				mav.setViewName("redirect:/lxhAdmin/adminDengLu.jsp");
			}else{
				Integer renQiInteger = renqi.equals("")?null:Integer.parseInt(renqi);
				Integer xinJiPingJiaInteger = xinJiPingJia.equals("")?null:Integer.parseInt(xinJiPingJia);
				Integer weekInteger = weekQianDanLiang.equals("")?null:Integer.parseInt(weekQianDanLiang);
				Integer monthInteger = monthQianDanLiang.equals("")?null:Integer.parseInt(monthQianDanLiang);
				Integer yearInteger = yearQianDanLiang.equals("")?null:Integer.parseInt(yearQianDanLiang);
				Integer indexXyBj_Integer = indexXyBj.equals("")?null:Integer.parseInt(indexXyBj);
				Integer weiZhiInteger = indexWeiZhi.equals("")?null:Integer.parseInt(indexWeiZhi);
				
				WenAn wenAn = new WenAn();
				wenAn.setMonthQianDanLiang(monthInteger);
				wenAn.setName(name);
				wenAn.setIndexWeiZhi(weiZhiInteger);
				wenAn.setRenQi(renQiInteger);
				wenAn.setRuHangShiJian(ruHangShiJian);
				wenAn.setShanChangGuoJia(shanchangguojia);
				wenAn.setShanChangZuanYe(shanChangZhuanYe);
				wenAn.setShenFen(shengFeng);
				wenAn.setWeekQianDanLiang(weekInteger);
				wenAn.setXinJiPingJia(xinJiPingJiaInteger);
				wenAn.setXueYuanPinJia(xueYuanPingJia);
				wenAn.setYearQianDanLiang(yearInteger);
				wenAn.setZhiYeXinTiao(zhiYeXingTiao);
				wenAn.setZiXunXingShi(zixunxingshi);
				wenAn.setIndexXyBj(indexXyBj_Integer);
				wenAn.setIndexMiaoShu(indexMiaoShu);
				
				
				String newImageName = SpercialCharUtil.suiJiImageName(photo.getOriginalFilename());// 生成随机图片名称，防止重复
				wenAn.setPhotoName(newImageName);
				photo.transferTo(new File(request.getSession().getServletContext().getRealPath("/")+
						Constants.wenAnLieBiaoImagePath+newImageName));
				
				
				if(!indexPhoto.isEmpty()){
					String newImageName2 = SpercialCharUtil.suiJiImageName(indexPhoto.getOriginalFilename());// 生成随机图片名称，防止重复
					wenAn.setIndexPhotoName(newImageName2);
					indexPhoto.transferTo(new File(request.getSession().getServletContext().getRealPath("/")+
							Constants.wenAnIndexImagePath+newImageName2));//保存首页图片图片
				}
				wenAnService.addWenAn(wenAn);
				
				WenAnContent wenAnContent = new WenAnContent();
				wenAnContent.setBoKeUrl(boKeUrl);
				wenAnContent.setDianHua(dianhua);
				wenAnContent.setWenAnGuid(wenAn.getGuid());
				wenAnContent.setJieShao(jieshao);
				wenAnContent.setQQ(QQ);
				wenAnContent.setWeiXin(weiXin);
				if(!picture.isEmpty()){
					String newImageName3 = SpercialCharUtil.suiJiImageName(picture.getOriginalFilename());// 生成随机图片名称，防止重复
					wenAnContent.setPictureName(newImageName3);//设置内容图片名称
					picture.transferTo(new File(request.getSession().getServletContext().getRealPath("/")+
							Constants.wenAnContentImagePath+newImageName3));//保存内容图片图片
				}
				wenAnContentService.addWenAnContent(wenAnContent);
				mav.addObject("msg", "新增成功");
				mav.setViewName("forward:/lxhAdmin/wenan/addWenAn.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("errorMsg", "数据库访问出错");
			mav.setViewName("forward:/view/fail.jsp");
		}
		return mav;
	}
	//修改 包括内容
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ModelAndView update(@RequestParam("wenAnGuid")String wenAnGuid,@RequestParam("name")String name,@RequestParam("photo")MultipartFile photo,
			@RequestParam("indexPhoto")MultipartFile indexPhoto,@RequestParam("indexXyBj")String indexXyBj,
			@RequestParam("shengFeng")String shengFeng,@RequestParam("ruHangShiJian")String ruHangShiJian,
			@RequestParam("zixunxingshi")String zixunxingshi,@RequestParam("shanchangguojia")String shanchangguojia,
			@RequestParam("shanChangZhuanYe")String shanChangZhuanYe,@RequestParam("zhiYeXingTiao")String zhiYeXingTiao,
			@RequestParam("xueYuanPingJia")String xueYuanPingJia,@RequestParam("renqi")String renqi,
			@RequestParam("xinJiPingJia")String xinJiPingJia,@RequestParam("weekQianDanLiang")String weekQianDanLiang,
			@RequestParam("monthQianDanLiang")String monthQianDanLiang,@RequestParam("yearQianDanLiang")String yearQianDanLiang,
			@RequestParam("dianhua")String dianhua,@RequestParam("QQ")String QQ,@RequestParam("weiXin")String weiXin,
			@RequestParam("picture")MultipartFile picture,@RequestParam("boKeUrl")String boKeUrl,@RequestParam("indexMiaoShu")String indexMiaoShu,
			@RequestParam("jieshao")String jieshao,@RequestParam("indexWeiZhi")String indexWeiZhi,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		AdminUser adminUser = (AdminUser)request.getSession().getAttribute("adminUserSession");
		try {
			if (adminUser==null) {
				mav.setViewName("redirect:/lxhAdmin/adminDengLu.jsp");
			}else{
				Integer renQiInteger = renqi.equals("")?null:Integer.parseInt(renqi);
				Integer xinJiPingJiaInteger = xinJiPingJia.equals("")?null:Integer.parseInt(xinJiPingJia);
				Integer weekInteger = weekQianDanLiang.equals("")?null:Integer.parseInt(weekQianDanLiang);
				Integer monthInteger = monthQianDanLiang.equals("")?null:Integer.parseInt(monthQianDanLiang);
				Integer yearInteger = yearQianDanLiang.equals("")?null:Integer.parseInt(yearQianDanLiang);
				Integer indexXyBj_Integer = indexXyBj.equals("")?null:Integer.parseInt(indexXyBj);
				Integer weiZhiInteger = indexWeiZhi.equals("")?null:Integer.parseInt(indexWeiZhi);
				
				WenAn wenAn = wenAnService.getWenAnByGuid(wenAnGuid);
				wenAn.setMonthQianDanLiang(monthInteger);
				wenAn.setName(name);
				wenAn.setIndexWeiZhi(weiZhiInteger);
				wenAn.setRenQi(renQiInteger);
				wenAn.setRuHangShiJian(ruHangShiJian);
				wenAn.setShanChangGuoJia(shanchangguojia);
				wenAn.setShanChangZuanYe(shanChangZhuanYe);
				wenAn.setShenFen(shengFeng);
				wenAn.setWeekQianDanLiang(weekInteger);
				wenAn.setXinJiPingJia(xinJiPingJiaInteger);
				wenAn.setXueYuanPinJia(xueYuanPingJia);
				wenAn.setYearQianDanLiang(yearInteger);
				wenAn.setZhiYeXinTiao(zhiYeXingTiao);
				wenAn.setZiXunXingShi(zixunxingshi);
				wenAn.setIndexXyBj(indexXyBj_Integer);
				wenAn.setIndexMiaoShu(indexMiaoShu);
				
				if (!photo.isEmpty()) {
					File f = new File(request.getSession().getServletContext().getRealPath("/")+
							Constants.wenAnLieBiaoImagePath+wenAn.getPhotoName());//原列表图片
					f.delete();//删除原列表图片
					
					String newImageName = SpercialCharUtil.suiJiImageName(photo.getOriginalFilename());// 生成随机图片名称，防止重复
					wenAn.setPhotoName(newImageName);
					photo.transferTo(new File(request.getSession().getServletContext().getRealPath("/")+
							Constants.wenAnLieBiaoImagePath+newImageName));
					
				}
				
				
				if(!indexPhoto.isEmpty()){
					File f = new File(request.getSession().getServletContext().getRealPath("/")+
							Constants.wenAnIndexImagePath+wenAn.getIndexPhotoName());
					f.delete();
					
					String newImageName2 = SpercialCharUtil.suiJiImageName(indexPhoto.getOriginalFilename());// 生成随机图片名称，防止重复
					wenAn.setIndexPhotoName(newImageName2);
					indexPhoto.transferTo(new File(request.getSession().getServletContext().getRealPath("/")+
							Constants.wenAnIndexImagePath+newImageName2));//保存首页图片图片
				}
				wenAnService.updateWenAn(wenAn);
				
				WenAnContent wenAnContent = wenAnContentService.findWenAnContentByWenAnGuid(wenAnGuid);
				wenAnContent.setBoKeUrl(boKeUrl);
				wenAnContent.setDianHua(dianhua);
				wenAnContent.setWenAnGuid(wenAn.getGuid());
				wenAnContent.setJieShao(jieshao);
				wenAnContent.setQQ(QQ);
				wenAnContent.setWeiXin(weiXin);
				if(!picture.isEmpty()){
					File f = new File(request.getSession().getServletContext().getRealPath("/")+
							Constants.wenAnContentImagePath+wenAnContent.getPictureName());
					f.delete();
					
					String newImageName3 = SpercialCharUtil.suiJiImageName(picture.getOriginalFilename());// 生成随机图片名称，防止重复
					wenAnContent.setPictureName(newImageName3);//设置内容图片名称
					picture.transferTo(new File(request.getSession().getServletContext().getRealPath("/")+
							Constants.wenAnContentImagePath+newImageName3));//保存内容图片图片
				}
				wenAnContentService.updateWenAnContent(wenAnContent);
				mav.addObject("msg", "修改成功");
				mav.addObject("wenAn", wenAn);
				mav.addObject("wenAnContent", wenAnContent);
				mav.setViewName("forward:/lxhAdmin/wenan/updateWenAn.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("errorMsg", "数据库访问出错");
			mav.setViewName("forward:/view/fail.jsp");
		}
		return mav;
	}
	
	@RequestMapping(value="/goUpdateWenAn")
	public ModelAndView goUpdateWenAn(@RequestParam("wenAnGuid")String wenAnGuid,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		AdminUser adminUser = (AdminUser)request.getSession().getAttribute("adminUserSession");
		try {
			if (adminUser==null) {
				mav.setViewName("redirect:/lxhAdmin/adminDengLu.jsp");
			}else{
				WenAn wenAn = wenAnService.getWenAnByGuid(wenAnGuid);
				WenAnContent wenAnContent = wenAnContentService.findWenAnContentByWenAnGuid(wenAnGuid);
				mav.addObject("wenAn", wenAn);
				mav.addObject("wenAnContent", wenAnContent);
				mav.setViewName("forward:/lxhAdmin/wenan/updateWenAn.jsp");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("errorMsg", "数据库访问出错");
			mav.setViewName("forward:/view/fail.jsp");
		}
		return mav;
	}
	
	//删除 包括内容
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(@RequestParam("wenAnGuid")String wenAnGuid,HttpServletRequest request){
		Map<String,String> map = new HashMap<String, String>();
		try {
			WenAn wenAn = wenAnService.getWenAnByGuid(wenAnGuid);
			WenAnContent wenAnContent = wenAnContentService.findWenAnContentByWenAnGuid(wenAnGuid);
			if (wenAn.getPhotoName()!=null) {
				File f = new File(request.getSession().getServletContext().getRealPath("/")+
						Constants.wenAnLieBiaoImagePath+wenAn.getPhotoName());//原列表图片
				f.delete();//删除原列表图片
			}
			if (wenAn.getIndexPhotoName()!=null) {
				File f2 = new File(request.getSession().getServletContext().getRealPath("/")+
						Constants.wenAnIndexImagePath+wenAn.getIndexPhotoName());
				f2.delete();//删除首页图片
			}
			wenAnService.deleteWenAn(wenAn);
			
			if (wenAnContent.getPictureName()!=null) {
				File f3 = new File(request.getSession().getServletContext().getRealPath("/")+
						Constants.wenAnContentImagePath+wenAnContent.getPictureName());
				f3.delete();//删除内容图片
			}
			wenAnContentService.deleteWenAnContent(wenAnContent);
			
			map.put("msg","success");//删除成功
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg","fail");//删除失败
		}
		return map;
	}
	
	
}
