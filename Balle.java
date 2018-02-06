import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Balle {
    
    private int x, y;
    private Point souris;
    private Personnage tireur;
    private Sprite image;
    private Hitbox balleHB;
    private MaFenetreJeu frame;
    
    public Balle(Personnage tireur, Point souris, MaFenetreJeu frame){
	this.tireur = tireur;
	this.x = tireur.getCoordonneX();
	this.y = tireur.getCoordonneY();
	this.souris = souris;
	this.frame = frame;
	this.image = new Sprite("./assets/balle.png");
	this.image.redimensionnerSprite(frame.getHeight(), frame.getWidth(), 283, 283, 20, 20);
	this.balleHB = new Hitbox(this);
    }
            
    public Personnage getTireur(){
	return this.tireur;
    }

    public int getX(){
	return this.x;
    }

    public void setX(int x){
	this.x=x;
	this.balleHB.setX(x);
    }

    public int getY(){
	return this.y;
    }

    public void setY(int y){
	this.y=y;
	this.balleHB.setY(y);
    }

    public Sprite getSprite(){
	return this.image;
    }

    public Hitbox getHitbox(){
	return this.balleHB;
    }
}

