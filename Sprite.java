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
    private ImageIcon i;
    private Image image;

    public Sprite(String s){
	this.i = new ImageIcon(s);
        this.image = i.getImage();
    }
    
    public Sprite(Personnage perso){
        this.i = new ImageIcon(perso.getSkin());
        this.image = i.getImage();
    }

    Image getImage(){
        return this.image;
    }

    ImageIcon getImageIcon(){
        return this.i;
    }
    
    void setImageIcon(ImageIcon i){
        this.i=i;
    }
    void setImage(ImageIcon i){
        this.image=i.getImage();
    }
    
    MonPoint getCentre(){
	double x = i.getIconHeight()/2;
	double y = i.getIconWidth()/2;
	MonPoint p = new MonPoint(x,y);
	return p;
    }

    void redimensionnerSprite(int height, int width, int heightOriginel, int widthOriginel, int heightVoulu, int widthVoulu){
	this.i = new ImageIcon(image.getScaledInstance((heightVoulu*height)/heightOriginel, (widthVoulu*height)/widthOriginel, Image.SCALE_DEFAULT)); 
	this.image = i.getImage();
    }
}
