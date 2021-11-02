package com.neosoft.rest.util;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {

	@CreatedBy
	@Column(name = "created_by", length = 100)
	private U createdBy;

	@CreatedDate
	@DateTimeFormat(pattern = "MM.dd.yyyy HH:mm:ss")
	@Column(name = "created_date")
	private Date createdDate;

	@LastModifiedBy
	@Column(name = "last_modified_by", length = 100)
	private U lastModifiedBy;

	@LastModifiedDate
	@DateTimeFormat(pattern = "MM.dd.yyyy HH:mm:ss")
	@Column(name = "last_modified_date")
	private Date lastModifiedDate;
}
