package ar.edu.utn.tacs2011c1.primerIntento;

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

public class CategoriesML extends Restlet {
	@Override  
    public void handle(Request request, Response response) {  
        // Print the user name of the requested orders  
		String categoria = (String) request.getAttributes().get("categoria");
		
		String message;

		message = cargarCategoriaJSON(categoria);
		
        response.setEntity(message, MediaType.TEXT_PLAIN /*MediaType.APPLICATION_JSON*/); 
}
	
	private String cargarCategoriaJSON(String categoria) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("https://api.mercadolibre.com/categories/" + categoria);

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
