package com.stock.service.impl;

import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dao.StockDao;
import com.stock.model.Sector;
import com.stock.model.Stock;
import com.stock.service.StockService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockDao stockDao;
	@Autowired
	Stock stock;

	@Override
	public Set<Stock> getAllData() {
		
        	long start = System.nanoTime();
        	List<Sector> sectorList = stockDao.getStockUrls();
        	for(int i=0; i < sectorList.size(); i++) {
        			
        		
	        		try {
		        		
	        			Document document = Jsoup.connect(sectorList.get(i).getUrl())
	        					.userAgent("Mozilla/5.0 (Windows NT 6.1)")
	        					.ignoreContentType(true)	        					
	        					.get(); 
	                
	        			Element table = document.select("table").get(1); //select the first table.
	        			Elements rows = table.select("tr");

	        			for (int j = 1; j < rows.size(); j++) { //first row is the col names so skip it.
	        			    Element row = rows.get(j);
	        			    Elements cols = row.select("td"); 	
	        			    
	        			    stock.setLastTradedPrice(cols.get(1).text());  //current price	                        
	                        stock.setPercentage(cols.get(2).text());  //percentage change
	                        stock.setVolume(cols.get(3).text());
	                        stock.setSectorId(sectorList.get(i).getSectorId());
	                        
	                        Elements links = cols.select("a[href]");
		       		         for (Element link : links) {
		       		        	stock.setStockUrl(link.attr("href"));
		       		        	stock.setStockName(link.text().replace("'", ""));	
		       		        	System.out.println("Company name = "+stock.getStockName());
		       		         }
	                        
	                        stockDao.insertData(stock);
	        			}
	               
	        	} catch (Exception e) {
                    System.out.println("Error occucured while reading from sectorId = "+ sectorList.get(i).getSectorId()+", stock name = "+stock.getStockName()+", exception ="+e);
                }
        		
        	}
        	
        	long end = System.nanoTime();
        	long timeDiff = end-start;
        	System.out.println("timeDiff = "+timeDiff);
        
        	return stockDao.getStockDetails();      
       
	}

	@Override
	public Set<Stock> getStocksData() {
		return stockDao.getStockDetails();   
	}	
	
}
