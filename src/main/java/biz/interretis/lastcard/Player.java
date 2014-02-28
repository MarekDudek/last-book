package biz.interretis.lastcard;

import java.util.Objects;

public final class Player {

    private final String name;

    public Player(final String aName) {
	name = aName;
    }

    @Override
    public int hashCode() {
	return Objects.hashCode(name);
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
	final Player other = (Player) object;
	return Objects.equals(name, other.name);
    }
}