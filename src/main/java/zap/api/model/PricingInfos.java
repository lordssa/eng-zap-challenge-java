package zap.api.model;

public class PricingInfos {
	
	private String yearlyIptu;
	private String price;
	private String businessType;
	private String monthlyCondoFee;
	private String period;
	private String rentalTotalPrice;
	   
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getRentalTotalPrice() {
		return rentalTotalPrice;
	}
	public void setRentalTotalPrice(String rentalTotalPrice) {
		this.rentalTotalPrice = rentalTotalPrice;
	}
	public String getYearlyIptu() {
		return yearlyIptu;
	}
	public void setYearlyIptu(String yearlyIptu) {
		this.yearlyIptu = yearlyIptu;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getMonthlyCondoFee() {
		return monthlyCondoFee;
	}
	public void setMonthlyCondoFee(String monthlyCondoFee) {
		this.monthlyCondoFee = monthlyCondoFee;
	}

  
}
