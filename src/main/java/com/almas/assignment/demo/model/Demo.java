package com.almas.assignment.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEMO")
public class Demo {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String fullUrl;
	
	@Column
	private String shortUrl;
	
	@Column
	private String description;

	public Demo() {
		super();
	}

	public Demo(Long id, String fullUrl, String shortUrl, String description) {
		super();
		this.id = id;
		this.fullUrl = fullUrl;
		this.shortUrl = shortUrl;
		this.description = description;
	}

	public Demo(String fullUrl, String shortUrl, String description) {
		super();
		this.fullUrl = fullUrl;
		this.shortUrl = shortUrl;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format("Demo [id=%s, fullUrl=%s, shortUrl=%s, description=%s]", id, fullUrl, shortUrl, description);
	}
}
