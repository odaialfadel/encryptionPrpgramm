import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Home {

	private JFrame frmPt;
	private JTextField txt_text;
	private JTextField txt_shift;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frmPt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ECBCode ecbcode = new ECBCode();

		frmPt = new JFrame();
		frmPt.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/png-clipart-search-encrypt-web-search-engine-encryption-duckduckgo-web-browser-others-blue-logo-thumbnail.png")));
		frmPt.setTitle("Team-PT");
		frmPt.setBounds(100, 100, 845, 471);
		frmPt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPt.getContentPane().setLayout(null);
		
		txt_text = new JTextField();
		txt_text.setBounds(33, 123, 429, 64);
		frmPt.getContentPane().add(txt_text);
		txt_text.setColumns(10);
		
		txt_shift = new JTextField();
		txt_shift.setColumns(10);
		txt_shift.setBounds(567, 123, 76, 64);
		frmPt.getContentPane().add(txt_shift);
		
		
		JButton btnNewButton = new JButton("Verschluesseln");
		btnNewButton.setIcon(new ImageIcon(Home.class.getResource("/img/EncryptMe2.png")));
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txt_text.getText().isEmpty() && !txt_shift.getText().isEmpty())
				{
					JOptionPane.showInputDialog("Ihre verschluesselte Nachricht:", ecbcode.encryption(txt_text.getText().toString().toUpperCase(),Integer.parseInt(txt_shift.getText())));
				}
				else JOptionPane.showMessageDialog(frmPt,"Geben Sie bitte gültige Werte!");
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(87, 246, 218, 148);
		frmPt.getContentPane().add(btnNewButton);
		
		JButton btnEntschluesseln = new JButton("Entschluesseln");
		btnEntschluesseln.setIcon(new ImageIcon(Home.class.getResource("/img/EncryptMe.png")));
		btnEntschluesseln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txt_text.getText().isEmpty() && !txt_shift.getText().isEmpty())
				{
					JOptionPane.showInputDialog("Ihre entschluesselte Nachricht:", ecbcode.decryption(txt_text.getText().toString().toUpperCase(),Integer.parseInt(txt_shift.getText())));
				}
				else JOptionPane.showMessageDialog(frmPt,"Geben Sie bitte gültige Werte!");
			}
		});
		btnEntschluesseln.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEntschluesseln.setBounds(494, 246, 226, 148);
		frmPt.getContentPane().add(btnEntschluesseln);
		
		JLabel lblNewLabel = new JLabel("Text bitte eingeben!");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 26));
		lblNewLabel.setBounds(42, 67, 338, 33);
		frmPt.getContentPane().add(lblNewLabel);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Laenge bitte eingeben!");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 26));
		lblNewLabel_1.setBounds(467, 67, 338, 33);
		frmPt.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Home.class.getResource("/img/network-security-in-world-of-encryption-1024x440.jpeg")));
		lblNewLabel_2.setBounds(0, 0, 829, 440);
		frmPt.getContentPane().add(lblNewLabel_2);
	}
	
}
