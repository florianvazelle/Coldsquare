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
import java.awt.geom.AffineTransform;

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
	drawFond(g);
	doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	
	//Changement
	int i = 0;

	// Va garder en memoire la transformation de base c'est a dire aucune
	AffineTransform originalTransform = g2d.getTransform();
	
	// Calcul Vecteur
	// Coordonnée centre image
	double Xa = 8 /* personnageVisible.get(i).listeDeSprite.get(0).getCentre().getX() */ +personnageVisible.get(i).getCoordonneX(); 
	double Ya = 30 /*personnageVisible.get(i).listeDeSprite.get(0).getCentre().getY()*/+personnageVisible.get(i).getCoordonneY();

	// Coordonnée Souris
	double Xb = personnageVisible.get(i).getRotationX();
	double Yb = personnageVisible.get(i).getRotationY();

	double pi = 4* Math.atan(1);
		
	double degree = (Math.atan2((Yb-Ya),(Xb-Xa))+pi/2)*(180/pi);

	g2d.rotate(Math.toRadians(degree), Xa, Ya);// a changer en fonction du sprite courant
		
	if(!personnageVisible.isEmpty()){
	    for(i=0;i< personnageVisible.size();i++){
		g2d.drawImage(personnageVisible.get(i).listeDeSprite.get(0).getImage(), personnageVisible.get(i).getCoordonneX(), personnageVisible.get(i).getCoordonneY(), this);      
		g2d.setTransform(originalTransform); // Reinitialise la transformation comme sauvegarder ulterierement
	    }
	}
    }

    // Je pense qu'il faudra généraliser la boucle for(i=0;i< personnageVisible.size();i++){ car par la suite il faudra calculer la rotation des ennemie
    
    private void drawFond(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	ImageIcon i = new ImageIcon("./assets/fond.jpg");
	Image imagefond= i.getImage();
	g2d.drawImage(imagefond,0,0,this);      
	
    }

    
}
