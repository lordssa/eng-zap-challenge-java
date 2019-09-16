package zap.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import zap.api.config.ApiProperties;
import zap.api.modelView.InventaryView;
import zap.api.service.VivaRealService;
import zap.api.service.ZapService;

@RestController
@RequestMapping(value="/api")
@EnableConfigurationProperties(ApiProperties.class)
@Api(value="API Rest Imóveis")
@CrossOrigin(origins="*")
public class BuildingResource {
	
	@Autowired
	ZapService _zapService;
	
	@Autowired
	VivaRealService _vivaRealService;
	
	@GetMapping("/listarImoveisZap")
	@ApiOperation(value="Retorna a lista de imóveis do portal Zap")
	public InventaryView ListarImoveisZap(@RequestParam(value="pageNumber", defaultValue="1") String pageNumber,
										   @RequestParam(value="pageSize", defaultValue="20") String pageSize){
		try {
			return _zapService.getListaImoveis(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/listarImoveisVivaReal")
	@ApiOperation(value="Retorna a lista de imóveis do portal Viva Real")
	public InventaryView ListarImoveisVivaReal(@RequestParam(value="pageNumber", defaultValue="1") String pageNumber,
										   @RequestParam(value="pageSize", defaultValue="20") String pageSize){
		try {
			return _vivaRealService.getListaImoveis(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
		} catch (Exception e) {
			return null;
		}
	}

}
