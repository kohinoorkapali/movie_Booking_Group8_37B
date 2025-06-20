package Model;

public class Movie_add {
    private String id;
    private String title;
    private String genre;
    private String synopsis;
    private String duration;
    private String showDate;
    private String imagePath;
    private double price;
    
    public Movie_add(String id, String title, String genre, String synopsis, String duration, String showDate, String imagePath, double price) {
    this.id = id;
    this.title = title;
    this.genre = genre;
    this.synopsis = synopsis;
    this.duration = duration;
    this.showDate = showDate;
    this.imagePath = imagePath;
    this.price = price;
}



    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getDuration() {
        return duration;
    }

    public String getShowDate() {
        return showDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public double getPrice() {
    return price;
}

public void setPrice(double price) {
    this.price = price;
}
}
