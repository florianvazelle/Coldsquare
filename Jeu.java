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
    Jeu(){
        this.frame = new MaFenetreJeu();
        this.af = new AfficherPersonnage();
        frame.add(af);
        initSteve();

    }

    void initSteve(){
        Personnage Steve = new Personnage("Steve",5,"./assets/sprite.jpg",50,50);
        Steve.addListeDeSprite(new Sprite(0,0,50,100,Steve));
        af.addPersonnageVisible(Steve);
        Deplacement deplacement = new Deplacement(Steve);
        frame.addKeyListener(new DeplacementControler(deplacement,af,frame));
	frame.addMouseListener(new ControlerSouris(Steve));
	af.repaint();
        frame.revalidate();
    }
}
