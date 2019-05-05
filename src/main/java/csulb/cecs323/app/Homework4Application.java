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
import javax.persistence.Persistence;
import java.util.Scanner;
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
   private QueryGenerator queryGenerator;

   private static final Logger LOGGER = Logger.getLogger(Homework4Application.class.getName());

   public Homework4Application(EntityManager manager) {
      this.entityManager = manager;
   }

   public void startApplication(){
       creator = new EntityCreator(entityManager);
       initializer = new EntityInitializer(entityManager);
       queryGenerator = new QueryGenerator(entityManager);

       initializeDatabase();
       displayUserInterface();
   }

    private void initializeDatabase(){
        initializer.initializeFood();
//        initializer.initializeCardio();
//        initializer.initializeExercises();
//        initializer.initializeUsers();
//        initializer.initializeCheckins();
        initializer.initializeMeal();
//        initializer.initializeWorkouts();
        initializer.initializeMealPlan();
//        initializer.initializeRoutines();
//        initializer.initializePrograms();

    }

    //@TODO error handling for user input in sub menus (Monge enters invalid userID or something)
    private void displayUserInterface(){
       Scanner in = new Scanner(System.in);
       int userInput;
       int subMenuInput;

       System.out.println("Welcome to our fitness application!\n");

       while (true) {
           System.out.println(
                   "\nPlease enter the number corresponding to the action you would like to take and press enter\n" +
                   "1. Display trends a trainer should monitor.\n" +
                   "2. Create a new check-in \n" +
                   "3. Remove a user\n" +
                   "4. Count the number of workouts a specific user has done.\n" +
                   "5. Retrieve the user who has lifted the most total weight\n" +
                   "6. Retrieve meal plans based on their calorie count.\n" +
                   "7. Retrieve the average weight loss for all users.\n" +
                   "8. Quit");

           userInput = Integer.parseInt(in.next());
           switch (userInput) {
               case 1:
                   System.out.println("\nPlease press the corresponding key and then enter to take the desired action.");
                   System.out.println("1. Display all users and show which users have never completed a program.");
                   System.out.println("2. Display all exercises and show which are not assigned to a workout.");
                   System.out.println("3. Retrieve a count of how many users are not currently on a program.");
                   System.out.println("4. Return to the previous menu.");
                   System.out.println("5. Quit");

                   subMenuInput = Integer.parseInt(in.next());
                   switch (subMenuInput) {
                       case 1:
                           queryGenerator.getMealPlansWith6OrLessMeals();
                           break;
                       case 2:
                           //Retrieve all exercises that are not assigned to a workout
                           break;
                       case 3:
                           //Retrieve a count of how many users are not currently on a program
                           break;
                       case 4:
                           break;
                       case 5:
                           System.exit(0);
                   }
                   break;

               case 2:
                   //create a new check-in
                   creator.createCheckInFromConsole();
                   break;
               case 3:
                   //Remove a user
                   break;
               case 4:
                   System.out.println("\nPlease enter the user's Id"); //or first name last name? or display users?
                   //Count the number of workouts a specific user has done.
                   break;
               case 5:
                   //Retrieve the user who has lifted the most total weight
                   break;
               case 6:
                   System.out.println("\nPlease enter desired number of average calories"); //want to just provide a numbered list of calorie totals
                   //Retrieve meal plans based on their calorie count.
                   break;
               case 7:
                  //Retrieve the average weight loss for all users
                   break;
               case 8:
                   System.exit(0);
           }
       }
    }

   public static void main(String[] args) {
       System.out.println(System.getProperty("java.class.path"));
       LOGGER.fine("Creating EntityManagerFactory and EntityManager");
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("homework4_PU");
       EntityManager manager = factory.createEntityManager();
       System.out.println("It's running I just disabled all the db messages\n");

       Homework4Application hw4application = new Homework4Application(manager);
       hw4application.startApplication();
    }
}
