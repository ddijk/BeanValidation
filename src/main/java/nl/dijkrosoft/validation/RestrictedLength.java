package nl.dijkrosoft.validation;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

@NotNull
@Constraint(validatedBy = RestrictedLength.RestrictedLengthValidator.class)
@Target(
  {
    METHOD, FIELD
  })
@Retention(RUNTIME)
@Documented
public @interface RestrictedLength
{
  final int DEFAULT_LENGTH = 2;

  String message() default "{my.msg}";

  Class<?>[] groups() default
  {
  };

  int length() default DEFAULT_LENGTH;

  Class<? extends Payload>[] payload() default
  {
  };

//  Class<?> groups() default
//  {
//  };
  class RestrictedLengthValidator implements ConstraintValidator<RestrictedLength, String>
  {
    int len = DEFAULT_LENGTH;

    public RestrictedLengthValidator()
    {
    }

    public void initialize(RestrictedLength a)
    {
      len = a.length();
    }

    public boolean isValid(String t, ConstraintValidatorContext cvc)
    {
      return t.length() == len;
      // cvc.getDefaultConstraintMessageTemplate
      // cvc.buildConstraintViolationWithTemplate("{my.msg}").
    }
  }

}
