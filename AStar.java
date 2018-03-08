import java.awt.Color;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.*;

public class AStar {
    private static Node [][] grid = new Node[5][5];
    private PriorityQueue<Node> openList; // Contains all nodes to be sorted
    private boolean closedList[][]; // Contains nodes sorted by their heuristic
    private final boolean[][] map;

    private Enemy enemy;
    private Terrain t;

    private int V_H_COST = 10;
    private int DIAGONAL_COST = 14;
    
    private final int xLimit;
    private final int yLimit;
    private static int startI, startJ;
    private static int endI, endJ;
        
        public AStar(int xMax, int yMax, boolean[][] map, Enemy enemy, Terrain t){
	this.xLimit = xMax;
	this.yLimit = yMax;
	this.openList = new PriorityQueue<Node>((Object o1, Object o2) -> {
		Node n1 = (Node) o1;
		Node n2 = (Node) o2;
		return compareNodes(n1, n2);
	    });
	this.map = map;
	this.t = t;
	this.enemy = enemy;
    }

    public static void setBlocked(int i, int j){
        grid[i][j] = null;
    }
    
    public static void setStartNode(Node n){
        startI = n.getX();
        startJ = n.getY();
    }
    
    public static void setEndNode(Node n){
        endI = n.getX();
        endJ = n.getY(); 
    }
    
    public int compareNodes(Node a, Node b){
	return a.finalCost<b.finalCost?-1:
	    a.finalCost>b.finalCost?1:0;
	
    }
    
    public void checkAndUpdateCost(Node current, Node t, int cost){
	/* If the neighbour is walkable and in the waiting list */
	if(t == null || closedList[t.getX()][t.getY()] || grid[t.getX()][t.getY()] == null) return;
	int t_final_cost = t.heuristic+cost;
	
	boolean inOpen = openList.contains(t);
	/* If the new way to this neighbour is shorter */
	if(!inOpen || t_final_cost<t.finalCost){
	    /* Update heuristic and moving costs */
	    t.finalCost = t_final_cost;
	    
	    /* Assign current node as neighbour's parent */
	    t.setParent(current);
	    
	    if(!inOpen)
		openList.add(t);
	}
    }

    private ArrayList<Node> getNeighbours(ArrayList<Node> directions, Node current){
	ArrayList<Node> neighbours = new ArrayList<Node>(); // Contains every node adjacent and in the map limits
	int checkX;
	int checkY;

	for(Node n : directions){ /* For every direction, checking if it's in the map limits */
            checkX = current.getX() + n.getX();
            checkY = current.getY() + n.getY();
            if((checkX < xLimit && checkY < yLimit && checkX > 0 && checkY > 0) && !(checkX == current.getX() && checkY == current.getY())){
		/* If so, then the node is added to the list of neighbours */
		neighbours.add(new Node(checkX, checkY));
            }
        }
        return neighbours;
    }
    
    private ArrayList<Node> getNeighboursVH(Node current){
	ArrayList<Node> directions = new ArrayList<Node>(); // Contains every node adjacent to the current position

	// Preparing horizontal and vertical directions
	directions.add(new Node(-1, 0));
	directions.add(new Node(1, 0));
	directions.add(new Node(0, -1));
	directions.add(new Node(0, 1));
	
	return getNeighbours(directions, current);
    }

    private ArrayList<Node> getNeighboursDIA(Node current){
	ArrayList<Node> directions = new ArrayList<Node>(); // Contains every node adjacent to the current position

	// Preparing all diagonal directions
	directions.add(new Node(1, 1));
	directions.add(new Node(-1, -1));
	directions.add(new Node(-1, 1));
	directions.add(new Node(1, -1));

	return getNeighbours(directions, current);
    }

    
    private ArrayList<Node> retracePath(Node start, Node current){
	ArrayList<Node> path = new ArrayList<>();
	
	while(current.getX() != start.getX() || current.getY() != start.getY()){
	    path.add(current);
	    //System.out.println("X : "+current.getX()+" | Y : "+current.getY());
	    if(current.getParent() == null) break;
	    current = current.getParent();
	    
	    
	}
	return path;
    }

    public void initAStar(Node start, Node target){
	grid = new Node[xLimit][yLimit];
	closedList = new boolean[xLimit][yLimit];
	
	setStartNode(start);	
	setEndNode(target); 
        
	for(int i=0;i<xLimit;++i){
	    for(int j=0;j<yLimit;++j){
		grid[i][j] = new Node(i, j);
		grid[i][j].heuristic = Math.abs(i-endI)+Math.abs(j-endJ);
	    }
	}
	try{
	    grid[start.getX()][start.getY()].finalCost = 0;
	}catch(NullPointerException e){
	    System.out.println("start.getX() "+start.getX()+" | start.getY() "+start.getY()+" xlimit "+xLimit+" yLimit "+yLimit);
	}
	for(int i=0;i< xLimit;i++){
	    for(int j=0;j<yLimit;j++)
		if(map[i][j])
		    setBlocked(i, j);
	}
    }
    
    public ArrayList<Node> findPath(Node start, Node target){
	initAStar(start, target);
	openList.add(start);
	Node current;
	ArrayList<Node> neighbours;

	long millis = System.currentTimeMillis();
	
	while(true){
	    current = openList.poll();
	    if(current==null)break;
            closedList[current.getX()][current.getY()]=true;
	    //System.out.println("millis "+(System.currentTimeMillis()-millis));
	    if((current.getX()  == target.getX() && current.getY() == target.getY()) || System.currentTimeMillis()-millis > 3000)
		return retracePath(start, current);

	    /* Retrieve horizontal and vertical node's neighbours */
	    neighbours = getNeighboursVH(current);
	    for(Node neighbour : neighbours){
		checkAndUpdateCost(current, neighbour, current.finalCost+V_H_COST);
	    }

	    /* Retrieve diagonal node's neighbours */
	    neighbours = getNeighboursDIA(current);
	    for(Node neighbour : neighbours){
		checkAndUpdateCost(current, neighbour, current.finalCost+DIAGONAL_COST);
	    }

	}
	return null;
    }
}
