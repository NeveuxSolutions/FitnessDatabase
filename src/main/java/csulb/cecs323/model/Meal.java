package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Meal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mealId;
    //@TODO mealname is a ck
    private String mealName;
    private Timestamp timeEaten;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;
    //@TODO should meal calories be derived in uml
    private double mealCalories;

    //@TODO should caloric totals have getters and setters as they are derived?
    public long getMealId() {
        return mealId;
    }

    public void setMealId(long mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Timestamp getTimeEaten() {
        return timeEaten;
    }

    public void setTimeEaten(Timestamp timeEat) {
        this.timeEaten = timeEat;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public double getMealCalories() {
        return mealCalories;
    }

    public void setMealCalories(double totalCalories) {
        this.mealCalories = totalCalories;
    }
}
