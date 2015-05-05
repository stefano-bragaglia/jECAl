package domain;

import java.util.Collection;
import java.util.Map.Entry;

/**
 * TODO Add some meaningful class description...
 */
public interface Analysis {

	String getId();
	Collection<String> getTokens();
	int getOccurrences(String token);
	Collection<Entry<String, Integer>> getVector();
	String getParent();
	String getConfig();

}
