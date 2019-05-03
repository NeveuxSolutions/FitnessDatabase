package csulb.cecs323.model;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    @ManyToMany(mappedBy = "workouts")
    private Set<Cardio> cardioExercises = new HashSet<>();

    @ManyToMany(mappedBy = "workouts")
    private Set<Exercise> exercises = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "WORKOUTASSIGNMENT", joinColumns = @JoinColumn(name = "workoutId", referencedColumnName = "workoutId"),
            inverseJoinColumns = @JoinColumn(name = "routineId", referencedColumnName = "routineId"))
    private Set<Routine> routines;

    /**
     * Null Constructor
     */
    public Workout() {}

    /**
     * Overloaded Constructor
     * @param workoutDescription description of the workout
     */
    public Workout(String workoutDescription, int day) {
        this.workoutDescription = workoutDescription;
        this.day = day;
    }

    /**
     * Overloaded constructor to assign a workout to a routine
     * @param workoutDescription the workout description
     * @param day the day to be performed
     * @param routine the routine being assigned to
     */
    public Workout(String workoutDescription, int day, Routine routine) {
        this.workoutDescription = workoutDescription;
        this.day = day;
        this.routines = Stream.of(routine).collect(Collectors.toSet());
        this.routines.forEach(x -> x.getWorkouts().add(this));
    }

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

    public Set<Cardio> getCardioExercises() {
        return cardioExercises;
    }

    public void setCardioExercises(Set<Cardio> cardioExercises) {
        this.cardioExercises = cardioExercises;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }
}
