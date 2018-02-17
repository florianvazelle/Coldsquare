public class Enemy extends Personnage{
	private boolean awake;

	public Enemy(String nom, int vie, String skin, int x_de_base, int y_de_base){
		super(nom, vie, skin, x_de_base, y_de_base);
		this.awake = false;
	}

	public Enemy(String nom, int vie, String skin, int x_de_base, int y_de_base, Arme arme){
		super(nom, vie, skin, x_de_base, y_de_base, arme);
		this.awake = false;
	}

	private int distance(int ax, int ay, int bx, int by){
		return (ax - bx) + (ay - by);
	}

	public void update(int playerPosX, int playerPosY){
		if(!awake 
			&& distance(super.getCoordonneX(), super.getCoordonneY(), playerPosX, playerPosY) < 144)
			awake = true;
	}
}