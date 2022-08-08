

/*
class name=Resultframe

programmer's name=Zainab Tanveer AND Qurat-ul-ain
Date=7-6-2021
purpose of class: Result will be generated 
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Resultframe extends JFrame {

	private JPanel contentPane;
	
	public Resultframe(int marks, String totalmarks, char grade, String userid, String coursename) 
	{
		super("Online examination system Result");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 150, 531, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		panel.setBounds(0, 0, 515, 432);
		contentPane.add(panel);
		
		
		JLabel res=new JLabel("RESULT");
		res.setFont(new Font("Calisto MT", Font.BOLD, 30));
		res.setBounds(160, 0, 150, 100);
		res.setForeground(new Color(225,6,0));
		panel.add(res);
		
		JLabel username=new JLabel("Username");
		username.setFont(new Font("Calisto MT", Font.BOLD, 20));
		username.setBounds(80, 40, 100, 100);
		username.setForeground(new Color(255,255,255));
		panel.add(username);
		
		
	
		JLabel username1=new JLabel(userid);
		username1.setFont(new Font("Calisto MT", Font.BOLD, 20));
		username1.setBounds(250, 40, 187, 100);
		username1.setForeground(new Color(255,255,255));
		panel.add(username1);
		
		JLabel cname=new JLabel("Coursename");
		cname.setFont(new Font("Calisto MT", Font.BOLD, 20));
		cname.setBounds(80, 80, 150, 100);
		cname.setForeground(new Color(255,255,255));
		panel.add(cname);
		
	
		JLabel coursename1=new JLabel(coursename);
		coursename1.setFont(new Font("Calisto MT", Font.BOLD, 20));
		coursename1.setBounds(250, 80, 170, 100);
		coursename1.setForeground(new Color(255,255,255));
		panel.add(coursename1);
		
		JLabel totalm=new JLabel("Total Marks");
		totalm.setFont(new Font("Calisto MT", Font.BOLD, 20));
		totalm.setBounds(80, 120, 150, 100);
		totalm.setForeground(new Color(255,255,255));
		panel.add(totalm);
		
		
		JLabel totalm1=new JLabel(totalmarks);
		totalm1.setFont(new Font("Calisto MT", Font.BOLD, 20));
		totalm1.setBounds(250, 120, 150, 100);
		totalm1.setForeground(new Color(255,255,255));
		panel.add(totalm1);
		
		
		JLabel obtainm=new JLabel("Obtained Marks");
		obtainm.setFont(new Font("Calisto MT", Font.BOLD, 20));
		obtainm.setBounds(80, 160, 150, 100);
		obtainm.setForeground(new Color(255,255,255));
		panel.add(obtainm);
		
		
		String mark=String.valueOf(marks);
		JLabel obtainm1=new JLabel(mark);
		obtainm1.setFont(new Font("Calisto MT", Font.BOLD, 20));
		obtainm1.setBounds(250, 160, 150, 100);
		obtainm1.setForeground(new Color(255,255,255));
		panel.add(obtainm1);
		
		
		JLabel graade=new JLabel("Grade");
		graade.setFont(new Font("Calisto MT", Font.BOLD, 20));
		graade.setBounds(80, 200, 150, 100);
		graade.setForeground(new Color(255,255,255));
		panel.add(graade);
		
		
		String grad=String.valueOf(grade);
		JLabel graade1=new JLabel(grad);
		graade1.setFont(new Font("Calisto MT", Font.BOLD, 20));
		graade1.setBounds(250, 200, 150, 100);
		graade1.setForeground(new Color(255,255,0));
		panel.add(graade1);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				studentframe sf=new studentframe (userid);
				sf.setVisible(true);
				
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Calisto MT", Font.BOLD, 16));
		btnNewButton.setBounds(177, 300, 89, 42);
		panel.add(btnNewButton);
		
		
		
	}
}
