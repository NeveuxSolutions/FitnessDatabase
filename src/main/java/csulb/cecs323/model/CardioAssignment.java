package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CardioAssignment {
    @Id
    private int cardioId;
    @Id
    private int workoutId;
}
