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
    private String mealName;
    private Timestamp timeEat;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;
    private double totalCalories;

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

    public Timestamp getTimeEat() {
        return timeEat;
    }

    public void setTimeEat(Timestamp timeEat) {
        this.timeEat = timeEat;
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

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }
}
