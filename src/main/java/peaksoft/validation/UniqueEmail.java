package peaksoft.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface UniqueEmail {

    String message() default "This email already exists  email must be unique ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
