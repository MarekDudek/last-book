package biz.interretis.lastcard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameRecordTest {

    @Test
    public void at_the_begining_all_players_score_zero()
    {
	// given
	final Player marek = new Player("Marek");
	final Player sznyc = new Player("Sznyc");
	final Player jam = new Player("Jam");
	final Player punx = new Player("Punx");
	final Player kefir = new Player("Kefir");

	// when
	final Game game = new Game(marek, sznyc, jam, punx, kefir);

	// then
	for (final Player player : game.players()) {
	    final Score score = game.score(player);
	    assertEquals(Score.ZERO, score);
	}
    }

}
