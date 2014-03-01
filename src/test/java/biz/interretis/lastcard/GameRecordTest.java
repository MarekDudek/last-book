package biz.interretis.lastcard;

import static biz.interretis.lastcard.Score.score;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.Lists;

public class GameRecordTest {

    // given
    private static final Player MAREK = new Player("Marek");
    private static final Player SZNYC = new Player("Sznyc");
    private static final Player JAM = new Player("Jam");
    private static final Player PUNX = new Player("Punx");
    private static final Player KEFIR = new Player("Kefir");

    @Test
    public void at_the_begining_all_players_score_zero()
    {
	// when
	final Game game = new Game(MAREK, SZNYC, JAM, PUNX, KEFIR);

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
	final Game game = new Game(MAREK, SZNYC, JAM);

	// when
	game.addPoints(MAREK, score(5));
	game.addPoints(SZNYC, score(10));
	game.addPoints(JAM, score(15));

	// then
	assertEquals(score(5), game.score(MAREK));
	assertEquals(score(10), game.score(SZNYC));
	assertEquals(score(15), game.score(JAM));
    }

    @Test
    public void player_with_highest_score_deals()
    {
	// given
	final Game game = new Game(MAREK, SZNYC, JAM);

	// when
	game.addPoints(MAREK, score(5));
	game.addPoints(SZNYC, score(10));
	game.addPoints(JAM, score(15));

	// then
	final Player dealer = game.dealer();
	assertEquals(JAM, dealer);
    }

    @Test
    public void points_from_all_previous_deas_are_summed_up()
    {
	// given
	final Game game = new Game(MAREK, SZNYC, JAM);

	// when
	game.addPoints(MAREK, score(5));
	game.addPoints(SZNYC, score(10));
	game.addPoints(JAM, score(15));

	game.addPoints(MAREK, score(20));
	game.addPoints(SZNYC, score(18));
	game.addPoints(JAM, score(16));

	game.addPoints(MAREK, score(2));
	game.addPoints(SZNYC, score(3));
	game.addPoints(JAM, score(4));

	// then
	assertEquals(score(27), game.score(MAREK));
	assertEquals(score(31), game.score(SZNYC));
	assertEquals(score(35), game.score(JAM));
    }

    @Test
    public void all_game_history_is_preserved()
    {
	// given
	final Game game = new Game(MAREK, SZNYC, JAM);

	// when
	game.addPoints(MAREK, score(5));
	game.addPoints(SZNYC, score(10));
	game.addPoints(JAM, score(15));

	game.addPoints(MAREK, score(20));
	game.addPoints(SZNYC, score(18));
	game.addPoints(JAM, score(16));

	game.addPoints(MAREK, score(2));
	game.addPoints(SZNYC, score(3));
	game.addPoints(JAM, score(4));

	// then
	assertEquals(Lists.newArrayList(score(5), score(25), score(27)), game.getScoreHistory(MAREK));
	assertEquals(Lists.newArrayList(score(10), score(28), score(31)), game.getScoreHistory(SZNYC));
	assertEquals(Lists.newArrayList(score(15), score(31), score(35)), game.getScoreHistory(JAM));
    }
}
