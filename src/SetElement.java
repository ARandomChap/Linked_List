
/**
 * SetElement implementation class.
 * 
 * Represents an element within the ordered set.
 * 
 * @author mdixon
 */
public class SetElement {

	/**
	 * The previous {@link SetElement} within the ordered set.
	 */
	SetElement previous = null;
	
	/**
	 * The next {@link SetElement} within the ordered set.
	 */
	SetElement next = null;
	
	/**
	 * The actual element contained within this ordered set element.
	 */
	Object element;
	
	
	//////////////////////////////////////////////////////////////////////
	
	/**
	 * @param element the element contained within this ordered set element.
	 */
	SetElement(Object element) {
		
		this.element = element;
	}
}
