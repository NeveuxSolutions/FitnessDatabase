package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class Food {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodId;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private double gramsProtein;
    @Column(nullable=false)
    private double gramsCarb;
    @Column(nullable=false)
    private double gramsFat;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGramsProtein() {
        return gramsProtein;
    }

    public void setGramsProtein(double proteinGram) {
        this.gramsProtein = proteinGram;
    }

    public double getGramsCarb() {
        return gramsCarb;
    }

    public void setGramsCarb(double carbGram) {
        this.gramsCarb = carbGram;
    }

    public double getGramsFat() {
        return gramsFat;
    }

    public void setGramsFat(double fatGram) {
        this.gramsFat = fatGram;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }
}
