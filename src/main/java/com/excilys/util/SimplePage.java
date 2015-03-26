package com.excilys.util;

import java.util.ArrayList;
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
    private int totalPages;
    private int displayablePages;
    private int entitiesByPage;
    private int totalEntities;

    /**
     * @post getPage() == 1
     * getSize() == DEFAULT_SIZE getSort().equals(Sort.ASC)
     * getProperties().size() == 1
     * getProperties().contains(Page.DEFAULT_PROPERTY)
     */
    public SimplePage() {
        this.page = 1;
        this.size = DEFAULT_SIZE;
        this.sort = Sort.ASC;
        this.properties = new ArrayList<>();
        this.properties.add(DEFAULT_PROPERTY);
    }
    
    /**
     * @param property Column on which apply the sort
     * @post getPage() == 1
     * getSize() == DEFAULT_SIZE getSort().equals(Sort.ASC)
     * getProperties().size() == 1
     * getProperties().contains(property)
     */
    public SimplePage(String property) {
        this.page = 1;
        this.size = DEFAULT_SIZE;
        this.sort = Sort.ASC;
        this.properties = new ArrayList<>();
        this.properties.add(property);
    }

    /**
     * @param page Current page
     * @param size Number of entities
     * @post getPage() == page getSize() == size getSort().equals(Sort.ASC)
     * getProperties().size() == 1
     * getProperties().contains(Page.DEFAULT_PROPERTY)
     */
    public SimplePage(int page, int size) {
        if (page < 1 || size < 0) {
            throw new IllegalArgumentException();
        }
        this.page = page;
        this.size = size;
        this.sort = Sort.ASC;
        this.properties = new ArrayList<>();
        this.properties.add(DEFAULT_PROPERTY);
    }

    /**
     * @param page Current page
     * @param size Number of entities
     * @param sort Current sort
     * @post getPage() == page getSize() == size getSort().equals(sort)
     * getProperties().size() == 1
     * getProperties().contains(Page.DEFAULT_PROPERTY)
     */
    public SimplePage(int page, int size, Sort sort) {
        this(page, size);
        if (sort == null) {
            throw new IllegalArgumentException();
        }
        this.sort = sort;
    }

    /**
     * @param page       Current page
     * @param size       Number of entities
     * @param sort       Current sort
     * @param properties Properties for the sort
     * @post getPage() == page getSize() == size getSort().equals(sort)
     * getProperties().size() == properties.length
     * getProperties().containsAll(Arrays.asList(properties))
     */
    public SimplePage(int page, int size, Sort sort, String... properties) {
        this(page, size, sort);
        if (properties.length > 0) {
            this.properties = Arrays.asList(properties);
        }
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
	public int getTotalPages() {
		return totalPages;
	}

    @Override
    public int getOffset() {
        return (page - 1) * size;
    }
//	
//	@Override
//	public Page getPrevious() {
//		return page > 1 ? new SimplePage(page - 1, size, sort) : null;
//	}

    @Override
    public Sort getSort() {
        return sort;
    }
    
	@Override
	public int getDisplayablePages() {
		return displayablePages;
	}
	
    @Override
    public Page getFirst() {
        return new SimplePage(1, size, sort);
    }

    @Override
    public Page getNext() {
        return new SimplePage(page + 1, size, sort);
    }
	
    @Override
    public String getProperties() {
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
	public int getEntitiesByPage() {
		return entitiesByPage;
	}
	
	@Override
	public int getTotalEntities() {
		return totalEntities;
	}

	@Override
	public void setTotalEntities(int totalEntities) {
		if (totalEntities <= 0) {
			throw new IllegalArgumentException();
		}
		this.totalEntities = totalEntities;
	}

    @Override
    public void setSort(Sort sort) {
        if (sort == null) {
            throw new IllegalArgumentException();
        }
        this.sort = sort;
    }
    
    @Override
    public void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        this.size = size;
    }
    
	@Override
	public void setTotalPages(int pages) {
		if (pages <= 0) {
			throw new IllegalArgumentException();
		}
		this.totalPages = pages;
	}
	
	@Override
	public void setDisplayablePages(int displayablePages) {
		if (displayablePages <= 0) {
			throw new IllegalArgumentException();
		}
		this.displayablePages = displayablePages;
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
	public void setEntitiesByPage(int entitiesByPage) {
		if (entitiesByPage <= 0) {
			throw new IllegalArgumentException();
		}
		this.entitiesByPage = entitiesByPage;
	}
}
