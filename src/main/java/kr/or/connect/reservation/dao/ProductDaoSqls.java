package kr.or.connect.reservation.dao;

public class ProductDaoSqls {
	public static final String SELECT_PRODUCT = "SELECT display_info_id, place_name, content AS product_content, description AS product_description, display_product.id AS product_id, save_file_name AS product_image_url "
			+ "FROM (SELECT display_info.id AS display_info_id, product.id, category_id, place_name, content, description "
			+ "FROM display_info JOIN product ON display_info.product_id = product.id) AS display_product "
			+ "JOIN (SELECT product_id AS id, save_file_name FROM product_image JOIN file_info ON product_image.file_id = file_info.id WHERE type='th') AS display_image "
			+ "ON display_product.id = display_image.id ORDER BY display_info_id limit :start, :limit;";
	public static final String SELECT_PRODUCT_BY_CATECORY_ID = "SELECT display_info_id, place_name, content AS product_content, description AS product_description, display_product.id AS product_id, save_file_name AS product_image_url "
			+ "FROM (SELECT display_info.id AS display_info_id, product.id, category_id, place_name, content, description "
			+ "FROM display_info JOIN product ON display_info.product_id = product.id) AS display_product "
			+ "JOIN (SELECT product_id AS id, save_file_name FROM product_image JOIN file_info ON product_image.file_id = file_info.id WHERE type='th') AS display_image "
			+ "ON display_product.id = display_image.id WHERE category_id = :categoryId "
			+ "ORDER BY display_info_id limit :start, :limit;";
	public static final String SELECT_COUNT = "SELECT count(*) FROM (SELECT display_info.id AS display_info_id, product.id, category_id, place_name, content, description "
			+ "FROM display_info JOIN product ON display_info.product_id = product.id) AS display_product "
			+ "JOIN (SELECT display_info_id AS id, save_file_name FROM display_info_image JOIN file_info ON display_info_image.display_info_id = file_info.id) AS display_image "
			+ "ON display_product.id = display_image.id;";
}
