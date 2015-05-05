package domain;

import java.util.Objects;

/**
 * TODO Add some meaningful class description...
 */
public class DocumentImpl implements Document {

	private String name;

	private String parent;

	private String content;

	public DocumentImpl(String name, String content, Space space) {
		Objects.requireNonNull(name);
		if ((name = name.trim()).isEmpty()) {
			throw new IllegalArgumentException("'name' is empty");
		}
		Objects.requireNonNull(content);
		Objects.requireNonNull(space);

		this.name = name;
		this.parent = space.getId();
		this.content = content;
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
	public String getContent() {
		checkConsistency();
		return content;
	}

	@Override
	public String toString() {
		String result = "DocumentImpl{" +
				"id='" + super.toString() + '\'' +
				", name='" + name + '\'' +
				", parent='" + parent + '\'' +
				", content='" + content + '\'' +
				'}';
		checkConsistency();
		return result;
	}

	private void checkConsistency() {
		assert nameIsValid() : "'name' is not valid (null or empty)";
		assert parentIsValid() : "'parent' is not valid (null or empty)";
		assert contentIsNotNull() : "'content' is null";
	}

	private boolean nameIsValid() {
		return !(null == name || name.isEmpty());
	}

	private boolean parentIsValid() {
		return !(null == parent || parent.isEmpty());
	}

	private boolean contentIsNotNull() {
		return null != content;
	}
}
