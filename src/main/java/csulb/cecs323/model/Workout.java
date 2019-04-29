package csulb.cecs323.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Workout {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long workoutId;
    private Timestamp workoutStartTime;

    @Column(nullable=false)
    private String workoutDescription;

    @Enumerated(EnumType.STRING)
    private WorkoutDay day;

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

    public WorkoutDay getDay() {
        return day;
    }

    public void setDay(WorkoutDay day) {
        this.day = day;
    }
}
