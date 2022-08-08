import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TeacherGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherGUI frame = new TeacherGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TeacherGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 600, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("You Login as Teacher");
		lblNewLabel_1.setBounds(370, 0, 219, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("(Logout)");
		lblNewLabel_2.setBounds(510, 5, 50, 14);
		lblNewLabel_2.setForeground(Color.RED);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 255, 0,50));
		panel.setBounds(145, 43, 310, 331);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Add Question");
		btnNewButton_1.setBackground(new Color(255, 153, 255));
		btnNewButton_1.setForeground(new Color(0, 255, 0));
		btnNewButton_1.setBounds(89, 45, 132, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Create Paper");
		btnNewButton.setForeground(new Color(0, 255, 0));
		btnNewButton.setBackground(new Color(255, 153, 255));
		btnNewButton.setBounds(89, 11, 132, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Search Question");
		btnNewButton_2.setForeground(new Color(0, 255, 0));
		btnNewButton_2.setBackground(new Color(255, 153, 255));
		btnNewButton_2.setBounds(89, 79, 134, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Delete Question");
		btnNewButton_3.setBackground(new Color(255, 153, 255));
		btnNewButton_3.setForeground(new Color(0, 255, 0));
		btnNewButton_3.setBounds(89, 113, 132, 23);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Update Question");
		btnNewButton_4.setForeground(new Color(0, 255, 0));
		btnNewButton_4.setBackground(new Color(255, 153, 255));
		btnNewButton_4.setBounds(89, 147, 132, 23);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("All Question");
		btnNewButton_5.setBackground(new Color(255, 153, 255));
		btnNewButton_5.setForeground(new Color(0, 255, 0));
		btnNewButton_5.setBounds(89, 177, 132, 23);
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Generate Result");
		btnNewButton_6.setForeground(new Color(0, 255, 0));
		btnNewButton_6.setBackground(new Color(255, 153, 255));
		btnNewButton_6.setBounds(89, 211, 132, 23);
		panel.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Generate Attendence");
		btnNewButton_7.setBackground(new Color(255, 153, 255));
		btnNewButton_7.setForeground(new Color(0, 255, 0));
		btnNewButton_7.setBounds(73, 248, 177, 23);
		panel.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Exit");
		btnNewButton_8.setForeground(new Color(0, 255, 0));
		btnNewButton_8.setBackground(new Color(255, 153, 255));
		btnNewButton_8.setBounds(89, 278, 132, 23);
		panel.add(btnNewButton_8);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(10, 0, 783, 436);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Farhat ul ain\\Documents\\6th Semester\\omr1.jpg"));
	}
}
