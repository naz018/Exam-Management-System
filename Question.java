
/*
class name=Paper
programmer's name=Areeba Naz and Ifra Maryam 
Date=3-6-2021
purpose of class: Teacher will be able to write question, its options and correct answers and then store it in Database.*/
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class Question extends JFrame {
	
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("Add Questions");
	private JTextField question_id;
	private JTextField opt_2;
	private JTextField question;
	private JTextField opt_1;
	private JTextField opt_4;
	private JTextField opt_3;
	private JTextField answer;
	PreparedStatement stmt;
	Statement stm;
	Connection conn;
	Integer count=0;
	ResultSet rs;
	ArrayList<Object> ques_list=new ArrayList<Object>();
	int auto_id;
	String q_id, paper_id;
	String q_description, option_1, option_2, option_3, option_4 ,correct_answer; // variables for storing getting data from text fields and storing into database

	/**
	 * Create the frame.
	 */
	//start of class
	public Question(String paperID) {
		super("Add Questions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1070, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		paper_id=paperID; //passing paper id from "Paper" class
		
		//Panel
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(15, 5, 1029, 43);
		contentPane.add(panel);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 16));
		contentPane.setLayout(null);
		
		//Panel containg components
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(135, 206, 250,100));
		panel_1.setBounds(128, 107, 804, 478);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//automatically generating question id
		try
		{
			connection();
			String Query="SELECT COUNT(q_id) FROM Question";
			rs=stm.executeQuery(Query);
			while(rs.next())
			{
				auto_id =rs.getInt(1);
				auto_id=auto_id+1;
				String str=String.valueOf(auto_id);
				question_id = new JTextField();
				question_id.setText(str);          //setting question id in the textfield
				question_id.setEditable(false);
				question_id.setBounds(648, 11, 86, 38);
				panel_1.add(question_id);
				question_id.setColumns(10);
				
			}
			}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		//label of "Question ID"
		JLabel lblNewLabel_1 = new JLabel("Question ID");
		lblNewLabel_1.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_1.setBounds(534, 22, 81, 20);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNewLabel_1);
		
		
		
		//Label showing paper ID
		JLabel paper_id_label = new JLabel("Paper ID : "+paper_id);
		paper_id_label.setFont(new Font("Calisto MT", Font.BOLD, 14));
		paper_id_label.setBounds(169, 18, 150, 26);
		panel_1.add(paper_id_label);
		
		//label of question
		JLabel lblNewLabel_2 = new JLabel("Question:");
		lblNewLabel_2.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_2.setBounds(77, 83, 67, 14);
		panel_1.add(lblNewLabel_2);
		
		//label of option 1
		JLabel lblNewLabel_3 = new JLabel("Option 1:");
		lblNewLabel_3.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_3.setBounds(77, 179, 67, 14);
		panel_1.add(lblNewLabel_3);
		
		//label of option 2
		JLabel lblNewLabel_4 = new JLabel("Option 2:");
		lblNewLabel_4.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_4.setBounds(443, 179, 67, 14);
		panel_1.add(lblNewLabel_4);
		
		//label of option 3
		JLabel lblNewLabel_5 = new JLabel("Option 3:");
		lblNewLabel_5.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_5.setBounds(77, 239, 67, 14);
		panel_1.add(lblNewLabel_5);
		
		//label of option 4
		JLabel lblNewLabel_6 = new JLabel("Option 4:");
		lblNewLabel_6.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_6.setBounds(443, 236, 67, 20);
		panel_1.add(lblNewLabel_6);
		
		//label of correct answer
		JLabel lblNewLabel_7 = new JLabel("Correct Answer");
		lblNewLabel_7.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_7.setBounds(254, 294, 121, 28);
		panel_1.add(lblNewLabel_7);
		
		//adding background image
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\Farhat ul ain\\Documents\\qq.jpg"));
		lblNewLabel_8.setBounds(15, 50, 1039, 611);
		contentPane.add(lblNewLabel_8);
		
		//Text field for writing Question
		question = new JTextField();
		question.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		question.setColumns(10);
		question.setBounds(168, 62, 566, 77);
		panel_1.add(question);
		
		//Text field for writing Option_1
		opt_1 = new JTextField();
		opt_1.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		opt_1.setColumns(10);
		opt_1.setBounds(168, 168, 247, 34);
		panel_1.add(opt_1);
		
		//Text field for writing Option_2
		opt_2 = new JTextField();
	    opt_2.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		opt_2.setColumns(10);
		opt_2.setBounds(520, 168, 214, 34);
		panel_1.add(opt_2);

		//Text field for writing Option_3
		opt_3 = new JTextField();
		opt_3.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		opt_3.setColumns(10);
		opt_3.setBounds(168, 228, 247, 34);
		panel_1.add(opt_3);
		
		
		//Text field for writing Option_4
		opt_4 = new JTextField();
		opt_4.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		opt_4.setColumns(10);
		opt_4.setBounds(520, 228, 214, 34);
		panel_1.add(opt_4);
		
		//Text field for writing Correct Answer
		answer = new JTextField();
		answer.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		answer.setColumns(10);
		answer.setBounds(385, 290, 247, 34);
		panel_1.add(answer);
		
		//Button for saving question into database
		JButton save = new JButton("Save");
		save.setBounds(268, 359, 95, 34);
		panel_1.add(save);
		save.setFont(new Font("Calisto MT", Font.BOLD, 14));
		save.setBackground(new Color(135, 206, 235));
		save.setForeground(new Color(0, 0, 0));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				q_id=question_id.getText();
			q_description=question.getText();
			option_1=opt_1.getText();
			option_2=opt_2.getText();
			option_3=opt_3.getText();
			option_4=opt_4.getText();
			correct_answer=answer.getText();
			
			//checking text fields if they are empty
			 if(q_description.equals(""))
			{
				JOptionPane.showMessageDialog(null, "PLEASE ENTER QUESTION");
			}
			else if(option_1.equals(""))
			{
				JOptionPane.showMessageDialog(null, "PLEASE ENTER OPTION # 1");
			}
			else if(option_2.equals(""))
			{
				JOptionPane.showMessageDialog(null, "PLEASE ENTER OPTION # 2");
			}
			else if(option_3.equals(""))
			{
				JOptionPane.showMessageDialog(null, "PLEASE ENTER OPTION # 3");
			}
			else if(option_4.equals(""))
			{
				JOptionPane.showMessageDialog(null, "PLEASE ENTER OPTION # 4");
			}
			else if(correct_answer.equals(""))
			{
				JOptionPane.showMessageDialog(null, "PLEASE ENTER CORRECT ANSWER OF THE QUESTION");
			}
			else
				{	
				count=count+1;
				connection();
				try
				{
					//storing question into database
					String query="INSERT INTO Question(q_id, paper_id,q_description,option_1,option_2,option_3,option_4,correct_answer)VALUES(?,?,?,?,?,?,?,?)";
					stmt = conn.prepareStatement(query);
					stmt.setString(1, q_id);
					stmt.setString(2, paper_id);
					stmt.setString(3, q_description);
					stmt.setString(4, option_1);
					stmt.setString(5, option_2);
					stmt.setString(6, option_3);
					stmt.setString(7, option_4);
					stmt.setString(8, correct_answer);
					stmt.executeUpdate();
					System.out.println("SUCCESSFULLY INSERTED IN QUESTION TABLE");
					JOptionPane.showMessageDialog(null, "Question saved successfully");
					auto_id++;
					String str=String.valueOf(auto_id);
					question_id.setText(str);
					question.setText("");
					opt_1.setText("");
					opt_2.setText("");
					opt_3.setText("");
					opt_4.setText("");
					answer.setText("");
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}

				}
			
		}
	});
		
		//Button for clearing fields
		JButton clear = new JButton("Clear");
		clear.setBounds(415, 359, 95, 34);
		panel_1.add(clear);
		clear.setFont(new Font("Calisto MT", Font.BOLD, 14));
		clear.setBackground(new Color(135, 206, 250,220));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				question.setText("");
				opt_1.setText("");
				opt_2.setText("");
				opt_3.setText("");
				opt_4.setText("");
				answer.setText("");
			}
		});
		clear.setForeground(new Color(0, 0, 0));
		
		//Button for closing question frame
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(count<5)
				{
					JOptionPane.showMessageDialog(null,"A PAPER MUST CONTAIN 5 QUESTIONS. PLEASE ADD QUESTIONS");
				}
				else {
					count=0;
					dispose();
					
				}
			
			}
		});
		close.setBounds(567, 359, 89, 34);
		panel_1.add(close);
		close.setFont(new Font("Calisto MT", Font.BOLD, 14));
		close.setBackground(new Color(135, 206, 250,220));
		close.setForeground(new Color(0, 0, 0));
		
		
		
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
	
	//Function returning question ID
	 String tell_id() {
		
		return q_id;
	}
	//Function returning question statement
	 String tell_ques() {
			
		return q_description;
	}
	//Function returning option 1
	 String tell_opt1()
	 {
		 return option_1;
	 }
	//Function returning option 2
	 String tell_opt2()
	 {
		 return option_2;
	 }
	//Function returning option 3
	 String tell_opt3()
	 {
		 return option_3;
	 }
	//Function returning option 4
	 String tell_opt4()
	 {
		 return option_4;
	 }
	//Function returning correct answer
	 String tell_ans()
	 {
		 return correct_answer;
	 }
	 
}
//End of class