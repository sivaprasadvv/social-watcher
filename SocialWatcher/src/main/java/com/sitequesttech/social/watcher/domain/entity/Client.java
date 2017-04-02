package com.sitequesttech.social.watcher.domain.entity;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * <p>
 * Client entity class
 * </p>
 */

@Entity
@Table(name = "client")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="client_id")
	private Long id;
	
	@Column(nullable = false, unique = false, name="client_name")
	private String clientName;

	@Column(nullable = true, unique = false, name="isEnabled")
	private Boolean isEnabled;	

	@Column(nullable = false, unique = false, name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(nullable = false, unique = false, name="created_by")
	private Long createdBy;
	
	@JoinTable(name = "client_user", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<User> users = new HashSet<User>();
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName="address_id", name = "address", nullable = false)
    private Address address = null;	
	
	@Column(nullable = true, unique = false, name="allowed_query_words_count")
	public Long allowedQueryWordsCount;
	
	@Column(name = "title", nullable = true, unique = false)
    private String title;
    
    @Column(name = "description", nullable = true, unique = false)
    private String description;
    
    @Column(nullable = true, unique = false, name="modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(referencedColumnName="login_id", name = "login")
	private Login login;
    
    @Column(nullable = true, unique = false, name="modified_by")
    private Long modifiedBy;
    
    @Column(nullable = true, unique = false, name="isAssigned")
	private Boolean isAssigned;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getClientName() {
		return clientName;
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
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

	public Set<User> getUsers() {
		return (users == null) ? users = new HashSet<User>() : users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getAllowedQueryWordsCount() {
		return allowedQueryWordsCount;
	}

	public void setAllowedQueryWordsCount(Long allowedQueryWordsCount) {
		this.allowedQueryWordsCount = allowedQueryWordsCount;
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
	
	public Boolean getIsAssigned() {
		return isAssigned;
	}

	public void setIsAssigned(Boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
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
