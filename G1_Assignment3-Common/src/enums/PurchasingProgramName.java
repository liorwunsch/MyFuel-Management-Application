package enums;

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