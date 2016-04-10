package com.rjpd.ml.main;

public class Main {
	
	public static void main(String[] args){
		Algorithm.setSolution("Nico Above The Law");
        Algorithm.setChars("!,.:;?áÁãÃâÂõÕôÔóÓéêÉÊíQWERTYUIOPASDFGHJKLÇZXCVBNMqwertyuiopasdfghjklçzxcvbnm1234567890' ");
        Algorithm.setCrossOverRate(0.6);
        Algorithm.setMutationRate(0.3);
        boolean elitism = true;
        int popSize = 100;
        int numGenerations = 10000;
        
        int numGenes = Algorithm.getSolution().length();
        Population pop = new Population(numGenes, popSize);
        
        boolean hasSolution = false;
        int generation = 0;
        
        System.out.println("Starting... Score of solution: " + Algorithm.getSolution().length());
        
        while(!hasSolution && generation < numGenerations){
        	generation++;
        	
        	pop = Algorithm.newGeneration(pop, elitism);
        	
        	System.out.println("Generation " + generation + " | Score: " + pop.getIndividual(0).getScore() + " | Best: " + pop.getIndividual(0).getGenes());
        	hasSolution = pop.isSolution(Algorithm.getSolution());
        }
        
        if (generation == numGenerations)
        	System.out.println("Maximum number of generations | " + pop.getIndividual(0).getGenes() + " " + pop.getIndividual(0).getScore());
        
        if (hasSolution)
        	System.out.println("Solution was found in generation number: " + generation + " | " + pop.getIndividual(0).getGenes() + " (Score: " + pop.getIndividual(0).getScore() + ")");
        
	}

}
