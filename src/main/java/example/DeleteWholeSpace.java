package example;

import java.util.Objects;

import core.ECARule;
import domain.Document;
import domain.Service;
import domain.ServiceImpl;
import domain.Space;

/**
 * TODO Add some meaningful class description...
 */
public class DeleteWholeSpace implements ECARule {

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
		if (!(args[0] instanceof Space)) {
			throw new IllegalArgumentException("'args[0]' is not a Space");
		}

		Space space = (Space) args[0];
		Service service = ServiceImpl.getInstance();
		for (Document document : service.find(space)) {
			service.delete(document);
		}
	}

}
