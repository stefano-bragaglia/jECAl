package example;

import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeMap;

import core.ECARule;
import domain.*;

/**
 * TODO Add some meaningful class description...
 */
public class ReanalyseDocument implements ECARule {

	@Override
	public boolean check(Object[] args) {
		return true;
	}

	@Override
	public void execute(Object[] args) {
		Objects.requireNonNull(args);
		if (1 != args.length) {
			throw new IllegalArgumentException("1 'args' expected");
		}
		if (!(args[0] instanceof Config)) {
			throw new IllegalArgumentException("'args[0]' is not a Config");
		}

		Config config = (Config) args[0];
		final String cfg = config.getId();
		Service service = ServiceImpl.getInstance();

		for (Space space : service.find()) {
			for (Document document : service.find(space)) {
				Analysis analysis = service.find(document, config);
				Map<String, Integer> occurrences = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
				StringTokenizer tokenizer = new StringTokenizer(document.getContent(), "\t\r\n\f,;.:\"' ", false);
				while (tokenizer.hasMoreTokens()) {
					String token = tokenizer.nextToken();
					occurrences.put(token, 1 + occurrences.getOrDefault(token, 0));
				}
				analysis = service.save(new AnalysisImpl(occurrences, document, config));
			}
		}
	}
}
