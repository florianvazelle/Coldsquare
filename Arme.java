public class Arme {
    private int munition;
    private int cadence; // dps
    private int dispersion;
    
    Arme(){
	this.munition = 5;
	this.cadence = 1;
	this.dispersion = 1;
    }
    
    int getMunition(){
	return this.munition;
    }
    
    void setMunition(int newMun){
	this.munition = newMun;
    }
    
    int getDispersion(){
	return this.dispersion;
    }
    
    void setDispersion(int newDispersion){
	this.dispersion = newDispersion;
    }
    
    int getCadence(){
	return this.cadence;
    }
    
    void setCadence(int newCadence){
	this.cadence = newCadence;
    }
}
