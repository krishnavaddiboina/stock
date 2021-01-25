package com.stock.dao;

import java.util.List;
import java.util.Set;

import com.stock.model.Sector;
import com.stock.model.Stock;

public interface StockDao {

	List<Sector> getStockUrls();

	void insertData(Stock stock);

	Set<Stock> getStockDetails();	

}
