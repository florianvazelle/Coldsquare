import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class DeplacementControler extends KeyAdapter {

    Deplacement d;
    AfficherPersonnage af;
    MaFenetreJeu frame;

    DeplacementControler(Deplacement d, AfficherPersonnage af, MaFenetreJeu frame){
        this.d=d;
        this.af=af;
        this.frame=frame;
    }

    public void keyTyped(KeyEvent e){
    	if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
    		
    		frame.add(panneau);
    	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
	d.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
	d.keyPressed(e);
	Point p = new Point(af.personnageVisible.get(0).getCoordonneX(), af.personnageVisible.get(0).getCoordonneY());
	d.move();
	for(int i = 1 ; i!=af.personnageVisible.size();i++){
	    if(af.personnageVisible.get(i).getVie()>0)
		if(Hitbox.collision(af.personnageVisible.get(0).getHitbox()/*Forcement Steve*/,af.personnageVisible.get(i).getHitbox())){
		    d.annulerMove(p);
		}
	}
	af.repaint();
	//frame.revalidate();
    }
}
