package example;

import java.util.*;

import core.ECARule;
import domain.*;

/**
 * TODO Add some meaningful class description...
 */
public class AnalyseDocument implements ECARule {

	@Override
	public boolean check(Object[] args) {
		Objects.requireNonNull(args);
		if (1 != args.length) {
			throw new IllegalArgumentException("1 'args' expected");
		}
		if (!(args[0] instanceof Document)) {
			throw new IllegalArgumentException("'args[0]' is not a Document");
		}

		Document document = (Document) args[0];
		Service service = ServiceImpl.getInstance();
		Config config = service.get();
		return null == service.find(document, config);
	}

	@Override
	public void execute(Object[] args) {
		Objects.requireNonNull(args);
		if (1 != args.length) {
			throw new IllegalArgumentException("1 'args' expected");
		}
		if (!(args[0] instanceof Document)) {
			throw new IllegalArgumentException("'args[0]' is not a Document");
		}

		Document document = (Document) args[0];
		Service service = ServiceImpl.getInstance();
		Config config = service.get();
		Map<String, Integer> occurrences = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		StringTokenizer tokenizer = new StringTokenizer(document.getContent(), "\t\r\n\f,;.:\"' ", false);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			occurrences.put(token, 1 + occurrences.getOrDefault(token, 0));
		}
		Analysis analysis = service.save(new AnalysisImpl(occurrences, document, config));
	}
}
