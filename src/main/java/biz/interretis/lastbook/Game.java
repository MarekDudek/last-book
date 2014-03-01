package biz.interretis.lastbook;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Game {

    private Map<Player, Score> currentScores;
    private Map<Player, List<Score>> history;

    public Game(final Player... players) {
	currentScores = Maps.newLinkedHashMap();
	history = Maps.newLinkedHashMap();
	for (final Player player : players) {
	    currentScores.put(player, Score.ZERO);
	    history.put(player, Lists.<Score> newLinkedList());
	}
	Preconditions.checkArgument(currentScores.size() >= 2, "Game needs at least two players");
	Preconditions.checkArgument(players.length == currentScores.size(), "Players must be unique");
    }

    public List<Player> players() {
	return ImmutableList.copyOf(currentScores.keySet());
    }

    public Score score(final Player player) {
	return currentScores.get(player);
    }

    public void addPoints(final Player player, final Score points) {
	final Score previous = currentScores.get(player);
	final Score current = previous.increasedBy(points);
	currentScores.put(player, current);
	history.get(player).add(current);
    }

    public Player dealer() {
	final Iterator<Player> players = currentScores.keySet().iterator();
	Player dealer = players.next();
	Score highScore = currentScores.get(dealer);
	while (players.hasNext()) {
	    final Player player = players.next();
	    final Score score = currentScores.get(player);
	    if (score.compareTo(highScore) > 0) {
		dealer = player;
		highScore = score;
	    }
	}
	return dealer;
    }

    public List<Score> getScoreHistory(final Player player) {
	return ImmutableList.copyOf(history.get(player));
    }
}
