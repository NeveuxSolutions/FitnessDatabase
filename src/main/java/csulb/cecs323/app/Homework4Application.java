/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.logging.Logger;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 *
 * This is for demonstration and educational purposes only.
 */
public class Homework4Application {
   private EntityManager entityManager;
   private EntityCreator creator;
   private EntityInitializer initializer;

   private static final Logger LOGGER = Logger.getLogger(Homework4Application.class.getName());

   public Homework4Application(EntityManager manager) {
      this.entityManager = manager;
   }

   public void startApplication(){
       creator = new EntityCreator(entityManager);
       initializer = new EntityInitializer(entityManager);

       initializeDatabase();
   }

   private void initializeDatabase(){
        initializer.initializeUsers();
        initializer.initializeCheckins();
        initializer.initializeFood();
        initializer.initializeCaloricTotals();
    }

   public static void main(String[] args) {
       System.out.println(System.getProperty("java.class.path"));
       LOGGER.fine("Creating EntityManagerFactory and EntityManager");
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("homework4_PU");
       EntityManager manager = factory.createEntityManager();
       Homework4Application hw4application = new Homework4Application(manager);
       hw4application.startApplication();
    }
    private void initializeDB(){

    }
}
