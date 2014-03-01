package biz.interretis.lastbook;

import java.util.Objects;

import com.google.common.base.Preconditions;

public final class Player
{

    public Player(final String aName) {
	name = Preconditions.checkNotNull(aName);
    }

    private final String name;

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
