import java.util.ArrayList;

public class AStar {
	private class Node {
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

	private ArrayList<Node> closedList; // Contains nodes sorted by their heuristic
	private ArrayList<Node> openList; // Contains all nodes to be sorted
	private final int xLimit;
	private final int yLimit;
	private final boolean[][] map;

	public AStar(int xMax, int yMax, boolean[][] map){
		this.xLimit = xMax;
		this.yLimit = yMax;
		this.closedList = new ArrayList<Node>();
		this.openList = new ArrayList<Node>();
		this.map = map;
	}

	public byte compareNodes(Node a, Node b){
		if(a.getHeuristic() < b.getHeuristic())
			return 1;
		else if(a.getHeuristic() == b.getHeuristic())
			return 0;
		else
			return -1;
	}

	private ArrayList<Node> getNeighbours(Node current){
		ArrayList<Node> directions = new ArrayList<Node>(); // Contains every node adjacent to the current position
		ArrayList<Node> neighbours = new ArrayList<Node>(); // Contains every node adjacent and in the map limits
		int checkX;
		int checkY;

		for(int i = -1; i < 1; i++){
			for(int j = -1; j < 1; j++){
				directions.add(new Node(i, j)); // Preparing all the directions
			}
		}

		for(Node n : directions){ /* For every direction, checking if it's in the map limits */
			checkX = current.getX() + n.getX();
			checkY = current.getY() + n.getY();

			if(checkX < xLimit && checkY < yLimit)
				/* If so, then the node is added to the list of neighbours */
				neighbours.add(new Node(checkX, checkY, map[checkX][checkY])); 
		}

		return neighbours;
	}

	private ArrayList<Node> retracePath(Node start, Node target){
		ArrayList<Node> path = new ArrayList<>();
		Node current = target;

		while(current != start){
			path.add(current);
			current = current.getParent();
		}

		return path;
	}

	private int getDistance(Node current, Node target){
		int distX = current.getX() - target.getX();
		int distY = current.getY() - target.getY();

		if(distX > distY)
			return 14*distY + 10*(distX - distY);
		else
			return 14*distX + 10*(distY - distX);
	}

	public ArrayList<Node> shortestWay(Node start, Node target){
		openList.add(start);
		Node current;
		int i = 0;

		while(openList.size() > 0){
			current = openList.get(0);
			i = 1;

			while(i < openList.size()){
				/* Get the node that costs less */
				if(openList.get(i).getCost() < current.getCost()
					|| openList.get(i).getCost() == current.getCost()
					&& openList.get(i).getHeuristic() < current.getHeuristic())
					current = openList.get(i);
				i += 1;
			}

			/* Remove the current node from the waiting list */
			openList.remove(current);
			closedList.add(current);

			/* If the current node is the target */
			if(current.equals(target))
				/* Retrace path */
				return retracePath(start, target);

			/* Retrieve current node's neighbours */
			ArrayList<Node> neighbours = getNeighbours(current);

			for(Node neighbour : neighbours){
				/* If the neighbour is walkable and in the waiting list */
				if(neighbour.isWalkable() && !closedList.contains(neighbour)){
					/* Calc the cost to move toward this neighbour */
					int newMovementCostToNeighbour = current.getCost() + getDistance(current, target);

					/* If the new way to this neighbour is shorter */
					if(newMovementCostToNeighbour < neighbour.getCost()
						|| !openList.contains(neighbour)){
						/* Update heuristic and moving costs */
						neighbour.setCost(newMovementCostToNeighbour);
						neighbour.setHeuristic(target);
						neighbour.setFCost();

						/* Assign current node as neighbour's parent */
						neighbour.setParent(current);

						if(!openList.contains(neighbour))
							openList.add(neighbour);
					}
				}
			}
		}

		return closedList;
	}
}