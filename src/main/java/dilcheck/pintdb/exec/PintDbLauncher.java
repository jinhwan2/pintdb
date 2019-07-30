package dilcheck.pintdb.exec;

import dilcheck.pintdb.domain.config.StoreConfig;
import dilcheck.pintdb.domain.p2p.ConnectionServer;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("dilcheck.pintdb")
public class PintDbLauncher {

  /**
   * run pintDB.
   * @param args arguments
   */
  public static void main(String[] args) throws IOException {
    loadStaticProperties();

    SpringApplication.run(PintDbLauncher.class, args);

    runHealthServer();
  }

  private static void loadStaticProperties() {
    new StoreConfig();
  }

  /**
   * run heart beat server.
   */
  public static void runHealthServer() throws IOException {
    Integer healthPort = StoreConfig.getHealthPort();
    new ConnectionServer().start(healthPort);
  }
}
