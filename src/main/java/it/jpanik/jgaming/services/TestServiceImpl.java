package it.jpanik.jgaming.services;

import it.jpanik.jgaming.dtos.TestDto;
import it.jpanik.jgaming.entities.Test;
import it.jpanik.jgaming.mappers.TestMapper;
import it.jpanik.jgaming.repositories.TestRepository;
import it.jpanik.jgaming.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Deborah Medici
 */
@Service
public class TestServiceImpl implements TestService {

    private final TestRepository repository;

    @Autowired
    public TestServiceImpl(final TestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TestDto> findAll(String name, String surname) {
        List<Test> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return TestMapper.INSTANCE.testListToTestListDto(result);
    }

    @Override
    public TestDto findById(Long id) {
        Optional<Test> test = repository.findById(id);
        return test.map(TestMapper.INSTANCE::testToTestDto).orElse(null);
    }

    @Override
    public TestDto save(TestDto testDto) {
        Test test = TestMapper.INSTANCE.testDtoToTest(testDto);
        repository.save(test);
        return TestMapper.INSTANCE.testToTestDto(test);
    }

    @Override
    public TestDto update(TestDto testDto) {
        Test test = repository.findById(testDto.getId()).orElseThrow(null);
        test = TestMapper.INSTANCE.updateTestFromTestDTo(testDto, test);
        repository.save(test);
        return TestMapper.INSTANCE.testToTestDto(test);
    }

    @Override
    public TestDto delete(Long id) {
        Optional<Test> test = repository.findById(id);
        test.ifPresent(repository::delete);
        return test.map(TestMapper.INSTANCE::testToTestDto).orElse(null);
    }
}
