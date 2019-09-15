package zap.api.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import zap.api.config.ApiProperties;
import zap.api.model.Building;

public abstract class DataSource {
	
	static final double MIN_LONGITUDE = -46.693419;
	static final double MIN_LATITUDE = -23.568704;
	static final double MAX_LONGITUDE = -46.641146;
	static final double MAX_LATITUDE = -23.546686;
	
	@Autowired
	private ApiProperties apiProp;
	
	protected String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	protected List<Building> getSource() throws Exception {

		String json;
		List<Building> listBuildings;
		try {
			json = readUrl(apiProp.getJsonUrl());
			//json = readUrl(
				//	"http://grupozap-code-challenge.s3-website-us-east-1.amazonaws.com/sources/source-sample.json");

			Gson gson = new Gson();

			Type collectionType = new TypeToken<List<Building>>() {
			}.getType();

			listBuildings = gson.fromJson(json, collectionType);

			return listBuildings;

		} catch (Exception e) {
			throw e;
		} finally {
			json = null;
			listBuildings = null;
		}
	}
	
}
