package com.model.game.character.combat.npcs.script;

import com.model.Server;
import com.model.game.character.Animation;
import com.model.game.character.Entity;
import com.model.game.character.Graphic;
import com.model.game.character.Hit;
import com.model.game.character.combat.Combat;
import com.model.game.character.combat.CombatFormulae;
import com.model.game.character.combat.Projectile;
import com.model.game.character.combat.combat_data.CombatType;
import com.model.game.character.combat.npcs.AbstractBossCombat;
import com.model.game.character.npc.NPC;
import com.model.task.ScheduledTask;
import com.model.utility.Utility;

public class Dragons extends AbstractBossCombat {

	public Dragons(int npcId) {
		super(npcId);
	}


	@Override
	public void execute(Entity attacker, Entity victim) {
		if(!attacker.isNPC()) {
			return;
		}
		CombatType style =  Utility.getRandom(2) == 0 ? CombatType.DRAGON_FIRE :  attacker.getPosition().distanceToEntity(attacker, victim) <= 2 ? CombatType.MELEE : CombatType.DRAGON_FIRE;
		NPC npc = (NPC) attacker;
		int maxHit = style == CombatType.DRAGON_FIRE ? 45 : 25;
		switch (style) {
		case MELEE:
			npc.playAnimation(Animation.create(npc.getDefinition().getAttackAnimation()));
			int randomHit = Utility.random(maxHit);
            Hit hitInfo = victim.take_hit(attacker, randomHit, CombatType.MELEE, false);
            Combat.hitEvent(attacker, victim, 1, hitInfo, CombatType.MELEE);
			break;
		case DRAGON_FIRE:
			npc.playAnimation(Animation.create(81));
			int delay = 1;
			randomHit = Utility.random(maxHit);
            hitInfo = victim.take_hit(attacker, randomHit - (int) (randomHit *CombatFormulae.dragonfireReduction(victim)), CombatType.DRAGON_FIRE, false);
            attacker.playGraphics(Graphic.create(1));
            Combat.hitEvent(attacker, victim, delay, hitInfo, CombatType.DRAGON_FIRE);
			break;
		default:
			break;
		
		}
		((NPC)attacker).attackTimer = (style == CombatType.MAGIC ? 6 : 5);
	}


	@Override
	public int distance(Entity attacker) {
		return 4;
	}

}