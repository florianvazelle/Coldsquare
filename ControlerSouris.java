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

public class ControlerSouris implements MouseMotionListener {

    AfficherPersonnage af;
    JFrame frame;
    Personnage perso;
    private int mX, mY;
    
    public ControlerSouris(Personnage perso, AfficherPersonnage af, MaFenetreJeu frame){
	this.perso=perso;
	this.af=af;
	this.frame=frame;
    }
    
    public void mouseMoved(MouseEvent me) {
	mX = (int) me.getPoint().getX();
	mY = (int) me.getPoint().getY();
	perso.setRotationX(mX);
	perso.setRotationY(mY);
	af.repaint(perso.getCoordonneX()-100,perso.getCoordonneY()-100,220,220);
    }
    
    public void mouseDragged(MouseEvent me) {
	mouseMoved(me);
	af.repaint(perso.getCoordonneX()-50,perso.getCoordonneY()-50,120,120);
    }
}
