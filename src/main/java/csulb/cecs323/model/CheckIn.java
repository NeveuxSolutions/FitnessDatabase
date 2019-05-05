package csulb.cecs323.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class CheckIn {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checkInId;
    @NotNull
    private Timestamp checkInTimeStamp;

    @Min(value = 40, message = "User can't weight less than 40lbs")
    private double weight;
    @Min(value = 1, message = "User can't have less than 1%BF ") @Max(value = 65, message = "User can not exceed 65%BF")
    private double bodyFat;

    @JoinColumn (name="UserId")
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private User userId;

    /**
     * Null Constructor
     */
    public CheckIn() {}

    /**
     * Overloaded Constructor
     * @param user the user for this checkin
     */
    public CheckIn(User user, double weight, double bodyFat, Timestamp checkInTimeStamp) {
        this.userId = user;
        this.weight = weight;
        this.bodyFat = bodyFat;
        this.checkInTimeStamp = checkInTimeStamp;
    }

    //GETTERS/SETTERS
    public Timestamp getCheckInTimeStamp() {
        return checkInTimeStamp;
    }

    public void setCheckInTimeStamp(Timestamp checkInTimeStamp) {
        this.checkInTimeStamp = checkInTimeStamp;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(double bodyFat) {
        this.bodyFat = bodyFat;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}