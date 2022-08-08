/*

class name=Admin	

programmer's name=Zainab Tanveer AND Quratulain
Date=6-5-2021
purpose of class: admin class is used by admin it stores admin's info
 */
public class Admin 
{
//attributes of the class
String first_name;
String last_name;
String username;
String Email_id;
int salary;
String password;

Admin(String fn,String ln,String user,String email,int s,String pass)
{
	first_name=fn;
	last_name=ln;
	username=user;
	Email_id=email;
	salary=s;
	password=pass;
	
}
Admin()
{
	 first_name="";
	 last_name="";
	 username="";
	 Email_id="";
	 salary=0;
	 password="";
}

//function to return Admin first name
public String getfname()
{
	return this.first_name;
}

//function to return Admin last name
public String getlname()
{
	return this.last_name;
}
//function to return Admin username
public String getusername()
{
	return this.username;
}
//function to return Admin email
public String getemail()
{
	return this.Email_id;
}
//function to return Admin salary
public int getsalary()
{
	return this.salary;
}
//function to return Admin password
public String getpass()
{
	return this.password;
}
}
