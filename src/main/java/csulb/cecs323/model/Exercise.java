package csulb.cecs323.model;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private BodyPart bodyPart;
    @Enumerated(EnumType.STRING)
    private ExerciseType exerciseType;
    @Enumerated(EnumType.STRING)
    private Tempo tempo;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "EXERCISEASSIGNMENT", joinColumns = @JoinColumn(name = "exerciseId", referencedColumnName = "exerciseId"),
            inverseJoinColumns = @JoinColumn(name = "workoutId", referencedColumnName = "workoutId"))
    private Set<Workout> workouts;

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
}
