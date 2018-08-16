package fr.carbuddyweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.Address;
import fr.carbuddy.dao.AddressDAO;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.exception.DAORuntimeException;

public class AddressDAOImpl implements AddressDAO {

	private DAOFactory daoFactory;

	public AddressDAOImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(Address arg0) throws DAORuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteAddress(Address arg0) throws DAORuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Address findById(Long addressId) throws DAORuntimeException {
		Address address = null;
		Connection connexion = daoFactory.getConnection();
        if(connexion == null) {
        	return address;
        }

        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder();
        	reqStr.append("SELECT id, country, city, postal, street ");
        	reqStr.append("FROM address ");
        	reqStr.append("WHERE id=?;");
            System.out.println("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
            pStatement = connexion.prepareStatement(reqStr.toString());
            pStatement.setLong(1, addressId);

            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            while (resultSet.next()) {
            	address = new Address();
            	Long id = resultSet.getLong("id");
            	String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String postal = resultSet.getString("postal");
                String street = resultSet.getString("street");

                address.setId(id);
                address.setCountry(country);
                address.setCity(city);
                address.setPostal(postal);
                address.setStreet(street);
            }
        } catch (SQLException e) {
            System.err.println("Error during the connection");
            e.printStackTrace();
        } finally {
            System.out.println("Closing ResultSet.");
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch ( SQLException ignore ) {}
            }
            System.out.println("Closing Statement.");
            if ( pStatement != null ) {
                try {
                    pStatement.close();
                } catch ( SQLException ignore ) {}
            }
        }
        return address;
	}

	@Override
	public boolean updateAddress(Address arg0, Address arg1) throws DAORuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

}
