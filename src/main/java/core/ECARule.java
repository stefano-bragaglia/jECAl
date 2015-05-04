package core;

/**
 * TODO Add some meaningful class description...
 */
public interface ECARule {
	boolean check(Object[] args);
	void execute(Object[] args);
}
