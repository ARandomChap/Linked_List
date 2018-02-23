
/**
 * Ordered Set interface.<br/><br/>
 * 
 * Provides an interface for managing an ordered set of mixed object types. Any object can be placed in the set, but the same object
 * can not appear within the set more than once.<br/><br/>
 * 
 * The ordering of the contents is based on when the element was added to the set. Elements added first appear first within the ordering.
 * 
 * @author mdixon
 */
public interface OrderedSet {

	/**
	 * Adds an element to the ordered set and returns its zero based index position.<br/><br/>
	 * 
	 * If the element is already contained within the set then it is not addaed again and the index of the existing element is returned.
	 * 
	 * @param element the element to be added.
	 * @return the index position of the element within the set.
	 */
	int add(Object element);
		
	/**
	 * Gets the count of the total number of elements in the set.
	 * 
	 * @return the count of the total number of elements in the set.
	 */
	int getCount();
		
	/**
	 * Replaces an element in the set, with the given element. The new element takes the position of the replaced element.
	 * 
	 * If the element already exists within the set then it is repositioned to be located at the position of the replaced element.
	 * 
	 * @param element the element which is to replace the old element.
	 * @param replace the element to be replaced.
	 * @return the index at which the element was replaced, -1 if the element to be replaced could not be found.
	 */
	int replace(Object element, Object replace);
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Checks if an element is present within the set.
	 * 
	 * @param element the element to be checked for presence.
	 * @return true if the given element is within the set, else false.
	 */
	boolean contains(Object element);
		
	/**
	 * Gets the element located at the given index position within the ordered set.
	 * 
	 * @param index the index of the required element.
	 * @return the element at the given index.
	 * @throws IndexOutOfBoundsException if the index is <0 or >= number of elements in the set.
	 */
	Object get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Gets the index of the given element within the ordered set.
	 * 
	 * @param element the element for which the index is required.
	 * @return the index of the element, -1 if the element is not in the ordered set.
	 */
	int indexOf(Object element);
		
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Inserts an element into the ordered set after the specified element.<br/><br/>
	 * 
	 * If the element already exists within the set then it is repositioned to be located after the specified element.
	 * 
	 * @param element the element to be inserted.
	 * @param after the element after which the element is to be inserted.
	 * @return the index position of the inserted element, -1 if the after element could not be found.
	 */
	public int insertAfter(Object element, Object after);
	
	/**
	 * Inserts an element into the  ordered set before the specified element.<br/><br/>
	 * 
	 * If the element already exists within the set then it is repositioned to be located before the specified element.
	 * 
	 * @param element the element to be inserted.
	 * @param before the element before which the element is to be inserted.
	 * @return the index position of the inserted element, -1 if the before element could not be found.
	 */
	public int insertBefore(Object element, Object before);
	
	/**
	 * Inserts an element into the ordered set at a position specified by an index.<br/><br/>
	 * 
	 * If the element already exists within the set then it is repositioned to the given index.
	 * 
	 * @param element the element is to be inserted.
	 * @param index the index at which the element is to be inserted.
	 * @throws IndexOutOfBoundsException if the index is <0 or >number of elements in the set.
	 */
	public void insertAt(Object element, int index) throws IndexOutOfBoundsException;


	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Removes the element located at the given zero based index position from the ordered set.
	 * 
	 * @param index the index of the element to be removed.
	 * @return the element removed from the set.
 	 * @throws IndexOutOfBoundsException if the index is <0 or >= number of elements in the set.
	 */
	Object remove(int index) throws IndexOutOfBoundsException;

	/**
	 * Removes the given element from the ordered set.
	 * 
	 * @param element the element to be removed from the set.
	 * @return true if the element was removed, else false.
	 */
	boolean remove(Object element);
	
	/**
	 * Removes all elements from the set.
	 */
	void removeAll();
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	/** 
	 * Gets a string version of the ordered set in the form -<br/><br/> <code>element1, element2, ... element_n</code>
	 * 
	 * The returned elements must be in the ordering maintained by the set.
	 * 
	 * @return a string version of the set.
	 */
	@Override
	String toString();
	
	/**
	 * Initialises the access cursor position to the front/end of the ordered set.
	 */
	void reset();
	
	/**
	 * Moves the access cursor forward one position, then returns the element at that position.<br/><br/>
	 * 
	 * Once the end of the ordered set has been reached, the subsequent call will wrap the cursor position to the first element in the ordered set.
	 *
	 * @return the next element in the ordered set, null if the end of the ordered set is reached.
	 */
	Object next();
	
	/**
	 * Moves the access cursor backward one position, then returns the element at that position.<br/><br/>
	 * 
	 * Once the start of the ordered set has been reached, the subsequent call will wrap the cursor position to the last element in the ordered set.
	 * 
	 * @return the previous element in the ordered set, null if the start of the ordered set is reached.
	 */
	Object previous();
}
