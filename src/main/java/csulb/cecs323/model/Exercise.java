package csulb.cecs323.model;

import javax.persistence.*;

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
}
