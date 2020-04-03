package kr.or.connect.reservation.dao;

public class MainPageDaoSqls {
	public static final String SELECT_PROMOTION = "SELECT promotion.id, promotion.product_id, file_info.save_file_name "
			+ "FROM promotion JOIN product_image ON promotion.product_id = product_image.product_id "
			+ "JOIN file_info ON product_image.file_id = file_info.id and type='th';";
	public static final String SELECT_CATEGORY = "SELECT id, name, count FROM category "
			+ "JOIN (SELECT category_id, count(*) as count FROM display_info JOIN product ON display_info.product_id = product.id GROUP BY category_id) AS counts "
			+ "ON category.id = counts.category_id";
}
