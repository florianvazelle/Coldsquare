import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Deplacement extends JPanel {

    private int direction_x;
    private int direction_y;
    Personnage perso;

    public Deplacement(Personnage perso) {
        this.perso=perso;
    }

    public void move() {
        int x = this.perso.getCoordonneX();
        int y = this.perso.getCoordonneY();

        x += direction_x;
        y += direction_y;

        this.perso.setCoordonneX(x);
        this.perso.setCoordonneY(y);
    }

    public void annulerMove(Point p){
	this.perso.setCoordonneX((int) p.getX());
        this.perso.setCoordonneY((int) p.getY());
    }
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            direction_x = -3;
        }

        if (key == KeyEvent.VK_RIGHT) {
            direction_x = 3;
        }

        if (key == KeyEvent.VK_UP) {
            direction_y = -3;
        }

        if (key == KeyEvent.VK_DOWN) {
            direction_y = 3;
        }
        
        if(key == KeyEvent.VK_ESCAPE) {
	    JPanel panneau = new JPanel();
	    JButton reprendre = new JButton("Reprendre");
	    JButton sauvegarder = new JButton("Sauvegarder");
	    JButton quitter = new JButton("Quitter");
	    panneau.setLayout(new GridBagLayout());
	    GridBagConstraints contraintes = new GridBagConstraints();
    		
	    contraintes.gridx = 1;
	    contraintes.gridy = 0;
	    contraintes.ipady = 100;
	    contraintes.fill = GridBagConstraints.HORIZONTAL;
	    contraintes.anchor = GridBagConstraints.CENTER;
	    contraintes.insets = new Insets(10,30,30,10);
	    panneau.add(reprendre, contraintes);
	    
	    contraintes.gridx = 1;
	    contraintes.gridy = 1;
	    panneau.add(sauvegarder,contraintes);
	    
	    contraintes.gridx = 1;
	    contraintes.gridy = 2;
	    panneau.add(quitter,contraintes);

	    Sauvegarde s = new Sauvegarde();
	    
	    System.out.println("Echap appuyé");
		
    	}

	if(key == KeyEvent.VK_R) {
            perso.getArme().setMunition(perso.getArme().getMunition()+2);
        }
    }
 public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

	if (key == KeyEvent.VK_LEFT) {
	    direction_x = 0;
        }

	if (key == KeyEvent.VK_RIGHT) {
            direction_x = 0;
	}

	if (key == KeyEvent.VK_UP) {
            direction_y = 0;
	}

	if (key == KeyEvent.VK_DOWN) {
            direction_y = 0;
        }
    }

}
