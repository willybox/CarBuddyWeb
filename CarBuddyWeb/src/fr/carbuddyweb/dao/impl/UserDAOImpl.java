package fr.carbuddyweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.UserDAO;
import fr.carbuddy.enumeration.Gender;
import fr.carbuddy.enumeration.string.StatusUser;
import fr.carbuddy.exception.DAORuntimeException;

public class UserDAOImpl implements UserDAO {
	private DAOFactory daoFactory;

	public UserDAOImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(User arg0) throws DAORuntimeException {
		// TODO Auto-generated method stub
		
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
		Connection connexion = daoFactory.getConnection();
        User user = null;
        if(connexion == null) {
        	return user;
        }
        
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT *")
	        	.append("FROM user, person ")
	        	.append("WHERE id=? ")
	        	.append("AND user.personId = person.id")
        	;
        	
        	System.out.print("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
            pStatement = connexion.prepareStatement(reqStr.toString());
            pStatement.setLong(1, userId);
            
            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            while (resultSet.next()) {
                user = new User();
                
                user.setId(resultSet.getLong("id"));
                
                /** -- Abstract Person properties -- */

                Gender genderEnum = Gender.getEnum(resultSet.getInt("gender"));
                user.setGender(genderEnum);
                user.setFirstname(resultSet.getString("firstname"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                
                /** -- User properties -- */
                
                int statusUser = resultSet.getInt("statusUser");
                StatusUser statusUserEnum;
                switch(statusUser) {
                	case 1:
                		statusUserEnum = StatusUser.DRIVER;
                		break;
                	default:
                		statusUserEnum = StatusUser.BUDDY;
                }
                user.setStatusUser(statusUserEnum);

                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
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

}
