public class Tirer extends Thread {
    Personnage perso;
    
    public Tirer(Personnage perso){
	this.perso = perso;
    }

    @Override
    public void run(){
	tirer();	
    }

    public synchronized void tirer(){
	int nbMun = this.perso.getArme().getMunition();
	if(nbMun>0){
	    this.perso.getArme().setMunition(nbMun-1);
	    
	    System.out.println(""+this.perso.getArme().getMunition());	
	    
	    try{
		sleep(this.perso.getArme().getCadence());
	    }catch(InterruptedException e){
	    }
	}
    }
}
