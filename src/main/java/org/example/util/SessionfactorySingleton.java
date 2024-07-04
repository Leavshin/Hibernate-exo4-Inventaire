package org.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionfactorySingleton {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    //constructeur en privé pour être sûre d'avoir une seule instance de session factory créée
    private SessionfactorySingleton (){
        registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }


    //méthode nous permettant de toujours venir récupérer la même instance de notre session factory
    public static synchronized SessionFactory getSessionFactory (){
        if(sessionFactory == null){
            new SessionfactorySingleton();
        }
        return sessionFactory;
    }
}
