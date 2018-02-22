import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.awt.*;

public class ControlerBalle extends Thread {

    private Balle b;
    private MonPoint souris;
    private JBalle ba;
    private MaFenetreJeu frame;
    private MenuPause mp;
    private Terrain t;
    
    ControlerBalle(Balle b, MonPoint souris, JBalle ba, MaFenetreJeu frame, MenuPause mp, Terrain t){
	this.b = b;
	this.souris = souris;
	this.ba = ba;
	this.frame = frame;
	this.mp = mp;
	this.t = t;
    }

    @Override
    public void run(){
	animationTirer();
    }
    
    public double avancerX(double length,double angle){
	return Math.cos(angle)*2;
    }
    
    public double avancerY(double length, double angle){
	return Math.sin(angle)*2;
    }
    
    void animationTirer(){
	double x = b.getX();
	double y = b.getY();
	MonPoint i = new MonPoint(x, y);
	
	double pi = 4* Math.atan(1);
	double angle = Math.atan2((souris.getY()-i.getY()),(souris.getX()-i.getX()));
	
	while((x < frame.getWidth() && y < frame.getHeight() && x > 0 && y > 0) && b.getEnJeu() ){
	    
	    x += avancerX(2, angle);
	    b.setX(x);
	    y += avancerY(2, angle);
	    b.setY(y);
	    
	    try{
		TimeUnit.MILLISECONDS.sleep(5);
	    }catch(InterruptedException e){
		e.printStackTrace();
	    }
	    ba.repaint((int)x-50, (int)y-50, 200, 200);

	    /*for(int j = 0 ; j!=t.listeMur.size() ; j++){
	      if(Hitbox.collision(b.getHitbox(), t.listeMur.get(j).getHitbox())){
	      ba.deleteBalle(b);
	      }
	      }
	    */
	    while(mp.getEnPause()){
		try{
		    this.sleep(2000);
		}catch(InterruptedException e){
		    e.printStackTrace();
		}
	    }
	}
	ba.deleteBalle(b);
    }
}

