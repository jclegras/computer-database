package com.excilys.persistence.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @inv getPage() >= 1 && 1 <= getProperties().size()
 * getSize() >= 0 getTotalPages >= 0 getDisplayablePages >= 0 getSort() !=
 * null
 */
public interface Page {
    /**
     * Default property on which apply the sorting
     */
    String DEFAULT_PROPERTY = Field.COMPUTER_NAME.toString();
    /**
     * Default size
     */
    int DEFAULT_SIZE = EntitySizePerPage.TEN.size;

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
     * @pre EntitySizePerPage.isValidSize(size)
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
     * @param properties
     * @pre properties != null
     * !properties.isEmpty()
     */
    void setProperties(List<String> properties);

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

        private static final Set<String> VALUES;

        static {
            VALUES = new HashSet<>();
            for (Sort s : Sort.values()) {
                VALUES.add(s.toString());
            }
        }

        public static boolean isValid(String sort) {
            return VALUES.contains(sort);
        }
    }

    enum EntitySizePerPage {
        TEN(10), FIFTY(50), HUNDRED(100);

        private static final Set<Integer> SIZES;
        static {
            SIZES = new HashSet<>();
            for (EntitySizePerPage espp : EntitySizePerPage.values()) {
                SIZES.add(espp.getSize());
            }
        }
        private final int size;

        EntitySizePerPage(int size) {
            this.size = size;
        }

        public static boolean isValidSize(int size) {
            return SIZES.contains(size);
        }

        public int getSize() {
            return size;
        }
    }
}
