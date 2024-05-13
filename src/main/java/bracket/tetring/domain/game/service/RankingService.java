package bracket.tetring.domain.game.service;

import bracket.tetring.domain.game.domain.Score;
import bracket.tetring.domain.game.mapper.RankingMapper;
import bracket.tetring.domain.game.repository.ScoreRepository;
import bracket.tetring.domain.game.dto.RankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final ScoreRepository scoreRepository;
    private final RankingMapper rankingMapper;

    @Transactional
    public List<RankingDto> getRankings() {
        List<Score> rankings = scoreRepository.findAllRankings();
        return rankingMapper.scoresToRankingDtos(rankings);
    }
}
