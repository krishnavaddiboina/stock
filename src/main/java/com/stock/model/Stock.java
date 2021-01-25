package com.stock.model;

import org.springframework.stereotype.Component;

@Component
public class Stock {
	private int stockId;
	private int sectorId;
	private String stockName;
	private String lastTradedPrice;
	private String amountChange;
	private String percentage;
	private String stockUrl;
	private String volume;
	private String updated;
	
	/**
	 * @return the stockId
	 */
	public int getStockId() {
		return stockId;
	}
	/**
	 * @param stockId the stockId to set
	 */
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
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
	 * @return the lastTradedPrice
	 */
	public String getLastTradedPrice() {
		return lastTradedPrice;
	}
	/**
	 * @param lastTradedPrice the lastTradedPrice to set
	 */
	public void setLastTradedPrice(String lastTradedPrice) {
		this.lastTradedPrice = lastTradedPrice;
	}
	/**
	 * @return the amountChange
	 */
	public String getAmountChange() {
		return amountChange;
	}
	/**
	 * @param amountChange the amountChange to set
	 */
	public void setAmountChange(String amountChange) {
		this.amountChange = amountChange;
	}
	/**
	 * @return the percentage
	 */
	public String getPercentage() {
		return percentage;
	}
	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	/**
	 * @return the stockUrl
	 */
	public String getStockUrl() {
		return stockUrl;
	}
	/**
	 * @param stockUrl the stockUrl to set
	 */
	public void setStockUrl(String stockUrl) {
		this.stockUrl = stockUrl;
	}
	/**
	 * @return the stockName
	 */
	public String getStockName() {
		return stockName;
	}
	/**
	 * @param stockName the stockName to set
	 */
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	/**
	 * @return the volume
	 */
	public String getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}
	/**
	 * @return the updated
	 */
	public String getUpdated() {
		return updated;
	}
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	
	
}
