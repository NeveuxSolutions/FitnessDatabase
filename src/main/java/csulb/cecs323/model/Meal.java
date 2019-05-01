package csulb.cecs323.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames={"mealName"})
)
public class Meal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mealId;
    private String mealName;
    private Timestamp timeEaten;

    //Relations
    @ManyToOne(fetch = FetchType.LAZY)
    private MealPlan mealPlan;

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
}
