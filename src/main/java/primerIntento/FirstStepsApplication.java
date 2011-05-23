package primerIntento;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;


public class FirstStepsApplication extends Application {

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createInboundRoot() {
    	
    	Restlet categoriesML = new CategoriesML();
    	Restlet sitesML = new SitesML();
    	
        // Create a router Restlet that routes each call to a new instance of HelloWorldResource.
        Router router = new Router(getContext());

        // Defines only one route
        router.attach("/hello", HelloWorldResource.class);
        router.attach("/by", HelloWorldResource.class);
        router.attach("/ml", MercadoLibre.class);
        router.attach("/mlClienteRest", MercadoLibreDeprecated.class);
        
        router.attach("/categories/{categoria}", categoriesML);
        router.attach("/sites/{sitio}", sitesML);


        return router;
    }

}   