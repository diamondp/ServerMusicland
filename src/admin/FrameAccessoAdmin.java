package admin;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connessione.Connessione;


public class FrameAccessoAdmin extends JFrame {
	
	public FrameAccessoAdmin(){
		JPanel first = this.createFormPanel();
		this.add(first, BorderLayout.NORTH);
		JPanel second = this.createButtonPanel();
		this.add(second, BorderLayout.SOUTH);
		this.createTheFrame();
	}
	
	public void createTheFrame(){
		this.setTitle("ACCEDI");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	public JPanel createFormPanel(){
		JPanel panel = new JPanel();
		//crea campi da compilare con email e password
		panel.setLayout(new GridLayout(2, 2));
		labelEmail = new JLabel("Email");
		labelPassword = new JLabel("Password");
		textEmail = new JTextField(10);
		textPassword = new JTextField(10);
		panel.add(labelEmail);
		panel.add(textEmail);
		panel.add(labelPassword);
		panel.add(textPassword);
		return panel;
	}
	
	public JPanel createButtonPanel(){
		JPanel panel = new JPanel();
		//crea campo contenente il bottone di accesso
		buttonLogin = new JButton("LOGIN");
		class Login implements ActionListener{
			public void actionPerformed(ActionEvent event){
				
				if((textEmail.getText().equals("admin")) && (textPassword.getText().equals("admin"))){
					//System.out.println("Sono qui");
					//frameMenuAdmin = new FrameMenuAdmin();
					//frameMenuAdmin.setVisible(true);
					Connessione frameConnessione = new Connessione();
					//frameConnessione = new Connessione();
					frameConnessione.setVisible(true);
					setVisible(false);
				}
				else{
					System.out.println("Sbagliat");
				}
			}
		}
		listenerButtonLogin = new Login();
		buttonLogin.addActionListener(listenerButtonLogin);
		panel.add(buttonLogin);
		return panel;
	}
	
	private JButton buttonLogin;
	private JLabel labelEmail;
	private JLabel labelPassword;
	private JTextField textEmail;
	private JTextField textPassword;
	private ActionListener listenerButtonLogin;

}
