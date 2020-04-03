package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public List<Category> list() {
		List<Category> list = categoryService.getCategorys();
		
		return list;
	}
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public String unauthorized() {
		return "Unauthorized";
	}
	
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public String forbidden() {
		return "Forbidden";
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String notFound() {
		return "Not Found";
	}
}
