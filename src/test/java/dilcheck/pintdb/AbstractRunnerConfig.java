package dilcheck.pintdb;

import dilcheck.pintdb.controller.DatabaseController;
import dilcheck.pintdb.domain.config.LoadConfig;
import dilcheck.pintdb.exec.PintDbLauncher;
import dilcheck.pintdb.service.SaveDatabaseService;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = {PintDbLauncher.class,
    DatabaseController.class, SaveDatabaseService.class, LoadConfig.class})
public class AbstractRunnerConfig extends SetupConfig {
}
