import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class Recommencer implements ActionListener {

    private Niveau n;
    private Jeu j;
    
    public Recommencer(Jeu jeu) {
	this.j = jeu;
	
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	j.jouer();
    }

}
