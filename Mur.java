import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Mur {
    private int x;
    private int y;
    private int longueur;
    private int largeur;
    private Hitbox hb;
    MaFenetreJeu frame;
    
    public Mur(MaFenetreJeu frame){
	this.frame = frame;
	this.x = parcoureAxe(0);
	this.y = parcoureAxe(0);
	this.longueur = parcoureAxe(this.x);
	this.largeur = parcoureAxe(this.y);
	this.hb = new Hitbox(this);
    }

    public Mur(int x, int y, int longueur, int largeur){
	this.x = x;
	this.y = y;
	this.longueur = longueur;
	this.largeur = largeur;
	this.hb = new Hitbox(this);
    }

    int parcoureAxe(int valInit){
	int i;
	for(i = valInit ; i != frame.getWidth() ; i++){
	    if((Math.random() > 0.95) ? true : false) break;
	}
	return i;
    }

    public int getX(){
	return this.x;
    }

    public int getY(){
	return this.y;
    }

    public int getLongueur(){
	return this.longueur;
    }

    public int getLargeur(){
	return this.largeur;
    }

    public Hitbox getHitbox(){
	return this.hb;
    }
}
    
