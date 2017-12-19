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

class Sprite {
    private int x;
    private int y;
    private int hauteur;
    private int longueur;
    ImageIcon i;
    private Image image;

    public Sprite(int x, int y, int hauteur, int longueur, Personnage perso){
        this.x=x;
        this.y=y;
        this.hauteur=hauteur;
        this.longueur=longueur;
        this.i = new ImageIcon(perso.getSkin());
        this.image = i.getImage();
    }

    Image getImage(){
        return this.image;
    }

    MonPoint getCentre(){
	double x = i.getIconHeight()/2;
	double y = i.getIconWidth()/2;
	MonPoint p = new MonPoint(x,y);
	return p;
    }
}
