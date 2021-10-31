package it.jpanik.jgaming.services.minefield;

import it.jpanik.jgaming.dtos.AckDto;
import it.jpanik.jgaming.dtos.minefield.MinefieldInstanceDto;
import it.jpanik.jgaming.entities.minefield.MinefieldInstance;
import it.jpanik.jgaming.enums.Level;
import it.jpanik.jgaming.exceptions.DuplicateException;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.mappers.minefield.MinefieldInstanceMapper;
import it.jpanik.jgaming.repositories.minefield.MinefieldInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Jacopo Cervellini
 */
@Service
public class MinefieldInstanceServiceImpl implements MinefieldInstanceService {

    private final MinefieldInstanceRepository instanceRepository;

    @Autowired
    public MinefieldInstanceServiceImpl(final MinefieldInstanceRepository instanceRepository) {
        this.instanceRepository = instanceRepository;
    }

    @Override
    public AckDto save(MinefieldInstanceDto instanceDto) throws DuplicateException {
        MinefieldInstance instance = MinefieldInstanceMapper.INSTANCE.minefieldInstanceDtoToMinefieldInstance(instanceDto);
        instance.setLevel(getLevel(instance.getBombs(), instance.getColumns(), instance.getLife()));
        MinefieldInstance instanceDb = this.instanceRepository.findByName(instance.getName());
        if (instanceDb != null) {
            throw new DuplicateException("Nome duplicato");
        } else {
            instance = this.instanceRepository.save(instance);
            AckDto ackDto = new AckDto();
            ackDto.setResult(true);
            return ackDto;
        }
    }

    @Override
    public List<MinefieldInstanceDto> get() {
        List<MinefieldInstance> result = new ArrayList<>();
        this.instanceRepository.findAll().forEach(result::add);
        return MinefieldInstanceMapper.INSTANCE.minefieldInstanceListToMinefieldInstanceListDto(result);
    }

    @Override
    public MinefieldInstanceDto delete(Long id) throws ServiceException {
        Optional<MinefieldInstance> instance = this.instanceRepository.findById(id);
        instance.ifPresent(instanceRepository::delete);
        return instance.map(MinefieldInstanceMapper.INSTANCE::minefieldInstanceToMinefieldInstanceDto).orElse(null);
    }

    @Override
    public MinefieldInstanceDto edit(MinefieldInstanceDto instanceDto) throws ServiceException {
        MinefieldInstance instance = this.instanceRepository.findById(instanceDto.getId()).orElseThrow(null);
        instance = MinefieldInstanceMapper.INSTANCE.updateMinefieldInstanceFromMinefieldInstanceDTo(instanceDto, instance);
        instance.setLevel(getLevel(instance.getBombs(), instance.getColumns(), instance.getLife()));
        instance = instanceRepository.save(instance);
        return MinefieldInstanceMapper.INSTANCE.minefieldInstanceToMinefieldInstanceDto(instance);
    }

    private Level getLevel(int bombs, int columns, int lives) {
        double indexDifficult = (double) (bombs) / (columns * columns);
        if (bombs < lives) {
            return Level.EASY;
        }
        if (indexDifficult <= 0.15) {
            return Level.EASY;
        } else {
            if (indexDifficult <= 0.18) {
                return Level.MEDIUM;
            } else {
                return Level.HARD;
            }
        }
    }
}
