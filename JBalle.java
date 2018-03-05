import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.AffineTransform;

public class JBalle extends JPanel {

    private AfficherPersonnage af;
    private MaFenetreJeu frame;
    private Jeu j;
    ArrayList<Balle> listeBalle;

    JBalle(AfficherPersonnage af, MaFenetreJeu frame, Jeu j){
        this.af = af;
        this.frame = frame;
	this.j = j;
        this.listeBalle = new ArrayList<Balle>();
        //setOpaque(true);
	setBackground(new Color(0,0,0,0));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
	int tailleInit = listeBalle.size();

	Personnage Steve = af.getSteve();
	
	for(int j = 0 ; j < tailleInit ; j++){
            if(tailleInit != listeBalle.size()) break; 
	    Balle currentBalle = listeBalle.get(j);
	    for(int i = 0 ; i != af.personnageVisible.size();i++){
		Personnage currentPerso = af.personnageVisible.get(i);
		if(Hitbox.collision(currentBalle.getHitbox(), currentPerso.getHitbox()) && currentBalle.getTireur() != currentPerso ){
		    balleToucher(currentPerso, currentBalle,  i+1);
		}
		else if(Hitbox.collision(currentBalle.getHitbox(), Steve.getHitbox()) && currentBalle.getTireur() != Steve){
		    balleToucher(Steve, currentBalle, 0);
		}		
	    }
	}
	
        if(!af.personnageVisible.isEmpty()){
            for(int i = 0 ; i < tailleInit ; i++){
		if(tailleInit != listeBalle.size()) break;
		Balle currentBalle = listeBalle.get(i);

		AffineTransform t = new AffineTransform();
                t.translate(currentBalle.getX(), currentBalle.getY());
		g2d.drawImage(currentBalle.getSprite().getImage(), t, null);
		
		//HITBOX
		//g.drawRect((int)currentBalle.getHitbox().getX(), (int)currentBalle.getHitbox().getY(), currentBalle.getHitbox().getWidth(), currentBalle.getHitbox().getHeight());
	    }
        }
    }

    void balleToucher(Personnage currentPerso, Balle currentBalle, int i){
	currentPerso.setVie(currentPerso.getVie()-1);
	Personnage Steve= af.getSteve();
	if(currentPerso.getVie() == 0 && currentPerso != Steve) {
	    this.j.setScore(this.j.getScore()+20);
	    this.j.setEnnemisRestants(this.j.getEnnemisRestants()-1);
	    Random r = new Random();
	    int valeur = 1+r.nextInt(4 - 1);
	    
	    if(valeur<=2) {
		Boite b = new Boite("./assets/boite_munition.png",currentPerso.getCoordonneX(), currentPerso.getCoordonneY()-50, i);
		af.addMunition(b);
	    }
	}
	this.j.jlp.repaint();
	currentBalle.setEnJeu(false);
	currentBalle.setHitbox(new Hitbox());
	
	if(this.j.verifWin()){
	    Niveau n = this.j.getNiveau();
	    Steve.setVie(n.getVie());
	    Steve.getArme().setCadence(n.getCadence());
	    Steve.getArme().setMunition(n.getBalle());
	    this.j.changerNiveau();
	}
    }
    
    void deleteBalle(Balle b){
	listeBalle.remove(b);
    }
}
