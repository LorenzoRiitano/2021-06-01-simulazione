package it.polito.tdp.genes.model;

public class Adiacenti implements Comparable<Adiacenti>{

	Genes genes;
	Double pDouble;
	public Adiacenti(Genes genes, Double pDouble) {
		super();
		this.genes = genes;
		this.pDouble = pDouble;
	}
	
	@Override
	public int compareTo(Adiacenti a1) {
		// TODO Auto-generated method stub
		return -(this.pDouble.compareTo(a1.getpDouble()));
	}

	public final Genes getGenes() {
		return genes;
	}

	public final void setGenes(Genes genes) {
		this.genes = genes;
	}

	public final Double getpDouble() {
		return pDouble;
	}

	public final void setpDouble(Double pDouble) {
		this.pDouble = pDouble;
	}

	@Override
	public String toString() {
		return genes.getGeneId()+" - "+pDouble;
	}
	
	
}
