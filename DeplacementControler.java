import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class DeplacementControler extends KeyAdapter {

    Deplacement d;
    AfficherPersonnage af;
    MaFenetreJeu frame;
    MenuPause mp;
    
    DeplacementControler(Deplacement d, AfficherPersonnage af, MaFenetreJeu frame, MenuPause mp){
        this.d=d;
        this.af=af;
        this.frame=frame;
	this.mp = mp;
    }

    public void keyTyped(KeyEvent e){    }

    @Override
    public void keyReleased(KeyEvent e) {
	d.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();

	if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	    mp.setEnPause(true);
	    frame.getLayeredPane().add(mp, new Integer(5));
        }
	if(!(mp.getEnPause())){
	    d.keyPressed(e);
	    
	    Personnage Steve = af.personnageVisible.get(0);
	    Point p = new Point(Steve.getCoordonneX(), Steve.getCoordonneY());
	    d.move();
	    for(int i = 1 ; i!=af.personnageVisible.size();i++){
		if(af.personnageVisible.get(i).getVie()>0)
		if(Hitbox.collision(Steve.getHitbox()/*Forcement Steve*/,af.personnageVisible.get(i).getHitbox())){
		    d.annulerMove(p);
		}
	    }
	    
	    for(int i = 0 ; i!=af.boiteMunition.size();i++){
		if(Hitbox.collision(Steve.getHitbox(),af.boiteMunition.get(i).getHitbox())){
		    //d.annulerMove(p);
		    System.out.println("pos S/B : "+ Steve.getHitbox().getX()+" "+ Steve.getHitbox().getY()+" "+af.boiteMunition.get(i).getHitbox().getX()+ " "+af.boiteMunition.get(i).getHitbox().getY() );
		    Steve.getArme().setMunition(Steve.getArme().getMunition()+af.boiteMunition.get(i).getValue());
		    af.boiteMunition.get(i).setHitbox(new Hitbox());
		    af.boiteMunition.get(i).setAfficher(2);
		    System.out.println("ok : + "+ af.boiteMunition.get(i).getValue()+" "+i);
		    
		}
	    } 
	    
	    af.repaint();
	    //frame.revalidate();
	}
    }
}
