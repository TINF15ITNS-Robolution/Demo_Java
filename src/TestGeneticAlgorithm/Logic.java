package TestGeneticAlgorithm;

public class Logic {

	public static final int spielfeldlaenge = 50;
	public static final int spielfeldbreite = 50;
	// der Nullpunkt in einem Spielfeld ist oben links (wie vom Frame halt) gelegt
	// private int[][] spielfeld;

	private View view;

	private Rocket[] rockets;

	public Logic(View v) {
		this.view = v;
	}

	public void spieleEineGenerationDurch(Population population) {
		rockets = population.getRockets();
		for (int i = 0; i < StartGeneticAlgorithm.lebensdauerPopulation; i++) {
			//System.out.println("Lebensjahr: " + (i + 1));
			// view.setLebensjahr(i+1);
			berechneNeuesLebensjahr(i);
			view.setSpielfeldArray(getSpielfeld());
			view.repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				System.out.println("Error beim Schlafenlegen des Threads: ");
				e.printStackTrace();
			}
		}
	}

	private void berechneNeuesLebensjahr(int dnasequencenumber) {

		for (int i = 0; i < rockets.length; i++) {

			// wenn Wand oder Ziel erreicht wurde, bleibt diese Rakete an dieser Stelle
			if (rockets[i].wandOderZiel)
				continue;
			;

			Position pos = rockets[i].getPosition();
			int heading = rockets[i].getHeading(dnasequencenumber);

			int newX = 0;
			int newY = 0;
			// 0° ist geraderaus den Bildschirm nach oben

			// 338° - 23° bedeuten dann das Kästchen direkt oben drüber
			if (338 <= heading && heading < 23) {
				newX = pos.getX();
				newY = pos.getY() + 1;
				Position newPos = new Position(newX, newY);
				rockets[i].wandOderZiel = checkWandOderZiel(newPos);
				if (!rockets[i].wandOderZiel)
					rockets[i].setPosition(newPos);

			}
			if (68 <= heading && heading < 113) {
				newX = pos.getX() + 1;
				newY = pos.getY() + 1;
				Position newPos = new Position(newX, newY);
				rockets[i].wandOderZiel = checkWandOderZiel(newPos);
				if (!rockets[i].wandOderZiel)
					rockets[i].setPosition(newPos);

			}
			if (113 <= heading && heading < 158) {
				newX = pos.getX() + 1;
				newY = pos.getY();
				Position newPos = new Position(newX, newY);
				rockets[i].wandOderZiel = checkWandOderZiel(newPos);
				if (!rockets[i].wandOderZiel)
					rockets[i].setPosition(newPos);

			}
			if (158 <= heading && heading < 203) {
				newX = pos.getX() + 1;
				newY = pos.getY() - 1;
				Position newPos = new Position(newX, newY);
				rockets[i].wandOderZiel = checkWandOderZiel(newPos);
				if (!rockets[i].wandOderZiel)
					rockets[i].setPosition(newPos);

			}
			if (203 <= heading && heading < 248) {
				newX = pos.getX();
				newY = pos.getY() - 1;
				Position newPos = new Position(newX, newY);
				rockets[i].wandOderZiel = checkWandOderZiel(newPos);
				if (!rockets[i].wandOderZiel)
					rockets[i].setPosition(newPos);

			}
			if (248 <= heading && heading < 293) {
				newX = pos.getX() - 1;
				newY = pos.getY() - 1;
				Position newPos = new Position(newX, newY);
				rockets[i].wandOderZiel = checkWandOderZiel(newPos);
				if (!rockets[i].wandOderZiel)
					rockets[i].setPosition(newPos);

			}
			if (293 <= heading && heading < 338) {
				newX = pos.getX() - 1;
				newY = pos.getY();
				Position newPos = new Position(newX, newY);
				rockets[i].wandOderZiel = checkWandOderZiel(newPos);
				if (!rockets[i].wandOderZiel)
					rockets[i].setPosition(newPos);

			}
			if (315 <= heading && heading < 360) {
				newX = pos.getX() - 1;
				newY = pos.getY() + 1;
				Position newPos = new Position(newX, newY);
				rockets[i].wandOderZiel = checkWandOderZiel(newPos);
				if (!rockets[i].wandOderZiel)
					rockets[i].setPosition(newPos);

			}

			// System.out.println("Rocket " + i + ": " + "an Position: " +
			// rockets[i].getPosition().getX() + "|" + rockets[i].getPosition().getY());
		}
	}

	private boolean checkWandOderZiel(Position pos) {
		// check Target
		for (Position p : View.target) {
			if (p.equals(pos))
				return true;
		}

		// check Wall
		if (pos.getX() < 0 || pos.getX() >= spielfeldbreite)
			return true;

		if (pos.getY() < 0 || pos.getY() >= spielfeldlaenge)
			return true;

		return false;
	}

	public int[][] getSpielfeld() {
		int[][] spielfeld = new int[spielfeldlaenge][spielfeldbreite];

		for (int i = 0; i < spielfeldlaenge; i++) {
			for (int k = 0; k < spielfeldbreite; k++) {
				spielfeld[i][k] = 0;

				for (int ro = 0; ro < rockets.length; ro++) {
					// warum auch immer macht er das nicht mehr als ein mal
					if (i == rockets[ro].getPosition().getY() && k == rockets[ro].getPosition().getX()) {
						spielfeld[i][k]++;
					}
				}
				// System.out.print(spielfeld[i][k] + " ");
			}
			// System.out.println(" ");
		}

		return spielfeld;
	}
}
