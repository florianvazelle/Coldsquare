import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ControlerEnemy extends Thread {
    private int[][] ancienPos;
    private AStar algo;
    private ArrayList<Node> path;
    
    Enemy perso;
    MaFenetreJeu frame;
    AfficherPersonnage af;
    JBalle ba;
    ControlerBalle controlerBalle;
    MenuPause mp;
    Terrain t;
    
    public ControlerEnemy(Enemy perso, MaFenetreJeu frame, AfficherPersonnage af, JBalle ba, MenuPause mp, Terrain t){
	this.perso = perso;
        this.frame = frame;
        this.af = af;
        this.ba = ba;
        this.mp = mp;
        this.t = t;
	this.path = new ArrayList<Node>();
	this.algo = new AStar(76, 41, t.getMap(), perso, t);

	this.ancienPos = new int[2][2];
        this.ancienPos[0][0] = 0;
	this.ancienPos[0][1] = 0;
        this.ancienPos[1][0] = 0;
        this.ancienPos[1][1] = 0;
    }

    @Override
    public void run(){
	action();
    }
    
    void action(){
	int vieInit = af.getSteve().getVie()+1;
	
	while(perso.getAwake()){
	    
	    int varX = perso.getCoordonneX();
	    int varY = perso.getCoordonneY();
	    
	    int playerPosX = af.getSteve().getCoordonneX();
	    int playerPosY = af.getSteve().getCoordonneY();
	    
	    if(perso.distance(varX, varY, playerPosX, playerPosY) > 100 && !(ancienPos[0][0] == varX/25 && ancienPos[0][1] == varY/25 && ancienPos[1][0] == playerPosX/25 && ancienPos[1][1] == playerPosY/25) ){
		
		path = algo.findPath(new Node(varX/25, varY/25), new Node(playerPosX/25, playerPosY/25));
		ancienPos[0][0] = varX/25;
		ancienPos[0][1] = varY/25;
		ancienPos[1][0] = playerPosX/25;
		ancienPos[1][1] = playerPosY/25;
	    }
	    
	    if(!path.isEmpty()){
		
		int targetX = path.get(path.size() - 1).getX();
		int targetY = path.get(path.size() - 1).getY();
		
		perso.setCoordonneX(perso.approachValues(varX, targetX*25, perso.getSpeed()));
		perso.setCoordonneY(perso.approachValues(varY, targetY*25, perso.getSpeed()));
		
		af.repaint(perso.getCoordonneX()-50, perso.getCoordonneY()-50, perso.getHitbox().getWidth()+100, perso.getHitbox().getHeight()+100);
	    }
	    
	    if((perso.getAwake() && perso.distance(perso.getCoordonneX(), perso.getCoordonneY(), playerPosX, playerPosY) < 250 && vieInit>af.getSteve().getVie() || (vieInit==af.getSteve().getVie() && ancienPos[0][1] != varY/25 && ancienPos[1][0] != playerPosX/25)) && !(mp.getEnPause())){
		vieInit = af.getSteve().getVie();
		tirer();
	    }
	    
	    if(perso.getVie() <=0 || af.getSteve().getVie() <=0 )
		perso.setAwake(false);
	    
	    try{
		TimeUnit.MILLISECONDS.sleep(5);
	    }catch(InterruptedException e){
		e.printStackTrace();
	    }
	}
    }
    
    public void tirer(){
	this.perso.setRotationX(af.getSteve().getCoordonneX());
	this.perso.setRotationY(af.getSteve().getCoordonneY());
	if(this.perso.getArme().getMunition()>0 && !this.perso.getATirer())
	    (new Tirer(perso, frame, af, ba, mp, t)).start();
    }
    
}


    
