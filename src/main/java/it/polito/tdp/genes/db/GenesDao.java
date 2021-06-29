package it.polito.tdp.genes.db;

import java.awt.desktop.AboutHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Genes> getVertici(Map<String, Genes> idMap) {
		// TODO Auto-generated method stub
		String sql = "SELECT g.* "
				+ "FROM genes AS g "
				+ "WHERE g.Essential='Essential' "
				+ "GROUP BY g.GeneID";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				if(!idMap.containsKey(genes.getGeneId()))
					idMap.put(genes.getGeneId(), genes);
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return (ArrayList<Genes>) result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}

	public ArrayList<Adiacenza> getAdiacenze(Map<String, Genes> idMap) {
		// TODO Auto-generated method stub
		String sql = "SELECT i.* "
				+ "FROM interactions AS i, genes AS g1, genes AS g2 "
				+ "WHERE i.GeneID1<> i.GeneID2 AND g1.GeneID= i.GeneID1 AND g2.GeneID= i.GeneID2 "
				+ "AND g1.Essential='Essential' AND g2.Essential=g1.Essential "
				+ "GROUP BY i.GeneID1,i.GeneID2 ";
		ArrayList<Adiacenza> result = new ArrayList<Adiacenza>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				if(idMap.get(res.getString("GeneID1"))!=null && idMap.get(res.getString("GeneID2"))!=null) {
					Adiacenza a=new Adiacenza(idMap.get(res.getString("GeneID1")), idMap.get(res.getString("GeneID2")), res.getDouble("Expression_Corr"));
				
					result.add(a);
				}
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}
	


	
}
