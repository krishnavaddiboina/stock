package com.stock.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.stock.model.Stock;
import com.stock.service.StockService;


@Controller
public class StockController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	StockService stockService;

	
	ModelAndView modelAndView = new ModelAndView();
	
	@RequestMapping("/")	
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) throws IOException{		
		System.out.println("I am in controller home()");
		
		Set<Stock> stockList = stockService.getAllData();
		
		modelAndView.addObject("stockList",stockList);
		modelAndView.setViewName("home");
		return modelAndView;	   
	}
	
	/**
	 * to insert data in DB and fetch it 
	 * @throws IOException 
	 *
	 */
	/*@RequestMapping("/fetchData")	
	public void getData(HttpServletRequest request, HttpServletResponse response) throws IOException{		
		System.out.println("I am in controller getData()");
		
		Set<Stock> stockList = stockService.getAllData();
		
		String searchList = new Gson().toJson(stockList);
        response.getWriter().write(searchList);	
	   
	}*/
	
	/**
	 * to get data from DB
	 * @throws IOException 
	 *
	 */
	/*@RequestMapping("/getStocksData")	
	public void getStocksData(HttpServletRequest request, HttpServletResponse response) throws IOException{		
		System.out.println("I am in controller getStocksData()");
		
		Set<Stock> stockList = stockService.getStocksData();
		
		String searchList = new Gson().toJson(stockList);
        response.getWriter().write(searchList);		
	   
	}*/
	
	
	/**
	 * this method is to insert new sector data into tbl_sector table.
	 * @throws IOException 
	 *
	 */
	@RequestMapping("/insertSectorData")	
	public void insertSectorData() throws IOException{	
		String urls[] = {"https://www.moneycontrol.com/markets/indian-indices/top-sensex-companies-list/4?classic=true!!!!!SENSEX","https://www.moneycontrol.com/markets/indian-indices/top-bsesmallcap-companies-list/26?classic=true!!!!!S&amp;P BSE Smallcap","https://www.moneycontrol.com/markets/indian-indices/top-bsemidcap-companies-list/25?classic=true!!!!!S&amp;P BSE Midcap","https://www.moneycontrol.com/markets/indian-indices/top-bsesmallcapselectindex-companies-list/75?classic=true!!!!!S&amp;P BSE SmallCap Select Index","https://www.moneycontrol.com/markets/indian-indices/top-bsemidcapselectindex-companies-list/74?classic=true!!!!!S&amp;P BSE MidCap Select Index","https://www.moneycontrol.com/markets/indian-indices/top-bselargecap-companies-list/73?classic=true!!!!!S&amp;P BSE LargeCap","https://www.moneycontrol.com/markets/indian-indices/top-bseallcap-companies-list/67?classic=true!!!!!S&amp;P BSE AllCap","https://www.moneycontrol.com/markets/indian-indices/top-bse-100-companies-list/1?classic=true!!!!!S&amp;P BSE 100","https://www.moneycontrol.com/markets/indian-indices/top-bse-200-companies-list/2?classic=true!!!!!S&amp;P BSE 200","https://www.moneycontrol.com/markets/indian-indices/top-bse-500-companies-list/12?classic=true!!!!!S&amp;P BSE 500","https://www.moneycontrol.com/markets/indian-indices/top-bsebankex-companies-list/18?classic=true!!!!!S&amp;P BSE BANKEX","https://www.moneycontrol.com/markets/indian-indices/top-bseauto-companies-list/20?classic=true!!!!!S&amp;P BSE Auto","https://www.moneycontrol.com/markets/indian-indices/top-bsebasicmaterials-companies-list/68?classic=true!!!!!S&amp;P BSE Basic Materials","https://www.moneycontrol.com/markets/indian-indices/top-bsecapitalgoods-companies-list/13?classic=true!!!!!S&amp;P BSE Capital Goods","https://www.moneycontrol.com/markets/indian-indices/top-bseconsumerdiscretionarygoods&amp;services-companies-list/69?classic=true!!!!!S&amp;P BSE Consumer  Discretionary Goods &amp; Services","https://www.moneycontrol.com/markets/indian-indices/top-bseconsumerdurables-companies-list/16?classic=true!!!!!S&amp;P BSE Consumer Durables","https://www.moneycontrol.com/markets/indian-indices/top-bseenergy-companies-list/70?classic=true!!!!!S&amp;P BSE Energy","https://www.moneycontrol.com/markets/indian-indices/top-bsefinance-companies-list/71?classic=true!!!!!S&amp;P BSE Finance","https://www.moneycontrol.com/markets/indian-indices/top-bsefmcg-companies-list/14?classic=true!!!!!S&amp;P BSE FMCG","https://www.moneycontrol.com/markets/indian-indices/top-bsehealthcare-companies-list/15?classic=true!!!!!S&amp;P BSE Healthcare","https://www.moneycontrol.com/markets/indian-indices/top-bseindiamfg-companies-list/66?classic=true!!!!!S&amp;P BSE India Mfg","https://www.moneycontrol.com/markets/indian-indices/top-bseindustrials-companies-list/72?classic=true!!!!!S&amp;P BSE Industrials","https://www.moneycontrol.com/markets/indian-indices/top-bseipo-companies-list/33?classic=true!!!!!S&amp;P BSE IPO","https://www.moneycontrol.com/markets/indian-indices/top-bseit-companies-list/17?classic=true!!!!!S&amp;P BSE IT","https://www.moneycontrol.com/markets/indian-indices/top-bsemetals-companies-list/21?classic=true!!!!!S&amp;P BSE Metals","https://www.moneycontrol.com/markets/indian-indices/top-bseoilandgas-companies-list/22?classic=true!!!!!S&amp;P BSE Oil and Gas","https://www.moneycontrol.com/markets/indian-indices/top-bsepower-companies-list/30?classic=true!!!!!S&amp;P BSE Power","https://www.moneycontrol.com/markets/indian-indices/top-bsepsu-companies-list/11?classic=true!!!!!S&amp;P BSE PSU","https://www.moneycontrol.com/markets/indian-indices/top-bserealty-companies-list/29?classic=true!!!!!S&amp;P BSE Realty","https://www.moneycontrol.com/markets/indian-indices/top-bseteck-companies-list/10?classic=true!!!!!S&amp;P BSE TECk","https://www.moneycontrol.com/markets/indian-indices/top-bsetelecom-companies-list/76?classic=true!!!!!S&amp;P BSE Telecom","https://www.moneycontrol.com/markets/indian-indices/top-bseutilities-companies-list/77?classic=true!!!!!S&amp;P BSE Utilities","https://www.moneycontrol.com/markets/indian-indices/top-bsedividendstabilityindex-companies-list/94?classic=true!!!!!S&amp;P BSE Dividend Stability Index","https://www.moneycontrol.com/markets/indian-indices/top-bseenhancedvalueindex-companies-list/99?classic=true!!!!!S&amp;P BSE Enhanced Value Index","https://www.moneycontrol.com/markets/indian-indices/top-bselowvolatilityindex-companies-list/92?classic=true!!!!!S&amp;P BSE Low Volatility Index","https://www.moneycontrol.com/markets/indian-indices/top-bsemomentumindex-companies-list/91?classic=true!!!!!S&amp;P BSE Momentum Index","https://www.moneycontrol.com/markets/indian-indices/top-bsequalityindex-companies-list/93?classic=true!!!!!S&amp;P BSE Quality Index","https://www.moneycontrol.com/markets/indian-indices/top-bsesensex-50index-companies-list/100?classic=true!!!!!S&amp;P BSE SENSEX 50 Index","https://www.moneycontrol.com/markets/indian-indices/top-bse-150midcap-companies-list/103?classic=true!!!!!S&amp;P BSE 150 MidCap","https://www.moneycontrol.com/markets/indian-indices/top-bse-250smallcap-companies-list/104?classic=true!!!!!S&amp;P BSE 250 SmallCap","https://www.moneycontrol.com/markets/indian-indices/top-bse-250largemid-companies-list/105?classic=true!!!!!S&amp;P BSE 250 LargeMid","https://www.moneycontrol.com/markets/indian-indices/top-bse-400midsmall-companies-list/106?classic=true!!!!!S&amp;P BSE 400 MidSmall"};
		
		for(int i=0; i<urls.length;i++) {
			String url = urls[i];
			String[] data = url.trim().split("!!!!!");
			String sql = "INSERT INTO tbl_sector (URL,SECTOR_NAME,ACTIVE) VALUES ('"+data[0]+"','"+data[1]+"','Y')";
			jdbcTemplate.update(sql);
		}	
	}
	
}


