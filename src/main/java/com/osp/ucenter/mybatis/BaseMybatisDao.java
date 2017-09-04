package com.osp.ucenter.mybatis;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.osp.ucenter.common.utils.LoggerUtils;
import com.osp.ucenter.mybatis.page.Pagination;

/**
 * 基础MybatisDao，与分页有关的服务都走这个类
 * 
 * @author zhangmingcheng
 */
@SuppressWarnings({ "unchecked" })
public class BaseMybatisDao<T> extends SqlSessionDaoSupport {

	private String NAMESPACE;
	final static Class<? extends Object> SELF = BaseMybatisDao.class;
	protected final Log logger = LogFactory.getLog(BaseMybatisDao.class);
	/** 默认的查询Sql id */
	final static String DEFAULT_SQL_ID = "findAll";
	/** 默认的查询Count sql id **/
	final static String DEFAULT_COUNT_SQL_ID = "findCount";

	/**
	 * Autowired 必须要有 ，mybatis-spring-1.3.0中取消了自动注入SqlSessionFactory 和
	 * SqlSessionTemplate.
	 */
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	public BaseMybatisDao() {
		try {
			// getGenericSuperclass()获得带有泛型的父类
			// 返回值Type：Type是 Java
			// 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
			Object genericClz = getClass().getGenericSuperclass();
			if (genericClz instanceof ParameterizedType) {
				Class<T> entityClass = (Class<T>) ((ParameterizedType) genericClz).getActualTypeArguments()[0];
				// 如 NAMESPACE=com.sojson.common.dao.UcUserMapper
				NAMESPACE = entityClass.getPackage().getName() + "." + entityClass.getSimpleName();
			}
		} catch (RuntimeException e) {
			LoggerUtils.error(SELF, "初始化失败，继承BaseMybatisDao，没有泛型！");
		}
	}
	
	/**
	 * 重载减少参数DEFAULT_SQL_ID="findAll"  DEFAULT_COUNT_SQL_ID="findCount"
	 * 
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Pagination findPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
		return findPage(DEFAULT_SQL_ID, DEFAULT_COUNT_SQL_ID, params, pageNo, pageSize);
	}


	/**
	 * 分页
	 * 
	 * @param sqlId
	 *            主语句
	 * @param countId
	 *            Count 语句
	 * @param params
	 *            参数
	 * @param pageNo
	 *            第几页
	 * @param pageSize每页显示多少条
	 * @param requiredType
	 *            返回的类型[可以不传参]
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Pagination findPage(String sqlId, String countId, Map<String, Object> params, Integer pageNo,
			Integer pageSize) {
		pageNo = null == pageNo ? 1 : pageNo;
		pageSize = null == pageSize ? 10 : pageSize;
		Pagination page = new Pagination();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		int offset = (page.getPageNo() - 1) * page.getPageSize();
		String page_sql = String.format(" limit  %s , %s ", offset, pageSize);
		params.put("page_sql", page_sql);
		// 如com.sojson.common.dao.UUserMapper.findAll
		sqlId = String.format("%s.%s", NAMESPACE, sqlId);
		countId = String.format("%s.%s", NAMESPACE, countId);
		try {
			SqlSession sqlSession = this.getSqlSession();
			List resultList = sqlSession.selectList(sqlId, params);
			int totalCount = sqlSession.selectOne(countId);
			page.setList(resultList);
			page.setTotalCount(totalCount);
		} catch (Exception e) {
			LoggerUtils.error(SELF, "jdbc.error.code.findByPageBySqlId", e);
		}
		return page;

	}
}
