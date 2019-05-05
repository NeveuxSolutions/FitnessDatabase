package csulb.cecs323.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Exercise {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long exerciseId;
    private String exerciseName;
    private int repCount;
    private int setCount;
    private double weight;

    @Enumerated(EnumType.STRING)
    private BodyPart bodyPart;
    @Enumerated(EnumType.STRING)
    private ExerciseType exerciseType;
    @Enumerated(EnumType.STRING)
    private Tempo tempo;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "EXERCISEASSIGNMENT", joinColumns = @JoinColumn(name = "exerciseId", referencedColumnName = "exerciseId"),
            inverseJoinColumns = @JoinColumn(name = "workoutId", referencedColumnName = "workoutId"))
    private Set<Workout> workouts = new HashSet<>();

    /**
     * Null Constructor
     */
    public Exercise() {}

    /**
     * Overloaded Constructor
     * @param exerciseName the name of the exercise
     * @param workouts the workout that exercise is being assigned to
     */
    public Exercise(String exerciseName, Workout workouts) {
        this.exerciseName = exerciseName;
        this.workouts = Stream.of(workouts).collect(Collectors.toSet());
        this.workouts.forEach(x -> x.getExercises().add(this));
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getRepCount() {
        return repCount;
    }

    public void setRepCount(int repCount) {
        this.repCount = repCount;
    }

    public int getSetCount() {
        return setCount;
    }

    public void setSetCount(int setCount) {
        this.setCount = setCount;
    }

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Tempo getTempo() {
        return tempo;
    }

    public void setTempo(Tempo tempo) {
        this.tempo = tempo;
    }

    public Set<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Set<Workout> workouts) {
        this.workouts = workouts;
    }

    public void addWorkout(Workout workout){
        workouts.add(workout);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
