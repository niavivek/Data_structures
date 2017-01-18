/*Name of program - HashSC.java
Student name - Nianthrini Vivekanandan
Current Date - 06/05/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class that extends hashtable and implements all the abstract methods.
 */
package hw5;


import java.util.*;

public class HashSC<E> extends HashTable<E>// CHANGE TO MAKE THIS A SUBCLASS OF HashTable for HW#5!!!!!!!!!!
{
   static final int INIT_TABLE_SIZE = 97;
   static final double INIT_MAX_LAMBDA = 1.5;
   
   protected LList<E>[] mLists;
   protected int mSize;
   protected int mTableSize;
   protected double mMaxLambda;

   
   public HashSC(int tableSize,Hasher<E> h,Comparator<E> c) // ADD Comparator<E> and Hasher<E> parameters for HW#5!!!!!!!!
   {
	   // Pass Comparator<E> and Hasher<E> parameters to the SUPERCLASS constructor for HW#5!!!!!!!!!!
	   
	   super(h,c);
      if (tableSize < INIT_TABLE_SIZE)
         mTableSize = INIT_TABLE_SIZE;
      else
         mTableSize = nextPrime(tableSize);

      allocateMListArray();  // uses mTableSize;
      mMaxLambda = INIT_MAX_LAMBDA;
   }
   
   public HashSC(Hasher<E> h,Comparator<E> c)// ADD Comparator<E> and Hasher<E> parameters for HW#5!!!!!!!!
   {
      this(INIT_TABLE_SIZE,h,c); // FIX THIS (also pass Comparator<E> and Hasher<E>)
   }
   


   public E getEntry(E target)
   {
		// FINISH THIS (should be like remove, but  OR the data for the Entry if found, null if not found (YOU MAY NEED TO CAST THE RETURN VALUE)
	 LList<E> theList = mLists[myHash(target)];
	   Iterator<E> iter = theList.iterator();
	   E currElem;
	   //return 
		//   what the iterator returned if the comparator's compare 
		//   method returns 0
	   for(int i=0; iter.hasNext(); ++i )
	   {
		   currElem = iter.next();
		   int value = comparator.compare(currElem, target);
		   if(value == 0)
		   {
			   return currElem;
		   }
	   }

    // not found
    return null;   
   }
   
   public boolean contains( E x)
   { 
      LList<E> theList = mLists[myHash(x)];
	   Iterator<E> iter = theList.iterator();
	  //get an interator for the linked list
	   E currElem;
	   
	   for(int i=0; iter.hasNext(); ++i )
	   {
		   //if it has elements- compare the value, if value is same return true
		   currElem = iter.next();
		   if(comparator.compare(currElem, x)==0)
		   {
			   return true;
		   }
	   }

     // not found
     return false;   
   }
   
   public void makeEmpty()
   {
      int k, size = mLists.length;

      for(k = 0; k < size; k++)
         mLists[k].clear();
      mSize = 0;  
   }
   
   public boolean insert( E x)
   {
	   LList<E> theList = mLists[myHash(x)];
	   Iterator<E> iter = theList.iterator();
	   E currElem;
	   //iterate using iterator and compare values
	   for(int i=0; iter.hasNext(); ++i )
	   {
		   currElem = iter.next();
		   if((comparator.compare(currElem, x)) == 0)
		   {
			   return false;//return false if element is already found, cannot insert duplicate
		   }
		   //if it already has 1 element, there is a collision
			   ++numCollisions;
	   }

      // not found   

		// ADD HERE: check and maybe UPDATE member counter variable
      // not found so we insert
	   theList.add(x);//if empty or not found insert the value in the list
		// ADD HERE: possibly update longestCollisionRun variable
		//    which should be counting the longest linked list
	   //if the length of linked list is greater than the longestrun variable update it
      if(longestCollisionRun < theList.getLength())
    	  longestCollisionRun = theList.getLength();
      // check load factor
      if( ++mSize > mMaxLambda * mTableSize )
         rehash();

      return true; 
   }
   
   public boolean remove( E x) 
   {
	   LList<E> theList = mLists[myHash(x)];
	   Iterator<E> iter = theList.iterator();
	   E currElem;
	   //use iterator to iterate the list
	   for(int i=0; iter.hasNext(); ++i )
	   {
		   currElem = iter.next();
		   if(comparator.compare(currElem, x)==0)
		   {//if values are same, remove the value and decrease count
			   theList.remove(i+1);
			   --mSize;
			   return true;
		   }
	   }

      // not found
      return false;   
   }

   public int size()  { return mSize; }
   
   public boolean setMaxLambda( double lam )
   {
      if (lam < .1 || lam > 100.)
         return false;
      mMaxLambda = lam;
      return true;
   }

   public void displayStatistics()
   {
	   System.out.println("\nIn the HashSC object:\n");
	   System.out.println( "Table Size = " +  mTableSize );;
	   System.out.println( "Number of entries = " + mSize);
	   System.out.println( "Load factor = " + (double)mSize/mTableSize);
	   System.out.println( "Number of collisions = " + this.numCollisions );
	   System.out.println( "Longest Linked List = " + this.longestCollisionRun );
   }
   
   // protected methods of class ----------------------
   protected void rehash()
   {
		// ADD CODE HERE TO RESET THE HashTable COUNTERS TO 0 for HW#5!!!!!!!!!!!!!!!!
	   numCollisions = 0;
	   longestCollisionRun = 0;
	   
      // we save old list and size then we can reallocate freely
      LList<E>[] oldLists = mLists;
      int k, oldTableSize = mTableSize;
      Iterator<E> iter;

      mTableSize = nextPrime(2*oldTableSize);
      
      // allocate a larger, empty array
      allocateMListArray();  // uses mTableSize;

      // use the insert() algorithm to re-enter old data
      mSize = 0;
      for(k = 0; k < oldTableSize; k++)
         for(iter = oldLists[k].iterator(); iter.hasNext() ; )
            insert( iter.next());
   }
   
   protected int myHash( E x)
   {
      int hashVal;

// CHANGE TO USE Hasher's hash method INSTEAD of x.hashCode for HW#5!!!!!!!!!!!
      hashVal = hasher.hash(x) % mTableSize; 
      if(hashVal < 0)
         hashVal += mTableSize;

      return hashVal;
   }
   
   protected static int nextPrime(int n)
   {
      int k, candidate, loopLim;

      // loop doesn't work for 2 or 3
      if (n <= 2 )
         return 2;
      else if (n == 3)
         return 3;

      for (candidate = (n%2 == 0)? n+1 : n ; true ; candidate += 2)
      {
         // all primes > 3 are of the form 6k +/- 1
         loopLim = (int)( (Math.sqrt((double)candidate) + 1)/6 );

         // we know it is odd.  check for divisibility by 3
         if (candidate%3 == 0)
            continue;

         // now we can check for divisibility of 6k +/- 1 up to sqrt
         for (k = 1; k <= loopLim; k++)
         {
            if (candidate % (6*k - 1) == 0)
               break;
            if (candidate % (6*k + 1) == 0)
               break;
         }
         if (k > loopLim)
            return candidate;
      }
   }
   
   private  void allocateMListArray()
   {
      int k;
      
      mLists = new LList[mTableSize];
      for (k = 0; k < mTableSize; k++)
         mLists[k] = new LList<E>();
   }

@Override
public void displayTable() {
	// TODO Auto-generated method stub
	 //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// DON'T FORGET TO OVERRIDE displayTable() (YOU WRITE FOR HW#5)
	// FOR EACH ARRAY ELEMENT...
//	    Display the index of the array element, THEN 
//	      if the linked list at that element is empty, display "---"
//	       otherwise, display the data in each linked list node all on ONE line, BUT
//	     	YOU MUST USE THE ITERATOR RETURNED FROM EACH LINKED LIST
//	     	to retrieve each Node's data (YOU ARE NOT ALLOWED TO CALL getEntry)
//	     	(see the test runs for examples) 
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	Iterator<E> iter;
	LList<E> theList;
	for(int i = 0;i < mLists.length;i++)
	{//use iterator for each linked list in the array
		 theList = mLists[i];
		  iter = theList.iterator();
		System.out.print(i+": ");
		//display values if not empty
		if(iter.hasNext())
		{
			System.out.print(iter.next());
			while(iter.hasNext()){
			System.out.print(" -> "+iter.next());
			}
		}
		else
			System.out.print("----");
		System.out.println();
	}
}
}

