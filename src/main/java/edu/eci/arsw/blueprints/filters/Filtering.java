package edu.eci.arsw.blueprints.filters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

public interface Filtering {
	public Blueprint filter(Blueprint blue);
	
	default Set<Blueprint> filterSet(Set<Blueprint> blue){
		Set<Blueprint> set = new HashSet<Blueprint>();
		for(Blueprint b: blue) set.add(filter(b));
		return set;
	} 
}