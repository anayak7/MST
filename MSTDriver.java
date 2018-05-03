package apps;
import structures.*;

import java.util.Scanner;

public class MSTDriver {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Enter the name of the file you want to create a graph out of.");
		String docName=sc.nextLine();
		
		Graph graph1= new Graph(docName);
		PartialTreeList ptl1= MST.initialize(graph1);
		//System.out.println("MST path is: " + MST.execute(ptl1));
		
		while(ptl1.size() != 0)
		{
			PartialTree pt= ptl1.remove();
			//Vertex vert1= ptl1.remove().getRoot();
			//PartialTree tree= ptl1.removeTreeContaining(vert1.neighbors.next.vertex);
			System.out.println("Vertex: " + pt.getRoot() + " " + "PQ: " + pt.getArcs());
			
		}
		
		
		
	
	}

}
