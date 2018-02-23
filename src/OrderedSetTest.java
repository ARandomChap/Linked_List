import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * JUnit test cases to be used to check implementation to the OrderedSet interface.
 * 
 * Use the @Ignore annotations to ignore a test during development.
 * 
 * @author mdixon
 */
public class OrderedSetTest {

	private final static String obj1 = "obj1";
	private final static String obj2 = "obj2";
	private final static String obj3 = "obj3";
	private final static String obj4 = "obj4";
	private final static String obj5 = "obj5";

	private OrderedSet orderedSet;

	@Before
	public void init() {
		orderedSet = new OrderedLinkedSetSolution();
	}

	@Ignore
	@Test
	public void exampleOfIgnoredTest() {

		fail("This test is ignored because of @Ignore annotation");
	}

	////////////////////////////////////////////////////////////////////////////////////////
	// CATEGORY: Adding and Counting.

	//@Ignore
	@Test
	public void shouldAddAndCount() {

		// CATEGORY: Adding and Counting.

		assertEquals("Incorrect index returned", 0, orderedSet.add(obj1));
		assertEquals("Incorrect count returned", 1, orderedSet.getCount());

		assertEquals("Incorrect index returned", 1, orderedSet.add(obj2));
		assertEquals("Incorrect count returned", 2, orderedSet.getCount());

		assertEquals("Incorrect index returned", 2, orderedSet.add(obj3));
		assertEquals("Incorrect count returned", 3, orderedSet.getCount());

		// attempt to add duplicate, which should not reinsert and return existing index position
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 1, orderedSet.add(obj2));
		assertEquals("Incorrect count returned", 3, orderedSet.getCount());

		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj3));
		assertEquals("Incorrect count returned", 3, orderedSet.getCount());
	}

	//@Ignore
	@Test
	public void shouldReplace() {

		// CATEGORY: Adding and Counting.

		orderedSet.add(obj1);
		orderedSet.add(obj2);
		orderedSet.add(obj2);
		orderedSet.add(obj3);

		// try to replace object which is not present
		assertEquals("Incorrect index returned", -1, orderedSet.replace(obj5, obj4));

		// replace occurrence of obj2
		assertEquals("Incorrect index returned", 1, orderedSet.replace(obj5, obj2));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj5));
		assertEquals("Incorrect index returned", -1, orderedSet.indexOf(obj2));

		// replace first element
		assertEquals("Incorrect index returned", 0, orderedSet.replace(obj2, obj1));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", -1, orderedSet.indexOf(obj1));

		// replace last element
		assertEquals("Incorrect index returned", 2, orderedSet.replace(obj4, obj3));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj4));
		assertEquals("Incorrect index returned", -1, orderedSet.indexOf(obj3));

		// replace object with itself
		assertEquals("Incorrect index returned", 2, orderedSet.replace(obj4, obj4));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj4));

		// replace element with an elements already located within the set to ensure it moves index and reduces count.
		assertEquals("Incorrect index returned", 0, orderedSet.replace(obj4, obj2));
		assertEquals("Incorrect count returned", 2, orderedSet.getCount());

	}

	////////////////////////////////////////////////////////////////////////////////////////
	// CATEGORY: Finding.

	//@Ignore
	@Test
	public void shouldCheckContains() {

		// CATEGORY: Finding.

		assertFalse(orderedSet.contains(obj1));
		assertFalse(orderedSet.contains(obj2));
		assertFalse(orderedSet.contains(obj3));

		orderedSet.add(obj1);
		assertEquals("Incorrect count returned", 1, orderedSet.getCount());

		assertTrue(orderedSet.contains(obj1));
		assertFalse(orderedSet.contains(obj2));
		assertFalse(orderedSet.contains(obj3));

		orderedSet.add(obj2);
		assertEquals("Incorrect count returned", 2, orderedSet.getCount());

		assertTrue(orderedSet.contains(obj1));
		assertTrue(orderedSet.contains(obj2));
		assertFalse(orderedSet.contains(obj3));

		orderedSet.add(obj3);
		assertEquals("Incorrect count returned", 3, orderedSet.getCount());

		assertTrue(orderedSet.contains(obj1));
		assertTrue(orderedSet.contains(obj2));
		assertTrue(orderedSet.contains(obj3));
	}

	//@Ignore
	@Test
	public void shouldGetIndexedElement() {

		// CATEGORY: Finding.

		orderedSet.add(obj1);
		assertSame("Incorrect element returned", obj1, orderedSet.get(0));

		orderedSet.add(obj2);
		assertSame("Incorrect element returned", obj1, orderedSet.get(0));
		assertSame("Incorrect element returned", obj2, orderedSet.get(1));

		orderedSet.add(obj3);
		assertSame("Incorrect element returned", obj1, orderedSet.get(0));
		assertSame("Incorrect element returned", obj2, orderedSet.get(1));
		assertSame("Incorrect element returned", obj3, orderedSet.get(2));

		orderedSet.add(obj2);
		assertSame("Incorrect element returned", obj1, orderedSet.get(0));
		assertSame("Incorrect element returned", obj2, orderedSet.get(1));
		assertSame("Incorrect element returned", obj3, orderedSet.get(2));

	}

	//@Ignore
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetMinusIndex() {

		// CATEGORY: Finding.
		orderedSet.get(-1);
	}

	//@Ignore
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetHighIndex() {

		// CATEGORY: Finding.
		orderedSet.get(0);
	}

	//@Ignore
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetHighIndex2() {

		// CATEGORY: Finding.
		orderedSet.add(obj1);
		orderedSet.add(obj2);
		orderedSet.get(2);
	}

	//@Ignore
	@Test
	public void shouldGetIndexOf() {

		// CATEGORY: Finding.

		assertEquals("Incorrect index returned", -1, orderedSet.indexOf(obj1));
		orderedSet.add(obj1);
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj1));

		orderedSet.add(obj2);
		orderedSet.add(obj3);
		orderedSet.add(obj3);
		orderedSet.add(obj1);
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj3));


		// check for non-existent object
		assertEquals("Incorrect index returned", -1, orderedSet.indexOf(obj4));
	}

	////////////////////////////////////////////////////////////////////////////////////////
	// CATEGORY: Removal.

	//@Ignore
	@Test
	public void shouldRemoveAll() {

		// CATEGORY: Removal.

		assertEquals("Incorrect count returned", 0, orderedSet.getCount());
		orderedSet.removeAll();
		assertEquals("Incorrect count returned", 0, orderedSet.getCount());

		assertEquals("Incorrect index returned", 0, orderedSet.add(obj1));
		assertEquals("Incorrect count returned", 1, orderedSet.getCount());
		orderedSet.removeAll();
		assertEquals("Incorrect count returned", 0, orderedSet.getCount());

		orderedSet.add(obj1);
		orderedSet.add(obj2);
		orderedSet.add(obj3);
		orderedSet.add(obj3);
		orderedSet.add(obj4);
		assertEquals("Incorrect count returned", 4, orderedSet.getCount());

		orderedSet.removeAll();
		assertEquals("Incorrect count returned", 0, orderedSet.getCount());
	}

	//@Ignore
	@Test
	public void shouldRemoveAt() {

		// CATEGORY: Removal.

		orderedSet.add(obj1);
		assertSame("Incorrect element removed", obj1, orderedSet.remove(0));
		assertEquals("Incorrect count returned", 0, orderedSet.getCount());

		orderedSet.add(obj1);
		orderedSet.add(obj2);
		assertSame("Incorrect element removed", obj1, orderedSet.remove(0));
		assertEquals("Incorrect count returned", 1, orderedSet.getCount());
		assertSame("Incorrect element removed", obj2, orderedSet.remove(0));
		assertEquals("Incorrect count returned", 0, orderedSet.getCount());

		orderedSet.add(obj1);
		orderedSet.add(obj2);
		assertSame("Incorrect element removed", obj2, orderedSet.remove(1));
		assertEquals("Incorrect count returned", 1, orderedSet.getCount());
		assertSame("Incorrect element removed", obj1, orderedSet.remove(0));

		orderedSet.add(obj1);
		orderedSet.add(obj2);
		orderedSet.add(obj3);
		assertSame("Incorrect element removed", obj2, orderedSet.remove(1));
		assertEquals("Incorrect count returned", 2, orderedSet.getCount());
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj3));

		orderedSet.removeAll();
		orderedSet.add(obj1);
		orderedSet.add(obj2);
		orderedSet.add(obj1);
		assertSame("Incorrect element removed", obj1, orderedSet.remove(0));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", -1, orderedSet.indexOf(obj1));

		orderedSet.removeAll();
		orderedSet.add(obj1);
		orderedSet.add(obj2);
		orderedSet.add(obj3);
		orderedSet.add(obj4);
		orderedSet.add(obj5);
		assertSame("Incorrect element removed", obj5, orderedSet.remove(4));
		assertEquals("Incorrect count returned", 4, orderedSet.getCount());
		assertEquals("Incorrect index returned", -1, orderedSet.indexOf(obj5));
	}

	//@Ignore
	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldRemoveAtMinusIndex() {

		// CATEGORY: Removal.

		orderedSet.remove(-1);
	}

	//@Ignore
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveAtHighIndex() {

		// CATEGORY: Removal.

		orderedSet.remove(0);
	}

	//@Ignore
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveAtHighIndex2() {

		// CATEGORY: Removal.

		orderedSet.add(obj1);
		orderedSet.add(obj2);
		orderedSet.remove(2);
	}

	//@Ignore
	@Test
	public void shouldRemoveElement() {

		// CATEGORY: Removal.

		assertFalse(orderedSet.remove(obj1));
		assertFalse(orderedSet.remove(obj2));
		assertFalse(orderedSet.remove(obj3));

		orderedSet.add(obj1);
		assertTrue(orderedSet.remove(obj1));
		assertFalse(orderedSet.remove(obj2));
		assertFalse(orderedSet.remove(obj3));

		assertEquals("Incorrect count returned", 0, orderedSet.getCount());

		orderedSet.add(obj1);
		orderedSet.add(obj2);
		orderedSet.add(obj1);
		orderedSet.add(obj3);
		orderedSet.add(obj1);
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj1));
		assertTrue(orderedSet.remove(obj1));
		assertEquals("Incorrect index returned", -1, orderedSet.indexOf(obj1));
		assertFalse(orderedSet.remove(obj1));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj2));
		assertEquals("Incorrect count returned", 2, orderedSet.getCount());

		assertTrue(orderedSet.remove(obj2));
		assertTrue(orderedSet.remove(obj3));

		orderedSet.add(obj1);
		orderedSet.add(obj2);
		orderedSet.add(obj3);
		orderedSet.add(obj4);
		orderedSet.add(obj5);

		assertTrue(orderedSet.remove(obj5));
		assertEquals("Incorrect count returned", 4, orderedSet.getCount());
		assertEquals("Incorrect index returned", 3, orderedSet.indexOf(obj4));
		assertEquals("Incorrect index returned", -1, orderedSet.indexOf(obj5));

		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj2));
		assertTrue(orderedSet.remove(obj3));
		assertFalse(orderedSet.remove(obj3));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj4));
	}

	////////////////////////////////////////////////////////////////////////////////////////
	// CATEGORY: Inserting.

	//@Ignore
	@Test
	public void shouldInsertAt() {

		// CATEGORY: Inserting.

		orderedSet.insertAt(obj1, 0);
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj1));

		orderedSet.insertAt(obj2, 0);
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj1));

		orderedSet.insertAt(obj3, 0);
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj3));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj1));

		orderedSet.insertAt(obj4, 3);
		assertEquals("Incorrect index returned", 3, orderedSet.indexOf(obj4));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj3));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj1));

		orderedSet.insertAt(obj5, 1);
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj5));
		assertEquals("Incorrect index returned", 4, orderedSet.indexOf(obj4));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj3));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 3, orderedSet.indexOf(obj1));

		// inserting when already exists

		orderedSet.insertAt(obj5, 1); // insert over own position: bug fix - should not change anything
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj5));
		assertEquals("Incorrect index returned", 4, orderedSet.indexOf(obj4));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj3));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 3, orderedSet.indexOf(obj1));

		// insert existing element in new index (after previous position).
		orderedSet.insertAt(obj5, 3);
		assertEquals("Incorrect index returned", 3, orderedSet.indexOf(obj5));
		assertEquals("Incorrect index returned", 4, orderedSet.indexOf(obj4));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj3));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj1));

		// insert existing element in new index (before previous position).
		orderedSet.insertAt(obj5, 0);
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj5));
		assertEquals("Incorrect index returned", 4, orderedSet.indexOf(obj4));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj3));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 3, orderedSet.indexOf(obj1));

		// insert existing element in new index (at very end).
		orderedSet.insertAt(obj5, 4);
		assertEquals("Incorrect index returned", 4, orderedSet.indexOf(obj5));
		assertEquals("Incorrect index returned", 3, orderedSet.indexOf(obj4));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj3));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj1));
	}

	//@Ignore
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsertAtMinusIndex() {

		// CATEGORY: Inserting.
		orderedSet.insertAt(obj1, -1);
	}

	//@Ignore
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsertAtHighIndex() {

		// CATEGORY: Inserting.
		orderedSet.insertAt(obj1, 1);
	}

	//@Ignore
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsertAtHighIndex2() {

		// CATEGORY: Inserting.
		orderedSet.add(obj1);
		orderedSet.add(obj2);
		orderedSet.insertAt(obj3, 3);
	}

	//@Ignore
	@Test
	public void shouldInsertBefore() {

		// CATEGORY: Inserting.
		orderedSet.add(obj2);
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj2));

		assertEquals("Incorrect index returned", 0, orderedSet.insertBefore(obj1, obj2));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj2));

		assertEquals("Incorrect index returned", 1, orderedSet.insertBefore(obj3, obj2));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj3));

		assertEquals("Incorrect index returned", 0, orderedSet.insertBefore(obj3, obj1));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj3));

		assertEquals("Incorrect index returned", 1, orderedSet.insertBefore(obj4, obj1));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 3, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj3));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj4));

		// check inserting before self has no effect on the index.
		assertEquals("Incorrect index returned", 2, orderedSet.insertBefore(obj1, obj1));
		assertEquals("Incorrect index returned", 0, orderedSet.insertBefore(obj3, obj3));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 3, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj3));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj4));

		// check unable to insert before non-existent element.
		assertFalse(orderedSet.contains(obj5));
		assertEquals("Incorrect count returned", 4, orderedSet.getCount());
		assertEquals("Incorrect index returned", -1, orderedSet.insertBefore(obj1, obj5));
		assertEquals("Incorrect count returned", 4, orderedSet.getCount());
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj1));
	}

	//@Ignore
	@Test
	public void shouldInsertAfter() {

		// CATEGORY: Inserting.
		orderedSet.add(obj2);
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj2));

		assertEquals("Incorrect index returned", 1, orderedSet.insertAfter(obj1, obj2));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj2));

		assertEquals("Incorrect index returned", 1, orderedSet.insertAfter(obj3, obj2));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj3));

		assertEquals("Incorrect index returned", 1, orderedSet.insertAfter(obj1, obj2));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 2, orderedSet.indexOf(obj3));

		assertEquals("Incorrect index returned", 2, orderedSet.insertAfter(obj4, obj1));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 3, orderedSet.indexOf(obj3));

		// check inserting after self has no effect on the index.
		assertEquals("Incorrect index returned", 1, orderedSet.insertAfter(obj1, obj1));
		assertEquals("Incorrect index returned", 3, orderedSet.insertAfter(obj3, obj3));
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj1));
		assertEquals("Incorrect index returned", 0, orderedSet.indexOf(obj2));
		assertEquals("Incorrect index returned", 3, orderedSet.indexOf(obj3));

		// check unable to insert after non-existent element.
		assertFalse(orderedSet.contains(obj5));
		assertEquals("Incorrect count returned", 4, orderedSet.getCount());
		assertEquals("Incorrect index returned", -1, orderedSet.insertAfter(obj1, obj5));
		assertEquals("Incorrect count returned", 4, orderedSet.getCount());
		assertEquals("Incorrect index returned", 1, orderedSet.indexOf(obj1));
	}

	////////////////////////////////////////////////////////////////////////////////////////
	// CATEGORY: Iterating + displaying.

	//@Ignore
	@Test
	public void shouldDoToString() {

		// CATEGORY: Iterating + displaying.

		assertEquals("Incorrect string returned", "", orderedSet.toString());

		orderedSet.add(obj1);
		assertEquals("Incorrect string returned", obj1, orderedSet.toString());

		orderedSet.add(obj2);
		assertEquals("Incorrect string returned", obj1 + ", " + obj2, orderedSet.toString());

		orderedSet.add(obj3);
		assertEquals("Incorrect string returned", obj1 + ", " + obj2 + ", " + obj3, orderedSet.toString());

		orderedSet.add(obj3);
		assertEquals("Incorrect string returned", obj1 + ", " + obj2 + ", " + obj3, orderedSet.toString());

		orderedSet.insertAt(obj4, 1);
		assertEquals("Incorrect string returned", obj1 + ", " + obj4 + ", " + obj2 + ", " + obj3, orderedSet.toString());

		assertEquals("Incorrect index returned", 1, orderedSet.replace(obj5, obj4));
		assertEquals("Incorrect string returned", obj1 + ", " + obj5 + ", " + obj2 + ", " + obj3, orderedSet.toString());

	}

	//@Ignore
	@Test
	public void shouldGetNext() {

		// CATEGORY: Iterating + displaying.
		orderedSet.reset();

		// ensure null returned when set empty.
		assertNull(orderedSet.next());

		// Get one element from the set
		orderedSet.add(obj1);
		orderedSet.reset();
		assertSame(obj1, orderedSet.next());
		assertNull(orderedSet.next());

		// Get several elements from set list
		orderedSet.add(obj2);
		orderedSet.add(obj3);
		orderedSet.reset();
		assertSame(obj1, orderedSet.next());
		assertSame(obj2, orderedSet.next());
		assertSame(obj3, orderedSet.next());
		assertNull(orderedSet.next());
	}

	//@Ignore
	@Test
	public void shouldGetPrev() {

		// CATEGORY: Iterating + displaying.
		orderedSet.reset();

		// ensure null returned when set empty.
		assertNull(orderedSet.previous());

		// Get one element from the set
		orderedSet.add(obj1);
		orderedSet.reset();
		assertSame(obj1, orderedSet.previous());
		assertNull(orderedSet.previous());

		// Get several elements from the set
		orderedSet.add(obj2);
		orderedSet.add(obj3);
		orderedSet.reset();
		assertSame(obj3, orderedSet.previous());
		assertSame(obj2, orderedSet.previous());
		assertSame(obj1, orderedSet.previous());
		assertNull(orderedSet.previous());
	}

	//@Ignore
	@Test
	public void shouldGetNextAndPrev() {

		// CATEGORY: Iterating + displaying.
		orderedSet.reset();
		assertNull(orderedSet.next());
		assertNull(orderedSet.previous());

		orderedSet.add(obj1);
		assertSame(obj1, orderedSet.next());
		assertNull(obj1, orderedSet.previous());

		assertSame(obj1, orderedSet.previous());
		assertNull(orderedSet.next());

		orderedSet.reset();
		orderedSet.add(obj2);
		orderedSet.add(obj3);
		assertSame(obj1, orderedSet.next());
		assertSame(obj2, orderedSet.next());
		assertSame(obj1, orderedSet.previous());
		assertSame(obj2, orderedSet.next());
		assertSame(obj3, orderedSet.next());
		assertSame(obj2, orderedSet.previous());
		assertSame(obj3, orderedSet.next());
		assertNull(orderedSet.next());

		// check forward wrap
		assertSame(obj1, orderedSet.next());
		assertNull(orderedSet.previous());

		// check backward wrap
		assertSame(obj3, orderedSet.previous());
		assertNull(orderedSet.next());
	}

	//@Ignore
	@Test
	public void shouldGetNextAndPrevWithRemove() {
		// Test removing elements during iteration

		// CATEGORY: Iterating + displaying.
		orderedSet.add(obj1);
		orderedSet.add(obj2);
		orderedSet.add(obj3);
		orderedSet.add(obj4);
		orderedSet.add(obj5);

		orderedSet.reset();
		orderedSet.remove(0);
		assertSame(obj2, orderedSet.next());

		orderedSet.remove(obj3);
		assertSame(obj4, orderedSet.next());

		orderedSet.remove(obj4);
		assertSame(obj5, orderedSet.next());

		assertNull(orderedSet.next());
		assertSame(obj2, orderedSet.next());

		orderedSet.removeAll();
		orderedSet.add(obj1);
		orderedSet.add(obj2);
		orderedSet.add(obj3);
		orderedSet.add(obj4);
		orderedSet.add(obj5);

		orderedSet.reset();
		orderedSet.remove(4);
		assertSame(obj4, orderedSet.previous());

		orderedSet.remove(obj4);
		assertSame(obj3, orderedSet.previous());
		orderedSet.remove(obj2);
		assertSame(obj1, orderedSet.previous());
	}
}
