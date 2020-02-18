package edu.eci.arsw.blueprints.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

@RestController
@RequestMapping(value="/blueprints")
public class BlueprintAPIController {
	@Autowired
    @Qualifier("services")
    BlueprintsServices bpp=null;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> manejadorGetPlueprint(){
		return new ResponseEntity<>(bpp.getAllBlueprints(),HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/{author}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlueprintByAuthor(@PathVariable String author){
		try {
			return new ResponseEntity<>(bpp.getBlueprintsByAuthor(author),HttpStatus.ACCEPTED);
		} catch (BlueprintNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{author}/{bpname}",method=RequestMethod.GET )
	public ResponseEntity<?> getBluePrintByNameAndAuthor(@PathVariable String author,@PathVariable String bpname){
		try {
			Blueprint b = bpp.getBlueprint(author, bpname);
			return new ResponseEntity<>(bpp.getBlueprint(author, bpname),HttpStatus.ACCEPTED);
		} catch (BlueprintNotFoundException e) {
			return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody Blueprint blue){
		try {
			bpp.addNewBlueprint(blue);
			return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
		} catch (BlueprintPersistenceException e) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(value="/{autor}/{nombre}",method=RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable String autor,@PathVariable String nombre ,@RequestBody Blueprint blue){
		System.out.println("b "+blue+" "+blue.getPoints());
		try {
			Blueprint b = bpp.getBlueprint(autor, nombre);
			b.setPoints(blue.getPoints());
			return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
		} catch (BlueprintNotFoundException e) {
			return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
		}
		//return new ResponseEntity(true,HttpStatus.ACCEPTED);
	}
}
