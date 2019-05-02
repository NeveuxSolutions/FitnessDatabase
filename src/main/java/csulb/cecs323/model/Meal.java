package csulb.cecs323.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(mappedBy = "meal")
    private Set<CaloricTotal> caloricTotals = new HashSet<>();


    public void Meal() {}

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

//    public MealPlan getMealPlan() {
//        return mealPlan;
//    }
//
//    public void setMealPlan(MealPlan mealPlan) {
//        this.mealPlan = mealPlan;
//    }
}
