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
 * Is responsible for creating entries in the Database.
 */
public class EntityCreator {
    private static final Logger LOGGER =  Logger.getLogger(Homework4Application.class.getName());
    private EntityManager entityManager;

    public EntityCreator(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    /**
     * Method to create a food Entity
     * @param name the name of the food
     * @param protein the amount of protein/gram
     * @param carb the amount of carbs/gram
     * @param fat the amount of fat/gram
     * @param type the type of food classifier (PROTEIN/CARBOHYDRATE/FAT)
     */
    public void createFood(String name, double protein, double carb, double fat, FoodType type) {
        LOGGER.fine("Creating Food Object");
        //General food object
        Food food = new Food();
        food.setName(name);
        food.setGramsProtein(protein);
        food.setGramsCarb(carb);
        food.setGramsFat(fat);
        switch(type) {
            case PROTEIN: food.setFoodType(FoodType.PROTEIN); break;
            case CARBOHYDRATE: food.setFoodType(FoodType.CARBOHYDRATE); break;
            case FAT: food.setFoodType(FoodType.FAT); break;
            default: System.out.println("No matching type found");
        }
        LOGGER.fine("Persisting Food object to DB");
        this.entityManager.persist(food);
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
}
