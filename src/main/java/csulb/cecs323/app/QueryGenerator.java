package csulb.cecs323.app;

import csulb.cecs323.model.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Generates all queries to the database
 */
public class QueryGenerator {
    private EntityManager entityManager;

    public QueryGenerator(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    /**
     * Query to find meal plans with 6 or less meals
     */
    public void getMealPlansWith6OrLessMeals(){
        Query query = entityManager.createQuery("" +
                "SELECT u,p,mp " +
                "FROM User u " +
                "INNER JOIN u.programs p " +
                "INNER JOIN  p.mealPlan mp");

        List<Object[]> resultList = query.getResultList();
        for(Object[] o : resultList) {
            User u = (User) o[0];
            Program p = (Program) o[1];
            MealPlan mp = (MealPlan) o[2];
            System.out.println(u.getfName() +" " + u.getlName() + " " + p.getProgramDescription() + " meal plan # meals: " + mp.getNumberOfMeals());

        }
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

        Query countQuery = entityManager.createQuery("" +
                "SELECT COUNT(w.workoutId) " +
                "FROM User u " +
                    "INNER JOIN Program p ON u= p.client " +
                    "INNER JOIN Routine r ON  p=r.program " +
                    "INNER JOIN Workout w WHERE u.userId = ?1");

        countQuery.setParameter(1, userId);
        long count = (Long) countQuery.getSingleResult();
        System.out.println("User " + userId + " has been assigned " + count + " workouts.");

    }

    /**
     * Query to find programs with only 20 year old users
     */
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
    /**
     * Query to find programs for diabetics
     */
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
    /**
     * Query to find the shortest user and their program
     */
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
        Query query = entityManager.createQuery("SELECT u, p FROM User u LEFT JOIN Program p ON u=p.client");
        List<Object[]> results = query.getResultList();
        for(Object[] o : results) {
            String fname = ((User) o[0]).getfName();
            Date date = ((Program) o[1]).getEndDate();
            System.out.println(fname + " " + date);
        }
    }

    /**
     * Query to find average number of meals a user eats
     */
    public void findAverageNumMeals() {
        Query query = entityManager.createQuery(
        "SELECT u.fName, u.lName, AVG(mp.numberOfMeals) " +
                "FROM User u " +
                "INNER JOIN Program p ON u=p.client " +
                "INNER JOIN MealPlan mp ON mp=p.mealPlan " +
                "GROUP BY u.fName, u.lName, mp.numberOfMeals");

        List<Object[]> results = query.getResultList();
        for (Object[] o : results) {
            System.out.println(o[0] + " " + o[1] + " " + o[2]);
        }
    }

    /**
     * Query to find the average weight of users who weigh more than 200lbs
     */
    public void findAverageWeight() {
        Query query = entityManager.createQuery(
        "SELECT u.fName, u.lName, AVG(c.weight)" +
                "FROM CheckIn c " +
                "INNER JOIN User u ON u=c.userId " +
                "INNER JOIN Program p ON u=p.client " +
                "GROUP BY u.fName, u.lName, c.bodyFat, c.weight " +
                "HAVING c.bodyFat > 15");

        List<Object[] > list = query.getResultList();
        for (Object[] o : list) {
            System.out.println(o[0] + " " + o[1] + " " + Math.round((double)o[2]));
        }
    }


    /**
     * Query to find the number of meals each food is assigned to
     */
    public void findFood() {
        Query query = entityManager.createQuery(
        "SELECT f.name, COUNT(m.mealName) " +
                "FROM Food f " +
                "LEFT JOIN CaloricTotal c ON c.food=f " +
                "LEFT JOIN Meal m ON m=c.meal " +
                "GROUP BY f.name");

        List<Object[] > list = query.getResultList();
        for (Object[] o : list) {
            System.out.println(o[0] + " " + o[1]);
        }
    }

    /**
     * Query to find what foods are assigned to meals but arent assigned to meal plans
     * Counts the number of meals that the food is assigned to which aren't assigned to meal plans
     */
    public void findTimeEaten() {

        Query query = entityManager.createQuery(
        "SELECT f.name, COUNT(m), COALESCE(mp.dietDescription, 'No MealPlan') " +
                "FROM Food f " +
                "LEFT OUTER JOIN f.caloricTotals c " +
                "LEFT OUTER JOIN c.meal m " +
                "LEFT OUTER JOIN m.mealPlans mp " +
                "WHERE mp.mealPlanId IS NULL " +
                "GROUP BY f.name"
        );

        List<Object[] > list = query.getResultList();
        for (Object[] o : list) {
            System.out.println(o[0] + " " + o[1] + " " + o[2]);
        }
    }

    /**
     * Query to find the users that have never resigned a program
     */
    public void findUnflappableUsers(){
        String fname;
        String lname;
        long programs;
        long checkIns;

        Query differenceQuery = entityManager.createQuery("" +
            "SELECT DISTINCT u, Count(c), Count(p) " +
            "FROM User u" +
            "   LEFT JOIN u.checkIns c " +
            "   LEFT JOIN u.programs p " +
            "   WHERE p.status NOT IN (" +
            "       SELECT p2.status" +
            "       FROM Program p2" +
            "           WHERE p2.status = ?1) " +
            "   GROUP BY u");

        differenceQuery.setParameter(1, Status.WITHDRAWN);

        List<Object[]> usersWithoutWithdraws = differenceQuery.getResultList();
        for(Object[] o : usersWithoutWithdraws){
            fname = ((User) o[0]).getfName();
            lname = ((User) o[0]).getlName();
            programs = ((Long)o[2]);
            checkIns = ((Long)o[1]);

            System.out.println("\n" + fname + " " + lname);
            System.out.println("Programs: " + programs);
            System.out.println("Check-Ins: " + checkIns);
        }
    }

//    public void findMaxCalories() {
//        Query query = entityManager.createQuery("SELECT u FROM User u INNER JOIN Program p ON u=p.client INNER JOIN  GROUP BY u.fName, u.lName, ")
//    }


}
