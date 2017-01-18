
/*Name of program - Graph.java
 Project members - Nianthrini Vivekanandan, Hien Vo and Igor Ponomarev
 Team number - 5 - Minimum Spanning Tree
 Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
 ECLIPSE Version: Mars.2 Release (4.5.2)
 PROGRAM DESCRIPTION - A class which represents the graph. It has methods to add and remove edges
 and also for traversals.
 */
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

interface Visitor<T> {
	public void visit(T obj);
}

// --- assumes definition of simple class Pair<E, F>

// --- Vertex class ------------------------------------------------------
class Vertex<E> {
	public static final double INFINITY = Double.MAX_VALUE;
	public HashMap<E, Pair<Vertex<E>, Double>> adjList = new HashMap<E, Pair<Vertex<E>, Double>>();
	public E data;
	public double dist; // used for particular graph problems, NOT the graph
	// itself
	public boolean visited;
	public Vertex<E> nextInPath; // used for particular graph problems, NOT the
	// graph itself

	public Vertex(E x) {
		data = x;
		dist = INFINITY;
		nextInPath = null;
	}

	public Vertex() {
		this(null);
	}

	public E getData() {
		return data;
	}

	public double getDistance() {
		return dist;
	}

	public boolean isVisited() {
		return visited;
	}

	public void visit() {
		visited = true;
	}

	public void unvisit() {
		visited = false;
	}

	public Iterator<Map.Entry<E, Pair<Vertex<E>, Double>>> iterator() {
		return adjList.entrySet().iterator();
	}

	public void addToAdjList(Vertex<E> neighbor, double cost) {
		if (adjList.get(neighbor.data) == null)
			adjList.put(neighbor.data, new Pair<Vertex<E>, Double>(neighbor, cost));
		// Note: if you want to change the cost, you'll need to remove it and
		// then add it back
	}

	public void addToAdjList(Vertex<E> neighbor, int cost) {
		addToAdjList(neighbor, (double) cost);
	}

	public boolean equals(Object rhs) {
		if (!(rhs instanceof Vertex<?>))
			return false;
		Vertex<E> other = (Vertex<E>) rhs;

		return (data.equals(other.data));

	}

	public int hashCode() {
		return (data.hashCode());
	}

	public void showAdjList() {
		Iterator<Entry<E, Pair<Vertex<E>, Double>>> iter;
		Entry<E, Pair<Vertex<E>, Double>> entry;
		Pair<Vertex<E>, Double> pair;

		System.out.print("Adj List for " + data + ": ");
		iter = adjList.entrySet().iterator();
		while (iter.hasNext()) {
			entry = iter.next();
			pair = entry.getValue();
			System.out.print(pair.first.data + "(" + String.format("%3.1f", pair.second) + ") ");
		}
		System.out.println();
	}
	/*
	 * //added for debugging public String toString() { return data.toString();
	 * 
	 * }
	 */
}

// --- Graph class ------------------------------------------------------
public class Graph<E> {
	// the graph data is all here --------------------------
	protected HashMap<E, Vertex<E>> vertexSet;
	protected LinkedStack<Pair<Vertex<E>, Double>> tempStack = new LinkedStack<>();

	// public graph methods --------------------------------
	public Graph() {
		vertexSet = new HashMap<E, Vertex<E>>();
	}

	public void addEdge(E source, E dest, double cost) {
		Vertex<E> src, dst;

		// put both source and dest into vertex list(s) if not already there
		src = addToVertexSet(source);
		dst = addToVertexSet(dest);

		// add dest to source's adjacency list
		src.addToAdjList(dst, cost);
		dst.addToAdjList(src, cost); // ADD THIS IF UNDIRECTED GRAPH
	}

	public void addEdge(E source, E dest, int cost) {
		addEdge(source, dest, (double) cost);
	}

	// adds vertex with x in it, and always returns ref to it
	public Vertex<E> addToVertexSet(E x) {
		Vertex<E> retVal = null;
		Vertex<E> foundVertex;

		// find if Vertex already in the list:
		foundVertex = vertexSet.get(x);

		if (foundVertex != null) // found it, so return it
		{
			return foundVertex;
		}

		// the vertex not there, so create one
		retVal = new Vertex<E>(x);
		vertexSet.put(x, retVal);

		return retVal; // should never happen
	}

	public boolean remove(E start, E end) {
		Vertex<E> startVertex = vertexSet.get(start);

		boolean removedOK = false;

		if (startVertex != null) {
			Pair<Vertex<E>, Double> endPair = startVertex.adjList.remove(end);
			// stores the removed vertex pair in linkedstack
			tempStack.push(endPair);
			// added to remove vertex without any edges from hashmap
			if (startVertex.adjList.isEmpty())
				vertexSet.remove(startVertex.data);
			removedOK = endPair != null;
		}
		// Add if UNDIRECTED GRAPH:
		Vertex<E> endVertex = vertexSet.get(end);
		if (endVertex != null) {
			Pair<Vertex<E>, Double> startPair = endVertex.adjList.remove(start);
			// stores the removed vertex pair in linkedstack
			tempStack.push(startPair);
			// added to remove vertex without any edges from hashmap
			if (endVertex.adjList.isEmpty())
				vertexSet.remove(endVertex.data);
			removedOK = startPair != null;
		}

		return removedOK;
	}

	// it undo's the previous removal by pop the removed values from the
	// linkdstack
	// it adds the edges back to the graph
	public boolean undoRemoval() {
		// if there is no element in the stack - return false
		if (tempStack.isEmpty())
			return false;
		else {
			// get the first and second vertex
			Pair<Vertex<E>, Double> startPair1 = tempStack.pop();
			Pair<Vertex<E>, Double> endPair1 = tempStack.pop();
			// add the removed edge back to the graph
			addEdge(startPair1.first.data, endPair1.first.data, startPair1.second);
			return true;
		}
	}

	// it undo's the previous n removal's by pop the removed values from the
	// linkdstack
	// it adds the edges back to the graph
	public boolean undoRemoval(int n) {
		// if n is -ve or stack is empty return false
		if (tempStack.isEmpty() || (n < 0))
			return false;

		else {
			// if n is > stacksize/2 - remember 2 vertices are stored in the
			// stack for each edge
			if (n > (tempStack.size() / 2))
				n = tempStack.size() / 2;

			while (!tempStack.isEmpty() && n != 0) {
				// till n becomes 0 and if stack is not empty
				Pair<Vertex<E>, Double> startPair1 = tempStack.pop();
				Pair<Vertex<E>, Double> endPair1 = tempStack.pop();
				// add the edge back to the graph
				addEdge(startPair1.first.data, endPair1.first.data, startPair1.second);
				n--;// decrease value of n
			}
			return true;
		}
	}

	public void showAdjTable() {
		Iterator<Entry<E, Vertex<E>>> iter;

		System.out.println("------------------------ ");
		iter = vertexSet.entrySet().iterator();
		while (iter.hasNext()) {
			(iter.next().getValue()).showAdjList();
		}
		System.out.println();
	}

	public void clear() {
		vertexSet.clear();
	}

	// reset all vertices to unvisited
	public void unvisitVertices() {
		Iterator<Entry<E, Vertex<E>>> iter;

		iter = vertexSet.entrySet().iterator();
		while (iter.hasNext()) {
			iter.next().getValue().unvisit();
		}
	}

	/** Breadth-first traversal from the parameter startElement */
	public void breadthFirstTraversal(E startElement, Visitor<E> visitor) {
		unvisitVertices();

		Vertex<E> startVertex = vertexSet.get(startElement);
		breadthFirstTraversalHelper(startVertex, visitor);
	}

	/** Depth-first traversal from the parameter startElement */
	public void depthFirstTraversal(E startElement, Visitor<E> visitor) {
		unvisitVertices();

		Vertex<E> startVertex = vertexSet.get(startElement);
		depthFirstTraversalHelper(startVertex, visitor);
	}

	// breadth first traversal
	protected void breadthFirstTraversalHelper(Vertex<E> startVertex, Visitor<E> visitor) {
		// create a queue
		LinkedQueue<Vertex<E>> vertexQueue = new LinkedQueue<>();
		E startData = startVertex.getData();
		// get the starting vertex and visit it
		startVertex.visit();
		visitor.visit(startData);
		// put the startng vertex in the queue
		vertexQueue.enqueue(startVertex);
		// while the queue is not empty, get the adjacent vertices of the first
		// vertex in the queue
		while (!vertexQueue.isEmpty()) {
			Vertex<E> nextVertex = vertexQueue.dequeue();
			Iterator<Map.Entry<E, Pair<Vertex<E>, Double>>> iter = nextVertex.iterator(); // iterate
			// adjacency
			// list
			// visit all the neighboring vertices and put it in the queue
			// continue for all the vertices in the queue if the neighboring
			// vertices are not yet visited
			while (iter.hasNext()) {
				Entry<E, Pair<Vertex<E>, Double>> nextEntry = iter.next();
				Vertex<E> neighborVertex = nextEntry.getValue().first;
				if (!neighborVertex.isVisited()) {
					vertexQueue.enqueue(neighborVertex);
					neighborVertex.visit();
					visitor.visit(neighborVertex.getData());
				}
			}
		}
	} // end breadthFirstTraversalHelper
		// depth first traversal

	protected void depthFirstTraversalHelper(Vertex<E> startVertex, Visitor<E> visitor) {
		// YOU COMPLETE THIS (USE THE ALGORITHM GIVEN FOR LESSON 11 EXERCISE)
		// create a stack of vertices
		LinkedStack<Vertex<E>> vertexStack = new LinkedStack<>();
		E startData = startVertex.getData();
		// get the starting vertex, visit it and push it into stack
		startVertex.visit();
		visitor.visit(startData);
		vertexStack.push(startVertex);
		// while the stack is not empty
		while (!vertexStack.isEmpty()) {
			Vertex<E> getVertex = vertexStack.peek();
			Iterator<Map.Entry<E, Pair<Vertex<E>, Double>>> iter = getVertex.iterator(); // iterate
			// adjacency
			// list
			// get the neighboring vetices, if it is not visited, visit it and
			// push into stack
			// break the loop, go back to getting the top of the stack and visit
			// the child,
			// continue till the last child
			// if it is already visited, visit the next adjacent child
			int visitedVertex = 0;// counter to see if all the child are already
			// visited

			while (iter.hasNext()) {
				Entry<E, Pair<Vertex<E>, Double>> nextEntry = iter.next();
				Vertex<E> neighborVertex = nextEntry.getValue().first;
				if (!neighborVertex.isVisited()) {
					vertexStack.push(neighborVertex);
					neighborVertex.visit();
					visitor.visit(neighborVertex.getData());
					++visitedVertex;// counter to increase if a unvisited child
					// exists
					break;
				} else {
					continue;
				}

			}
			// if all the child are visited for the vertex, pop it from the
			// stack
			if (visitedVertex == 0)
				vertexStack.pop();

		}
	}

	// WRITE THE INSTANCE METHOD HERE TO
	// WRITE THE GRAPH's vertices and its
	// adjacency list TO A TEXT FILE (SUGGEST TO PASS AN
	// ALREADY OPEN PrintWriter TO THIS) !
	public boolean writeToFile(PrintWriter bw) {
		
			
			// iterator for each entry
			Iterator<Entry<E, Vertex<E>>> iter;

			bw.write("------------------------ \n");

			iter = vertexSet.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<E, Vertex<E>> element = iter.next();
				// get the adjacency list for the vertex element
				HashMap<E, Pair<Vertex<E>, Double>> adjList = (element.getValue()).adjList;
				Iterator<Entry<E, Pair<Vertex<E>, Double>>> iter1;
				Entry<E, Pair<Vertex<E>, Double>> entry;
				Pair<Vertex<E>, Double> pair;
				// write the adjacency list
				bw.write("Adj List for " + element.getValue().data + ": ");
				iter1 = adjList.entrySet().iterator();
				while (iter1.hasNext()) {
					entry = iter1.next();
					pair = entry.getValue();
					bw.write(pair.first.data + "(" + String.format("%3.1f", pair.second) + ") ");
				}
				bw.write("\n");
			}

		return true;
	}

}
