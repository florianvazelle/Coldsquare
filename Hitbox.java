public class Hitbox {

    private int x;
    private int y;
    private int w;
    private int h;

    Hitbox(){} //Hitbox null
    
    Hitbox(Personnage p){
	Sprite s = p.listeDeSprite.get(0);
	this.h = s.getImageIcon().getIconHeight();
	this.w = s.getImageIcon().getIconWidth();
	this.x = p.getCoordonneX();
	this.y = p.getCoordonneY();
    }

    Hitbox(Balle b){
	Sprite s = b.getSprite();
	this.h = s.getImageIcon().getIconHeight();
	this.w = s.getImageIcon().getIconWidth();
	this.x = b.getX();
	this.y = b.getY();
    }

    static boolean collision(Hitbox element1, Hitbox element2){	
	if(element2.getX() >= element1.getX()+element1.getWidth() || (element2.getX() + element2.getWidth() <= element1.getX()) || (element2.getY() >= element1.getY() + element1.getHeight()) || (element2.getY() + element2.getHeight() <= element1.getY()))
	    return false;
	else
	    return true;
    }

    int getX(){
	return this.x;
    }

    void setX(int x){
	this.x=x;
    }
    
    int	getY(){
        return this.y;
    }

    void setY(int y){
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
