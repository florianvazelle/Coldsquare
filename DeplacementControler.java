import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class DeplacementControler extends KeyAdapter {
    Deplacement d;
    AfficherPersonnage af;
    MaFenetreJeu frame;
    MenuPause mp;
    Terrain t;
    Jeu j;
    DeplacementControler(Deplacement d, AfficherPersonnage af, MaFenetreJeu frame, MenuPause mp, Terrain t, Jeu j){
        this.d=d;
        this.af=af;
        this.frame=frame;
	this.mp = mp;
	this.t = t;
	this.j=j;
    }

    public void keyTyped(KeyEvent e){    }

    @Override
    public void keyReleased(KeyEvent e) {
	d.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();
		
	if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	    mp.setEnPause(true);
	    frame.getLayeredPane().add(mp, new Integer(5));
        }
	if( (!(mp.getEnPause())) && (!(j.n.getWin())) ){
	    d.keyPressed(e);
	    
	    Personnage Steve = af.getSteve();
	    Point p = new Point(Steve.getCoordonneX(), Steve.getCoordonneY());
	    d.move();
	    for(int i = 0 ; i!=af.personnageVisible.size();i++){
		if(af.personnageVisible.get(i).getVie()>0)
		    if(Hitbox.collision(Steve.getHitbox(), af.personnageVisible.get(i).getHitbox())){
			d.annulerMove(p);
		    }
	    }
	    
	    for(int i = 0 ; i != t.listeMur.size() ; i++){
		if(Hitbox.collision(Steve.getHitbox(), t.listeMur.get(i).getHitbox())){
		    d.annulerMove(p);
		}
	    }
	    
	    for(int i = 0 ; i != af.boiteMunition.size() ; i++){
		if(Hitbox.collision(Steve.getHitbox(), af.boiteMunition.get(i).getHitbox())){
		    Steve.getArme().setMunition(Steve.getArme().getMunition()+af.boiteMunition.get(i).getValue());
		    af.boiteMunition.get(i).setHitbox(new Hitbox());
		    af.boiteMunition.get(i).setAfficher(2);
		}
	    } 

	    for(int i = 0 ; i != af.personnageVisible.size();i++){
        	if(af.personnageVisible.get(i).getVie()>0){
		    
		    //Incrementer IA
		    Enemy currentEnemy = af.personnageVisible.get(i);
		    currentEnemy.update(Steve.getCoordonneX(), Steve.getCoordonneY());
		    if(currentEnemy.getAwake() && !currentEnemy.getControlerEnemyAwake()){
			(new ControlerEnemy(currentEnemy, frame, af, j.ba, mp, t)).start();
			currentEnemy.setControlerEnemyAwake(true);
		    }
		}
	    }
	    af.repaint();
	}
    }
}
