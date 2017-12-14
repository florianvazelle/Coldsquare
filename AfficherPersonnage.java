import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.Event;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;

class AfficherPersonnage extends JPanel {
    ArrayList<Personnage> personnageVisible;
    
    public AfficherPersonnage(){
	this.personnageVisible = new ArrayList<Personnage>();
    }

    void addPersonnageVisible(Personnage perso){
	this.personnageVisible.add(perso);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
	doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	
	//Changement

	g2d.rotate(Math.toRadians(30), 50, 50);
		
	if(!personnageVisible.isEmpty()){
            g2d.drawImage(personnageVisible.get(0).listeDeSprite.get(0).getImage(), personnageVisible.get(0).getCoordonneX(), personnageVisible.get(0).getCoordonneY(), this);
	}
    }

}
