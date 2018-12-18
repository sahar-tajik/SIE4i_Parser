package view;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;


import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Company;
import sieparser.SieDocument;
import sieparser.SieDocumentReader;
import validation.High­lightLis­tener;
import validation.MandatoryFields;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class MainUI {

	private JFrame frmImportSieFile;
	private JLabel lblCompanyName;
	private JLabel lblCompanyCode;
	private JLabel lblAddress;
	private JTextField txtFldCompanyName;
	private JTextField txtFldCompanyCode;
	private JTextField txtFldStreet;
	private JLabel lblImportFile;
	private JLabel lblNewLabel;
	private JTextField txtFldZipCode;
	private JLabel lblFileName;
	private JButton btnImportFile;
	
	private JFileChooser sieFile;
	private JLabel lblCompanyCodeError;
	private JLabel lblStreetError;
	private JLabel lblZipCodeError;
	private JLabel lblFileError;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frmImportSieFile.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmImportSieFile = new JFrame();
		frmImportSieFile.setTitle("Import SIE File");
		frmImportSieFile.setBounds(100, 100, 491, 300);
		frmImportSieFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmImportSieFile.getContentPane().setLayout(null);
		
		
		lblCompanyCode = new JLabel("Company Code:*");
		lblCompanyCode.setBounds(12, 59, 124, 15);
		frmImportSieFile.getContentPane().add(lblCompanyCode);
		
		lblAddress = new JLabel("Address - Street:*");
		lblAddress.setBounds(12, 86, 124, 19);
		frmImportSieFile.getContentPane().add(lblAddress);
		
		JLabel lblCompanyNameError = new JLabel("");
		lblCompanyNameError.setForeground(Color.RED);
		lblCompanyNameError.setBounds(273, 32, 192, 15);
		frmImportSieFile.getContentPane().add(lblCompanyNameError);
		
		txtFldCompanyName = new JTextField();
		txtFldCompanyName.setBounds(141, 32, 124, 19);
		frmImportSieFile.getContentPane().add(txtFldCompanyName);
		txtFldCompanyName.setColumns(10);
		new High­lightLis­tener(txtFldCompanyName);

		lblCompanyName = new JLabel("Company Name:*");
		lblCompanyName.setBounds(12, 32, 124, 15);
		frmImportSieFile.getContentPane().add(lblCompanyName);		
		
		txtFldCompanyCode = new JTextField();
		txtFldCompanyCode.setBounds(141, 57, 124, 19);
		frmImportSieFile.getContentPane().add(txtFldCompanyCode);
		txtFldCompanyCode.setColumns(10);
		new High­lightLis­tener(txtFldCompanyCode);
		
		lblCompanyCodeError = new JLabel("");
		lblCompanyCodeError.setForeground(Color.RED);
		lblCompanyCodeError.setBounds(273, 59, 192, 15);
		frmImportSieFile.getContentPane().add(lblCompanyCodeError);
		
		
		txtFldStreet = new JTextField();
		txtFldStreet.setBounds(141, 86, 124, 19);
		frmImportSieFile.getContentPane().add(txtFldStreet);
		txtFldStreet.setColumns(10);
		new High­lightLis­tener(txtFldStreet);
		
		lblStreetError = new JLabel("");
		lblStreetError.setForeground(Color.RED);
		lblStreetError.setBounds(273, 88, 192, 15);
		frmImportSieFile.getContentPane().add(lblStreetError);
		
		lblNewLabel = new JLabel("ZipCode:*");
		lblNewLabel.setBounds(12, 114, 83, 15);
		frmImportSieFile.getContentPane().add(lblNewLabel);
		
		txtFldZipCode = new JTextField();
		txtFldZipCode.setBounds(141, 112, 124, 19);
		frmImportSieFile.getContentPane().add(txtFldZipCode);
		txtFldZipCode.setColumns(10);
		new High­lightLis­tener(txtFldZipCode);
		
		lblZipCodeError = new JLabel("");
		lblZipCodeError.setForeground(Color.RED);
		lblZipCodeError.setBounds(273, 114, 192, 15);
		frmImportSieFile.getContentPane().add(lblZipCodeError);
		
		
		lblImportFile = new JLabel("Import File:");
		lblImportFile.setBounds(12, 148, 110, 15);
		frmImportSieFile.getContentPane().add(lblImportFile);
		
		lblFileError = new JLabel("");
		lblFileError.setForeground(Color.RED);
		lblFileError.setBounds(273, 148, 192, 15);
		frmImportSieFile.getContentPane().add(lblFileError);
		
		lblFileName = new JLabel("");
		lblFileName.setBounds(151, 167, 318, 25);
		frmImportSieFile.getContentPane().add(lblFileName);
		
		JButton btnImport = new JButton("Select File");
		btnImport.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
				 
				 sieFile = new JFileChooser();
				 sieFile.setDialogTitle("Please select a SIE file to import.");
				 int result = sieFile.showOpenDialog(null);
				 if(result == JFileChooser.APPROVE_OPTION) {
					 // first empty the lblFileName
					 if(!lblFileName.getText().isEmpty())
						 lblFileName.setText("");
					 
					// empty the lblFileError
					if(!lblFileError.getText().isEmpty())
						lblFileError.setText("");
					 
					 lblFileName.setText(sieFile.getSelectedFile().getName());
					 
				 }
				 
				 
				 
			}
		});
		btnImport.setBounds(141, 143, 114, 25);
		frmImportSieFile.getContentPane().add(btnImport);
		
		btnImportFile = new JButton("Import File");
		btnImportFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				List<JTextField> 	mandatoryTextFields = new ArrayList<>();
				mandatoryTextFields.add(txtFldCompanyName);
				mandatoryTextFields.add(txtFldCompanyCode);
				mandatoryTextFields.add(txtFldStreet);
				mandatoryTextFields.add(txtFldZipCode);
				
				List<JLabel> 		errorLables = new ArrayList<>();
				errorLables.add(lblCompanyNameError);
				errorLables.add(lblCompanyCodeError);
				errorLables.add(lblStreetError);
				errorLables.add(lblZipCodeError);
				
				MandatoryFields mandatoryFields = new MandatoryFields(mandatoryTextFields,errorLables);
				
				if(mandatoryFields.checkingMandatoryFields()) {
				
					SieDocument doc = null;
					try {
			            File file = new File(sieFile.getSelectedFile().getPath());
			            SieDocumentReader reader = new SieDocumentReader();
			            
			            doc = reader.readDocument(file.getAbsolutePath());
			            if (reader.getValidationExceptions().size() > 0)
			            {
			                for (Exception ex : reader.getValidationExceptions()) {
			                    System.out.println(ex.toString());
			                }
			                
			            }else {
			            	
			            	Company company = new Company(txtFldCompanyName.getText(), txtFldCompanyCode.getText(), txtFldStreet.getText(), txtFldZipCode.getText());
			            	ShowTransactions transactions = new ShowTransactions(company,doc);
			            	transactions.setVisible(true);
			            	
			            }
			         } catch (Exception e) {
			        	 lblFileError.setText("Please select file!");
			        	 
			        }
				}
				 
				
			}
		});
		btnImportFile.setBounds(141, 233, 114, 25);
		frmImportSieFile.getContentPane().add(btnImportFile);
		
		
		
		
	}
}
