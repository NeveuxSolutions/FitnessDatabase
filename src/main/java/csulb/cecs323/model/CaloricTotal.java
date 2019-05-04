package csulb.cecs323.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class CaloricTotal implements Serializable {
    @Transient
    private final int CALORIE_PROTEIN_CARB = 4;
    @Transient
    private final int CALORIE_FAT = 9;
    private int quantity;
    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;

    @Id
    @ManyToOne
    @JoinColumn
    private Food food;

    @Id
    @ManyToOne
    @JoinColumn
    private Meal meal;

    /**
     * Null Constructor
     */
    public CaloricTotal() {}

    /**
     * Overloaded Constructor for setting
     * @param food food object to be assigned to the meal
     * @param meal the meal object that the food is being assigned to
     */
    public CaloricTotal(Food food, Meal meal) {
        this.food = food;
        this.meal = meal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaloricTotal)) return false;
        CaloricTotal that = (CaloricTotal) o;
        return Objects.equals(food.getName(), that.food.getName()) &&
                Objects.equals(meal.getMealName(), that.meal.getMealName()) &&
                Objects.equals(totalCalories, that.totalCalories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food.getName(), meal.getMealName(), totalCalories);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.setTotalProtein();
        this.setTotalCarbs();
        this.setTotalFat();
        this.setTotalCalories();
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories() {
        this.totalCalories = (CALORIE_PROTEIN_CARB * (totalCarbs + totalProtein) + (CALORIE_FAT * totalFat));
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein() {
        this.totalProtein = food.getGramsProtein() * quantity;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs() {
        this.totalCarbs = food.getGramsCarb() * quantity;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat() {
        this.totalFat = food.getGramsFat() * quantity;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
