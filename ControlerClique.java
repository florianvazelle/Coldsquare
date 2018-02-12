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
import java.awt.event.MouseMotionListener;

public class ControlerClique implements MouseListener {
    MaFenetreJeu frame;
    Personnage perso;
    AfficherPersonnage af;
    JBalle ba;
    MenuPause mp;
    
    ControlerClique(Personnage perso, AfficherPersonnage af, MaFenetreJeu frame, JBalle ba, MenuPause mp){
	this.perso = perso;
	this.af = af;
        this.frame=frame;
	this.ba = ba;
	this.mp = mp;
    }
    
    public void mouseClicked(MouseEvent e){
	if(!(mp.getEnPause()))
	    (new Tirer(perso, frame, af, ba, mp)).start();
	//af.repaint();
	//frame.revalidate();
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}
