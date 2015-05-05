package example;

import core.ECAEngine;
import domain.*;

/**
 * TODO Add some meaningful class description...
 */
public class Application {

	private static final String BANKING = "A bank is a financial intermediary and money creator that creates money by" +
			" " +
			"lending money to a borrower, thereby creating a corresponding deposit on the bank's balance sheet.\n" +
			"Lending activities can be performed directly by loaning or indirectly through capital markets.\n" +
			"Due to their importance in the financial system and influence on national economies, banks are highly " +
			"regulated in most countries.\n" +
			"Most nations have institutionalised a system known as fractional reserve banking, under which banks hold" +
			" " +
			"liquid assets equal to only a portion of their current liabilities.\n" +
			"In addition to other regulations intended to ensure liquidity, banks are generally subject to minimum " +
			"capital requirements based on an international set of capital standards, known as the Basel Accords.\n" +
			"Banking in its modern sense evolved in the 14th century in the rich cities of Renaissance Italy but in " +
			"many ways was a continuation of ideas and concepts of credit and lending that had its roots in the " +
			"ancient world.\n" +
			"In the history of banking, a number of banking dynasties—notably the Medicis, the Fuggers, the Welsers," +
			" " +
			"the Berenbergs, and the Rothschilds—have played a central role over many centuries.\n" +
			"The oldest existing retail bank is Monte dei Paschi di Siena, while the oldest existing merchant bank is" +
			" " +
			"Berenberg Bank.\n";

	private static final String VEHICLE = "A car is a wheeled, self-powered motor vehicle used for transportation.\n" +
			"Most definitions of the term specify that cars are designed to run primarily on roads, to have seating " +
			"for one to eight people, to typically have four wheels, and to be constructed principally for the " +
			"transport of people rather than goods.[3][4]\n" +
			"The year 1886 is regarded as the birth year of the modern car.\n" +
			"In that year, German inventor Karl Benz built the Benz Patent-Motorwagen.\n" +
			"Cars did not become widely available until the early 20th century.\n" +
			"One of the first cars that was accessible to the masses was the 1908 Model T, an American car " +
			"manufactured by the Ford Motor Company.\n" +
			"Cars were rapidly adopted in the United States of America, where they replaced animal-drawn carriages " +
			"and" +
			" carts, but took much longer to be accepted in Western Europe and other less-developed parts of the " +
			"world" +
			".\n" +
			"Cars are equipped with controls used for driving, parking, and passenger comfort and safety.\n" +
			"New controls have also been added to vehicles, making them more complex.\n" +
			"Examples include air conditioning, navigation systems, and in car entertainment.\n" +
			"Most cars in use today are propelled by an internal combustion engine, fueled by deflagration of " +
			"gasoline" +
			" (also known as petrol) or diesel.\n" +
			"Both fuels are known to cause air pollution and are also blamed for contributing to climate change and " +
			"global warming.[5]\n" +
			"Vehicles using alternative fuels such as ethanol flexible-fuel vehicles and natural gas vehicles are " +
			"also" +
			" gaining popularity in some countries.\n";

	public static void main(String[] args) {

		Service service = ServiceImpl.getInstance();
		Config config = service.save(new ConfigImpl());
		Space space = service.save(new SpaceImpl("MySpace"));
		Document doc1 = service.save(new DocumentImpl("banking.txt", BANKING, space));
		Document doc2 = service.save(new DocumentImpl("vehicle.txt", VEHICLE, space));
//		System.out.println(service);

//		service.delete(space);
//		System.out.println(service);

		for (Document document : service.find(space)) {
			System.out.println(service.find(document, config));
		}
		config = service.save(new ConfigImpl());
		for (Document document : service.find(space)) {
			System.out.println(service.find(document, config));
		}

		System.err.println("Done.");
	}
}
