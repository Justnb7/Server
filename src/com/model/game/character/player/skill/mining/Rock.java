package com.model.game.character.player.skill.mining;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * An enumeration of ore vein information.
 * 
 * @author Jason MacKeigan
 * @date Feb 18, 2015, 5:14:50 PM
 */
public enum Rock {
	
	COPPER(new int[] {7484, 7453, 13450, 2090, 11961, 11960, 11962}, 436, 1, 18, 0, 5, 15, 10000),
	TIN(new int[] {7485, 7486, 13447, 2095, 9714, 9716, 11957, 11958, 11959}, 438, 1, 18, 0, 5, 15, 10000),
	IRON(new int[] {7488, 7455, 13710, 2092, 11954, 11955, 11956, 13710}, 440, 15, 35, 5, 8, 18, 6000),
	COAL(new int[] {7456, 7485, 7489, 9717, 9718, 9719, 13714, 13714}, 453, 30, 50, 3, 15, 23, 5000),
	GOLD(new int[] {7491, 7458, 13707, 2099, 9722, 9720, 13707}, 444, 40, 65, 3, 25, 25, 4500),
	MITHRIL(new int[] {7492, 7459, 13718, 13718}, 447, 55, 80, 3, 40, 27, 3500),
	ADAMANTITE(new int[] {7460, 7493, 13720 , 2105, 13720}, 449, 70, 95, 2, 50, 30, 3000),
	VOLCANIC_SULPHUR(new int[] {28498}, 13571, 42, 60, 2, 50, 30, 3000),
	RUNITE(new int[] {7494, 7461, 14175}, 451, 85, 125, 0, 100, 40, 1000);
	
	/**
	 * An array of object ids that are associated with the mineral obtained from them
	 */
	private final int[] objectIds;
	
	/**
	 * The mineral extracted from the object
	 */
	private final int mineral;
	
	/**
	 * The level required to mine this ore
	 */
	private final int level;
	
	/**
	 * The experience gained from mining this mineral
	 */
	private final int experience;
	
	/**
	 * The probability that the mineral will deplete
	 */
	private final int depletionProbability;
	
	/**
	 * The amount of cycles that need to pass before the mineral is extractable
	 */
	private final int respawnRate;
	
	/**
	 * The default amount of cycles it takes to extract ore from a vein
	 */
	private final int extractionRate;
	
	private final int petChance;
	
	/**
	 * Constructs a new mineral
	 * @param objectIds		the objects that the mineral can be extracted from
	 * @param mineral		the item identification value of the mineral extracted
	 * @param level			the level required to extract minerals from the object(s)		
	 * @param experience	the experience gain after extraction
	 */
	private Rock(int[] objectIds, int mineral, int level, int experience, int depletionProbability, int respawnRate, int extractionRate, int petChance) {
		this.objectIds = objectIds;
		this.mineral = mineral;
		this.level = level;
		this.experience = experience;
		this.depletionProbability = depletionProbability;
		this.respawnRate = respawnRate;
		this.extractionRate = extractionRate;
		this.petChance = petChance;
	}
	
	/**
	 * The array of objectId values associated with this mineral
	 * @return	the array of object id's 
	 */
	public int[] getObjectIds() {
		return objectIds;
	}
	
	/**
	 * The mineral extracted from an object
	 * @return	the identification value of the mineral received
	 */
	public int getMineral() {
		return mineral;
	}
	
	/**
	 * The level required to extract minerals
	 * @return	the level required
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * The experience gained from extracting a mineral
	 * @return	the experience gained
	 */
	public int getExperience() {
		return experience;
	}
	
	/**
	 * The rate, in cycles, the mineral respawns at
	 * @return	the rate at which the mineral respawns
	 */
	public int getRespawnRate() {
		return respawnRate;
	}
	
	/**
	 * Every mineral has a difference rate at which they deplate. Some minerals
	 * faster then others, and some minerals instantly after being extracted from.
	 * @return	the probability of depletion. When the probability is 0 the chance
	 * of depletion is 1:1.
	 */
	public int getDepletionProbability() {
		return depletionProbability;
	}
	
	/**
	 * The default amount of cycles it takes for a single ore to be extracted
	 * @return	the default extraction rate
	 */
	public int getExtractionRate() {
		return extractionRate;
	}
	
	public int getPetChance() {
		return petChance;
	}
	
	/**
	 * The identification value of the object with no mineral remaining after extraction
	 */
	public static final int EMPTY_ROCK = 7469;
	
	/**
	 * An unmodification set of {@link Rock} objects that will be used as a constant
	 * for obtaining information about certain minerals.
	 */
	private static final Set<Rock> MINERALS = Collections.unmodifiableSet(EnumSet.allOf(Rock.class));
	
	/**
	 * Retrieves the {@link Rock} object with the same objectId 
	 * as the parameter passed.
	 * @param objectId	the object id of the mineral
	 * @return	the mineral object with the corresponding object id
	 */
	public static Rock forObjectId(int objectId) {
		for (Rock mineral : MINERALS) {
			for (int objId : mineral.objectIds) {
				if (objId == objectId) {
					return mineral;
				}
			}
		}
		return null;
	}

}