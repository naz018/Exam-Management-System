
/*

class name=Teacher

programmer's name=Zainab Tanveer AND Ifra Maryam
Date=29-5-2021
purpose of class: teacher will enter their information and then it will match with database values to allow users to login to the system

 */
public class Teacher 
{
	String first_name;
	String last_name;
	String username;
	String Email_id;
	Course coursecode;
	int salary;
	String password;

	Teacher(String fn,String ln,String user,String email,int s,String pass)
	{
		first_name=fn;
		last_name=ln;
		username=user;
		Email_id=email;
		salary=s;
		password=pass;
		
	}
	Teacher()
	{
		 first_name="";
		 last_name="";
		 username="";
		 Email_id="";
		 salary=0;
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

	public int getsalary()
	{
		return this.salary;
	}
	public String getpass()
	{
		return this.password;
	}

}
