package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class MealPlan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mealPlanId;
    private String mealPlanName;
    //@TODO sort out meal quantities int enum ??? change to @ElementCollection...Collection<Integer>
    private int numberOfMeals;
    private String dietDescription;

    @Enumerated(EnumType.STRING)
    private DietGoal dietGoal;

    public long getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(long mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public String getMealPlanName() {
        return mealPlanName;
    }

    public void setMealPlanName(String mealPlanName) {
        this.mealPlanName = mealPlanName;
    }

    public int getNumberMeals() {
        return numberOfMeals;
    }

    public void setNumberMeals(int numberMeals) {
        this.numberOfMeals = numberMeals;
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
}
