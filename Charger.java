import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import javax.swing.*;

class Charger implements ActionListener {

    public int vie;
    public int cadence;
    public int munitions;
    public int enemis;
    public int level;
    public int score;
    public boolean cac;
    public String pseudo;
    private Menu m;
    private Niveau n;
    private Jeu j;
    private MenuController mc;
    
    public Charger(Menu menu) {
	this.m = menu;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

	this.pseudo = m.recherche.getText(); 
	
	try {
	    Class.forName("org.mariadb.jdbc.Driver");
	}
	catch (ClassNotFoundException b) {
	    System.err.println("Pilote indisponible!");
	}

	try {
	    Connection connexion = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/simonr","simonr","Azertyuiop");
	    PreparedStatement insertVal = connexion.prepareStatement("SELECT * FROM Sauvegarde WHERE pseudo = '"+pseudo+"'");
	    ResultSet rs = insertVal.executeQuery();
	    while (rs.next()) {
		vie = rs.getInt(2);
		cadence = rs.getInt(3);
		munitions = rs.getInt(4);
		enemis = rs.getInt(5);
		level = rs.getInt(6);
		score = rs.getInt(7);
		cac = rs.getBoolean(8);
	    }
	    j.n.setVie(vie);
	    j.n.setCadence(cadence);
	    j.n.setBalle(munitions);
	    j.n.setEnemis(enemis);
	    j.n.setLevel(level);
	    j.n.setCac(cac);
	    j.setScore(score);
	    j = new Jeu(mc);

	    insertVal.close();
	    rs.close();
	    connexion.close();

	    m.f.setVisible(false);
	    m.f.dispose();
	    
	} catch (SQLException b) {
	    System.err.println("Erreur de connexion :"+b.getMessage());
	}
	
	
    }
}
