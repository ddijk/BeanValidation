package nl.dijkrosoft.validation;

import nl.dijkrosoft.validation.constraints.NotEmptyFields;
import nl.dijkrosoft.validation.groups.GroupOne;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class Person {

    @RestrictedLength(length = 4, groups = Default.class)
    String naam;

    @NotNull
    @Min(value = 3)
    @Max(value = 5)
    int age;

    List<Bike> bikesList = new ArrayList<>();

    List<String> aliases = new ArrayList<>();

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NotNull(groups = GroupOne.class)
    public List<Bike> getBikesList() {
        return bikesList;
    }


    @NotEmptyFields(groups = GroupOne.class)
    public List<String> getAliases() {
        return aliases;
    }

    public static void main(String[] args) {
        Person p = new Person();
        p.setNaam("dik");
        p.setAge(8);
        p.getBikesList().add(null);
        p.bikesList = null;
        p.aliases.add(null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //   Set<ConstraintViolation<Person>> violations = validator.validateProperty(p, "naam");
        Set<ConstraintViolation<Person>> violations = null;
        System.out.println("Validate using default group");
        violations = validator.validate(p);
        printViolations(violations);

        System.out.println("Validate using group GroupOne");
        violations = validator.validate(p, GroupOne.class);
        printViolations(violations);

    }

    private static void printViolations(Set<ConstraintViolation<Person>> violations) {
        System.out.println("aantal violations:" + violations.size());

        for (ConstraintViolation<Person> v : violations) {
            System.out.println("viol: " + v.getPropertyPath() + ":" + v.getMessage());
        }
    }
}
