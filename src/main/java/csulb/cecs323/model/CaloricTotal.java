package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class CaloricTotal {
    @Id private int foodId;
    @Id private int mealId;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "foodId", referencedColumnName = "foodId")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    private Meal meal;

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

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
