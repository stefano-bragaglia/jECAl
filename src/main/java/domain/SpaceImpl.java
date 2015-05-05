package domain;

import java.util.Objects;

/**
 * TODO Add some meaningful class description...
 */
public class SpaceImpl implements Space {

	private String name;

	public SpaceImpl(String name) {
		Objects.requireNonNull(name);
		if ((name = name.trim()).isEmpty()) {
			throw new IllegalArgumentException("'name' is empty");
		}

		this.name = name;
		checkConsistency();
	}

	@Override
	public String getId() {
		checkConsistency();
		return super.toString();
	}

	@Override
	public String getName() {
		checkConsistency();
		return name;
	}

	@Override
	public String toString() {
		String result = "SpaceImpl{" +
				"id='" + super.toString() + '\'' +
				", name='" + name + '\'' +
				'}';
		checkConsistency();
		return result;
	}

	private void checkConsistency() {
		assert nameIsValid() : "'name' is not valid (null or empty)";
	}

	private boolean nameIsValid() {
		return !(null == name || name.isEmpty());
	}
}
