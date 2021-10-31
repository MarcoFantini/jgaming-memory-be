package it.jpanik.jgaming.mappers;

import it.jpanik.jgaming.dtos.TestDto;
import it.jpanik.jgaming.entities.Test;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Deborah Medici
 */
@Mapper
public interface TestMapper {

    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

    TestDto testToTestDto(Test test);

    Test testDtoToTest(TestDto testDto);

    @InheritConfiguration(name = "testDtoToTest")
    Test updateTestFromTestDTo(TestDto testDto, @MappingTarget Test test);

    List<TestDto> testListToTestListDto(List<Test> testList);

    List<Test> testDtoToListToTestList(List<TestDto> testDtos);
}
