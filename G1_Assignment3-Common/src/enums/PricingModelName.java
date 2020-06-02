package enums;

public enum PricingModelName {
	PayInPlace {
		public String toString() {
			return "Pay In Place";
		}
	},
	MonthlyProgramSingleCar {
		public String toString() {
			return "Monthly Program Single Car";
		}
	},
	MonthlyProgramMultipleCars {
		public String toString() {
			return "Monthly Program Multiple Cars";
		}
	},
	FullProgramSingleCar {
		public String toString() {
			return "Full Program Single Car";
		}
	};
}
