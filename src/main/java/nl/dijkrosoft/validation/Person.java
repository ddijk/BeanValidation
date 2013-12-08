package nl.dijkrosoft.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Person
{

  @NotNull
  String naam;

  @NotNull
  @Min(value = 3)
  @Max(value = 5)
  int age;

  public String getNaam()
  {
    return naam;
  }

  public void setNaam(String naam)
  {
    this.naam = naam;
  }

  public int getAge()
  {
    return age;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

  public static void main(String[] args)
  {
    Person p = new Person();
    p.setNaam("dick");
    p.setAge(8);

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    //   Set<ConstraintViolation<Person>> violations = validator.validateProperty(p, "naam");
    Set<ConstraintViolation<Person>> violations = validator.validate(p);
    System.out.println("aantal violations:" + violations.size());

    for (ConstraintViolation<Person> v : violations)
    {
      System.out.println("viol: " + v.getPropertyPath() + ":" + v.getMessage());
    }

  }
}
