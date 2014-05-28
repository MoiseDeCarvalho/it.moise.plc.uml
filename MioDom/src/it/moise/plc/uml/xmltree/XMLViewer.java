package it.moise.plc.uml.xmltree;



import it.moise.plc.uml.gui.ImageButton;
import it.moise.plc.uml.gui.JFileChooserComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;


/**
 * Simple gui to display an xml tree.
 */
public class XMLViewer extends JPanel {
    private String nameFileXML = "-1";
    private final String title = "JDOM XML Tree";
    private JToolBar toolBar = new JToolBar();
    private JPanel topPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    
  //  private final MenuBar menuBar = new MenuBar();
  //  private final Menu fileMenu = new Menu();
  //  private final MenuItem open = new MenuItem();
  //  private final JFileChooser fileChooser = new JFileChooser();
    private JFileChooser fc;
  //  ImageButton xmlButton;
    
    private final XMLTree xmlTree;
    private File file;
    private JTree tree;
    private Exception exception;
    
    private final int windowHeight = 290;
    private final int leftWidth = 230;
    private final int rightWidth = 970;
    private final int windowWidth = leftWidth + rightWidth;
    private final Font treeFont = new Font("Lucida Console", Font.BOLD, 14);
    private final Font textFont = new Font("Lucida Console", Font.PLAIN, 13);
    private FileReader fileXML = new FileReader("outputFileCodesys.xml");
   
    /**
     * Creates a simple gui for viewing xml in a tree.
     * @throws IOException 
     */
    public XMLViewer() throws IOException {        
    	this.setName("Browser");
       // setTitle(getClass().getSimpleName());
       // setPreferredSize(new Dimension(windowWidth, windowHeight));
       // setFocusable(true);
        //setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);        
    	//this.xmlButton = new ImageButton("open.png","" , "open", "OpenFile");
        xmlTree = new XMLTree();
        
        /****************************************************************************
         * 
         * 
         */
        
        
        try {
            xmlTree.parseFile(fileXML);
        } catch (Exception e) {
            exception = e;
        }
        tree = xmlTree.getTree();
     //   display();
        
        
        //////////////////////////////////////
   //     xmlButton.addActionListener(new MyActionListener());
     //   fileMenu.setLabel("File");
        this.setLayout(new BorderLayout());
      //  this.toolBar.add(this.xmlButton);
       // this.topPanel.add(this.toolBar);
      //  this.add(this.topPanel, BorderLayout.LINE_START);
       // open.setLabel("Browse");
       // fileMenu.add(open);
       // menuBar.add(fileMenu);
        
      
		
		//JRadioButtonTypePOU sceltaPou = new JRadioButtonTypePOU(globalVar);
	
        
       //this.add(xmlButton, BorderLayout.NORTH);
       
        
       
        
        //open.dispatchEvent(new ActionEvent(open,1001,open.getActionCommand()));
   //}
    
    /**
     * Construct a frame of the most recently read-in document.
     */
    
    
    /**
     * Displays the tree.
     * 
     * @param tree JTree to display
     */
   // public void display() {
        try {
            
            
            JScrollPane treeScrollPane = null;
            JScrollPane textScrollPane = null;
            
            // Build left-side view
            if(tree != null) {
                tree.setFont(treeFont);        
                treeScrollPane = new JScrollPane(tree);
                treeScrollPane.setPreferredSize(new Dimension(leftWidth, windowHeight));
            } else {
                JEditorPane errorMessagePane = new JEditorPane();
                errorMessagePane.setEditable(false);
                errorMessagePane.setContentType("text/plain");
                errorMessagePane.setText("Error: unable to build tree from xml:\n"+ exception.toString());
                System.out.println("Error: unable to build tree from xml:\n"+ exception.toString());
                errorMessagePane.setCaretPosition(0);
                treeScrollPane = new JScrollPane(errorMessagePane);
            }
            
            // Build right-side view
            if(fileXML != null) {
                StringBuilder sb = new StringBuilder();
                
                //TODO show validation
                String path = System.getProperty("user.dir")+"\\outputFileCodesys.xml".replace("\\", "/");
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(path));
                    String line = "";
                    String percorso = (new File(".")).getAbsolutePath();
                    System.out.println("\n" + percorso);
                    System.out.println("\n" + System.getProperty("user.dir"));
                    
                    reader.close();
                    reader = new BufferedReader(new FileReader(path));
                    while((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    reader.close();
                } catch (Exception e) {
                    System.err.println("exception when reading file for display");
                    e.printStackTrace();
                } 
                
                JEditorPane textPane = new JEditorPane();
                textPane.setEditable(false);
                textPane.setContentType("text/plain");
                textPane.setText(sb.toString());
                textPane.setCaretPosition(0);
                textPane.setFont(textFont);
                textScrollPane = new JScrollPane(textPane);
                textScrollPane.setPreferredSize(new Dimension(rightWidth, windowHeight));
            }
    
            // Build split-pane view
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                    treeScrollPane, textScrollPane);
            
            splitPane.setContinuousLayout(true);
            splitPane.setDividerLocation(leftWidth);
            splitPane.setPreferredSize(new Dimension(windowWidth + 10,
                    windowHeight + 10));
    
            // Add GUI components
     //       this.centerPanel.setLayout(new BorderLayout());
            this.centerPanel.add("Center", splitPane);
            this.add(this.centerPanel);
       //     this.add(this.centerPanel, BorderLayout.CENTER);
          //  pack();
            setVisible(true);
        } catch (Exception e) {
            System.err.println("error when updating xml viewer");
            e.printStackTrace();
        }
        this.revalidate();
        
        
    
      
    }
/*    
     //listener for when user selects a file to view 
    private class MyActionListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == xmlButton) {
            	String nameButton ="";
				ImageButton who = (ImageButton) ae.getSource(); // sorgente dell evento
				nameButton = who.getName();
				fc = new JFileChooser();
				file = new File("aa.txt");
				JOptionPane.showMessageDialog(null, "directory" + file.getPath());
				fc.addChoosableFileFilter(new FileFilter(){
					private final String xml = "xml";
					
					public boolean accept(File f){
						if (f.isDirectory())
							return true;
						String nameFile = f.getName();
						String ext = nameFile.split("\\.")[1];
						return ext.contains("xml");
					}
					public String getDescription(){ return "Solo file .xml"; }
					
				});
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fc.setFileFilter(new XMLFileFilter());
				//fc.setCurrentDirectory(new File("C:/"));
				if (getNameFileUml() != null){
				//	JOptionPane.showMessageDialog(null, "JToolBarComponent " + fc.getNameFileUml() +" " +fc.getPathFileUml());
				// devo verificare che il file selezionato sia di tipo uml	
				//	setNameFileUml(getPathFileUml());
					
				}

                int returnVal = fc.showOpenDialog(fc);
            	;
                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    //reset for currently selected message
                    exception = null;
                    
                    file = fc.getSelectedFile();
                    
                    // update the gui for this file
                    //JOptionPane.showMessageDialog(null, title + " | " + (file != null ? file.getAbsolutePath() : "Select A File"));
                    
                    // remember last directory used
                    fc.setCurrentDirectory(file);
                    
                    try {
                        xmlTree.parseFile(new FileReader(file));
                    } catch (Exception e) {
                        exception = e;
                    }
                    tree = xmlTree.getTree();
                    display();
                }
            }
        }
    }
  */  
  //  public static void main(String[] argv) {
  //      new XMLViewer();
  //  }
    
    
    
    public void setNameFileUml(String name){
		this.nameFileXML = name;
		
	}
	public String getNameFileUml(){
		return this.nameFileXML;
	}
}

