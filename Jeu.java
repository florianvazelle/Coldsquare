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
    Terrain fond;
    JBalle ba;
    MenuPause mp;
    
    Personnage p;
    Niveau n;
    JLayeredPane jlp;
    private int nombreEnnemi=1;
    private int enCours=1;
    private int score = 0;
    
    public Jeu(){
	this.n= new Niveau(this);
	this.frame = new MaFenetreJeu();

	jlp = new JLayeredPane();
	frame.setLayeredPane(jlp);
	
	jlp.setOpaque(true);
	this.fond=  new Terrain(frame);
	this.af = new AfficherPersonnage();
	this.ba = new JBalle(af, frame, this);
	this.mp = new MenuPause(frame);
	
	frame.setLayout(null);

	initFond();
	initSteve();
	for(int e=0;e<n.getEnnemis();e++) {
	    initEnnemi();
	}
	fond.setBounds(0,0,1920,1040);
	af.setBounds(0,0,1920,1040);
	ba.setBounds(0,0,1920,1040);
	mp.setBounds(0,0,1920,1040);
	i.setBounds(0,500,250,450);
	n.setBounds(150, 150, 1600, 700);

	af.setOpaque(false);
	ba.setOpaque(false);
	mp.setOpaque(false);

	i.setBackground(new Color(0, 0, 0, 50));
	
	jlp.add(fond, new Integer(0));
	jlp.add(af,  new Integer(1));
	jlp.add(i,  new Integer(2));
	jlp.add(ba,  new Integer(3));
		
	frame.revalidate();
    }
    
   void jouer() {
	
    	jlp.repaint();
    	af.boiteMunition.clear();
	for(int i=af.personnageVisible.size()-1;i>0;i--){
	    af.personnageVisible.remove(af.personnageVisible.get(i));
	}
	Personnage Steve = af.personnageVisible.get(0);
    	Steve.setVie(n.getVie());
    	Steve.getArme().setCadence(n.getCadence());
    	Steve.getArme().setDispersion(n.getDispersion());
    	Steve.getArme().setMunition(n.getBalle());
	
    	int nbEnnemi=n.getEnnemis();
    	for(int e=0;e<nbEnnemi;e++) {
	    initEnnemi();
	}
    }
    
    void initFond(){
	fond.repaint();
    }
    
    void initSteve(){
	
	Arme gunSteve = new Arme();
	Personnage Steve = new Personnage("Steve",5,"./assets/sprite.png",50,50,gunSteve);
	Steve.addListeDeSprite(new Sprite(Steve));
	Steve.addListeDeSprite(new Sprite("./assets/ennemi.png"));
	Steve.getHitbox().setHeight(Steve.getHitbox().getHeight()-24);
	
	n.setPerso(Steve);
	af.addPersonnageVisible(Steve);
        Deplacement deplacement = new Deplacement(Steve);
        frame.addKeyListener(new DeplacementControler(deplacement,af,frame, mp, fond));
	//frame.addMouseListener(new ControlerSouris(Steve));
        this.i = new Info(Steve, this);
	i.repaint();
	af.repaint();
        frame.revalidate();
	frame.addMouseMotionListener(new ControlerSouris(Steve, af,frame));
	frame.addMouseListener(new ControlerClique(Steve, af, frame, ba, mp,this, fond));	   
    }
    
      void initEnnemi(){
	  
	Random r = new Random();
	int nb = r.nextInt(1890) + 10 ; 
	int nb2 = r.nextInt(1000) + 10 ;

	Personnage Ennemi = new Personnage("Ennemi"+i,1,"./assets/ennemi.png",nb,nb2);
	Ennemi.addListeDeSprite(new Sprite(Ennemi));
	Ennemi.addListeDeSprite(new Sprite("./assets/ennemi_mort1.png"));

	for(int i=0;i<fond.getMur().size();i++){
	    if(Hitbox.collision(Ennemi.getHitbox(),this.fond.getMur().get(i).getHitbox())){
		System.out.println("collisionMur");
	         nb = r.nextInt(1890) + 10 ; 
		 nb2 = r.nextInt(1000) + 10 ;
		 Ennemi.setCoordonneX(nb);
		 Ennemi.setCoordonneY(nb2);
		 i=0;
	    }
	    
	   for(int j = 0 ; j!=af.personnageVisible.size();j++){
	       System.out.println("collisionPerso1"+af.personnageVisible.size());
	       if(af.personnageVisible.get(j).getVie()>0){
		    System.out.println("collisionPerso2");
		   if(Hitbox.collision(Ennemi.getHitbox(),af.personnageVisible.get(j).getHitbox())){
		    System.out.println("collisionPerso3");
		    nb = r.nextInt(1890) + 10 ; 
		    nb2 = r.nextInt(1000) + 10 ;
		    Ennemi.setCoordonneX(nb);
		    Ennemi.setCoordonneY(nb2);
		    i=0; 
		   }
	    }   
	     
	   }
	}
	
	af.addPersonnageVisible(Ennemi);
	af.repaint();
	frame.revalidate();
    }

    public void levelComplete()
    {
	Personnage Steve= af.personnageVisible.get(0);
	Niveau n = this.getNiveau();
	Steve.setVie(n.getVie());
	Steve.getArme().setCadence(n.getCadence());
	Steve.getArme().setDispersion(n.getDispersion());
	Steve.getArme().setMunition(n.getBalle());
	this.changerNiveau();
	
    }
    
    boolean verifWin() {
        int nbMort=0;
        for(int i=1; i < af.personnageVisible.size() ; i++) {
	    if(af.personnageVisible.get(i).getVie() <= 0) {
       		nbMort++;
	    }
        }
        if(nbMort == af.personnageVisible.size()-1) {
	    return true;
        }
	return false;
    }
    
    void changerNiveau() {
	//jlp.remove(af);
	//jlp.remove(i);
	//jlp.repaint();
	jlp.add(n,  new Integer(4));	
	n.repaint();
	
    }
    
    void setNext() {
	this.enCours=1;
	jlp.remove(n);
    }
    
    public Niveau getNiveau() {
    	return this.n;
    }
    
    public int getScore() {
	return this.score;
    }
    
    public void setScore(int s) {
    	this.score=s;
    }
    
}

