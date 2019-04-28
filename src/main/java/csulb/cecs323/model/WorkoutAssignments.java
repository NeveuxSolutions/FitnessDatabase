package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WorkoutAssignments {
    @Id
    private int workoutId;
    @Id
    private int routineId;
}
