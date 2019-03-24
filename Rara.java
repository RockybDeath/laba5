import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Rara {
    int massa() default 3;
    int weight() default 10;
    int height() default 70;
    String color() default "черный";
    String breed();
}

