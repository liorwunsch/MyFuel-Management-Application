package enums;

/**
 * @author Elroy, Vlad, Lior
 */
public enum CustomerType {
	Person {
		public String toString() {
			return "Person";
		}
	},
	Company {
		public String toString() {
			return "Company";
		}
	};

	public static double getRank(String customerType) {
		if (customerType.equals("Person"))
			return 5;
		else if (customerType.equals("Company"))
			return 10;
		return 0;
	}
}
