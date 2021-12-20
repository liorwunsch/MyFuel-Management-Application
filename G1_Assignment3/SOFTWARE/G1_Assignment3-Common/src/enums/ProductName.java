package enums;

/**
 * @author Elroy, Vlad, Lior
 */
public enum ProductName {
	Gasoline {
		public String toString() {
			return "Gasoline";
		}
	},
	Diesel {
		public String toString() {
			return "Diesel";
		}
	},
	MotorbikeFuel {
		public String toString() {
			return "Motorbike Fuel";
		}
	},
	HomeFuel {
		public String toString() {
			return "Home Fuel";
		}
	},

	Nan {
		public String toString() {
			return "Nan";
		}
	};
}
