package fr.carbuddyweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.carbuddy.bean.Address;
import fr.carbuddy.dao.AbstractAddressDAO;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddyweb.global.ReadOnlyGlobal;
import util.library.add.on.sql.AddOnSQL;

public class AddressDAOImpl extends AbstractAddressDAO {

	private DAOFactory daoFactory;

	public AddressDAOImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Address create(Address addressToCreate) throws DAORuntimeException {
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("INSERT INTO address(country, city, postal, street) ")
	        	.append("VALUES(?, ?, ?, ?)")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqStr.toString(),
        			true,
        			addressToCreate.getCountry(),
        			addressToCreate.getCity(),
        			addressToCreate.getPostal(),
        			addressToCreate.getStreet()
        		);
        	int successCount = pStatement.executeUpdate();
        	
            if(successCount > 0) {
	            ResultSet addressRS = pStatement.getGeneratedKeys();
	            if(addressRS.next()) {
	            	addressToCreate.setId(addressRS.getLong(1));
	            } else {
	            	throw new DAORuntimeException("Address creation failed, no auto-generated ID returned.");
	            }
            } else {
            	throw new DAORuntimeException("Address creation failed.");
            }
        } catch (SQLException e) {
            throw new DAORuntimeException("Error during the connection");
        } finally {
            AddOnSQL.fancyClose(resultSet, ReadOnlyGlobal.NO_DEBUG);
            AddOnSQL.fancyClose(pStatement, ReadOnlyGlobal.NO_DEBUG);
        }

        return addressToCreate;
	}

	@Override
	public boolean deleteAddress(Address arg0) throws DAORuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Address findById(Long addressId) throws DAORuntimeException {
		Connection connection = daoFactory.getConnection();
		Address address = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
    			.append("SELECT id, country, city, postal, street ")
	        	.append("FROM address ")
	        	.append("WHERE id=?")
	        	.append(";");
            System.out.println("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
            pStatement = AddOnSQL
            	.initPreparedStatement(
        			connection,
            		reqStr.toString(),
            		false,
            		addressId
            	);

            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            if(resultSet.next()) {
            	address = getAddressFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAORuntimeException("Error during the connection");
        } finally {
            AddOnSQL.fancyClose(resultSet, ReadOnlyGlobal.NO_DEBUG);
            AddOnSQL.fancyClose(pStatement, ReadOnlyGlobal.NO_DEBUG);
        }
        return address;
	}

	@Override
	public boolean updateAddress(Address arg0, Address arg1) throws DAORuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Address getAddress(Address addressproperties) throws DAORuntimeException {
		Connection connection = daoFactory.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
    			.append("SELECT id, country, city, postal, street ")
	        	.append("FROM address ")
	        	.append("WHERE country=? ")
	        	.append("AND city=? ")
	        	.append("AND postal=? ")
	        	.append("AND street=?")
	        	.append(";");
            System.out.println("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
            pStatement = AddOnSQL
            	.initPreparedStatement(
        			connection,
            		reqStr.toString(),
            		false,
        			addressproperties.getCountry(),
        			addressproperties.getCity(),
        			addressproperties.getPostal(),
        			addressproperties.getStreet()
            	);

            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            if(resultSet.next()) {
            	addressproperties.setId(resultSet.getLong("id"));
            } else {
            	return null;
            }
        } catch (SQLException e) {
            throw new DAORuntimeException("Error during the connection");
        } finally {
            AddOnSQL.fancyClose(resultSet, ReadOnlyGlobal.NO_DEBUG);
            AddOnSQL.fancyClose(pStatement, ReadOnlyGlobal.NO_DEBUG);
        }
        return addressproperties;
	}

	@Override
	public List<String> listCity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> listCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> listPostal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> listStreet() {
		// TODO Auto-generated method stub
		return null;
	}

}
