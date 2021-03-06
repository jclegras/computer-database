package com.excilys.model;

import com.excilys.model.converter.LocalDateTimePersistenceConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "computer")
public class Computer {
	@Id
	@GeneratedValue
    private long id;
    private String name;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime introduced;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime discontinued;
    @OneToOne
    private Company company;

    public static Builder builder() {
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getIntroduced() {
        return introduced;
    }

    public LocalDateTime getDiscontinued() {
        return discontinued;
    }

    public Company getCompany() {
        return company;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduced(LocalDateTime introduced) {
        this.introduced = introduced;
    }

    public void setDiscontinued(LocalDateTime discontinued) {
        this.discontinued = discontinued;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result
                + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result
                + ((introduced == null) ? 0 : introduced.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Computer other = (Computer) obj;
        if (company == null) {
            if (other.company != null)
                return false;
        } else if (!company.equals(other.company))
            return false;
        if (discontinued == null) {
            if (other.discontinued != null)
                return false;
        } else if (!discontinued.equals(other.discontinued))
            return false;
        if (id != other.id)
            return false;
        if (introduced == null) {
            if (other.introduced != null)
                return false;
        } else if (!introduced.equals(other.introduced))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Computer [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", introduced=");
        builder.append(introduced);
        builder.append(", discontinued=");
        builder.append(discontinued);
        builder.append(", company=");
        builder.append(company);
        builder.append("]");
        return builder.toString();
    }

    public static class Builder {
        private final Computer c;

        private Builder() {
            c = new Computer();
        }

        public Builder id(long id) {
            c.setId(id);
            return this;
        }

        public Builder name(String name) {
            c.setName(name);
            return this;
        }

        public Builder introduced(LocalDateTime introduced) {
            c.setIntroduced(introduced);
            return this;
        }

        public Builder discontinued(LocalDateTime discontinued) {
            c.setDiscontinued(discontinued);
            return this;
        }

        public Builder company(Company company) {
            c.setCompany(company);
            return this;
        }

        public Computer build() {
            return c;
        }
    }

}
