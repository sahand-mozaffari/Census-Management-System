package ui;

enum Option {
	Exit {
		@Override
		public String toString() {
			return "Exit";
		}

		@Override
		void trigger() {
			System.exit(0);
		}
	};

	abstract void trigger();
}
