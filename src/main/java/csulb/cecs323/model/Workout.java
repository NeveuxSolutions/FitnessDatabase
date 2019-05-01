package csulb.cecs323.model;

import javax.persistence.*;
import java.sql.Timestamp;
import javax.validation.constraints.*;

@Entity
public class Workout {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workoutId;
    private Timestamp workoutStartTime;

    @Column(nullable=false)
    private String workoutDescription;
    @Min(1) @Max(7)
    private int day;

    public Timestamp getWorkoutStartTime() {
        return workoutStartTime;
    }

    public void setWorkoutStartTime(Timestamp workoutStartTime) {
        this.workoutStartTime = workoutStartTime;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
