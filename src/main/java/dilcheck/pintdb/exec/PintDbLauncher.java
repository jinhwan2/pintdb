package dilcheck.pintdb.exec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("dilcheck.pintdb")
public class PintDbLauncher {
	public static void main(String[] args) {
		SpringApplication.run(PintDbLauncher.class, args);
	}
}
