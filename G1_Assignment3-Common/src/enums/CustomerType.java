package enums;

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
}
