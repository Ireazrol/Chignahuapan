package application;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.ListCellRenderer;
/**
 * @web https://www.youtube.com/channel/UCQn4vCkPzibtBMVa38bVFNA
 * @author Jheyson Matta
 */
public class JChomboRenderer  extends JLabel implements ListCellRenderer{

    private JMenuItem[] items;
    /** Constrcutor de clase */
    public JChomboRenderer( JMenuItem[] items )
    {
        setOpaque(true);
        this.items = items;            
    }
    
    public Component getListCellRendererComponent(JList list, Object value,  int index, boolean isSelected, boolean cellHasFocus) {
       int selectedIndex = ((Integer)value).intValue();
       JMenuItem icon = this.items[selectedIndex];
       setIcon( icon.getIcon() );
       setToolTipText(icon.getLabel());
       //setText(icon.getLabel());
       if (isSelected == true) { 
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
       } else {
            //setText(icon.getLabel());
            setBackground(list.getBackground());
            setForeground(list.getForeground());
       }
       Rectangle visibleRect = new Rectangle(new Dimension(20, 10)); 
       computeVisibleRect(visibleRect);
       
        return this;
        
    }
    
}
