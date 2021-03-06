package com.liuxue.dao;

import org.springframework.stereotype.Repository;

import com.liuxue.entity.WenAn;

/**
 * 文案的数据库操作库
 * @author 李成龙
 * @date 2015-06-05
 * 
 * */

@Repository
public class WenAnDao extends BaseDao<WenAn>{

	//分页
	//pageNo 页号,从1开始, int pageSize 每页记录数
	public Page page(int pageNo, int pageSize){
		String hql ="from WenAn m order by m.xinJiPingJia desc";
		return pagedQuery(hql, pageNo, pageSize);
	}
	
	//查出首页选用
	public List<WenAn> findIndexWenAn(){
		String hql = "from WenAn z where z.indexXyBj=1 order by z.indexWeiZhi";
		List<WenAn> list = (List<WenAn>)getHibernateTemplate().find(hql);
		return list;
	}
	
	
	
	
	
	
}
