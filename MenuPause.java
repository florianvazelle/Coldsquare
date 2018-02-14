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

    boolean enPause = false;
    
    MenuPause(){
	System.out.println("Echap appuye");
	JButton reprendre = new JButton("Reprendre");
	JButton sauvegarder = new JButton("Sauvegarder");
	JButton quitter = new JButton("Quitter");
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
	this.add(sauvegarder,contraintes);
	
	contraintes.gridx = 1;
	contraintes.gridy = 2;
	this.add(quitter,contraintes);
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
