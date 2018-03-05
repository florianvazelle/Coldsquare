import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;

class Sauvegarde implements ActionListener {

    private Niveau n;
    private MenuController mc;
    public String pseudo;
    public int vie;
    public int cadence;
    public int enemis;
    public int level;
    public int munitions;
    public int score;
    public boolean cac;
    private Jeu j;
    
    public Sauvegarde (Jeu jeu) {
	this.j = jeu;
	n = this.j.getNiveau();
	
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	
	this.pseudo = j.getPseudo();
        this.vie = n.getVie();
        this.cadence = n.getCadence();
	this.enemis = n.getEnnemis();
        this.level = n.getLevel();
        this.munitions = n.getBalle();
	this.cac = n.getCac();
	this.score = j.getScore();
	//System.out.println(""+pseudo);
	try {
	    Class.forName("org.mariadb.jdbc.Driver");
	}
	catch (ClassNotFoundException b) {
	    System.err.println("Pilote indisponible!");
	}

	try {
	    Connection connexion = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/simonr","simonr","Azertyuiop");

	    PreparedStatement verifVal = connexion.prepareStatement("SELECT EXISTS(SELECT * FROM Sauvegarde WHERE Pseudo = '"+pseudo+"')");
	    ResultSet verif = verifVal.executeQuery();
	    PreparedStatement insertVal = null;
	    ResultSet sauvegardeComplete = null;
	    PreparedStatement updateVal = null;
	    ResultSet update = null;
	    verif.next();
	    if (verif.getBoolean(1)==true) {
		updateVal = connexion.prepareStatement("UPDATE Sauvegarde SET Vie="+vie+", Cadence="+cadence+",Munitions="+munitions+",Enemis="+enemis+",Level="+level+",Score="+score+",Corps="+cac+" WHERE Pseudo='"+pseudo+"'");
		update = updateVal.executeQuery();
		updateVal.close();
		update.close();
	    } else {
		insertVal = connexion.prepareStatement("INSERT INTO Sauvegarde VALUES('"+pseudo+"',"+vie+","+cadence+","+munitions+","+enemis+","+level+","+score+","+cac+")");
		sauvegardeComplete = insertVal.executeQuery();
		insertVal.close();
		sauvegardeComplete.close();
	    }
	    JOptionPane jop = new JOptionPane();
	    jop.showMessageDialog(null,"Sauvegarde réussie!","Information",JOptionPane.INFORMATION_MESSAGE);
	    verifVal.close();
	    verif.close();
	    connexion.close();

	} catch (SQLException b) {
	    System.err.println("Erreur de connexion: "+ b.getMessage());

	}
    }
}

	
