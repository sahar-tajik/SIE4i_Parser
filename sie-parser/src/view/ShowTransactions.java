package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.Company;
import sieparser.SieDocument;
import sieparser.SieVoucher;
import sieparser.SieVoucherRow;
import table.TreeTable;

public class ShowTransactions extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowTransactions frame = new ShowTransactions();
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
	public ShowTransactions() {
		initialize();
	}
	
	public  ShowTransactions(Company company, SieDocument doc) {
		initialize();
		add(new JPanel().add(new JLabel(company.toString())), BorderLayout.PAGE_START);
		initializeTree(doc);
		
		
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(800, 800);
		setLayout(new BorderLayout());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}
	
	private void initializeTree(SieDocument doc) {
		
		List<SieVoucher> voucher = doc.getVER();
		List<String[]> content = new ArrayList<>();
		
		for (int i = 0; i < voucher.size(); i++) {
			content.add(new String[] {voucher.get(i).toString()});
			List<SieVoucherRow> rows = voucher.get(i).getRows(); 
			for (int j = 0; j < rows.size(); j++) {
				content.add(rows.get(j).getStringRow());
			}
		}

		TreeTable treeTable = new TreeTable(content);

		
		add(new JScrollPane(treeTable.getTreeTable()), BorderLayout.CENTER);

		  
		
	}

}
