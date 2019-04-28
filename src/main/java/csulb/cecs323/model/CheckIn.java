package csulb.cecs323.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class CheckIn {
    //@TODO is checkin timestamp type valid?
    @Id
    private Timestamp checkInTimeStamp;
    private double weight;
    private double bodyFat;
    @ManyToOne (cascade=CascadeType.ALL) //not sure about cascade just put it in as a reminder
    @JoinColumn(name="CLIENTID") //renames column in db
    private User userId;

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
