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
 * A not so simple application for tracking fitness for individuals
 */
public class Homework4Application {
   private EntityManager entityManager;
   private EntityEditor editor;
   private EntityInitializer initializer;
   private QueryGenerator queryGenerator;

   private static final Logger LOGGER = Logger.getLogger(Homework4Application.class.getName());

   public Homework4Application(EntityManager manager) {
      this.entityManager = manager;
   }

   public void startApplication(){
       editor = new EntityEditor(entityManager);
       initializer = new EntityInitializer(entityManager);
       queryGenerator = new QueryGenerator(entityManager);

//       initializeDatabase();
       displayUserInterface();
   }

    private void initializeDatabase(){
        initializer.initializeFood();
        initializer.initializeUsers();
        initializer.initializeCheckins();
        initializer.initializeMeal();
        initializer.initializeRoutines();
        initializer.initializeWorkouts();
        initializer.initializeExercises();
        initializer.initializeCardio();
        initializer.initializeMealPlan();
        initializer.initializePrograms();
    }

    private void displayUserInterface(){
       Scanner in = new Scanner(System.in);
       int userInput;
       int subMenuInput;

       System.out.println("Welcome to our fitness application!\n");

       while (true) {
           System.out.println(
                   "\nPlease enter the number corresponding to the action you would like to take and press enter\n" +
                   "1. Display dietary options.\n" +
                   "2. Create a new check-in \n" +
                   "3. Remove a workout from a routine\n" +
                   "4. Count the number of workouts a specific user been assigned.\n" +
                   "5. Retrieve programs with only users who are in their 20s\n" +
                   "6. Retrieve individuals on a program for diabetics and their diet.\n" +
                   "7. Retrieve the shortest user's program and their diet.\n" +
                   "8. Retrieve all users who have never withdrawn from a program and the number of programs/checkins they've had.\n" +
                   "9. Quit");

           userInput = Integer.parseInt(in.next());
           switch (userInput) {
               case 1:
                   System.out.println("\nPlease press the corresponding key and then enter to take the desired action.");
                   System.out.println("1. Display the frequency each food is assigned.");
                   System.out.println("2. Display .");
                   System.out.println("3. Retrieve .");
                   System.out.println("4. Return to the previous menu.");
                   System.out.println("5. Quit");

                   subMenuInput = Integer.parseInt(in.next());
                   switch (subMenuInput) {
                       case 1:
                           queryGenerator.getMealPlansWith6OrLessMeals();
                           break;
                       case 2:
                           queryGenerator.findTimeEaten();
                           break;
                       case 3:
                           break;
                       case 4:
                           break;
                       case 5:
                           System.exit(0);
                   }
                   break;
               case 2:
                   //create a new check-in
                   editor.createCheckInFromConsole();
                   break;
               case 3:
                   editor.removeWorkoutFromConsole();
                   break;
               case 4:
                 //do we need this one?
                   //Count the number of workouts a specific user has done.
                   break;
               case 5:
                   queryGenerator.getProgramsUsedBy20YearOlds();
                   break;
               case 6:
                   queryGenerator.getDiabeticPrograms();
                   break;
               case 7:
                   queryGenerator.getShortestUser();
                   break;
               case 8:
                   queryGenerator.findUnflappableUsers();
                   break;
               case 9:
                   System.exit(0);
           }
       }
    }

   public static void main(String[] args) {
       System.out.println(System.getProperty("java.class.path"));
       LOGGER.fine("Creating EntityManagerFactory and EntityManager");
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("homework4_PU");
       EntityManager manager = factory.createEntityManager();
       System.out.println("Program started...currently loading the database\n");

       Homework4Application hw4application = new Homework4Application(manager);
       hw4application.startApplication();
    }
}
