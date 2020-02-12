package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
/**
 *
 * @author hcadavid
 */
@Component("InMemoryBlueprintPersistence")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        Blueprint bp1=new Blueprint("Juan", "diaz",new Point[] {new Point(4,3),new Point(5,7),new Point(6,3)});
        Blueprint bp2=new Blueprint("Juan", "diaz",new Point[] {new Point(4,3),new Point(9,7),new Point(2,1)});
        Blueprint bp3=new Blueprint("David", "Ramirez",new Point[] {new Point(4,8),new Point(6,2),new Point(6,3)});
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

	@Override
	public Set<Blueprint> getBluePrintsByAutor(String author) {
		Set<Blueprint> s = new HashSet<>();
		for(Blueprint b: blueprints.values()) {
			if(b.getAuthor().equals(author)) s.add(b);
		}
		return s;
	}

	@Override
	public Set<Blueprint> getAllBlueprints() {
		Set<Blueprint> b = new HashSet<>();
		for(Blueprint bl:blueprints.values()) b.add(bl);
		return b;
	}

    
    
}