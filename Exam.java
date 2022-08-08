/*
 class name=Exam
 programmer's name=Zainab Tanveer AND Qurat-ul-ain
 Date=7-6-2021
 purpose of class: student select options of questions and marks calculated accordingly
  */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.JTextField;
public class Exam extends JFrame 
{
String userid,fname,lname;
String coursecode,coursename,paperdate;
String questionid="";
String pid;//used for paperid
String answer;
int min,sec=0;//used for timer 
int marks=0;//increment marks when answer is checked
Statement stmt;
JLabel question;
JRadioButton option1,option2,option3,option4;
Timer time;
ButtonGroup bgroup;
JLabel labelmarks;
JLabel queslabel;
JButton next;
JLabel usernamelabel;
JLabel totalmarks;
private JPanel contentPane;
private JTextArea quesdesc;
String queslimit;

	/**
	 * Create the frame.
	 */
	public Exam(String user,String ccode)
	{
		 super("Online examination system");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(250, 100, 1070, 595);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			userid=user;
			coursecode=ccode;
			System.out.println(userid+coursecode);
			

			/*orange panel*/
			JPanel oespanel = new JPanel();
			oespanel.setBackground(new Color(255,165,0,220));
			oespanel.setBounds(0, 0, 1054, 85);
			contentPane.add(oespanel);
			oespanel.setLayout(null);
			
			
			/*date format */
			
			extractdateofpaper();
			JLabel lblDate = new JLabel("DATE");
			lblDate.setFont(new Font("Calisto MT", Font.BOLD, 20));
			lblDate.setBounds(295, 23, 105, 51);
			oespanel.add(lblDate);
			
			JLabel Labeldate = new JLabel("LABELDATE");
			Labeldate.setFont(new Font("Calisto MT", Font.BOLD, 20));
			Labeldate.setBounds(395, 23, 158, 51);
			Labeldate.setText(paperdate);
			oespanel.add(Labeldate);
			
			
			JLabel lblNewLabel = new JLabel("WELCOME");
			lblNewLabel.setFont(new Font("Calisto MT", Font.BOLD, 30));
			lblNewLabel.setBounds(33, 23, 202, 51);
			oespanel.add(lblNewLabel);
			
			
			/* timer labels */
			JLabel lblTotalTime = new JLabel("TOTAL TIME");
			lblTotalTime.setFont(new Font("Calisto MT", Font.BOLD, 20));
			lblTotalTime.setBounds(643, 0, 149, 51);
			oespanel.add(lblTotalTime);
			
			JLabel lblMin = new JLabel("2 Min");
			lblMin.setFont(new Font("Calisto MT", Font.BOLD, 20));
			lblMin.setBounds(788, 0, 149, 51);
			oespanel.add(lblMin);
			
			JLabel lblTimeTaken = new JLabel("TIME TAKEN\r\n");
			lblTimeTaken.setFont(new Font("Calisto MT", Font.BOLD, 20));
			lblTimeTaken.setBounds(643, 40, 149, 45);
			oespanel.add(lblTimeTaken);
			
			/* timer */
			JLabel min1 = new JLabel("00");
			min1.setForeground(new Color(255, 0, 0));
			min1.setFont(new Font("Calisto MT", Font.BOLD, 20));
			min1.setBounds(788, 44, 29, 37);
			oespanel.add(min1);
			
			JLabel sec1 = new JLabel("00");
			sec1.setForeground(Color.RED);
			sec1.setFont(new Font("Calisto MT", Font.BOLD, 20));
			sec1.setBounds(814, 44, 29, 37);
			oespanel.add(sec1);
			
			setLocationRelativeTo(this);
			
			time =new Timer(1000,new ActionListener()
					{

						@Override
						public void actionPerformed(ActionEvent e)
						{
							
							min1.setText(String.valueOf(min));
							sec1.setText(String.valueOf(sec));
							if(sec==60)
							{
							min++;	
							if(min==2)
							{
								time.stop(); //when time completes paper will automatically submit
								answercheck();
								int rid=	getresultid();
								char grade=submit(rid);
								String tmarks=totalmarks.getText();
								Resultframe rs=new Resultframe(marks,tmarks,grade,userid,coursename);
								rs.setVisible(true);
							}
							}
							sec++;
						}
				
					});
			time.start();
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 204, 255));
			panel.setBounds(21, 151, 295, 381);
			panel.setLayout(null);
			contentPane.add(panel);
			
			
			JLabel lblNewLabel_1 = new JLabel("Username");
			lblNewLabel_1.setForeground(Color.BLACK);
			lblNewLabel_1.setFont(new Font("Calisto MT", Font.BOLD, 16));
			lblNewLabel_1.setBounds(10, 24, 133, 31);
			panel.add(lblNewLabel_1);
			
			
			usernamelabel = new JLabel(userid);
			usernamelabel.setForeground(Color.BLACK);
			usernamelabel.setFont(new Font("Calisto MT", Font.BOLD, 16));
			usernamelabel.setBounds(139, 24, 133, 31);
			panel.add(usernamelabel);
			
			
			JLabel lblName = new JLabel("Name");
			lblName.setForeground(Color.BLACK);
			lblName.setFont(new Font("Calisto MT", Font.BOLD, 16));
			lblName.setBounds(10, 66, 133, 31);
			panel.add(lblName);
			
			
			JLabel lblTotalQuestions = new JLabel("Paper id");
			lblTotalQuestions.setForeground(Color.BLACK);
			lblTotalQuestions.setFont(new Font("Calisto MT", Font.BOLD, 16));
			lblTotalQuestions.setBounds(10, 108, 133, 31);
			panel.add(lblTotalQuestions);
			
			
			getnameofuser();
			JLabel lblSalman = new JLabel(fname+" "+lname);
			lblSalman.setForeground(Color.BLACK);
			lblSalman.setFont(new Font("Calisto MT", Font.BOLD, 16));
			lblSalman.setBounds(139, 66, 159, 31);
			panel.add(lblSalman);
			
			
			JLabel label_4 = new JLabel("00");
			label_4.setForeground(Color.BLACK);
			label_4.setFont(new Font("Calisto MT", Font.BOLD, 16));
			label_4.setBounds(139, 108, 133, 31);
			label_4.setText(pid);
			panel.add(label_4);
			
			/*coursename function call*/
			getcoursename();
			JLabel lblNa = new JLabel("Course Name");
			lblNa.setForeground(Color.BLACK);
			lblNa.setFont(new Font("Calisto MT", Font.BOLD, 16));
			lblNa.setBounds(10, 150, 133, 31);
			panel.add(lblNa);
			
			
			JLabel courselabel = new JLabel("kch ni");
			courselabel.setForeground(Color.BLACK);
			courselabel.setFont(new Font("Calisto MT", Font.BOLD, 16));
			courselabel.setBounds(139, 150, 133, 31);
			courselabel.setText(coursename);
			panel.add(courselabel);
			
			
			JLabel lblQuestionNumber = new JLabel("Question Number");
			lblQuestionNumber.setForeground(Color.BLACK);
			lblQuestionNumber.setFont(new Font("Calisto MT", Font.BOLD, 16));
			lblQuestionNumber.setBounds(10, 197, 133, 31);
			panel.add(lblQuestionNumber);
			
			
			queslabel = new JLabel("0");
			queslabel.setForeground(Color.BLACK);
			queslabel.setFont(new Font("Calisto MT", Font.BOLD, 16));
			queslabel.setBounds(159, 197, 133, 31);
			queslabel.setText(questionid);
			panel.add(queslabel);
			
			JLabel lblYourMarks = new JLabel("Your Marks");
			lblYourMarks.setForeground(Color.BLACK);
			lblYourMarks.setFont(new Font("Calisto MT", Font.BOLD, 16));
			lblYourMarks.setBounds(10, 245, 133, 31);
			panel.add(lblYourMarks);
			
			
			labelmarks = new JLabel("0");
			labelmarks.setForeground(Color.BLACK);
			labelmarks.setFont(new Font("Calisto MT", Font.BOLD, 16));
			labelmarks.setBounds(159, 245, 133, 31);
			panel.add(labelmarks);
			
			
			JLabel lblTotalMarks = new JLabel("Total Marks");
			lblTotalMarks.setForeground(Color.BLACK);
			lblTotalMarks.setFont(new Font("Calisto MT", Font.BOLD, 16));
			lblTotalMarks.setBounds(10, 287, 133, 31);
			panel.add(lblTotalMarks);
			
			
			totalmarks = new JLabel("20");
			totalmarks.setForeground(Color.BLACK);
			totalmarks.setFont(new Font("Calisto MT", Font.BOLD, 16));
			totalmarks.setBounds(159, 287, 133, 31);
			panel.add(totalmarks);
			
			
			
			question = new JLabel("Question "+questionid);
			question.setForeground(new Color(34, 139, 34));
			question.setFont(new Font("Calisto MT", Font.BOLD, 24));
			question.setBounds(337, 96, 126, 45);
			contentPane.add(question);
			
			bgroup=new ButtonGroup();
			option1 = new JRadioButton("New radio button");
			option1.setFont(new Font("Calisto MT", Font.BOLD, 20));
			option1.setBounds(333, 206, 507, 45);
			bgroup.add(option1);
			contentPane.add(option1);
			
			option2 = new JRadioButton("New radio button");
			option2.setFont(new Font("Calisto MT", Font.BOLD, 20));
			option2.setBounds(333, 278, 507, 45);
			bgroup.add(option2);
			contentPane.add(option2);
			
			
			option3 = new JRadioButton("New radio button");
			option3.setFont(new Font("Calisto MT", Font.BOLD, 20));
			option3.setBounds(333, 352, 507, 45);
			bgroup.add(option3);
			contentPane.add(option3);
			
			
			option4 = new JRadioButton("New radio button");
			option4.setFont(new Font("Calisto MT", Font.BOLD, 20));
			option4.setBounds(333, 421, 507, 45);
			bgroup.add(option4);
			contentPane.add(option4);
			

			quesdesc = new JTextArea();
			quesdesc.setEditable(false);
			quesdesc.setFont(new Font("Calisto MT", Font.BOLD, 20));
			quesdesc.setBounds(333, 137, 674, 50);
			contentPane.add(quesdesc);
			quesdesc.setColumns(10);
			getpaper();
			
			
			next = new JButton("NEXT");
			next.setForeground(Color.BLACK);
			next.setBackground(new Color(204, 255, 102));
			next.addActionListener(new ActionListener()
			{
				/* when next button click new question will arrive and answer of last question will be checked */
				public void actionPerformed(ActionEvent e)
				{
					answercheck();
					question();
					
				}
			});
			next.setFont(new Font("Calisto MT", Font.BOLD, 20));
			next.setBounds(374, 487, 112, 45);
			contentPane.add(next);
			
			JButton btnSubmit = new JButton("SUBMIT");
			btnSubmit.setForeground(Color.BLACK);
			btnSubmit.setBackground(new Color(204, 255, 102));
			btnSubmit.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					int rid=0;
					char grade='c';
					time.stop();
					String tmarks=totalmarks.getText();
					int a=JOptionPane.showConfirmDialog(null, "Do you really want to submit Paper?","Select",JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
					rid=getresultid();
					grade=submit(rid);
					Resultframe rs=new Resultframe(marks,tmarks,grade,userid,coursename);
					rs.setVisible(true);
					
					}
				}
			});
			
			
			btnSubmit.setFont(new Font("Calisto MT", Font.BOLD, 20));
			btnSubmit.setBounds(663, 487, 112, 45);
			contentPane.add(btnSubmit);
			
		
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBackground(new Color(0, 0, 0));
			panel_1.setBounds(10, 141, 315, 400);
			contentPane.add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Farhat ul ain\\Documents\\picture_2.jpg"));
		lblNewLabel_3.setBounds(10, 85, 1056, 490);
		contentPane.add(lblNewLabel_3);
		
		
		
		
	}
	
	public void getcoursename()
	{ /* find course name for selected coursecode*/
		connection();
		String query="SELECT course_name FROM Course WHERE course_code='"+coursecode+"'";
		try 
		{
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				coursename=rs.getString(1);
			}
			
		}
		catch (SQLException e1)
		{
			
			e1.printStackTrace();
		}
		
	}
	public void extractdateofpaper()
	{
		/* find course name for selected coursecode */
		connection();
		String query="SELECT paper_id,paper_date FROM Paper WHERE courseCode='"+coursecode+"'";
		try
		{
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				pid=rs.getString(1);
				paperdate=rs.getString(2);
			}
			
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		System.out.println(pid);
	}
	
	public void getnameofuser()
	{
		/*   this function will get the full name of the user who logged in
		  */
			String query="SELECT first_name,last_name FROM Student WHERE username='"+userid+"'";
			try {
				ResultSet rs=stmt.executeQuery(query);
				while(rs.next())
				{
					fname=rs.getString(1);
					lname=rs.getString(2);
				}
				
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	
	public void getpaper()
	{
		/* get information of paper */
		connection();
		String query="SELECT TOP 1 * FROM Question WHERE paper_id='"+pid+"'";
		try {
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				questionid=rs.getString(1);
				queslabel.setText(rs.getString(1));
			    question.setText("Question "+rs.getString(1));
			    quesdesc.setText(rs.getString(3));
				option1.setText(rs.getString(4));
				option2.setText(rs.getString(5));
				option3.setText(rs.getString(6));
				option4.setText(rs.getString(7));
				answer=rs.getString(8);
			}
			
		}
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getlastquestion();
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
	
	public void answercheck()
	{
		/* this function will check answer od each question*/
		String stdanswer = null;
		if(option1.isSelected())
		{
			stdanswer=option1.getText();
		}
		else if(option2.isSelected())
		{
			stdanswer=option2.getText();
		}
		else if(option3.isSelected())
		{
			stdanswer=option3.getText();
		}
		else if(option4.isSelected())
		{
			stdanswer=option4.getText();
		}
		if(stdanswer.equals(answer))
		{
			marks=marks+4; //each question is of  4 marks
			String mark=String.valueOf(marks);
			labelmarks.setText(mark);
			
		}
		
		
		/*question number change*/
		int quesid=Integer.parseInt(questionid);
		quesid=quesid+1;
		questionid=String.valueOf(quesid);
		/*last question hide next button*/
		if(questionid.equals(queslimit))
		{
			next.setVisible(false);
		}
	}
	public void getlastquestion()
	{
		connection();
		String query="SELECT q_id FROM Question WHERE paper_id='"+pid+"'";
		try {
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				queslimit=rs.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void question()
	{
		connection();
		String query="SELECT * FROM Question WHERE paper_id='"+pid+"' AND q_id='"+questionid+"'";
		try {
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				queslabel.setText(rs.getString(1));
				question.setText("Question "+rs.getString(1));
				quesdesc.setText(rs.getString(3));
				option1.setText(rs.getString(4));
				option2.setText(rs.getString(5));
				option3.setText(rs.getString(6));
				option4.setText(rs.getString(7));
				answer=rs.getString(8);
			}
			
		}
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public char submit(int rid)
	{
		/* after submission result will be stored in result database table */
		String usernameString=usernamelabel.getText();
		answercheck();
		int resultid=rid;
		float totalmarks=20;
		float omarks=marks;
		char grade='F';
		if(omarks>15 && omarks<=20)
		{
			grade='A';
		}
		else if (omarks>10 && omarks<=15)
		{
			grade='B';
		}
		else if (omarks>5 && omarks<=10)
		{
			grade='C';
		}
		else if (omarks>0 && omarks<=5)
		{
			grade='D';
		}
		
		
			connection();
			String query="INSERT INTO Result VALUES ("+resultid+","+omarks+","+totalmarks+",'"+grade+"','"+userid+"')";
			try
			{
				stmt.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return grade;
	}
	public int getresultid()
	{
		int rid = 0;
		connection();
		String query="SELECT result_id FROM Result";
		try {
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				rid=rs.getInt(1);
			}
			
		}
		
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rid++;
		return rid;
	}
}









