package csulb.cecs323.app;

import csulb.cecs323.model.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Is responsible for creating non-initialized entries in the Database.
 */
public class EntityCreator {
    private static final Logger LOGGER =  Logger.getLogger(Homework4Application.class.getName());
    private EntityManager entityManager;

    public EntityCreator(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    public void createCheckInFromConsole(){
        Scanner in = new Scanner(System.in);
        CheckIn checkIn;
        String date = null;
        String time;
        int clientId;
        User user;
        Timestamp timestamp = null;
        double weight;
        double bodyFat;
        boolean invalidDate = true;
        boolean invalidTime = true;

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        //get a valid date
        System.out.println("Please enter the date of the check-in (YYYY-MM-DD)");
        while (invalidDate) {
            try {
                date = in.next();
                Date.valueOf(date);
                invalidDate = false;
            } catch (Exception e) {
                System.out.println("Please enter a valid date");
            }
        }
        //get a valid time
        System.out.println("Please enter the time of the check-in (HH:MM)");
        while (invalidTime) {
            try {
                time = in.next();
                timestamp = Timestamp.valueOf(date + " " + time + ":00");
                invalidTime = false;
            } catch (Exception e) {
                System.out.println("Please enter a valid time");
            }
        }

        //get userID
        System.out.println("Please enter the userId for the client attending the check-in (ex:3)");
        Query query = entityManager.createQuery("SELECT u.userId FROM User u");
        List<Integer> resultList = query.getResultList();
        clientId = in.nextInt();

        while (!resultList.contains(clientId)){
            System.out.println("Invalid entry, please enter a valid ID (ex:3)");
            clientId = in.nextInt();
        }
        Query usersQuery = entityManager.createQuery("SELECT u FROM User u WHERE u.userId = ?1");
        usersQuery.setParameter(1, clientId);
        user = (User) usersQuery.getSingleResult();

        //get weight
        System.out.println("Please enter the client's weight");
        weight = in.nextDouble();
        while (weight < 40){
            System.out.println("Weights must exceed 40lbs, please enter a valid weight");
            weight = in.nextDouble();
        }

        //get body fat percentage
        System.out.println("Please enter the client's body fat percentage");
        bodyFat = in.nextDouble();
        while (bodyFat < 1 | bodyFat > 65){
            System.out.println("Please enter a percentage between 1 and 65");
            bodyFat = in.nextDouble();
        }

        LOGGER.fine("Creating Check-in Object");
        checkIn = new CheckIn(user,weight,bodyFat,timestamp);
        entityManager.persist(checkIn);
        tx.commit();
        System.out.println("Check-in successfully added!");
    }

    /**
     * Removes a workout from a routine
     */
    public void removeWorkoutFromConsole(){
        Scanner in = new Scanner(System.in);
        int workoutId;
        int routineId;
        Workout workout;
        EntityTransaction tx = entityManager.getTransaction();
        //find valid workout ids
        Query workoutQuery = entityManager.createQuery("SELECT w.workoutId FROM Workout w");
        List<Integer> workoutList = workoutQuery.getResultList();

        System.out.println("Warning, removing a workout will also remove any associated routines and programs!\n"+
                            "Please enter the workoutId to be removed or 0 to return to main menu.");
        workoutId = in.nextInt();
        //check user input is valid
        while (!workoutList.contains(workoutId) | workoutId == 0){
            if(workoutId == 0){
                return;
            }
            System.out.println("Invalid entry, please enter a valid workoutID or 0 to return to main menu.");
            workoutId = in.nextInt();
        }

        System.out.println("Please enter the routineId to be removed 0 or to return to main menu.");
        routineId = in.nextInt();

        //find valid routines
        Query routineQuery = entityManager.createQuery("" +
            "SELECT r.routineId " +
            "FROM Workout w " +
            "INNER JOIN w.routines r");

        List<Integer> routineList = routineQuery.getResultList();
        while (!routineList.contains(routineId) | routineId == 0){
            if(routineId == 0){
                return;
            }
            System.out.println("Invalid entry, please enter a valid routineID or 0 to return to main menu.");
            routineId = in.nextInt();
        }

        //find the workout to remove
        Query usersQuery = entityManager.createQuery("" +
            "SELECT w " +
            "FROM Workout w " +
            "INNER JOIN w.routines r " +
            "WHERE  r.routineId = ?1 AND w.workoutId = ?2");

        usersQuery.setParameter(1, routineId);
        usersQuery.setParameter(2, workoutId);
        workout = (Workout) usersQuery.getSingleResult();

        tx.begin();
        entityManager.remove(workout);
        tx.commit();
    }


}
