import java.util.ArrayList;

public class Enemy extends Personnage{
    private boolean awake;
    private boolean[][] map;
    private int direction;
    private int speed;
    Terrain t;
    ControlerEnemy ce;
    ArrayList<Node> path;
    AStar algo;
    
    public Enemy(String nom, int vie, String skin, int x_de_base, int y_de_base, boolean[][] map, Terrain t){
	super(nom, vie, skin, x_de_base, y_de_base);
	this.awake = false;
	this.map = map;
	this.direction = 2;
	this.speed = 2;
	this.t = t;
	this.algo = new AStar(76, 41, map, this, t);
    }
    
    void setCe(ControlerEnemy ce){
	this.ce = ce;
    }

    private int distance(int ax, int ay, int bx, int by){
	return (int) Math.sqrt((float) Math.pow((bx - ax), 2.0) + (float) Math.pow((by - ay), 2.0));
    }
    
    private int approachValues(int start, int end, int shift){
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
	System.out.println("--------------------------------NEW UPDATE--------------------------------");
	if(!awake 
	   && distance(super.getCoordonneX(), super.getCoordonneY(), playerPosX, playerPosY) < 250){
	    awake = true;
	    
	}
	
	/*if(awake && distance(super.getCoordonneX(), super.getCoordonneY(), playerPosX, playerPosY) < 400)
	  ce.tirer();
	*/
	if(awake){
	    if(distance(super.getCoordonneX(), super.getCoordonneY(), playerPosX, playerPosY) > 100 )
		path = algo.findPath(new Node(super.getCoordonneX()/25, super.getCoordonneY()/25), new Node(playerPosX/25, playerPosY/25));
	    
	    if(!path.isEmpty()){
		
		int targetX = path.get(path.size() - 1).getX();
		int targetY = path.get(path.size() - 1).getY();
	
		super.setCoordonneX(approachValues(super.getCoordonneX(), targetX*25, 3));
		super.setCoordonneY(approachValues(super.getCoordonneY(), targetY*25, 3));
	    }
	}
    }
}
