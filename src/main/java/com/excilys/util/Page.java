package com.excilys.util;


/**
 * @inv getPage() >= 1 1 <= getProperties().size() getSize() >= 0 getSort() !=
 *      null
 */
public interface Page {
	enum Sort {
		ASC, DESC
	}

	/**
	 * Default property on which apply the sorting
	 */
	String DEFAULT_PROPERTY = "ID";
	/**
	 * Default size
	 */
	int DEFAULT_SIZE = 10;
	
	boolean isPrevious();

	/**
	 * @return Current page
	 */
	int getPage();

	/**
	 * @return Number entities by page
	 */
	int getSize();

	/**
	 * @return Offset for this page
	 */
	int getOffset();

	/**
	 * @return Current sort
	 */
	Sort getSort();

	/**
	 * Returns the first {@link Page}.
	 * 
	 * @return first page
	 */
	Page getFirst();
	
//	/**
//	 * Returns the {@link Page} requesting the previous {@link Page}.
//	 *
//	 * @return previous page
//	 */
//	Page getPrevious();

	/**
	 * Returns the {@link Page} requesting the next {@link Page}.
	 *
	 * @return next page
	 */
	Page getNext();

	/**
	 * @return Properties on which apply the sort
	 */
	String getProperties();

	/**
	 * @pre page >= 1
	 * @param page
	 *            Current page
	 */
	void setPage(int page);

	/**
	 * @pre size >= 0
	 * @param size
	 *            Number entities
	 */
	void setSize(int size);

	/**
	 * @pre sort != null
	 * @param sort
	 *            Current sort
	 */
	void setSort(Sort sort);

	/**
	 * @pre |properties| > 0
	 * @param properties
	 *            Properties for the sort
	 */
	void setProperties(String... properties);
}
