package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CaloricTotal {
    @Id
    private int foodId;
    @Id
    private int mealId;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }
//@TODO  is quantity in the right place
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
