/*Name of program - Prim.java
 Project members - Nianthrini Vivekanandan, Hien Vo and Igor Ponomarev
 Team number - 5 - Minimum Spanning Tree
 Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
 ECLIPSE Version: Mars.2 Release (4.5.2)
 PROGRAM DESCRIPTION - A class to perform Prim's algorithm for minimum spanning tree.
 */

//--- Edge class ------------------------------------------------------

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Map.Entry;

class Edge<E> implements Comparable<Edge<E>> {
	Vertex<E> source, dest;
	double cost;

	Edge(Vertex<E> src, Vertex<E> dst, Double cst) {
		source = src;
		dest = dst;
		cost = cst;
	}

	Edge(Vertex<E> src, Vertex<E> dst, Integer cst) {
		this(src, dst, cst.doubleValue());
	}

	Edge() {
		this(null, null, 1.);
	}

	public String toString() {
		return "Edge: " + source.getData() + " to " + dest.getData() + ", distance: " + cost;
	}

	public int compareTo(Edge<E> rhs) {
		return (cost < rhs.cost ? -1 : cost > rhs.cost ? 1 : 0);
	}
}

public class Prim<E> extends Graph<E> {
	private PriorityQueue<Edge<E>> edgeHeap; // will add Edges from largest to
	private ArrayList<Edge<E>> primResult;// will get the result of MST

	public Prim() {
		edgeHeap = new PriorityQueue<>();
	}

	public void clear() {
		super.clear();
		primResult = null;
		edgeHeap.clear();
	}

	// algorithms
	public ArrayList<Edge<E>> applyPrim(E x) {
		Iterator<Entry<E, Vertex<E>>> iter;
		LinkedList<HashSet<Vertex<E>>> vertexSets = new LinkedList<HashSet<Vertex<E>>>();
		Iterator<HashSet<Vertex<E>>> fIter;
		HashMap<E, Vertex<E>> vertsInGraph;
		HashSet<Vertex<E>> singleton, vertSet, vertSetSrc = null, vertSetDst = null;
		Vertex<E> randomVertex;
		Vertex<E> src, dst, vert;
		Edge<E> smallestEdge;
		ArrayList<Edge<E>> newEdges = new ArrayList<Edge<E>>();
		int k, numVertsFound;

		// form a forest of sets, initializing each with only
		// one vertex from the graph
		vertsInGraph = vertexSet; // refer to Superclass' vertex set
		for (k = 0, iter = vertsInGraph.entrySet().iterator(); iter.hasNext(); k++) {
			vert = iter.next().getValue();
			singleton = new HashSet<Vertex<E>>();
			singleton.add(vert);
			vertexSets.add(singleton);
		}
		// get a random vertex from the list of vertices in the graph
		// iter = vertsInGraph.entrySet().iterator();
		// randomVertex = iter.next().getValue();
		// assign the vertex input from user to the variable as the starting
		// point
		randomVertex = vertexSet.get(x);
		// Check if the input from user is a valid vertex
		if (randomVertex == null) {
			System.out.println("Invalid vertex");
			return null;
		}

		// build an edge heap for the current vertex
		buildEdgeHeap(randomVertex);
		while (!edgeHeap.isEmpty() && vertexSets.size() > 1) {

			// pop smallest edge left in heap
			smallestEdge = edgeHeap.remove();
			src = smallestEdge.source;
			dst = smallestEdge.dest;

			// see if src and dst are in different sets. if so, take union
			for (fIter = vertexSets.iterator(), numVertsFound = 0; fIter.hasNext() && (numVertsFound < 2);) {
				vertSet = fIter.next();

				if (vertSet.contains(src)) {
					vertSetSrc = vertSet;
					numVertsFound++;
				}

				if (vertSet.contains(dst)) {
					vertSetDst = vertSet;
					numVertsFound++;
				}
			}
			if (vertSetSrc == vertSetDst) // same sets: reject
				continue;

			newEdges.add(smallestEdge);
			vertSetSrc.addAll(vertSetDst);
			vertexSets.remove(vertSetDst);
			// build a edge heap for the added vertex
			buildEdgeHeap(dst);
		}
		primResult = newEdges;// store the result to the local variable
		return newEdges;
	}

	// Builds a heap of edges which are connected to the vertex x
	private boolean buildEdgeHeap(Vertex<E> x) {
		Vertex<E> src, dst;
		Pair<Vertex<E>, Double> edge;
		double cost;
		src = x;
		Iterator<Entry<E, Pair<Vertex<E>, Double>>> edgeIter;
		// get the adjacency list for the vertex and if the heap doesnt contain
		// the edge add it
		for (edgeIter = x.adjList.entrySet().iterator(); edgeIter.hasNext();) {
			edge = edgeIter.next().getValue();
			dst = edge.first;
			cost = edge.second;
			if (!edgeHeap.contains(new Edge<E>(src, dst, cost)))
				edgeHeap.add(new Edge<E>(src, dst, cost));
		}
		return true;
	}

	// print the minimum spanning tree and the toal length
	public void printMSTPrim() {
		// check if MST was created
		if (primResult == null) {
			System.out.println("First create minimum spanning tree");
			return;
		}
		System.out.println("The minimum spanning tree according to Prim's algorithm is");
		Iterator<Edge<E>> iter1 = primResult.iterator();
		double length = 0;
		while (iter1.hasNext()) {
			Edge<E> values = iter1.next();
			System.out.println(values);
			length += values.cost;
		}
		System.out.println("The length of the tree is " + length);
	}

	// write the adjacency list and the minimum spanning tree to a file
	public boolean writeToFile(PrintWriter bw) {

			return super.writeToFile(bw);

	}
	// write the adjacency list and the minimum spanning tree to a file
		public boolean writePrimToFile(PrintWriter bw) {

			{
				// check if MST was created
				if (primResult == null) {
					System.out.println("First create minimum spanning tree");
					return false;
				}

				Iterator<Edge<E>> iter2 = primResult.iterator();
				if (iter2.hasNext()) {
					// writing the minimum spanning tree
					bw.write("------------------------ \n");
					bw.write("The minimum spanning tree according to Prim's algorithm is \n\n");
					// get the minimum spanning tree and write it to file
					double length = 0;
					while (iter2.hasNext()) {
						Edge<E> values = iter2.next();
						bw.write(values.toString() + "\n");
						length += values.cost;
					}
					bw.write("\nThe length of the tree is " + length);
				}

			}

			return true;

		}
}
