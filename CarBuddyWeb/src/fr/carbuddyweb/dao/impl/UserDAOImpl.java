package fr.carbuddyweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import fr.carbuddy.bean.Address;
import fr.carbuddy.bean.User;
import fr.carbuddy.dao.AbstractUserDAO;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddyweb.global.ReadOnlyGlobal;
import util.library.add.on.sql.AddOnSQL;

public class UserDAOImpl extends AbstractUserDAO {
	private DAOFactory daoFactory;

	public UserDAOImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public User create(User userToCreate) throws DAORuntimeException {
		Connection connection = daoFactory.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqPersonStr = new StringBuilder()
	        	.append("INSERT INTO person(name, firstname, gender, addressId, email, phone, birthday) ")
	        	.append("VALUES(?, ?, ?, ?, ?, ?, ?)")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	PreparedStatement personPreparedStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqPersonStr.toString(),
        			true,
        			userToCreate.getName(),
        			userToCreate.getFirstname(),
        			userToCreate.getGender().getValue(),
        			userToCreate.getAddress().getId(),
        			userToCreate.getEmail(),
        			userToCreate.getPhone(),
        			userToCreate.getBirthday()
        		);
        	int personSuccessCount = personPreparedStatement.executeUpdate();
        	ResultSet personRS = personPreparedStatement.getGeneratedKeys();
        	if(personSuccessCount > 0) {
	        	if(personRS.next()) {
	        	
		        	StringBuilder reqStr = new StringBuilder()
			        	.append("INSERT INTO user(statusUser, userName, password, personId) ")
			        	.append("VALUES(?, ?, ?, ?)")
			        	.append(";")
		        	;
		        	
		        	System.out.print("Request \"" + reqStr.toString());
		        	
		            /** Creating requests manager */
		        	pStatement = AddOnSQL
		        		.initPreparedStatement(
		    				connection,
		        			reqStr.toString(),
		        			true,
		        			userToCreate.getStatusUser().getValue(),
		        			userToCreate.getUsername(),
		        			userToCreate.getPassword(),
		        			personRS.getLong(1)
		        		);
		            
		            /** Executing Insert into query */
		            int successCount = pStatement.executeUpdate();
		            System.out.println("\" done.");
		            
		            if(successCount > 0) {
			            ResultSet userRS = pStatement.getGeneratedKeys();
			            if(userRS.next()) {
			            	userToCreate.setId(userRS.getLong("id"));
			            } else {
			            	throw new DAORuntimeException("User creation failed, no auto-generated ID returned.");
			            }
		            } else {
		            	throw new DAORuntimeException("User creation failed.");
		            }
	        	} else {
	            	throw new DAORuntimeException("User creation failed, no auto-generated ID returned for Person.");
	            }
        	} else {
            	throw new DAORuntimeException("User creation failed at Person creation.");
            }
        } catch (SQLException e) {
            throw new DAORuntimeException("Error during the connection");
        } finally {
            AddOnSQL.fancyClose(resultSet, ReadOnlyGlobal.NO_DEBUG);
            AddOnSQL.fancyClose(pStatement, ReadOnlyGlobal.NO_DEBUG);
        }

        return userToCreate;
	}

	@Override
	public boolean deleteUser(User arg0) throws DAORuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<User> findByEmail(String arg0) throws DAORuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(Long userId) throws DAORuntimeException {
		Connection connection = daoFactory.getConnection();
        User user = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM user, person ")
	        	.append("WHERE id=? ")
	        	.append("AND user.personId = person.id")
	        	.append(";")
        	;
        	
        	System.out.print("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
        			reqStr.toString(),
        			false,
        			userId
        		);
            
            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            if(resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAORuntimeException("Error during the connection");
        } finally {
            AddOnSQL.fancyClose(resultSet, ReadOnlyGlobal.NO_DEBUG);
            AddOnSQL.fancyClose(pStatement, ReadOnlyGlobal.NO_DEBUG);
        }

        return user;
	}

	@Override
	public Set<User> findByName(String arg0) throws DAORuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(User arg0, User arg1) throws DAORuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findByUsername(String username) {
		Connection connection = daoFactory.getConnection();
        User user = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM user, person ")
	        	.append("WHERE userName=? ")
	        	.append("AND user.personId = person.id")
	        	.append(";")
        	;
        	
        	System.out.print("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
        			reqStr.toString(),
        			false,
        			username
        		);
            
            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            if(resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAORuntimeException("Error during the connection");
        } finally {
            AddOnSQL.fancyClose(resultSet, ReadOnlyGlobal.NO_DEBUG);
            AddOnSQL.fancyClose(pStatement, ReadOnlyGlobal.NO_DEBUG);
        }

        return user;
	}

}
