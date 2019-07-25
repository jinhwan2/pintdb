package dilcheck.pintdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseController {
  @GetMapping("/")
  public String defaultPage() {
    return "pintDB server";
  }
}
