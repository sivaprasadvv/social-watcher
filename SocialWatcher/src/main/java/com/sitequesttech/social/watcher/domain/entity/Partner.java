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
 * Partner entity class
 * </p>
 */

@Entity
@Table(name = "partner")
public class Partner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="partner_id")
	private Long id;
	
	@Column(nullable = false, unique = false, name="partner_name")
	private String partnerName;
	
	@Column(nullable = false, unique = false, name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(nullable = false, unique = false, name="created_by")
	private Long createdBy;
	 
	@JoinTable(name = "partner_client", joinColumns = @JoinColumn(name = "partner_id"), inverseJoinColumns = @JoinColumn(name = "client_id"))
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Client> clients = new HashSet<Client>();
	
	@Column(nullable = true, unique = false, name="modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(nullable = true, unique = false, name="modified_by")
	private Long modifiedBy;
	
	@Column(nullable = true, unique = false, name="allowed_clients_count")
	public Long allowedClientsCount;
	
	@Column(name = "title", nullable = true, unique = false)
    private String title;
    
    @Column(name = "description", nullable = true, unique = false)
    private String description;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName="address_id", name = "address", nullable = false)
    private Address address = null;
    
	@Column(nullable = true, unique = false, name="isEnabled")
	private Boolean isEnabled;	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(referencedColumnName="login_id", name = "login")
	private Login login;
    
    public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Set<Client> getClients() {
		return (clients == null) ? clients = new HashSet<Client>() : clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
	
	public Long getAllowedClientsCount() {
		return allowedClientsCount;
	}

	public void setAllowedClientsCount(Long allowedClientsCount) {
		this.allowedClientsCount = allowedClientsCount;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
