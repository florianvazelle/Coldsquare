import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.awt.*;

public class ControlerBalle extends Thread {

    Balle b;
    Point souris;
    JBalle ba;
    MaFenetreJeu frame;

    ControlerBalle(Balle b, Point souris, JBalle ba, MaFenetreJeu frame){
	this.b = b;
	this.souris = souris;
	this.ba = ba;
	this.frame = frame;
    }

    @Override
    public void run(){
	animationTirer();
    }
    
    void animationTirer(){
	int x = b.getX();
	int y = b.getY();
	Point i = new Point(x, y);
	
	/*
	  if(i.getX() == souris.getX()){
	    while(x < frame.getWidth() && y < frame.getHeight() && x > 0 && y > 0 ){
		if(i.getY()>souris.getY())
		    b.setY(y--);
		if(i.getY()<souris.getY())
		    b.setY(y++);
		i = new Point(x,y);
		try{
		    TimeUnit.SECONDS.sleep(5);
		}catch(InterruptedException e){
		    e.printStackTrace();
		}
		//ba.setBounds(x, y, ba.getImage().getImageIcon().getIconWidth(), ba.getImage().getImageIcon().getIconHeight());
		ba.repaint();
                //frame.revalidate();
	    }
        }
	
        else if(i.getY() == souris.getY()){
            while(x < frame.getWidth() && y < frame.getHeight() && x > 0 && y > 0){
                if(i.getX()>souris.getX())
                    b.setX(x--);
		if(i.getX()<souris.getX())
                    b.setX(x++);
                i = new Point(x,y);
                try{
                    TimeUnit.SECONDS.sleep(5);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                //ba.setBounds(x, y, ba.getImage().getImageIcon().getIconWidth(), ba.getImage().getImageIcon().getIconHeight());
                ba.repaint();
                //frame.revalidate();
	    }
        }
	
        else{
	*/
	    // coefficient directeur
            double m = ((double) (souris.getY() - i.getY()) / (double) (souris.getX() - i.getX()));

            // ordonnée a l'origine
	    int p = (int) i.getY() - (int) ( m * i.getX() );

            System.out.println("m : "+m+" & p : "+p);

            if(i.getX()<souris.getX()){
                while(x < frame.getWidth() && y < frame.getHeight() && x > 0 && y > 0){
                    b.setX(x++);
                    y = ((int) (m*x) )+  p;
                    b.setY(y);

		    i = new Point(x,y);
                    try{
                        TimeUnit.MILLISECONDS.sleep(5);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
		    ba.repaint(x-50, y-50, 200, 200);
		}
            }
            if(i.getX()>souris.getX()){
                while(x < frame.getWidth() && y < frame.getHeight() && x > 0 && y > 0){
                    b.setX(x--);
                    y = ((int) (m*x) )+  p;
                    b.setY(y);

                    i = new Point(x,y);
                    try{
                         TimeUnit.MILLISECONDS.sleep(5);
                    }catch(InterruptedException e){
                        e.printStackTrace();
		    }
		    ba.repaint(x-50, y-50, 200, 200);
		}
            }
	    //}
	ba.deleteBalle(b);
    }
}
