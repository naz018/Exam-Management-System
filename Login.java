/*

 class name=Login

 programmer's name=Zainab Tanveer
 Date=29-5-2021
 purpose of class: users will enter their information and then it will match with database values to allow users to login to the system
  */

import java.awt.BorderLayout;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	ArrayList<Admin>list=new ArrayList<Admin>(); 
	Admin a1;
	ArrayList<Student>stdlist=new ArrayList<Student>(); 
	Student s1;
	ArrayList<Teacher>techlist=new ArrayList<Teacher>(); 
	Teacher t1;
	private JPanel contentPane;
	private JTextField userfield;
	private JPasswordField passwordfield;
	Statement stmt;
	/* used for admin attributes to get tha values from database and store in these variables */
	String fname,lname,email,a_user,a_pass;
	int salary;
	String usertype;//get the value from selected user type
	
	
	/*name used to get text from userfield and check it if it is valid then store it in name1 variable and 
	name2 is final variable that matches with database value*/
	String name,pass,name1,name2,pass2;
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try
				{
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login()
	{
		 super("Online examination system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1070, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//lightpink panel
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225,180));
		//panel.setForeground(new Color(216, 191, 216));
		panel.setBounds(310, 119, 466, 469);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//orange panel
		JPanel oespanel = new JPanel();
		oespanel.setBackground(new Color(255,165,0,220));
		oespanel.setBounds(0, 0, 1054, 85);
		contentPane.add(oespanel);
		oespanel.setLayout(null);
		
		
		JLabel oesheading = new JLabel("ONLINE EXAMINATION SYSTEM");
		oesheading.setForeground(new Color(0, 0, 0));
		oesheading.setHorizontalAlignment(SwingConstants.CENTER);
		oesheading.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 35));
		oesheading.setBounds(237, 21, 619, 40);
		oespanel.add(oesheading);
		
		
		JLabel lblLogin = new JLabel("LOGIN\r\n");
		lblLogin.setForeground(new Color(0, 0, 0));
		lblLogin.setBounds(168, 37, 145, 66);
		lblLogin.setFont(new Font("Calisto MT", Font.BOLD, 34));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblLogin);
		
		
		JLabel username = new JLabel("Username:");
		username.setForeground(Color.BLACK);
		username.setBounds(65, 114, 95, 42);
		username.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		panel.add(username);
		
		
		userfield  = new JTextField();
		userfield.setForeground(Color.BLACK);
		userfield.setBackground(new Color(176, 224, 230));
		userfield.setBounds(209, 126, 145, 20);
		userfield.setColumns(10);
		panel.add(userfield);
		
		
		JLabel password = new JLabel("Password:");
		password.setForeground(Color.BLACK);
		password.setBounds(65, 183, 80, 37);
		password.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		panel.add(password);
		
		
		passwordfield = new JPasswordField();
		passwordfield.setForeground(Color.BLACK);
		passwordfield.setBackground(new Color(176, 224, 230));
		passwordfield.setBounds(209, 192, 145, 20);
		panel.add(passwordfield);
		
		
		JLabel lblSelectUserType = new JLabel("Select user type?");
		lblSelectUserType.setForeground(Color.BLACK);
		lblSelectUserType.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		lblSelectUserType.setBounds(65, 242, 144, 42);
		panel.add(lblSelectUserType);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		comboBox.setBackground(new Color(176, 224, 230));
		comboBox.setForeground(Color.BLACK);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"admin", "student", "teacher"}));
		comboBox.setBounds(209, 254, 145, 22);
		panel.add(comboBox);
		
	
		
		JButton signin = new JButton("SIGN IN");
		signin.addActionListener(new ActionListener() //actionlistener of sign in
		{
			
			int invalid,found=0; //invalid is to check if data is valid or not
			
			int usernamefound=0;//when username matches with database then its value becomes 1
			public void actionPerformed(ActionEvent e)
			
			{	
				usertype=(String) comboBox.getSelectedItem();
				connection();
				extractingvaluesfromdatabase();
				name=userfield.getText();
				pass=passwordfield.getText();
				
				
				if(userfield.getText().isEmpty()|| passwordfield.getText().isEmpty()) //if fields are empty
				{
					JOptionPane.showMessageDialog(null,"MUST ENTER USERNAME AND PASSWORD");
				}
				else
				{ 
			
					if(name.length()>10||name.length()<6) //check on the length of username
					{
						JOptionPane.showMessageDialog(null,"USERNAME MUST BE ATLEAST 6 CHARACTERS OR 10");
						userfield.setText(null);
					}
					else if(pass.length()>10||pass.length()<5)
					{
						JOptionPane.showMessageDialog(null,"PASSWORD MUST BE ATLEAST 5 CHARACTERS OR 10");
						passwordfield.setText(null);
					}
					else
					{
						/* checks for the correct data if username containes alphabets or number or underscore so it is correct and store in name1 variable*/
						 for(int i=0;i<name.length();i++)
							{
							if((name.charAt(i)>='a')&&(name.charAt(i)<='z')||(name.charAt(i)>='A')&&(name.charAt(i)<='Z')||(name.charAt(i)>='1')&&(name.charAt(i)<='9')||(name.charAt(i)=='_'))
							{
								name1=name;
							}
							else 
							{
								invalid=1;
								found=1;
							}
						   }
					    	if(invalid==1)
					    	{
					    		JOptionPane.showMessageDialog(null,"INVALID  USERNAME ENTER AGAIN");
								userfield.setText(null);
					    	}
					    	if(found==0)
					    	{
					    		
					    		if(usertype.equals("admin"))
					   	{
					    		for(int i1=0;i1<list.size();i1++)
					    				
					    		{
					    			a1=list.get(i1);
					    			a_user=a1.getusername();
					    			a_pass=a1.getpass();
					    		if(name1.equals(a_user)&&pass.equals(a_pass))
					    		{
					    			usernamefound=1;
					    			name2=name1;
					    			pass2=a_pass;
					    			
					    		}
					    }
					    		
					    		if(usernamefound==1)
					    		{
					    		  if(name2.equals(name1)&&pass2.equals(pass))
					    			{
					    				JOptionPane.showMessageDialog(null, " PASSWORD  MATCH SUCCESSFULLY");
					    			}
					    		else if(!pass2.equals(pass) && name2.equals(name1) )
							        {
									 JOptionPane.showMessageDialog(null, " PASSWORD DOES NOT MATCH");
									 passwordfield.setText(null);
							        }
								 else if(!name2.equals(name1) && pass2.equals(pass))
								 {
									 JOptionPane.showMessageDialog(null, "USERNAME DOES NOT MATCH");
									 userfield.setText(null);
								 }
								 else if(!pass2.equals(pass) && !name2.equals(name1))
								 {
									 JOptionPane.showMessageDialog(null,"USER NAME AND PASSWORD DOES NOT MATCH ","Error",JOptionPane.ERROR_MESSAGE);	 
								 }
					    		}
					    		else
					    		{
					    			JOptionPane.showMessageDialog(null,"YOU'RE NOT REGISTERED AS ADMIN KINDLY REGISTER YOURSELF FIRST THEN SIGNIN","Error",JOptionPane.ERROR_MESSAGE);	
					    		userfield.setText(null);
					    		passwordfield.setText(null);
					    		}
					    	}
					    		else if(usertype.equals("student"))
					    		{
					    			for(int i1=0;i1<stdlist.size();i1++)
						    		{
						    			s1=stdlist.get(i1);
						    			a_user=s1.getusername();
						    			a_pass=s1.getpass();
						    			
						    		if(name1.equals(a_user)&&pass.equals(a_pass))
						    		{
						    			usernamefound=1;
						    			name2=a_user;
						    			pass2=a_pass;
						    		}
						    		}
					    			
						    		if(usernamefound==1)
						    		{
						    		  if(name2.equals(name1)&&pass2.equals(pass))
						    			{
						    				JOptionPane.showMessageDialog(null, " PASSWORD  MATCH SUCCESSFULLY");
						    				studentframe sf=new studentframe(name2);
						    				sf.setVisible(true);
						    			}
						    		else if(!pass2.equals(pass) && name2.equals(name1) )
								        {
										 JOptionPane.showMessageDialog(null, " PASSWORD DOES NOT MATCH");
										 passwordfield.setText(null);
								        }
									 else if(!name2.equals(name1) && pass2.equals(pass))
									 {
										 JOptionPane.showMessageDialog(null, "USERNAME DOES NOT MATCH");
										 userfield.setText(null);
									 }
									 else if(!pass2.equals(pass) && !name2.equals(name1))
									 {
										 JOptionPane.showMessageDialog(null,"USER NAME AND PASSWORD DOES NOT MATCH ","Error",JOptionPane.ERROR_MESSAGE);	 
									 }
						    		}
						    		else
						    		{
						    			JOptionPane.showMessageDialog(null,"YOU'RE NOT REGISTERED AS STUDENT KINDLY REGISTER YOURSELF FIRST THEN SIGNIN","Error",JOptionPane.ERROR_MESSAGE);	
						    		userfield.setText(null);
						    		passwordfield.setText(null);
						    		}
					    		}
					    		else if(usertype.equals("teacher"))
					    		{
					    			for(int i1=0;i1<techlist.size();i1++)
						    		{
						    			t1=techlist.get(i1);
						    			a_user=t1.getusername();
						    			a_pass=t1.getpass();
						    			
						    		if(name1.equals(a_user)&&pass.equals(a_pass))
						    		{
						    			usernamefound=1;
						    			name2=a_user;
						    			pass2=a_pass;
						    		}
						    		}
					    			
						    		if(usernamefound==1)
						    		{
						    		  if(name2.equals(name1)&&pass2.equals(pass))
						    			{
						    				JOptionPane.showMessageDialog(null, " PASSWORD  MATCH SUCCESSFULLY");
						    			}
						    		else if(!pass2.equals(pass) && name2.equals(name1) )
								        {
										 JOptionPane.showMessageDialog(null, " PASSWORD DOES NOT MATCH");
										 passwordfield.setText(null);
								        }
									 else if(!name2.equals(name1) && pass2.equals(pass))
									 {
										 JOptionPane.showMessageDialog(null, "USERNAME DOES NOT MATCH");
										 userfield.setText(null);
									 }
									 else if(!pass2.equals(pass) && !name2.equals(name1))
									 {
										 JOptionPane.showMessageDialog(null,"USER NAME AND PASSWORD DOES NOT MATCH ","Error",JOptionPane.ERROR_MESSAGE);	 
									 }
						    		}
						    		else
						    		{
						    			JOptionPane.showMessageDialog(null,"YOU'RE NOT REGISTERED AS TEACHER KINDLY REGISTER YOURSELF FIRST THEN SIGNIN","Error",JOptionPane.ERROR_MESSAGE);	
						    		userfield.setText(null);
						    		passwordfield.setText(null);
						    		}
					    		}
					    }
						
					}
				}
			}
		});
		signin.setForeground(new Color(0, 0, 0));
		signin.setBounds(101, 331, 108, 37);
		signin.setBackground(new Color(255,165,0,220));
		signin.setFont(new Font("Calisto MT", Font.BOLD, 16));
		panel.add(signin);
		
		JButton cancel = new JButton("CANCEL");
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				userfield.setText(null);
				passwordfield.setText(null);
			}
		});
		
		
		cancel.setForeground(new Color(0, 0, 0));
		cancel.setFont(new Font("Calisto MT", Font.BOLD, 16));
		cancel.setBackground(new Color(255,165,0,220));
		cancel.setBounds(246, 331, 108, 37);
		panel.add(cancel);
		
		
		JButton register = new JButton("Don't have account? Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register r=new Register();
				r.setVisible(true);
			}
		});
		
		
		register.setBackground(new Color(255,165,0,220));
		register.setForeground(new Color(255, 0, 0));
		register.setFont(new Font("Calisto MT", Font.BOLD, 16));
		register.setBounds(112, 403, 245, 34);
		panel.add(register);
		
		
		
		
		
		//background image
		JLabel backgroundlabel = new JLabel("");
		backgroundlabel.setIcon(new ImageIcon("C:\\Users\\Farhat ul ain\\Documents\\picture_1.jpeg"));
		backgroundlabel.setBounds(0, 85, 1054,576);
		contentPane.add(backgroundlabel);
		
	
		
		
		
	}
	
	private void extractingvaluesfromdatabase()
	{
		/*   this function will check what kind of user is trying to login so program will extract data of that
		 * particular user from database and check it
		  */
		 
		if(usertype.equals("admin"))
	{
		String query="SELECT * FROM System_Admin";
		try 
		{
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				fname=rs.getString(1);
				lname=rs.getString(2);
				a_user=rs.getString(3);
				email=rs.getString(4);
				a_pass=rs.getString(5);
				salary=rs.getInt(6);
				a1=new Admin();
				a1.first_name=fname;
				a1.last_name=lname;
				a1.username=a_user;
				a1.Email_id=email;
				a1.password=a_pass;
				a1.salary=salary;
				list.add(a1);
			}
			
		} 
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		else if (usertype.equals("student"))
		{
			String query="SELECT * FROM Student";
			try {
				ResultSet rs=stmt.executeQuery(query);
				while(rs.next())
				{
					fname=rs.getString(1);
					lname=rs.getString(2);
					a_user=rs.getString(3);
					email=rs.getString(4);
					a_pass=rs.getString(5);
					s1=new Student();
					s1.first_name=fname;
					s1.last_name=lname;
					s1.username=a_user;
					s1.Email_id=email;
					s1.password=a_pass;
					stdlist.add(s1);
				}
				
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (usertype.equals("teacher"))
		{

			String query="SELECT * FROM Teacher";
			try {
				ResultSet rs=stmt.executeQuery(query);
				while(rs.next())
				{
					fname=rs.getString(1);
					lname=rs.getString(2);
					a_user=rs.getString(3);
					email=rs.getString(4);
					a_pass=rs.getString(5);
					salary=rs.getInt(6);
					t1=new Teacher();
					t1.first_name=fname;
					t1.last_name=lname;
					t1.username=a_user;
					t1.Email_id=email;
					t1.password=a_pass;
					t1.salary=salary;
					techlist.add(t1);
				}
				
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	public void connection()
	{ /*
	connection of database with this program
	*/
			try
			{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connString="jdbc:sqlserver://HAIER-PC\\SQLEXPRESS;" + "databaseName= Online Examination;integratedSecurity=true";
			Connection conn=DriverManager.getConnection(connString);
			 stmt=conn.createStatement();
			 System.out.println("connect to Microsoft SQL Server");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
}
