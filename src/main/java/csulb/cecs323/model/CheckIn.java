package csulb.cecs323.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class CheckIn {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checkInId;
    private Timestamp checkInTimeStamp;
    private double weight;
    private double bodyFat;

    //@TODO review/insert cascades + make checkin timestamp a unique ck
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)//not sure about cascade just put it in as a reminder
    private User userId;

    /**
     * Null Constructor
     */
    public CheckIn() {}

    /**
     * Overloaded Constructor
     * @param user the user for this checkin
     */
    public CheckIn(User user) {
        this.userId = user;
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
