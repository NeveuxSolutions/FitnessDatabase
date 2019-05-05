package csulb.cecs323.app;

import csulb.cecs323.model.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class QueryGenerator {
    private EntityManager entityManager;

    public QueryGenerator(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void trialJoins(){
//        only selects distinct users so no casting is necessary

//        Query query = entityManager.createQuery("SELECT DISTINCT u FROM User u INNER JOIN u.checkIns c");
//        List<User> resultList = query.getResultList();
//        for( User u:resultList){
//            System.out.print(u.getfName());
//            System.out.print(u.getlName());
//            System.out.println(u.getUserId());
//        }

//        combines two tables into one, requires casting to access individual attributes
        Query query = entityManager.createQuery("SELECT u,c FROM User u INNER JOIN u.checkIns c");
        List<Object[]> resultList = query.getResultList();
        for(Object[] o : resultList) {
            User u = (User) o[0];
            CheckIn c = (CheckIn) o[1];
            System.out.println(u.getfName() +" " + u.getlName() + " " + c.getCheckInTimeStamp() + " Bodyfat: " + c.getBodyFat());

        }
//        simple left join no nulls to test on currently, dont want to break shit
//        Query query = entityManager.createQuery("SELECT u,c FROM User u LEFT JOIN u.checkIns c");
//        List<Object[]> resultList = query.getResultList();
//        for(Object[] o : resultList) {
//            User u = (User) o[0];
//            CheckIn c = (CheckIn) o[1];
//            System.out.println(u.getfName() +" " + u.getlName() + " " + c.getCheckInTimeStamp() + " Bodyfat: " + c.getBodyFat());
//
//        }
    }

    public void getMealPlansWith6OrLessMeals(){
        Query query = entityManager.createQuery("SELECT u,p,mp FROM User u INNER JOIN u.programs p INNER JOIN  p.mealPlan mp");
        List<Object[]> resultList = query.getResultList();
        for(Object[] o : resultList) {
            User u = (User) o[0];
            Program p = (Program) o[1];
            MealPlan mp = (MealPlan) o[2];
            System.out.println(u.getfName() +" " + u.getlName() + " " + p.getProgramDescription() + " meal plan # meals: " + mp.getNumberOfMeals());

        }

    }

    public void getUnassignedWorkouts(){


    }

    public void getUnassigedUsers(){


    }

    public void findMatchingCaloricIntake(){


    }

    public void countUserWorkouts(){
        Scanner in = new Scanner(System.in);
        int userId;

        System.out.println("\nPlease enter the user's Id");
        Query query = entityManager.createQuery("SELECT u.userId FROM User u");
        List<Integer> resultList = query.getResultList();
        userId = in.nextInt();

        while (!resultList.contains(userId)){
            System.out.println("Invalid entry, please enter a valid ID (ex:3)");
            userId = in.nextInt();
        }

        Query countQuery = entityManager.createQuery("SELECT COUNT(w.workoutId) FROM User u INNER JOIN Program p ON u= p.client INNER JOIN Routine r ON  p=r.program INNER JOIN Workout w WHERE u.userId = ?1 AND w.status = ?2");
        countQuery.setParameter(1, userId);
        countQuery.setParameter(2, Status.COMPLETED);
        long count = (Long) countQuery.getSingleResult();
        System.out.println("User " + userId + " has completed " + count + " workouts.");
    }

    public void getStrongestUser(){


    }

    public void getMealPlanByCalorie(){
    }

    public void getAverageWeightLoss(){
    }
}
