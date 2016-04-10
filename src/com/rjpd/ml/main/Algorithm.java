package com.rjpd.ml.main;

import java.util.Random;

public class Algorithm {

	private static String solution;
	private static String chars;
	private static double crossoverRate;
	private static double mutationRate;

	public static Population newGeneration(Population population,
			boolean elitism) {
		Random r = new Random();

		// Generates a new population with same size of the previous population
		Population newPopulation = new Population(
				population.getPopulationSize());

		// Keep best individual in the new population
		if (elitism) {
			newPopulation.setIndividual(population.getIndividual(0));
		}

		while (newPopulation.getNumIndividuals() < newPopulation
				.getPopulationSize()) {
			// Selects parents through tournament
			Individual[] parents = tournament(population);
			Individual[] sons = new Individual[2];

			if (r.nextDouble() <= crossoverRate) {
				sons = crossover(parents[1], parents[0]);
			} else {
				sons[0] = new Individual(parents[0].getGenes());
				sons[1] = new Individual(parents[1].getGenes());
			}

			// Add new sons in the new generation
			newPopulation.setIndividual(sons[0]);
			newPopulation.setIndividual(sons[1]);
		}

		newPopulation.orderPopulation();
		return newPopulation;
	}

	public static Individual[] crossover(Individual indone, Individual indtwo) {
		Random r = new Random();

		int cutoffOne = r.nextInt((indone.getGenes().length() / 2) - 2) + 1;
		int cutoffTwo = r.nextInt((indone.getGenes().length() / 2) - 2)
				+ indone.getGenes().length() / 2;

		Individual[] sons = new Individual[2];

		String geneFather = indone.getGenes();
		String geneMother = indtwo.getGenes();

		String geneSonOne;
		String geneSonTwo;

		geneSonOne = geneFather.substring(0, cutoffOne);
		geneSonOne += geneMother.substring(cutoffOne, cutoffTwo);
		geneSonOne += geneFather.substring(cutoffTwo, geneFather.length());

		geneSonTwo = geneMother.substring(0, cutoffOne);
		geneSonTwo += geneFather.substring(cutoffOne, cutoffTwo);
		geneSonTwo += geneMother.substring(cutoffTwo, geneMother.length());

		sons[0] = new Individual(geneSonOne);
		sons[1] = new Individual(geneSonTwo);

		return sons;
	}

	public static Individual[] tournament(Population population) {
		Random r = new Random();
		Population intermediatePopulation = new Population(3);

		intermediatePopulation.setIndividual(population.getIndividual(r
				.nextInt(population.getPopulationSize())));
		intermediatePopulation.setIndividual(population.getIndividual(r
				.nextInt(population.getPopulationSize())));
		intermediatePopulation.setIndividual(population.getIndividual(r
				.nextInt(population.getPopulationSize())));

		intermediatePopulation.orderPopulation();

		Individual[] parents = new Individual[2];

		parents[0] = intermediatePopulation.getIndividual(0);
		parents[1] = intermediatePopulation.getIndividual(1);

		return parents;
	}

	public static String getSolution() {
		return solution;
	}

	public static void setSolution(String solution) {
		Algorithm.solution = solution;
	}

	public static String getChars() {
		return chars;
	}

	public static void setChars(String chars) {
		Algorithm.chars = chars;
	}

	public static double getCrossoverRate() {
		return crossoverRate;
	}

	public static void setCrossOverRate(double crossoverRate) {
		Algorithm.crossoverRate = crossoverRate;
	}

	public static double getMutationRate() {
		return mutationRate;
	}

	public static void setMutationRate(double mutationRate) {
		Algorithm.mutationRate = mutationRate;
	}

}
