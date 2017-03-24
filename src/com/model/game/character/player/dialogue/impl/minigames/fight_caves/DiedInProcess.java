package com.model.game.character.player.dialogue.impl.minigames.fight_caves;

import com.model.game.character.player.dialogue.Dialogue;
import com.model.game.character.player.dialogue.Expression;
import com.model.game.character.player.dialogue.Type;

public class DiedInProcess extends Dialogue {

	private static int TZHAAR_MEJ_JAL = 2180;
	
	@Override
	protected void start(Object... parameters) {
		send(Type.NPC, TZHAAR_MEJ_JAL, Expression.CALM_TALK, "Well done in the cave,", "here, take TokKul as reward.");
		setPhase(0);
	}
	
	@Override
	protected void next() {
		System.out.println("next : phase " + getPhase());
		if (getPhase() == 0) {
			stop();
		}
	}

}