package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ProductDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Product;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Product> selectAll(Integer start, Integer limit){
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PRODUCT, params, rowMapper);
	}
	
	public List<Product> selectByCategoryId(Integer start, Integer limit, Integer categoryId){
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("categoryId", categoryId);
		return jdbc.query(SELECT_PRODUCT_BY_CATECORY_ID, params, rowMapper);
	}
	
	public int getCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
}
