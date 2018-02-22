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
		return Math.sqrt((float) Math.pow((float) (bx - ax), 2.0) + Math.pow((float) (by - ay), 2.0));
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
				return end
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
				direction = '1';
			else if(targetX < super.getCoordonneX())
				direction = '3';
			else if(targetY > super.getCoordonneY())
				direction = '2';
			else if(targetY < super.getCoordonneY())
				direction = '0';

			super.setCoordonneX(approachValues(super.getCoordonneX(), targetX, this.speed));
			super.setCoordonneY(approachValues(super.getCoordonneY(), targetY, this.speed));
		}
	}
}