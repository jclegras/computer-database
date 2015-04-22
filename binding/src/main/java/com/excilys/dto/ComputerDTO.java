package com.excilys.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.excilys.validation.Date;

public class ComputerDTO {
    private long id;
    @NotNull(message = "{validation.notNull}")
    @Size(min = 1, max = 100)
    private String name;
    @Date
    private String introduced;
    @Date
    private String discontinued;
    private String companyId;
    private String companyName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {	
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
