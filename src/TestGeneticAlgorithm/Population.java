package TestGeneticAlgorithm;

import java.util.LinkedList;
import java.util.List;

public class Population {

	private final int populationsize = 25;
	private final int targetHitFitnessMultiplikator = 10;
	private final int rocketCrashedFitnessDividor = 10;
	private final double mutationPercentage = 0.05;
	private Rocket[] population;
	private List<Rocket> DNAPool;

	public Population() {
		// Population initialisieren
		population = new Rocket[populationsize];
		for (int i = 0; i < population.length; i++) {
			population[i] = new Rocket(StartGeneticAlgorithm.lebensdauerPopulation);
		}
	}

	public Rocket[] getRockets() {
		return population;
	}

	public void evaluate() {
		// bewerte Fitness
		double minDistance = 1000;
		for (int i = 0; i < population.length; i++) {
			double distance = calcDist(population[i].getPosition().getX(), population[i].getPosition().getY(),
					View.target[0].getX(), View.target[0].getY());

			if (distance < minDistance)
				minDistance = distance;
			// abhängig von der Entfernung können Werte zwischen 0 und 1 erreicht werden
			if (distance == 0)
				population[i].setFitness(1 * targetHitFitnessMultiplikator);

			if (distance > 0) {
				if (population[i].wandOderZiel) {
					population[i].setFitness((1 / distance) / rocketCrashedFitnessDividor);
				} else {
					population[i].setFitness(1 / distance);
				}
			}

		}
		System.out.println("minimale Distanz: " + minDistance);

		//max Fitness herausfinden
		double maxFitness = 0;
		for (Rocket r : population) {
			if (r.getFitness() > maxFitness) {
				maxFitness = r.getFitness();
			}
		}
		System.out.println("Max Fitness: " + maxFitness);

		// normalisiere alle Fitness-Werte
		for (int i = 0; i < population.length; i++) {
			population[i].setFitness(population[i].getFitness() / maxFitness);
		}

		// füge zum DNAPool hinzu
		DNAPool = new LinkedList<Rocket>();
		for (int i = 0; i < population.length; i++) {
			double normalizedFitness = population[i].getFitness() * 100;
			// falls alle in der ersten Runde in die Wand fliegen, wird durch die
			// Bewertungsmethode ausnahmsweise keine Rakete dem Pool hinzugefügt, was zu
			// einer Exception führt
			if (normalizedFitness < 1)
				normalizedFitness = 1;
			// abhängig von der Fitness wird die Rakete dem Pool x mal zugefügt
			for (int j = 0; j < normalizedFitness; j++) {
				DNAPool.add(population[i]);
			}
		}
	}

	public void selection() {
		// wähle zwei Eltern zufällig aus
		for (int i = 0; i < population.length; i++) {
			Rocket parentA = DNAPool.get((int) (Math.random() * DNAPool.size()));
			Rocket parentB = DNAPool.get((int) (Math.random() * DNAPool.size()));
			Rocket child = new Rocket(crossoverDNA(parentA, parentB));

			population[i] = child;
		}

	}

	public int[] crossoverDNA(Rocket rocketA, Rocket rocketB) {
		int[] dnaA = rocketA.getDNA();
		int[] dnaB = rocketB.getDNA();

		int mid = (int) (Math.random() * dnaA.length);
		int[] newDNA = new int[dnaA.length];
		for (int i = 0; i < newDNA.length; i++) {
			if (i < mid) {
				newDNA[i] = dnaA[i];
			} else {
				newDNA[i] = dnaB[i];
			}
		}
		
		//Mutation		
		for (int i = 0; i < newDNA.length; i++) {
			if (Math.random() < mutationPercentage) {
				newDNA[i] = (int) (Math.random() * 360d);
			}
		}

		return newDNA;
	}

	public int[] crossoverDNA2(Rocket rocketA, Rocket rocketB) {
		int[] dnaA = rocketA.getDNA();
		int[] dnaB = rocketB.getDNA();

		int[] newDNA = new int[dnaA.length];

		for (int i = 0; i < newDNA.length; i++) {
			if (Math.random() < 0.5) {
				newDNA[i] = dnaA[i];
			} else {
				newDNA[i] = dnaB[i];
			}
		}
		
		//Mutation		
		for (int i = 0; i < newDNA.length; i++) {
			if (Math.random() < mutationPercentage) {
				newDNA[i] = (int) (Math.random() * 360d);
			}
		}

		return newDNA;
	}

	private double calcDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
}
