package com.model.game.character.player.account.account_type;

import java.util.Arrays;
import java.util.List;

import com.model.game.character.player.account.Account;
import com.model.game.character.player.account.AccountType;

/**
 * Represents an ultimate iron man account, a type chosen by a player.
 *
 * @author Patrick van Elderen
 */
public class UltimateIronManAccount extends AccountType {

	@Override
	public String alias() {
		return "Ultimate Iron Man";
	}

	@Override
	public int getPrivilege() {
		return 23;
	}

	@Override
	public boolean unownedDropsVisible() {
		return false;
	}

	@Override
	public boolean tradingPermitted() {
		return true;
	}

	@Override
	public boolean stakingPermitted() {
		return true;
	}
	
	@Override
    public List<String> attackableTypes() {
        return Arrays.asList(Account.IRON_MAN_TYPE.alias(), Account.ULTIMATE_IRON_MAN_TYPE.alias(), Account.HARDCORE_IRON_MAN_TYPE.alias());
    }

	@Override
	public boolean shopAccessible(String shop) {
		return false;
	}

	@Override
	public boolean changable() {
		return false;
	}

	@Override
	public boolean dropAnnouncementVisible() {
		return false;
	}

	@Override
	public int modeType() {
		return 2;
	}

	@Override
	public boolean loseStatusOnDeath() {
		return false;
	}

	@Override
	public boolean canBank() {
		return false;
	}

	@Override
	public boolean canUseItemProtection() {
		return false;
	}

}