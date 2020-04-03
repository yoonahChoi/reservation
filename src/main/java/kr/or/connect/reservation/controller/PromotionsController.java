package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.PromotionService;

@RestController
@RequestMapping("/api/promotions")
public class PromotionsController {
	
	@Autowired
	PromotionService promotionService;
	
	@GetMapping
	public List<Promotion> list() {
		List<Promotion> list = promotionService.getPromotions();
		
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
