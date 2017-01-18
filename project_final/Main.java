
/*Name of program - Main.java
 Project members - Nianthrini Vivekanandan, Hien Vo and Igor Ponomarev
 Team number - 5 - Minimum Spanning Tree
 Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
 ECLIPSE Version: Mars.2 Release (4.5.2)
 PROGRAM DESCRIPTION - Driver class to test the project.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

public class Main {

	public static Scanner userScanner = new Scanner(System.in);
	// create a static variable to hold the graph
	private static Prim<String> mainGraph = new Prim<>();

	public static void main(String[] args) {
		while (true) {
			DisplayMenu();
			boolean inputNotCorrect = true;

			String input;

			while (inputNotCorrect) {
				input = userScanner.nextLine();
				inputNotCorrect = false;
				switch (input) {
				case "1":
					// read Graph
					readGraph();
					break;

				case "2":
					// add edge to graph
					addEdge();
					break;

				case "3":
					// remove edge from the graph
					RemoveEdge();
					break;

				case "4":
					// Undo previous removals
					UndoRemovals();
					break;

				case "5":
					// Dispay graph
					DisplayGraph();
					break;

				case "6":
					// Create minimum spanning tree
					getMST();
					break;

				case "7":
					// Display minimum spanning tree
					mainGraph.printMSTPrim();
					break;

				case "8":
					// Write graph to file
					WriteToFile();
					break;
				case "9":
					// Write graph to file
					WritePrimToFile();
					break;

				case "10":
					System.exit(0);
					break;

				default:
					System.out.print("Invalid input. Please try again: ");
					inputNotCorrect = true;
					break;
				}
			}
			System.out.println("\nPress Enter to continue\n");
			userScanner.nextLine();
		}

	}

	// method to display menu
	private static void DisplayMenu() {
		System.out.print("1). Read graph from a file. \n" + "2). Add edge to the graph \n"
				+ "3). Remove edge from a graph \n" + "4). Undo previous removals. \n" + "5). Display graph. \n"
				+ "6). Create minimum spanning tree. \n" + "7). Display minimum spanning tree. \n"
				+ "8). Write graph to file. \n" + "9). Write solution to file. \n"+ "10). Exit. \n" + "Enter your choice: ");
	}

	// method to read graph from file
	private static void readGraph() {
		mainGraph.clear();
		Scanner scanner1 = null;
		scanner1 = openInputFile();
		// if the file is empty display error message
		if (scanner1 == null) {
			System.out.println("Error reading the file");
			return;
		}

		String input;
		String edge_one, edge_two;
		Double distance;
		// if the file has content display success
		while (scanner1.hasNextLine()) {
			input = scanner1.nextLine();
			edge_one = input.substring(0, input.indexOf('-'));
			edge_two = input.substring((input.indexOf('-') + 1), input.indexOf(':'));
			distance = Double.parseDouble(input.substring((input.indexOf(':') + 1)));
			mainGraph.addEdge(edge_one, edge_two, distance);
		}
		System.out.println("Graph successfully read.");
	}

	// method to open file
	public static Scanner openInputFile() {
		String filename;
		Scanner scanner = null;

		System.out.print("Enter the input filename: ");
		filename = userScanner.nextLine();
		File file = new File(filename);

		try {
			scanner = new Scanner(file);
		} // end try
		catch (FileNotFoundException fe) {
			System.out.println("Can't open input file\n");
			return null; // array of 0 elements
		} // end catch
		return scanner;
	}

	// method to write MST to file
	private static void WritePrimToFile() {
		boolean result = false;
		System.out.println("Enter the file name: ");
		File file = new File(userScanner.nextLine());
	
		// if file doesnt exists, then create it
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		PrintWriter out;
		try {
			out = new PrintWriter(file.getAbsoluteFile());
			result = mainGraph.writePrimToFile(out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result)
			System.out.println("Solution written to file successfully");
		else
			System.out.println("Could not write solution to file");
		
	}
	// method to write MST to file
		private static void WriteToFile() {
			boolean result = false;
			
				System.out.println("Enter the file name: ");
				File file = new File(userScanner.nextLine());
				
				// if file doesnt exists, then create it
				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				PrintWriter out;
				try {
					out = new PrintWriter(file.getAbsoluteFile());
					result = mainGraph.writeToFile(out);
					out.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (result)
					System.out.println("Graph written to file successfully");
				else
					System.out.println("Could not write graph to file");
			
		}

	// method to add edge
	private static void addEdge() {
		// get input from user to the edge and write it to graph
		String edge_one, edge_two;
		Double distance;
		System.out.println("Enter the first edge name: ");
		edge_one = new String(userScanner.nextLine());
		System.out.println("Enter the second edge name: ");
		edge_two = new String(userScanner.nextLine());
		System.out.println("Enter the distance between edges name: ");
		distance = Double.parseDouble(userScanner.nextLine());
		mainGraph.addEdge(edge_one, edge_two, distance);
		System.out.println("Edge added.");
	}

	// method to get user input for displaying graph
	private static void DisplayGraph() {
		// display the graph based on user choice
		boolean inputNotCorrect = true;

		String input;
		System.out.println(
				"Choose the type of display:\n 1). Breadth first traversal.\n 2). Depth first traversal.\n 3). Show adjacency list.\n 4). All the above.\n Enter your choice:");
		while (inputNotCorrect) {

			input = userScanner.nextLine();
			inputNotCorrect = false;
			switch (input) {
			case "1":
				BreadthFirstTrav();
				break;
			case "2":
				DepthFirstTrav();
				break;
			case "3":
				System.out.println("\nAdjacency List");
				mainGraph.showAdjTable();
				break;
			case "4":
				BothTrav();
				System.out.println("\nAdjacency List");
				mainGraph.showAdjTable();
				break;

			default:
				System.out.print("Invalid input. Please try again: ");
				inputNotCorrect = true;
				break;
			}

		}

	}

	// method to remove edge
	private static void RemoveEdge() {
		// get input of vertices from user and remove the edge
		System.out.println("Enter the first Vertex to be removed: ");
		String input = userScanner.nextLine();
		System.out.println("Enter the second Vertex to be removed: ");
		String input1 = userScanner.nextLine();
		if (mainGraph.remove(input, input1)) {
			System.out.println("Successful removal.");
		} else {
			System.out.println("Removal failed.");
		}
	}

	// method to undo the removal
	private static void UndoRemovals() {
		// undo previous removals based on the number to undo
		System.out.println("Enter amount of removals to undo: ");
		Integer input = Integer.parseInt(userScanner.nextLine());
		if (mainGraph.undoRemoval(input)) {
			System.out.println("Successfully undone removals.");
		} else {
			System.out.println("Failed to undo.");
		}
	}

	// method to display breadth first traversal
	private static void BreadthFirstTrav() {
		// get starting position from user and display
		System.out.println("Possible starting positions: ");
		DisplaySet();
		System.out.print("Enter starting position: ");
		System.out.print("Breadth First:");
		mainGraph.breadthFirstTraversal(userScanner.nextLine(), new VertexVisitor<String>());

	}

	// method to display depth first traversal
	private static void DepthFirstTrav() {
		// get starting position from user and display
		System.out.println("Possible starting positions: ");
		DisplaySet();
		System.out.print("Enter starting position: ");
		System.out.print("Depth First:");
		mainGraph.depthFirstTraversal(userScanner.nextLine(), new VertexVisitor<String>());
	}

	// method to do all 3 display options
	private static void BothTrav() {
		// get starting position from user and display
		System.out.println("Possible starting positions: ");
		DisplaySet();
		System.out.print("Enter starting position: ");
		String startPos = userScanner.nextLine();
		System.out.println("\nBreadth First Traversal.");
		mainGraph.breadthFirstTraversal(startPos, new VertexVisitor<String>());
		System.out.println("\nDepth First Traversal.");
		mainGraph.depthFirstTraversal(startPos, new VertexVisitor<String>());
	}

	// method to display all vertices
	private static void DisplaySet() {
		// displays all the vertices in the graph
		String output = new String();
		Iterator<Entry<String, Vertex<String>>> iter;
		iter = mainGraph.vertexSet.entrySet().iterator();
		while (iter.hasNext()) {
			output += iter.next().getValue().data;
			output += ", ";
		}
		System.out.println(output);
		return;
	}

	// method to find MST
	private static void getMST() {
		// get starting position from user and find MST
		System.out.println("Possible starting positions: ");
		DisplaySet();
		System.out.print("Enter starting position: ");
		String startPos = userScanner.nextLine();
		if (mainGraph.applyPrim(startPos) != null)
		{
			System.out.println("Minimum spanning tree created");
			mainGraph.printMSTPrim();
		}
		else
			System.out.println("Could not create minimum spanning tree");
	}
}