package zap.api.service;


import java.util.ArrayList;
import java.util.List;

import zap.api.model.Building;

public abstract class ApiService {
	
	protected <T> List<List<Building>> getPages(List<Building> list2, int pageSize) {	   
	    List<Building> list = new ArrayList<Building>(list2);
	    if (pageSize <= 0 || pageSize > list.size())
	        pageSize = list.size();
	    int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
	    List<List<Building>> pages = new ArrayList<List<Building>>(numPages);
	    for (int pageNum = 0; pageNum < numPages;)
	        pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
	    return pages;
	}

}
