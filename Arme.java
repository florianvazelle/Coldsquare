public class Arme {
    private int munition;
    private int cadence; // dps
    
    Arme(){
	this.munition = 5;
	this.cadence = 1;
    }

    Arme(int munition, int cadence){
        this.munition = munition;
        this.cadence = cadence;
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
