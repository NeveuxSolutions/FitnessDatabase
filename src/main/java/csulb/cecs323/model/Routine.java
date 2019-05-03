package csulb.cecs323.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Routine {
    //@TODO rdb diagram looks wrong
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routineId;
    private String routineName;
    private String routineDescription;

    @Enumerated(EnumType.STRING)
    private TrainingStyle trainingStyle;
    //private Program programId;

    @ManyToMany(mappedBy = "routines")
    private Set<Workout> workouts = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
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
}
