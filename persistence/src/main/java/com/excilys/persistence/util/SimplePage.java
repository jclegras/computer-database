package com.excilys.persistence.util;

import java.util.Arrays;
import java.util.List;

/**
 * A simple page without information about entities.
 */
public class SimplePage implements Page {
    private Sort sort;
    private List<String> properties;
    private int page;
    private int size;
    private String textualProperties;
    private long totalPages;
    private long displayablePages;
    private int entitiesByPage;
    private long totalEntities;

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean isPrevious() {
        return page > 1;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void setPage(int page) {
        if (page < 1) {
            throw new IllegalArgumentException();
        }
        this.page = page;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        if (!EntitySizePerPage.isValidSize(size)) {
            throw new IllegalArgumentException();
        }
        this.size = size;
    }

    @Override
    public long getTotalPages() {
        return totalPages;
    }

    @Override
    public void setTotalPages(long pages) {
        if (pages < 0L) {
            throw new IllegalArgumentException();
        }
        this.totalPages = pages;
    }

    @Override
    public int getOffset() {
        return (page - 1) * size;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public void setSort(Sort sort) {
        if (sort == null) {
            throw new IllegalArgumentException();
        }
        this.sort = sort;
    }

    @Override
    public long getDisplayablePages() {
        return displayablePages;
    }

    @Override
    public void setDisplayablePages(long displayablePages) {
        if (displayablePages < 0L) {
            throw new IllegalArgumentException();
        }
        this.displayablePages = displayablePages;
    }

    @Override
    public Page getFirst() {
        return builder().size(size).sort(sort).build();
    }

    @Override
    public Page getNext() {
        return builder().page(page + 1).size(size).sort(sort).build();
    }

    @Override
    public String getProperties() {
        if (properties == null) {
            return null;
        }
        if (textualProperties == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(properties.get(0));
            for (int k = 1; k < properties.size(); ++k) {
                sb.append(", ").append(properties.get(k));
            }
            textualProperties = sb.toString();
        }
        return textualProperties;
    }

    @Override
    public void setProperties(String... properties) {
        if (properties.length == 0) {
            throw new IllegalArgumentException();
        }
        if (properties.length > 0) {
            this.properties = Arrays.asList(properties);
        }
    }

    @Override
    public void setProperties(List<String> properties) {
        if (properties == null || properties.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.properties = properties;
    }

    @Override
    public long getTotalEntities() {
        return totalEntities;
    }

    @Override
    public void setTotalEntities(long totalEntities) {
        if (totalEntities < 0L) {
            throw new IllegalArgumentException();
        }
        this.totalEntities = totalEntities;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + page;
        result = prime * result + size;
        result = prime * result + ((sort == null) ? 0 : sort.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SimplePage other = (SimplePage) obj;
        if (page != other.page)
            return false;
        if (size != other.size)
            return false;
        if (sort != other.sort)
            return false;
        return true;
    }

    public static class Builder {
        private Page page;

        private Builder() {
            this.page = new SimplePage();
        }

        public Builder page(int page) {
            this.page.setPage(page);
            return this;
        }

        public Builder size(int size) {
            page.setSize(size);
            return this;
        }

        public Builder totalPages(long totalPages) {
            page.setTotalPages(totalPages);
            return this;
        }

        public Builder displayablePages(long displayablePages) {
            page.setDisplayablePages(displayablePages);
            return this;
        }

        public Builder sort(Sort sort) {
            page.setSort(sort);
            return this;
        }

        public Builder properties(String... properties) {
            page.setProperties(properties);
            return this;
        }

        public Builder totalEntities(long totalEntities) {
            page.setTotalEntities(totalEntities);
            return this;
        }

        public Page build() {
            if (page.getPage() == 0) {
                page.setPage(1);
            }
            if (page.getSize() == 0) {
                page.setSize(DEFAULT_SIZE);
            }
            if (page.getSort() == null) {
                page.setSort(Sort.ASC);
            }
            if (page.getProperties() == null) {
                page.setProperties(Arrays.asList(DEFAULT_PROPERTY));
            }
            return page;
        }
    }
}
