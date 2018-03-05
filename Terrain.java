import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Terrain extends JPanel {
    
    private boolean map[][];
    MaFenetreJeu frame;
    ArrayList<Mur> listeMur;
    ImageIcon arbre;
    ImageIcon fond;
    
    public Terrain(MaFenetreJeu frame){
	this.frame = frame;
	listeMur = new ArrayList<Mur>();

	this.map = new boolean[frame.getWidth()/25][frame.getHeight()/25];

	initMap();
	
	this.arbre = new ImageIcon(new ImageIcon("./assets/arbre.png").getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT));
	this.fond = new ImageIcon(new ImageIcon("./assets/fond.png").getImage().getScaledInstance(1920,1920,Image.SCALE_DEFAULT));
    }

    void initMap(){
	this.listeMur.clear();

	Mur haut = new Mur(0, 0, frame.getWidth(), 25);
	Mur gauche = new Mur(0, 0, 25, frame.getHeight());
	Mur droit = new	Mur(frame.getWidth()-25, 0, 25, frame.getHeight());
	/* Trois bas cae visuellement la frame arrive a la taille de la frame moins 55, je ne sais pas pourquoi et les ennemies ayant une hitbox plus
	   petite qu'au debut il peuvent spawner en dessou, donc au cas ou
	*/
	Mur bas	= new Mur(0, frame.getHeight()-55, frame.getWidth(), 25);
	Mur bas2 = new Mur(0, frame.getHeight()-30, frame.getWidth(), 25);
	Mur bas3 = new Mur(0, frame.getHeight()-5, frame.getWidth(), 25);
	
	listeMur.add(haut);
	listeMur.add(gauche);
	listeMur.add(droit);
	listeMur.add(bas);
	listeMur.add(bas2);
	listeMur.add(bas3);
	
	for(int i = 0 ; i!=1920/25 ; i++){
            for(int j = 0 ; j!=1040/25 ; j++){
		if(i >= 2 && i <= 4 && j >= 2 && j <= 4 )
		    this.map[i][j] = false;
		else
		    this.map[i][j] = (Math.random() > 0.97) ? true : false;
                if(this.map[i][j])
                    listeMur.add(new Mur(i*25, j*25, 25, 25));
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	drawMur(g);
    }
    
    public void drawMur(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	
	g2d.drawImage(this.fond.getImage(), 0, 0, this);
	
	for(int i = 0;i!=listeMur.size();i++){
	    Mur currentMur = listeMur.get(i);

	    //HITBOX
	    /*
	      g.setColor(Color.BLUE);
	      g.drawRect((int)currentMur.getHitbox().getX(), (int)currentMur.getHitbox().getY(), currentMur.getHitbox().getWidth(), currentMur.getHitbox().getHeight());
	    */
	    
	    if(currentMur.getLongueur() >= currentMur.getLargeur()){
		for(int j = 0 ; j<currentMur.getLongueur() ; j+=25)
		    g2d.drawImage(this.arbre.getImage(), currentMur.getX()+j, currentMur.getY(), this);
	    }else{
		for(int j = 0 ; j<currentMur.getLargeur() ; j+=25)
		    g2d.drawImage(this.arbre.getImage(), currentMur.getX(), currentMur.getY()+j, this);
	    }
	}
    }
    
    public ArrayList<Mur> getMur(){
	return this.listeMur;
    }
    
    public boolean[][] getMap(){
	return this.map;
    }
}
