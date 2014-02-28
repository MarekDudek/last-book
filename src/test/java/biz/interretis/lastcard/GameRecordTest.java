package biz.interretis.lastcard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameRecordTest {

    // given
    final Player marek = new Player("Marek");
    final Player sznyc = new Player("Sznyc");
    final Player jam = new Player("Jam");
    final Player punx = new Player("Punx");
    final Player kefir = new Player("Kefir");

    @Test
    public void at_the_begining_all_players_score_zero()
    {
	// when
	final Game game = new Game(marek, sznyc, jam, punx, kefir);

	// then
	for (final Player player : game.players()) {
	    final Score score = game.score(player);
	    assertEquals(Score.ZERO, score);
	}
    }

    @Test
    public void after_first_deal_scores_are_remembered()
    {
	// given
	final Game game = new Game(marek, sznyc, jam);

	// when
	game.addPoints(marek, new Score(5));
	game.addPoints(sznyc, new Score(10));
	game.addPoints(jam, new Score(15));

	// then
	assertEquals(new Score(5), game.score(marek));
	assertEquals(new Score(10), game.score(sznyc));
	assertEquals(new Score(15), game.score(jam));
    }
}
