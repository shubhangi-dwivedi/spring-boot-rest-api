package com.example.spring_boot_rest_api.service;

import com.example.spring_boot_rest_api.model.Tutorial;

import java.util.ArrayList;
import java.util.List;

public class TutorialService {
    static List<Tutorial> tutorials=new ArrayList<>();
    static long id=0;

    public List<Tutorial> findAll(){
        return tutorials;
    }

    public List<Tutorial> findByTitleContaining(String title){
        return tutorials.stream().filter(tutorial -> tutorial.getTitle().contains(title)).toList();
    }
}
