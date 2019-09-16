package zap.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import zap.api.model.Building;
import zap.api.modelView.InventaryView;
import zap.api.repository.VivaRealRepository;


@Service
public class VivaRealService extends ApiService {
	
	@Autowired
	VivaRealRepository _vivaRealRepository;
	
	public InventaryView getListaImoveis(int pageNumber, int pageSize) throws NumberFormatException, Exception{
		InventaryView result = new InventaryView(pageNumber, pageSize);	
		List<Building> listaZap = _vivaRealRepository.getListaImoveis();
		List pages = getPages(listaZap, pageSize);
		if(pages.size() >= pageNumber) {
			result.setListings((List<Building>) pages.get(pageNumber-1));	
			result.setTotalCount(listaZap.size());
		}
				
		return result;		
	}

}
