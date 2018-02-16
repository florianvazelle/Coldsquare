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
    public int dispersion;
    public int enemis;
    public int level;
    public int munitions;
    public int score;
    private Jeu j;
    
    public Sauvegarde (Jeu jeu) {
	this.j = jeu;
	n = this.j.getNiveau();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

	mc = new MenuController();
	
	this.pseudo = mc.getNom();
        this.vie = n.getVie();
        this.cadence = n.getCadence();
        this.dispersion = n.getDispersion();
        this.enemis = n.getEnnemis();
        this.level = n.getLevel();
        this.munitions = n.getBalle();
	this.score = j.getScore();
	System.out.println(""+pseudo);
	try {
	    Class.forName("org.mariadb.jdbc.Driver");
	}
	catch (ClassNotFoundException b) {
	    System.err.println("Pilote indisponible!");
	}

	try {
	    Connection connexion = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/simonr","simonr","Azertyuiop");

	    PreparedStatement insertVal = connexion.prepareStatement("INSERT INTO Sauvegarde VALUES('"+pseudo+"',"+vie+","+cadence+","+munitions+","+dispersion+","+enemis+","+level+","+score+")");
	    ResultSet sauvegardeComplete = insertVal.executeQuery();
	    if (sauvegardeComplete.first()) {
		JOptionPane jop = new JOptionPane();
		jop.showMessageDialog(null,"Sauvegarde réussie!","Information",JOptionPane.INFORMATION_MESSAGE);
	    }
	    insertVal.close();
	    sauvegardeComplete.close();
	    connexion.close();
	} catch (SQLException b) {
	    System.err.println("Erreur de connexion: "+ b.getMessage());

	}
    }
}

	
