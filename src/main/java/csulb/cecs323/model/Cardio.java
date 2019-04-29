package csulb.cecs323.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class Cardio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardioId;
    private Time duration;
    @Enumerated(EnumType.STRING)
    private CardioActivity exercise;

    public int getCardioId() {
        return cardioId;
    }

    public void setCardioId(int cardioId) {
        this.cardioId = cardioId;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public CardioActivity getExercise() {
        return exercise;
    }

    public void setExercise(CardioActivity exercise) {
        this.exercise = exercise;
    }
}
