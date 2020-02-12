package edu.eci.arsw.blueprints.filters.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.filters.Filtering;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
@Component("SubsamplingFiltering")
public class SubsamplingFiltering implements Filtering{
	public SubsamplingFiltering() {
		
	}
	@Override
	public Blueprint filter(Blueprint blue) {
		List<Point> points = blue.getPoints();
		Point[] pointsfilter = new Point[points.size()/2+points.size()%2];
		for(int i=0;i<points.size();i++) {
			if(i%2==0) pointsfilter[i/2] = points.get(i); 
		}
		return new Blueprint(blue.getName(),blue.getAuthor(),pointsfilter);
	}

}