package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;

@Entity
public class Workout {
    private Timestamp workoutStartTime;
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
