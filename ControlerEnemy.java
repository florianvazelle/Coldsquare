public class ControlerEnemy {
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
    }

    public void tirer(){
	perso.setRotationX(af.getSteve().getCoordonneX());
	perso.setRotationY(af.getSteve().getCoordonneY());
	(new Tirer(perso, frame, af, ba, mp, t)).start();
    }

}


    
