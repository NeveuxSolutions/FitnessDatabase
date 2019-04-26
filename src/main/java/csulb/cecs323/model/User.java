package csulb.cecs323.model;

import javax.persistence.*;

//need to do enums, cks and fks + junction with programs
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //need to do the one to many?
    private int userId;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phone;
    private String fName;
    private String lName;
    private int age;
    private double height;
    private Experience userExperience;
}
