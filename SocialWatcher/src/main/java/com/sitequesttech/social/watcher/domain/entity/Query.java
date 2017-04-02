package com.sitequesttech.social.watcher.domain.entity;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name = "query")
public class Query {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="query_id")
	private Long id;
	
	@Column(nullable = false, unique = false, name="query_text")
	private String queryText;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="query")
    private List<QueryResult> queryResults = new ArrayList<QueryResult>(0);
	
	@Column(nullable = false, unique = false, name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(nullable = false, unique = false, name="created_by")
	private Long createdBy;
	
	/*@Column(name="socialmedia_id")
	private Long socialMediaId;*/
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "socialmedia_id")
	private SocialMedia socialMedia;
	
	@Transient
	private String[] selectedSocialMedia;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "client_id")
	private Client client;
	
	@Column(nullable = true, unique = false, name="modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(nullable = true, unique = false, name="modified_by")
	private Long modifiedBy;
	
	@Transient
	private String selectedClient;

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
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the queryText
	 */
	public String getQueryText() {
		return queryText;
	}

	/**
	 * @param queryText the queryText to set
	 */
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	/**
	 * @return the queryResults
	 */
	public List<QueryResult> getQueryResults() {
		return queryResults;
	}

	/**
	 * @param queryResults the queryResults to set
	 */
	public void setQueryResults(List<QueryResult> queryResults) {
		this.queryResults = queryResults;
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

	/*public Long getSocialMediaId() {
		return socialMediaId;
	}

	public void setSocialMediaId(Long socialMediaId) {
		this.socialMediaId = socialMediaId;
	}*/

	public SocialMedia getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}

	public String[] getSelectedSocialMedia() {
		return selectedSocialMedia;
	}

	public void setSelectedSocialMedia(String[] selectedSocialMedia) {
		this.selectedSocialMedia = selectedSocialMedia;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(String selectedClient) {
		this.selectedClient = selectedClient;
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
