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
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class ControlerSouris implements MouseListener {

    Personnage perso;
	
    public ControlerSouris(Personnage perso){
	this.perso=perso;
    }
    
    public void	mouseClicked(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseEntered(MouseEvent e){
	int sourisX = e.getX();
	int sourisY = e.getY();

	perso.setRotationX(sourisX);
	perso.setRotationY(sourisY);
	
    }
    public void mouseExited(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}
