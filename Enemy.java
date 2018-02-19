import java.util.ArrayList;

public class Enemy extends Personnage{
	private boolean awake;
	private boolean[][] map;
	private int direction;
	private int speed;

	public Enemy(String nom, int vie, String skin, int x_de_base, int y_de_base, boolean[][] map){
		super(nom, vie, skin, x_de_base, y_de_base);
		this.awake = false;
		this.map = map;
		this.direction = 2;
		this.speed = 2;
	}

	public Enemy(String nom, int vie, String skin, int x_de_base, int y_de_base, Arme arme, boolean[][] map){
		super(nom, vie, skin, x_de_base, y_de_base, arme);
		this.awake = false;
		this.map = map;
		this.direction = 2;
		this.speed = 2;
	}

	private int distance(int ax, int ay, int bx, int by){
		return (ax - bx) + (ay - by);
	}

	private int approachValues(int ax, int bx, int speed){
		return ax + (bx - ax + speed);
	}

	public void update(int playerPosX, int playerPosY){
		if(!awake 
			&& distance(super.getCoordonneX(), super.getCoordonneY(), playerPosX, playerPosY) < 144)
			awake = true;

		AStar algo = new AStar(76, 41, map);
		ArrayList<Node> path = algo.findPath(new Node(super.getCoordonneX(), super.getCoordonneY()),
			new Node(playerPosX, playerPosY));
		//Soon tm ça change
		if(!path.isEmpty() && awake){
			/* Follow the path */
			int targetX =  	path.get(path.size() - 1).getX();
			int targetY = path.get(path.size() - 1).getY();

			/* Choosing the direction */
			if(targetX > super.getCoordonneX())
				direction = 'E';
			else if(targetX < super.getCoordonneX())
				direction = 'W';
			else if(targetY > super.getCoordonneY())
				direction = 'S';
			else if(targetY < super.getCoordonneY())
				direction = 'N';

			super.setCoordonneX(approachValues(super.getCoordonneX(), targetX, this.speed));
			super.setCoordonneY(approachValues(super.getCoordonneY(), targetY, this.speed));
		}
	}
}