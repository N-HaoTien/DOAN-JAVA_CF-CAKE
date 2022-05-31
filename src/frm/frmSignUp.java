package frm;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.TaiKhoanDAO;
import entities.NhanVien;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class frmSignUp extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtEmail;
	private JButton btnSignUp;
	private JButton btnClear;
	private JPasswordField txtPassword;
	private JPasswordField txtCongifPass;
	private JLabel lblNewLabel_1_2;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmSignUp frame = new frmSignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void Clear() {
		txtPassword.setText("");
		txtCongifPass.setText("");
		txtEmail.setText("");
		txtName.setText("");
		txtUserName.setText("");
	}
	/**
	 * Create the frame.
	 */
	public frmSignUp() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frmLogin frm = new frmLogin();
				frm.setVisible(true);
				setVisible(false);
			}
		});
		setTitle("SignUp");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		this.setResizable(false);
		Toolkit tool = getToolkit();
		Dimension size = tool.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 5, 586, 408);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTnngNhp = new JLabel("Tên Đăng Nhập");
		lblTnngNhp.setFont(new Font("Arial", lblTnngNhp.getFont().getStyle(), lblTnngNhp.getFont().getSize() + 10));
		lblTnngNhp.setBounds(88, 35, 173, 28);
		panel.add(lblTnngNhp);

		JLabel lblNewLabel_1 = new JLabel("Mật Khẩu");
		lblNewLabel_1
				.setFont(new Font("Arial", lblNewLabel_1.getFont().getStyle(), lblNewLabel_1.getFont().getSize() + 10));
		lblNewLabel_1.setBounds(88, 93, 173, 28);
		panel.add(lblNewLabel_1);

		JLabel lblConfigpass = new JLabel("Nhập Lại MK");
		lblConfigpass
				.setFont(new Font("Arial", lblConfigpass.getFont().getStyle(), lblConfigpass.getFont().getSize() + 10));
		lblConfigpass.setBounds(88, 152, 173, 28);
		panel.add(lblConfigpass);

		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setFont(
				new Font("Arial", lblNewLabel_1_1.getFont().getStyle(), lblNewLabel_1_1.getFont().getSize() + 10));
		lblNewLabel_1_1.setBounds(88, 258, 173, 28);
		panel.add(lblNewLabel_1_1);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Arial", txtUserName.getFont().getStyle(), txtUserName.getFont().getSize() + 10));
		txtUserName.setBounds(271, 36, 217, 27);
		panel.add(txtUserName);
		txtUserName.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", txtEmail.getFont().getStyle(), txtEmail.getFont().getSize() + 10));
		txtEmail.setColumns(10);
		txtEmail.setBounds(271, 259, 217, 27);
		panel.add(txtEmail);

		btnSignUp = new JButton("Đăng Ký");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaiKhoanDAO TK = new TaiKhoanDAO();
				NhanVien nv = new NhanVien();
				nv.setTaiKhoan(txtUserName.getText());
				nv.setMatKhau(String.valueOf(txtPassword.getPassword()));
				nv.setTenNv(txtName.getText());
				nv.setEmail(txtEmail.getText());
				nv.setQuyen(true);
				String Pass = new String(txtPassword.getPassword());
				String ConfigPass = new String(txtCongifPass.getPassword());
				if(txtEmail.getText().isEmpty() && txtName.getText().isEmpty() && txtPassword.getText().isEmpty() && txtUserName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bạn chưa điền đầy đủ thông tin !");
				}
				else {
					if (!Pass.equals(ConfigPass)) {
						JOptionPane.showMessageDialog(null, "Mật Khẩu Nhập Lại Không Giống Nhau !");
						txtPassword.setText("");
						txtCongifPass.setText("");
					} else {
						if (TK.SaveorUpdate(nv)) {
							JOptionPane.showMessageDialog(null, "Tạo Tài Khoản Thành Công !");
							frmLogin frmLogin = new frmLogin();
							frmLogin.setVisible(true);
							dispose();
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Họ và Tên Hoặc Tài Khoản Đã Tồn Tại !");
							Clear();
						}
					}
				}
				
			}
		});
		btnSignUp.setFont(new Font("Arial", btnSignUp.getFont().getStyle(), btnSignUp.getFont().getSize() + 8));
		btnSignUp.setBounds(146, 332, 125, 34);
		panel.add(btnSignUp);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
		});
		btnClear.setFont(new Font("Arial", btnClear.getFont().getStyle(), btnClear.getFont().getSize() + 8));
		btnClear.setBounds(328, 332, 125, 34);
		panel.add(btnClear);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", txtPassword.getFont().getStyle(), txtPassword.getFont().getSize() + 10));
		txtPassword.setBounds(271, 93, 217, 27);
		panel.add(txtPassword);

		txtCongifPass = new JPasswordField();
		txtCongifPass
				.setFont(new Font("Arial", txtCongifPass.getFont().getStyle(), txtCongifPass.getFont().getSize() + 10));
		txtCongifPass.setBounds(271, 153, 217, 27);
		panel.add(txtCongifPass);

		lblNewLabel_1_2 = new JLabel("H\u1ECD V\u00E0 T\u00EAn");
		lblNewLabel_1_2.setFont(
				new Font("Arial", lblNewLabel_1_2.getFont().getStyle(), lblNewLabel_1_2.getFont().getSize() + 10));
		lblNewLabel_1_2.setBounds(88, 204, 173, 28);
		panel.add(lblNewLabel_1_2);

		txtName = new JTextField();
		txtName.setFont(new Font("Arial", txtName.getFont().getStyle(), txtName.getFont().getSize() + 10));
		txtName.setColumns(10);
		txtName.setBounds(271, 205, 217, 27);
		panel.add(txtName);
	}

}
