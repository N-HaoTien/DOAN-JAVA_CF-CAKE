package frm;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JTextField;

import DAO.TaiKhoanDAO;
import entities.NhanVien;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class frmPNV extends JPanel {
	private JTextField txtTenNV;
	private JTextField txtEmail;
	private Integer IDNV = frmLogin.IDNV;
	/**
	 * Create the panel.
	 */
	private TaiKhoanDAO TK =new TaiKhoanDAO();
	
	public void ShowThongTin() {
		NhanVien a = TK.findbyID(IDNV);
		txtTenNV.setText(a.getTenNv());
		txtEmail.setText(a.getEmail());
	}
	public frmPNV() {
		setBackground(Color.GRAY);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("T\u00EAn Nh\u00E2n Vi\u00EAn");
		lblNewLabel.setFont(new Font("Arial", lblNewLabel.getFont().getStyle(), lblNewLabel.getFont().getSize() + 10));
		lblNewLabel.setBounds(54, 39, 171, 37);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Arial", lblNewLabel_1.getFont().getStyle(), lblNewLabel_1.getFont().getSize() + 10));
		lblNewLabel_1.setBounds(54, 110, 171, 37);
		add(lblNewLabel_1);
		
		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Arial", txtTenNV.getFont().getStyle(), txtTenNV.getFont().getSize() + 10));
		txtTenNV.setBounds(279, 39, 186, 27);
		add(txtTenNV);
		txtTenNV.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", txtEmail.getFont().getStyle(), txtEmail.getFont().getSize() + 10));
		txtEmail.setColumns(10);
		txtEmail.setBounds(279, 110, 186, 31);
		add(txtEmail);
		
		JButton btnNewButton = new JButton("L\u01B0u Thay \u0110\u1ED5i");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = txtEmail.getText();
				String b = txtTenNV.getText();
				
				TK.Update(IDNV,a,b);
			}
		});
		btnNewButton.setFont(new Font("Arial", btnNewButton.getFont().getStyle(), btnNewButton.getFont().getSize() + 10));
		btnNewButton.setBounds(166, 177, 177, 27);
		add(btnNewButton);
		ShowThongTin();
	}
}
