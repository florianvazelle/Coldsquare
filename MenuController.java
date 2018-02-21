import javax.swing.*;
import java.awt.event.*;

public class MenuController implements ActionListener {
	
    int id = 0;
    Menu menu;
    JPanel p = new JPanel();
    public String nom;

    public MenuController() {

    }
    
    public MenuController(int i, JPanel panneau) {
	this.id = i;
	this.p = panneau;
    }
    
    public void actionPerformed(ActionEvent e) {
	if (id == 1) {
	    menu.card.show(menu.cards, menu.listContent[3]);
	}
	else if (id == 2) {
	    menu.card.show(menu.cards, menu.listContent[2]);
	}
	else if (id == 3) {
	    menu.card.show(menu.cards, menu.listContent[1]);
	    
	}
	else if (id == 4) {
	    menu.f.setVisible(false);
	    menu.f.dispose();
	}
	else if (id == 5) {
	    menu.card.show(menu.cards, menu.listContent[0]);
	}
	else if (id == 6) {
	    nom = menu.pseudo.getText();
	    System.out.println(""+nom);
	    new Jeu(this);
	    menu.f.setVisible(false);
	    menu.f.dispose();
	}
    }
    public void setMenu(Menu menu) {
	this.menu = menu;
    }

    public String getNom() {
	return this.nom;
    }

}
