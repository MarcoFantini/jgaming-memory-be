package it.jpanik.jgaming.services.test;

import it.jpanik.jgaming.dtos.TestDto;

import java.util.List;

/**
 * @author Deborah Medici
 */
public interface TestService {

    List<TestDto> findAll(String name, String surname);

    TestDto findById(Long id);

    TestDto save(TestDto testDto);

    TestDto update(TestDto testDto);

    TestDto delete(Long id);
}
