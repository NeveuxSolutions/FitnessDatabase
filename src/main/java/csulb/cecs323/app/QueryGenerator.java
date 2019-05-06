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

    public void getProgramsUsedBy20YearOlds(){
        String fname;
        String lname;
        String program;
        String routine;

        Query youngQuery = entityManager.createQuery("" +
                "SELECT DISTINCT u,p,r " +
                "FROM User u " +
                    "INNER JOIN u.programs p " +
                    "INNER JOIN p.routine r " +
                    "   WHERE EXISTS (" +
                            "SELECT u2.age " +
                                "FROM User u2 "+
                        "           WHERE u.age > ?1 AND u.age < ?2)");

        youngQuery.setParameter(1, 19);
        youngQuery.setParameter(2, 30);

        List<Object[]> programList = youngQuery.getResultList();
        for(Object[] o: programList){
            fname = ((User) o[0]).getfName();
            lname = ((User) o[0]).getlName();
            program = ((Program) o[1]).getProgramGoal().toString();
            routine = ((Routine) o[2]).getRoutineName();

            System.out.println("\nUser: " +fname + " " + lname);
            System.out.println("Program Goal: " + program);
            System.out.println("Routine Name: " + routine);
        }
    }

    public void getDiabeticPrograms(){
        String mealPlanName;
        String fname;
        String lname;
        String program;

        Query mealQuery = entityManager.createQuery("" +
            "SELECT DISTINCT mp, p, u " +
                "FROM MealPlan mp" +
                "   INNER JOIN  mp.program p " +
                "   INNER JOIN p.client u " +
                "   WHERE mp.mealPlanId IN (" +
                        "SELECT mp2.mealPlanId " +
                            "FROM MealPlan mp2 " +
                            "    INNER JOIN mp2.program p " +
                            "    WHERE p.programDescription LIKE :code)");

        mealQuery.setParameter("code", "%" + "Diabetic" + "%");
        List<Object[]> mealList = mealQuery.getResultList();

        for(Object[] o : mealList) {
            mealPlanName = ((MealPlan) o[0]).getMealPlanName();
            fname = ((User) o[2]).getfName();
            lname = ((User) o[2]).getlName();
            program = ((Program) o[1]).getProgramDescription();

            System.out.println("\nUser: " +fname + " " + lname);
            System.out.println("Program Description: " + program);
            System.out.println("Meal Plan Name: " + mealPlanName);
        }
    }

    public void getShortestUser(){
        String mealPlanName;
        String fname;
        String lname;
        String program;
        double height;

        Query shortQuery = entityManager.createQuery("" +
            "SELECT DISTINCT u, p, mp " +
                "FROM User u" +
                "   INNER JOIN  u.programs p " +
                "   INNER JOIN p.mealPlan mp " +
                "   WHERE u.height <= ALL (" +
                        "SELECT u2.height " +
                            "FROM User u2)");

        List<Object[]> shortUserStats = shortQuery.getResultList();
        for(Object[] o: shortUserStats){
            fname = ((User) o[0]).getfName();
            lname = ((User) o[0]).getlName();
            height = ((User) o[0]).getHeight();
            program = ((Program) o[1]).getProgramDescription();
            mealPlanName = ((MealPlan) o[2]).getMealPlanName();

            System.out.println("\nUser:" +fname + " " + lname);
            System.out.println("Height in inches: " + height);
            System.out.println("Program Description: " +program);
            System.out.println("Meal Plan Name: " + mealPlanName);
        }
    }

    public void userMealPlans() {
        Query query = entityManager.createQuery("SELECT u FROM User u INNER JOIN Program p ON u=p.client");
        List<User> users = query.getResultList();
        for(User user : users) {
            System.out.println(user.getfName());
        }
    }
}
