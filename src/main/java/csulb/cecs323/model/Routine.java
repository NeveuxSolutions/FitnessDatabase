package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long routineId;
    private String routineName;
    private String routineDescription;

    @Enumerated(EnumType.STRING)
    private TrainingStyle trainingStyle;

    public long getRoutineId() {
        return routineId;
    }

    public void setRoutineId(long routineId) {
        this.routineId = routineId;
    }

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
}
