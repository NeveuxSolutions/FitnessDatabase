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
//       displayUserInterface();
   }

    private void initializeDatabase(){
//        initializer.test();
        initializer.initializeFood();
//        initializer.initializeUsers();
//        initializer.initializeCheckins();
        initializer.initializeMeal();
        initializer.initializeMealPlan();

    }

    //@TODO should be outside console? so we dont have all the db print statements breaking up the user interface or should we just delete them all?
    //@TODO error handling for user input in sub menus (Monge enters invalid userID or something)
    private void displayUserInterface(){
       Scanner in = new Scanner(System.in);
       int userInput = -1;
        System.out.println("Welcome to our fitness application!");


        while (true) {
           System.out.println("\nPlease press the corresponding key and then enter to take the desired action.");
           System.out.println("1. was one erased or did we just start at two in the google doc?");
           System.out.println("2. List all user meal plans with less than 6 meals.");
           System.out.println("3. Retrieve all exercises that are not assigned to a workout.");
           System.out.println("4. Retrieve a count of how many users are not currently on a program.");
           System.out.println("5. Count the number of workouts for a user.");
           System.out.println("6. Retrieve strongest users.");
           System.out.println("7. Retrieve meal plans by calorie count.");
           System.out.println("8. Retrieve average weight loss for a user.");
           System.out.println("9: Exit Program");
           userInput = Integer.parseInt(in.next());

           switch (userInput){
               case 1:
                        break;
               case 2:
                        queryGenerator.getMealPlansWith6OrLessMeals();
                        break;
               case 3:
                        break;
               case 4:
                        break;
               case 5:
                        System.out.println("\nPlease enter the user's Id"); //or first name last name? or display users?
                        Scanner scanner5 = new Scanner(System.in);

                        break;
               case 6:
                        System.out.println("\nPlease enter the exercise name"); //want to just provide a numbered list of exercises?
                        Scanner scanner6 = new Scanner(System.in);
                        break;
               case 7:
                        System.out.println("\nPlease enter desired number of average calories"); //want to just provide a numbered list of calorie totals
                        Scanner scanner7 = new Scanner(System.in);
                        break;
               case 8:
                        System.out.println("\nPlease enter the user's Id"); //or first name last name? or display users?
                        Scanner scanner8 = new Scanner(System.in);
                        queryGenerator.trialJoins();
                        break;
               case 9:  System.exit(0);
                        break;
           }
       }
    }

   public static void main(String[] args) {
       System.out.println(System.getProperty("java.class.path"));
       LOGGER.fine("Creating EntityManagerFactory and EntityManager");
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("homework4_PU");
       EntityManager manager = factory.createEntityManager();

       Homework4Application hw4application = new Homework4Application(manager);
       hw4application.startApplication();
    }
}
