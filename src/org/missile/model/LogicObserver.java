package org.missile.model;

import java.util.List;

import org.missile.view.Drawable;

public interface LogicObserver {
	public void newElement(Drawable d);
	public void removeElement(Drawable d);
	public void removeElementCollection(List<Drawable> list);
}
