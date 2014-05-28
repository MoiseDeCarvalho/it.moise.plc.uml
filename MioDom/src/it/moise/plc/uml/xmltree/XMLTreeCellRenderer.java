package it.moise.plc.uml.xmltree;



import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * Changes how the tree displays elements.
 */
public class XMLTreeCellRenderer extends DefaultTreeCellRenderer {
    
    //colors for tree items
    private final Color elementColor = new Color(0, 0, 128);
    private final Color textColor = new Color(0, 128, 0);
 
    //remove icons
    public XMLTreeCellRenderer() {
        setOpenIcon(new ImageIcon("open.gif"));
        setClosedIcon(new ImageIcon("closed.gif"));
        setLeafIcon(new ImageIcon("leaf.gif"));
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        JDOMAdapterNode adapterNode = (JDOMAdapterNode)value;
        if(adapterNode.node.isRootElement()) {
            value = adapterNode.node.getName();
        } else if(adapterNode.node.getChildren().size() > 0) {
            value = adapterNode.node.getName();
        } else {
            value = adapterNode.node.getName() +" ["+adapterNode.node.getTextTrim()+"]";
        }
        
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        
        if(!selected) {
            if(adapterNode.node.getTextTrim().length() == 0) {
                setForeground(elementColor);
            } else {
                setForeground(textColor);
            }
        }
        
        return this;
        
    }
}

