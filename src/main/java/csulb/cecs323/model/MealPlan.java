package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class MealPlan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mealPlanId;
    private String mealPlanName;
    private int numberOfMeals;
    private String dietDescription;

    @Enumerated(EnumType.STRING)
    private DietGoal dietGoal;

    @ManyToMany(mappedBy = "mealPlans")
    private List<Meal> meals = new ArrayList<>();

    @OneToOne(mappedBy = "mealPlan")
    private Program program;

    /**
     * Null Constructor
     */
    public MealPlan() {}

    /**
     * Overloaded constructor
     * @param mealPlanName name of meal plan
     */
    public MealPlan(String mealPlanName) {
        this.mealPlanName = mealPlanName;
    }

    // GETTERS/SETTERS
    public String getMealPlanName() {
        return mealPlanName;
    }

    public void setMealPlanName(String mealPlanName) {
        this.mealPlanName = mealPlanName;
    }

    public int getNumberOfMeals() {
        return numberOfMeals;
    }

    public void setNumberOfMeals(int numberOfMeals) {
        this.numberOfMeals = numberOfMeals;
    }

    public String getDietDescription() {
        return dietDescription;
    }

    public void setDietDescription(String dietDescription) {
        this.dietDescription = dietDescription;
    }

    public DietGoal getDietGoal() {
        return dietGoal;
    }

    public void setDietGoal(DietGoal dietGoal) {
        this.dietGoal = dietGoal;
    }


    public void setMeal(Meal meal) { this.meals.add(meal); }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
