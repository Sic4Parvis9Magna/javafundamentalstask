package epamtasks.oop.t07;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Submarine {
    int nuberOfEngines();
    boolean useAmomicPower() default  false;
}
