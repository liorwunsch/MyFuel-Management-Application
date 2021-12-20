package enums;

/**
 * @author Elroy, Vlad, Lior
 */
public enum Affiliation {
	Management {
		public String toString() {
			return "Management";
		}
	},
	Marketing {
		public String toString() {
			return "Marketing";
		}
	},
	FuelStation {
		public String toString() {
			return "Fuel Station";
		}
	},
	Supplier {
		public String toString() {
			return "Supplier";
		}
	};
}
