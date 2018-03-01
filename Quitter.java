import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Quitter implements ActionListener {

    private MaFenetreJeu frame;
    
    public Quitter(MaFenetreJeu mf) {
	this.frame  = mf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	frame.setVisible(false);
	frame.dispose();
    }


}
