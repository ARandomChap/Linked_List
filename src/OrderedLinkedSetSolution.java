/**
 * OrderedLinkedSet class. Provides an implementation of the {@link OrderedSet} interface.
 * 
 * Implements a double linked list to maintain ordering within a set of elements.
 * 
 * @author c3451748
 */

public class OrderedLinkedSetSolution implements OrderedSet {
	
	SetElement first;		// This is the first element in the Linked List, it is almost always used as a start point for entering and navigating the list.
	SetElement last;		// This is the last element in the Linked List, it can also be used as a entry point.
	SetElement pos;			// This is the Access Cursor within the list (The current position).
	SetElement prevEl;		// This stores the previous element that the list what last on, makes it easier to manipulate and return values to the remove/insert methods.
	int count = 0;			// Count is the amount of elements in the list at a given time.

	@Override
	public int add(Object element) {
		//Use indexOf to check whether element is already in the set.  If so, don't add it!
		if(this.indexOf(element) >= 0){
			return indexOf(element);	
		}
		//make the above check before, doesn't waste memory if the SetEl is already in list.
		SetElement newEl = new SetElement(element);	
		
		//Deal with case where the set is empty
		if(first == null){
			//Make newEl the first and last element of the set (it will be the only one in the set)
			first = newEl;
			last = newEl;
			
		} else {
			//Make newEl the last element of the set, after the existing last element
			newEl.previous = last;
			last.next = newEl;
			last = newEl;
		}
		return count++;
	}
	
	@Override
	public int getCount() {
		return count;
	}

	@Override
	public int replace(Object element, Object replace) {
		SetElement testEl = this.first;
		
		// uses other methods to check whether the element is already in the list and remove
		// it if it is. Managed to save lines of code here by reusing the contains and remove methods.
		if(contains(element) && replace != element){
			remove(element);
		}
				
		for(int i = 0; i < count; i++){	
			if(replace == testEl.element){
				testEl.element = element;	//simpler to replace the element within the SetElement rather than insert it into list.
				return i;
			} 
			else{ testEl = testEl.next; }
		}
		return -1;
	}

	@Override
	public boolean contains(Object element) {
		// uses indexOf the check if element is within list, returns it if != to -1.
		// the index values assert to true of false based on if negative or positive.
		return indexOf(element) != -1;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		// check for Index out of bounds, throw exception if true.
		if( index < 0 || index >= count){
			throw new IndexOutOfBoundsException();
		}
		
		reset();	//asserts position to null.
		
		for(int i = 0; i < count; i++){	
			//reuse other methods to test if index of element == to given index. Don't have to rewrite code, more efficient. 
			if(this.indexOf(next()) == index){
				return pos.element;
			}
		}
		return index;
	}

	@Override
	public int indexOf(Object element) {
		SetElement testEl = first;
		
		//loop through to test is element is present at position.
		for(int i= 0; i < count; i ++){
			if(testEl.element == element){
				return i;
			}
			// proceeds to next element if not.
			else{ testEl = testEl.next; }
		}
		return -1;
	}

	@Override
	public int insertAfter(Object element, Object after) {
		
		//test to see if element to insert == to the element after, makes no changes, returns index of element. 
		if(indexOf(element) == indexOf(after)){
			return indexOf(element);
		}
		// if doesn't contain after, returns -1;
		// could return idexOf(after), however means going through list twice.
		else if (!contains(after)){
			return -1;
		}
		
		//uses insetAt method to add the element to list (efficiency), index offset by +1 so it's after.
		insertAt(element, indexOf(after)+1);

		return indexOf(element);
	}
	
	@Override
	public int insertBefore(Object element, Object before) {
		
		//test to see if element to insert == to the element before, makes no changes, returns index of element. 
		if(indexOf(element) == indexOf(before)){
			return indexOf(element);
		}
		// if doesn't contain after, returns -1;
		else if (!contains(before)){
			return -1;
		}
		
		//uses insetAt method to add the element to list (efficiency).
		insertAt(element, indexOf(before));
				
		return indexOf(element);
	}

	@Override
	public void insertAt(Object element, int index) throws IndexOutOfBoundsException {
		//test to see if element to insert == to the element before, makes no changes, returns index of element. 
		if( index < 0 || index > count){
			throw new IndexOutOfBoundsException();
		}
		
		SetElement theEl = new SetElement(element);
		SetElement testEl = first;
		
		// checks to see if the element is within the list. if not proceed to add.
		if(contains(theEl.element)){	
			for(int c = 0 ; c < count; c++){
				//checks to see if element is already in list, and if the element is at the same position, if so return and make no changes.
				if(theEl.element == testEl.element && indexOf(theEl.element) == index){
					return;
				}				
				testEl = testEl.next;
			}
			// reuses remove method to remove the element
			remove(theEl.element);
		}
		
		testEl = first;
		
		// if nothing in list just set theEl as the first and last.
		if(first == null && last == null){
			last = theEl;
			first = theEl;
			count++;
			return;
		}
		
		for(int i = 0; i < count; i++){
			// compensates for if insert is after last position.
			if (index == count){
				last.next = theEl;
				last.next.previous = last;
				last = last.next;
				count++;
				return;
			}	
			// if the index of the current element == index, then proceed to insert.
			if(indexOf(testEl.element) == index){
				theEl.next = testEl;
				theEl.previous = testEl.previous;
				testEl.previous = theEl;
				testEl = testEl.previous;
				if(testEl.previous != null){
					testEl.previous.next = testEl;
				}
				count++;	
			}
			
			// Test to make sure the first and last elements are corrent and have the correct links.
			if(testEl.next == null){
				last = testEl;
				}
			
			if(testEl.previous == null){
				first = testEl;
				}
			
			testEl = testEl.next;
		}
	return;
}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
	SetElement testEl = null;
	
	// if index out of bounds throw exception.
	if( index < 0 || index >= count){
		throw new IndexOutOfBoundsException();
	}

	// will the SetElement to be manipulated is null, search through list for element == index and set testEl to that position
	while(testEl == null){
		if(indexOf(next()) == index){
			testEl = pos;
		}
	}
	
	prevEl = testEl;				
				
		for(int i = 0; i < count; i++){
			// if the index of the current element == index, then proceed to remove.
			if(indexOf(testEl.element) == index){
				// need individual test for the first and last element, as they need to be dealt with differently to rest of list.
				if(testEl.next == null){
					last = testEl.previous;
					}
					
				if(testEl.previous == null){
					first = testEl.next;
					}
					
				// make sure its isn't last in list, if so proceed to remove the links.
				if(testEl.next != null){
					testEl = testEl.next;	
					testEl.previous = testEl.previous.previous;					
					}
					
				// also make sure that is isn't the first.
				if(testEl.previous != null){
					testEl = testEl.previous;						
					testEl.next = testEl.next.next;
					}

					count--;
					return prevEl.element;
					}
				}	
		return null;
	}

	@Override
	public boolean remove(Object element) {		
	// check to see if element is in list, if not - false
	if(!contains(element)){
		return false;
	}
	// uses indexOf to find index of the given element in list, passes index to remove(int) method
	// saves re-writing code similar to that in the remove(int) method.
	remove(indexOf(element));																		////////////////// does this actually work.
	return true;
	}

	@Override
	public void removeAll() {
		// sets first & last to null, which will remove all of the links in-between as well.
		first = null;
		last = null;
		count = 0;	
	}

	@Override
	public void reset() {
		//asserts position in the list to null, this way the other methods can decide what position to start at.
		pos = null;
	}

	@Override
	public Object next() {
	// test to see if there is anything in the list, return null if not.
		while(first != null){
			// if pos is null start at first. Use case when the end of the list has been reached and resets to null.
			if(pos == null){
				prevEl = pos;
				pos = first;
				return pos.element;
			}
			// move to next link
			pos = pos.next;
			
			//test to see if the pos == null, if so assert Null.
			if(pos == null){
				return null;
			}
		// return current position if none of other tests are met.
		return pos.element;
		}	
	  return null;
	}

	@Override
	public Object previous() {
	// test to see if there is anything in the list, return null if not.
		while(last != null){
			// if pos is null start at last. Use case when the start of the list has been reached and resets to null.
			if(pos == null){
				prevEl = pos;
				pos = last;
				return pos.element;
			}
			// move to prev link
			pos = pos.previous;

			//test to see if the pos == null, if so assert Null.
			if(pos == null){
				return null;
			}
		// return current position if none of other tests are met.
		return pos.element;
		}
	  return null;
	}

	public String toString() {
		SetElement testEl = first;
		prevEl = testEl;
		// initial value of the string
		String result = "";
		
		// loop through list with next until it reaches the end.
		while(next() != null){
			// tests if pos is first, displays only that with no punctuation. 
			if(pos == first){
				result += pos.element;
			}
			// else prints the element with the punctuation required in the tests.
			else{
				testEl = testEl.next;
				result += ", " +pos.element;
			}
		}
		return result;
	}
}