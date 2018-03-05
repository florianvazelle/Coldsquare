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

public class Jeu{
    
    AfficherPersonnage af;
    MaFenetreJeu frame;
    Info i;
    Terrain fond;
    JBalle ba;
    MenuPause mp;
    Sauvegarde s;
    Recommencer r;
    Personnage p;
    Niveau n;
    JLayeredPane jlp;
    private int ennemisRestants;
    private int enCours=1;
    private int score = 0;
    public MenuController mc;

    int vie;
    int cadence;
    int enemis;
    String pseudo;
    int munitions;
    int level;
    boolean cac;
    int scoreN;
	
    public Jeu(int v, int c, int m, int e, int l, int sc, boolean ca, String p) {
	this.vie = v;
	this.cadence = c;
	this.munitions = m;
	this.enemis = e;
	this.level = l;
	this.score = sc;
	this.cac = ca;
	this.pseudo = p;
	this.n = new Niveau(this);
	this.s = new Sauvegarde(this);
	this.r = new Recommencer(this);
	this.frame = new MaFenetreJeu();

	jlp = new JLayeredPane();
	frame.setLayeredPane(jlp);
	jlp.setOpaque(true);

	this.fond =  new Terrain(frame);
	this.mp = new MenuPause(frame,s,r);
	this.af = new AfficherPersonnage(this, mp);
	this.ba = new JBalle(af, frame, this);
	
	n.setVie(vie);
	n.setCadence(cadence);
	n.setBalle(munitions);
	n.setEnemis(enemis);
	n.setLevel(level);
	n.setCac(cac);
	setScore(score);

	this.ennemisRestants = n.getEnnemis();
	frame.setLayout(null);

	fond.repaint();
	initSteve();
	for(int ep = 0 ; ep < n.getEnnemis() ; ep++) {
	    initEnnemi();
	}
		
	fond.setBounds(0,0,1920,1040);

	af.setBounds(0,0,1920,1040);
	ba.setBounds(0,0,1920,1040);
	mp.setBounds(0,0,1920,1040);
	i.setBounds(0,500,250,450);
	n.setBounds(0, 0, 1920, 1040);

	af.setOpaque(false);
	ba.setOpaque(false);
	mp.setOpaque(false);

	i.setBackground(new Color(0, 0, 0, 50));

	jlp.add(fond, new Integer(0));
	jlp.add(af, new Integer(1));
	jlp.add(i, new Integer(2));
	jlp.add(ba, new Integer(3));

	frame.revalidate();
	
    }
    
    public Jeu(MenuController m){
	this.n = new Niveau(this);
	this.s = new Sauvegarde(this);
	this.r = new Recommencer(this);
	this.frame = new MaFenetreJeu();
	this.mc = m;
	
	/* Construction du JLayeredPane qui va gerer les differents JPanel */
	jlp = new JLayeredPane();
	frame.setLayeredPane(jlp);
	jlp.setOpaque(true);
	
	/*
	  Ajout au JLayeredPane de :
	  fond -> JPanel qui gerer le fond d'ecran ainsi que le spawn des arbres (obstacles)
	  af -> JPanel qui gerer l'affichage des personnages (Hero et Ennemie)
	  ba -> JPanel qui gerer l'affichage des Balles
	  mp -> JPanel qui correspond au Menu de pause
	*/
	
	this.fond=  new Terrain(frame);
	this.mp = new MenuPause(frame,s,r);
	this.af = new AfficherPersonnage(this, mp);
	this.ba = new JBalle(af, frame, this);
	
	this.ennemisRestants = this.n.getEnnemis();
	frame.setLayout(null);
	
	fond.repaint();
	initSteve();
	for(int e = 0 ; e < n.getEnnemis() ; e++) {
	    initEnnemi();
	}
	
	fond.setBounds(0,0,1920,1040);
	
	af.setBounds(0,0,1920,1040);
	ba.setBounds(0,0,1920,1040);
	mp.setBounds(0,0,1920,1040);
	i.setBounds(0,500,250,450);
	n.setBounds(0, 0, 1920, 1040);
	
	af.setOpaque(false);
	ba.setOpaque(false);
	mp.setOpaque(false);
	
	i.setBackground(new Color(0, 0, 0, 50));
	
	jlp.add(fond, new Integer(0));
	jlp.add(af, new Integer(1));
	jlp.add(i, new Integer(2));
	jlp.add(ba, new Integer(3));
	
	frame.revalidate();
    }
    
    void jouer() {
	
	 this.n = this.getNiveau();
	 this.scoreN = this.getScore();
	 n.setScore(scoreN);
	    
	jlp.repaint();
	af.boiteMunition.clear();
	for(int i = af.personnageVisible.size()-1 ; i >= 0 ; i--){
	    af.personnageVisible.remove(af.personnageVisible.get(i));
	}
	
	Personnage Steve = af.getSteve();
	Steve.setVie(n.getVie());
	Steve.getArme().setCadence(n.getCadence());
	Steve.getArme().setMunition(n.getBalle());
	Steve.setCac(n.getCac());
	Steve.setCoordonneX(50);
	Steve.setCoordonneY(50);
	
	fond.initMap();
	
	int nbEnnemi = n.getEnnemis();
	this.ennemisRestants = nbEnnemi;
	for(int e = 0 ; e < nbEnnemi ; e++) {
	    initEnnemi();
	}
    }
    
    void initSteve(){
	
	Arme gunSteve = new Arme();
	Personnage Steve = new Personnage("Steve",5,"./assets/sprite.png",50,50,gunSteve);
	Steve.addListeDeSprite(new Sprite(Steve));
	Steve.addListeDeSprite(new Sprite("./assets/ennemi.png"));
	Steve.getHitbox().setHeight(Steve.getHitbox().getHeight()-24);
	
	n.setPerso(Steve);
	af.setSteve(Steve);
	Deplacement deplacement = new Deplacement(Steve);
	frame.addKeyListener(new DeplacementControler(deplacement,af,frame, mp, fond, this));
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
	int nb = r.nextInt(1700); 
	int nb2 = r.nextInt(810);

	Random r2 = new Random();
	int nb3 = r2.nextInt(2);
	
	if(nb3 == 0) {
	    nb += 200;
	}else if(nb3 == 1) {
	    nb2 += 200;
	}else if(nb3 == 2) {
	    nb += 200;
	    nb2 += 200;
	}	
	
	Enemy Ennemi = new Enemy("Ennemi", 1, "./assets/ennemi.png", nb, nb2, new Arme(100, 1));
	Ennemi.addListeDeSprite(new Sprite(Ennemi));
	Ennemi.addListeDeSprite(new Sprite("./assets/ennemi_mort1.png"));
	Ennemi.addListeDeSprite(new Sprite("./assets/ennemi_mort2.png"));
	Ennemi.addListeDeSprite(new Sprite("./assets/ennemi_mort3.png"));
	Ennemi.getHitbox().setY(Ennemi.getHitbox().getY()+13);
	Ennemi.getHitbox().setHeight(Ennemi.getHitbox().getHeight()-13);
	
	int distanceX = Ennemi.getCoordonneX()- af.getSteve().getCoordonneX();
	int distanceY = Ennemi.getCoordonneY()- af.getSteve().getCoordonneY();
	
	/*	if( distanceY < 100 && distanceX < 100) {
	    System.out.println("trop pres X: "+ distanceX+ " Y: " + distanceY);
	    }*/
	
	boolean bonnePlace = false;
	while(bonnePlace == false) {
	    for(int i=0;i<fond.getMur().size();i++){
		if(Hitbox.collision(Ennemi.getHitbox(),this.fond.getMur().get(i).getHitbox())){
		    //System.out.println("collisionMur");
		    nb = r.nextInt(1700); 
		    nb2 = r.nextInt(810);
		    nb3= r2.nextInt(2);
		    if(nb3==0) {
			nb += 200;
		    }else if(nb3==1) {
			nb2 += 200;
		    }else if(nb3==2) {
			nb += 200;
			nb2 += 200;
		    }
		    Ennemi.setCoordonneX(nb);
		    Ennemi.setCoordonneY(nb2);
		    
		    i=0;
		    bonnePlace=false;
		}
	    }
	    bonnePlace = true;
	    
	    for(int j = 0 ; j < af.personnageVisible.size();j++){
		
		if(af.personnageVisible.get(j).getVie()>0){
		    if(Hitbox.collision(Ennemi.getHitbox(),af.personnageVisible.get(j).getHitbox())
		       || (j == 0 && Hitbox.collision(Ennemi.getHitbox(), af.getSteve().getHitbox()))){
			nb = r.nextInt(1700); 
			nb2 = r.nextInt(810);
			nb3 = r2.nextInt(2);
			if(nb3==0) {
			    nb += 200;
			}
			else if(nb3==1) {
			    nb2 += 200;
			}
			else if(nb3==2) {
			    nb += 200;
			    nb2 += 200;
			}
			
			Ennemi.setCoordonneX(nb);
			Ennemi.setCoordonneY(nb2);
			
			j = af.personnageVisible.size();
			bonnePlace = false;
		    }
		}     
	    }
	}
	
	af.addPersonnageVisible(Ennemi);
	//System.out.println("Ennemi "+ (af.personnageVisible.size()-1)+" bien place:" +bonnePlace);
	//af.repaint(Ennemi.getCoordonneX()-50, Ennemi.getCoordonneY()-50, Ennemi.getHitbox().getWidth()+100, Ennemi.getHitbox().getHeight()+100);
	af.repaint();
	frame.revalidate();
    }
    
    public void levelComplete(){
	Personnage Steve = af.getSteve();
	Niveau n = this.getNiveau();
	Steve.setVie(n.getVie());
	Steve.getArme().setCadence(n.getCadence());
	Steve.getArme().setMunition(n.getBalle());
	this.changerNiveau();
    }

    boolean verifWin() {
	int nbMort = 0;
	for(int i = 0; i < af.personnageVisible.size() ; i++) {
	    if(af.personnageVisible.get(i).getVie() <= 0) {
		nbMort++;
	    }
	}
	if(nbMort == af.personnageVisible.size()) {
	    return true;
	}
	return false;
    }
    
    void changerNiveau() {
	//jlp.remove(af);
	//jlp.remove(i);
	//jlp.repaint();
	n.setWin(true);
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
    
	public void setPseudo(String nom) {
		this.pseudo = nom;
	}
	
	public String getPseudo() {
		return this.pseudo;
	}
	
    public int getEnnemisRestants() {
	return this.ennemisRestants;
    }
    
    public void setEnnemisRestants(int s) {
	this.ennemisRestants=s;
	i.repaint();
    }
    
}

