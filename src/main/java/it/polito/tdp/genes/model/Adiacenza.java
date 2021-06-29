package it.polito.tdp.genes.model;

public class Adiacenza {

	Genes g1;
	Genes g2;
	Double peso;
	public Adiacenza(Genes g1, Genes g2, Double peso) {
		super();
		this.g1 = g1;
		this.g2 = g2;
		this.peso = peso;
	}
	public final Genes getG1() {
		return g1;
	}
	public final void setG1(Genes g1) {
		this.g1 = g1;
	}
	public final Genes getG2() {
		return g2;
	}
	public final void setG2(Genes g2) {
		this.g2 = g2;
	}
	public final Double getPeso() {
		return peso;
	}
	public final void setPeso(Double peso) {
		this.peso = peso;
	}
	
	
}
