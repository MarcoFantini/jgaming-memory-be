package it.jpanik.jgaming.services.rank;

import it.jpanik.jgaming.dtos.RankDto;
import it.jpanik.jgaming.dtos.RankUpdateDto;
import it.jpanik.jgaming.entities.Rank;
import it.jpanik.jgaming.entities.User;
import it.jpanik.jgaming.enums.GameType;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.mappers.RankMapper;
import it.jpanik.jgaming.repositories.RankRepository;
import it.jpanik.jgaming.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Jacopo Cervellini
 */
@Service
public class RankServiceImpl implements RankService {

    private final RankRepository rankRepository;
    private final UserRepository userRepository;

    @Autowired
    public RankServiceImpl(final RankRepository repository, final UserRepository userRepository) {
        this.rankRepository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public RankDto save(RankUpdateDto rankUpdateDto) throws ServiceException {
        User user = userRepository.findById(rankUpdateDto.getUserId()).orElseThrow(() -> new ServiceException("Utente non trovato"));
        Optional<Rank> rank = rankRepository.findByUserAndGameType(user, rankUpdateDto.getGameType());
        Rank value;
        if (rank.isPresent()) {
            value = rank.get();
            if (rankUpdateDto.getGameType().equals(GameType.BLACKJACK))
                value.setScore(rankUpdateDto.getVictoryPoints());
            else
                value.setScore(value.getScore() + (rankUpdateDto.getVictoryPoints()));
        } else {
            value = new Rank();
            value.setUser(user);
            value.setScore(rankUpdateDto.isResult() ? (rankUpdateDto.getVictoryPoints()) : 0);
            value.setGameType(rankUpdateDto.getGameType());
        }
        value = rankRepository.save(value);
        return RankMapper.INSTANCE.rankToRankDto(value);
    }

    @Override
    public List<RankDto> getRank(GameType gameType) {
        List<Rank> result = this.rankRepository.findByGameTypeOrderByScoreDesc(gameType);
        return RankMapper.INSTANCE.rankListToRankListDto(result);
    }

    @Override
    public Rank getByUserAndGameType(User user, GameType gameType) throws ServiceException {
        return rankRepository.findByUserAndGameType(user, gameType).orElseThrow(() -> new ServiceException("Rank not found"));
    }
}
