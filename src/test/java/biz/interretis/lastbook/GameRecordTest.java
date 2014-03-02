package biz.interretis.lastbook;

import static biz.interretis.lastbook.Score.score;
import static biz.interretis.lastbook.Winner.WINNER;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import biz.interretis.lastbook.Game;
import biz.interretis.lastbook.Player;
import biz.interretis.lastbook.Score;

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

    @Test(expected = IllegalArgumentException.class)
    public void at_least_two_players_are_neccessary()
    {
	new Game(SZNYC);
    }

    @Test(expected = IllegalArgumentException.class)
    public void players_must_be_unique()
    {
	new Game(MAREK, SZNYC, SZNYC);
    }

    @Test
    public void after_first_deal_scores_are_remembered()
    {
	// given
	final Game game = new Game(MAREK, SZNYC, JAM);

	// when
	game.winner(MAREK);
	game.addPoints(SZNYC, score(10));
	game.addPoints(JAM, score(15));

	// then
	assertEquals(score(0), game.score(MAREK));
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
	game.winner(MAREK);
	game.addPoints(SZNYC, score(10));
	game.addPoints(JAM, score(15));

	game.addPoints(MAREK, score(20));
	game.winner(SZNYC);
	game.addPoints(JAM, score(16));

	game.addPoints(MAREK, score(2));
	game.addPoints(SZNYC, score(3));
	game.winner(JAM);

	// then
	assertEquals(score(22), game.score(MAREK));
	assertEquals(score(13), game.score(SZNYC));
	assertEquals(score(31), game.score(JAM));
    }

    @Test
    public void all_game_history_is_preserved()
    {
	// given
	final Game game = new Game(MAREK, SZNYC, JAM);

	// when
	game.winner(MAREK);
	game.addPoints(SZNYC, score(10));
	game.addPoints(JAM, score(15));

	game.addPoints(MAREK, score(20));
	game.winner(SZNYC);
	game.addPoints(JAM, score(16));

	game.addPoints(MAREK, score(2));
	game.addPoints(SZNYC, score(3));
	game.winner(JAM);

	// then
	assertEquals(Lists.newArrayList(WINNER, score(20), score(22)), game.scoreHistory(MAREK));
	assertEquals(Lists.newArrayList(score(10), WINNER, score(13)), game.scoreHistory(SZNYC));
	assertEquals(Lists.newArrayList(score(15), score(31), WINNER), game.scoreHistory(JAM));
    }

    @Test
    public void there_must_be_the_winner_of_every_deal()
    {
	// given
	final Game game = new Game(MAREK, SZNYC, JAM);

	// when
	game.addPoints(MAREK, score(5));
	game.addPoints(SZNYC, score(10));
	game.winner(JAM);
    }
}
