import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class Recommencer implements ActionListener {

    private Niveau n;
    private Jeu j;
	private int score;
    
    public Recommencer(Jeu jeu) {
	this.j = jeu;
	n = this.j.getNiveau();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	    score = n.getScore();
	j.setScore(score);
	    j.jouer();
    }

}
