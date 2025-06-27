/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nitan
 */
public class Moviegenre {
      private String id;
    private String title;
    private String genre;
    private String imagePath; // Optional, if you're displaying posters

    public Moviegenre(String id, String title, String genre, String imagePath) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.imagePath = imagePath;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getPosterPath() { return imagePath; }
}
