package enums;

/**
 * @author Elroy, Vlad, Lior
 */
public enum PurchasingProgramName {
	Standard {
		@Override
		public String toString() {
			return "Standard";
		}
	},
	Premium {
		@Override
		public String toString() {
			return "Premium";
		}
	};
}