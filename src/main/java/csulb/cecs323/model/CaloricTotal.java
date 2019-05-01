package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class CaloricTotal {
    @Id private int foodId;
    @Id private int mealId;
    private final int CALORIE_PROTEIN_CARB = 4;
    private final int CALORIE_FAT = 9;
    private int quantity;
    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;


    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "foodId", referencedColumnName = "foodId")
    private Food food;


    public int getQuantity() {
        return quantity;
    }
//@TODO  is quantity in the right place?
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

}
