package edu.eci.arsw.blueprints.model;

/**
 *
 * @author hcadavid
 */
public class Point {
   
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }    
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int hashCode() {
    	return 1;
    }
    public boolean equals(Object o) {
    	if(!(o instanceof Point)) return false;
    	Point p = (Point) o;
    	return p.getX()==x && p.getY()==y;
    }
    
    public String toString() {
    	return "("+x+", "+y+")";
    }
    
    
    
}