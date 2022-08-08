package scad_project;
/*
class name=Paper
programmer's name=Areeba Naz and Ifra Maryam 
Date=3-6-2021
purpose of class: Teacher will be able to enter paper information and then store it in Database, edit paper, search paper and delete paper.*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.awt.event.ActionEvent;

//Start of class
public class Paper extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	PreparedStatement stmt;
	Statement stm;
	ResultSet rs;
	Connection conn;
	String c_code; //For getting value of selected course
	String p_id; //For storing values from text fields
	int auto_pid;
	String p_time,p_date; //For storing values from text fields
	
	//Parameter constructor of class
	public Paper(String course) {
		super("Paper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1070, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
			contentPane.setLayout(null);
			c_code=course;// passing course code selected by Teacher
			
			//panel of light gray color
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255,165,60,100));
			panel.setBounds(310, 119, 466, 469);
			contentPane.add(panel, BorderLayout.EAST);
			panel.setLayout(null);
			
			//Panel to add heading... Dark orange color
			JPanel oespanel = new JPanel();
			oespanel.setBackground(new Color(255,165,60,220));
			oespanel.setBounds(0, 0, 1054, 85);
			contentPane.add(oespanel);
			oespanel.setLayout(null);
			
			//Heading of System
			JLabel lblNewLabel = new JLabel("ONLINE EXAMINATION SYSTEM");
			//lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setForeground(new Color(0, 0, 0));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 40));
			lblNewLabel.setBounds(237, 21, 619, 40);
			oespanel.add(lblNewLabel);
			
			//Heading of panel to enter information
			JLabel lblLogin = new JLabel("Please Enter Paper Information");
			lblLogin.setBackground(new Color(0, 0, 0));
			lblLogin.setForeground(new Color(0, 0, 0));
			lblLogin.setBounds(78, 11, 335, 66);
			lblLogin.setFont(new Font("Calisto MT", Font.BOLD, 23));
			lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblLogin);
			
			//Label of Paper ID
			JLabel lblNewLabel_1 = new JLabel("Paper ID");
			lblNewLabel_1.setForeground(new Color(0, 0, 0));
			lblNewLabel_1.setFont(new Font("Calisto MT", Font.BOLD, 20));
			lblNewLabel_1.setBounds(37, 126, 135, 32);
			panel.add(lblNewLabel_1);
			
			//Label of Ppaer_time
			JLabel lblNewLabel_3 = new JLabel("Paper Time");
			lblNewLabel_3.setFont(new Font("Calisto MT", Font.BOLD, 20));
			lblNewLabel_3.setForeground(new Color(0, 0, 0));
			lblNewLabel_3.setBounds(37, 169, 135, 24);
			panel.add(lblNewLabel_3);
			
			//Label of paper Date
			JLabel lblNewLabel_4 = new JLabel("Paper Date");
			lblNewLabel_4.setForeground(new Color(0, 0, 0));
			lblNewLabel_4.setFont(new Font("Calisto MT", Font.BOLD, 20));
			lblNewLabel_4.setBounds(43, 204, 163, 26);
			panel.add(lblNewLabel_4);
			
			//Label of Course Code
			JLabel lblNewLabel_5 = new JLabel("Course Code");
			lblNewLabel_5.setFont(new Font("Calisto MT", Font.BOLD, 20));
			lblNewLabel_5.setForeground(new Color(0, 0, 0));
			lblNewLabel_5.setBounds(37, 247, 135, 20);
			panel.add(lblNewLabel_5);
			
			//automatically generating paper ID
			try
			{
				connection();
				String Query="SELECT paper_id FROM Paper";
				rs=stm.executeQuery(Query);
				while(rs.next())
				{
					auto_pid =rs.getInt(1);
					System.out.println("PAPER ID");
					System.out.println(auto_pid);
					System.out.println("PAPER ID");
					auto_pid=auto_pid+1;
					String str=String.valueOf(auto_pid);
					textField = new JTextField();
					textField.setText(str);         //seting paper id in the text field
					textField.setEditable(false);
					textField.setBounds(648, 11, 86, 38);
					panel.add(textField);
					textField.setColumns(10);
					
				}
				}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			textField.setFont(new Font("Calisto MT", Font.PLAIN, 14));
			textField.setBounds(228, 124, 200, 31);
		
			//Text field to enter paper Time
			textField_2 = new JTextField();
			textField_2.setFont(new Font("Calisto MT", Font.PLAIN, 14));
			textField_2.setBounds(228, 166, 200, 32);
			panel.add(textField_2);
			textField_2.setText("hh:mm:ss");
			textField_2.setColumns(10);
			
			//Text field to enter paper Date
			textField_3 = new JTextField();
			textField_3.setFont(new Font("Calisto MT", Font.PLAIN, 14));
			textField_3.setBounds(228, 202, 200, 32);
			panel.add(textField_3);
			textField_3.setText("YYYY-MM-DD");
			textField_3.setColumns(10);
			
			//Text field to enter Course code (Automatic Fill)
			textField_4 = new JTextField();
			textField_4.setEditable(false);
			textField_4.setFont(new Font("Calisto MT", Font.PLAIN, 14));
			textField_4.setBounds(228, 241, 200, 32);
			panel.add(textField_4);
			textField_4.setText(course);
			textField_4.setColumns(10);
			
			//Button to ADD paper info into Database and Open Question Interface 
			JButton btnNewButton = new JButton("Add Questions");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					p_id=textField.getText(); 
					p_time=textField_2.getText();
					p_date=textField_3.getText();
				    if(p_date.equals("YYYY-MM-DD")|| p_time.equals("hh:mm:ss"))
					{
						JOptionPane.showMessageDialog(null, "PLEASE ENTER PAPER DATE AND TIME");
						
					}
				    
					
					else if(p_time.length()>8)
					{
						JOptionPane.showMessageDialog(null, "LENGHT OF TIME SHOULD MUST BE 8");
					}
					else if( p_date.length()>11)
					{
						JOptionPane.showMessageDialog(null, "LENGHT OF DATE SHOULD MUST BE 11");
					}
					else
					{
						connection();
						try
						{
							//Inserting Data into Database
							String query="INSERT INTO Paper(paper_id,paper_time,paper_date,courseCode)VALUES(?,?,?,?)";
							stmt = conn.prepareStatement(query);
							stmt.setString(1, p_id);
							stmt.setString(2, p_time);
							stmt.setString(3, p_date);
							stmt.setString(4, c_code);
							stmt.executeUpdate();
							System.out.println("SUCCESSFULLY INSERTED IN PAPER");
							
						}
						catch(SQLException e1)
						{
							e1.printStackTrace();
						}
						Question q=new Question(p_id);
						q.setVisible(true);

					}
				}
			});
			btnNewButton.setBackground(new Color(255,165,60,220));
			btnNewButton.setFont(new Font("Calisto MT", Font.BOLD, 18));
			btnNewButton.setForeground(new Color(0, 0, 0));
			btnNewButton.setBounds(36, 332, 157, 32);
			panel.add(btnNewButton);
			
			//Clear button to make text fields empty
			JButton btnNewButton_1 = new JButton("Clear");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					textField_2.setText("hh:mm:ss");
					textField_3.setText("YYYY-MM-DD");
				}
			});
			btnNewButton_1.setBackground(new Color(255,165,60,220));
			
			btnNewButton_1.setFont(new Font("Calisto MT", Font.BOLD, 18));
			btnNewButton_1.setForeground(new Color(0, 0, 0));
			btnNewButton_1.setBounds(306, 332, 123, 32);
			panel.add(btnNewButton_1);
			
			//Back button to go back to the previous window
			JButton back = new JButton("Back");
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				dispose();
				}
			});
			back.setForeground(Color.BLACK);
			back.setFont(new Font("Calisto MT", Font.BOLD, 18));
			back.setBackground(new Color(255, 165, 60, 220));
			back.setBounds(306, 401, 123, 32);
			panel.add(back);
			
			//Button allowing to edit / delete / search Questions
			JButton btnEditdeleteQuestions = new JButton("Edit/Delete Questions");
			btnEditdeleteQuestions.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					p_id=textField.getText();
					if(p_id.equals(""))
					{
						JOptionPane.showMessageDialog(null, "PLEASE ENTER ID FIRST OF WHICH QUESTIONS YOU WANT TO SEARCH / EDIT / DELETE ");
					}
					else
					{
						SearchPaper p=new SearchPaper(p_id);
						p.setVisible(true);
					}
					;
				}
			});
			btnEditdeleteQuestions.setForeground(Color.BLACK);
			btnEditdeleteQuestions.setFont(new Font("Calisto MT", Font.BOLD, 18));
			btnEditdeleteQuestions.setBackground(new Color(255, 165, 60, 220));
			btnEditdeleteQuestions.setBounds(37, 401, 221, 32);
			panel.add(btnEditdeleteQuestions);
			
			
			//Adding background picture
			JLabel backgroundlabel = new JLabel("");
			backgroundlabel.setIcon(new ImageIcon("C:\\Users\\Ifra Maryam\\Desktop\\SCAD\\pexels-cottonbro-3944419 (1).jpg"));
			backgroundlabel.setBounds(0, 85, 1054,576);
			contentPane.add(backgroundlabel, BorderLayout.SOUTH);	
			
	}
	
	//Method for database connection
	public void connection()
	{ 
		try
		{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		System.out.println("COMMMAND COMPLETED SUCCESSFULY");
		String connstring="jdbc:sqlserver://IFRA-MARYAM\\MSSQLSERVER;"+"databaseName=Online Examination System;integratedSecurity=true;";
		conn=DriverManager.getConnection(connstring);
		 stm= conn.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

// End of class
