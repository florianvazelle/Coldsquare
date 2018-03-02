import java.awt.*;
import java.awt.event.*;

public class ControlerPause implements ActionListener {
    MenuPause mp;
    MaFenetreJeu frame;
    
    ControlerPause(MenuPause mp, MaFenetreJeu frame){
	this.mp = mp;
	this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
	Object o = e.getSource();
	if(mp.reprendre == o){ 
	    mp.setEnPause(false);
	    frame.getLayeredPane().remove(mp);
	    frame.setFocusable(true);                                                                                                                                                      
            frame.requestFocus();
	    frame.getLayeredPane().repaint();
	}
    }
}
