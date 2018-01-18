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

class Personnage {
    private String nom;
    private int vie;
    private Arme arme;
    private String skin;
    private int coordonneX;
    private int coordonneY;
    private int rotationX;
    private int rotationY;
    private Hitbox hb;
    ArrayList<Sprite> listeDeSprite;

    // Faire plusieur constructeur pour l'arme
    
    public Personnage(String nom, int vie, String skin, int x_de_base, int y_de_base){
        this.nom=nom;
        this.vie=vie;
        this.arme= new Arme();
        this.skin=skin;
        this.coordonneX=x_de_base;
        this.coordonneY=y_de_base;
        this.listeDeSprite = new ArrayList<Sprite>();
	this.rotationX = 0;
        this.rotationY = 0;
    }

    public Personnage(String nom, int vie, String skin, int x_de_base, int y_de_base, Arme arme){
	this.nom=nom;
        this.vie=vie;
        this.arme=arme;
        this.skin=skin;
        this.coordonneX=x_de_base;
        this.coordonneY=y_de_base;
        this.listeDeSprite = new ArrayList<Sprite>();
	this.rotationX = 0;
        this.rotationY = 0;
    }
    
    void addListeDeSprite(Sprite sp){
        this.listeDeSprite.add(sp);
	if(this.listeDeSprite.size() == 1)
	    hb = new Hitbox(this);
    }

    String getNom(){
        return this.nom;
    }

    int getVie(){
        return this.vie;
    }

    void setVie(int newVie){
        this.vie=newVie;
    }

    Arme getArme(){
        return this.arme;
    }

    void setArme(Arme newArme){
        this.arme=newArme;
    }

    String getSkin(){
        return this.skin;
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

    int getRotationX(){
        return this.rotationX;
    }

    void setRotationX(int rotationX){
        this.rotationX = rotationX;
    }

    int getRotationY(){
        return this.rotationY;
    }

    void setRotationY(int rotationY){
        this.rotationY = rotationY;
    }

    Hitbox getHitbox(){
	return this.hb;
    }
}
