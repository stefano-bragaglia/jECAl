package domain;

import java.util.Collection;

/**
 * TODO Add some meaningful class description...
 */
public interface Service {

	void delete(Space space);
	void delete(Document document);
	void delete(Analysis analysis);

	Collection<Space> find();
	Collection<Document> find(Space space);
	Analysis find(Document document);

	Space save(Space space);
	Document save(Document document);
	Analysis save(Analysis analysis);

}
