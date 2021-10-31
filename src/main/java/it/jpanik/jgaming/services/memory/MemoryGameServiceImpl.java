package it.jpanik.jgaming.services.memory;

import it.jpanik.jgaming.dtos.memory.GussetDto;
import it.jpanik.jgaming.dtos.memory.MemoryGameDto;
import it.jpanik.jgaming.entities.memory.MemoryInstance;
import it.jpanik.jgaming.repositories.memory.MemoryInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Marco Fantini
 */
@Service
public class MemoryGameServiceImpl implements MemoryGameService {

    private final MemoryInstanceRepository memoryInstanceRepository;

    @Autowired
    public MemoryGameServiceImpl(final MemoryInstanceRepository memoryInstanceRepository){
        this.memoryInstanceRepository = memoryInstanceRepository;
    }

    @Override
    public MemoryGameDto getMemoryGame(Long id) {
        Optional<MemoryInstance> instance = this.memoryInstanceRepository.findById(id);
        return buildGame(instance.orElse(null));
    }

    private MemoryGameDto buildGame(MemoryInstance instance) {
        int gussetId = 0;
        int y = 0, c = instance.getColumns(), r = instance.getRows();

        MemoryGameDto memoryGame = new MemoryGameDto();

        GussetDto[][] gussetsConfig = new GussetDto[r][c];

        ArrayList<GussetDto> list = new ArrayList<>();
        do {
            y++;
            list.add(new GussetDto(gussetId, String.valueOf(y)));
            gussetId++;
            list.add(new GussetDto(gussetId, String.valueOf(y)));
            gussetId++;
        } while(y < ((r*c)/2));
        Collections.shuffle(list);

        y=0;
        for (int i=0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                gussetsConfig[i][j] = list.get(y);
                y++;
            }
        }

        memoryGame.setGussets(gussetsConfig);
        memoryGame.setRows(instance.getRows());
        memoryGame.setColumns(instance.getColumns());
        memoryGame.setName(instance.getName());
        memoryGame.setMaxErrors(instance.getMaxErrors());
        memoryGame.setVictoryPoints(instance.getVictoryPoints());
        memoryGame.setTimer(instance.getTimer());
        return memoryGame;
    }
}
