package com.model.game.character.combat.npcs;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.model.game.character.combat.npcs.script.Dragons;
import com.model.game.character.combat.npcs.script.KetZek;
import com.model.game.character.combat.npcs.script.TzTokJad;


/**
 * All the bosses that have a custom script
 * @author Patrick van Elderen
 * @date Feb, 23-2-2016
 */
public class BossScripts {
	
	private static Map<Integer, AbstractBossCombat> bosses = new HashMap<>();
	
	
	private static final KetZek KET_ZEK = new KetZek(3125);
	private static final TzTokJad JAD = new TzTokJad(3127);
	private static final int[] DRAGONS2 = {247, 252, 264, 268, 270, 273, 274};
	private static final Dragons DRAGONS = new Dragons(247);
	
	static {
		
		bosses.put(KET_ZEK.npcId, KET_ZEK);
		bosses.put(JAD.npcId, JAD);
		bosses.put(DRAGONS.npcId, DRAGONS);

	}

	
	public static AbstractBossCombat get(int npcId) {
		if (!bosses.containsKey(npcId))
			return null;
		return bosses.get(npcId);
	}
	
	public static boolean isBoss(int npcId) {
		return Objects.nonNull(get(npcId));
	}
}
