package ar.edu.utn.tacs2011c1.primerIntento;

import java.io.IOException;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class MercadoLibreDeprecated extends ServerResource{
	
	@Get
	public String represent() throws IOException{
		
		ClientResource resource = new ClientResource("https://api.mercadolibre.com/items/MLA114618178");
		
		String jsonString = resource.get(MediaType.APPLICATION_JSON).getText();
		
		JSONObject itemJson = (JSONObject) JSONSerializer.toJSON(jsonString);
		
		String title = itemJson.getString("title");

		
		return title;
		
	
	}

}
