package service;

import mapper.FilmMapper;
import model.Film;
import utils.DBUtils;
import java.util.List;

public class FilmService {

    public List<Film> getAllFilms(){
        String query = "SELECT * FROM film";
        return DBUtils.executeQuery(query, new FilmMapper());
    }

    public Film getAllFilmById(int filmId){
        String query = "SELECT * FROM film WHERE film_id IN( " + filmId + ")";
        return DBUtils.executeQuery(query, new FilmMapper()).get(0);
    }

    public List<Film> getLastUpdateOfFilms(){
        String query = "SELECT last_update FROM film";
        return DBUtils.executeQuery(query, new FilmMapper());
    }

}
