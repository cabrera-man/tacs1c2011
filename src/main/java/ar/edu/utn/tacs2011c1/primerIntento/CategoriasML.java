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

public class CategoriasML extends Restlet {
	
	@Override  
    public void handle(Request request, Response response) {  
        // Print the requested URI path  
        /*String message = "Account of user \""  
                + request.getAttributes().get("category") + "\"";  
        response.setEntity(message, MediaType.TEXT_PLAIN);*/
		String categoryID = (String) request.getAttributes().get("category");
		
		String bodyText = "";
		try {
			bodyText = this.getDataCategories(categoryID);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setEntity(bodyText, MediaType.TEXT_PLAIN);
    }
	
	private String getDataCategories(String categoria) throws ClientProtocolException, IOException{
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("https://api.mercadolibre.com/categories/" + categoria);

        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = httpclient.execute(httpget, responseHandler);
        
        return responseBody;
	}
}
