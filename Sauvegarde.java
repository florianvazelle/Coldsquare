import java.awt.*;
import java.awt.Event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

class Sauvegarde implements ActionListener {

    public Niveau n;
    private Jeu j;
    public int vie;
    public int munitions;
    public int cadence;
    public int dispersion;
    public int level;
    
    public Sauvegarde(){

    }

    public void actionPerformed(ActionEvent e) {

	FileOutputStream fos;
	fos = new FileOutputStream("./save/save1");
	DataOutputStream dos;
	dos = new DataOutputStream(fos);

	n = new Niveau(j);
	vie = n.getVie();
	munitions = n.getBalle();
	cadence = n.getCadence();
	dispersion = n.getDispersion();
	level = n.getLevel();

	dos.writeInt(vie);
	dos.writeInt(munitions);
	dos.writeInt(cadence);
	dos.writeInt(dispersion);
	dos.writeInt(level);

    }
}

	
