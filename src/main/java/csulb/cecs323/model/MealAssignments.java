package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MealAssignments {
    @Id
    private int mealId;
    @Id
    private int mealPlanId;
}
