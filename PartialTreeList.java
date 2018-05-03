package apps;

import java.util.Iterator;
import java.util.NoSuchElementException;

import structures.Vertex;


public class PartialTreeList implements Iterable<PartialTree> {
    
	/**
	 * Inner class - to build the partial tree circular linked list 
	 * 
	 */
	public static class Node {
		/**
		 * Partial tree
		 */
		public PartialTree tree;
		
		/**
		 * Next node in linked list
		 */
		public Node next;
		
		/**
		 * Initializes this node by setting the tree part to the given tree,
		 * and setting next part to null
		 * 
		 * @param tree Partial tree
		 */
		public Node(PartialTree tree) {
			this.tree = tree;
			next = null;
		}
	}

	/**
	 * Pointer to last node of the circular linked list
	 */
	private Node rear;
	
	/**
	 * Number of nodes in the CLL
	 */
	private int size;
	
	/**
	 * Initializes this list to empty
	 */
    public PartialTreeList() {
    	rear = null;
    	size = 0;
    }

    /**
     * Adds a new tree to the end of the list
     * 
     * @param tree Tree to be added to the end of the list
     */
    public void append(PartialTree tree) {
    	Node ptr = new Node(tree);
    	if (rear == null) {
    		ptr.next = ptr;
    	} else {
    		ptr.next = rear.next;
    		rear.next = ptr;
    	}
    	rear = ptr;
    	size++;
    }

    /**
     * Removes the tree that is at the front of the list.
     * 
     * @return The tree that is removed from the front
     * @throws NoSuchElementException If the list is empty
     */
    public PartialTree remove() 
    throws NoSuchElementException {
    		
    		/* COMPLETE THIS METHOD */
    	
    	if(size == 0) //if list is empty
    	{
    		throw new NoSuchElementException();
    	}

    	Node front=null;

    	if(size == 1) //if list has one element
    	{
    		front=rear;
    		rear=null;

    	} else {

    		front=rear.next;
    		rear.next=rear.next.next;
    	}

    	size--;

    		return front.tree;
    }

    /**
     * Removes the tree in this list that contains a given vertex.
     * 
     * @param vertex Vertex whose tree is to be removed
     * @return The tree that is removed
     * @throws NoSuchElementException If there is no matching tree
     */
    public PartialTree removeTreeContaining(Vertex vertex) 
    throws NoSuchElementException {
    		/* COMPLETE THIS METHOD */
    	
    	Node ptr=rear.next, prev=rear;
    	//System.out.println("vertex is: " + vertex.getRoot().parent);

    	if(size == 0) //if list is empty
    	{
    		throw new NoSuchElementException("No matching tree");
    	}

    	if(size == 1) //if list only contains rear
    	{
    		//System.out.println("list size is 1 & prev's value is: " + prev.tree.getRoot().name);
    		
    		rear = null;
    		size--;
    		return prev.tree;

    	}

    	while(!ptr.equals(rear)) //traversing through adjacency LL, need to traverse once before loop check to be able to check rear 
    	{	
    		//System.out.println("ptr is: " + ptr.tree.getRoot() + " " + "prev is: " + prev.tree.getRoot());

    		if(ptr.tree.getRoot().equals(vertex.getRoot())) //comparing curr vertex to the one we want
    		{	
    			prev.next=ptr.next;
    			size--;
    			return ptr.tree;
    		}

    		prev=prev.next;
    		ptr=ptr.next;
    	}

    	//System.out.println("I'm outside the while loop!");
    //	System.out.println("ptr tree is: " + ptr.tree.getRoot());

    	if(ptr.equals(rear))
    	{
        //	System.out.println("ptr does equal rear!!");
        	
	    	if(ptr.tree.getRoot().equals(vertex.getRoot()))
		{
	        	//System.out.println("ptr does equal vertex!");

			prev.next=ptr.next;
			rear=prev;
			size--;
			return ptr.tree;
			
		} else {
			
    		throw new NoSuchElementException("No matching tree");

		}
    	}

    	return null;
    }
    
    /**
     * Gives the number of trees in this list
     * 
     * @return Number of trees
     */
    public int size() {
    	return size;
    }
    
    /**
     * Returns an Iterator that can be used to step through the trees in this list.
     * The iterator does NOT support remove.
     * 
     * @return Iterator for this list
     */
    public Iterator<PartialTree> iterator() {
    	return new PartialTreeListIterator(this);
    }
    
    private class PartialTreeListIterator implements Iterator<PartialTree> {
    	
    	private PartialTreeList.Node ptr;
    	private int rest;
    	
    	public PartialTreeListIterator(PartialTreeList target) {
    		rest = target.size;
    		ptr = rest > 0 ? target.rear.next : null;
    	}
    	
    	public PartialTree next() 
    	throws NoSuchElementException {
    		if (rest <= 0) {
    			throw new NoSuchElementException();
    		}
    		PartialTree ret = ptr.tree;
    		ptr = ptr.next;
    		rest--;
    		return ret;
    	}
    	
    	public boolean hasNext() {
    		return rest != 0;
    	}
    	
    	public void remove() 
    	throws UnsupportedOperationException {
    		throw new UnsupportedOperationException();
    	}
    	
    }
}


