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
    private boolean cac;
    private String skin;
    private int coordonneX;
    private int coordonneY;
    private int rotationX;
    private int rotationY;
    private Hitbox hb;
    private Hitbox hbcc; // Corps a corps
    private boolean aTirer;
    ArrayList<Sprite> listeDeSprite;

    // Faire plusieurs constructeurs pour l'arme
    
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
	this.aTirer = false;
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
	this.aTirer = false;
    }
    
    public void addListeDeSprite(Sprite sp){
        this.listeDeSprite.add(sp);
	if(this.listeDeSprite.size() == 1){
	    this.hb = new Hitbox(this);
	    this.hbcc = new Hitbox(this);
	    this.hbcc.setHeight(this.hbcc.getHeight()+6);
	    this.hbcc.setWidth(this.hbcc.getWidth()+6);
	    this.hbcc.setX(this.hbcc.getX()-3);
	    this.hbcc.setY(this.hbcc.getY()-3);
	}
    }

    public String getNom(){
        return this.nom;
    }

    public int getVie(){
        return this.vie;
    }

    public void setVie(int newVie){
        this.vie=newVie;
    }

    public Arme getArme(){
        return this.arme;
    }

    public void setArme(Arme newArme){
        this.arme=newArme;
    }
    
    public boolean getCac() {
	    return this.cac;
    }
    
    public void setCac(boolean b) {
	   this.cac=b;
    }

    public String getSkin(){
        return this.skin;
    }

    public int getCoordonneX(){
	return this.coordonneX;
    }

    public void setCoordonneX(int newX){
	this.coordonneX=newX;
	hb.setX(newX);
	hbcc.setX(newX-3);
    }

    public int getCoordonneY(){
	return this.coordonneY;
    }

    public void setCoordonneY(int newY){
	this.coordonneY=newY;
	if(this.nom == "Steve")
	    hb.setY(newY+24);
	else
	    hb.setY(newY+13);
	hbcc.setY(newY-3);
    }

    public int getRotationX(){
        return this.rotationX;
    }

    public void setRotationX(int rotationX){
        this.rotationX = rotationX;
    }

    public int getRotationY(){
        return this.rotationY;
    }

    public void setRotationY(int rotationY){
        this.rotationY = rotationY;
    }

    public Hitbox getHitbox(){
	return this.hb;
    }

    public void setHitbox(Hitbox h){
	this.hb = h;
    }
    
    public Hitbox getHitboxCC(){
	return this.hbcc;
    }

    public void setHitboxCC(Hitbox h){
	this.hbcc = h;
    }

    public boolean getATirer(){
	return this.aTirer;
    }

    public void setATirer(boolean aTirer){
	this.aTirer = aTirer;
    }

}
