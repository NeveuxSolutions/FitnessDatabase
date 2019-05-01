package csulb.cecs323.app;

import csulb.cecs323.model.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.util.logging.Logger;

/**
 * Is responsible for creating entries in the Database.
 */
public class EntityCreator {
    private static final Logger LOGGER =  Logger.getLogger(Homework4Application.class.getName());
    private EntityManager entityManager;
    private User[] users = new User[20];

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


}
