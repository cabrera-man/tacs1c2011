package ar.edu.utn.tacs2011c1.servicioML;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;

public class SitesML extends Restlet{
	@Override
	public void handle(Request request, Response response){
		String sitio = (String) request.getAttributes().get("sitio");
		
		String mensaje = "";
		
		mensaje = cargarSitioJSON(sitio);
		
		response.setEntity(mensaje, MediaType.TEXT_PLAIN);
	}

	private String cargarSitioJSON(String sitio) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("https://api.mercadolibre.com/sites/" + sitio);

        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody;
		
        //La firma del metodo que llama a este me obliga a usar exception de Runtime
        try {
			responseBody = httpclient.execute(httpget, responseHandler);
		} catch (ClientProtocolException e) {
			throw new RuntimeException("ClientProtocolException");
		} catch (IOException e) {
			throw new RuntimeException("IOException");
		}
        
        return responseBody;
	}
	

}
