package csulb.cecs323.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;

//need to do enums, cks and fks + junction with programs
@Entity
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"phone", "fName", "lName"})
)
public class User {
    //@TODO add check contraints for things like age >0 phone numbers, heights>0 and non nulls
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable=false)
    private String fName;

    @Column(nullable=false)
    private String lName;

    @Min(value = 5, message = "user can't be younger than 5") @Column(nullable=false)
    private int age;
    private double height;

    @Enumerated(EnumType.STRING) @Column(nullable=false)
    private Gender gender;
    private String phone;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel userExperienceLevel;






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

