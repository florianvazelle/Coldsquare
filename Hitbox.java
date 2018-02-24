public class Hitbox {

    private double x;
    private double y;
    private int w;
    private int h;

    Hitbox(){} //Hitbox null

    Hitbox(double x, double y, int w, int h){
        this.h = h;
        this.w = w;
        this.x = x;
        this.y = y;
    }
    
    Hitbox(Personnage p){
	Sprite s = p.listeDeSprite.get(0);
       	this.h = s.getImageIcon().getIconHeight();
	this.w = s.getImageIcon().getIconWidth();
	this.x = p.getCoordonneX();
	this.y = p.getCoordonneY();
    }

    Hitbox(Balle b){
	Sprite s = b.getSprite();
	this.h = s.getImageIcon().getIconHeight()-20;
	this.w = s.getImageIcon().getIconWidth()-20;
	this.x = b.getX()+10;
	this.y = b.getY()+10;
    }

    Hitbox(Boite b){
    	Sprite s = b.getSprite();
    	this.h = s.getImageIcon().getIconHeight();
    	this.w = s.getImageIcon().getIconWidth();
    	this.x = b.getCoordonneX();
    	this.y = b.getCoordonneY();
    }

    Hitbox(Mur m ){
        this.h = m.getLargeur();
        this.w = m.getLongueur();
	this.x = m.getX();
        this.y = m.getY();
    }

    static boolean collision(Hitbox element1, Hitbox element2){	
	if(element2.getX() >= element1.getX()+element1.getWidth() || (element2.getX() + element2.getWidth() <= element1.getX()) || (element2.getY() >= element1.getY() + element1.getHeight()) || (element2.getY() + element2.getHeight() <= element1.getY()))
	    return false;
	else
	    return true;
    }

    double getX(){
	return this.x;
    }

    void setX(double x){
	this.x=x;
    }
    
    double getY(){
        return this.y;
    }

    void setY(double y){
	this.y=y;
    }

    int	getHeight(){
	return this.h;
    }

    void setHeight(int h){
	this.h = h;
    }

    int	getWidth(){
        return this.w;
    }

    void setWidth(int w){
	this.w = w;
    }

}
