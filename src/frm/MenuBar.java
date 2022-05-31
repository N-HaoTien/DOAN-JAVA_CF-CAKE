package frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.TaiKhoanDAO;
import entities.LoaiSp;
import entities.NhanVien;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuBar extends JFrame {

	private JPanel contentPane;
	
	public Integer IDNV = frmLogin.IDNV;
	public JMenuItem Sell;
	public JMenuItem Tke;
	JMenuItem mntmNewMenuItem;
	JMenuItem SANPHAM;
	JMenuItem HoaDon;
	JMenuItem LOAISP;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuBar frame = new MenuBar();
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
	public MenuBar() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				NhanVien nv = new TaiKhoanDAO().findbyID(IDNV);
				if(nv.getQuyen() == false) {
					Sell.setEnabled(false);
					Tke.setEnabled(false);
					HoaDon.setEnabled(false);
					LOAISP.setEnabled(false);
					SANPHAM.setEnabled(false);
				}
			}
		});
		setType(Type.NORMAL);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 415);
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 297, 415);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel paneChucNang = new JPanel();
		paneChucNang.addMouseListener(new PanelButtonMouseAdapter(paneChucNang));
		paneChucNang.setBackground(Color.GRAY);
		paneChucNang.setBounds(0, 219, 297, 41);
		panel.add(paneChucNang);
		paneChucNang.setLayout(null);
		
		JMenuBar menu = new JMenuBar();
		menu.setBounds(70, 0, 130, 41);
		menu.setForeground(new Color(0, 0, 0));
		menu.setBackground(Color.GRAY);
		paneChucNang.add(menu);
		
		JMenu menuChucnang = new JMenu("CH\u1EE8C N\u0102NG");
		menuChucnang.setForeground(new Color(255, 255, 255));
		menu.add(menuChucnang);
		menuChucnang.setFont(new Font("Dialog", Font.BOLD, 18));
		menuChucnang.setBackground(Color.GRAY);
		
		Sell = new JMenuItem("B\u00C1N H\u00C0NG");
		Sell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBanHang frm =new frmBanHang();
				frm.setVisible(true);
				dispose();
			}
		});
		Sell.setForeground(new Color(0, 0, 0));
		Sell.setFont(new Font("Dialog", Font.BOLD, 15));
		Sell.setBackground(Color.WHITE);
		menuChucnang.add(Sell);
		
		Tke = new JMenuItem("TH\u1ED0NG K\u00CA");
		Tke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmThongKe frm = new frmThongKe();
				frm.setVisible(true);
				dispose();
			}
		});
		Tke.setForeground(new Color(0, 0, 0));
		Tke.setFont(new Font("Dialog", Font.BOLD, 15));
		Tke.setBackground(Color.WHITE);
		menuChucnang.add(Tke);
		
		JPanel paneHome = new JPanel();
		paneHome.setForeground(new Color(255, 255, 255));
		paneHome.addMouseListener(new PanelButtonMouseAdapter(paneHome) );
		paneHome.setBackground(Color.GRAY);
		paneHome.setBounds(0, 121, 297, 41);
		panel.add(paneHome);
		paneHome.setLayout(null);
		
		JMenuBar menuTAIKHOAN = new JMenuBar();
		menuTAIKHOAN.setBackground(Color.GRAY);
		menuTAIKHOAN.setBounds(70, 0, 130, 41);
		paneHome.add(menuTAIKHOAN);
		
		JMenu mnNewMenu = new JMenu("TÀI KHOẢN");
		mnNewMenu.setForeground(new Color(255, 255, 255));
		mnNewMenu.setFont(new Font("Dialog", Font.BOLD, 18));
		mnNewMenu.setBackground(Color.GRAY);
		menuTAIKHOAN.add(mnNewMenu);
		
		JMenuItem ttcn = new JMenuItem("Thông tin cá nhân");
		ttcn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 frmThongTinCaNhan frm = new frmThongTinCaNhan(); frm.setVisible(true); dispose();
				 
			}
		});
		ttcn.setFont(new Font("Dialog", Font.BOLD, 15));
		ttcn.setBackground(new Color(255, 255, 255));
		mnNewMenu.add(ttcn);
		mntmNewMenuItem = new JMenuItem("Nhân viên");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmNhanVien frm = new frmNhanVien();
				frm.setVisible(true);
				dispose();
			}
		});
		mntmNewMenuItem.setFont(new Font("Dialog", Font.BOLD, 15));
		mntmNewMenuItem.setBackground(new Color(255, 255, 255));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Đăng xuất");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin frm = new frmLogin();
				frm.setVisible(true);
				dispose();
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Dialog", Font.BOLD, 15));
		mntmNewMenuItem_1.setBackground(new Color(255, 255, 255));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Đổi mật khẩu");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDoiMatKhau frm = new frmDoiMatKhau();
				frm.setVisible(true);
				dispose();
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Dialog", Font.BOLD, 15));
		mntmNewMenuItem_2.setBackground(new Color(255, 255, 255));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JPanel paneLogin = new JPanel();
		paneLogin.addMouseListener(new PanelButtonMouseAdapter(paneLogin));
		paneLogin.setBackground(Color.GRAY);
		paneLogin.setBounds(0, 315, 297, 41);
		panel.add(paneLogin);
		paneLogin.setLayout(null);
		
		JMenuBar menuQUANLY = new JMenuBar();
		menuQUANLY.setForeground(new Color(0, 0, 0));
		menuQUANLY.setBackground(Color.GRAY);
		menuQUANLY.setBounds(71, 0, 130, 41);
		paneLogin.add(menuQUANLY);
		
		JMenu menuChucnang_1 = new JMenu("QUẢN LÝ");
		menuChucnang_1.setForeground(Color.WHITE);
		menuChucnang_1.setFont(new Font("Dialog", Font.BOLD, 18));
		menuChucnang_1.setBackground(Color.GRAY);
		menuQUANLY.add(menuChucnang_1);
		
		JMenuItem KHACHHANG = new JMenuItem("KHÁCH HÀNG");
		KHACHHANG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmKhachHang frm = new frmKhachHang();
				frm.setVisible(true);
				dispose();
			}
		});
		KHACHHANG.setFont(new Font("Dialog", Font.BOLD, 15));
		KHACHHANG.setBackground(Color.WHITE);
		menuChucnang_1.add(KHACHHANG);
		
		SANPHAM = new JMenuItem("SẢN PHẨM");
		SANPHAM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSP frm = new frmSP();
				frm.setVisible(true);
				dispose();
			}
		});
		SANPHAM.setFont(new Font("Dialog", Font.BOLD, 15));
		SANPHAM.setBackground(Color.WHITE);
		menuChucnang_1.add(SANPHAM);
		
		JMenuItem LOAISP = new JMenuItem("LOẠI SẢN PHẨM");
		LOAISP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLoaiSP frm = new frmLoaiSP();
				frm.setVisible(true);
				dispose();
			}
		});
		LOAISP.setFont(new Font("Dialog", Font.BOLD, 15));
		LOAISP.setBackground(Color.WHITE);
		menuChucnang_1.add(LOAISP);
		
		HoaDon = new JMenuItem("Hóa Đơn");
		HoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLichSuGiaoDich frm = new frmLichSuGiaoDich();
				frm.setVisible(true);
				dispose();
			}
		});
		HoaDon.setFont(new Font("Dialog", Font.BOLD, 15));
		HoaDon.setBackground(Color.WHITE);
		menuChucnang_1.add(HoaDon);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\duybm\\Downloads\\logo1.png"));
		lblNewLabel_1.setBounds(36, 47, 202, 63);
		panel.add(lblNewLabel_1);
		
		JLabel lblExit = new JLabel("X");
		lblExit.setForeground(new Color(255, 255, 255));
		lblExit.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lblExit.setBounds(866, 0, 20, 29);
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình không?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
					MenuBar.this.dispose();
				}
			}	
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblExit.setBackground(Color.RED);
			}	
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblExit.setBackground(Color.WHITE);
			}		
		});
		contentPane.add(lblExit);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\duybm\\Downloads\\Untitled design.png"));
		lblNewLabel.setBounds(325, 66, 490, 280);
		contentPane.add(lblNewLabel);
		
		JLabel lblLogo = new JLabel("COFFE & CAKE ELTEE");
		lblLogo.setBounds(351, 15, 277, 41);
		contentPane.add(lblLogo);
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Dialog", Font.BOLD, 25));
	}	
	
	private class PanelButtonMouseAdapter extends MouseAdapter {
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(112,128,144));
		}	
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(Color.GRAY);
		}	
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(60,179,113));
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(112,128,144));
		}

	}

}
