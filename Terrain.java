import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Terrain extends JPanel {

    private Boolean map[][];
    MaFenetreJeu frame;
    ArrayList<Mur> listeMur;
    ImageIcon arbre;
    ImageIcon fond;
    
    public Terrain(MaFenetreJeu frame){
	this.frame = frame;
	listeMur = new ArrayList<Mur>();
	Mur haut = new Mur(0, 0, frame.getWidth(), 25);
	Mur gauche = new Mur(0, 0, 25, frame.getHeight());
        //Mur info = new Mur(-1, 475, 275, 525);
        Mur droit = new	Mur(frame.getWidth()-25, 0, 25, frame.getHeight());
        Mur bas	= new Mur(0, frame.getHeight()-55, frame.getWidth(), 25);

	listeMur.add(haut);
	listeMur.add(gauche);
	//listeMur.add(info);
	listeMur.add(droit);
	listeMur.add(bas);

	this.map = new Boolean[frame.getWidth()/25][frame.getHeight()/25];
	
	for(int i = 0 ; i!=frame.getWidth()/25 ; i++){
	    for(int j = 0 ; j!=frame.getHeight()/25 ; j++){
		this.map[i][j] = (Math.random() > 0.95) ? true : false;
		if(this.map[i][j])
		    listeMur.add(new Mur(i*25, j*25, 25, 25));
	    }
	}
	
	this.arbre = new ImageIcon(new ImageIcon("./assets/arbre.png").getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT));
	this.fond = new ImageIcon(new ImageIcon("./assets/fond.png").getImage().getScaledInstance(1920,1920,Image.SCALE_DEFAULT));
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
	    if(currentMur.getLongueur() >= currentMur.getLargeur()){
		for(int j = 0 ; j<currentMur.getLongueur() ; j+=25)
		    g2d.drawImage(this.arbre.getImage(), currentMur.getX()+j, currentMur.getY(), this);
	    }else{
		for(int j = 0 ; j<currentMur.getLargeur() ; j+=25)
                    g2d.drawImage(this.arbre.getImage(), currentMur.getX(), currentMur.getY()+j, this);
	    }
	}
	/*
	for(int i = 0 ; i!=frame.getWidth()/25 ; i++){
            for(int j = 0 ; j!=frame.getHeight()/25 ; j++){
		if(this.map[i][j])
		    g2d.drawImage(this.arbre.getImage(), i*25, j*25, this);
	    }
	    }*/
    }
    
    public ArrayList<Mur> getMur(){
	return this.listeMur;
    }
    
}