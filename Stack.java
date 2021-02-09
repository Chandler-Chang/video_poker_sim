/* CSE205: 11333/T, Th 4:30-5:45pm
 * Assignment: 6, Final
 * Author: Chandler Chang (1216655869)
 * Description: Stack interface for Assignment 6
 */

/**
A stack is a last in first out (LIFO) data structure.
New items are pushed onto the top of the list.
Items are popped from the top of the list.
*/
public interface Stack {
	/**
	   Pushes an item onto the top of this stack.
	*/
	public void push(Object item);

	/**
		 Removes the object at the top of this stack and returns that object as the value of this function.
		 @return the item at the top of the stack or throws a NoSuchElementException is the stack is empty
	*/
	public Object pop();
}
