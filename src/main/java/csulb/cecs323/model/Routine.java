package csulb.cecs323.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames={"routineName"})
)
public class Routine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routineId;
    @Column(nullable=false)
    private String routineName;
    private String routineDescription;

    @Enumerated(EnumType.STRING)
    private TrainingStyle trainingStyle;

    @ManyToMany(mappedBy = "routines")
    private Set<Workout> workouts = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "programId", referencedColumnName = "programId")
    private Program program;


    /**
     * Null Constructor
     */
    public Routine() {}

    /**
     * Overloaded Constructor
     * @param routineName the name of the routine
     */
    public Routine(String routineName) {
        this.routineName = routineName;
    }

    //GETTERS/SETTERS
    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public String getRoutineDescription() {
        return routineDescription;
    }

    public void setRoutineDescription(String routineDescription) {
        this.routineDescription = routineDescription;
    }

    public TrainingStyle getTrainingStyle() {
        return trainingStyle;
    }

    public void setTrainingStyle(TrainingStyle trainingStyle) {
        this.trainingStyle = trainingStyle;
    }

    public Set<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Set<Workout> workouts) {
        this.workouts = workouts;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public int getRoutineId() {
        return routineId;
    }

    public void addWorkout(Workout workout){
        workouts.add(workout);
    }
}
