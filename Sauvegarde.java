import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;

class Sauvegarde implements ActionListener {

    public Niveau n;
    public MenuController mc;
    public String pseudo;
    public int vie;
    public int cadence;
    public int dispersion;
    public int enemis;
    public int level;
    public int munitions;
    
    public Sauvegarde () {
	this.pseudo = mc.nom;
	this.vie = n.getVie();
	this.cadence = n.getCadence();
	this.dispersion = n.getDispersion();
	this.enemis = n.getEnnemis();
	this.level = n.getLevel();
	this.munitions = n.getBalle();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	try {
	    Class.forName("org.mariadb.jdbc.Driver");
	}
	catch (ClassNotFoundException b) {
	    System.err.println("Pilote indisponible!");
	}

	try {
	    Connection connexion = DriverManager.getConnection("jdbc:mardiadb://dwarves.iut-fbleau.fr/simonr","simonr","Azertyuiop");

	    PreparedStatement insertVal = connexion.prepareStatement("INSERT INTO Sauvegarde VALUES('"+pseudo+"',"+vie+","+cadence+","+munitions+","+dispersion+","+enemis+","+level);
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

	
