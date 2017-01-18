/*Name of program - HashRandom.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class to store the values as a hashtable and it overrides the methods
in the hashtable interface. Uses random object for hashing.
 */
package extraCredit_RandomProbe;
import java.util.*;

// HashQP class --------------------------------------------
public class HashRandom<E> extends HashTable<E>
{
	protected static final int ACTIVE = 0;
	   protected static final int EMPTY = 1;
	   protected static final int DELETED = 2;
	   
	   static final int INIT_TABLE_SIZE = 97;
	   static final double INIT_MAX_LAMBDA = 0.49;
	   
	   protected HashEntry<E>[] mArray;
	   protected int mSize;
	   protected int mLoadSize;
	   protected int mTableSize;
	   protected double mMaxLambda;
	   
	   // public methods ---------------------------------
	   public HashRandom(int tableSize,Hasher<E> h, Comparator<E> c) // ADD Comparator<E> and Hasher<E> parameters for HW#5!!!!!!!!
	   {
		   // PASS corresponding parameters to Comparator<E> and Hasher<E> parameters of the superclass constructor!!!!!
		   
		   super(h,c);
	      mLoadSize = mSize = 0;
	      if (tableSize < INIT_TABLE_SIZE)
	         mTableSize = INIT_TABLE_SIZE;
	      else
	         mTableSize = nextPrime(tableSize);

	      allocateArray();  // uses mTableSize;
	      mMaxLambda = INIT_MAX_LAMBDA;
	   }

	   public HashRandom(Hasher<E> h, Comparator<E> c)// ADD Comparator<E> and Hasher<E> parameters for HW#5!!!!!!!!
	   {
	      this(INIT_TABLE_SIZE,h,c); // FIX THIS (also pass Comparator<E> and Hasher<E>)
	   }
	   
	   public boolean insert( E x)
	   {
	      int bucket = findPos(x);
	      if ( mArray[bucket].state == ACTIVE )
	         return false;
	      //get the bucket value and if it active, add the values
	      mArray[bucket].data = x;
	      mArray[bucket].state = ACTIVE;
	      mSize++;
	      
	      // check load factor
	      if( ++mLoadSize > mMaxLambda * mTableSize )
	      {
	         rehash();
	      }

	      return true;
	   }
	   
	   public boolean remove( E x )
	   {
	      int bucket = findPos(x);
	      //if state for the bucket is not active return false
	      if ( mArray[bucket].state != ACTIVE )
	         return false;
	      //change the state to deleted and decrease count
	      mArray[bucket].state = DELETED;
	      mSize--; // mLoadSize not dec'd because it counts any non-EMP location
	      return true;
	   }
	   
	   public boolean contains(E x ) 
	   {
		   //return the value if the state is active
	      return mArray[findPos(x)].state == ACTIVE;
	   }
	   
	   public int size()  { return mSize; }
	   
	   public void makeEmpty()
	   {
	      int k, size = mArray.length;

	      for(k = 0; k < size; k++)
	         mArray[k].state = EMPTY;
	      mSize = mLoadSize = 0;
	   }
	   
	   public boolean setMaxLambda( double lam )
	   {
	      if (lam < .1 || lam > INIT_MAX_LAMBDA )
	         return false;
	      mMaxLambda = lam;
	      return true;
	   }
	   
	   public void displayStatistics() // NEW WITH HW#5 (you'll call this in main)
	   {
		   System.out.println("\nIn the HashQP object:\n");
		   System.out.println( "Table Size = " +  mTableSize );;
		   System.out.println( "Number of entries = " + mSize);
		   System.out.println( "Load factor = " + (double)mSize/mTableSize);
		   System.out.println( "Number of collisions = " + this.numCollisions );
		   System.out.println( "Longest Collision Run = " + this.longestCollisionRun );
	   }


	   public E getEntry(E target)
	   {
			// FINISH THIS (should be like remove, but  OR  (YOU MAY NEED TO CAST THE RETURN VALUE)
		   int bucket = findPos(target);
		   //return 
		   //when the returned value state is not active- return null- if not found
		      if ( mArray[bucket].state != ACTIVE )
		         return null;

		      else
		      return mArray[bucket].data;
		 //   what the iterator returned if the comparator's compare 
				//   method returns 0
		   
	   }

	   // protected methods of class ----------------------
	   
	   protected int findPos( E x )
	   {
	      int index = myHash(x);
	      int counter = 0;//counter for longestcollisionrun for each iteration
	      Random ranObj = new Random(index);//random object passing hash(x) as seed
	      //if the location is already occupied -loop to get next location
	      while ( mArray[index].state != EMPTY
	         && (comparator.compare(mArray[index].data,x) != 0) ) // CHANGE TO USE Comparator's compare for HW#5!!!!!!!!!!!
	      {
	    	  //get the next interger in the random object and get modulus 
	    	  index = (ranObj.nextInt()) % mTableSize;
	    	  //if the value is more than table size subtract it from table size
	         if ( index >= mTableSize )
	         {
	            index -= mTableSize;
	         }
	         //if the value is less than table size add it to table size
	         while(index < 0)
	         {
	        	 index += mTableSize; 
	         }
	         
	         ++numCollisions; // **************** FOR EX. 8.2 **********************
			 counter++;// ADD HERE: update local counter variable for HW#5!!!
	      }//loop checks if location is empty for storage

		// ADD HERE: maybe update longestCollisionRun variable for HW#5!!!!!!!
	      //if local counter is larger than the longestrun update the longestrun value
	      if(counter > longestCollisionRun)
	      {
	    	  longestCollisionRun = counter;
	      }
	      return index;
	   }
	   
	   protected void rehash()
	   {
		   numCollisions = 0; // **************** FOR EX. 8.2 **********************
		// ADD CODE HERE TO RESET THE HashTable longestCollisionRun TO 0 for HW#5!!!!
		   longestCollisionRun = 0;
	      // we save old list and size then we can reallocate freely
	      HashEntry<E>[] oldArray = mArray;
	      int k, oldTableSize = mTableSize;;

	      mTableSize = nextPrime(2*oldTableSize);
	      
	      // allocate a larger, empty array
	      allocateArray();  // uses mTableSize;

	      // use the insert() algorithm to re-enter old data
	      mSize = mLoadSize = 0;
	      for(k = 0; k < oldTableSize; k++)
	         if (oldArray[k].state == ACTIVE)
	            insert( oldArray[k].data );
	   }
	   
	   protected int getNumCollisions(){ return numCollisions; }// **************** FOR EX. 8.2 **********************
	   
	   protected int myHash(E x)
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
	   
	   void allocateArray()
	   {
	      int k;
	      
	      mArray = new HashEntry[mTableSize];
	      for (k = 0; k < mTableSize; k++)
	         mArray[k] = new HashEntry<E>();
	   }

	   
	   // INNER CLASS: HashEntry, used ONLY internally in HashQP---------------------
	   class HashEntry<E>
	   {
	      public E data;
	      public int state;
	      
	      public HashEntry( E x, int st )
	      {
	         data = x;
	         state = st;
	      }

	      public HashEntry()
	      {
	         this(null, HashRandom.EMPTY);
	      }
	}


	@Override
	public void displayTable() {
		// TODO Auto-generated method stub

		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// DON'T FORGET TO OVERRIDE displayTable() (YOU WRITE FOR HW#5)
		// FOR EACH ARRAY ELEMENT...
//		    Display the index of the array element, THEN 
//		      if the element isn't ACTIVE, display "---",
//		      if the element IS ACTIVE, display the data of the entry (toString)
//		      (see the test runs for examples) 
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//for the whole table
		for(int index = 0;index < mTableSize;index++)
		{
			//display value if state is active else ----
			if(mArray[index].state != ACTIVE)
				System.out.println(index+": ----");
			else
			System.out.println(index+": "+mArray[index].data.toString());
		}
		
	}
}