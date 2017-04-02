package com.sitequesttech.social.watcher.domain.entity;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "query_result")
public class QueryResult {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="query_result_id")
	private Long id;
	
	@Column(nullable = true, unique = false, name="title")
	private String title;
	
	@Column(nullable = true, unique = false, name="url", columnDefinition="text")
	private String url;
	
	@Column(nullable = true, unique = false, name="description", columnDefinition="text")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "query_id", nullable = false)
	private Query query;
	
	@Column(nullable = true, unique = false, name="isReviewed")
	private Boolean isReviewed;
	
	@Column(nullable = true, unique = false, name="reviewed_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reviewedDate;
	
	@Column(nullable = true, unique = false, name="reviewed_by")
	private Long reviewedBy;
	
	@Column(nullable = false, unique = false, name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(nullable = false, unique = false, name="created_by")
	private Long createdBy;
	
	@Column(name="RANK")
	private Long rank;
	
	@Column(nullable = true, unique = false, name="profile_image_url")
	private String profileImageUrl;
	
	@Column(nullable = true, unique = false, name="comment", columnDefinition="text")
	private String comment;
	
	@Transient
	private String socialMediaName;
	
	@Column(nullable = true, unique = false, name="isDeleted")
	private String isDeleted;
	
	@Transient
	private String socialMediaLogo;
	
	@Column(nullable = false, unique = true, name="source_id")
	private String sourceId;
	
	@Column(nullable = false, unique = false, name="source_created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sourceCreatedDate;
	
	@Transient
	private String sourceCreatedAt;
	
	@Column(nullable = true, unique = false, name="place")
	private String place;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public Boolean getIsReviewed() {
		return isReviewed;
	}

	public void setIsReviewed(Boolean isReviewed) {
		this.isReviewed = isReviewed;
	}

	public Date getReviewedDate() {
		return reviewedDate;
	}

	public void setReviewedDate(Date reviewedDate) {
		this.reviewedDate = reviewedDate;
	}

	public Long getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(Long reviewedBy) {
		this.reviewedBy = reviewedBy;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSocialMediaName() {
		return socialMediaName;
	}

	public void setSocialMediaName(String socialMediaName) {
		this.socialMediaName = socialMediaName;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getSocialMediaLogo() {
		return socialMediaLogo;
	}

	public void setSocialMediaLogo(String socialMediaLogo) {
		this.socialMediaLogo = socialMediaLogo;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public Date getSourceCreatedDate() {
		return sourceCreatedDate;
	}

	public void setSourceCreatedDate(Date sourceCreatedDate) {
		this.sourceCreatedDate = sourceCreatedDate;
	}

	public String getSourceCreatedAt() {
		return sourceCreatedAt;
	}

	public void setSourceCreatedAt(String sourceCreatedAt) {
		this.sourceCreatedAt = sourceCreatedAt;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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
