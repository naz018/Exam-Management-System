package scad_project;
/*
class name=Resultframe

programmer's name=Ifra Maryam AND Areeba Naz
Date=7-6-2021
purpose of class: Teacher will be able to search, edit or delete uestion from paper 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SearchPaper extends JFrame {
	Connection conn;
	PreparedStatement prep;
	ArrayList<Question> q_list=new ArrayList<Question>(); 
	Question q1;
	ResultSet rs;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("Search Question");
	private JTextField question_id;
	private JTextField question;
	private JTextField opt1;
	private JTextField opt2;
	private JTextField opt3;
	private JTextField opt4;
	private JTextField ans;
	Statement stmt;
	
	String get_id,ques_id,paper_id,q_des, opt_1,opt_2,opt_3,opt_4,c_answer;//storing values from text fields
	String  update_id,update_ques, update_opt1,update_opt2,update_opt3,update_opt4,update_ans; //storing updated values

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 * @return 
	 */
	
	public SearchPaper(String paperID) {
		super("Search / Edit / Delete Questions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1070, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		paper_id=paperID;
		q1=new Question(paper_id); //passing paper id
		
		//panel
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1079, 24);
		panel.setBackground(new Color(0, 0, 0));
		contentPane.add(panel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		panel.add(lblNewLabel);
		contentPane.setLayout(null);
		
		//2nd panel
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 192, 203,150));
		panel_1.setBounds(210, 89, 683, 490);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//label for Question id
		JLabel lblNewLabel_1 = new JLabel("Question ID");
		lblNewLabel_1.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_1.setBounds(262, 51, 81, 20);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNewLabel_1);
		
		//text field for question id
		question_id = new JTextField();
		question_id.setBounds(353, 43, 104, 33);
		panel_1.add(question_id);
		question_id.setColumns(10);
		
		//label for Question
		JLabel lblNewLabel_2 = new JLabel("Question:");
		lblNewLabel_2.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_2.setBounds(57, 105, 67, 14);
		panel_1.add(lblNewLabel_2);
		
		//label for option 1
		JLabel lblNewLabel_3 = new JLabel("Option 1:");
		lblNewLabel_3.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_3.setBounds(57, 213, 67, 14);
		panel_1.add(lblNewLabel_3);
		
		//label for option 2
		JLabel lblNewLabel_4 = new JLabel("Option 2:");
		lblNewLabel_4.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_4.setBounds(355, 213, 67, 14);
		panel_1.add(lblNewLabel_4);
		
		//label for option 3
		JLabel lblNewLabel_5 = new JLabel("Option 3:");
		lblNewLabel_5.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_5.setBounds(57, 257, 67, 14);
		panel_1.add(lblNewLabel_5);
		
		//label for option 4
		JLabel lblNewLabel_6 = new JLabel("Option 4:");
		lblNewLabel_6.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_6.setBounds(355, 257, 67, 14);
		panel_1.add(lblNewLabel_6);
		
		//label for option 5
		JLabel lblNewLabel_7 = new JLabel("Correct Answer");
		lblNewLabel_7.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblNewLabel_7.setBounds(176, 292, 122, 28);
		panel_1.add(lblNewLabel_7);
		
		//textfield for question
		question = new JTextField();
		question.setBounds(134, 87, 505, 106);
		panel_1.add(question);
		question.setColumns(10);
		
		//text field for option 1
		opt1 = new JTextField();
		opt1.setBounds(134, 204, 211, 33);
		panel_1.add(opt1);
		opt1.setColumns(10);
		
		//text field for option 2
		opt2 = new JTextField();
		opt2.setBounds(428, 204, 211, 33);
		panel_1.add(opt2);
		opt2.setColumns(10);
		
		//text field for option 3
		opt3 = new JTextField();
		opt3.setBounds(134, 248, 211, 33);
		panel_1.add(opt3);
		opt3.setColumns(10);
		
		//text field for option 4
		opt4 = new JTextField();
		opt4.setBounds(428, 248, 211, 33);
		panel_1.add(opt4);
		opt4.setColumns(10);
		
		//text field correct answer
		ans = new JTextField();
		ans.setBounds(308, 291, 211, 28);
		panel_1.add(ans);
		ans.setColumns(10);
		
		//adding background image
		JLabel backgroundlabel = new JLabel("");
		backgroundlabel.setIcon(new ImageIcon("C:\\Users\\Ifra Maryam\\Desktop\\SCAD\\search.jpg"));
		backgroundlabel.setBounds(0, 11, 1054,650);
	    //getContentPane().add(backgroundlabel, BorderLayout.SOUTH);
		contentPane.add(backgroundlabel);
		
		//Button for searching a question
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_id=question_id.getText();
				//checking entered id
				if(get_id.equals(""))
				{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER ID FIRST");
					question.setText("");
	 				opt1.setText("");
	 				opt2.setText("");
	 				opt3.setText("");
	 				opt4.setText("");
	 				ans.setText("");
				}
				else
				{
					int found1=0,check=0;
				for(int i=0; i < get_id.length()&&check==0; i++) {
					
				     Boolean flag = Character.isDigit(get_id.charAt(i));
				     if(flag) {
				    	 
				    	 connection();
				    	 try
				    	 {
				    		 connection();
				    		 String query="SELECT COUNT(q_id) FROM Question WHERE q_id='"+get_id+"'";
				 			String Query="SELECT q_description, option_1, option_2, option_3, option_4, correct_answer FROM Question WHERE q_id='"+get_id+"'";
				 			rs=stmt.executeQuery(query);
				 			int count;
				 			while(rs.next()&&found1==0)
				 			{
				 				
				 				count=rs.getInt(1);
				 				if(count!=0)
				 				{
				 					rs=stmt.executeQuery(Query);
				 					while(rs.next())
						 			{
						 				question.setText(rs.getString(1));
						 				opt1.setText(rs.getString(2));
						 				opt2.setText(rs.getString(3));
						 				opt3.setText(rs.getString(4));
						 				opt4.setText(rs.getString(5));
						 				ans.setText(rs.getString(6));
						 			}
				 				}
				 				else
				 				{
				 					found1=1;
				 					JOptionPane.showMessageDialog(null, "ID DO NOT EXIST");
				 					question_id.setText("");
				 					question.setText("");
					 				opt1.setText("");
					 				opt2.setText("");
					 				opt3.setText("");
					 				opt4.setText("");
					 				ans.setText("");
				 				}
				 			}
				    	 }
						 catch(SQLException e)
				    	 {
							 e.printStackTrace();
				    	 }
				    	 
				     }
				     else {
				    	 check=1;
				    	 JOptionPane.showMessageDialog(null, "PLEASE ENTER A NUMBER");
				    	 question.setText("");
			 				opt1.setText("");
			 				opt2.setText("");
			 				opt3.setText("");
			 				opt4.setText("");
			 				ans.setText("");
				    	 
				     }
				    }


				}
				
			}
		});
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setFont(new Font("Calisto MT", Font.BOLD, 14));
		btnSearch.setBackground(new Color(255, 153, 204));
		btnSearch.setBounds(156, 358, 95, 34);
		panel_1.add(btnSearch);
		
		//Button for clearing text fields
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				question_id.setText("");
				question.setText("");
				opt1.setText("");
				opt2.setText("");
				opt3.setText("");
				opt4.setText("");
				ans.setText("");
			}
		});
		btnClear.setForeground(Color.BLACK);
		btnClear.setFont(new Font("Calisto MT", Font.BOLD, 14));
		btnClear.setBackground(new Color(255, 153, 204));
		btnClear.setBounds(294, 358, 95, 34);
		panel_1.add(btnClear);
		
		//Button for going to the previous interface
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}
		});
		btnBack.setForeground(Color.BLACK);
		btnBack.setFont(new Font("Calisto MT", Font.BOLD, 14));
		btnBack.setBackground(new Color(255, 153, 204));
		btnBack.setBounds(424, 358, 95, 34);
		panel_1.add(btnBack);
		
		//Button for deleting a question
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				question_id.setEditable(true);
				update_id=question_id.getText();
				//checking entered id
				if(update_id.equals(""))
				{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER ID FIRST");
				}
				else
				{
					for(int i=0; i < update_id.length(); i++)
					{
				         Boolean flag = Character.isDigit(update_id.charAt(i));
				         if(flag) {
				        	 connection(); 
				        	 try
				        	 {
				        		 //deleting from db
				        		 String query=("DELETE FROM Question WHERE q_id=?");
				        		 prep = conn.prepareStatement(query);
				        		 prep.setString(1, update_id);
				        		 prep.executeUpdate();
				        		 System.out.println("SUCCESSFULLY Edited IN QUESTION TABLE");
				        		 JOptionPane.showMessageDialog(null, "Question DELETED Successfully");
				        		 question_id.setText("");
				        		 question.setText("");
				        		 opt1.setText("");
				        		 opt2.setText("");
				        		 opt3.setText("");
				        		 opt4.setText("");
				        		 ans.setText("");
				        		 }
				        	 catch(SQLException e1)
				        	 {
				        		 e1.printStackTrace();				        		 
				        	}
				         }
				         else
				         {
				        	 JOptionPane.showMessageDialog(null, "PLEASE ENTER A NUMBER");
				         }
				    }
				}

			}
		});
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Calisto MT", Font.BOLD, 14));
		btnDelete.setBackground(new Color(255, 153, 204));
		btnDelete.setBounds(156, 414, 95, 34);
		panel_1.add(btnDelete);
		
		//Button for editing a question
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				question_id.setEditable(false);
				update_id=question_id.getText();
				update_ques=question.getText();
				update_opt1=opt1.getText();
				update_opt2=opt2.getText();
				update_opt3=opt3.getText();
				update_opt4=opt4.getText();
				update_ans=ans.getText();
				
				
				if(update_id.equals(""))
				{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER ID FIRST");
				}
				else
				{
					connection();
				try
				{
					//Editing in database
					String query=("UPDATE Question SET q_description=?, option_1 =?, option_2=?,option_3=?,option_4=?,correct_answer=? WHERE q_id=?");
					prep = conn.prepareStatement(query);
					prep.setString(1, update_ques);
					prep.setString(2, update_opt1);
					prep.setString(3, update_opt2);
					prep.setString(4, update_opt3);
					prep.setString(5, update_opt4);
					prep.setString(6, update_ans);
					prep.setString(7, update_id);
					prep.executeUpdate();
					System.out.println("SUCCESSFULLY Edited IN QUESTION TABLE");
					JOptionPane.showMessageDialog(null, "Question Edited Successfully");
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
			}
			}
		});
		btnEdit.setForeground(Color.BLACK);
		btnEdit.setFont(new Font("Calisto MT", Font.BOLD, 14));
		btnEdit.setBackground(new Color(255, 153, 204));
		btnEdit.setBounds(424, 414, 95, 34);
		panel_1.add(btnEdit);
	
	}

	public void connection()
	{ /*
	connection of database with this program
	*/
		try
		{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		System.out.println("COMMMAND COMPLETED SUCCESSFULY IN SEARCH PAPER");
		String connstring="jdbc:sqlserver://IFRA-MARYAM\\MSSQLSERVER;"+"databaseName=Online Examination System;integratedSecurity=true;";
		conn=DriverManager.getConnection(connstring);
		 stmt=conn.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Extracting values from database
	//function returning question id
	String tell_id()
	{
		return ques_id;
	}
}
//end of class

//////////////////