package dilcheck.pintdb.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.web.bind.annotation.RequestMapping;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping("/api/v1")
public @interface V1ApiVersion {

}