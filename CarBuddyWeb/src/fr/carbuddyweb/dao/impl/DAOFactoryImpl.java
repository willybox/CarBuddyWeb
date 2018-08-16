package fr.carbuddyweb.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import fr.carbuddy.dao.AddressDAO;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.UserDAO;
import fr.carbuddy.exception.DAOConfigurationRuntimeException;

public class DAOFactoryImpl implements DAOFactory {

    private static final String FICHIER_PROPERTIES = "/fr/carbuddy/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USER = "user";
    private static final String PROPERTY_PASSWORD = "pass";
    private String url;
    private String username;
    private String password;
	private Connection connection;

	private DAOFactoryImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
    public static DAOFactory getInstance() throws DAOConfigurationRuntimeException {
        Properties properties = new Properties();
        String url;
        String userName;
        String pass;
        String driver;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

        if (fichierProperties == null) {
            throw new DAOConfigurationRuntimeException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
        }
        try {
            properties.load(fichierProperties);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            userName = properties.getProperty(PROPERTY_USER);
            pass = properties.getProperty(PROPERTY_PASSWORD);

        } catch (IOException e) {
            throw new DAOConfigurationRuntimeException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationRuntimeException("Le driver est introuvable dans le classpath.", e);
        }
        DAOFactory instance = new DAOFactoryImpl(url, userName, pass);
        return instance;
    }
    
	private void connect() {
        try {
        	System.out.println("Connecting to database...");
        	connection = DriverManager.getConnection(url, username, password);
        	System.out.println("Connection granted.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		if(connection != null) {
        	System.out.println("Closing connection...");
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				/** Quit */
				return;
			}
		}
    	System.out.println("Connection closed.");
	}

	public Connection getConnection() {
		if(connection == null) {
			connect();
		}
		return connection;
	}

    /*
     * Méthodes de récupération de l'implémentation des différents DAO (un seul
     * pour le moment)
     */
	@Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl(this);
    }
	
	@Override
    public AddressDAO getAddressDAO() {
        return new AddressDAOImpl(this);
    }

}
