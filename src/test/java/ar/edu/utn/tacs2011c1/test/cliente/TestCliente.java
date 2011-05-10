package ar.edu.utn.tacs2011c1.test.cliente;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class TestCliente {
		
	  
	
	@Test
	public void testGetApiML() throws ResourceException, IOException{
	ClientResource resource = new ClientResource("https://api.mercadolibre.com/items/MLA110394682");  
	 
	resource.get().write(System.out);

	
	assertTrue(true);
	}

	
}
