package com.nysecasestudy.parsers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NYSEDataParser {
	private String stockTicker;
	private String tradeDate;
	private float openPrice;
	private float highPrice;
	private float lowPrice;
	private float closePrice;
	private long volume;
	public void parse(String record){
		String[] fields=record.split(",");
		this.stockTicker=fields[0];
		this.tradeDate=fields[1];
		this.openPrice=new Float(fields[2]);
		this.highPrice=new Float(fields[3]);
		this.lowPrice=new Float(fields[4]);
		this.closePrice=new Float(fields[5]);
		this.volume=new Long(fields[6]);
	}
	public String getTradeYearMonth(){
		SimpleDateFormat orgDate = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat tgtDate = new SimpleDateFormat("YYYY-MM");
		Date date=null;
		try{
			date=orgDate.parse(this.tradeDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		String tgtDateFormat=tgtDate.format(date);
		return tgtDateFormat;
	}
	public String getStockTicker() {
		return stockTicker;
	}
	public void setStockTicker(String stockTicker) {
		this.stockTicker = stockTicker;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public float getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(float openPrice) {
		this.openPrice = openPrice;
	}
	public float getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(float highPrice) {
		this.highPrice = highPrice;
	}
	public float getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(float lowPrice) {
		this.lowPrice = lowPrice;
	}
	public float getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(float closePrice) {
		this.closePrice = closePrice;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	

}
