

/*
class name=Resultframe

programmer's name=Zainab Tanveer AND Qurat-ul-ain
Date=7-6-2021
purpose of class: Result will be generated 
 */
public class Student
{
	String first_name;
	String last_name;
	String username;
	String Email_id;
	Course coursecode;
	
	String password;

	Student(String fn,String ln,String user,String email,String pass)
	{
		first_name=fn;
		last_name=ln;
		username=user;
		Email_id=email;
		password=pass;
		
	}
	Student()
	{
		 first_name="";
		 last_name="";
		 username="";
		 Email_id="";
		 password="";
	}
	public String getfname()
	{
		return this.first_name;
	}

	public String getlname()
	{
		return this.last_name;
	}
	public String getusername()
	{
		return this.username;
	}
	public String getemail()
	{
		return this.Email_id;
	}

	
	public String getpass()
	{
		return this.password;
	}
}
