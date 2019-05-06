package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames={"name"})
)
public class Food {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodId;

    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private double gramsProtein;
    @Column(nullable=false)
    private double gramsCarb;
    @Column(nullable=false)
    private double gramsFat;

    @OneToMany(mappedBy = "food")
    private Set<CaloricTotal> caloricTotals;

    /**
     * Null Constructor
     */
    public void Food(){}

    /**
     * Overloaded Constructor
     * @param name name of the food
     * @param protein amount of protein / gram
     * @param carbs amount of carbs / gram
     * @param fat amount of fat / gram
     */
    public void Food(String name, double protein, double carbs, double fat) {
        this.name = name;
        this.gramsProtein = protein;
        this.gramsCarb = carbs;
        this.gramsFat = fat;
    }

    // GETTERS/SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGramsProtein() {
        return gramsProtein;
    }

    public void setGramsProtein(double gramsProtein) {
        this.gramsProtein = gramsProtein;
    }

    public double getGramsCarb() {
        return gramsCarb;
    }

    public void setGramsCarb(double gramsCarb) {
        this.gramsCarb = gramsCarb;
    }

    public double getGramsFat() {
        return gramsFat;
    }

    public void setGramsFat(double gramsFat) {
        this.gramsFat = gramsFat;
    }

}
