
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

//------------------------------------------------------
public class GraphTester
	{
	// ------- main --------------
	public static void main(String[] args)
		{
		// build graph
		Prim<String> myGraph1 = new Prim<String>();
		Kruskal<String> myGraph2 = new Kruskal<String>();
		/*
		 * myGraph1.addEdge("A", "B", 5); myGraph1.addEdge("A", "C", 1);
		 * myGraph1.addEdge("A", "D", 4); myGraph1.addEdge("B", "E", 4);
		 * myGraph1.addEdge("B", "F", 2); myGraph1.addEdge("C", "G", 3);
		 * myGraph1.addEdge("D", "H", 4); myGraph1.addEdge("D", "I", 3);
		 * myGraph1.addEdge("F", "J", 5); myGraph1.addEdge("G", "K", 6);
		 * myGraph1.addEdge("G", "L", 2); myGraph1.addEdge("H", "M", 6);
		 * myGraph1.addEdge("H", "N", 5); myGraph1.addEdge("I", "N", 7);
		 */
		/*
		 * myGraph1.addEdge("A", "D", 6); myGraph1.addEdge("B", "C", 3);
		 * myGraph1.addEdge("B", "D", 8); myGraph1.addEdge("C", "E", 7);
		 * myGraph1.addEdge("B", "E", 5); myGraph1.addEdge("D", "E", 9);
		 * myGraph1.addEdge("A", "B", 2);
		 */

		myGraph1.addEdge("A", "F", 5);
		myGraph1.addEdge("A", "D", 6);
		myGraph1.addEdge("A", "B", 2);
		myGraph1.addEdge("B", "C", 7);
		myGraph1.addEdge("F", "C", 1);
		myGraph1.addEdge("C", "D", 9);
		myGraph1.addEdge("D", "G", 8);
		myGraph1.addEdge("D", "E", 4);
		myGraph1.addEdge("E", "F", 3);
		myGraph1.showAdjTable();

		/*
		 * myGraph1.addEdge("0","1",4); myGraph1.addEdge("0","7",8);
		 * myGraph1.addEdge("7","6",1); myGraph1.addEdge("6","5",2);
		 * myGraph1.addEdge("5","4",10); myGraph1.addEdge("4","3",9);
		 * myGraph1.addEdge("3","2",7); myGraph1.addEdge("2","1",8);
		 * myGraph1.addEdge("7","1",11); myGraph1.addEdge("7","8",7);
		 * myGraph1.addEdge("2","8",2); myGraph1.addEdge("8","6",6);
		 * myGraph1.addEdge("2","5",4); myGraph1.addEdge("3","5",14);
		 */
		/*
		 * myGraph1.addEdge("v","u",0); myGraph1.addEdge("v","w",0);
		 * myGraph1.addEdge("v","x",0); myGraph1.addEdge("u","t",0);
		 * myGraph1.addEdge("u","q",0); myGraph1.addEdge("q","r",0);
		 * myGraph1.addEdge("q","s",0);
		 */
		/*
		 * myGraph2.addEdge("A", "B", 5); myGraph2.addEdge("A", "C", 1);
		 * myGraph2.addEdge("A", "D", 4); myGraph2.addEdge("B", "E", 4);
		 * myGraph2.addEdge("B", "F", 2); myGraph2.addEdge("C", "G", 3);
		 * myGraph2.addEdge("D", "H", 4); myGraph2.addEdge("D", "I", 3);
		 * myGraph2.addEdge("F", "J", 5); myGraph2.addEdge("G", "K", 6);
		 * myGraph2.addEdge("G", "L", 2); myGraph2.addEdge("H", "M", 6);
		 * myGraph2.addEdge("H", "N", 5); myGraph2.addEdge("I", "N", 7);
		 */
		/*
		 * myGraph2.addEdge("A", "B", 2); myGraph2.addEdge("A", "D", 6);
		 * myGraph2.addEdge("B", "C", 3); myGraph2.addEdge("B", "D", 8);
		 * myGraph2.addEdge("C", "E", 7); myGraph2.addEdge("B", "E", 5);
		 * myGraph2.addEdge("D", "E", 9);
		 */
		/*
		 * myGraph2.addEdge("A", "F", 5); myGraph2.addEdge("A", "D", 6);
		 * myGraph2.addEdge("A", "B", 2); myGraph2.addEdge("B", "C", 7);
		 * myGraph2.addEdge("F", "C", 1); myGraph2.addEdge("C", "D", 9);
		 * myGraph2.addEdge("D", "G", 8); myGraph2.addEdge("D", "E", 4);
		 * myGraph2.addEdge("E", "F", 3);
		 */

		myGraph2.addEdge("0", "1", 4);
		myGraph2.addEdge("0", "7", 8);
		myGraph2.addEdge("7", "6", 1);
		myGraph2.addEdge("6", "5", 2);
		myGraph2.addEdge("5", "4", 10);
		myGraph2.addEdge("4", "3", 9);
		myGraph2.addEdge("3", "2", 7);
		myGraph2.addEdge("2", "1", 8);
		myGraph2.addEdge("7", "1", 11);
		myGraph2.addEdge("7", "8", 7);
		myGraph2.addEdge("2", "8", 2);
		myGraph2.addEdge("8", "6", 6);
		myGraph2.addEdge("2", "5", 4);
		myGraph2.addEdge("3", "5", 14);

		myGraph1.printMSTPrim();
		System.out.println();
		myGraph2.printMSTKruskal();

		myGraph1.showAdjTable();
		myGraph1.remove("A", "F");
		myGraph1.remove("A", "D");
		System.out.println("After removal");
		myGraph1.showAdjTable();
		System.out.println("After undo removal");
		myGraph1.undoRemoval(1);
		myGraph1.showAdjTable();
		Visitor<String> v = new VertexVisitor<>();
		myGraph1.breadthFirstTraversal("A", v);
		System.out.println("depth First");
		myGraph1.depthFirstTraversal("A", v);

		File file = new File("AL.txt");

		// if file doesnt exists, then create it
		if (!file.exists())
			{
			try
				{
				file.createNewFile();
				} catch (IOException e)
				{
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}

		PrintWriter out;
		try
			{
			out = new PrintWriter(file.getAbsoluteFile());
			myGraph1.writeToFile(out);
			out.close();
			} catch (FileNotFoundException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

		}

	}
