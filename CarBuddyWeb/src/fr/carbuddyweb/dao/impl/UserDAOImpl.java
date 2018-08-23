package fr.carbuddyweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.carbuddy.bean.User;
import fr.carbuddy.dao.AbstractUserDAO;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.enumeration.order.by.UserOrderBy;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.service.PersonService;
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
	        		userToCreate.setPersonId(personRS.getLong(1));
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
		        			userToCreate.getPersonId()
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
	public boolean deleteUser(User userToDelete) throws DAORuntimeException {
		Connection connection = daoFactory.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqPersonStr = new StringBuilder()
	        	.append("DELETE FROM person ")
	        	.append("WHERE id=?")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	PreparedStatement personPreparedStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqPersonStr.toString(),
        			true,
        			userToDelete.getPersonId()
        		);
        	int personSuccessCount = personPreparedStatement.executeUpdate();
        	PersonService persService = new PersonService(userToDelete);
        	if(personSuccessCount > 0) {
        		System.out.println(
        			"Successfully removed "
        			+ persService.presentWithFirstnameAndName()
        			+ " from database."
        		);
        	} else {
        		System.out.println("Person with id "
        			+ userToDelete.getPersonId()
        			+ " not found in database. Deletion failed."
        		);
        	}

        	StringBuilder reqStr = new StringBuilder()
    	        	.append("DELETE FROM user ")
    	        	.append("WHERE id=?")
    	        	.append(";")
	        	;
	        	
	        	System.out.print("Request \"" + reqStr.toString());
	        	
	            /** Creating requests manager */
	        	pStatement = AddOnSQL
	        		.initPreparedStatement(
	    				connection,
	        			reqStr.toString(),
	        			true,
	        			userToDelete.getId()
	        		);
	            
	            /** Executing Insert into query */
	            int successCount = pStatement.executeUpdate();
	            System.out.println("\" done.");
	            
	            if(successCount > 0) {
	        		System.out.println(
	        			"Successfully removed "
	        			+ userToDelete.getUsername()
	        			+ " from database."
	        		);
	        	} else {
	        		System.out.println("User with id "
	        			+ userToDelete.getId()
	        			+ " not found in database. Deletion failed."
	        		);
	        	}

        } catch (SQLException e) {
            throw new DAORuntimeException("Error during the connection");
        } finally {
            AddOnSQL.fancyClose(resultSet, ReadOnlyGlobal.NO_DEBUG);
            AddOnSQL.fancyClose(pStatement, ReadOnlyGlobal.NO_DEBUG);
        }

		return true;
	}

	@Override
	public List<User> findByEmail(String arg0) throws DAORuntimeException {
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
	public List<User> findByName(String arg0) throws DAORuntimeException {
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
            	long addressId = resultSet.getLong("addressId");
            	user.setAddress(
        			daoFactory.getAddressDAO().findById(addressId)
                );
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
	public List<User> listUser(UserOrderBy orderBy, boolean arg1) {
		List<User> users = new ArrayList<>();
        
        Connection connexion = daoFactory.getConnection();
        if(connexion == null) {
        	return users;
        }
        
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	if(orderBy == null) {
        		orderBy = UserOrderBy.ID_USER;
        	}
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM user, person ")
	        	.append("WHERE user.personId = person.id ")
	        	.append("ORDER BY ").append(orderBy.toString())
	        	.append(";")
	        ;
        	System.out.print("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
            pStatement = connexion.prepareStatement(reqStr.toString());

            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            while (resultSet.next()) {
            	User user = getUserFromResultSet(resultSet);
            	long addressId = resultSet.getLong("addressId");
            	user.setAddress(
        			daoFactory.getAddressDAO().findById(addressId)
                );
            	users.add(user);
            }
        } catch (SQLException e) {
            throw new DAORuntimeException("Error during the connection");
        } finally {
            AddOnSQL.fancyClose(resultSet, ReadOnlyGlobal.NO_DEBUG);
            AddOnSQL.fancyClose(pStatement, ReadOnlyGlobal.NO_DEBUG);
        }

        return users;
	}

}
