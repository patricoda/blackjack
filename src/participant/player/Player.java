package participant.player;

import participant.Participant;

public class Player extends Participant {
	private final String name;

	public Player(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
