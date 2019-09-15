package zap.api.modelView;

import java.util.ArrayList;
import java.util.List;

import zap.api.model.Building;

public class InventaryView {
	
	private int pageNumber;
	private int pageSize;
	private int totalCount;
	private List<Building> listings;
	
		
	public InventaryView(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalCount = 0;
		
		this.listings = new ArrayList<>();
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<Building> getListings() {
		return listings;
	}
	public void setListings(List<Building> listings) {
		this.listings = listings;
	}
	
	


}
