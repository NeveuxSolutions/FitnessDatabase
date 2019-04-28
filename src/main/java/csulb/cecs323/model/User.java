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

