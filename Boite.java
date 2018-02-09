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

class Boite {
    private int value;
    private Sprite s;
    private int coordonneX;
    private int coordonneY;
    private Hitbox hb;
    private int id;
    private int afficher;

    // Faire plusieurs constructeur pour l'arme
    

    public Boite(String path, int x_de_base, int y_de_base,int id) {
    	Random r = new Random();
    	int valeur = 1 + r.nextInt(3 - 1);
    	this.value=valeur;
    	this.s= new Sprite(path);
	    ImageIcon im_mun = new ImageIcon(s.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
	    s.setImageIcon(im_mun);
	    s.setImage(im_mun);
    	this.coordonneX= x_de_base;
    	this.coordonneY= y_de_base;
    	this.hb= new Hitbox(this);
    	this.id=id; // Numéro du perso tué dans l'arraylist AfficherPersonnage
    	afficher=0;
	}

    
    Sprite getSprite(){
        return this.s;
    }
    
    Image getImage(){
        return this.s.getImage();
    }

    int getValue(){
        return this.value;
    }
    
    void setValue(int value){
    	this.value=value;
    	
    }
    
    int getAfficher(){
        return this.afficher;
    }
    
    void setAfficher(int value){
    	this.afficher=value;
    	
    }
    
    int getId(){
        return this.id;
    }
    
    int getCoordonneX(){
	return this.coordonneX;
    }

    void setCoordonneX(int newX){
	this.coordonneX=newX;
	hb.setX(newX);
    }

    int getCoordonneY(){
	return this.coordonneY;
    }

    void setCoordonneY(int newY){
	this.coordonneY=newY;
	hb.setY(newY);
    }

    Hitbox getHitbox(){
	return this.hb;
    }

    void setHitbox(Hitbox h){
	this.hb = h;
    }
}

