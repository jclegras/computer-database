package com.excilys.util;

import java.util.HashSet;
import java.util.Set;

/**
 * @inv getPage() >= 1 getProperties() != null && 1 <= getProperties().size()
 * getSize() >= 0 getTotalPages >= 0 getDisplayablePages >= 0 getSort() !=
 * null
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
     * @param page Current page
     * @pre page >= 1
     */
    void setPage(int page);

    /**
     * @return Number entities by page
     */
    int getSize();

    /**
     * @param size Number entities
     * @pre size >= 0
     */
    void setSize(int size);

    /**
     * @return Number of pages
     */
    long getTotalPages();

    /**
     * @param pages Number of pages
     * @pre pages >= 0
     */
    void setTotalPages(long pages);

    /**
     * @return Number of displayable pages
     */
    long getDisplayablePages();

    /**
     * @param displayablePages Number of pages
     * @pre displayablePages >= 0
     */
    void setDisplayablePages(long displayablePages);

    /**
     * @return Offset for this page
     */
    int getOffset();

    /**
     * @return Current sort
     */
    Sort getSort();

    /**
     * @param sort Current sort
     * @pre sort != null
     */
    void setSort(Sort sort);

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
     * @param properties Properties for the sort
     * @pre |properties| > 0
     */
    void setProperties(String... properties);

    /**
     * @return Number of entities by page
     */
    int getEntitiesByPage();

    /**
     * @param entitiesByPage Entities by page
     * @pre entitiesByPage >= 0
     */
    void setEntitiesByPage(int entitiesByPage);

    /**
     * @return Number of entities
     */
    long getTotalEntities();

    /**
     * @param totalEntities Total entities
     * @pre totalEntities >= 0
     */
    void setTotalEntities(long totalEntities);

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
