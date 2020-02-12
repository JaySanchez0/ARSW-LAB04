package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filters.Filtering;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
   
    @Autowired
    @Qualifier("InMemoryBlueprintPersistence")
    BlueprintsPersistence bpp=null;
    @Autowired
    @Qualifier("SubsamplingFiltering")
    private Filtering filter;
    
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException{
        bpp.saveBlueprint(bp);
    }
    
    public Set<Blueprint> getAllBlueprints(){
        return bpp.getAllBlueprints();
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        return bpp.getBlueprint(author, name);
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        return bpp.getBluePrintsByAutor(author);
    }
    /**
     * 
     * @param author - the Blueprint Author name
     * @param name - The blueprint name
     * @return give a Blueprint with the filter apply
     * @throws BlueprintNotFoundException - if don't exist the Blueprint
     */
    public Blueprint getFilterBlueprint(String author,String name) throws BlueprintNotFoundException {
    	return filter.filter(getBlueprint(author,name));
    }
    /**
     * @return give a Set of All Blueprints with the filter Apply
     */
    public Set<Blueprint> getAllBlueprintFilering(){
    	return filter.filterSet(getAllBlueprints());
    }
    
}