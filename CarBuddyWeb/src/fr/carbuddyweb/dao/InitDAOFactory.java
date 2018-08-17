package fr.carbuddyweb.dao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fr.carbuddy.dao.DAOFactory;
import fr.carbuddyweb.dao.impl.DAOFactoryImpl;
import fr.carbuddyweb.global.ReadOnlyGlobal;

public class InitDAOFactory implements ServletContextListener {
    private DAOFactory daoFactory;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        /** Getting ServletContext at application loading */
        ServletContext servletContext = event.getServletContext();
        /** Instantiation of DAOFactory */
        this.daoFactory = DAOFactoryImpl.getInstance();
        /** Saving as attribute in application scope */
        servletContext.setAttribute(ReadOnlyGlobal.ATT_DAO_FACTORY, this.daoFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        /** Nothing to do when application closed... */
    }
}