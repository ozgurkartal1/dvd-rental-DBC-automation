package service;

import mapper.FilmDetailsMapper;
import model.FilmDetails;
import utils.DBUtils;

import java.util.List;

public class FilmDetailsService {

    public List<FilmDetails> getAllFilmDetails(){
        String query = "SELECT film.title,film.description,film.release_year,category.name AS category," +
                       "STRING_AGG(CONCAT(actor.first_name, ' ', actor.last_name), ', ') AS actor_list " +
                       "FROM film " +
                       "INNER JOIN film_category ON film.film_id = film_category.film_id " +
                       "INNER JOIN category ON film_category.category_id = category.category_id " +
                       "INNER JOIN film_actor ON film.film_id = film_actor.film_id " +
                       "INNER JOIN actor ON film_actor.actor_id = actor.actor_id " +
                       "GROUP BY film.title,film.description,film.release_year,category.name;";

        return DBUtils.executeQuery(query, new FilmDetailsMapper());
    }

    public FilmDetails getFilmByTitle(String titleName){
        String query = "SELECT film.title,film.description,film.release_year,category.name AS category," +
                "STRING_AGG(CONCAT(actor.first_name, ' ', actor.last_name), ', ') AS actor_list" +
                " FROM film " +
                " INNER JOIN film_category ON film.film_id = film_category.film_id" +
                " INNER JOIN category ON film_category.category_id = category.category_id" +
                " INNER JOIN film_actor ON film.film_id = film_actor.film_id" +
                " INNER JOIN actor ON film_actor.actor_id = actor.actor_id" +
                " WHERE film.title = '" + titleName + "'" +
                " GROUP BY film.title,film.description,film.release_year,category.name";

        System.out.println(query);

        return DBUtils.executeQuery(query, new FilmDetailsMapper()).get(0);
    }
}
