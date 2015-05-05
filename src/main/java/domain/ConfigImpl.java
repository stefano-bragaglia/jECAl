package domain;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * TODO Add some meaningful class description...
 */
public class ConfigImpl implements Config {

	public ConfigImpl() {
	}

	@Override
	public String getId() {
		checkConsistency();
		return super.toString();
	}

	@Override
	public String toString() {
		String result = "Config{" +
				"id='" + super.toString() + '\'' +
				'}';
		checkConsistency();
		return result;
	}

	private void checkConsistency() {
	}

}
