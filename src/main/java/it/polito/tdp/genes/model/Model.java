package it.polito.tdp.genes.model;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private SimpleWeightedGraph<Genes, DefaultWeightedEdge> grafo;
	private Map<String,Genes> idMap;
	public Model() {
		dao=new GenesDao();
		idMap=new HashMap<String,Genes>();
	}
	public void creaGrafo() {
		grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.getVertici(idMap));
		
		for(Adiacenza a: dao.getAdiacenze(idMap)) {
			if(a.getG1().getChromosome()!=a.getG2().getChromosome()) {
				Graphs.addEdge(grafo, a.getG1(), a.getG2(),Math.abs(a.getPeso()));
			}else {
				Graphs.addEdge(grafo, a.getG1(), a.getG2(),2.0*Math.abs(a.getPeso()));
			}
		}
		System.out.println("GRAFO CREATO");
		System.out.println("# vertici "+ grafo.vertexSet().size());
		System.out.println("# archi "+grafo.edgeSet().size());
	}
	public int totVertici() {
		// TODO Auto-generated method stub
		return grafo.vertexSet().size();
	}
	public  int totArchi() {
		// TODO Auto-generated method stub
		return grafo.edgeSet().size();
	}
	public Set<Genes> getVertici() {
		// TODO Auto-generated method stub
		return grafo.vertexSet();
	}
	public ArrayList<Adiacenti> getAdiacenti(Genes gg){
		ArrayList<Adiacenti> aa = new ArrayList<>();
		for(Genes g:Graphs.neighborListOf(grafo, gg)) {
			DefaultWeightedEdge e= this.grafo.getEdge(gg, g);
			Adiacenti adiacenti=new Adiacenti(g, grafo.getEdgeWeight(e));
			aa.add(adiacenti);
		}
		Collections.sort(aa);
		return aa;
		
	}
	
	
	
	
}
