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

class Jeu{

    AfficherPersonnage af;
    MaFenetreJeu frame;
    Info i;
    private int nombreEnnemi=3;

    public Jeu(){

	this.frame = new MaFenetreJeu();
	JLayeredPane jlp = new JLayeredPane();
	frame.setLayeredPane(jlp);
	
	jlp.setOpaque(true);
	this.af = new AfficherPersonnage();
	
	frame.setLayout(null);

	initFond();
	initSteve();
	initEnnemi();
	af.setBounds(0,0,1920,1040);
	i.setBounds(0,500,250,450);

	jlp.add(af,JLayeredPane.DEFAULT_LAYER);
	jlp.add(i,JLayeredPane.MODAL_LAYER);
        
	
	frame.revalidate();
    }

    void initFond(){
	
	af.repaint();
	frame.revalidate();
    }

    void initSteve(){

	Arme gunSteve = new Arme();
	Personnage Steve = new Personnage("Steve",5,"./assets/sprite.png",50,50,gunSteve);
	Steve.addListeDeSprite(new Sprite(Steve));
	af.addPersonnageVisible(Steve);
        Deplacement deplacement = new Deplacement(Steve);
        frame.addKeyListener(new DeplacementControler(deplacement,af,frame));
	//frame.addMouseListener(new ControlerSouris(Steve));
        this.i=new Info(Steve);
	i.repaint();
	af.repaint();
        frame.revalidate();
	frame.addMouseMotionListener(new ControlerSouris(Steve, af,frame));
	frame.addMouseListener(new ControlerClique(Steve,af,frame));	   

    }
    
    void initEnnemi(){
	for(int i=0;i<nombreEnnemi;i++){
	    Random r = new Random();
	    int nb = r.nextInt(1890) + 10 ; 
	    int nb2 = r.nextInt(1000) + 10 ; 
	    Personnage Ennemi = new Personnage("Ennemi"+i,5,"./assets/ennemi.png",nb,nb2);
	    Ennemi.addListeDeSprite(new Sprite(Ennemi));
	    af.addPersonnageVisible(Ennemi);
	    af.repaint();
	    frame.revalidate();
	}
    }
}
