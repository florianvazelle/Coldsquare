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

public class Niveau extends JPanel{

    private int level;
    private int nbEnnemi;
    private int nbCadence;
    private int nbDispersion;
    private int nbBalle;
    private int nbVie;
    private ImageIcon vie;
    private ImageIcon cadence;
    private ImageIcon balle;
    private ImageIcon dispersion;
    private ImageIcon plus;
    private JButton plusjb;
    private JButton plusjb2;
    private JButton plusjb3;
    private JButton plusjb4;
    private JButton suivant;
    private Personnage perso;
    private int nbAmelioration;
    private Jeu j;
    public Niveau(Jeu j){
    	this.j=j;
    	this.level=1;
    	this.nbEnnemi=1;
    	this.nbCadence=1;
    	this.nbBalle=5;
    	this.nbVie=5;
    	this.nbDispersion=1;
    	vie = new ImageIcon(new ImageIcon("./assets/vie.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    	cadence = new ImageIcon(new ImageIcon("./assets/sonic.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    	dispersion = new ImageIcon(new ImageIcon("./assets/dispersion.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    	balle = new ImageIcon(new ImageIcon("./assets/munition.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    	plus = new ImageIcon(new ImageIcon("./assets/plus.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    	plusjb= new JButton(plus);
    	plusjb2= new JButton(plus);
    	plusjb3= new JButton(plus);
    	plusjb4= new JButton(plus);
    	suivant= new JButton("suivant");

    	plusjb.setBounds(1050,230,50,50);
    	plusjb2.setBounds(1050,290,50,50);
    	plusjb3.setBounds(1050,350,50,50);
    	plusjb4.setBounds(1050,410,50,50);
    	suivant.setBounds(1050,620,250,50);

    	BoutonListener b= new BoutonListener(this);
    	plusjb.addActionListener(b);
    	plusjb2.addActionListener(b);
    	plusjb3.addActionListener(b);
    	plusjb4.addActionListener(b);
    	suivant.addActionListener(b);

    	this.setLayout(null);
    	this.add(plusjb);    
    	this.add(plusjb2);
    	this.add(plusjb3);
    	this.add(plusjb4);
    	this.add(suivant);

    	nbAmelioration=1;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFond(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void drawFond(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
   	g.fillRect(0,0,1920,1040);
 
	g2d.drawImage(vie.getImage(), 800,230, this);      
	g2d.drawImage(balle.getImage(), 800,290, this);      
	g2d.drawImage(cadence.getImage(), 800,350, this);      
	g2d.drawImage(dispersion.getImage(), 800,410, this);      
	g2d.setColor(Color.WHITE);
	g2d.setFont(new Font("Verdana", Font.BOLD , 20)); 
	g2d.drawString("Level "+level+" Complete", 800, 200);
	g2d.drawString("Vie : "+nbVie, 870, 255);
	g2d.drawString("Munitions : "+nbBalle, 870, 315);
	g2d.drawString("Cadence : "+nbCadence, 870, 375);
	g2d.drawString("Dispersion : "+nbDispersion, 870, 435);
//	g2d.drawImage(plus.getImage(), 1050,230, this);      
	//g2d.drawImage(plus.getImage(), 1050,290, this);      
	//g2d.drawImage(plus.getImage(), 1050,350, this);      
	//g2d.drawImage(plus.getImage(), 1050,410, this);  
	g2d.drawString("Amelioration possible : "+nbAmelioration, 800, 550);

    }
 
    public void setPerso(Personnage p) {
    	this.perso=p;
    
    }
    
    public void setNbAmelioration(int nb) {
    	this.nbAmelioration=nb;
    
    }
    
    public int getVie() {
     return this.nbVie;
    }
    public int getCadence() {
        return this.nbCadence;
    }
    public int getBalle() {
    	     return this.nbBalle;
       }
    public int getDispersion() {
    	     return this.nbDispersion;
       }
    public int getEnnemis() {
	     return this.nbEnnemi;
  }

    class BoutonListener implements ActionListener{
    	
    	Niveau n;
    	
    	public BoutonListener(Niveau n) {
    		this.n=n;
    	}
    	
    	public void actionPerformed(ActionEvent e) {
        	Object o= e.getSource();
    		if(nbAmelioration >0) {

        	if(o == plusjb) {
        		nbVie+=1;
        	}else if(o == plusjb2) {
        		nbBalle+=1;
        	}else if(o == plusjb3) {
        		nbCadence+=1;
        	}else if(o == plusjb4) {
        		nbDispersion+=1;
        	}
        	nbAmelioration-=1;
    		}
    		if(o == suivant) {
        		level+=1;
        		nbEnnemi+=1;
        		n.j.setNext();
        		n.j.jouer();
        	}
        	n.repaint();
        }
    	
      }
}
