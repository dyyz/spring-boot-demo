package com.example.demo.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public long getQueryCount(StringBuilder sql, Map<String, Object> params) {
		Query query = em.createQuery("select count(u) " + sql.toString());
		setQueryParameter(query, params);
		Number number = (Number) query.getSingleResult();
		em.close();
		return number.longValue();
	}
	
	@Override
	public List getQueryResult(StringBuilder sql, Map<String, Object> params, 
			Pageable page) {
		Query query = em.createQuery("select u " + sql.toString());
		setQueryParameter(query, params);
		Integer pageSize = page.getPageSize();
        Integer pageNo = page.getPageNumber();
        // 实际查询返回分页对象
        int startIndex = pageSize * pageNo;
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		List list = query.getResultList();
		em.close();
		return list;
	}
	
	private void setQueryParameter(Query query, Map<String, Object> params) {
		for(Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

}
