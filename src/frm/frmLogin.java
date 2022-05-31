package frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.TaiKhoanDAO;
import entities.NhanVien;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.Color;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class frmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	public static Integer IDNV;
	public static String TenNV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin frame = new frmLogin();
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
	public frmLogin() {
		setTitle("H\u1EC7 Th\u1ED1ng \u0110\u0103ng Nh\u1EADp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 450);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 328, 413);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon("C:\\Users\\Admin\\Downloads\\277454778_502929354703568_8372963507976385781_n.png"));
		lblNewLabel.setBounds(0, 0, 328, 413);
		panel.add(lblNewLabel);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Pass = new String(txtPassword.getPassword());
				String userName = txtUserName.getText();
				NhanVien nv = null;
				nv = new TaiKhoanDAO().Login(userName, Pass);
				if (nv == null) {
					JOptionPane.showMessageDialog(null, "Tài Khoản hoặc Mật Khẩu Sai !");
				} 
				else 
				{
					NhanVien a = new TaiKhoanDAO().find(userName);
					if (a != null) {
						IDNV = a.getIdnv();
						TenNV = a.getTenNv();
					}
					JOptionPane.showMessageDialog(null, "Đăng Nhập Thành Công !");
					MenuBar Main = new MenuBar(); Main.setVisible(true); dispose();
					
					/*
					 * MenuBar Main = new MenuBar(); Main.setVisible(true); dispose();
					 */

				}
			}
		});
		btnLogin.setForeground(Color.RED);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setFont(new Font("Arial", btnLogin.getFont().getStyle(), btnLogin.getFont().getSize() + 8));
		btnLogin.setBounds(383, 227, 160, 42);
		contentPane.add(btnLogin);

		JButton btnClear = new JButton("Đăng Ký");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSignUp frm = new frmSignUp();
				frm.setVisible(true);
				dispose();
			}
		});
		btnClear.setFont(new Font("Arial", btnClear.getFont().getStyle(), btnClear.getFont().getSize() + 8));
		btnClear.setForeground(Color.RED);
		btnClear.setBackground(Color.WHITE);
		btnClear.setBounds(557, 227, 160, 42);
		contentPane.add(btnClear);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Arial", txtUserName.getFont().getStyle(), txtUserName.getFont().getSize() + 8));
		txtUserName.setBounds(520, 88, 179, 26);
		contentPane.add(txtUserName);

		JLabel lblNewLabel_1 = new JLabel("Mật Khẩu");
		lblNewLabel_1
				.setFont(new Font("Arial", lblNewLabel_1.getFont().getStyle(), lblNewLabel_1.getFont().getSize() + 8));
		lblNewLabel_1.setBounds(374, 155, 109, 20);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tài Khoản");
		lblNewLabel_1_1.setFont(
				new Font("Arial", lblNewLabel_1_1.getFont().getStyle(), lblNewLabel_1_1.getFont().getSize() + 8));
		lblNewLabel_1_1.setBounds(373, 91, 137, 20);
		contentPane.add(lblNewLabel_1_1);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", txtPassword.getFont().getStyle(), txtPassword.getFont().getSize() + 8));
		txtPassword.setBounds(520, 149, 179, 26);
		contentPane.add(txtPassword);
	}
}
