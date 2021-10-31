package it.jpanik.jgaming.controllers;

import it.jpanik.jgaming.dtos.TestDto;
import it.jpanik.jgaming.services.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** @author Deborah Medici */
@RestController
@RequestMapping("/tests")
public class TestController {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

  private final TestService testService;

  @Autowired
  public TestController(final TestService testService) {
    this.testService = testService;
  }

  @GetMapping
  public List<TestDto> getTests(
      @RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
    LOGGER.info("Called GET /tests");
    return testService.findAll(name, surname);
  }

  @GetMapping("/{id}")
  public TestDto findById(@PathVariable Long id) {
    LOGGER.info("Called GET /tests/" + id);
    return testService.findById(id);
  }

  @PostMapping
  public TestDto save(@RequestBody TestDto test) {
    LOGGER.info("Called POST /tests");
    return testService.save(test);
  }

  @PutMapping
  public TestDto update(@RequestBody TestDto test) {
    LOGGER.info("Called PUT /tests");
    return testService.update(test);
  }

  @DeleteMapping("/{id}")
  public TestDto deleteTest(@PathVariable Long id) {
    LOGGER.info("Called DELETE /tests/" + id);
    return testService.delete(id);
  }
}
