package it.moise.plc.uml.gui;



import it.moise.plc.uml.ConversionToCoDeSys;
import it.moise.plc.uml.MioDom;
import it.moise.plc.uml.Variable;
import it.moise.plc.uml.xmltree.XMLViewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;














public class JFrameContainer extends JFrame  {
	
	private JFrameContainer win;
	private MioDom mioDom;
	private boolean analisiEffettuata = false;
	private JPanel panelContainer = new JPanel(new BorderLayout());
	private JPanel topContainer = new JPanel(new BorderLayout());
	private JPanel radioBoxPanel = new JPanel();
	private JPanel messagePanel = new JPanel();
	private JPanel varPanel = new JPanel();
	private JPanel varListPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel sxToolBarpanel = new JPanel();
	private JPanel helpPanel = new JPanel();
	private boolean helpAttivato = false;
	private XMLViewer browser;
	private JToolBar toolBar = new JToolBar();
	private JLabel titoloLblMessagePanel = new JLabel("Messaggi del sistema");
	private JButton openButton;
	private ImageButton helpButton;
	private ImageButton exportButton;
	private ImageButton analysisButton;
	private JTextArea txtArea = new JTextArea("Area messaggi",6, 60);
	JScrollPane scrollPaneTxtArea = new JScrollPane(txtArea);
	private String txt = "";
	private JLabel nameFileUmlLabel = new JLabel("");
	private String nameFileUml = "";
	private String pathFileUml = "";
	
	private String[] typePou = {"Program", "Function Block Diagram", "Function"};
	private String[] type = {"Prg", "FDB", "Func"};
	private String valoreRadioButton = "";
	private JLabel tipoPou = new JLabel();	
	private boolean estensioneFileUmlcorretta = false;
	private boolean primoAvvio= false;
	private JFrame frameHelp = new JFrame("Guida all'uso");
	//// table
	private JTable table;
	private JTextArea output;
    private String[] headerTable={"Global", "Local", "Temp", "Input", "Output", "Constant", "Nome variabile", "Tipo variabile"};
    private int row = 0;
    private int column = 0;
    boolean modificato = false;
	
	private Object[][] data = {
			{new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(true), "Da Verificare", "Tipo variabile"},
			{new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(true), "Da Verificare", "Tipo variabile"}
			};
   
	    
	public JFrameContainer() throws IOException {
		super ("Welcome, convert your state machine diagram in plc");
		this.win = this;
		//String valore = Date.
		setIcon();
		
		
		// scelgo l'icona del programma
		String path="help.png";
		ImageIcon icon = new ImageIcon(path);
		//this.setIconImage((Image) icon);
		// dimensione finestra
		
		this.helpButton = new ImageButton("help.png", "", "help", "Help");
		this.exportButton = new ImageButton("export.png", "", "convert", "Execute conversion");
		this.openButton = new ImageButton("open.png","" , "open", "File Open");
		this.analysisButton = new ImageButton("analisys.png", "", "analysis", "Parses document UML-XMI");
		
		//JRadioButtonTypePOU sceltaPou = new JRadioButtonTypePOU(globalVar);
		this.openButton.addActionListener(new ActionListener(){										
												public void actionPerformed(ActionEvent e){
													String nameButton ="";
													ImageButton who = (ImageButton) e.getSource(); // sorgente dell evento
													nameButton = who.getName();
													JFileChooserComponent fc = new JFileChooserComponent("uml");
													if (fc.getNameFileUml() != null){
													//	JOptionPane.showMessageDialog(null, "JToolBarComponent " + fc.getNameFileUml() +" " +fc.getPathFileUml());
													// devo verificare che il file selezionato sia di tipo uml	
														setNameFileUml(fc.getPathFileUml());
														txtArea.append("\n->E' stato selezionato il file: " + fc.getPathFileUml());
													}
												}
												
												
											});
		
		
		// action listener per scelta button
		final MyActionListener listenerButton = new MyActionListener();
		this.exportButton.addActionListener(listenerButton);
		// action listener per helpButton
		final MyActionListenerHelp listenerButtonHelp = new MyActionListenerHelp();
		this.helpButton.addActionListener(listenerButtonHelp);
		// action listener per helpButton
		final MyActionListener listenerButtonAnalysis = new MyActionListener();
		this.analysisButton.addActionListener(listenerButtonAnalysis);
		/*
		 * sezione per la creazione del MESSAGE PANEL
		 */
		
		txt = "ciao moise";
		titoloLblMessagePanel.setPreferredSize(new Dimension(1000,18));
		titoloLblMessagePanel.setFont(new Font("serif", Font.BOLD, 14));
		titoloLblMessagePanel.setForeground(Color.RED);
		titoloLblMessagePanel.setVerticalAlignment(SwingConstants.NORTH);
		titoloLblMessagePanel.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.txtArea.setBackground(Color.BLACK);
		this.txtArea.setForeground(Color.GREEN);
		this.txtArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		this.txtArea.setCaretColor(Color.GREEN);
		this.txtArea.setLineWrap(true);	//quando una parola raggiunge il bordo destro del controllo vai a capo
		this.txtArea.setWrapStyleWord(true);	// vai a capo in base alla parole e non ai caratteri
		this.txtArea.setCaretPosition(txt.length()); 	// sposta la posizione del cursore
		this.txtArea.setEditable(false);
		this.txtArea.setAutoscrolls(true);
		
		this.messagePanel.setLayout(new BorderLayout());
		this.messagePanel.add(titoloLblMessagePanel, BorderLayout.NORTH);
		this.messagePanel.add(scrollPaneTxtArea, BorderLayout.CENTER);
		this.messagePanel.setSize(new Dimension(1100,128));
		this.messagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		/*
		 * Sezione del pannello delle variabili
		 */
		this.varPanel.setLayout(new BorderLayout());
		JLabel titoloVarPanel = new JLabel("Se sono presenti delle variabili, seleziona l'ambito di scopo");
		titoloVarPanel.setFont(new Font("serif", Font.BOLD, 14));
		titoloVarPanel.setForeground(Color.BLACK);
		titoloVarPanel.setVerticalAlignment(SwingConstants.NORTH);
		titoloVarPanel.setHorizontalAlignment(SwingConstants.LEFT);
		this.varPanel.add(titoloVarPanel, BorderLayout.NORTH);
		this.table = new JTable(new MyTableModel());
		
		this.table.setPreferredScrollableViewportSize(new Dimension(1180,150));table.setSize(1180, 150);
		this.table.setFillsViewportHeight(true);
        this.table.getCellSelectionEnabled();
        this.table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.varPanel.add(new JScrollPane(this.table), BorderLayout.CENTER);
		
      
		this.varPanel.setName("varPanello");
		this.centerPanel.add(this.varPanel,0);
		
		
		
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints gridContraint = new GridBagConstraints ();
		this.sxToolBarpanel.setLayout(gridBag);
		gridContraint.fill= GridBagConstraints.BOTH;
		gridBag.setConstraints(openButton, gridContraint);
		this.sxToolBarpanel.add(openButton);
		
		gridContraint.gridwidth= GridBagConstraints.REMAINDER; 
		gridContraint.fill= GridBagConstraints.BOTH;
		gridBag.setConstraints(analysisButton, gridContraint);
		this.sxToolBarpanel.add(analysisButton);
		
		gridContraint.gridwidth=1;
		gridBag.setConstraints(exportButton, gridContraint);
		gridBag.setConstraints(exportButton, gridContraint);
		this.sxToolBarpanel.add(exportButton);
		
		gridContraint.gridwidth= GridBagConstraints.REMAINDER; 
		gridContraint.fill= GridBagConstraints.BOTH;
		gridBag.setConstraints(helpButton, gridContraint);
		this.sxToolBarpanel.add(helpButton);
		this.sxToolBarpanel.setSize(new Dimension(128,128));
		
		
		
		//this.toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.toolBar.add(sxToolBarpanel);
		this.toolBar.add(messagePanel);
		this.toolBar.setFloatable(false);
		this.topContainer.add(this.toolBar, BorderLayout.NORTH);
		//this.topContainer.add(messagePanel, BorderLayout.CENTER);
		
		this.panelContainer.add(this.topContainer, BorderLayout.PAGE_START);
		this.panelContainer.add(this.centerPanel, BorderLayout.CENTER);
		// scelgo cosa succederà quando si chiude la finestra
		
		this.panelContainer.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.add(this.panelContainer);
		this.setResizable(false);
		this.setSize(1220, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// rendo visibile la finestra
		this.setVisible(true);
		// centro la finestra nello schermo
		this.setLocationRelativeTo(null);
	}
	
	public void setNameFileUml(String name){
		this.nameFileUml= name;
		
	}
	public String getNameFileUml(){
		return this.nameFileUml;
	}
		
	private void setValoreRadioButton(ActionEvent e){
		//this.tipoPou.setText(value);
		System.out.println(e.getSource());
		JRadioButton r = (JRadioButton) e.getSource();
		System.out.println(r.getName());
	}
	public String getValoreRadioButton(){
		return this.valoreRadioButton;
		
		//JFrame a = new JFrame();
		//a.s
	}
	
	public class MyActionListener implements ActionListener{
		public void actionPerformed (ActionEvent e){
			
			
			ImageButton o = (ImageButton) e.getSource();
		
			//setVisible(false);
			String fileUml;
			//setValoreRadioButton(getValoreRadioButton());
			
			switch (o.getName()){
			case "analysis":
				try {
					//  senza scegliere il pulsante ConversionToCoDeSys p = new ConversionToCoDeSys(getValoreRadioButton(),"C:/Users/hp envy/workspaceJava/MioDom/model.uml");
					fileUml= getNameFileUml().replace("\\", "/");
					String estensione = "";				
					if (fileUml.length() == 0){
						// file non selezionato
						txtArea.append("\n->Non hai selezionato nessun file .uml");
						//JOptionPane.showMessageDialog(null, "Non hai selezionato nessun file .uml");
					}else{
						estensione = fileUml.substring(fileUml.length()-3, fileUml.length());
						//JOptionPane.showMessageDialog(null, "Estensione uml " + estensione);
							if (estensione.equals("uml")){
							//	JOptionPane.showMessageDialog(null, "Il file scelto è: " + fileUml);
							//	JOptionPane.showMessageDialog(null, fileUml);
							mioDom = new MioDom(fileUml, txtArea);
							analisiEffettuata = true;
							txtArea.append("\n->Analisi del modello UML terminata");
							txtArea.append("\n->Sono stati individuati " + mioDom.getGrafoStati().size() + " stati.");
							txtArea.append("\n->Sono stati individuati " + mioDom.getVectorTransitions().size() + " transizioni.");
							txtArea.append("\n->Sono stati individuati " + mioDom.getArrayStateLevel().length + " livelli.");
							if(mioDom.getModello().getStateMachine().getVectorVariable().size() > 0){
								txtArea.append("\n->Sono presenti nr. " + mioDom.getModello().getStateMachine().getVectorVariable().size() + " variabili.");
								txtArea.append("\n->Prossimo passo associare le variabili con il proprio ambiente");
								caricoVariabili();
							}else{
								// aggiorno la tabella, nel csaso in cui sia già stato utilizzato per converitre prima un altro file
								table.setModel(new MyTableModel());
							}
						}else{
							txtArea.append("\n->Il file selezionato non è di tipo .uml");
						}
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "convert":
					try {
						if (browser !=null){
							System.out.println("Browser già istanziato");
							browser = null;
							centerPanel.revalidate();
						}
						//  senza scegliere il pulsante ConversionToCoDeSys p = new ConversionToCoDeSys(getValoreRadioButton(),"C:/Users/hp envy/workspaceJava/MioDom/model.uml");
						fileUml= getNameFileUml().replace("\\", "/");
						String estensione = "";				
						if (fileUml.length() == 0){
							// file non selezionato
							txtArea.append("\n->Non hai selezionato nessun file .uml");
						}else{
							estensione = fileUml.substring(fileUml.length()-3, fileUml.length());
							//JOptionPane.showMessageDialog(null, "Estensione uml " + estensione);
							if (estensione.equals("uml")){
							//	JOptionPane.showMessageDialog(null, "Il file scelto è: " + fileUml);
							//	JOptionPane.showMessageDialog(null, fileUml);
							if (!analisiEffettuata){
								txtArea.append("\n->Devi prima effettuare l'analisi del documento UML");
							}else{
								
								/*
								 * 
								 *  imposto il tipo delle variabili che sono stati scelti
								 */
								impostaTipoVariabili();
								
								//mioDom = new MioDom(fileUml, txtArea);
								txtArea.append("\n->Conversione terminata. Il codice è stato inserito \nall'interno del file outputFileCodesys.xml");
								ConversionToCoDeSys p = new ConversionToCoDeSys(mioDom, "FDB", txt, txtArea);
								
								browser = new XMLViewer();
								
								if (!primoAvvio){
									centerPanel.add(browser,1);
									primoAvvio=true;
									txtArea.append("\n->Creato albero di visualizzazione.");
								}else{
									//System.out.println("ci sono nr comoponenti:" + centerPanel.getComponentCount());
									int nr=centerPanel.getComponentCount();
									txtArea.append("\n->L'albero di visualizzazione è stato modificato.");
									for (int i =0; i < nr; i++){
									//	System.out.println("nome componente:  " +centerPanel.getComponent(i).getName());
										
									}
									centerPanel.remove(1);centerPanel.add(browser,1);
								}
								txtArea.append("\n->Il file è disponibile per la visualizzazione");
								centerPanel.revalidate();
								
							}
							
							}else{
								txtArea.append("\n->Il file selezionato non è di tipo .uml");
							}
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//dispose();
				break;	
			
				}
			
			
		}
		
	}
	
	

	/**
	 * Imposta il valore della variabile txt che viene utilizzata per mostrare i messaggi nella textArea
	 * @param value
	 */
	public void setTxt(String value){
		this.txt = value;
	}
	/**
	 * Restituisce il valore della variabile txt, che viene utilizzata per mostrare i messaggi nella textArea
	 * @return
	 */
	public String getTxt(){
		return this.txt;
	}
	public JTextArea getTxtArea(){
		return this.txtArea;
	}
	
	public void caricoVariabili(){
		int nrVar = this.mioDom.getModello().getStateMachine().getVectorVariable().size();
		int riga =0;
		int col =0;
		this.data = new Object[nrVar][8];
		for (int l=0; l < nrVar; l++){
			for (int i=0; i < 6; i++){
				if (i < 6){
					if (i==0)
						data[riga][i] = new Boolean(true);
					else
						data[riga][i] = new Boolean(false);
				}
			}
			data[riga][6] = this.mioDom.getModello().getStateMachine().getVectorVariable().get(l).getName();
			data[riga][7] = this.mioDom.getModello().getStateMachine().getVectorVariable().get(l).getType();
			riga++;
			this.mioDom.getModello().getStateMachine().getVectorVariable().get(l).setScopeVariable("GLOBAL");
		}
		JOptionPane.showMessageDialog(null,"Sono state individtuate delle variabili");
		 /*Boolean valore = (Boolean)table.getModel().getValueAt(0, 0);
		 if(valore)
			 table.getModel().setValueAt(new Boolean(true), 0, 0);
		 else
			 table.getModel().setValueAt(new Boolean(false), 0, 0);
		*/
		table.setModel(new MyTableModel());
		this.centerPanel.add(this.varPanel,0);
		//this.varPanel.repaint();
		this.revalidate();
	}
	/******************************************************************/
		// TABLE VAR
	/* 
	   * Effettua il controllo sulle altre celle della riga modificata e stampa la tabella prima della modifica e la nuova riga 
	   * di come saràiii
	   */
	  private void stampaValoriTabella(int riga, int col){
		  boolean modificato = false;
		  /**************************************************/
		 // output.append("\n\nEcco la tabella dei valori  INIZIALE della modifica1\n");
		  for (int i=0; i < 2; i++){
			  for(int j=0; j < 5; j++){
				  if (table.getModel().getValueAt(i, j).getClass().equals(java.lang.Boolean.class)){
					  Boolean valore = (Boolean)table.getModel().getValueAt(i, j);
					//  output.append(String.valueOf(valore) +" ");
					  System.out.println(table.getModel().getValueAt(i, j).getClass());
			        }else{
			        	output.append(" label\n");      	 
			        }  
			  }
			//  output.append("riga: i="+i +"\n");
		  }
		  /*
		   * modifico la nuova riga
		   */
		  boolean oldValue=false;
		  boolean newValue=false;
		  boolean cellaValore = false;		 
		  
		  //output.append("\n\nEcco la tabella dei valori  NELLA MODIFICA della modifica\n");
		  if (table.getModel().getValueAt(riga, col).getClass().equals(java.lang.Boolean.class)){
			  cellaValore = (Boolean)table.getModel().getValueAt(riga, col);
		  }
		  if (col < 6){
			  for(int j=0; j < 6; j++){
				  if (table.getModel().getValueAt(riga, j).getClass().equals(java.lang.Boolean.class)){ 
					 /* 1 - *************  */
					  if (!cellaValore && (j!=col)){
						// la cella deve diventare TRUE e le altre FALSE 
						 if (j!=col){
							 oldValue = (Boolean)table.getModel().getValueAt(riga, j);
							 table.getModel().setValueAt(new Boolean(false), riga, j);
							 data[riga][j] = new Boolean(false);
							 newValue = (Boolean)table.getModel().getValueAt(riga, j);}
					  }	
					  /* 2 - *************  */
					  	if (!cellaValore && (j==col)){
						// la cella deve diventare TRUE e le altre FALSE 
						 if (j==col){
							 oldValue = (Boolean)table.getModel().getValueAt(riga, col);
							 table.getModel().setValueAt(new Boolean(true), riga, col);
							 data[riga][col] = new Boolean(true);
							 newValue = (Boolean)table.getModel().getValueAt(riga, col);}	 
					  	}	
				  	/* 3 - *************  */
					  if (cellaValore && (j!=col)){
						// la cella deve diventare FALSE e le altre FALSE 
						 if (j!=col){
						 }
					  }	
				  /* 4 - *************  */
				  	if (cellaValore && (j==col)){
					// la cella deve diventare TRUE e le altre FALSE 
					 if (j==col){
						 oldValue = (Boolean)table.getModel().getValueAt(riga, col);
						 table.getModel().setValueAt(new Boolean(false), riga, col);
						 data[riga][col] = new Boolean(false);
						 newValue = (Boolean)table.getModel().getValueAt(riga, col);modificato = true;}	 
					 
				  	}
					  	
				 }
			  } 
		  }	 
		if (modificato){
			boolean check=false;
			// avviso che almeno un chek ci deve essere ed imposto di default la prima colonna
			JOptionPane.showMessageDialog(null, "Attenzione almeno un check deve essere vero\n ");
			if (col==0){
			 table.getModel().setValueAt(new Boolean(true), riga, 1);
			 data[riga][1] = new Boolean(true);}
			else{
				table.getModel().setValueAt(new Boolean(true), riga, 0);
				data[riga][0] = new Boolean(true);
			}
		}
	
	
	 table.setModel(new MyTableModel());
}
	  
	class MyTableModel extends AbstractTableModel{
		
		public boolean isCellEditable(int riga, int col) {
			boolean ris;
			row = riga;
			column = col;
			stampaValoriTabella(row, col);
          //Note that the data/cell address is constant,
          //no matter where the cell appears onscreen.			
          if (col == 6 || col ==7) {
   //       	output.append("riga,colonna: " + riga + "," + col + " valore = " + getValueAt(riga, col-1)+ "\n");
          	//setValueAt(new Boolean(true), 1,3);
              ris =  false;
          } else {
              ris = true;
          }         
          return ris;
      }

		  public String getColumnName(int col) {
	            return headerTable[col];
	        }
		public Class getColumnClass(int c){
			return getValueAt(0,c).getClass();
		}
		@Override
		public int getColumnCount() {
			return headerTable.length;
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}
		public void setValueAt(Object value, int row, int col) {
          data[row][col] = value;
          this.fireTableCellUpdated(row, col);
          fireTableDataChanged();
      }
	}		
				
	/**
	 * Imposto il tipo delle variabile che sono stati selezionati, sempre se eventualmente le variabili esistono
	 */
	private void impostaTipoVariabili(){
		int i=0;
		
		Boolean valore = false;
		int nrVar = this.mioDom.getModello().getStateMachine().getVectorVariable().size();
		for (Variable item : this.mioDom.getModello().getStateMachine().getVectorVariable()){
			for (int col = 0; col < 6; col++){
				valore = (Boolean)table.getModel().getValueAt(i, col);
				if (valore){
					switch (col) {
						case  0:
							item.setScopeVariable("GLOBAL");
							break;
						case 1:
							item.setScopeVariable("LOCAL");
							break;
						case 2:
							item.setScopeVariable("TEMP");
							break;
						case 3:
							item.setScopeVariable("INPUT");
							break;
						case 4:
							item.setScopeVariable("OUTPUT");
							break;
						case 5:
							item.setScopeVariable("CONSTANT");
							break;
					}
				}
			}
			i++;
		}
	}
	
	/*************** Pagina WEB di help*********************/
	
	 private JEditorPane createEditorPane() {
	        JEditorPane editorPane = new JEditorPane();
	        editorPane.setEditable(false);
	        java.net.URL helpURL = JFrameContainer.class.getResource(
	                                        "index.html");
	        if (helpURL != null) {
	            try {
	                editorPane.setPage(helpURL);
	            } catch (IOException e) {
	                System.err.println("Attempted to read a bad URL: " + helpURL);
	            }
	        } else {
	            System.err.println("Couldn't find file: index.html");
	        }

	        return editorPane;
	    }
	
		
		public class MyActionListenerHelp implements ActionListener{
			public void actionPerformed (ActionEvent e){
				JEditorPane editor =  createEditorPane();
				
				if (!helpAttivato){
					helpAttivato = true;
					frameHelp.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					helpPanel.add(editor);
					helpPanel.setSize(new Dimension(1300,780));
			        //Add content to the window.
					frameHelp.add(new JScrollPane(helpPanel));
					//frameHelp.setSize(800, 600);
					frameHelp.setPreferredSize(new Dimension(1300,780));
					frameHelp.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/ico.png")));
			        //Display the window.
				}
				frameHelp.pack();
				frameHelp.setVisible(true);
			}
		}
	
	private void setIcon(){
		try{
			 System.out.println("Bye");
			 setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/ico.png")));
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
}