package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Product;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	
	@Override
	@Transactional
	public List<Product> getProducts(Integer start) {
		List<Product> list = productDao.selectAll(start, ProductService.LIMIT);
		return list;
	}

	@Override
	public List<Product> getProducts(Integer start, Integer categoryId) {
		List<Product> list = productDao.selectByCategoryId(start, ProductService.LIMIT, categoryId);
		return list;
	}
	
	@Override
	@Transactional
	public int getCount() {
		int count = productDao.getCount();
		return count;
	}


}
