package com.rjpd.ml.main;

public class Population {
	private Individual[] individuals;
	private int populationSize;

	// Generates a population with random individuals
	public Population(int numGenes, int popSize) {
		populationSize = popSize;
		individuals = new Individual[popSize];
		for (int i = 0; i < individuals.length; i++) {
			individuals[i] = new Individual(numGenes);
		}
	}

	// Generates a population with empty individuals
	public Population(int popSize) {
		populationSize = popSize;
		individuals = new Individual[popSize];
		for (int i = 0; i < individuals.length; i++) {
			individuals[i] = null;
		}
	}

	// Puts individual in a defined position
	public void setIndividual(Individual individual, int position) {
		individuals[position] = individual;
	}

	// Puts individual in the next free position
	public void setIndividual(Individual individual) {
		for (int i = 0; i < individuals.length; i++) {
			if (individuals[i] == null) {
				individuals[i] = individual;
				return;
			}
		}
	}

	// Verifies if population has a solution
	public boolean isSolution(String solution) {
		Individual i = null;
		for (int j = 0; j < individuals.length; j++) {
			if (individuals[j].getGenes().equals(solution)) {
				i = individuals[j];
				break;
			}
		}
		if (i == null)
			return false;
		return true;
	}

	// Orders population by score.
	public void orderPopulation(){
		boolean changed = true;
		while(changed){
			changed = false;
			for (int i = 0; i < individuals.length - 1; i++) {
				if(individuals[i].getScore() < individuals[i + 1].getScore()){
					Individual temp = individuals[i];
					individuals[i] = individuals[i + 1];
					individuals[i + 1] = temp;
					changed = true;
				}
			}
		}
	}
	
	// Number of individuals in the population
	public int getNumIndividuals(){
		int num = 0;
		for (int i = 0; i < individuals.length; i++) {
			if(individuals[i] != null)
				num++;
		}
		return num;
	}
	
	public int getPopulationSize(){
		return populationSize;
	}
	
	public Individual getIndividual(int position){
		return individuals[position];
	}
}
