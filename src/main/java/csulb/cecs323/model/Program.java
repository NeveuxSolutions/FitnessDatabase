package csulb.cecs323.model;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames={"startDate", "programDescription"})
)
public class Program {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long programId;
    @Column(nullable=false)
    private Date startDate;
    private Date endDate;
    @Column(nullable=false)
    private String programDescription;

    @Enumerated(EnumType.STRING)
    private ProgramGoal programGoal;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "userId")
    @CascadeOnDelete
    private User client;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "routineId", referencedColumnName = "routineId")
    private Routine routine;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "mealPlanId", referencedColumnName = "mealPlanId")
    private MealPlan mealPlan;


    /**
     * Null constructor
     */
    public Program() {}

    /**
     * Overloaded Constructor
     * @param programDescription program description
     */
    public Program(String programDescription, User client) {
        this.programDescription = programDescription;
        this.client = client;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public ProgramGoal getProgramGoal() {
        return programGoal;
    }

    public void setProgramGoal(ProgramGoal programGoal) {
        this.programGoal = programGoal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public long getProgramId() {
        return programId;
    }

    public void setProgramId(long programId) {
        this.programId = programId;
    }
}
