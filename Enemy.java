import java.util.ArrayList;

public class Enemy extends Personnage{
	private boolean awake;
	private boolean[][] map;
	private int direction;

	public Enemy(String nom, int vie, String skin, int x_de_base, int y_de_base, boolean[][] map){
		super(nom, vie, skin, x_de_base, y_de_base);
		this.awake = false;
		this.map = map;
		this.direction = 2;
	}

	public Enemy(String nom, int vie, String skin, int x_de_base, int y_de_base, Arme arme, boolean[][] map){
		super(nom, vie, skin, x_de_base, y_de_base, arme);
		this.awake = false;
		this.map = map;
		tis.direction = 2;
	}

	private int distance(int ax, int ay, int bx, int by){
		return (ax - bx) + (ay - by);
	}

	public void update(int playerPosX, int playerPosY){
		if(!awake 
			&& distance(super.getCoordonneX(), super.getCoordonneY(), playerPosX, playerPosY) < 144)
			awake = true;

		AStar algo = new AStar(76, 41, map);
		ArrayList<Node> path = algo.findPath(new Node(super.getCoordonneX(), super.getCoordonneY()),
												new Node(playerPosX, playerPosY));

		if(path && awake){
			
		}
	}
}