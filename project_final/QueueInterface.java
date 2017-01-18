/*Name of program - QueueInterface.javaProject members - Nianthrini Vivekanandan, Hien Vo and Igor PonomarevTeam number - 5 - Minimum Spanning TreeComputer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; ECLIPSE Version: Mars.2 Release (4.5.2)PROGRAM DESCRIPTION - An interface for ADT queue. *//** * An interface for the ADT queue. *  * @author Frank M. Carrano * @author Timothy M. Henry * @version 4.0 UPDATED by C. Lee-Klawender */public interface QueueInterface<T>	{	/**	 * Adds a new entry to the back of this queue.	 * 	 * @param newEntry	 *            An object to be added.	 * @return True if succesfully added the newEntry, false otherwise	 */	public boolean enqueue(T newEntry);	/**	 * Removes and returns the entry at the front of this queue.	 * 	 * @return The object at the front of the queue or null if the queue is	 *         empty before the operation.	 */	public T dequeue();	/**	 * Retrieves the entry at the front of this queue.	 * 	 * @return The object at the front of the queue or null if the queue is	 *         empty.	 */	public T peekFront();	/**	 * Detects whether this queue is empty.	 * 	 * @return True if the queue is empty, or false otherwise.	 */	public boolean isEmpty();	/**	 * Returns number of items in this queue	 * 	 * @return: Number of items	 */	public int size();	} // end QueueInterface