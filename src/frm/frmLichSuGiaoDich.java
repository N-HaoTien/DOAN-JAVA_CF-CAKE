package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

import DAO.CTHDModel;
import DAO.ConnectionProvider;
import DAO.HoaDonDAO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmLichSuGiaoDich extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JDateChooser dateStart;
	private JDateChooser dateEnd;
	public static Integer IDHD;
	public ConnectionProvider cn = new ConnectionProvider();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLichSuGiaoDich frame = new frmLichSuGiaoDich();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void XuatHoaDon(Integer idhd) {
		try {
			Hashtable map = new Hashtable();

			JasperReport Report = JasperCompileManager.compileReport("src\\frm\\HoaDon.jrxml");

			map.put("MaHD", idhd);

			JasperPrint p = JasperFillManager.fillReport(Report, map, cn.getCon());

			JasperViewer.viewReport(p, false);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void XuatHoaDonKH(Integer idhd) {
		try {
			Hashtable map = new Hashtable();

			JasperReport Report = JasperCompileManager.compileReport("src\\frm\\RptHoaDon.jrxml");

			map.put("IDHDD", idhd);

			JasperPrint p = JasperFillManager.fillReport(Report, map, cn.getCon());

			JasperViewer.viewReport(p, false);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void ShowCTHD(String start, String end) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<CTHDModel> ct = new HoaDonDAO().getAllHDbyTime(start, end);
		Iterator<CTHDModel> itr = ct.iterator();
		while (itr.hasNext()) {
			CTHDModel pro = itr.next();
			dtm.addRow(new Object[] { pro.getIDHD(), pro.getTenNV(), pro.getGhichu(), pro.getIDKH(), pro.getGiamGia(),
					pro.getThanhTien() });
		}
	}

	/**
	 * Create the frame.
	 */
	public frmLichSuGiaoDich() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				ShowCTHD("", "");
			}

			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				MenuBar frm = new MenuBar();
				frm.setVisible(true);
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 866, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnCheck = new JButton("Xem");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				String Start = "", End = "";
				if (dateStart.getDate() == null || dateEnd.getDate() == null) {
					Start = "";
					End = "";
				} else {
					Start = formatter.format(dateStart.getDate());
					End = formatter.format(dateEnd.getDate());
				}
				ShowCTHD(Start, End);
			}
		});
		btnCheck.setForeground(Color.RED);
		btnCheck.setBackground(Color.WHITE);
		btnCheck.setFont(new Font("Arial", btnCheck.getFont().getStyle(), btnCheck.getFont().getSize() + 8));
		btnCheck.setBounds(324, 45, 160, 42);
		contentPane.add(btnCheck);

		dateStart = new JDateChooser();
		dateStart.setDateFormatString("dd-MM-yyyy");
		dateStart.setBounds(30, 45, 202, 42);
		contentPane.add(dateStart);

		dateEnd = new JDateChooser();
		dateEnd.setDateFormatString("dd-MM-yyyy");
		dateEnd.setBounds(576, 45, 202, 42);
		contentPane.add(dateEnd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 106, 852, 467);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				IDHD = (Integer) model.getValueAt(index, 0);
				Integer IDKH = (Integer) model.getValueAt(index, 3);
				XuatHoaDon(IDHD);
			}
		});
		table.setFont(new Font("Arial", table.getFont().getStyle(), 10));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "IDHD", "Ng\u01B0\u1EDDi L\u1EADp",
				"Th\u1EDDi \u0110i\u1EC3m L\u1EADp", "IDKH", "Gi\u1EA3m Gi\u00E1", "T\u1ED5ng Ti\u1EC1n" }));
		scrollPane.setViewportView(table);
	}
}
