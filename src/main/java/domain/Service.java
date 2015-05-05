package domain;

import java.util.Collection;

import core.Event;
import example.DeleteWholeSpace;

/**
 * TODO Add some meaningful class description...
 */
public interface Service {

	void delete(Config config);
	void delete(Space space);
	void delete(Document document);
	void delete(Analysis analysis);

	Config get();
	Collection<Space> find();
	Collection<Document> find(Space space);
	Analysis find(Document document, Config config);

	Config save(Config config);
	Space save(Space space);
	Document save(Document document);
	Analysis save(Analysis analysis);

}
