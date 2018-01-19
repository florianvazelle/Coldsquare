public class Hitbox {

    private int x;
    private int y;
    private int w;
    private int h;

    Hitbox(Personnage p){
	Sprite s = p.listeDeSprite.get(0);
	this.h = s.getImageIcon().getIconHeight()+2;
	this.w = s.getImageIcon().getIconWidth()+2;
	this.x = p.getCoordonneX()+1;
	this.y = p.getCoordonneY()+1;
    }

    Hitbox(BalleAnimation ba){
	Sprite s = ba.getImage();
	this.h = s.getImageIcon().getIconHeight()+2;
	this.w = s.getImageIcon().getIconWidth()+2;
	System.out.println(" lol "+h);
	this.x = ba.getCoordonneX()+1;
	this.y = ba.getCoordonneY()+1;
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

    int	getWidth(){
        return this.w;
    }
}
