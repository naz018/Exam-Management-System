
/* 
 *classname = Register
 * programmer's name = Areeba Naz
 * Dated = 5 June 2021
 * Purpose of this class is to do Registration for new Admin, new Teacher and new Student
 */

// importing all relevant classes
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Register extends JFrame 
{

	private JPanel contentPane;
	private JTextField firstnfield;  //declare textfield for user's first name 
	private JTextField	lastnfield;  //declare textfield for user's first name 
	private JTextField userfield;    //declare textfield for user's User name 
	private	JTextField emailfield;    //declare textfield for user's Email 
	private  JPasswordField passwordfield;   //declare textfield for user's Password Field
	private JComboBox comboBox; //declare combobox consist of the type of User like admin, teacher or student
	String usertype;//get the value from selected user type
	Statement stmt; // declare Statement used for establishing connection with the database
	Connection conn; // declare Connection used for connecting with the database
	ResultSet rs;
	String first_name,last_name,username,email_id, a_password,t_password,s_password; // used to get text from the textfield, the names are same like the column name in database
	 
	String fname,lname,uname,email,pass;//used to get text from fields
	PreparedStatement pstmt;
	/**
	 * 
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Register() 
	{   super("Online examination system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1070, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel(); // creating panel on which we write registration, firstname, lastname, username, email, password and their textfields respectively, usertype and its combobox sign up and cancel button
		panel.setBackground(new Color(238, 130, 238,110));
		//panel.setForeground(new Color(216, 191, 216));
		panel.setBounds(310, 119, 466, 512);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel oespanel = new JPanel(); // creating panel on which we write ONLINE EXAMINATION SYSTEM
		oespanel.setBackground(new Color(255,165,0,220));
		oespanel.setBounds(0, 0, 1054, 85);
		contentPane.add(oespanel);
		oespanel.setLayout(null);
		
		JLabel oesheading = new JLabel("ONLINE EXAMINATION SYSTEM"); // writing name of Project on the above declared panel
		oesheading.setForeground(new Color(0, 0, 0));
		oesheading.setHorizontalAlignment(SwingConstants.CENTER);
		oesheading.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 35));
		oesheading.setBounds(237, 21, 619, 40);
		oespanel.add(oesheading);
		
		
		JLabel lblLogin = new JLabel("REGISTRATION\r\n");
		lblLogin.setForeground(new Color(0, 0, 0));
		lblLogin.setBounds(81, 24, 290, 66);
		lblLogin.setFont(new Font("Calisto MT", Font.BOLD, 34));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblLogin);
		
		JLabel username1 = new JLabel("First Name:");
		username1.setForeground(new Color(0, 0, 0));
		username1.setBounds(65, 100, 95, 42);
		username1.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		panel.add(username1);
		
	    firstnfield  = new JTextField();
		firstnfield.setBackground(new Color(176, 224, 230));
		firstnfield.setBounds(209, 113, 145, 20);
		firstnfield.setColumns(10);
		panel.add(firstnfield);
		
		JLabel password = new JLabel("Last Name:");
		password.setForeground(new Color(0, 0, 0));
		password.setBounds(65, 165, 95, 37);
		password.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		panel.add(password);
		
		
		lastnfield = new JTextField();
		lastnfield.setBackground(new Color(176, 224, 230));
		lastnfield.setBounds(209, 175, 145, 20);
		panel.add(lastnfield);
		
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		lblUsername.setBounds(65, 223, 95, 42);
		panel.add(lblUsername);
		
		userfield = new JTextField();
		userfield.setBackground(new Color(176, 224, 230));
		userfield.setBounds(209, 236, 145, 20);
		panel.add(userfield);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		lblEmail.setBounds(65, 276, 95, 42);
		panel.add(lblEmail);
		
		emailfield = new JTextField();
		emailfield.setBackground(new Color(176, 224, 230));
		emailfield.setBounds(209, 289, 145, 20);
		panel.add(emailfield);
		
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		lblPassword.setBounds(65, 329, 95, 42);
		panel.add(lblPassword);
		
		passwordfield= new JPasswordField();
		passwordfield.setBounds(209, 342, 145, 20);
		passwordfield.setBackground(new Color(176, 224, 230));
		panel.add(passwordfield);
		
		comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setBackground(new Color(176, 224, 230));
		comboBox.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Student", "Teacher"}));
		comboBox.setBounds(212, 391, 142, 22);
		panel.add(comboBox);
		
		
		JLabel ChooseUserType = new JLabel("Choose User Type");
		ChooseUserType.setForeground(Color.BLACK);
		ChooseUserType.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		ChooseUserType.setBounds(65, 382, 137, 42);
		panel.add(ChooseUserType);
		
		JButton signup = new JButton("SIGN UP");
		/*
		 *here all the checks applied on textfields are written
		 *like if all fields empty what happens when pressing signup button
		 *     if any fields empty what happens when pressing signup button
		 *     identifies limit for all data enter in textfield
		 *     identifies where the alphabets. numbers and special characters are used
		 */
		
		signup.addActionListener(new ActionListener() 
		{
		
			public void actionPerformed(ActionEvent e)
			{
				int p=0,q=0,r=0,s=0;
				
				fname= firstnfield.getText();
				 lname= lastnfield.getText();
				 uname= userfield.getText();
				 email= emailfield.getText();
				 pass= passwordfield.getText();
				
				if(firstnfield.getText().isEmpty()||lastnfield.getText().isEmpty()|| userfield.getText().isEmpty()||emailfield.getText().isEmpty()|| passwordfield.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"MUST ENTER FIRSTNAME, LASTNAME, USERNAME,EMAIL AND PASSWORD ");
				}
				else
				{ 
			
					if(fname.length()>9 || fname.length()<3)
					{
						JOptionPane.showMessageDialog(null,"FIRSTNAME MUST BE ATLEAST 3 CHARACTERS OR 9");
						firstnfield.setText(null);
					}
					else if(lname.length()>9|| lname.length()<3)
					{
						JOptionPane.showMessageDialog(null,"LASTNAME MUST BE ATLEAST 3 CHARACTERS OR 9");
						lastnfield.setText(null);
					}
					else if(uname.length()>10|| uname.length()<6)
					{
						JOptionPane.showMessageDialog(null,"USERNAME MUST BE ATLEAST 6 CHARACTERS OR 10");
						userfield.setText(null);
					}
					else if(email.length()>20|| email.length()<10)
					{
						JOptionPane.showMessageDialog(null,"EMAIL MUST BE ATLEAST 10 CHARACTERS OR 20");
						emailfield.setText(null);
					}
					else if(pass.length()>10|| pass.length()<5)
					{
						JOptionPane.showMessageDialog(null,"PASSWORD MUST BE ATLEAST 5 CHARACTERS OR 10");
						passwordfield.setText(null);
					}
					 else
					{
						 
					     for(int i=0;i<fname.length();i++)
					{
					
					     if((fname.charAt(i)>='a')&&(fname.charAt(i)<='z')||(fname.charAt(i)>='A')&&(fname.charAt(i)<='Z'))
					{
						first_name=fname;
					}
					     else {
					    	 p=1;
					     }
					}
					     if(p==1)
					 {
					    	JOptionPane.showMessageDialog(null,"INVALID  FIRSTNAME ENTER AGAIN");
							firstnfield.setText(null);
					
				     }
					
						 
					     for(int i=0;i<lname.length();i++)
							{    
					     if((lname.charAt(i)>='a')&&(lname.charAt(i)<='z')||(lname.charAt(i)>='A')&&(lname.charAt(i)<='Z'))
							{
								last_name=lname;
							}
					     else {
					    	 q=1;
					     }
					       }
					     if(q==1)
							 {
							    	JOptionPane.showMessageDialog(null,"INVALID  LASTNAME ENTER AGAIN");
									lastnfield.setText(null);
							
						     }
							
					     
					     for(int i=0;i<uname.length();i++)
							{    
					     if((uname.charAt(i)>='a')&&(uname.charAt(i)<='z')||(uname.charAt(i)>='A')&&(uname.charAt(i)<='Z')||(uname.charAt(i)>='1')&&(uname.charAt(i)<='9')||(uname.charAt(i)=='_'))
							{
								username=uname;
							}
					     else 
					     {
					    	 r=1;
					         }
							
							}
					     
					     if(r==1)
					 
							 {
							    	JOptionPane.showMessageDialog(null,"INVALID  USERNAME ENTER AGAIN");
									userfield.setText(null);
							
						     }
							
					     
					     for(int i=0;i<email.length();i++)
							{    
					     if((email.charAt(i)>='a')&&(email.charAt(i)<='z')||(email.charAt(i)>='A')&&(email.charAt(i)<='Z')||(email.charAt(i)>='1')&&(email.charAt(i)<='9')||(email.charAt(i)=='@')||(email.charAt(i)=='.'))
							{
								email_id=email;
							}
					     else {
					    	 s=1;
					     }
					}
					     if(s==1)
					 
							 {
							    	JOptionPane.showMessageDialog(null,"INVALID  EMAIL ENTER AGAIN");
									emailfield.setText(null);
							
						     }
							
					}	}
				if(p==0 && q==0 && r==0 && s==0)
				{
					
				
				insertingvaluesintodatabase(); // function calling declared below
				}
			
			}}); //end of action listener
		signup.setForeground(new Color(0, 0, 0));
		signup.setBounds(52, 451, 108, 37);
		signup.setBackground(new Color(255,165,0,220));
		signup.setFont(new Font("Calisto MT", Font.BOLD, 16));
		panel.add(signup);
		
		JButton btnCancel = new JButton("CLEAR");
		btnCancel.addActionListener(new ActionListener()// action listener identifies that if user press Cancel button all fields become empty
		{
			public void actionPerformed(ActionEvent e)
			{
				userfield.setText(null);
				firstnfield.setText(null);
				lastnfield.setText(null);
				passwordfield.setText(null);
				emailfield.setText(null);
			}
		});
		
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Calisto MT", Font.BOLD, 16));
		btnCancel.setBackground(new Color(255, 165, 0, 220));
		btnCancel.setBounds(187, 451, 108, 37);
		panel.add(btnCancel);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}
		});
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("Calisto MT", Font.BOLD, 16));
		btnLogin.setBackground(new Color(255, 165, 0, 220));
		btnLogin.setBounds(317, 451, 108, 37);
		panel.add(btnLogin);
		
		
		
		
		JLabel backgroundlabel = new JLabel("");
		backgroundlabel.setIcon(new ImageIcon("C:\\Users\\Farhat ul ain\\Documents\\reg.jpg"));
		backgroundlabel.setBounds(0, 87, 1054,600);
		contentPane.add(backgroundlabel);
	}// ending of constructor
	
	private void insertingvaluesintodatabase() // this function is used for inserting data(which the user written while registring himself) in database
	{
		 connection(); // this function is used for connecting the java with sql server(database)) 
		usertype= (String) comboBox.getSelectedItem(); // getting what a user selects from the combobox and storing the selecting data in usertype
		if(usertype.equals("Admin"))
	       {
			 // try to connecting with the table named Student(already declared in database))
			
			// try to connecting with the table named System_Admin(already declared in database)
			
			try
			{
			String query="INSERT INTO System_Admin (first_name,last_name,username,email_id,a_password)VALUES (?,?,?,?,?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, first_name);
				pstmt.setString(2, last_name);
				pstmt.setString(3, username);
				pstmt.setString(4, email_id);
				pstmt.setString(5, a_password);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "REGISTERED SUCCESSFULLY");
			}
			catch (SQLException e1)
			{
			
				e1.printStackTrace();
			}	 
	 }
		else if(usertype.equals("Student"))
	       {
			
			 // try to connecting with the table named Student(already declared in database))
			try
			{
				String query="INSERT INTO Student (first_name,last_name,username,email_id,s_password)VALUES (?,?,?,?,?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, first_name);
				pstmt.setString(2, last_name);
				pstmt.setString(3, username);
				pstmt.setString(4, email_id);
				pstmt.setString(5, s_password);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "REGISTERED SUCCESSFULLY");
		  }
			catch (SQLException e1)
			{
			
				e1.printStackTrace();
			}
	       }
		else if(usertype.equals("Teacher"))
	       {
			 
			 // try to connecting with the table named Student(already declared in database))
			
			
			try
			{
				String query="INSERT INTO Teacher (first_name,last_name,username,email_id,t_password)VALUES (?,?,?,?,?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, first_name);
				pstmt.setString(2, last_name);
				pstmt.setString(3, username);
				pstmt.setString(4, email_id);
				pstmt.setString(5, t_password);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "REGISTERED SUCCESSFULLY");
			}
			catch (SQLException e1)
			{
			
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
			String connString="jdbc:sqlserver://HAIER-PC\\SQLEXPRESS;" + "databaseName= Online Examination; integratedSecurity=true";
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