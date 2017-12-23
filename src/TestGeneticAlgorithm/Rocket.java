package TestGeneticAlgorithm;

public class Rocket {

	private Position position;
	public boolean wandOderZiel = false;

	private int[] dna;
	private double fitness = 0;

	public Rocket(int lebensdauer) {
		setPosition(new Position((int) (Logic.spielfeldbreite / 2), 5));
		dna = new int[lebensdauer];

		for (int i = 0; i < dna.length; i++) {
			dna[i] = (int) (Math.random() * 360d);

		}

	}
	
	public Rocket(int[] dna) {
		setPosition(new Position((int) (Logic.spielfeldbreite / 2), 5));
		this.dna = dna;
	}


	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getHeading(int i) {
		return dna[i];
	}

	public int[] getDNA() {
		return dna;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

}
