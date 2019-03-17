package bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/16.
 */

public class Movie implements  Serializable {
    private String movie_picture;
    private String movie_director;
    private String movie_starring;
    private String movie_name;


    public String getMovie_name() {
        return movie_name;
    }

    public String getMovie_director() {
        return movie_director;
    }

    public String getMovie_starring() {
        return movie_starring;
    }

    public void setMovie_starring(String movie_starring) {
        this.movie_starring = movie_starring;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_picture() {
        return movie_picture;
    }

    public void setMovie_picture(String movie_picture) {
        this.movie_picture = movie_picture;
    }
}

