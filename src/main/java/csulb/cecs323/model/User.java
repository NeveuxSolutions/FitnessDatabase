package csulb.cecs323.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

//need to do enums, cks and fks + junction with programs
@Entity
//@TODO double check this is how ck's are done
//@TODO can enum fks just be hardcoded?
//@TODO after ck's + FKs are done recheck not null constraints
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"phone", "fName", "lName"})
)
public class User {
    //@TODO add check contraints for things like age >0 phone numbers, heights>0 and non nulls
    @PrimaryKeyJoinColumn @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //need to do the one to many?
    private int userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Gender gender;
    private String phone;

    @Column(nullable=false)
    private String fName;

    @Column(nullable=false)
    private String lName;

    @Column(nullable=false)
    @Min(30) //@TODO does atMin only restrict insertions because initial "forced" insertions still insert if they are invalid according to min
    private int age;
    private double height;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel userExperienceLevel;
    
    @OneToMany(mappedBy="userId")
    private java.util.List<CheckIn> checkIns = new ArrayList<>();

    public void addCheckIn(CheckIn appointment){
        checkIns.add(appointment);
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

