package com.rjpd.ml.main;

import java.util.Random;

public class Individual {

	private String genes = "";
	private int score = 0;

	// Generates a random individual
	public Individual(int numGenes) {		
		genes = "";
		Random r = new Random();
		String chars = Algorithm.getChars();

		for (int i = 0; i < numGenes; i++) {
			genes += chars.charAt(r.nextInt(chars.length()));
		}

		generateScore();
	}
	
	// Generates a individual with the given genes
	public Individual(String genes){
		this.genes = genes;
		
		Random r = new Random();
		
		if(r.nextDouble() <= Algorithm.getMutationRate()){
			String chars = Algorithm.getChars();
			String newGene = "";
			
			int randomPosition = r.nextInt(genes.length());
			
			for (int i = 0; i < genes.length(); i++) {
				if(i == randomPosition)
					newGene += chars.charAt(r.nextInt(chars.length()));
				else
					newGene += genes.charAt(i);
			}
			this.genes = newGene;
		}
		
		generateScore();
	}

	private void generateScore() {
		String solution = Algorithm.getSolution();
		for (int i = 0; i < solution.length(); i++) {
			if (solution.charAt(i) == genes.charAt(i))
				score++;
		}
	}

	public int getScore() {
		return score;
	}

	public String getGenes() {
		return genes;
	}

}
