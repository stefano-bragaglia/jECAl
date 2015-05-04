package example;

import core.ECAEngine;
import domain.*;

/**
 * TODO Add some meaningful class description...
 */
public class Application {
	public static void main(String[] args) {

		Service service = ServiceImpl.getInstance();
		Space space = service.save(new SpaceImpl("MySpace"));
		Document doc1 = service.save(new DocumentImpl("file.txt", space));
		Document doc2 = service.save(new DocumentImpl("readme.md", space));
		System.out.println(service);

		service.delete(space);
		System.out.println(service);

		System.err.println("Done.");
	}
}
