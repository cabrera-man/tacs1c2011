package ar.edu.utn.tacs2011c1.test.cliente;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class TestCliente {

	@Test
	public void testGetApiML() throws ResourceException, IOException {
		ClientResource resource = new ClientResource(
				"https://api.mercadolibre.com/items/MLA114618178");
		String jsonString = resource.get(MediaType.APPLICATION_JSON).getText();

		JSONObject itemJson = (JSONObject) JSONSerializer.toJSON(jsonString);

		String title = itemJson.getString("title");

		assertTrue(title.equals(new String("Tambor Mapex Biack Panter")));

	}
}