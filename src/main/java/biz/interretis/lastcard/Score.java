package biz.interretis.lastcard;

import java.util.Objects;

public final class Score {

    private final int points;

    public Score(final int aPoints) {
	points = aPoints;
    }

    public static final Score ZERO = new Score(0);

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
}
