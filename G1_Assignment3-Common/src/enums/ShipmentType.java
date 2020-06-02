package enums;

/**
 * @author Elroy, Vlad, Lior
 */
public enum ShipmentType {
	Regular {
		public String toString() {
			return "Regular";
		}
	},
	Urgent {
		public String toString() {
			return "Urgent";
		}
	};
}