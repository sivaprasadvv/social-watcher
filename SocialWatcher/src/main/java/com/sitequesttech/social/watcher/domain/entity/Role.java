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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>
 * Role entity class
 * </p>
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;
    
    @Column(name = "title", nullable = true, unique = false)
    private String title;
    
    @Column(name = "description", nullable = true, unique = false)
    private String description;
    
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

    public Role() {
    }

    public Role(final String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
	public final Date getCreatedDate() {
		return createdDate;
	}

	public final void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public final Long getCreatedBy() {
		return createdBy;
	}

	public final void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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
