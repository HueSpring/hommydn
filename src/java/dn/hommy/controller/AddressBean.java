
//city + district + wards
package dn.hommy.controller;

import dn.hommy.dao.AddressDao;
import dn.hommy.entity.City;
import dn.hommy.entity.District;
import dn.hommy.entity.Wards;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AddressBean {

    private City city = new City();
    private District district = new District();
    private Wards wards = new Wards();

    public AddressBean() {
    }

    //--------------------------------------------FIND---------------------------------------------------------------------
    //find all cities
    public ArrayList<City> getCities() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        AddressDao dao = new AddressDao();
        return dao.findAllCities();
    }
}
