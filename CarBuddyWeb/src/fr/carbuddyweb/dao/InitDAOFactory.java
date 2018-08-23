package fr.carbuddyweb.dao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.mysql.DAOFactoryMySQLImpl;
import fr.carbuddy.global.ConstantValues;

public class InitDAOFactory implements ServletContextListener {
    private DAOFactory daoFactory;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        /** Getting ServletContext at application loading */
        ServletContext servletContext = event.getServletContext();
        /** Instantiation of DAOFactory */
        this.daoFactory = DAOFactoryMySQLImpl.getInstance();
        /** Saving as attribute in application scope */
        servletContext.setAttribute(ConstantValues.ATT_DAO_FACTORY, this.daoFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        /** Nothing to do when application closed... */
    }
}