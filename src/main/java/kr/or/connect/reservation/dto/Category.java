package kr.or.connect.reservation.dto;

public class Category {
	private int count;
	private int id;
	private String name;
	
	public int getCount() {
		return count;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Category [count=" + count + ", id=" + id + ", name=" + name + "]";
	}

}
