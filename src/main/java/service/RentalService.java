package service;

import mapper.RentalMapper;
import model.Rental;
import utils.DBUtils;

import java.util.List;

public class RentalService {

    public List<Rental> getAllRentals(){
        String query = "SELECT * FROM rental";
        return DBUtils.executeQuery(query, new RentalMapper());
    }

    public Rental getRentalById(int rentalId){
        String query = "SELECT * FROM rental WHERE rental_id IN (" + rentalId + ")";
        return DBUtils.executeQuery(query, new RentalMapper()).get(0);
    }

    public List<Rental> getNumberOfRentalsPerStaffs(){
        String query = "SELECT staff_id, COUNT(rental_id) FROM rental " +
                "GROUP BY staff_id ORDER BY staff_id";

        return DBUtils.executeQuery(query, new RentalMapper());
    }
}
