package com.sitequesttech.social.watcher.domain.entity;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="address_id")
	private Long id = null;
	
	@Column(nullable = true, unique = false, name="street1")
    private String street1 = null;
	
	@Column(nullable = true, unique = false, name="street2")
    private String street2 = null;
	
	@Column(nullable = true, unique = false, name="city")
    private String city = null;
	
	@Column(nullable = true, unique = false, name="state")
    private String state = null;
	
	@Column(nullable = true, unique = false, name="zip_postal")
    private String zipPostal = null;
	
	@Column(nullable = true, unique = false, name="country")
    private String country = null;
	
	@Column(nullable = false, unique = false, name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(nullable = false, unique = false, name="created_by")
	private Long createdBy;
	
	@Column(nullable = true, unique = false, name="modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(nullable = true, unique = false, name="modified_by")
	private Long modifiedBy;

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

    /**
     * Gets id (primary key).
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id (primary key).
     */
    public void setId(Long id) {
        this.id = id;
    }    

    /**
	 * @return the street1
	 */
	public String getStreet1() {
		return street1;
	}

	/**
	 * @param street1 the street1 to set
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	/**
	 * @return the street2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * @param street2 the street2 to set
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
     * Gets city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets state.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets zip or postal code.
     */
    public String getZipPostal() {
        return zipPostal;
    }

    /**
     * Sets zip or postal code.
     */
    public void setZipPostal(String zipPostal) {
        this.zipPostal = zipPostal;
    }

    /**
     * Gets country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdBy
	 */
	public Long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return reflectionToString(this);
	}

}
