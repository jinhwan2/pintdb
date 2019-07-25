package dilcheck.pintdb.controller;

import dilcheck.pintdb.model.V1ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@V1ApiVersion
public class DatabaseController {
  @GetMapping("/")
  public String defaultPage() {
    return "pintDB server";
  }
}
