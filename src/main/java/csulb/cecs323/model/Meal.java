package csulb.cecs323.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
//@Table(
//    uniqueConstraints = @UniqueConstraint(columnNames={"mealName"})
//)
public class Meal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mealId;
    private String mealName;
    private Timestamp timeEaten;
    private double mealCalories;

    //Relations
    @OneToMany(mappedBy = "meal")
    private Set<CaloricTotal> caloricTotals = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MEALASSIGNMENTS", joinColumns = @JoinColumn(name = "mealId", referencedColumnName = "mealId"),
    inverseJoinColumns = @JoinColumn(name = "mealPlanId", referencedColumnName = "mealPlanId"))
    private List<MealPlan> mealPlans;

    /**
     * Null Constructor
     */
    public Meal() {}

    /**
     * Overloaded Constructor
     * @param mealName Name of the meal
     * @param mealPlans the mealPlan the meal is being assigned to
     */
    public Meal(String mealName, MealPlan mealPlans) {
        this.mealName = mealName;

    }

    // GETTERS/SETTERS

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Timestamp getTimeEaten() {
        return timeEaten;
    }

    public void setTimeEaten(Timestamp timeEaten) {
        this.timeEaten = timeEaten;
    }

    public Set<CaloricTotal> getCaloricTotals() {
        return caloricTotals;
    }

    public void setCaloricTotals(Set<CaloricTotal> caloricTotals) {
        this.caloricTotals = caloricTotals;
    }

    public void assignToMealPlan(MealPlan mealPlans) {
        this.mealPlans.add(mealPlans);
    }

    public double getMealCalories() {
        return mealCalories;
    }


    /**
     * IDK if we need this
     */
    public void setMealCalories() {
        double totalCalories = 0.0;
        for (CaloricTotal caloricTotal : caloricTotals) {
            totalCalories += caloricTotal.getTotalCalories();
        }
        this.mealCalories = totalCalories;
    }
}
