package domain;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * TODO Add some meaningful class description...
 */
public class AnalysisImpl implements Analysis {

	private String id;

	private Map<String, Integer> occurrences;

	private String parent;

	@Deprecated
	public AnalysisImpl() {
	}

	public AnalysisImpl(final Map<String, Integer> occurrences, Document document) {
		Objects.requireNonNull(occurrences);

		this.occurrences = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		this.occurrences.putAll(occurrences);
		this.parent = document.getId();
		checkConsistency();
	}

	@Override
	public String getId() {
		checkConsistency();
		return super.toString();
	}

	@Override
	public Collection<String> getTokens() {
		checkConsistency();
		return occurrences.keySet();
	}

	@Override
	public int getOccurrences(String token) {
		Objects.requireNonNull(token);
		if ((token = token.trim()).isEmpty()) {
			throw new IllegalArgumentException("'token' is empty");
		}

		int result = 0;
		if (occurrences.containsKey(token))
			result = occurrences.get(token);
		checkConsistency();
		return result;
	}

	@Override
	public Collection<Map.Entry<String, Integer>> getVector() {
		checkConsistency();
		return occurrences.entrySet();
	}

	@Override
	public String getParent() {
		checkConsistency();
		return parent;
	}

	@Override
	public String toString() {
		String result = "AnalysisImpl{" +
				"id='" + id + '\'' +
				", occurrences=" + occurrences +
				", parent='" + parent + '\'' +
				'}';
		checkConsistency();
		return result;
	}

	private void checkConsistency() {
		assert occurrencesIsNotNull() : "'occurrences' is null";
		assert parentIsValid() : "'parent' is not valid (null or empty)";
	}

	private boolean occurrencesIsNotNull() {
		return null != occurrences;
	}

	private boolean parentIsValid() {
		return !(null == parent || parent.isEmpty());
	}

}
