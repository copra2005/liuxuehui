package com.liuxue.dao;

import org.springframework.stereotype.Repository;

import com.liuxue.entity.Student;

/**
 * 学生（成功案例）的数据库操作库
 * @author 李成龙
 * @date 2015-06-05
 * 
 * */

@Repository
public class StudentDao extends BaseDao<Student>{

	//分页
	//pageNo 页号,从1开始, int pageSize 每页记录数
	public Page page(int pageNo, int pageSize){
		String hql ="from Student m order by m.jinRuXueXiaoPingJi desc";
		return pagedQuery(hql, pageNo, pageSize);
	}
	
	//查出首页选用
	public List<Student> findIndexStudent(){
		String hql = "from Student z where z.indexXyBj=1 order by z.indexWeiZhi";
		List<Student> list = (List<Student>)getHibernateTemplate().find(hql);
		return list;
	}
	
	
	
}
