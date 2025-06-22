/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nitan
 */
public class Movie {
          private int id;
    private String title;
    private String genre;
    private int duration;
    private String poster_path; 
    
    public Movie() {}
    
    public Movie(int id, String title, String genre, int duration, String poster_path) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.poster_path = poster_path;

     }

    public int getId() {
        return id;
    }
    public void setId(int id) {
    this.id =id;
    }
    

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
    this.title = title;
    }

    
    
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
    this.genre = genre;
    }

    
    
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
    this.duration = duration;
    }
   
    public String getPoster_Path(){
        return poster_path;
    }
    public void setPoster_Path(String poster_path){
        this.poster_path = poster_path;
    }
}
