package csulb.cecs323.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames={"phone", "fName", "lName"})
)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private double height;
    private String phone;

    @Column(nullable=false)
    private String fName;
    @Column(nullable=false)
    private String lName;
    @Min(value = 5, message = "user can't be younger than 5") @Column(nullable=false)
    private int age;


    @Enumerated(EnumType.STRING) @Column(nullable=false)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private ExperienceLevel userExperienceLevel;

    // Relationships
    @OneToMany(mappedBy="userId")
    private java.util.List<CheckIn> checkIns = new ArrayList<>();
    @OneToMany(mappedBy="client") //client is the name of the fk attribute
    private java.util.List<Program> programs = new ArrayList<>();

    /**
     * Null Constructor
     */
    public User() {}

    /**
     * Overloaded Constructor
     * @param fname first name of user
     * @param lname last name of user
     */
    public User(String fname, String lname) {
        this.fName = fname;
        this.lName = lname;
        this.age = 10;
        this.gender = Gender.FEMALE;
        this.height = 10;
    }

    public void addCheckIn(CheckIn appointment){
        checkIns.add(appointment);
    }

    public void addProgram(Program program){
        programs.add(program);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public ExperienceLevel getUserExperienceLevel() {
        return userExperienceLevel;
    }

    public void setUserExperienceLevel(ExperienceLevel userExperienceLevel) {
        this.userExperienceLevel = userExperienceLevel;
    }

}

