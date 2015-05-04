package domain;

import java.util.*;

import core.ECAEngine;
import core.Event;
import example.DeleteWholeSpace;

/**
 * TODO Add some meaningful class description...
 */
public class ServiceImpl implements Service {

	/**
	 * Private constructor.
	 * Prevents instantiation from other classes.
	 */
	private ServiceImpl() {
		this.spaces = new HashMap<>();
		this.documents = new HashMap<>();
		this.analyses = new HashMap<>();
		checkConsistency();
	}

	/**
	 * Initializes singleton.
	 * <p>
	 * {@code ServiceImplHolder} is loaded on the first execution of {@code ServiceImpl.getInstance()} or the first
	 * access to {@code ServiceImplHolder.INSTANCE}, not before.
	 */
	private static class ServiceImplHolder {
		/**
		 * Singleton instance of the {@code ServiceImpl} class.
		 */
		private static final Service INSTANCE = ECAEngine.enable(new ServiceImpl());
	}

	/**
	 * Returns the singleton instance of the {@code ServiceImpl} class.
	 *
	 * @return the singleton instance of the {@code ServiceImpl} class
	 */
	public static Service getInstance() {
		return ServiceImplHolder.INSTANCE;
	}

	private Map<String, Space> spaces;

	private Map<String, Document> documents;

	private Map<String, Analysis> analyses;

	@Override
	@Event(DeleteWholeSpace.class)
	public void delete(Space space) {
		Objects.requireNonNull(space);

		spaces.remove(space.getId());
		checkConsistency();
	}

	@Override
	public void delete(Document document) {
		Objects.requireNonNull(document);

		documents.remove(document.getId());
		checkConsistency();
	}

	@Override
	public void delete(Analysis analysis) {
		Objects.requireNonNull(analysis);

		analyses.remove(analysis.getId());
		checkConsistency();
	}

	@Override
	public Collection<Space> find() {
		checkConsistency();
		return spaces.values();
	}

	@Override
	public Collection<Document> find(Space space) {
		Objects.requireNonNull(space);

		final String id = space.getId();
		Set<Document> result = new HashSet<>();
		for (Document document : documents.values()) {
			if (id.equals(document.getParent())) {
				result.add(document);
			}
		}
		checkConsistency();
		return result;
	}

	@Override
	public Analysis find(Document document) {
		Objects.requireNonNull(document);

		Analysis result = null;
		final String id = document.getId();
		for (Analysis analysis : analyses.values()) {
			if (id.equals(analysis.getParent())) {
				result = analysis;
				break;
			}
		}
		checkConsistency();
		return result;
	}

	@Override
	public Space save(Space space) {
		Objects.requireNonNull(space);

		spaces.put(space.getId(), space);
		checkConsistency();
		return space;
	}

	@Override
	public Document save(Document document) {
		Objects.requireNonNull(document);

		documents.put(document.getId(), document);
		checkConsistency();
		return document;
	}

	@Override
	public Analysis save(Analysis analysis) {
		Objects.requireNonNull(analysis);

		analyses.put(analysis.getId(), analysis);
		checkConsistency();
		return analysis;
	}

	@Override
	public String toString() {
		String result = "Service{" +
				"spaces=" + spaces +
				", documents=" + documents +
				", analyses=" + analyses +
				'}';
		checkConsistency();
		return result;
	}

	private void checkConsistency() {
		assert spacesIsNotNull() : "'spaces' is null";
		assert documentsIsNotNull() : "'documents' is null";
		assert analysesIsNotNull() : "'analyses' is null";
	}

	private boolean spacesIsNotNull() {
		return null != spaces;
	}

	private boolean documentsIsNotNull() {
		return null != documents;
	}

	private boolean analysesIsNotNull() {
		return null != analyses;
	}

}
