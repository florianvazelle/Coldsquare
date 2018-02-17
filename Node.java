public class Node {
	private int x, y;
	private int cost, heuristic, fcost;
	private final boolean walkable;
	private Node parent;
 
	public Node(int x, int y){
		this.x = x;
		this.y = y;
		this.cost = 0;
		this.heuristic = 0;
		this.fcost = 0;
		this.walkable = true;
		this.parent = null;
	}

	public Node(int x, int y, boolean walkable){
		this.x = x;
		this.y = y;
		this.cost = 0;
		this.heuristic = 0;
		this.fcost = 0;
		this.walkable = walkable;
		this.parent = null;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public Node getParent(){
		return parent;
	}

	public int getHeuristic(){
		return heuristic;
	}

	public int getCost(){
		return cost;
	}

	public int getFCost(){
		return fcost;
	}

	public boolean isWalkable(){
		return this.walkable;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public void setHeuristic(Node target){
		this.heuristic = this.cost + ((this.x - target.getX()) + (this.y - target.getY()));
	}

	public void setParent(Node parent){
		this.parent = parent;
	}

	public void setCost(int cost){
		this.cost = cost;
	}

	public void setFCost(){
		this.fcost = this.cost + this.heuristic;
	}
}