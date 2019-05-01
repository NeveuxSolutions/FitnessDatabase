package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CaloricTotal {
    @Id private int foodId;
    @Id private int mealId;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
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
