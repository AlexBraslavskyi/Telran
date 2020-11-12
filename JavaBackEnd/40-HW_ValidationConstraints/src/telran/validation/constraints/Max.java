package telran.validation.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
/**
 * 
 * @author abraslik
 *constraint only for integer numbers that can't be greater then value interface function
 */
public @interface Max {
int value();
String message() default "max constraint violation";
}
