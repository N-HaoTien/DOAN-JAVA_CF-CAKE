package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.TaiKhoanDAO;
import entities.NhanVien;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class frmDoiMatKhau extends JFrame {

	private JPanel contentPane;
	private JButton btnSignUp;
	private JButton btnClear;
	private JPasswordField txtPassword;
	private JPasswordField txtCongifPass;
	private JPasswordField txtConfigpass;
	public Integer IDNV = frmLogin.IDNV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmDoiMatKhau frame = new frmDoiMatKhau();
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
		txtConfigpass.setText("");
	}
	public boolean Validate() {
		String Pass = new String(txtPassword.getPassword());
		String ConfigPass = new String(txtCongifPass.getPassword());
		String NhapLai = new String(txtConfigpass.getPassword());
		if( Pass.isEmpty() && ConfigPass.isEmpty() && NhapLai.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Create the frame.
	 */
	public frmDoiMatKhau() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				NhanVien nv = new TaiKhoanDAO().findbyID(IDNV);
				txtPassword.setText(nv.getMatKhau());
			}
			@Override
			public void windowClosing(WindowEvent e) {
				MenuBar frm = new MenuBar();
				frm.setVisible(true);
				setVisible(false);
			}
			
		});
		setTitle("Đổi Mật Khẩu");
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

		JLabel lblNewLabel_1 = new JLabel("Mật Khẩu");
		lblNewLabel_1
				.setFont(new Font("Arial", lblNewLabel_1.getFont().getStyle(), lblNewLabel_1.getFont().getSize() + 10));
		lblNewLabel_1.setBounds(37, 105, 243, 28);
		panel.add(lblNewLabel_1);

		JLabel lblConfigpass = new JLabel("Nhập Mật Khẩu Mới");
		lblConfigpass
				.setFont(new Font("Arial", lblConfigpass.getFont().getStyle(), lblConfigpass.getFont().getSize() + 10));
		lblConfigpass.setBounds(37, 164, 243, 28);
		panel.add(lblConfigpass);

		btnSignUp = new JButton("Đổi Mật Khẩu");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaiKhoanDAO TK = new TaiKhoanDAO();
				String Pass = new String(txtPassword.getPassword());
				String ConfigPass = new String(txtCongifPass.getPassword());
				String NhapLai = new String(txtConfigpass.getPassword());
				if(Validate() == false) {
					JOptionPane.showMessageDialog(null, "Xin Mời Nhập Đủ Thông Tin");
				}else {
						
						if (!NhapLai.equals(ConfigPass)) {
							JOptionPane.showMessageDialog(null, "Mật Khẩu Nhập Lại Không Giống Nhau !");
							txtConfigpass.setText("");
							txtCongifPass.setText("");
						} else {
							
							TK.Update(IDNV, ConfigPass);
							JOptionPane.showMessageDialog(null, "Đổi Mật Khẩu Thành Công");
							Clear();
							MenuBar frm = new MenuBar();
							frm.setVisible(true);
							dispose();
							setVisible(false);
						}
					}
				
			}
		});
		btnSignUp.setFont(new Font("Arial", btnSignUp.getFont().getStyle(), btnSignUp.getFont().getSize() + 8));
		btnSignUp.setBounds(130, 332, 172, 34);
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
		txtPassword.setBounds(321, 104, 217, 27);
		panel.add(txtPassword);

		txtCongifPass = new JPasswordField();
		txtCongifPass
				.setFont(new Font("Arial", txtCongifPass.getFont().getStyle(), txtCongifPass.getFont().getSize() + 10));
		txtCongifPass.setBounds(321, 164, 217, 27);
		panel.add(txtCongifPass);

		JLabel lblNhpLiMt = new JLabel("Nhập Lại Mật Khẩu Mới");
		lblNhpLiMt.setFont(new Font("Arial", lblNhpLiMt.getFont().getStyle(), lblNhpLiMt.getFont().getSize() + 10));
		lblNhpLiMt.setBounds(37, 213, 274, 28);
		panel.add(lblNhpLiMt);

		txtConfigpass = new JPasswordField();
		txtConfigpass
				.setFont(new Font("Arial", txtConfigpass.getFont().getStyle(), txtConfigpass.getFont().getSize() + 10));
		txtConfigpass.setBounds(321, 213, 217, 27);
		panel.add(txtConfigpass);
	}
}
