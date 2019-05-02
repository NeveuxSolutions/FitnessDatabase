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

    public CaloricTotal() {}

    public CaloricTotal(Food food, Meal meal) {
        this.food = food;
        this.meal = meal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setTotalProtein() {
        this.totalProtein = this.quantity * this.food.getGramsProtein();
    }

    public void setTotalCarbs() {
        this.totalCarbs = this.quantity * this.food.getGramsCarb();
    }

    public void setTotalFat() {
        this.totalFat = this.quantity * this.food.getGramsFat();
    }

    public void setTotalCalories() {
        this.totalCalories = ((this.totalProtein + this.totalCarbs) * CALORIE_PROTEIN_CARB) + (this.totalFat * CALORIE_FAT);
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
}
