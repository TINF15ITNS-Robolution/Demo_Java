package TestGeneticAlgorithm;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class StartGeneticAlgorithm {

	public static final int lebensdauerPopulation = 100;

	public static void main(String[] args) {

		// Frame initialisieren
		JFrame frame = new JFrame("Test");
		frame.setSize(1000, 1000);
		frame.setLocation(200, 30);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Panel intialisieren, auf dem gemalt wird
		View view = new View();
		view.setSize(1000, 1000);
		view.setVisible(true);
		frame.add(view);

		frame.setVisible(true);
		view.repaint();

		Logic logic = new Logic(view);

		Population p = new Population();
		int generation = 0;
		while (true) {
			logic.spieleEineGenerationDurch(p);
			generation++;
			System.out.println("Generation: " + generation);

			p.evaluate();
			
			p.selection();

		}

	}

}
