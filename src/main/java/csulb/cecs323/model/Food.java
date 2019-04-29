package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class Food {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodId;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private double proteinGram;
    @Column(nullable=false)
    private double carbGram;
    @Column(nullable=false)
    private double fatGram;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProteinGram() {
        return proteinGram;
    }

    public void setProteinGram(double proteinGram) {
        this.proteinGram = proteinGram;
    }

    public double getCarbGram() {
        return carbGram;
    }

    public void setCarbGram(double carbGram) {
        this.carbGram = carbGram;
    }

    public double getFatGram() {
        return fatGram;
    }

    public void setFatGram(double fatGram) {
        this.fatGram = fatGram;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }
}
