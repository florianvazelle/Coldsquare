import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class MenuPause extends JPanel {

    private boolean enPause;
    JButton reprendre;
    JButton sauvegarder;
    JButton quitter;
    JButton recommencer;
    ControlerPause cp;
    MaFenetreJeu frame;
    Sauvegarde s;
    Recommencer r;
    Quitter q;
    
    MenuPause(MaFenetreJeu frame, Sauvegarde save, Recommencer recomm){
	this.frame = frame;
	this.s = save;
	this.r = recomm;
	this.q = new Quitter(frame);
	this.enPause = false;
	this.cp = new ControlerPause(this, frame);
	
	this.reprendre = new JButton(new ImageIcon("./assets/Reprendre.png"));
	this.recommencer = new JButton(new ImageIcon("./assets/Recommencer.png"));
	this.sauvegarder = new JButton(new ImageIcon("./assets/Sauvegarder.png"));
	this.quitter = new JButton(new ImageIcon("./assets/Quitter.png"));

	this.reprendre.addActionListener(cp);
	this.recommencer.addActionListener(cp);
	this.sauvegarder.addActionListener(cp);
	this.quitter.addActionListener(cp);
	
	this.setLayout(new GridBagLayout());
	GridBagConstraints contraintes = new GridBagConstraints();
	
	contraintes.gridx = 1;
	contraintes.gridy = 0;
	contraintes.ipady = 100;
	contraintes.fill = GridBagConstraints.HORIZONTAL;
	contraintes.anchor = GridBagConstraints.CENTER;
	contraintes.insets = new Insets(10,30,30,10);
	this.add(reprendre, contraintes);
	
	contraintes.gridx = 1;
	contraintes.gridy = 1;
	this.add(recommencer,contraintes);
	
	contraintes.gridx = 1;
	contraintes.gridy = 2;
	this.add(sauvegarder,contraintes);

	contraintes.gridx = 1;
	contraintes.gridy = 3;
	this.add(quitter,contraintes);

	recommencer.addActionListener((ActionListener) r);
	sauvegarder.addActionListener((ActionListener) s);
	quitter.addActionListener((ActionListener) q);
    }

    @Override
    public void paintComponent(Graphics g){

    }

    boolean getEnPause(){
	return this.enPause;
    }

    void setEnPause(boolean enPause){
	this.enPause = enPause;
    }
}
