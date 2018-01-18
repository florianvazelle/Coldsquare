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
    FondPanel fond;
    private int nombreEnnemi=3;

    public Jeu(){

	this.frame = new MaFenetreJeu();
	JLayeredPane jlp = new JLayeredPane();
	frame.setLayeredPane(jlp);
	
	jlp.setOpaque(true);

	this.fond=  new FondPanel();
	this.af = new AfficherPersonnage();
	
	frame.setLayout(null);

	initFond();
	initSteve();
	initEnnemi();
	fond.setBounds(0,0,1920,1040);
	af.setBounds(0,0,1920,1040);
	
	af.setOpaque(false);
	i.setBounds(0,500,250,450);

	jlp.add(fond,JLayeredPane.DEFAULT_LAYER);
	jlp.add(af,JLayeredPane.PALETTE_LAYER);
	jlp.add(i,JLayeredPane.POPUP_LAYER);
        
	
	frame.revalidate();
    }

    void initFond(){
	
	fond.repaint();
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

class FondPanel extends JPanel{

    ImageIcon fond;
    public FondPanel()
    {
	fond = new ImageIcon(new ImageIcon("./assets/fond.png").getImage().getScaledInstance(1920,1040,Image.SCALE_DEFAULT));
	System.out.println("fond1");
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
	drawFond(g);
        Toolkit.getDefaultToolkit().sync();
	System.out.println("fond2");
    }
    
    public void drawFond(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
       System.out.println("fond3");
	Image imagefond= fond.getImage();
	System.out.println("fond4");
	g2d.drawImage(imagefond,0,0,this);      
	System.out.println("fond5");
	}
}
