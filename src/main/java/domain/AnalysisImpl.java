package domain;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * TODO Add some meaningful class description...
 */
public class AnalysisImpl implements Analysis {

	private Map<String, Integer> occurrences;

	private String parent;

	private String config;

	public AnalysisImpl(final Map<String, Integer> occurrences, Document document, Config config) {
		Objects.requireNonNull(occurrences);
		Objects.requireNonNull(document);
		Objects.requireNonNull(config);

		this.occurrences = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		this.occurrences.putAll(occurrences);
		this.parent = document.getId();
		this.config = config.getId();
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
	public String getConfig() {
		checkConsistency();
		return config;
	}

	@Override
	public String toString() {
		String result = "Analysis{" +
				"id='" + super.toString() + '\'' +
				", occurrences=" + occurrences +
				", parent='" + parent + '\'' +
				", config='" + config + '\'' +
				'}';
		checkConsistency();
		return result;
	}

	private void checkConsistency() {
		assert occurrencesIsNotNull() : "'occurrences' is null";
		assert parentIsValid() : "'parent' is not valid (null or empty)";
		assert configIsValid() : "'config' is not valid (null or empty)";
	}

	private boolean occurrencesIsNotNull() {
		return null != occurrences;
	}

	private boolean parentIsValid() {
		return !(null == parent || parent.isEmpty());
	}

	private boolean configIsValid() {
		return !(null == config || config.isEmpty());
	}

}