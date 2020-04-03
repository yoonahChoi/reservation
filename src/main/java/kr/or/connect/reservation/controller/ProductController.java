package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping
	public Map<String, Object> list(@RequestParam(name="start", required=false, defaultValue="0") int start,
			@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId) {
		
		List<Product> list = null;
		
		
		if(categoryId == 0) {
			list = productService.getProducts(start);
		}else {
			list = productService.getProducts(start, categoryId);
		}
		
		int count = productService.getCount();
		
		Map<String, Object> map = new HashMap<>();
		map.put("items", list);
		map.put("totalCount", count);
		
		return map;
		
	}
}
