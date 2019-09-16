package zap.api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import zap.api.model.Building;
import zap.api.model.Location;

@Repository
public class VivaRealRepository extends DataSource {

	static final double MAX_VALOR_VENDA = 700000;
	static final double ACRESCIMO_MAX_VALOR_ALUGUEL = 1.5;
	static final double MAX_VALOR_ALUGUEL = 4000;
			

	private boolean isInBoundedBoxZap(Location location) {
		
		if(location.getLat() >= MIN_LATITUDE 
		   && location.getLat() <= MAX_LATITUDE
		   && location.getLon() >= MIN_LONGITUDE 
		   && location.getLon() <= MAX_LONGITUDE) {
			return true;
		}
		
		return false;
	}


	public List<Building> getListaImoveis() throws Exception {
		List<Building> listBuildingsVivaReal = new ArrayList<Building>();
		
		double maxValorAluguel = MAX_VALOR_ALUGUEL;
		for (Building b : getSource()) {
			if (b.getAddress() != null && b.getAddress().getGeoLocation() != null
					&& b.getAddress().getGeoLocation().getLocation() != null
					&& b.getAddress().getGeoLocation().getLocation().getLat() != 0
					&& b.getAddress().getGeoLocation().getLocation().getLon() != 0
					&& b.getPricingInfos() != null ) {
				
				//Quando for venda
				if(b.getPricingInfos().getBusinessType().equalsIgnoreCase("SALE")) {
					
					//Quando for venda, valor do imóvel for menor ou igual ao valor máximo					
					// it's Viva Real
					if(Double.parseDouble(b.getPricingInfos().getPrice()) <= MAX_VALOR_VENDA) {					
						listBuildingsVivaReal.add(b);
					}
					
				}else if(b.getPricingInfos().getBusinessType().equalsIgnoreCase("RENTAL")
						 && b.getPricingInfos().getMonthlyCondoFee() != null 
						 && isNumeric(b.getPricingInfos().getMonthlyCondoFee())){
					
					//Quando o imóvel estiver dentro do bounding box dos arredores do Grupo ZAP
					//a regra de valor máximo (do aluguel do imóvel) SERÁ 50% maior.
					if(isInBoundedBoxZap(b.getAddress().getGeoLocation().getLocation())) {
						maxValorAluguel = maxValorAluguel*ACRESCIMO_MAX_VALOR_ALUGUEL;	
					}
					//O valor do condomínio não pode ser maior/igual que 30% do valor do aluguel
					if(Double.parseDouble(b.getPricingInfos().getRentalTotalPrice()) <= maxValorAluguel
						&& Double.parseDouble(b.getPricingInfos().getMonthlyCondoFee()) < Double.parseDouble(b.getPricingInfos().getRentalTotalPrice()) * 0.3) {
						listBuildingsVivaReal.add(b);
					}
					
				}				
		
			}
		}
		return listBuildingsVivaReal;

	}
   
	public static boolean isNumeric(String strNum) {
	    return strNum.matches("-?\\d+(\\.\\d+)?");
	}

}
