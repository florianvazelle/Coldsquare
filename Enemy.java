import java.util.ArrayList;

public class Enemy extends Personnage{
    private ArrayList<Node> path;
    private AStar algo;
    private boolean awake;
    private boolean[][] map;
    private int speed;
    private int[][] ancienPos;

    Terrain t;
    ControlerEnemy ce;
    
    public Enemy(String nom, int vie, String skin, int x_de_base, int y_de_base, boolean[][] map, Terrain t){
	super(nom, vie, skin, x_de_base, y_de_base);
	this.awake = false;
	this.map = map;
	this.speed = 3;
	this.t = t;
	this.algo = new AStar(76, 41, map, this, t);

	this.ancienPos = new int[2][2];
	this.ancienPos[0][0] = 0;
	this.ancienPos[0][1] = 0;
	this.ancienPos[1][0] = 0;
	this.ancienPos[1][1] = 0;
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
	if(!awake 
	   && distance(super.getCoordonneX(), super.getCoordonneY(), playerPosX, playerPosY) < 250){
	    awake = true;
	    
	}

	int varX = super.getCoordonneX();
	int varY = super.getCoordonneY();
	
	/*if(awake && distance(super.getCoordonneX(), super.getCoordonneY(), playerPosX, playerPosY) < 400)
	  ce.tirer();
	*/
	if(awake){
	    if(distance(varX, varY, playerPosX, playerPosY) > 100 && !(ancienPos[0][0] == varX/25 && ancienPos[0][1] == varY/25 && ancienPos[1][0] == playerPosX/25 && ancienPos[1][1] == playerPosY/25) ){
		System.out.println("here");
		path = algo.findPath(new Node(super.getCoordonneX()/25, super.getCoordonneY()/25), new Node(playerPosX/25, playerPosY/25));
		ancienPos[0][0] = varX/25;
		ancienPos[0][1] = varY/25;
		ancienPos[1][0] = playerPosX/25;
		ancienPos[1][1] = playerPosY/25;
	    }
	    
	    if(!path.isEmpty()){
		
		int targetX = path.get(path.size() - 1).getX();
		int targetY = path.get(path.size() - 1).getY();
		
		super.setCoordonneX(approachValues(varX, targetX*25, speed));
		super.setCoordonneY(approachValues(varY, targetY*25, speed));
	    }
	}
    }
}
