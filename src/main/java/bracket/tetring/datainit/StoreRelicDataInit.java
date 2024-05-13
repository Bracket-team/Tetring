package bracket.tetring.datainit;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.repository.PlayerRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.repository.StoreRelicRepository;
import bracket.tetring.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class StoreRelicDataInit {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final StoreRepository storeRepository;
    private final RelicRepository relicRepository;
    private final StoreRelicRepository storeRelicRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Player testPlayer = playerRepository.findByName("송하준").orElseThrow(
                () -> new NoSuchElementException("송하준 플레이어가 없습니다.")
        );
        Game testGame = gameRepository.findPlayingGameByPlayerId(testPlayer.getPlayerId()).orElseThrow(
                () -> new NoSuchElementException("플레이 중인 게임은 없습니다. id: 123")
        );
        Store testStore = storeRepository.findById(testGame.getGameId()).orElseThrow(
                () -> new NoSuchElementException("상점이 존재하지 않습니다. 게임 아이디:" + testGame.getGameId())
        );
        Relic relic = relicRepository.findById(2).orElseThrow(
                () -> new NoSuchElementException("2번 유물이 존재하지 않습니다.")
        );
        StoreRelic storeRelic = new StoreRelic(testStore, relic, 2);
        storeRelicRepository.save(storeRelic);
    }
}
