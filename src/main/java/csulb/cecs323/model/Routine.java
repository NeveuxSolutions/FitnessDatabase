package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class Routine {
    //@TODO rdb diagram looks wrong
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routineId;
    private String routineName;
    private String routineDescription;
    @Enumerated(EnumType.STRING)
    private TrainingStyle trainingStyle;
    //private Program programId;

    public int getRoutineId() {
        return routineId;
    }

    public void setRoutineId(int routineId) {
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

   /* public Program getProgramId() {
        return programId;
    }

    public void setProgramId(Program programId) {
        this.programId = programId;
    }*/
}
