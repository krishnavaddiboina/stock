package com.stock.model;

import org.springframework.stereotype.Component;

@Component
public class Sector {
	private int sectorId;
	private String sectorName;
	private String url;
	
	/**
	 * @return the sectorId
	 */
	public int getSectorId() {
		return sectorId;
	}
	/**
	 * @param sectorId the sectorId to set
	 */
	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}
	/**
	 * @return the sectorName
	 */
	public String getSectorName() {
		return sectorName;
	}
	/**
	 * @param sectorName the sectorName to set
	 */
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
		
}
