package cloud_final.models;

public class User {
	private int user_id;
	private String username;
	
	public User(int id, String name)
	{
		user_id = id;
		username = name;
	}
	
	public int getUser_id()
	{
		return user_id;
	}
	
	public String getUsername()
	{
		return username;
	}
}
