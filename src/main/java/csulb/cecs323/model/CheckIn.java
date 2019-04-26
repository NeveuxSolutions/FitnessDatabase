package csulb.cecs323.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class CheckIn {
    @Id
    private Timestamp checkInTimeStamp;
    private double weight;
    private double bodyFat;
    @ManyToOne (cascade=CascadeType.ALL) //not sure about cascade just put it in as a reminder
    @JoinColumn(name="CLIENTID") //renames column in db
    private User userId;
}
