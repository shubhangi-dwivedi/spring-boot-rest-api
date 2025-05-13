package com.example.spring_boot_rest_api.controller;
import java.util.ArrayList;
import java.util.List;
import com.example.spring_boot_rest_api.model.Tutorial;
import com.example.spring_boot_rest_api.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title){
        try{
            List<Tutorial> tutorials=new ArrayList<>();

            if(title==null)
                tutorialService.findAll().forEach(tutorials::add);
            else
                tutorialService.findByTitleContaining(title).forEach(tutorials::add);

            if(tutorials.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id){
        Tutorial tutorial= tutorialService.findById(id);

        if(tutorial!=null){
            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(tutorial, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial){
        try{
            Tutorial _tutorial=tutorialService.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));

            return new ResponseEntity<>(tutorial, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial){
        Tutorial _tutorial=tutorialService.findById(id);

        if(_tutorial!=null){
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());

            return new ResponseEntity<>(tutorialService.save(_tutorial), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> deleteTutorial(@PathVariable("id") long id){
        try {
            tutorialService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<Tutorial> deleteAllTutorials(){
        try {
            tutorialService.deleteAll();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished(){
        try {
            List<Tutorial> tutorials=tutorialService.findByPublished(true);

            if (tutorials.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
