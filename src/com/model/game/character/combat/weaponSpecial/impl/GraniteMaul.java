package com.model.game.character.combat.weaponSpecial.impl;

import com.model.game.character.Animation;
import com.model.game.character.Entity;
import com.model.game.character.Graphic;
import com.model.game.character.combat.CombatFormulae;
import com.model.game.character.combat.combat_data.CombatType;
import com.model.game.character.combat.weaponSpecial.SpecialAttack;
import com.model.game.character.player.Player;
import com.model.utility.Utility;

public class GraniteMaul implements SpecialAttack {

	@Override
	public int[] weapons() {
		return new int[] { 4153, 12848 };
	}

	@Override
	public void handleAttack(final Player player, final Entity target) {
		int damage = Utility.random(player.getCombat().calculateMeleeMaxHit());
		
		player.playAnimation(Animation.create(1667));
		player.playGraphics(Graphic.create(337, 0, 100));

		boolean missed = !CombatFormulae.getAccuracy((Entity)player, (Entity)target, 0, getAccuracyMultiplier());
		if (missed)
			damage = 0;
		
		target.take_hit(player, damage, CombatType.MELEE).giveXP(player);
	}

	@Override
	public int amountRequired() {
		return 50;
	}

	@Override
	public boolean meetsRequirements(Player player, Entity victim) {
		return true;
	}
	
	@Override
	public double getAccuracyMultiplier() {
		return 1;
	}

	@Override
	public double getMaxHitMultiplier() {
		return 1;
	}
}