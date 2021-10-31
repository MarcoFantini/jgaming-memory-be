package it.jpanik.jgaming.services.minefield;

import it.jpanik.jgaming.dtos.minefield.MinefieldCellDto;
import it.jpanik.jgaming.dtos.minefield.MinefieldGameDto;
import it.jpanik.jgaming.entities.minefield.MinefieldInstance;
import it.jpanik.jgaming.repositories.minefield.MinefieldInstanceRepository;
import it.jpanik.jgaming.services.minefield.MinefieldGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Jacopo Cervellini
 */
@Service
public class MinefieldGameServiceImpl implements MinefieldGameService {

    private final MinefieldInstanceRepository instanceRepository;

    @Autowired
    public MinefieldGameServiceImpl(final MinefieldInstanceRepository instanceRepository) {
        this.instanceRepository = instanceRepository;
    }

    @Override
    public MinefieldGameDto getMinefieldGame(Long id) {
        Optional<MinefieldInstance> instance = this.instanceRepository.findById(id);
        return buildGame(instance.orElse(null));
    }

    private MinefieldGameDto buildGame(MinefieldInstance instance) {
        MinefieldGameDto game = new MinefieldGameDto();
        MinefieldCellDto[][] cells = new MinefieldCellDto[instance.getColumns()][instance.getColumns()];
        int nbombs = instance.getBombs();
        int ntreasures = instance.getTreasures();
        int i = 0;
        ArrayList<MinefieldCellDto> list = new ArrayList<>();
        do {
            MinefieldCellDto cell = new MinefieldCellDto();
            if (nbombs > 0) {
                cell.setBomb(true);
                cell.setTreasure(false);
                list.add(cell);
                nbombs--;
            } else {
                if (ntreasures > 0) {
                    cell.setBomb(false);
                    cell.setTreasure(true);
                    list.add(cell);
                    ntreasures--;
                } else {
                    cell.setTreasure(false);
                    cell.setBomb(false);
                    list.add(cell);
                }
            }
            i++;
        } while (i < instance.getColumns() * instance.getColumns());
        Collections.shuffle(list);
        int index = 0;
        for (int y = 0; y < instance.getColumns(); y++) {
            for (int j = 0; j < instance.getColumns(); j++) {
                cells[y][j] = list.get(index);
                index++;
            }
        }
        game.setCells(cells);
        game.setLife(instance.getLife());
        game.setName(instance.getName());
        game.setTreasures(instance.getTreasures());
        game.setColumns(instance.getColumns());
        game.setRow(instance.getColumns());
        game.setLevel(instance.getLevel());
        return game;
    }

}
