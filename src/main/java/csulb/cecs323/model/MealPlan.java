package csulb.cecs323.model;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<Meal> meals = new HashSet<>();

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

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }
}
