package biz.interretis.lastcard;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

public class Game {

    private Map<Player, Score> scores;

    public Game(final Player... players) {
	scores = Maps.newLinkedHashMap();
	for (final Player player : players) {
	    scores.put(player, Score.ZERO);
	}
	Preconditions.checkArgument(players.length == scores.size(), "Players must be unique");
    }

    public List<Player> players() {
	return ImmutableList.copyOf(scores.keySet());
    }

    public Score score(final Player player) {
	return scores.get(player);
    }

    public void addPoints(final Player player, final Score addedPoints) {
	final Score currentScore = scores.get(player);
	final Score newScore = currentScore.increasedBy(addedPoints);
	scores.put(player, newScore);
    }

    public Player dealer() {
	final Iterator<Player> players = scores.keySet().iterator();
	Player dealer = players.next();
	Score highScore = scores.get(dealer);
	while (players.hasNext()) {
	    final Player player = players.next();
	    final Score score = scores.get(player);
	    if (score.compareTo(highScore) > 0) {
		dealer = player;
		highScore = score;
	    }
	}
	return dealer;
    }
}
