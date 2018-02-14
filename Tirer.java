import java.util.*;
import java.util.Timer;
import java.awt.*;
import java.io.*;
import javax.sound.sampled.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JLayeredPane;


public class Tirer extends Thread {
    Personnage perso;
    MaFenetreJeu frame;
    AfficherPersonnage af;
    JBalle ba;
    ControlerBalle controlerBalle;
    MenuPause mp;
    Terrain t;
    
    public Tirer(Personnage perso, MaFenetreJeu frame, AfficherPersonnage af, JBalle ba, MenuPause mp, Terrain t){
	this.perso = perso;
	this.frame = frame;
	this.af = af;
	this.ba = ba;
	this.mp = mp;
	this.t = t;
    }

    public void run(){
	tirer();	
    }

    public synchronized void tirer(){
	int nbMun = this.perso.getArme().getMunition();
	if(nbMun>0){
	    this.perso.getArme().setMunition(nbMun-1);

	    //CHANGEMENT
	    Point souris = new Point(perso.getRotationX(),perso.getRotationY());
	    Balle b = new Balle(perso, souris, frame);
	    frame.getLayeredPane().add(ba, JLayeredPane.MODAL_LAYER);
	    ba.listeBalle.add(b);
	    controlerBalle = new ControlerBalle(b, souris, ba, frame, mp, t);
	    controlerBalle.start();
	    	    
	    //Son du tire
	    Son fusil = new Son("sounds/206.wav");
	    InputStream stream = new ByteArrayInputStream(fusil.getSamples());
	    fusil.play(stream);
	    
	    System.out.println("Munition : "+this.perso.getArme().getMunition());	
	    
	    try{
		TimeUnit.SECONDS.sleep(this.perso.getArme().getCadence());
	    }catch(InterruptedException e){
		e.printStackTrace();
	    }
	}
    }  
}
