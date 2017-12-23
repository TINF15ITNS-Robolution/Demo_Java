package TestGeneticAlgorithm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class View  extends JPanel {

	private int[][] spielfeld;
	
	//public static final Position[] target = {new Position(24, 45), new Position(25, 45),  new Position(24, 46), new Position(25, 46)};
	public static final Position[] target = {new Position(25, 45)};
	
	//hier könnte man jetzt Hindernisse definieren
	
	
	private int kastenbreite = 12;
	private int kastenlaenge = 12;
	private int kastenabstand = 3;
	private int spielfeldrand = 30;
	
	private JTextField textfieldLebensjahr;
	
	public View () {
		this.setBackground(Color.WHITE);
		
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setVisible(true);
		
		/*this.textfieldLebensjahr = new JTextField();
		this.textfieldLebensjahr.setBounds(100, 0, 100, 30);
		this.add(textfieldLebensjahr);
		this.textfieldLebensjahr.setVisible(true);*/


		// Spielfeld initalisieren
		spielfeld = new int[Logic.spielfeldlaenge][Logic.spielfeldbreite];
		for (int i = 0; i < spielfeld.length; i++) {
			for (int k = 0; k < spielfeld[0].length; k++) {
				spielfeld[i][k] = 0;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		//g.clearRect(0, 0, spielfeldrand + (spielfeld.length * (kastenbreite + kastenabstand)), spielfeldrand + (spielfeld[0].length * (kastenlaenge + kastenabstand)));
		for (int i = 0; i < spielfeld.length; i++) {
			for (int k = 0; k < spielfeld[0].length; k++) {
				if(spielfeld[i][k] > 0) {
					g.setColor(Color.GRAY);
					g.fillRect(spielfeldrand + (k * (kastenbreite + kastenabstand)) , spielfeldrand + (i * (kastenlaenge + kastenabstand)), kastenbreite, kastenlaenge);
				}else{
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(spielfeldrand + (k * (kastenbreite + kastenabstand)) , spielfeldrand + (i * (kastenlaenge + kastenabstand)), kastenbreite, kastenlaenge);
					}
				
			}
		}
		
		//zeichne Target
		g.setColor(Color.GREEN);
		for(int i = 0; i < target.length; i++) {
			g.fillRect(spielfeldrand + (target[i].getX() * (kastenbreite + kastenabstand)) , spielfeldrand + (target[i].getY() * (kastenlaenge + kastenabstand)), kastenbreite, kastenlaenge);
		}

		
	}
	
	public void setLebensjahr(int i) {
		this.textfieldLebensjahr.setText("Lebensjahr: "  + i);
	}
	
	public void setSpielfeldArray(int[][] s) {
		this.spielfeld = s;
	}
}
