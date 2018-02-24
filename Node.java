public class Node {
    private int x, y;
    int heuristic, finalCost;
    private Node parent;
    
    public Node(int x, int y){
	this.x = x;
	this.y = y;
	this.finalCost = 0;
	this.heuristic = 0;
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
        
    public void setHeuristic(int heuristic){
	this.heuristic = heuristic;
    }
    
    public void setParent(Node parent){
	this.parent = parent;
    }
}
