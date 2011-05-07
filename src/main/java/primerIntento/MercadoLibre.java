package primerIntento;

import java.io.IOException;

import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class MercadoLibre extends ServerResource{
	
	@Get
	public String represent(){
		
		String retorno = "";
		try {
			retorno = new ClientResource("https://api.mercadolibre.com/sites/MLA/search?q=ipod").get().getText();
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		retorno = "esto deberia devolver" + retorno;
		
		return retorno;
		
	
	}

}
