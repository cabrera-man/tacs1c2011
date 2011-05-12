package primerIntento;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class MercadoLibre extends ServerResource {
	
	@Get
	public String representacion() throws ClientProtocolException, IOException{
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("https://api.mercadolibre.com/items/MLA114618178");

        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = httpclient.execute(httpget, responseHandler);
        
        return responseBody;
        
		
	}
	

}
