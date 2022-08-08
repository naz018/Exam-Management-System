package scad_project;
/*
class name=Techer_frame

programmer's name=Ifra Maryam AND Areeba Naz
Date=7-6-2021
purpose of class: Teacher will be able to make a paper after selecting a course and add Questions in it. Also can edit or delete question
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Techer_frame extends JFrame {
	String selected_course;
	private JFrame frame;
	JPanel panel1 = new JPanel();
	private JPanel contentPane;
	String username_t;
	Statement stmt;
	ArrayList<Course>list=new ArrayList<Course>(); 
	Course c1;
	String courseName, courseCode;
	ArrayList<Teacher>techlist=new ArrayList<Teacher>(); 
	Teacher t1;
	JComboBox course_list = new JComboBox();
	
	/**
	 * Launch the application.
	 */
	public Techer_frame(String name2) {
		
		 super("Online examination system");
		 
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(250, 100, 1070, 700);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
		username_t=name2;//passing username from login class
		JPanel oespanel = new JPanel();
		oespanel.setBackground(new Color(255,165,60,220));
		oespanel.setBounds(0, 0, 1054, 85);
		contentPane.add(oespanel);
		oespanel.setLayout(null);
		
		//Label 
		JLabel oesheading = new JLabel("ONLINE EXAMINATION SYSTEM");
		oesheading.setForeground(new Color(0, 0, 0));
		oesheading.setHorizontalAlignment(SwingConstants.CENTER);
		oesheading.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 35));
		oesheading.setBounds(228, 21, 619, 40);
		oespanel.add(oesheading);
		
		//panel
		panel1.setBackground(new Color(255,165,60,100));
		//panel.setForeground(new Color(216, 191, 216));
		panel1.setBounds(267, 119, 573, 469);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		//Label of welcome and automatically add username
		JLabel welcome = new JLabel("Welcome "+username_t);
		welcome.setForeground(new Color(0, 0, 0));
		welcome.setFont(new Font("Calisto MT", Font.BOLD, 24));
		welcome.setBounds(226, 11, 252, 33);
		panel1.add(welcome);
		
		//Label for selecting course
		JLabel selectCourse = new JLabel("Select the course");
		selectCourse.setForeground(new Color(0, 0, 0));
		selectCourse.setFont(new Font("Calisto MT", Font.BOLD, 20));
		selectCourse.setBounds(27, 65, 161, 40);
		panel1.add(selectCourse);
		
		//comboBox containing list of courses
		course_list.setToolTipText("Select course");
		course_list.setBackground(new Color(255,165,60,220));
		course_list.setFont(new Font("Calisto MT", Font.PLAIN, 15));
		course_list.setBounds(198, 69, 183, 33);
		panel1.add(course_list);
	
		//Label for course information
		JLabel lblNewLabel = new JLabel("Course Information");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Calisto MT", Font.BOLD, 22));
		lblNewLabel.setBounds(27, 165, 222, 33);
		panel1.add(lblNewLabel);
		
		//Label of course
		JLabel lblNewLabel_1 = new JLabel("GK 890    General Knowledge");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Calisto MT", Font.BOLD, 18));
		lblNewLabel_1.setBounds(47, 351, 276, 22);
		panel1.add(lblNewLabel_1);
		
		//Label of course
		JLabel lblJsJava = new JLabel("JS230      Java");
		lblJsJava.setForeground(new Color(0, 0, 0));
		lblJsJava.setFont(new Font("Calisto MT", Font.BOLD, 18));
		lblJsJava.setBounds(47, 209, 276, 25);
		panel1.add(lblJsJava);
		
		//Label of course
		JLabel lblMMaths = new JLabel("M123      Maths");
		lblMMaths.setForeground(new Color(0, 0, 0));
		lblMMaths.setFont(new Font("Calisto MT", Font.BOLD, 18));
		lblMMaths.setBounds(47, 226, 276, 33);
		panel1.add(lblMMaths);
		
		//Label of course
		JLabel lblOrOperationalResearch = new JLabel("OR202    Operational Research");
		lblOrOperationalResearch.setForeground(new Color(0, 0, 0));
		lblOrOperationalResearch.setFont(new Font("Calisto MT", Font.BOLD, 18));
		lblOrOperationalResearch.setBounds(45, 280, 276, 25);
		panel1.add(lblOrOperationalResearch);
		
		//Label of course
		JLabel lblOsOperatingSystem = new JLabel("OS191     Operating System");
		lblOsOperatingSystem.setForeground(new Color(0, 0, 0));
		lblOsOperatingSystem.setFont(new Font("Calisto MT", Font.BOLD, 18));
		lblOsOperatingSystem.setBounds(45, 255, 276, 25);
		panel1.add(lblOsOperatingSystem);
		
		//Label of course
		JLabel lblSeSoftwareEngineering = new JLabel("SE101      Software Engineering");
		lblSeSoftwareEngineering.setForeground(new Color(0, 0, 0));
		lblSeSoftwareEngineering.setFont(new Font("Calisto MT", Font.BOLD, 18));
		lblSeSoftwareEngineering.setBounds(45, 303, 276, 25);
		panel1.add(lblSeSoftwareEngineering);
		
		//Label of course
		JLabel lblSeDataStructure = new JLabel("SE321      Data Structure");
		lblSeDataStructure.setForeground(new Color(0, 0, 0));
		lblSeDataStructure.setFont(new Font("Calisto MT", Font.BOLD, 18));
		lblSeDataStructure.setBounds(45, 326, 276, 25);
		panel1.add(lblSeDataStructure);
			
		
		//Button to make a new paper
		JButton Next = new JButton("Make Paper");
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected_course=(String) course_list.getSelectedItem();	
				Paper frame = new Paper(selected_course);
				frame.setVisible(true);
			}
		});
		Next.setFont(new Font("Calisto MT", Font.BOLD, 18));
		Next.setBackground(new Color(255,165,60,220));
		Next.setBounds(387, 394, 148, 40);
		panel1.add(Next);
		
		//Button for teacher to logout
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}
		});
		btnLogout.setFont(new Font("Calisto MT", Font.BOLD, 18));
		btnLogout.setBackground(new Color(255, 165, 60, 220));
		btnLogout.setBounds(27, 394, 148, 40);
		panel1.add(btnLogout);
		
		//adding background image
		JLabel backgroundlabel = new JLabel("");
		backgroundlabel.setIcon(new ImageIcon("C:\\Users\\Ifra Maryam\\Desktop\\SCAD\\teacher.jpg"));
		backgroundlabel.setBounds(0, 85, 1054,576);
		contentPane.add(backgroundlabel);
		
		connection();
		extractingvaluesfromdatabase();
	}
	
	public void connection()
	{ /*
	connection of database with this program
	*/
		try
		{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		System.out.println("COMMMAND COMPLETED SUCCESSFULY");
		String connstring="jdbc:sqlserver://IFRA-MARYAM\\MSSQLSERVER;"+"databaseName=Online Examination System;integratedSecurity=true;";
		Connection conn=DriverManager.getConnection(connstring);
		 stmt=conn.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//extracting values from database
	private void extractingvaluesfromdatabase()
	{
		String query="SELECT * FROM Course";
		try {
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				courseCode=rs.getString(1);
				courseName=rs.getString(2);
				c1=new Course();
				c1.course_name=courseName;
				c1.course_code=courseCode;
				course_list.addItem(courseCode  );
			}
			
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
	}

	
}

//end of class
