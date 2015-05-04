package domain;

import java.util.Objects;

/**
 * TODO Add some meaningful class description...
 */
public class DocumentImpl implements Document {

	private String id;

	private String name;

	private String parent;

	@Deprecated
	public DocumentImpl() {
	}

	public DocumentImpl(String name, Space space) {
		Objects.requireNonNull(name);
		if ((name = name.trim()).isEmpty()) {
			throw new IllegalArgumentException("'name' is empty");
		}
		Objects.requireNonNull(space);

		this.name = name;
		this.parent = space.getId();
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
	public String getParent() {
		checkConsistency();
		return parent;
	}

	@Override
	public String toString() {
		String result = "DocumentImpl{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", parent='" + parent + '\'' +
				'}';
		checkConsistency();
		return result;
	}

	private void checkConsistency() {
		assert nameIsValid() : "'name' is not valid (null or empty)";
		assert parentIsValid() : "'parent' is not valid (null or empty)";
	}

	private boolean nameIsValid() {
		return !(null == name || name.isEmpty());
	}

	private boolean parentIsValid() {
		return !(null == parent || parent.isEmpty());
	}

}
