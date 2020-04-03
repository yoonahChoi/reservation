package kr.or.connect.reservation.dto;

public class Promotion {
	private int id;
	private int productId;
	private String saveFileName;
	
	public int getId() {
		return id;
	}
	public int getProductId() {
		return productId;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	
	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + ", saveFileName=" + saveFileName + "]";
	}
	
}
