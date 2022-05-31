package frm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.TaiKhoanDAO;
import java.awt.Color;

public class frmThongTinCaNhan extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenNV;
	private JTextField txtEmail;
	private Integer IDNV = frmLogin.IDNV;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmThongTinCaNhan frame = new frmThongTinCaNhan();
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
	public frmThongTinCaNhan() {
		setBackground(Color.GRAY);
		setTitle("Đổi Mật Khẩu");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		this.setResizable(false);
		Toolkit tool = getToolkit();
		Dimension size = tool.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("T\u00EAn Nh\u00E2n Vi\u00EAn");
		lblNewLabel.setFont(new Font("Arial", lblNewLabel.getFont().getStyle(), lblNewLabel.getFont().getSize() + 10));
		lblNewLabel.setBounds(54, 85, 171, 37);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Arial", lblNewLabel_1.getFont().getStyle(), lblNewLabel_1.getFont().getSize() + 10));
		lblNewLabel_1.setBounds(54, 161, 171, 37);
		getContentPane().add(lblNewLabel_1);
		
		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Arial", txtTenNV.getFont().getStyle(), txtTenNV.getFont().getSize() + 10));
		txtTenNV.setBounds(279, 89, 186, 27);
		getContentPane().add(txtTenNV);
		txtTenNV.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", txtEmail.getFont().getStyle(), txtEmail.getFont().getSize() + 10));
		txtEmail.setColumns(10);
		txtEmail.setBounds(279, 167, 186, 31);
		getContentPane().add(txtEmail);
		
		JButton btnNewButton = new JButton("L\u01B0u Thay \u0110\u1ED5i");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = txtEmail.getText();
				String b = txtTenNV.getText();
				
				new TaiKhoanDAO().Update(IDNV,a,b);
			}
		});
		btnNewButton.setFont(new Font("Arial", btnNewButton.getFont().getStyle(), btnNewButton.getFont().getSize() + 10));
		btnNewButton.setBounds(161, 296, 177, 27);
		getContentPane().add(btnNewButton);
	}

}
