
/*
class name=studentframe

programmer's name=Zainab Tanveer
Date=7-6-2021
purpose of class: after login student will enter into this page and select course and start test

 */
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
public class studentframe extends JFrame
{
	String username1;
	private JPanel contentPane;
	Statement stmt;
	String firstname,lastname;
	String coursecode,coursename1;
	JComboBox comboBox;
	public studentframe(String name2)
	{
		 super("Online examination system");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(250, 100, 1070, 700);
			contentPane = new JPanel();
			setContentPane(contentPane);
			contentPane.setLayout(null);
		
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255,165,0,90));
			panel.setLayout(null);
			panel.setBounds(77, 122, 954, 398);
			contentPane.add(panel);
			
			username1=name2;//which student is login save its username in this variable
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
		oesheading.setBounds(76, 23, 619, 40);
		oespanel.add(oesheading);
		
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame jf=new JFrame();
				jf.setAlwaysOnTop(true);
				int a=JOptionPane.showConfirmDialog(jf,"Do you really want to exit Application?","Select",JOptionPane.YES_NO_OPTION);
				if(a==0)
				{
					System.exit(0);
				}
			}
		});
		btnNewButton.setBackground(new Color(51, 255, 153));
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setFont(new Font("Calisto MT", Font.BOLD, 20));
		btnNewButton.setBounds(956, 11, 98, 34);
		oespanel.add(btnNewButton);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFrame jf=new JFrame();
				jf.setAlwaysOnTop(true);
				int a=JOptionPane.showConfirmDialog(jf,"Do you really want to logout?","Select",JOptionPane.YES_NO_OPTION);
				if(a==0)
				{
					setVisible(false);
					new Login().setVisible(true);
				}
			}
		});
		btnLogout.setVerticalAlignment(SwingConstants.BOTTOM);
		btnLogout.setFont(new Font("Calisto MT", Font.BOLD, 20));
		btnLogout.setBackground(new Color(51, 255, 153));
		btnLogout.setBounds(817, 11, 137, 34);
		oespanel.add(btnLogout);
		
		
		connection();
		extractingvaluesfromdatabase();
		
		JLabel welcome = new JLabel("WELCOME");
		welcome.setForeground(new Color(0, 0, 128));
		welcome.setFont(new Font("Calisto MT", Font.BOLD, 24));
		welcome.setBounds(55, 33, 175, 38);
		panel.add(welcome);
		
		JLabel name=new JLabel(firstname+" "+lastname);
		name.setForeground(new Color(225,6,0));
		name.setFont(new Font("Calisto MT", Font.BOLD, 24));
		name.setBounds(230, 33, 175, 38);
		panel.add(name);
		
		comboBox = new JComboBox();
		comboBox.setForeground(Color.BLACK);
		comboBox.setBackground(new Color(192, 192, 192));
		comboBox.setFont(new Font("Calisto MT", Font.BOLD, 20));
		comboBox.setBounds(216, 162, 279, 31);
		panel.add(comboBox);
		
		connection();
		extractcourses();
		
		JLabel selectcourse = new JLabel("Select Course");
		selectcourse.setForeground(Color.BLACK);
		selectcourse.setFont(new Font("Calisto MT", Font.BOLD, 25));
		selectcourse.setBounds(44, 162, 190, 38);
		panel.add(selectcourse);
		
		JButton save = new JButton("SAVE");
		save.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				connection();
				coursename1=(String) comboBox.getSelectedItem();
				findcoursecode();
				String query="INSERT INTO Student_Course_Detail VALUES ('"+coursecode+"','"+username1+"')";
				try
				{
					
					stmt.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "SUCCESSFULLY SAVED COURSE YOU CAN NOW START EXAM");
				}
				catch (SQLException e1)
				{
				
					e1.printStackTrace();
				}
			}
		});
		save.setFont(new Font("Calisto MT", Font.BOLD, 24));
		save.setBounds(111, 325, 138, 50);
		save.setBackground(new Color(255, 204, 51));
		panel.add(save);
		
		JTextArea examdetail = new JTextArea();
		examdetail.setBackground(new Color(220, 220, 220));
		examdetail.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		examdetail.setForeground(Color.BLACK);
		examdetail.setText("Online Examination System is managing all activities related to\r\nexamination right after registration student have to select \r\ncourse and then save it after that he can start his test\r\n\\\\INSTRUCTIONS\\\\\r\n1-there are total 5 questions each with 5 marks\r\n2-Maximum time is 5 minutes\r\n3-After time completion paper will be automatically called off\r\n4-No negative marking\r\nGoodLuck");
		examdetail.setBounds(529, 82, 425, 175);
		examdetail.setEditable(false);
		panel.add(examdetail);
		
		JButton btnNewButton_1 = new JButton("START TEST");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				Exam ex=new Exam(username1,coursecode);
				ex.setVisible(true);
			}
		});
		btnNewButton_1.setBackground(new Color(255, 204, 51));
		btnNewButton_1.setFont(new Font("Calisto MT", Font.BOLD, 24));
		btnNewButton_1.setBounds(296, 325, 221, 50);
		panel.add(btnNewButton_1);
		
		
		
		
/* background image in this label */
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Calisto MT", Font.BOLD, 20));
		lblNewLabel.setBackground(new Color(204, 255, 255));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Farhat ul ain\\Documents\\select course.jpg"));
		lblNewLabel.setBounds(0, 85, 1054, 576);
		contentPane.add(lblNewLabel);
		

		
		
		
		

		
	}
	
	public void findcoursecode()
	{
/* function will get coursecode of the selected coursename by user*/
		String query="SELECT course_code FROM Course WHERE course_name='"+coursename1+"'";
		try {
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				coursecode=rs.getString(1);
				
			}
			
		}
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void extractcourses()
	{

		/*   this function will get the name of the course from the database
		  */
		String query="SELECT * FROM Course";
		try {
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				coursecode=rs.getString(1);
				coursename1=rs.getString(2);
				comboBox.addItem(coursename1);
			}
			
		}
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void extractingvaluesfromdatabase()
	{
		/*   this function will get the name and full name of the user who logged in
		  */
			String query="SELECT first_name,last_name FROM Student WHERE username='"+username1+"'";
			try {
				ResultSet rs=stmt.executeQuery(query);
				while(rs.next())
				{
					firstname=rs.getString(1);
					lastname=rs.getString(2);
				}
				
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
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


