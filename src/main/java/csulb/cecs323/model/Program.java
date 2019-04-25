package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Program {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long programId;
    private Date startDate;
    private Date endDate;
    private String programDescription;

    @Enumerated(EnumType.STRING)
    private ProgramGoal programGoal;

    @Enumerated(EnumType.STRING)
    private Status status;

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
}
