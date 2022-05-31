package frm;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import DAO.CTHDModel;
import DAO.HoaDonDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.jfree.data.general.PieDataset;
public class frmThongKe extends JFrame {

	private JPanel contentPane;
	private JPanel PnlTien;
	private JPanel PnlSP;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmThongKe frame = new frmThongKe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void Setdata(JPanel pnl) {
		ArrayList<CTHDModel> list = new HoaDonDAO().ThongKeTien();
		
		if(list != null) {
			DefaultCategoryDataset data = new DefaultCategoryDataset();
			
			for(CTHDModel ct : list) {
				data.addValue(ct.getThanhTien(), "Thống Kê", ct.getThang());
			}
			JFreeChart chart = ChartFactory.createBarChart("Thống Kê Doanh Thu Cả Năm", "Tháng", "Tổng Tiền", data);
			
			ChartPanel panel = new ChartPanel(chart);
			
			panel.setPreferredSize(new Dimension(PnlTien.getWidth(),PnlTien.getHeight()));
			
			pnl.removeAll();
			pnl.setLayout(new CardLayout());
			pnl.add(panel);
			pnl.validate();
			pnl.repaint();
		}
	}
	public void SetdataSL(JPanel pnl) {
		ArrayList<CTHDModel> list = new HoaDonDAO().ThongKeSL();
		ArrayList<Integer> a =new ArrayList<Integer>();
		if(list != null) {
			DefaultCategoryDataset data = new DefaultCategoryDataset();
			DefaultPieDataset pie = new DefaultPieDataset();
			for(CTHDModel ct : list) {
				data.addValue(ct.getSoluong(), "Thống Kê", ct.getTenSP());
				pie.setValue(ct.getTenSP() + ':' + ct.getSoluong() , ct.getSoluong());
			}
			JFreeChart chart1 = ChartFactory.createPieChart("Thống Kê Số Lượng Hàng Bán Chạy", pie,true,true,true);
			
			//PiePlot3D plot = (PiePlot3D) chart1.getPlot();
			
			JFreeChart chart = ChartFactory.createBarChart("Thống Kê Số Lượng Hàng Bán Chạy", "Tên Sản Phẩm", "Số Lượng", data);
			
			ChartPanel panel = new ChartPanel(chart1);
			
			panel.setPreferredSize(new Dimension(PnlSP.getWidth(),PnlSP.getHeight()));
			
			pnl.removeAll();
			pnl.setLayout(new CardLayout());
			pnl.add(panel);
			pnl.validate();
			pnl.repaint();
		}
	}
	/**
	 * Create the frame.
	 */
	public frmThongKe() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Setdata(PnlTien);
				SetdataSL(PnlSP);
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
		
		PnlSP = new JPanel();
		PnlSP.setBounds(0, 0, 852, 265);
		contentPane.add(PnlSP);
		PnlSP.setLayout(null);
		
		PnlTien = new JPanel();
		PnlTien.setBounds(0, 272, 852, 301);
		contentPane.add(PnlTien);
	}
}
