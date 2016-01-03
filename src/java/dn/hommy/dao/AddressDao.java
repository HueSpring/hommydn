
//city + district + wards

package dn.hommy.dao;

import dn.hommy.connect.ConnectionFactory;
import dn.hommy.entity.City;
import dn.hommy.entity.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AddressDao {
    
    //----------------------------------------FIND------------------------------------------------------------------------------
    //find all city
    public ArrayList<City> findAllCities() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM city";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<City> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    City entity = new City();
                    entity.setIdcity(rs.getInt("idcity"));
                    entity.setCity_name(rs.getString("city_name"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    
}
