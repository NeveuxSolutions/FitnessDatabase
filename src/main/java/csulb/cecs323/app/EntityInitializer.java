package csulb.cecs323.app;

import csulb.cecs323.model.*;
import org.omg.PortableInterceptor.DISCARDING;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.sql.Date;
import static csulb.cecs323.model.CardioActivity.*;
import static csulb.cecs323.model.CardioType.*;
import static csulb.cecs323.model.ProgramGoal.*;
import static csulb.cecs323.model.Status.*;

import java.util.Random;
import java.util.logging.Logger;

public class EntityInitializer {
    private static final Logger LOGGER =  Logger.getLogger(Homework4Application.class.getName());
    private EntityManager entityManager;
    private User[] users = new User[20];
    private Food[] foods = new Food[20];
    private Cardio[] cardios = new Cardio[20];
    private Routine[] routines = new Routine[20];
    private Workout[] workouts = new Workout[20];
    private Exercise[] exercises = new Exercise[20];
    private MealPlan[] mealPlans = new MealPlan[20];
    private Meal[] meal_list = new Meal[20];
    Program[] programs = new Program[20];

    public EntityInitializer(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    /**
     * Creates the initial 20 users in the database
     */
    public void initializeUsers(){
        String[] firstNames = {
                "Leon","Erin","Maxim","Giana","Ernesto","Kylan",
                "Mia","Mollie","Keshawn","Fernanda","Harper","Brooks",
                "Ryan","Zachariah","Axel","April","Aidyn","Christine","Khalil","Yoselin"};

        String[] lastNames = {
                "Reid","Dorsey","Decker","Downs","Mcclure","Wolf","Carpenter","Pugh",
                "Yoder","Leblanc","Everett","Deleon","Cline","Ellison","Farmer","Ibarra",
                "Jenkins","Fuentes","Foley","House"};

        Gender[] genders = {
                Gender.MALE, Gender.FEMALE,Gender.MALE,Gender.FEMALE,
                Gender.MALE,Gender.MALE,Gender.FEMALE,Gender.FEMALE,
                Gender.MALE,Gender.FEMALE,Gender.MALE,Gender.MALE,
                Gender.MALE,Gender.MALE,Gender.MALE,Gender.FEMALE,
                Gender.MALE,Gender.FEMALE,Gender.MALE,Gender.FEMALE};

        String[] phoneNumbers = {
                "14192858408","15808949745","13377344905","12342518479","12816447280",
                "18314653819","16672131040","14105739632","12299391177","16052551838",
                "14309694987","14847497190","14088737542","12023335955","17864456938",
                "13473994703","14323371521","13864448344","14353005466","15512007137"};

        int[] ages = {40,67,33,75,61,74,63,37,42,73,66,86,61,23,31,68,87,39,24,66};
        int[] heights = {60,62,76,59,72,66,69,80,59,70,65,76,67,66,64,62,65,57,56,57};

        ExperienceLevel[] experience = {
                ExperienceLevel.BEGINNER,ExperienceLevel.BEGINNER,ExperienceLevel.INTERMEDIATE,
                ExperienceLevel.BEGINNER,ExperienceLevel.ADVANCED,ExperienceLevel.INTERMEDIATE,
                ExperienceLevel.INTERMEDIATE,ExperienceLevel.INTERMEDIATE,ExperienceLevel.ELITE,
                ExperienceLevel.BEGINNER,ExperienceLevel.BEGINNER,ExperienceLevel.BEGINNER,
                ExperienceLevel.INTERMEDIATE,ExperienceLevel.BEGINNER,ExperienceLevel.ADVANCED,
                ExperienceLevel.INTERMEDIATE,ExperienceLevel.INTERMEDIATE,ExperienceLevel.INTERMEDIATE,
                ExperienceLevel.ELITE,ExperienceLevel.BEGINNER};

        LOGGER.fine("Creating default users");
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        for(int i = 0; i < 20; i++){
            User user = new User();
            user.setfName(firstNames[i]);
            user.setlName(lastNames[i]);
            user.setGender(genders[i]);
            user.setPhone(phoneNumbers[i]);
            user.setAge(ages[i]);
            user.setHeight(heights[i]);
            user.setUserExperienceLevel(experience[i]);
            users[i] = user;
            entityManager.persist(user);
        }

        tx.commit();
        LOGGER.fine("Done creating default users");
    }

    /**
     * Loads 20 initial check-ins plus another randomly distributed 20 follow-on check-ins.
     */
    public void initializeCheckins(){
        Timestamp[] timestamps = {
                Timestamp.valueOf("2018-04-19 13:00:00"),Timestamp.valueOf("2016-09-29 07:00:00"),
                Timestamp.valueOf("2018-03-29 08:00:00"),Timestamp.valueOf("2017-03-22 11:00:00"),
                Timestamp.valueOf("2017-04-11 01:15:00"),Timestamp.valueOf("2017-07-08 15:00:00"),
                Timestamp.valueOf("2016-07-16 17:00:00"),Timestamp.valueOf("2017-02-14 10:00:00"),
                Timestamp.valueOf("2016-02-28 21:00:00"),Timestamp.valueOf("2017-10-25 15:00:00"),
                Timestamp.valueOf("2018-08-04 06:00:00"),Timestamp.valueOf("2018-06-06 14:30:00"),
                Timestamp.valueOf("2018-03-27 03:00:00"),Timestamp.valueOf("2018-03-25 12:00:00"),
                Timestamp.valueOf("2019-03-14 05:00:00"),Timestamp.valueOf("2017-06-16 20:00:00"),
                Timestamp.valueOf("2017-05-20 19:00:00"),Timestamp.valueOf("2016-04-05 13:00:00"),
                Timestamp.valueOf("2018-01-25 18:00:00"),Timestamp.valueOf("2016-12-28 20:00:00"),
                Timestamp.valueOf("2017-12-13 11:00:00"),Timestamp.valueOf("2016-11-14 07:00:00"),
                Timestamp.valueOf("2017-07-12 19:30:00"),Timestamp.valueOf("2016-02-09 07:00:00"),
                Timestamp.valueOf("2017-12-12 21:00:00"),Timestamp.valueOf("2016-12-23 19:00:00"),
                Timestamp.valueOf("2019-03-16 18:00:00"),Timestamp.valueOf("2019-01-01 06:00:00"),
                Timestamp.valueOf("2017-10-20 14:00:00"),Timestamp.valueOf("2017-05-06 09:00:00"),
                Timestamp.valueOf("2018-01-25 10:00:00"),Timestamp.valueOf("2016-09-11 10:45:00"),
                Timestamp.valueOf("2016-10-28 01:00:00"),Timestamp.valueOf("2016-07-13 09:00:00"),
                Timestamp.valueOf("2016-03-28 21:00:00"),Timestamp.valueOf("2018-04-07 11:00:00"),
                Timestamp.valueOf("2017-02-24 06:30:00"),Timestamp.valueOf("2017-05-09 09:00:00"),
                Timestamp.valueOf("2017-09-28 18:00:00"),Timestamp.valueOf("2017-05-01 09:00:00")};

        double[] fatPercentages = {
                24,18,15,22,21.1,20,19,19,25,15,15,19,17.3,5,11,15,20,9,9,
                14.5, 5,22,15,15,18,25.1,13,18,6,6,24,8,19,15,9,13,14.2,25,14,5};

        double[] weights = {
                149,191,198,167,210,202,179,169,209,209,104,204,163,162,151,107,128,161,179,124,171,139,
                188,208,132,173,204,193,201,167,139,123,106,164,188,192,123,123,189,204};

        int[] clientsIds = {
                0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
                12,17,4,19,18,4,8,12,14,10,17,4,19,17,10,18,11,13,4,13};

        LOGGER.fine("Creating default check-ins");
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        for(int i = 0; i < 40; i++){
            CheckIn checkIn = new CheckIn();
            checkIn.setBodyFat(fatPercentages[i]);
            checkIn.setCheckInTimeStamp(timestamps[i]);
            checkIn.setWeight(weights[i]);
            users[clientsIds[i]].addCheckIn(checkIn);
            checkIn.setUserId(users[clientsIds[i]]);
            entityManager.persist(checkIn);
        }

        tx.commit();
        LOGGER.fine("Done creating default check-ins");
    }

    /**
     * Method to add foods to the database
     */
    public void initializeFood() {
        LOGGER.fine("Creating Food Table");
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        String[] food_list =
                {"Chicken Breast", "Eggs", "Almonds", "Oats", "Cottage Cheese",
                "Greek Yogurt", "Milk", "Broccoli", "Lean Beef", "Tuna",
                "Quinoa", "Whey Protein", "Ground Turkey", "Salmon", "Shrimp",
                "Brussels Sprout", "Cashews"};

        double[] fat = {0.03, .11, .49, .01, .04, .00, .01, .00, .15, .00, .01, .01, .1, .13, 0.3, .00, .44};
        double[] carb = {0.00, 0.01, .22, .12, .03, .03, .05, .07, .00, .00, .21, .06, .00, .00, .02, .09, .30};
        double[] protein = {.31, .13, .21, .02, .11, .1, .03, .03, .26, .25, .04, .75, .27, .20, .24, .03, .18};


        for (int i = 0; i < food_list.length; i++) {
            //Create Food
            Food food = new Food();
            food.setName(food_list[i]);
            food.setGramsProtein(protein[i]);
            food.setGramsCarb(carb[i]);
            food.setGramsFat(fat[i]);
            foods[i] = food;

            //Persist in database
            entityManager.persist(food);
        }

        tx.commit();
        LOGGER.fine("Done Creating Food Table");
    }

    /**
     * Method to initialize meals to the database
     */
    public void initializeMeal() {
        LOGGER.fine("Creating Meal Table");
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        String[] meals = {
                "Chicken Breast/Broccoli/Almonds", "Chicken Breast/Brussels Sprout", "Lean Beef/Quinoa",
                "Eggs/Oats/Milk", "Ground Turkey/Broccoli", "Salmon/Cashews", "Tuna/Cottage Cheese", "Shrimp/Brussels Sprout",
                "Eggs/Oats", "Salmon/Broccoli", "Chicken Breast/Cottage Cheese", "Shrimp/Cottage Cheese", "Lean Beef/Broccoli",
                "Ground Turkey/Almonds", "Chicken Breast/Broccoli", "Salmon/Cottage Cheese", "Chicken Breast/Quinoa", "Eggs/Cashews",
                "Salmon/Almonds", "Lean Beef/Almonds"};

        Timestamp[] timestamps = {
                Timestamp.valueOf("2018-04-19 13:00:00"),Timestamp.valueOf("2016-09-29 07:00:00"),
                Timestamp.valueOf("2018-03-29 08:00:00"),Timestamp.valueOf("2017-03-22 11:00:00"),
                Timestamp.valueOf("2017-04-11 01:15:00"),Timestamp.valueOf("2017-07-08 15:00:00"),
                Timestamp.valueOf("2016-07-16 17:00:00"),Timestamp.valueOf("2017-02-14 10:00:00"),
                Timestamp.valueOf("2016-02-28 21:00:00"),Timestamp.valueOf("2017-10-25 15:00:00"),
                Timestamp.valueOf("2018-08-04 06:00:00"),Timestamp.valueOf("2018-06-06 14:30:00"),
                Timestamp.valueOf("2018-03-27 03:00:00"),Timestamp.valueOf("2018-03-25 12:00:00"),
                Timestamp.valueOf("2019-03-14 05:00:00"),Timestamp.valueOf("2017-06-16 20:00:00"),
                Timestamp.valueOf("2017-05-20 19:00:00"),Timestamp.valueOf("2016-04-05 13:00:00"),
                Timestamp.valueOf("2018-01-25 18:00:00"),Timestamp.valueOf("2016-12-28 20:00:00")
        };

        // Iterate through all the meals
        for (int i = 0; i < meals.length; i++) {
            //Create a new meal
            Meal meal = new Meal();
            meal.setMealName(meals[i]);
            meal.setTimeEaten(timestamps[i]);

            //Get a single meal and tokenize the words
            String[] food_list = meals[i].split("\\/");

            for (int j = 0; j < food_list.length; j++){
                // Get that food from the database
                Food food = getFood(food_list[j]);

                // Assign the food to the meal
                CaloricTotal caloricTotal = new CaloricTotal(food, meal);

                //Set amount
                Random rand = new Random();
                int amount = rand.nextInt(200) + 50;
                caloricTotal.setQuantity(amount);
                meal.addCaloricTotal(caloricTotal);
                meal.setMealCalories();
                entityManager.persist(meal);
                entityManager.persist(caloricTotal);
            }
            meal_list[i] = meal;
        }

        tx.commit();
        LOGGER.fine("Done Creating Meal Table");
    }

    public void initializeMealPlan() {
        LOGGER.fine("Creating MealPlan Table");
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        String[] mealPlanName = {
                "Meal plan for swimmers", "Bodybuilding Prep",
                "NFL Strength Meals", "Rehabilitation",
                "Contest Prep", "Triathalon High Carb",
                "Low Cal Endurance Plan", "Strength for Gymnastics",
                "Youth Strength", "Rehabilitation for acl",
                "Special Needs Endurance", "Swimming Endurance",
                "Med Carb Endurance", "High protein Youth",
                "High Calorie Impact Plan", "High Carb Endurance",
                "Low Calorie mobility", "High Calorie Protein/Carb",
                "Strength Training High Protein", "Fat Loss Low Carb"};

        String[] dietDescription = {
                "A meal plan for swimmers", "A bodybuilding prep meal plan",
                "NFL players meal plan", "A meal plan for rehabilitation",
                "A contest prep meal plan for bodybuilders", "Triathalon meal plan",
                "Low calorie endurance meal plan", "Strength meal plan for gymnastics",
                "Meal plan for youth", "Rehabilitation for acl tear meal plan",
                "Special needs meal plan", "Endurance meal plan for swimming",
                "Carb endurance meal plan", "High protein meal plan for youth",
                "High calorie meal plan for impact training", "High carb for endurance training",
                "Low calorie for mobility meal plan", "High calorie protein/carb meal plan",
                "Strength training high protein meal plan", "Fat loss meal plan with low carbs"
        };

        DietGoal[] dietGoals = {
                DietGoal.MAINTENANCE, DietGoal.FAT_LOSS, DietGoal.BULKING,
                DietGoal.FAT_LOSS, DietGoal.FAT_LOSS, DietGoal.BULKING,
                DietGoal.FAT_LOSS, DietGoal.BULKING, DietGoal.MAINTENANCE,
                DietGoal.BULKING, DietGoal.FAT_LOSS, DietGoal.MAINTENANCE,
                DietGoal.MAINTENANCE, DietGoal.MAINTENANCE, DietGoal.FAT_LOSS,
                DietGoal.BULKING, DietGoal.MAINTENANCE, DietGoal.FAT_LOSS,
                DietGoal.FAT_LOSS, DietGoal.BULKING
        };

        //@TODO This is trash. Just trying to test
        for (int i = 0; i < users.length; i++) {

            //Create Meal Plan Object
            MealPlan mealPlan = new MealPlan();
            mealPlan.setMealPlanName(mealPlanName[i]);
            mealPlan.setDietDescription(dietDescription[i]);
            mealPlan.setDietGoal(dietGoals[i]);

            //Assign random number of meals to mealplan
            Random rand = new Random();
            int numMeals = rand.nextInt(6) + 1;
            for (int j = 0; j < numMeals; j++) {
                Meal meal = meal_list[j];
                meal.assignToMealPlan(mealPlan);
                mealPlan.setMeal(meal);
            }

            mealPlan.setNumberOfMeals();
            mealPlans[i] = mealPlan;
            //Assign the meal to the meal plan
            entityManager.persist(mealPlan);
        }

        tx.commit();
        LOGGER.fine("Done Creating MealPlan Table");
    }

    /**
     * Method that adds initial routines to the database
     */
    public void initializeRoutines(){
        String[] names = {
                "Strength training for swimmers", "Body building contest preparation",
                "NFL combine preparation", "Hip rehabilitation with strength focus",
                "Body building contest preparation", "Triathalon endurance preparation",
                "NHL off-season general programming", "Strength training for senior citizens",
                "Agility and endurance for curling amateurs", "Gymnastic strength programming",
                "General youth strength training", "Post acl-tear rehabilitation",
                "Endurance training for special needs athletes", "Pool centric endurance training",
                "Offseason rock climbing strength program", "Generic mobility and strength routine",
                "Amateur duathalon endurance training", "Low impact strength training for youths",
                "High impact, high endurance program for elite track athletes", "Mobility program for desk workers"};

        String[] descriptions = {
                "Designed for the CSULB womens swim team", "For serious competitors only",
                "Focuses on lower body development", "Full squat depth is a pre-requisite",
                "For less serious body builders", "Created for full triathalons",
                "Assumes sport specific training is done in parallel", "User should have the ability to sit/stand",
                "Designed for Canadians only", "Specialized for those not taller than 5'8",
                "Best for those under the age of 10", "Type 3 tears only", "Requires special coaching",
                "Pool of depth 10+ feet required", "Does not include lower body training", "For all age groups",
                "Created for cold climates", "Gender neutral", "Sprint focused",
                "45 degrees of hip flexion and extension are prerequisites"};

        TrainingStyle[] styles = {
                TrainingStyle.STRENGTH, TrainingStyle.BODYBUILDING, TrainingStyle.SPORT_SPECIFIC,
                TrainingStyle.STRENGTH, TrainingStyle.BODYBUILDING, TrainingStyle.ENDURANCE,
                TrainingStyle.SPORT_SPECIFIC, TrainingStyle.STRENGTH, TrainingStyle.SPORT_SPECIFIC,
                TrainingStyle.SPORT_SPECIFIC, TrainingStyle.STRENGTH, TrainingStyle.STRENGTH,
                TrainingStyle.ENDURANCE, TrainingStyle.ENDURANCE, TrainingStyle.STRENGTH,
                TrainingStyle.STRENGTH, TrainingStyle.ENDURANCE, TrainingStyle.STRENGTH,
                TrainingStyle.SPORT_SPECIFIC, TrainingStyle.STRENGTH};

        LOGGER.fine("Creating Routines Table");
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        for (int i = 0; i < 20; i++) {
            Routine routine = new Routine(names[i]);
            routine.setRoutineDescription(descriptions[i]);
            routine.setTrainingStyle(styles[i]);

            routines[i] = routine;
            entityManager.persist(routine);
        }

        tx.commit();
        LOGGER.fine("Done Creating Routine Table");
    }
    /**
     * Method that adds initial workouts to the database
     */
    public void initializeWorkouts(){
        String[] workoutDescriptions = {
                "Low intensity recovery", "High intensity upper body strength",
                "Aerobic training for heavy atheletes", "General Lower Body strength",
                "Upper body pressing", "Back and arm centric", "Powerlifting meet simulation",
                "Isometric centered upper body strength", "Mobility centric with light lower body",
                "Generic low impact cross training", "Arm blaster", "Core development",
                "High volume shoulder centric", "Giant sets for lower body",
                "Low volume fast tempo compound lifts", "Upper back concentrics",
                "Fartlek run with post-run mobility", "200m sprints on the minute every minute",
                "Lower back release", "Gymnastic rings introduction"};

        int[] days = new int[]{1, 4, 3, 7, 4, 6, 3, 4, 2, 5, 1, 5, 7, 4, 2, 4, 6, 3, 7, 3};
        int[] startTime = new int[]{9, 12, 12, 9, 21, 6, 6, 12, 14, 15, 16, 6, 12, 13, 15, 10, 11, 13, 14, 13};

        LOGGER.fine("Creating Workout Table");
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        for (int i = 0; i < 20; i++) {
            Workout workout = new Workout(workoutDescriptions[i], days[i], routines[i]);
            workout.setWorkoutStartTime(startTime[i]);
            entityManager.persist(workout);
            workouts[i] = workout;
        }

        tx.commit();
        LOGGER.fine("Done Creating Workout Table");

        //assign workouts to routines
        for(Routine r : routines){
            int i = r.getRoutineId();
            if(r.getRoutineId() < 10){
                r.addWorkout(workouts[i+1]);
                r.addWorkout(workouts[i+9]);
                r.addWorkout(workouts[i+4]);
                r.addWorkout(workouts[i+3]);
                r.addWorkout(workouts[i+7]);
            }
            if(i >= 10){
                r.addWorkout(workouts[i-1]);
                r.addWorkout(workouts[i-9]);
                r.addWorkout(workouts[i-4]);
                r.addWorkout(workouts[i-3]);
                r.addWorkout(workouts[i-7]);
            }
            for(Workout w : r.getWorkouts()){
                w.getRoutines().add(r);
            }
        }
    }
    /**
     * Method that adds initial exercises to the database
     */
    public void initializeExercises(){
        String[] exerciseNames = {
                "Squat","Lunge","Deadlift","Calf Raise","Chest Fly", "Pull up",
                "Sit up","Push up","Back Extension","Bicep Curl","Bench Press",
                "Overhead Press","Snatch","Clean and Jerk","Skull Crusher","Dip",
                "Bent over row","Hamstring Curl","Leg extension","Leg Press"};

        BodyPart[] muscles = {
                BodyPart.QUADS,BodyPart.HAMSTRINGS,BodyPart.HAMSTRINGS,BodyPart.CALVES,
                BodyPart.CHEST,BodyPart.BACK,BodyPart.ABS,BodyPart.SHOULDERS,
                BodyPart.BACK,BodyPart.BICEP,BodyPart.CHEST,BodyPart.SHOULDERS,
                BodyPart.QUADS, BodyPart.TRAPS, BodyPart.TRICEP, BodyPart.CHEST,
                BodyPart.BACK, BodyPart.HAMSTRINGS, BodyPart.QUADS, BodyPart.QUADS};

        ExerciseType[] exerciseType = {
                ExerciseType.FREEWEIGHT,ExerciseType.BODYWEIGHT,ExerciseType.FREEWEIGHT,
                ExerciseType.MACHINE,ExerciseType.FREEWEIGHT,ExerciseType.BODYWEIGHT,
                ExerciseType.BODYWEIGHT,ExerciseType.BODYWEIGHT,ExerciseType.BODYWEIGHT,
                ExerciseType.FREEWEIGHT,ExerciseType.FREEWEIGHT,ExerciseType.FREEWEIGHT,
                ExerciseType.FREEWEIGHT,ExerciseType.FREEWEIGHT,ExerciseType.FREEWEIGHT,
                ExerciseType.BODYWEIGHT,ExerciseType.FREEWEIGHT,ExerciseType.MACHINE,
                ExerciseType.MACHINE,ExerciseType.MACHINE};

        Tempo[] tempos = {
                Tempo.CONCENTRICEMPHASIS,Tempo.STRETCHCONTRACT,Tempo.STRETCHCONTRACT,Tempo.CONSTANTTENSION,
                Tempo.CONCENTRICEMPHASIS,Tempo.CONCENTRICEMPHASIS,Tempo.STRETCHCONTRACT,Tempo.STRETCHCONTRACT,
                Tempo.CONSTANTTENSION,Tempo.CONCENTRICEMPHASIS,Tempo.CONCENTRICEMPHASIS,Tempo.STRETCHCONTRACT,
                Tempo.STRETCHCONTRACT,Tempo.CONSTANTTENSION,Tempo.CONCENTRICEMPHASIS,Tempo.CONCENTRICEMPHASIS,
                Tempo.STRETCHCONTRACT,Tempo.STRETCHCONTRACT,Tempo.CONSTANTTENSION,Tempo.CONCENTRICEMPHASIS,};

        int[] reps = {35,7,12,23,8,4,2,6,10,11,15,3,5,17,4,2,8,12,12,6};
        int[] sets = {2,6,3,5,4,10,6,6,9,5,9,4,7,7,5,12,5,4,8,6};

        LOGGER.fine("Creating Exercise Table");
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        for (int i = 0; i < 20; i++) {
            Exercise exercise = new Exercise();

            exercise.setExerciseName(exerciseNames[i]);
            exercise.setBodyPart(muscles[i]);
            exercise.setExerciseType(exerciseType[i]);
            exercise.setTempo(tempos[i]);
            exercise.setRepCount(reps[i]);
            exercise.setSetCount(sets[i]);
          //  exercise.addWorkout(workouts[i]);

            exercises[i] = exercise;
            entityManager.persist(exercise);
        }

        //assign 5 exercises to each workout
        for(Workout w : workouts){
            int i = w.getWorkoutId();
            if(w.getWorkoutId() < 10){
                w.addExercise(exercises[i+1]);
                w.addExercise(exercises[i+9]);
                w.addExercise(exercises[i+4]);
                w.addExercise(exercises[i+3]);
                w.addExercise(exercises[i+7]);
            }
            if(i >= 10){
                w.addExercise(exercises[i-1]);
                w.addExercise(exercises[i-9]);
                w.addExercise(exercises[i-4]);
                w.addExercise(exercises[i-3]);
                w.addExercise(exercises[i-7]);
            }
            for(Exercise e : w.getExercises()){
                e.getWorkouts().add(w);
            }
        }

        tx.commit();
        LOGGER.fine("Done Creating Exercise Table");
    }

    /**
     * Method that adds initial cardio activities to the database
     */
    public void initializeCardio(){
        CardioActivity[] activity = {
                CardioActivity.STAIRMASTER, CardioActivity.BIKE, CardioActivity.RUN,
                CardioActivity.SWIM, CardioActivity.ELLIPTICAL,CardioActivity.RUN,
                CardioActivity.RUN,CardioActivity.SWIM, BIKE, RUN,CardioActivity.STAIRMASTER,
                CardioActivity.BIKE, CardioActivity.RUN, CardioActivity.SWIM, CardioActivity.ELLIPTICAL,
                CardioActivity.RUN,CardioActivity.RUN,CardioActivity.SWIM, BIKE, RUN};

        CardioType[] types = {
                GPP, LISS, HIIT, LISS, GPP, HIIT, LISS, GPP, HIIT,
                LISS, GPP, LISS, HIIT, LISS, GPP, HIIT, LISS, GPP, HIIT, LISS};

        int[] durations = {20,50,15,40,40,14,43,36,25,55,22,52,16,41,47,13,57,31,22,59};

        LOGGER.fine("Creating Cardio Table");
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        for (int i = 0; i < 20; i++) {
            Cardio cardio = new Cardio();

            cardio.setCardioType(types[i]);
            cardio.setDuration(durations[i]);
            cardio.setCardioActivity(activity[i]);
            cardio.addWorkout(workouts[i]);
            workouts[i].addCardio(cardio);

            entityManager.persist(cardio);
        }

        tx.commit();
        LOGGER.fine("Done Creating Cardio Table");
    }
    /**
     * Method that adds initial programs to the database
     */
    public void initializePrograms(){
        String[] startDates = {
                "2017-01-04","2017-12-07","2017-09-08","2017-12-21","2017-06-26",
                "2018-03-03","2017-01-07","2017-09-15","2018-05-02","2017-04-21",
                "2018-02-25","2018-02-03","2017-05-21","2017-11-04","2018-08-13",
                "2018-04-02","2017-05-06","2018-07-17","2018-03-24","2018-07-04"};

        String[] endDates = {
                "2018-10-02","2019-02-21","2019-03-24","2019-01-26","2018-12-06",
                "2018-11-04","2019-04-19","2019-04-21","2019-03-24","2018-12-19",
                "2019-02-15","2019-02-19","2018-12-05","2018-11-13","2018-11-15",
                "2018-10-01","2019-03-14","2019-02-22","2018-12-28","2018-10-27"};

        String[] programDescriptions = {
                "A weight loss program focusing endurance","A bulking program with a strength emphasis",
                "A mobility program with minimal strength training and a calorie deficit",
                "A general endurance training program for clients with high caloric needs",
                "Short term muscle building program with calorie surplus","Total body strength with vegan diet",
                "A cutting program with an upper body developmental emphasis","Diabetic endurance program",
                "Sugar sensitive bulking program","Hard gainer mass development","Mobility for those with anemia",
                "Youth, basic training and dieting","Long term vegan mobility training",
                "Triathalete endurance and strength program with calorie surplus","Six pack development",
                "Quad size enhancement with calorie deficit","Special needs strength training and calorie maintenance",
                "High mileage endurance program with high carb emphasis","Low impact strength training with paleo diet",
                "General strength program for those with peanut allergies"};

        ProgramGoal[] goals = {
                FATLOSS, MUSCLE, STRENGTH, ENDURANCE, MUSCLE,
                STRENGTH, FATLOSS, ENDURANCE,MUSCLE,STRENGTH,
                STRENGTH,STRENGTH,ENDURANCE,FATLOSS,MUSCLE,
                STRENGTH,ENDURANCE,STRENGTH,STRENGTH, STRENGTH};

        Status[] statuses = {
                COMPLETED, WITHDRAWN, COMPLETED,WITHDRAWN,COMPLETED,
                COMPLETED,INPROGRESS,INPROGRESS,COMPLETED,COMPLETED,
                COMPLETED, WITHDRAWN, COMPLETED,WITHDRAWN,COMPLETED,
                COMPLETED,INPROGRESS,INPROGRESS,COMPLETED,COMPLETED};

        LOGGER.fine("Creating Program Table");
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        for (int i = 0; i < 20; i++) {
            Program program = new Program();

            program.setStartDate(Date.valueOf(startDates[i]));
            program.setEndDate(Date.valueOf(endDates[i]));
            program.setProgramDescription(programDescriptions[i]);
            program.setProgramGoal(goals[i]);
            program.setClient(users[i]);
            program.setStatus(statuses[i]);
            program.setMealPlan(mealPlans[i]);
            program.setRoutine(routines[i]);
            routines[i].setProgram(program);

            programs[i] = program;
            entityManager.persist(program);
        }

        //adjust for programs still in-progress
        programs[7].setEndDate(null);
        programs[6].setEndDate(null);
        programs[17].setEndDate(null);
        programs[16].setEndDate(null);

        tx.commit();
        LOGGER.fine("Done Creating Program Table");
    }

    //--------------------------------------------------------
    // Private Query methods to pull data from static tables
    //--------------------------------------------------------

    /**
     * Query to get a single food by given name.
     * @param name the name of the food to be retrieved
     * @return the food found
     */
    private Food getFood(String name) {
        Query query = entityManager.createQuery("SELECT f " + "FROM Food f " + "WHERE f.name LIKE :name");
        return (Food) query.setParameter("name", name).getSingleResult();
    }

    /**
     * Query to get a single meal
     * @param meal the name of the meal
     * @return the meal found
     */
    private Meal getMeal(String meal) {
        Query query = entityManager.createQuery("SELECT m FROM Meal m WHERE m.mealName LIKE :meal");
        return (Meal) query.setParameter("meal", meal).getSingleResult();
    }

    /**
     * Query to get a user by specified name
     * @param fName the first name of the user
     * @param lName the last name of the user
     * @return the retrieved user
     */
    private User getUser(String fName, String lName) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.fName LIKE :fName AND u.lName LIKE :lName");
        return (User) query.setParameter("fName", fName).setParameter("lName", lName).getSingleResult();
    }
}
