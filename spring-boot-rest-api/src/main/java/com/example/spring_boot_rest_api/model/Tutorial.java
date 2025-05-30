package com.example.spring_boot_rest_api.model;

public class Tutorial {
    private long id=0;
    private String title;
    private String description;
    private boolean published;

    public Tutorial(){

    }

    public Tutorial( String title, String description, boolean published){
        this.title=title;
        this.description=description;
        this.published=published;
    }

    public void setId(long id){
        this.id=id;
    }

    public long getId(){
        return id;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getDescription(){
        return description;
    }

    public void setPublished(boolean published){
        this.published=published;
    }

    public boolean isPublished(){
        return published;
    }

    @Override
    public String toString(){
        return "Tutorial [id="+id+", title="+title+", description="+description+", published="+published+"]";
    }

}
