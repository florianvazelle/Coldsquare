import javax.swing.*;

import java.awt.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyEvent;

public class MenuMort extends JPanel{

	private Jeu j;
	private JButton recommencer;
	private JButton menu;
	private ArrayList<String> tabPseudo;
	private ArrayList<String> tabLevel;
	private ArrayList<String> tabScore;

	private boolean connecte=false;
	
	public MenuMort(Jeu j){
	        this.j=j;
		this.setLayout(null);
		this.tabPseudo= new ArrayList<String>();
		this.tabScore= new ArrayList<String>();
		this.tabLevel= new ArrayList<String>();

		recommencer= new JButton("Recommencer une partie");
		menu= new JButton("Menu");
		recommencer.setBounds(750,850,200,70);
		menu.setBounds(1050,850,200,70);
		BoutonListener b = new BoutonListener(this);
		recommencer.addActionListener(b);
		menu.addActionListener(b);
		this.add(recommencer);
		this.add(menu);
		this.setBounds(0,0,1920,1040);
		j.jlp.add(this, new Integer(6));
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch (ClassNotFoundException b1) {
			System.err.println("Pilote indisponible!");
		}

		try {
			if(connecte == false) {
				Connection connexion = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/simonr","simonr","Azertyuiop");
				connecte=true;
				String sql= "SELECT EXISTS(SELECT * FROM `Score` WHERE Pseudo='"+j.getPseudo()+"')";
				Statement verifNomSt = connexion.createStatement();
				ResultSet verifNomRs = verifNomSt.executeQuery(sql);
				PreparedStatement insertVal=null;
				ResultSet resScore=null;
				Statement ResSt;
				ResultSet ResSet;
				verifNomRs.next();
				if(verifNomRs.getBoolean(1)==false) {
					insertVal = connexion.prepareStatement("INSERT INTO Score VALUES('"+j.getPseudo()+"',"+j.getNiveau().getLevel()+","+j.getScore()+")");
					resScore = insertVal.executeQuery();
				}else {
					String sqltmp= "SELECT * FROM `Score` WHERE Pseudo='"+j.getPseudo()+"'";
					ResSt = connexion.createStatement();
					ResSet = ResSt.executeQuery(sqltmp);
					ResSet.next();
					if(j.getScore()>ResSet.getInt(3)) {
						insertVal = connexion.prepareStatement("UPDATE `Score` SET `Niveau` = '"+j.getNiveau().getLevel()+"', `Score` = '"+j.getScore()+"' WHERE `Score`.`Pseudo` = '"+j.getPseudo()+"';");
						resScore = insertVal.executeQuery();
					}else if(j.getScore() == ResSet.getInt(3) && j.getNiveau().getLevel() > ResSet.getInt(2)) {
						insertVal = connexion.prepareStatement("UPDATE `Score` SET `Niveau` = '"+j.getNiveau().getLevel()+"', `Score` = '"+j.getScore()+"' WHERE `Score`.`Pseudo` = '"+j.getPseudo()+"';");
						resScore = insertVal.executeQuery();
					}
					ResSt.close();
					ResSet.close();
				}
				String sql2= "SELECT * FROM `Score` ORDER BY `Score`.`Score` DESC, `Score`.`Niveau` DESC";
				Statement selectVal = connexion.createStatement();
				ResultSet tabScore = selectVal.executeQuery(sql2);
				tabScore.last();
				int nbRow = tabScore.getRow();
				//System.out.println(""+nbRow);
				tabScore.beforeFirst();
				
				while( tabScore.next() != false) {
					String pseudo = tabScore.getString(1);
					int lvl = tabScore.getInt(2);
					int score = tabScore.getInt(3);
					
					this.tabPseudo.add(pseudo);
					this.tabScore.add(Integer.toString(score));
					this.tabLevel.add(Integer.toString(lvl));
				
				}
				
				if(insertVal != null)
				insertVal.close();
				
				if(resScore != null)
				resScore.close();
				
				selectVal.close();
				tabScore.close();
				connexion.close();
				
			}
		} catch (SQLException b2) {
			System.err.println("Erreur de connexion: "+ b2.getMessage());

		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawFond(g);
		Toolkit.getDefaultToolkit().sync();
	}

	public void drawFond(Graphics g){

		Graphics2D g2d = (Graphics2D) g;
		g2d.fillRect(0,0,1920,1040);
		g2d.setColor(new Color(250,250,250));
		g2d.drawRect(800,100,900,700);
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Verdana", Font.BOLD , 20)); 
		g2d.drawString("GAME OVER", 200, 200);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Pseudo : "+j.getPseudo(), 200, 300);
		g2d.drawString("Niveau : "+j.getNiveau().getLevel(), 200, 350);
		g2d.drawString("Score : "+j.getScore(), 200 , 400);
		g2d.drawString("Pseudo", 870, 150);
		g2d.drawString("Niveau", 1150, 150);
		g2d.drawString("Score ", 1450 , 150);
		int posTab=-1;
		if(tabPseudo.size()<10) {
			for(int i=0; i< tabPseudo.size() ; i++) {

				if(j.getPseudo().equals(tabPseudo.get(i))) {
					g2d.setColor(new Color(219,169,1));
				}else {
					g2d.setColor(Color.WHITE);
				}
				g2d.drawString((i+1)+". ",820,(210+50*i));
				g2d.drawString(tabPseudo.get(i),870,(210+50*i));
				g2d.drawString(tabLevel.get(i),1150,(210+50*i));
				g2d.drawString(tabScore.get(i),1450,(210+50*i));
			}	
		}else {
			for(int i=0; i< 10 ; i++) {
				if(j.getPseudo().equals(tabPseudo.get(i))) {
					g2d.setColor(new Color(219,169,1));	
					posTab=i;
				}else {
					g2d.setColor(Color.WHITE);
				}
				g2d.drawString((i+1)+". ",820,(210+50*i));
				g2d.drawString(tabPseudo.get(i),870,(210+50*i));
				g2d.drawString(tabLevel.get(i),1150,(210+50*i));
				g2d.drawString(tabScore.get(i),1450,(210+50*i));

			}
			if(posTab==-1) {
			for(int i=0;i<tabPseudo.size();i++) {
				if(j.getPseudo().equals(tabPseudo.get(i))) {
					g2d.setColor(new Color(219,169,1));	
					posTab=i;
				}
			}
				g2d.drawString((posTab+1)+". ",820,(210+50*11));
				g2d.drawString(tabPseudo.get(posTab),870,(210+50*11));
				g2d.drawString(tabLevel.get(posTab),1150,(210+50*11));
				g2d.drawString(tabScore.get(posTab),1450,(210+50*11));
			
			}
		}
		
		
		
		
		
	}
	
	
	class BoutonListener implements ActionListener{
		
		MenuMort m;
		
		public BoutonListener(MenuMort m) {
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			Object o= e.getSource();
			
			if(o==recommencer){
				m.j.frame.dispose();
				String s= m.j.getPseudo();
				m.j= new Jeu(m.j.mc);
				m.j.setPseudo(s);
			}else if(o==menu) {
				new Menu();
				m.j.frame.dispose();
			}
		}
		
	}
	

}
