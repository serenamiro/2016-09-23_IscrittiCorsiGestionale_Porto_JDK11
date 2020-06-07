package it.polito.tdp.gestionale.db;

public class TestDAO {

	// Test main
		public static void main(String[] args) {

			PortoDAO pd = new PortoDAO();
			System.out.println(pd.getAutore(85));
			System.out.println(pd.getArticolo(2293546));
			System.out.println(pd.getArticolo(1941144));
		}
}
