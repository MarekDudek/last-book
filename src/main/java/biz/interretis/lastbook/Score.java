package biz.interretis.lastbook;

import java.util.Objects;

import com.google.common.collect.ComparisonChain;

public final class Score implements DealResult, Comparable<Score>
{
    public static final Score ZERO = new Score(0);

    public static Score score(final int points) {
	return new Score(points);
    }

    private Score(final int aPoints) {
	points = aPoints;
    }

    private final int points;

    public Score increasedBy(final Score added) {
	return new Score(points + added.points);
    }

    @Override
    public int hashCode() {
	return Objects.hashCode(points);
    }

    @Override
    public boolean equals(final Object object) {
	if (this == object) {
	    return true;
	}
	if (object == null) {
	    return false;
	}
	if (getClass() != object.getClass()) {
	    return false;
	}
	final Score other = (Score) object;
	return Objects.equals(points, other.points);
    }

    @Override
    public int compareTo(final Score other) {
	return ComparisonChain.start().compare(points, other.points).result();
    }
}
