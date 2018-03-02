public class Arme {
    private int munition;
    private int cadence; // dps
    
    Arme(){
	this.munition = 5;
	this.cadence = 1;
    }
    
    int getMunition(){
	return this.munition;
    }
    
    void setMunition(int newMun){
	this.munition = newMun;
    }
    
    int getCadence(){
	return this.cadence;
    }
    
    void setCadence(int newCadence){
	this.cadence = newCadence;
    }
}

 	    // coefficient directeur
 	    double m = ((double) (souris.getY() - i.getY()) / (double) (souris.getX() - i.getX()));
 	    
 	    // ordonnée a l'origine
 	    int p = (int) i.getY() - (int) ( m * i.getX() );
 
 	    if(i.getX()<souris.getX()){	    
 		while(x < frame.getWidth() && y < frame.getHeight() && x > 0 && y > 0){
 		    ba.setX(x++);			
 		    y = ((int) (m*x) )+  p;
 		    ba.setY(y);
 		    
 		    i = new Point(x,y);

			
			
