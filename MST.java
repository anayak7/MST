package apps;

import structures.*;
import structures.Vertex.Neighbor;

import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
	
		/* COMPLETE THIS METHOD */
		
		PartialTreeList pt= new PartialTreeList(); //creating an empty list
		MinHeap<PartialTree.Arc> heap= new MinHeap<PartialTree.Arc>(); //creating a heap (priority queue)
		
		for(int i=0; i < graph.vertices.length; i++) //traversing through vertices list to create single vertex partial trees
		{
			PartialTree vert= new PartialTree(graph.vertices[i]); //creating a partial tree with single vertex

			for(Neighbor j=graph.vertices[i].neighbors; j != null ; j= j.next) //traversing through vert's neightbors to create edges
			{
				//System.out.println("vert's neighbors: " + j.vertex.name);
				PartialTree.Arc edge= new PartialTree.Arc(graph.vertices[i], j.vertex, j.weight);
				heap.insert(edge); //inserting edge into heap
			}
			
			pt.append(vert); //adding vert to list
			vert.getArcs().merge(heap); //adding heap to PQ	
			heap = new MinHeap<PartialTree.Arc>(); //creating new heap
			//System.out.println("heap size after creating a new one is: " + heap.size());			
		}
		
		return pt;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		
		/* COMPLETE THIS METHOD */

		ArrayList<PartialTree.Arc> mstEdges= new ArrayList<PartialTree.Arc> (); //to store the edges that create MST
		
		while(ptlist.size() != 1) //if there's 1 tree, that means the algo is done
		{
			PartialTree curr = ptlist.remove();//removing partial tree from list
			PartialTree.Arc min = curr.getArcs().deleteMin(); //removing min
			Vertex v2= min.v2; //easy access to min's v2
			
			//System.out.println("curr is: " + curr.getRoot() + " " + "curr's min edge is: " + min);
			
			if(v2.getRoot().equals(curr.getRoot())) //checking if vertices are the same
			{
				//System.out.println("curr's root is: " + curr.getRoot() + " " + "curr's root's name is: " + curr.getRoot().name);
				
				while(v2.getRoot().equals(curr.getRoot()) && !curr.getArcs().isEmpty()) //finding an edge whose v2 doesn't match tree's root
				{
					min=curr.getArcs().deleteMin(); //choosing next arc
					v2=min.v2;
					//System.out.println("New min is : " + min);
				}	
				
			} 	

			//System.out.println("v2 is: " + v2.name);
			PartialTree curr2= ptlist.removeTreeContaining(v2); //removing v2 tree
			//System.out.println("curr2 tree is: " + curr2.getRoot());
			
			curr2.getRoot().parent=curr.getRoot(); //setting curr2's parent as curr
			curr.merge(curr2); //merging trees
			curr.getArcs().merge(curr2.getArcs()); //merging heaps
			
			ptlist.append(curr); //adding merged tree back to list
			//System.out.println("ptlist size is: " + ptlist.size());
			mstEdges.add(min); //adding min to mstEdges bc it's part of the MST path
			
		}
		
		//System.out.println("curr is: " + curr.getRoot() + " " + "curr's min edge is: " + min);

		return mstEdges;
		
		
	}
	
}
