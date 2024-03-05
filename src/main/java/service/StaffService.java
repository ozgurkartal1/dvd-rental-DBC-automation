package service;

import mapper.StaffMapper;
import model.Staff;
import utils.DBUtils;

import java.util.List;

public class StaffService {

    public List<Staff> getAllStaffs(){
        String query = "SELECT * FROM staff";
        return DBUtils.executeQuery(query, new StaffMapper());
    }

    public Staff getStaffById(int staffId){
        String query = "SELECT * FROM staff WHERE staff_id IN (" + staffId + ")";
        return DBUtils.executeQuery(query, new StaffMapper()).get(0);
    }

    public List<Staff> getActiveFeatureForStaffs(){
        String query = "SELECT active FROM staff";
        return DBUtils.executeQuery(query, new StaffMapper());
    }
}
