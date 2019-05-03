package csulb.cecs323.model;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Cardio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardioId;
    private Time duration;
    @Enumerated(EnumType.STRING)
    private CardioActivity exercise;
    @Enumerated(EnumType.STRING)
    private CardioType cardioType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CARDIOASSIGNMENTS", joinColumns = @JoinColumn(name = "cardioId", referencedColumnName = "cardioId"),
    inverseJoinColumns = @JoinColumn(name = "workoutId", referencedColumnName = "workoutId"))
    private Set<Workout> workouts;

    /**
     * Null Constructor
     */
    public Cardio() {}

    /**
     * Overloaded Constructor
     * @param exercise the exercise to be performed
     * @param workouts the workout that the exercise is being assigned to
     */
    public Cardio(CardioActivity exercise, Workout workouts) {
        this.exercise = exercise;
        this.workouts = Stream.of(workouts).collect(Collectors.toSet());
        this.workouts.forEach(x -> x.getCardioExercises().add(this));

    }

}
