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

	/**
	 * @return Current page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @return Number entities by page
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * @return Offset for this page
	 */
	public int getOffset() {
		return (page - 1) * size;
	}

	/**
	 * @return Current sort
	 */
	public Sort getSort() {
		return sort;
	}
	
	/**
	 * @return Properties on which apply the sort
	 */
	public String getProperties() {
		final StringBuilder sb = new StringBuilder();
		sb.append(properties.get(0));
		for (int k = 1; k < properties.size(); ++k) {
			sb.append(", " + properties.get(k));
		}
		return sb.toString();
	}
	
	/**
	 * @pre page >= 1
	 * @param page Current page
	 */
	public void setPage(int page) {
		if (page < 1) {
			throw new IllegalArgumentException();
		}
		this.page = page;
	}

	/**
	 * @pre size >= 0
	 * @param size Number entities
	 */
	public void setSize(int size) {
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		this.size = size;
	}
	
	/**
	 * @pre sort != null
	 * @param sort Current sort
	 */
	public void setSort(Sort sort) {
		if (sort == null) {
			throw new IllegalArgumentException();
		}
		this.sort = sort;
	}
	
	/**
	 * @pre |properties| > 0
	 * @param properties Properties for the sort
	 */
	public void setProperties(String ...properties) {
		if (properties.length > 0) {
			this.properties = Arrays.asList(properties);
		}
	}
}
