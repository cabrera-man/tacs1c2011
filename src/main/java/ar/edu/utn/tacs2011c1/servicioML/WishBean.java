package ar.edu.utn.tacs2011c1.servicioML;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class WishBean {
    
	/**
	 * Properties
	 */
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

	@Persistent
    private User user;

	@Persistent
    private String title;
	
	@Persistent
    private String imgURL;
	
	@Persistent
    private String itemId;
	
	@Persistent
	private String userName;

	/**
	 * Constructor
	 */
	
	public WishBean(User user, String title, String imgURL, String itemId, String userName) {
		super();
		this.user = user;
		this.title = title;
		this.imgURL = imgURL;
		this.itemId = itemId;
		this.userName = userName;
	}
	
	/**
	 * Accessors and Mutators
	 */

    public Long getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}