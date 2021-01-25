package com.stock.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.stock.dao.StockDao;
import com.stock.model.Sector;
import com.stock.model.Stock;

@Repository
public class StockDaoImpl implements StockDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Sector> getStockUrls() {
		List<Sector> sectorList = new ArrayList<Sector>();	
		 
		String sql = "SELECT * FROM TBL_SECTOR WHERE ACTIVE = 'Y'";

	      jdbcTemplate.query(sql, new RowCallbackHandler() {
	    	 public void processRow(ResultSet rs) throws SQLException {
	    	 	if (rs != null) {
	    	 		Sector sector = new Sector();
	    	 		sector.setSectorId(rs.getInt("SECTOR_ID"));
	    	 		sector.setSectorName(rs.getString("SECTOR_NAME"));
	    	 		sector.setUrl(rs.getString("URL"));
	    	 		sectorList.add(sector);    	 		
	    	 	}
	    	 }
	      });
	     return sectorList;
	}

	@Override
	public void insertData(Stock stock) {
		
		String sqlQuery = "SELECT COUNT(*) FROM TBL_STOCK_DETAILS WHERE STOCK_NAME = '"+stock.getStockName()+"'";
		boolean result = false;
		int count = jdbcTemplate.queryForObject(sqlQuery, new Object[] { }, Integer.class);		    
		if (count > 0) {
		   result = true;
		}
		
		String sql = "";
		if(result == true) {
			sql = "UPDATE TBL_STOCK_DETAILS SET LAST_TRADED_PRICE = '"+stock.getLastTradedPrice()+"',VOLUME='"+stock.getVolume()+"',"
					+ "PERCENTAGE='"+stock.getPercentage()+"',UPDATED = CURRENT_TIMESTAMP,STOCK_URL = '"+stock.getStockUrl()+"' WHERE STOCK_NAME = '"+stock.getStockName()+"'";
					
		}else {		
			sql = "INSERT INTO TBL_STOCK_DETAILS (SECTOR_ID_FK,LAST_TRADED_PRICE,VOLUME,PERCENTAGE,UPDATED,STOCK_URL,STOCK_NAME) VALUES "
					+ "("+stock.getSectorId()+",'"+stock.getLastTradedPrice()+"','"+stock.getVolume()+"','"+stock.getPercentage()+"',CURRENT_TIMESTAMP,'"+stock.getStockUrl()+"','"+stock.getStockName()+"')";
		}
		jdbcTemplate.update(sql);
		
	}

	@Override
	public Set<Stock> getStockDetails() {
		Set<Stock> stockList = new LinkedHashSet<Stock>();	
		
		 String sql = "SELECT * FROM TBL_STOCK_DETAILS ORDER BY CAST(PERCENTAGE AS DECIMAL(10,5))";	     

	      jdbcTemplate.query(sql, new RowCallbackHandler() {
	    	 public void processRow(ResultSet rs) throws SQLException {
	    	 	if (rs != null) {
	    	 		Stock stock = new Stock();
	    	 		stock.setStockId(rs.getInt("STOCK_ID"));
	    	 		stock.setStockName(rs.getString("STOCK_NAME"));
	    	 		stock.setStockUrl(rs.getString("STOCK_URL"));
	    	 		stock.setLastTradedPrice(rs.getString("LAST_TRADED_PRICE"));
	    	 		stock.setAmountChange(rs.getString("PRICE_CHANGE"));
	    	 		stock.setPercentage(rs.getString("PERCENTAGE")); 
	    	 		stock.setVolume(rs.getString("VOLUME"));
	    	 		stock.setUpdated(rs.getString("UPDATED"));
	    	 		stockList.add(stock);
	    	 	}
	    	 }
	      });
	     return stockList;
	}
	

}
