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
import java.awt.geom.AffineTransform;

class Info extends JPanel {

    Personnage perso;    
    ImageIcon vie; 
    ImageIcon cadence;
    ImageIcon cac; 
    ImageIcon munition;
    Jeu j;

    public Info(Personnage p, Jeu j){
	this.perso = p;
	this.j = j;
	vie = new ImageIcon(new ImageIcon("./assets/vie.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
	cadence = new ImageIcon(new ImageIcon("./assets/sonic.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
	cac = new ImageIcon(new ImageIcon("./assets/poing.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
	munition = new ImageIcon(new ImageIcon("./assets/munition.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
	drawInfo(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawInfo(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	
	//g.fillRect(0,0,250,450);
      //  this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
	
	g2d.drawImage(vie.getImage(), 10,20, this);      
	g2d.drawImage(munition.getImage(), 10,90, this);      
	g2d.drawImage(cadence.getImage(), 10,150, this);      
	g2d.drawImage(cac.getImage(), 10,210, this);      
	g2d.setColor(Color.WHITE);
	g2d.setFont(new Font("Verdana", Font.BOLD , 20)); 
	g2d.drawString("Vie : "+perso.getVie(), 70, 50);
	g2d.drawString("Munitions : "+perso.getArme().getMunition(), 70, 120);
	g2d.drawString("Cadence : "+perso.getArme().getCadence(), 70, 180);
	
	if(perso.getCac()==false)
		g2d.drawString("CAC : Non ", 70, 240);
	else if(perso.getCac()==true)
		g2d.drawString("CAC : Oui ", 70, 240);
	
	g2d.drawString("Ennemis : "+j.getEnnemisRestants(),70 , 370);
	g2d.drawString("Score : "+j.getScore(),70 , 400);
	g2d.drawString("Niveau : "+j.getNiveau().getLevel(), 70, 430);
    }
}
