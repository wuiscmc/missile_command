package org.missile.model;

import java.util.List;

import org.missile.view.Drawable;

/**
 * Interface that is implemented by all those classes which want to receive
 * updates from the Logic class.
 * <p>
 * The purpose of this interface is to decouple as much as possible the view
 * layer and the model business layer. In this case, this representation follows
 * the <a href="http://en.wikipedia.org/wiki/Observer_pattern">Observer design
 * pattern</a>.
 * 
 * @author Luis Carlos Mateos
 * 
 */
public interface LogicObserver {
	/**
	 * Notifies the observers about a new element has been added.
	 * 
	 * @param d
	 *            {@link Drawable}Êreference to the element added.
	 */
	public void newElement(Drawable d);

	/**
	 * Notifies the observers about an element has been removed from the system.
	 * 
	 * @param d
	 *            {@link Drawable}Êreference to the removed element.
	 */
	public void removeElement(Drawable d);
	
	/**
	 * Notifies the observers about a set of elements has been removed.
	 * 
	 * @param list List of {@link Drawable}Êreference to the removed set.
	 */
	public void removeElementCollection(List<Drawable> list);
}
