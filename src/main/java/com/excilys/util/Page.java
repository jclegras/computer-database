package com.excilys.util;

import java.util.HashSet;
import java.util.Set;

/**
 * @inv getPage() >= 1 getProperties() != null && 1 <= getProperties().size()
 *      getSize() >= 0 getTotalPages >= 0 getDisplayablePages >= 0 getSort() !=
 *      null
 */
public interface Page {
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
	 * @return Number of pages
	 */
	int getTotalPages();

	/**
	 * @return Number of displayable pages
	 */
	int getDisplayablePages();

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
	 * @return Number of entities by page
	 */
	int getEntitiesByPage();

	/**
	 * @return Number of entities
	 */
	int getTotalEntities();

	/**
	 * @param page
	 *            Current page
	 * @pre page >= 1
	 */
	void setPage(int page);

	/**
	 * @param size
	 *            Number entities
	 * @pre size >= 0
	 */
	void setSize(int size);

	/**
	 * @param pages
	 *            Number of pages
	 * @pre pages >= 0
	 */
	void setTotalPages(int pages);

	/**
	 * @pre displayablePages >= 0
	 * @param displayablePages
	 *            Number of pages
	 */
	void setDisplayablePages(int displayablePages);

	/**
	 * @param sort
	 *            Current sort
	 * @pre sort != null
	 */
	void setSort(Sort sort);

	/**
	 * @param properties
	 *            Properties for the sort
	 * @pre |properties| > 0
	 */
	void setProperties(String... properties);

	/**
	 * @pre entitiesByPage >= 0
	 * @param entitiesByPage
	 *            Entities by page
	 */
	void setEntitiesByPage(int entitiesByPage);

	/**
	 * @pre totalEntities >= 0
	 * @param totalEntities
	 *            Total entities
	 */
	void setTotalEntities(int totalEntities);

	enum Sort {
		ASC, DESC;

		private static final Set<String> values;
		static {
			values = new HashSet<>();
			for (Sort s : Sort.values()) {
				values.add(s.toString());
			}
		}

		public static boolean isValid(String sort) {
			return values.contains(sort);
		}
	}
}
