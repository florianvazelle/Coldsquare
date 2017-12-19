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
	int i = 0;

	// Calcul Vecteur
	// Coordonnée centre image
	double Xa = personnageVisible.get(i).listeDeSprite.get(0).getCentre().getX(); 
	double Ya = personnageVisible.get(i).listeDeSprite.get(0).getCentre().getY();

	// Coordonnée Souris
	double Xb = personnageVisible.get(i).getRotationX();
	double Yb = personnageVisible.get(i).getRotationY();

	double pi = 4* Math.atan(1);
		
	double degree = (Math.atan2((Yb-Ya),(Xb-Xa))+pi/2)*(180/pi);//personnageVisible.get(i).getRotationX() - personnageVisible.get(i).getRotationY();
	System.out.println(""+degree);
	g2d.rotate(Math.toRadians(degree),personnageVisible.get(i).listeDeSprite.get(0).getCentre().getX(), personnageVisible.get(i).listeDeSprite.get(0).getCentre().getY());// a changer en fonction du sprite courant
		
	if(!personnageVisible.isEmpty()){
	    for(i=0;i< personnageVisible.size();i++){
		g2d.drawImage(personnageVisible.get(i).listeDeSprite.get(0).getImage(), personnageVisible.get(i).getCoordonneX(), personnageVisible.get(i).getCoordonneY(), this);      
	    }
	}
    }
    
}
