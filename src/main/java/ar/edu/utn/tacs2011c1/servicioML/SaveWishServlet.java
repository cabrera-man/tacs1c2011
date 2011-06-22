package ar.edu.utn.tacs2011c1.servicioML;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import ar.edu.utn.tacs2011c1.servicioML.WishBean;
import ar.edu.utn.tacs2011c1.servicioML.PMF;

public class SaveWishServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();

		User user = userService.getCurrentUser();
		String title = req.getParameter("title");
		String imgURL = req.getParameter("imgURL");
		String itemId = req.getParameter("itemId");
		String userName = user.getNickname();

		WishBean wishBean = new WishBean(user, title, imgURL, itemId, userName);

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {

			/*
			 * Query query = pm.newQuery(WishBean.class);
			 * query.setFilter("(itemId == itemIdParam)");
			 * query.setFilter("(userName == userNameParam)");
			 * query.declareParameters("String itemIdParam");
			 * query.declareParameters("String userNameParam");
			 * 
			 * if(noEstaRepetido(query, userName, itemId))
			 * pm.makePersistent(wishBean);
			 */
/*			String query = "select from " + WishBean.class.getName();
			
			List<WishBean> wishes = (List<WishBean>) pm.newQuery(query)
					.execute();
			
			Iterator it = wishes.iterator();
			
			if(wishes != null)
				while (it.hasNext()) {
					WishBean wish = (WishBean) it.next();
			}*/
			pm.makePersistent(wishBean);
		} finally {
			pm.close();
		}
	}

	/*
	 * private boolean noEstaRepetido(Query query, String userName, String
	 * itemId){ return ((Collection)query.execute(userName, itemId)).isEmpty();
	 * }
	 */
}