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

    //@TODO drop student table
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

    /**
     * Creates the initial 20 users in the database
     */
    public void initializeUsers(){
        String[] firstNames = new String[]{"Leon","Erin","Maxim","Giana","Ernesto","Kylan","Mia","Mollie","Keshawn","Fernanda","Harper","Brooks","Ryan","Zachariah","Axel","April","Aidyn","Christine","Khalil","Yoselin"};
        String[] lastNames = new String[]{"Reid","Dorsey","Decker","Downs","Mcclure","Wolf","Carpenter","Pugh","Yoder","Leblanc","Everett","Deleon","Cline","Ellison","Farmer","Ibarra","Jenkins","Fuentes","Foley","House"};
        Gender[] genders = new Gender[]{Gender.MALE, Gender.FEMALE,Gender.MALE,Gender.FEMALE,Gender.MALE,Gender.MALE,Gender.FEMALE,Gender.FEMALE,Gender.MALE,Gender.FEMALE,Gender.MALE,Gender.MALE,Gender.MALE,Gender.MALE,Gender.MALE,Gender.FEMALE,Gender.MALE,Gender.FEMALE,Gender.MALE,Gender.FEMALE};
        String[] phoneNumbers = new String[]{"14192858408","15808949745","13377344905","12342518479","12816447280","18314653819","16672131040","14105739632","12299391177","16052551838","14309694987","14847497190","14088737542","12023335955","17864456938","13473994703","14323371521","13864448344","14353005466","15512007137"};
        int[] ages = new int[]{40,67,33,75,61,74,63,37,42,73,66,86,61,23,31,68,87,39,24,66};
        int[] heights = new int[]{60,62,76,59,72,66,69,80,59,70,65,76,67,66,64,62,65,57,56,57};
        ExperienceLevel[] experience = new ExperienceLevel[]{ExperienceLevel.BEGINNER,ExperienceLevel.BEGINNER,ExperienceLevel.INTERMEDIATE,ExperienceLevel.BEGINNER,ExperienceLevel.ADVANCED,ExperienceLevel.INTERMEDIATE,ExperienceLevel.INTERMEDIATE,ExperienceLevel.INTERMEDIATE,ExperienceLevel.ELITE,ExperienceLevel.BEGINNER,ExperienceLevel.BEGINNER,ExperienceLevel.BEGINNER,ExperienceLevel.INTERMEDIATE,ExperienceLevel.BEGINNER,ExperienceLevel.ADVANCED,ExperienceLevel.INTERMEDIATE,ExperienceLevel.INTERMEDIATE,ExperienceLevel.INTERMEDIATE,ExperienceLevel.ELITE,ExperienceLevel.BEGINNER};

        LOGGER.fine("Creating default users");
        for(int i = 0; i < 20; i++){
            LOGGER.fine("Creating user: " + firstNames[i] + " " + lastNames[i]);
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();

            User user = new User();
            user.setfName(firstNames[i]);
            user.setlName(lastNames[i]);
            user.setGender(genders[i]);
            user.setPhone(phoneNumbers[i]);
            user.setAge(ages[i]);
            user.setHeight(heights[i]);
            user.setUserExperienceLevel(experience[i]);
            users[i] = user; //janky workaround for now

            entityManager.persist(user);
            tx.commit();
            LOGGER.fine("Done creating user: " + firstNames[i] + " " + lastNames[i]);
        }
        LOGGER.fine("Done creating default users");
    }

    /**
     * Loads 20 initial check-ins plus another randomly distributed 20 follow-on check-ins.
     */
    public void initializeCheckins(){
        Timestamp[] timestamps = new Timestamp[]{Timestamp.valueOf("2018-04-19 13:00:00"),Timestamp.valueOf("2016-09-29 07:00:00"),Timestamp.valueOf("2018-03-29 08:00:00"),Timestamp.valueOf("2017-03-22 11:00:00"),Timestamp.valueOf("2017-04-11 01:15:00"),Timestamp.valueOf("2017-07-08 15:00:00"),Timestamp.valueOf("2016-07-16 17:00:00"),Timestamp.valueOf("2017-02-14 10:00:00"),Timestamp.valueOf("2016-02-28 21:00:00"),Timestamp.valueOf("2017-10-25 15:00:00"),Timestamp.valueOf("2018-08-04 06:00:00"),Timestamp.valueOf("2018-06-06 14:30:00"),Timestamp.valueOf("2018-03-27 03:00:00"),Timestamp.valueOf("2018-03-25 12:00:00"),Timestamp.valueOf("2019-03-14 05:00:00"),Timestamp.valueOf("2017-06-16 20:00:00"),Timestamp.valueOf("2017-05-20 19:00:00"),Timestamp.valueOf("2016-04-05 13:00:00"),Timestamp.valueOf("2018-01-25 18:00:00"),Timestamp.valueOf("2016-12-28 20:00:00"),Timestamp.valueOf("2017-12-13 11:00:00"),Timestamp.valueOf("2016-11-14 07:00:00"),Timestamp.valueOf("2017-07-12 19:30:00"),Timestamp.valueOf("2016-02-09 07:00:00"),Timestamp.valueOf("2017-12-12 21:00:00"),Timestamp.valueOf("2016-12-23 19:00:00"),Timestamp.valueOf("2019-03-16 18:00:00"),Timestamp.valueOf("2019-01-01 06:00:00"),Timestamp.valueOf("2017-10-20 14:00:00"),Timestamp.valueOf("2017-05-06 09:00:00"),Timestamp.valueOf("2018-01-25 10:00:00"),Timestamp.valueOf("2016-09-11 10:45:00"),Timestamp.valueOf("2016-10-28 01:00:00"),Timestamp.valueOf("2016-07-13 09:00:00"),Timestamp.valueOf("2016-03-28 21:00:00"),Timestamp.valueOf("2018-04-07 11:00:00"),Timestamp.valueOf("2017-02-24 06:30:00"),Timestamp.valueOf("2017-05-09 09:00:00"),Timestamp.valueOf("2017-09-28 18:00:00"),Timestamp.valueOf("2017-05-01 09:00:00")};
        double[] fatPercentages = new double[]{24,18,15,22,21.1,20,19,19,25,15,15,19,17.3,5,11,15,20,9,9,14.5,5,22,15,15,18,25.1,13,18,6,6,24,8,19,15,9,13,14.2,25,14,5};
        double[] weights = new double[]{149,191,198,167,210,202,179,169,209,209,104,204,163,162,151,107,128,161,179,124,171,139,188,208,132,173,204,193,201,167,139,123,106,164,188,192,123,123,189,204};
        int[] clientsIds = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,12,17,4,19,18,4,8,12,14,10,17,4,19,17,10,18,11,13,4,13};

        LOGGER.fine("Creating default check-ins");
        for(int i = 0; i < 40; i++){
            LOGGER.fine("Creating check-in: " + timestamps[i]);
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();

            CheckIn checkIn = new CheckIn();
            checkIn.setBodyFat(fatPercentages[i]);
            checkIn.setCheckInTimeStamp(timestamps[i]);
            checkIn.setWeight(weights[i]);
            users[clientsIds[i]].addCheckIn(checkIn);
            checkIn.setUserId(users[clientsIds[i]]);

            entityManager.persist(checkIn);
            tx.commit();
            LOGGER.fine("Done creating check-in: " + timestamps[i]);
        }
        LOGGER.fine("Done creating default check-ins");
    }

    /**
     * Loads 10 default workouts into the database.
     */
    public void initializeWorkouts(){
        LOGGER.fine("Creating default workouts");

        LOGGER.fine("Done creating default workouts");
    }
}
