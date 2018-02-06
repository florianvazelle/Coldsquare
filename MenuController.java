import javax.swing.*;
import java.awt.event.*;

public class MenuController implements ActionListener {
	
	int id = 0;
	Menu menu;
	JPanel p = new JPanel();
	
	public MenuController(int i, JPanel panneau) {
		this.id = i;
		this.p = panneau;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (id == 1) {
			//new Jeu();
			menu.f.setVisible(false);
			menu.f.dispose();
		}
		else if (id == 2) {
			menu.affichageCharger();
			menu.card.next(menu.cards);
		}
		else if (id == 3) {
			menu.affichageOptions();
			menu.card.next(menu.cards);
		}
		else if (id == 4) {
			menu.f.setVisible(false);
			menu.f.dispose();
		}
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
