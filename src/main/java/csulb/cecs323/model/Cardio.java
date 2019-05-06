package csulb.cecs323.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames={"duration", "cardioActivity"})
)
public class Cardio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardioId;
    private int duration; //sql duration and java Time are NOT compatible whatsoever...
    @Enumerated(EnumType.STRING)
    private CardioActivity cardioActivity;
    @Enumerated(EnumType.STRING)
    private CardioType cardioType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CARDIOASSIGNMENTS", joinColumns = @JoinColumn(name = "cardioId", referencedColumnName = "cardioId"),
            inverseJoinColumns = @JoinColumn(name = "workoutId", referencedColumnName = "workoutId"))
    private Set<Workout> workouts = new HashSet<>();

    /**
     * Null Constructor
     */
    public Cardio() {}

    /**
     * Overloaded Constructor
     * @param cardioActivity the cardioActivity to be performed
     * @param workouts the workout that the cardioActivity is being assigned to
     */
    public Cardio(CardioActivity cardioActivity, Workout workouts) {
        this.cardioActivity = cardioActivity;
        this.workouts = Stream.of(workouts).collect(Collectors.toSet());
        this.workouts.forEach(x -> x.getCardioExercises().add(this));

    }

    public int getCardioId() {
        return cardioId;
    }

    public void setCardioId(int cardioId) {
        this.cardioId = cardioId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CardioActivity getCardioActivity() {
        return cardioActivity;
    }

    public void setCardioActivity(CardioActivity cardioActivity) {
        this.cardioActivity = cardioActivity;
    }

    public CardioType getCardioType() {
        return cardioType;
    }

    public void setCardioType(CardioType cardioType) {
        this.cardioType = cardioType;
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
}