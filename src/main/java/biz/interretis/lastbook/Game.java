package biz.interretis.lastbook;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Game
{
    private Map<Player, LinkedList<Score>> scores;

    public Game(final Player... players)
    {
	scores = Maps.newLinkedHashMap();
	for (final Player player : players) {
	    scores.put(player, Lists.<Score> newLinkedList());
	}
	Preconditions.checkArgument(scores.size() >= 2, "Game needs at least two players");
	Preconditions.checkArgument(players.length == scores.size(), "Players must be unique");
    }

    public List<Player> players()
    {
	return ImmutableList.copyOf(scores.keySet());
    }

    public Score score(final Player player)
    {
	final LinkedList<Score> history = scores.get(player);
	if (history.isEmpty()) {
	    return Score.ZERO;
	}
	return history.getLast();
    }

    public List<Score> scoreHistory(final Player player)
    {
	return ImmutableList.copyOf(scores.get(player));
    }

    public void addPoints(final Player player, final Score points)
    {
	final Score previous = score(player);
	final Score current = previous.increasedBy(points);
	scores.get(player).add(current);
    }

    public Player dealer()
    {
	final Iterator<Player> players = scores.keySet().iterator();
	return dealer(players.next(), players);
    }

    private Player dealer(final Player current, final Iterator<Player> rest)
    {
	if (rest.hasNext()) {
	    final Player next = rest.next();
	    final Player dealer = playerWithMorePoints(current, next);
	    return dealer(dealer, rest);
	} else {
	    return current;
	}
    }

    private Player playerWithMorePoints(final Player first, final Player second)
    {
	if (score(first).compareTo(score(second)) > 0) {
	    return first;
	} else {
	    return second;
	}
    }
}
