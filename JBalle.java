import javax.swing.*;
import java.awt.*;
import java.util.*;

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

        for(int j = 0 ; j < tailleInit ; j++){
            if(tailleInit != listeBalle.size()) break; 
	    Balle currentBalle = listeBalle.get(j);
	    for(int i = 0 ; i != af.personnageVisible.size();i++){
		Personnage currentPerso = af.personnageVisible.get(i);
		if(Hitbox.collision(currentBalle.getHitbox(), currentPerso.getHitbox()) && currentBalle.getTireur() != currentPerso){
		    currentPerso.setVie(currentPerso.getVie()-1);
		    if(currentPerso.getVie()==0) {
		    	this.j.setScore(this.j.getScore()+20);
		    	this.j.setEnnemisRestants(this.j.getEnnemisRestants()-1);
		    }
		    Random r = new Random();
	    	int valeur = 1+r.nextInt(4 - 1);
	    	System.out.println(valeur);
	    	if(valeur<=2) {
		    Boite b = new Boite("./assets/boite_munition.png",currentPerso.getCoordonneX(), currentPerso.getCoordonneY()-50,i);
		    af.addMunition(b);
	    	}
		    af.repaint(currentPerso.getCoordonneX()-50,currentPerso.getCoordonneY()-50,120,120);
		    deleteBalle(currentBalle);
		    if(this.j.verifWin()){
		    	Personnage Steve= af.personnageVisible.get(0);
		    	Niveau n = this.j.getNiveau();
		    	Steve.setVie(n.getVie());
		    	Steve.getArme().setCadence(n.getCadence());
		    	Steve.getArme().setMunition(n.getBalle());
		    	this.j.changerNiveau();
			}
		}
	    }
	}
	
        if(!af.personnageVisible.isEmpty()){
            for(int i = 0 ; i < tailleInit ; i++){
		if(tailleInit != listeBalle.size()) break;
		Balle currentBalle = listeBalle.get(i);
		g2d.drawImage(currentBalle.getSprite().getImage(),currentBalle.getX(),currentBalle.getY(), this);
            }
        }
    }

    void deleteBalle(Balle b){
	listeBalle.remove(b);
    }
}
