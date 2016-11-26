package com.gzzhwl.core.mybatis.support;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Lists;
import com.gzzhwl.core.exception.ModelValidException;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.page.PageContainer;

public class SqlSessionDaoSupport implements DaoSupport {
	private SqlSessionTemplate sqlSession;
	private Validator validator;

	public SqlSessionDaoSupport(SqlSessionTemplate sqlSession, Validator validator) {
		this.sqlSession = sqlSession;
		this.validator = validator;
	}

	@Override
	public <E> int insert(String statement, E parameter) {
		Set<ConstraintViolation<E>> paramError = validator.validate(parameter);
		if (paramError.isEmpty()) {
			return sqlSession.insert(statement, parameter);
		} else {
			Iterator<ConstraintViolation<E>> _iError = paramError.iterator();
			List<String> errorMessage = Lists.newArrayList();
			while (_iError.hasNext()) {
				ConstraintViolation<E> error = _iError.next();
				errorMessage.add(error.getMessage());
			}
			throw new ModelValidException(errorMessage);
		}
	}

	@Override
	public <E> int update(String statement, E parameter) {
		return sqlSession.update(statement, parameter);
	}

	@Override
	public <K, V, T> T get(String statement, Map<K, V> parameter) {
		return sqlSession.selectOne(statement, parameter);
	}

	@Override
	public <K, V> Map<K, V> findOne(String statement, Map<K, V> parameter) {
		return sqlSession.selectOne(statement, parameter);
	}

	@Override
	public <K, V> int delete(String statement, Map<K, V> parameter) {
		return sqlSession.delete(statement, parameter);
	}

	@Override
	public <E, K, V> List<E> find(String statement, Map<K, V> parameter) {
		return sqlSession.selectList(statement, parameter);
	}

	@Override
	public <E> List<E> find(String statement) {
		return sqlSession.selectList(statement);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, K, V> Page<E> page(String pageStatement, Map<K, V> parameter, int current, int pagesize) {
		PageBounds pageBounds = new PageBounds(current, pagesize);
		PageList<E> result = (PageList<E>) sqlSession.selectList(pageStatement, parameter, pageBounds);
		Paginator paginator = result.getPaginator();
		return new PageContainer<E>(paginator.getTotalCount(), paginator.getPage(), paginator.getTotalPages(),
				paginator.getLimit(), paginator.isHasNextPage(), paginator.isHasPrePage(), result);
	}

	@Override
	public Connection getConnection() {
		return sqlSession.getConnection();
	}

	@Override
	public Configuration getConfiguration() {
		return sqlSession.getConfiguration();
	}

	@Override
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSession;
	}

}
