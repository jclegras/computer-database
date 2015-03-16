package com.excilys.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Page {
	public enum Sort {
		ASC, DESC;
	}
	
	private static final String DEFAULT_PROPERTY = "ID";
	private Sort sort;
	private List<String> properties;
	private int page;
	private int size;
	
	public Page(int page, int size) {
		if (page < 1 || size < 0) {
			throw new IllegalArgumentException();
		}
		this.page = page;
		this.size = size;
		this.sort = Sort.ASC;
		this.properties = new ArrayList<>();
		this.properties.add(DEFAULT_PROPERTY);
	}
	
	public Page(int page, int size, Sort sort) {
		this(page, size);
		if (sort == null) {
			throw new IllegalArgumentException();
		}
		this.sort = sort;
	}
	
	public Page(int page, int size, Sort sort, String ...properties) {
		this(page, size, sort);
		if (properties.length > 0) {
			this.properties = Arrays.asList(properties);
		}
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}
	
	public int getOffset() {
		return (page - 1) * size;
	}

	public Sort getSort() {
		return sort;
	}
	
	public String getProperties() {
		final StringBuilder sb = new StringBuilder();
		sb.append(properties.get(0));
		for (int k = 1; k < properties.size(); ++k) {
			sb.append(", " + properties.get(k));
		}
		return sb.toString();
	}
	
	public void setPage(int page) {
		if (page < 1) {
			throw new IllegalArgumentException();
		}
		this.page = page;
	}

	public void setSize(int size) {
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		this.size = size;
	}
	
	public void setSort(Sort sort) {
		if (sort == null) {
			throw new IllegalArgumentException();
		}
		this.sort = sort;
	}
	
	public void setProperties(String ...properties) {
		if (properties.length > 0) {
			this.properties = Arrays.asList(properties);
		}
	}
}
