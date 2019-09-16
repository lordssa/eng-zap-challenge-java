package zap.api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import zap.api.model.Building;
import zap.api.model.Location;

@Repository
public class ZapRepository extends DataSource {

	static final double MIN_VALOR_VENDA = 600000;
	static final double DESCONTO_MIN_VALOR_VENDA = 0.9;
	static final double MIN_VALOR_METRO_QUADRADO = 3500;
	static final double MIN_VALOR_ALUGUEL = 3500;
	
		
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
		List<Building> listBuildingsZap = new ArrayList<Building>();

	
		double minValorVenda = MIN_VALOR_VENDA;
		for (Building b : getSource()) {
			if (b.getAddress() != null && b.getAddress().getGeoLocation() != null
					&& b.getAddress().getGeoLocation().getLocation() != null
					&& b.getAddress().getGeoLocation().getLocation().getLat() != 0
					&& b.getAddress().getGeoLocation().getLocation().getLon() != 0
					&& b.getPricingInfos() != null ) {
				
				//Quando for venda
				if(b.getPricingInfos().getBusinessType().equalsIgnoreCase("SALE")) {
					
					// Quando o imóvel estiver dentro do bounding box dos arredores do Grupo ZAP
					// O valor mínimo do imóvel sofre desconto.
					if(isInBoundedBoxZap(b.getAddress().getGeoLocation().getLocation())) {
						minValorVenda = minValorVenda*DESCONTO_MIN_VALOR_VENDA;	
					}
					//Quando for venda, valor do imóvel for maior ou igual ao valor mínimo
					//e o valor do metro quadrado for menor ou igual ao previamente estipulado.
					// it's Zap
					if(Double.parseDouble(b.getPricingInfos().getPrice()) >= minValorVenda && b.getUsableAreas() > 0
					   && Double.parseDouble(b.getPricingInfos().getPrice())/b.getUsableAreas() > MIN_VALOR_METRO_QUADRADO) {
					
						listBuildingsZap.add(b);
					}
					
				}else if(b.getPricingInfos().getBusinessType().equalsIgnoreCase("RENTAL")
						 && Double.parseDouble(b.getPricingInfos().getRentalTotalPrice()) >= MIN_VALOR_ALUGUEL) {
					//Quando for aluguel e no mínimo o valor for previamente estipulado.
					listBuildingsZap.add(b);
				}				
			}
		}
		return listBuildingsZap;

	}

}
