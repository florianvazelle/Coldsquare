import java.util.ArrayList;

public class Enemy extends Personnage{
    private boolean awake;
    private int speed;

    private boolean controlerEnemyAwake; 
    
    public Enemy(String nom, int vie, String skin, int x_de_base, int y_de_base){
	super(nom, vie, skin, x_de_base, y_de_base);
	this.awake = false;
	this.speed = 3;

	this.controlerEnemyAwake = false;
    }

    public Enemy(String nom, int vie, String skin, int x_de_base, int y_de_base, Arme arme){
	super(nom, vie, skin, x_de_base, y_de_base, arme);
	this.awake = false;
	this.speed = 3;

	this.controlerEnemyAwake = false;
    }

    public int distance(int ax, int ay, int bx, int by){
	return (int) Math.sqrt((float) Math.pow((bx - ax), 2.0) + (float) Math.pow((by - ay), 2.0));
    }

    public int approachValues(int start, int end, int shift){
	if(start < end)
            if(start + shift < end)
		return start + shift;
            else
                return end;
	else
            if(start - shift > end)
		return start - shift;
            else
                return end;
    }
    
    public void update(int playerPosX, int playerPosY){
	if(!awake 
	   && distance(super.getCoordonneX(), super.getCoordonneY(), playerPosX, playerPosY) < 250){
	    awake = true;
	}
    }
    
    public boolean getAwake(){
	return this.awake;
    }
    
    public void setAwake(boolean awake){
	this.awake = awake;
    }

    public boolean getControlerEnemyAwake(){
	return this.controlerEnemyAwake;
    }
    
    public void setControlerEnemyAwake(boolean controlerEnemyAwake){
	this.controlerEnemyAwake = controlerEnemyAwake;
    }

    public int getSpeed(){
	return this.speed;
    }
}

