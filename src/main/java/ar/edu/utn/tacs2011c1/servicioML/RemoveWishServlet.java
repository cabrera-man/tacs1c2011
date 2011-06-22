package ar.edu.utn.tacs2011c1.servicioML;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import ar.edu.utn.tacs2011c1.servicioML.WishBean;
import ar.edu.utn.tacs2011c1.servicioML.PMF;

public class RemoveWishServlet extends HttpServlet {

	private Long keyToRemove;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();

		User user = userService.getCurrentUser();
		String itemId = req.getParameter("itemId");
		String userName = user.getNickname();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			
			String query = "select from " + WishBean.class.getName();
			@SuppressWarnings("unchecked")
			List<WishBean> result = (List<WishBean>) pm.newQuery(query).execute();
			for(WishBean w : result){
				if(w.getItemId().equals(itemId) && w.getUserName().equals(userName)){
					keyToRemove = w.getId();
					WishBean wish = pm.getObjectById(WishBean.class,keyToRemove);
					pm.deletePersistent(wish);
				}
			}
			
			
		} finally {
			pm.close();
		}
	}

}