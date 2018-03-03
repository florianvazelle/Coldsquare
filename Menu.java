import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Image;

public class Menu {
    public CardLayout card = new CardLayout();
    public JPanel cards = new JPanel(card);
    public JPanel menu = new JPanel();
    public String[] listContent = {"card1","card2","card3","card4"};
    public JPanel jeu = new JPanel();
    public JPanel options = new JPanel();
    public JPanel charger = new JPanel();
    public JFrame f = new JFrame();
    private JLabel title;
    private JLabel test;
    private static Menu m;
    public JTextField pseudo = new JTextField();
    public JTextField recherche = new JTextField();
    
    public Menu() {
	f.setSize(800,600);
	f.setLocationRelativeTo(null);
	f.setTitle("ColdSquare");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	ecranMenu();
	f.add(cards);
	f.setVisible(true);
    }
    
    public void ecranMenu() {
	JLabel titre = new JLabel();
	titre.setIcon(new ImageIcon("./assets/Colsquare.png"));
	JButton jouer = new JButton(new ImageIcon("./assets/Nouvelle.png"));
	JButton charger = new JButton(new ImageIcon("./assets/Charger.png"));
	JButton quitter = new JButton(new ImageIcon("./assets/Quitter.png"));
	menu.setLayout(new GridBagLayout());
	GridBagConstraints contraintes = new GridBagConstraints();
	
	/* Implementation de toutes les contraintes pour chaque composant et ajout au GridBagLayout, les contraintes sont les suivantes :
	   
	   gridx : choix de la colonne d'affichage
	   gridy : choix de la ligne d'affichage
	   ipady : choix de la hauteur de l'element
	   fill : agrandissement de l'element pour qu'il remplisse la case (peut remplir de manière : Horizontal, Vertical, Aucune, ou les deux
	   anchor : pour placer le composant dans la case quand il est plus petit que la case
	   insets : modifie l'espace entre chaque case, 4 parametres : en haut, a gauche, en bas, à droite
	   weightx : valeur qui va gerer l'espace restant horizontal
	   gridwidth : taille de la case (peut faire la taille d'1 case, de 2 cases, ect ...)
	*/
	
	contraintes.gridx = 1;
	contraintes.gridy = 0;
	contraintes.ipady = 50;
	contraintes.fill = GridBagConstraints.HORIZONTAL;
	contraintes.anchor = GridBagConstraints.CENTER;
	contraintes.insets = new Insets(10,30,30,10);
	menu.add(titre, contraintes);
		
	contraintes.gridx = 1;
	contraintes.gridy = 1;
	menu.add(jouer,contraintes);
	
	contraintes.gridx = 1;
	contraintes.gridy = 2;
	menu.add(charger,contraintes);
		
	contraintes.gridx = 1;
	contraintes.gridy = 3;
	menu.add(quitter, contraintes);
	
	cards.add(menu,listContent[0]);
	
	MenuController c1 = new MenuController(1,menu);
	MenuController c2 = new MenuController(2,menu);
	MenuController c4 = new MenuController(4,menu);
	c1.setMenu(this);
	c2.setMenu(this);
	c4.setMenu(this);
	jouer.addActionListener((ActionListener) c1);
	charger.addActionListener((ActionListener) c2);
	quitter.addActionListener((ActionListener) c4);
	affichageCharger();
	affichagePseudo();
    }
    
    public void affichageCharger() {
	JButton chercher = new JButton("Jouer");
	JLabel aide = new JLabel("Entrez votre pseudo :");
	charger.setLayout(new GridBagLayout());
	GridBagConstraints contraintes = new GridBagConstraints();
	JButton home = new JButton(new ImageIcon("./assets/maison.png"));
	
	contraintes.gridx = 0;
	contraintes.gridy = 0;
	contraintes.fill = GridBagConstraints.HORIZONTAL;
        contraintes.anchor = GridBagConstraints.CENTER;
	charger.add(aide,contraintes);
	
	contraintes.gridx = 0;
	contraintes.gridy = 1;
	contraintes.insets = new Insets(10,30,30,10);
	charger.add(recherche, contraintes);

	contraintes.gridx = 1;
	contraintes.gridy = 1;
	charger.add(chercher, contraintes);

	contraintes.gridx = 0;
        contraintes.gridy = 2;
	contraintes.insets = new Insets(350,0,0,500);
	charger.add(home,contraintes);

	Charger c = new Charger(this);
	chercher.addActionListener((ActionListener) c);
	
	MenuController c1 = new MenuController(5,menu);
	c1.setMenu(this);
	home.addActionListener((ActionListener) c1);
	
	cards.add(charger,listContent[2]);
    }

    public void affichagePseudo() {
	JButton valider = new JButton("Valider");
	JLabel nom = new JLabel("Entrez votre pseudo :");
	JButton maison = new JButton(new ImageIcon("./assets/maison.png"));
	jeu.setLayout(new GridBagLayout());
	GridBagConstraints contraintes = new GridBagConstraints();

	contraintes.gridx = 0;
	contraintes.gridy = 0;
	contraintes.fill = GridBagConstraints.HORIZONTAL;
	contraintes.anchor = GridBagConstraints.CENTER;
	contraintes.insets = new Insets(5,10,10,5);
	jeu.add(nom,contraintes);

	contraintes.gridx = 0;
	contraintes.gridy = 1;
	jeu.add(pseudo,contraintes);

	contraintes.gridx = 1;
	contraintes.gridy = 1;
	jeu.add(valider,contraintes);

	contraintes.gridx = 0;
        contraintes.gridy = 2;
        contraintes.insets = new Insets(350,0,0,500);
        jeu.add(maison,contraintes);
	
	MenuController c = new MenuController(6,menu);
	c.setMenu(this);
	valider.addActionListener((ActionListener) c);

	MenuController c1 = new MenuController(5,menu);
        c1.setMenu(this);
	maison.addActionListener((ActionListener) c1);
	
	cards.add(jeu, listContent[3]);
    }
    
    public static void main(String[] args) throws IOException {
	m = new Menu();
    }
}
