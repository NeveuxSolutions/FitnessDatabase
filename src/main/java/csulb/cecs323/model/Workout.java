package csulb.cecs323.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.constraints.*;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames={"workoutDescription"})
)
public class Workout {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workoutId;
    private int workoutStartTime;

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

    public int getWorkoutStartTime() {
        return workoutStartTime;
    }

    public void setWorkoutStartTime(int workoutStartTime) {
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

    public int getWorkoutId() {
        return workoutId;
    }

    public Set<Routine> getRoutines() {
        return routines;
    }

    public void addCardio(Cardio cardio){
        cardioExercises.add(cardio);
    }

    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }
}