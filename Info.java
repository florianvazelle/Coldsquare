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
    ImageIcon vie = new ImageIcon(new ImageIcon("./assets/vie.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    ImageIcon cadence = new ImageIcon(new ImageIcon("./assets/sonic.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    ImageIcon dispersion = new ImageIcon(new ImageIcon("./assets/dispersion.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    ImageIcon munition = new ImageIcon(new ImageIcon("./assets/munition.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    public Info(Personnage p){
	this.perso = p;

    }

  

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
	drawInfo(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawInfo(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

	g.fillRect(0,0,250,450);
    	g2d.drawImage(vie.getImage(), 10,20, this);      
	g2d.drawImage(munition.getImage(), 10,90, this);      
	g2d.drawImage(cadence.getImage(), 10,150, this);      
	g2d.drawImage(dispersion.getImage(), 10,210, this);      
	g2d.setColor(Color.WHITE);
	g2d.setFont(new Font("Verdana", Font.BOLD , 20)); 
	g2d.drawString("Vie : "+perso.getVie(), 70, 50);
	g2d.drawString("Munitions : "+perso.getArme().getMunition(), 70, 120);
	g2d.drawString("Cadence : "+perso.getArme().getCadence(), 70, 180);
	g2d.drawString("Dispersion : "+perso.getArme().getDispersion(), 70, 240);
	
    }

  

    
}
