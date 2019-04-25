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

import csulb.cecs323.model.FoodType;
import csulb.cecs323.model.Student;
import csulb.cecs323.model.Food;

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

   private static final Logger LOGGER = Logger.getLogger(Homework4Application.class.getName());

   public Homework4Application(EntityManager manager) {
      this.entityManager = manager;
   }

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("homework4_PU");
      EntityManager manager = factory.createEntityManager();
      Homework4Application hw4application = new Homework4Application(manager);


      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      tx.begin();

      hw4application.createStudentEntity();

      tx.commit();
      LOGGER.fine("End of Transaction");

   }

   /**
    * Create and persist a Student object to the database.
    */
   public void createStudentEntity() {
      LOGGER.fine("Creating Student object");

      Student graceHopper = new Student();
      graceHopper.setFirstName("Grace");
      graceHopper.setLastName("Hopper");
      graceHopper.setGpa(4.0);

      LOGGER.fine("Persisting Student object to DB");
      this.entityManager.persist(graceHopper);
   }

    /**
     * Method to create a food Entity
     * @param name the name of the food
     * @param protein the amount of protein/gram
     * @param carb the amount of carbs/gram
     * @param fat the amount of fat/gram
     * @param type the type of food classifier (PROTEIN/CARBOHYDRATE/FAT)
     */
   public void createFoodEntity(String name, double protein, double carb, double fat, FoodType type) {
       LOGGER.fine("Creating Food Object");
       //General food object
       Food food = new Food();
       food.setName(name);
       food.setProteinGram(protein);
       food.setCarbGram(carb);
       food.setFatGram(fat);
       switch(type) {
           case PROTEIN: food.setFoodType(FoodType.PROTEIN); break;
           case CARBOHYDRATE: food.setFoodType(FoodType.CARBOHYDRATE); break;
           case FAT: food.setFoodType(FoodType.FAT); break;
               default: System.out.println("No matching type found");
       }
       LOGGER.fine("Persisting Food object to DB");
       this.entityManager.persist(food);
   }




}
