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
	public String[] listContent = {"card1","card2","card3"};
	public JPanel jeu = new JPanel();
	public JPanel options = new JPanel();
	public JPanel charger = new JPanel();
	public JFrame f = new JFrame();
	private JLabel title;
	private JLabel test;
	private static Menu m;
	public FondMenu fm;
	public ButtonGroup bg = new ButtonGroup();

	public Menu() {
		f.setSize(800,600);
		f.setLocationRelativeTo(null);
		f.setTitle("ColdSquare");
		this.fm=  new FondMenu();
		initFond();
		fm.setBounds(0,0,800,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(cards);
		ecranMenu();
		f.setVisible(true);
	}
	
	public void ecranMenu() {
		JLabel titre = new JLabel("ColdSquare");
		JButton jouer = new JButton("Nouvelle Partie");
		JButton charger = new JButton("Charger Partie");
		JButton options = new JButton("Options");
		JButton quitter = new JButton("Quitter");
		menu.setLayout(new GridBagLayout());
		GridBagConstraints contraintes = new GridBagConstraints();
		
		Font font = new Font("Courier",Font.BOLD,20);
		titre.setFont(font);		
		titre.setForeground(Color.blue);
		/* Implémentation de toutes les contraintes pour chaque composant et ajout au GridBagLayout, les contraintes sont les suivantes :
		   
		   gridx : choix de la colonne d'affichage
		   gridy : choix de la ligne d'affichage
		   ipady : choix de la hauteur de l'élément
		   fill : agrandissement de l'élément pour qu'il remplisse la case (peut remplir de manière : Horizontal, Vertical, Aucune, ou les deux
		   anchor : pour placer le composant dans la case quand il est plus petit que la case
		   insets : modifie l'espace entre chaque case, 4 paramètres : en bas, à gauche, en haut, à droite
		   weightx : valeur qui va gérer l'espace restant horizontal
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
		menu.add(options, contraintes);
		
		contraintes.gridx = 1;
		contraintes.gridy = 4;
		menu.add(quitter, contraintes);
		
		cards.add(menu,listContent[0]);
		
		MenuController c1 = new MenuController(1,menu);
		MenuController c2 = new MenuController(2,menu);
		MenuController c3 = new MenuController(3,menu);
		MenuController c4 = new MenuController(4,menu);
		c1.setMenu(this);
		c2.setMenu(this);
		c3.setMenu(this);
		c4.setMenu(this);
		jouer.addActionListener((ActionListener) c1);
		charger.addActionListener((ActionListener) c2);
		options.addActionListener((ActionListener) c3);
		quitter.addActionListener((ActionListener) c4);
	}
	
	public void affichageOptions() {
		
		JRadioButton easy = new JRadioButton("Facile");
		JRadioButton medium = new JRadioButton("Moyen");
		JRadioButton hard = new JRadioButton("Difficile");
		
		easy.setSelected(true);
		bg.add(easy);
		bg.add(medium);
		bg.add(hard);
		
		test = new JLabel("Easy : 5 coeurs, 5mun/ennemis! Medium : 3 coeurs, 3mun/ennemis! Hard : 2 coeurs, 2mun/ennemis!");
		
		options.add(easy);
		options.add(medium);
		options.add(hard);
		options.add(test);
		cards.add(options,listContent[2]);
	}
	
	public void affichageCharger() {
		test = new JLabel("CHARGER");
		charger.add(test);
		cards.add(charger,listContent[1]);
	}
	
	void initFond() {
		fm.repaint();
	}
	
	public static void main(String[] args) {
		m = new Menu();
	}
}
class FondMenu extends JPanel {
	 	ImageIcon fond;
	    
	 	public FondMenu(){
		fond = new ImageIcon(new ImageIcon("./assets/fond.png").getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
	    }
	    
	    @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
		drawFond(g);
	        Toolkit.getDefaultToolkit().sync();
	    }
	    
	    public void drawFond(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Image imagefond= fond.getImage();
		g2d.drawImage(imagefond,0,0,this);      
	    }
}