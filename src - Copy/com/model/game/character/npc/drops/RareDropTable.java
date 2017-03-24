package com.model.game.character.npc.drops;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.model.game.item.GameItem;




/**
 * 
 * @author Optimum
 */
public class RareDropTable 
{
    private static final Random random = new SecureRandom();

	private List<NpcDrop> possibleDrops;
	
    private HashMap<DropType, Float> dropChances;

    public RareDropTable(List<NpcDrop> possibleDrops, HashMap<DropType, Float> dropChances)
    {
        this.possibleDrops = possibleDrops;
        this.dropChances = dropChances;
    }

    private GameItem getRandomFor(DropType dropType)
    {
    	GameItem item = null;

        while (item == null)
        {
            NpcDrop selected = possibleDrops.get(random.nextInt(possibleDrops.size()));

            if (selected.getDropType() == dropType)
            {
                item = selected.getRandomAmount();
            }
        }

        return item;
    }

    public GameItem generateDropList(float percentIncrease)
    {
    	GameItem itemToDrop = null;

        for(NpcDrop specials : possibleDrops) //Special Table drops
        {
            if (specials.getDropType() == DropType.SPECIAL)
            {
                if (specials.roll(percentIncrease))
                {
                    itemToDrop = specials.getRandomAmount();
                    break;
                }
            }
        }

        if (itemToDrop == null)
        {
            if (contains(DropType.VERY_RARE))
            {
                if (PercentageRoll.roll(NpcDropData.calculatePercentIncrease(getChanceFor(DropType.VERY_RARE), percentIncrease)))
                   return getRandomFor(DropType.VERY_RARE);
            }
        }
        if (itemToDrop == null)
        {
            if (contains(DropType.RARE))
            {
                if (PercentageRoll.roll(NpcDropData.calculatePercentIncrease(getChanceFor(DropType.RARE), percentIncrease)))
                    return itemToDrop = getRandomFor(DropType.RARE);
            }
        }
        if (itemToDrop == null)
        {
            if (contains(DropType.UNCOMMON))
            {
                if (PercentageRoll.roll(NpcDropData.calculatePercentIncrease(getChanceFor(DropType.UNCOMMON), percentIncrease)))
                    return itemToDrop = getRandomFor(DropType.UNCOMMON);
            }
        }
        if (itemToDrop == null)
        {
            if (contains(DropType.COMMON))
            {
                if (PercentageRoll.roll(NpcDropData.calculatePercentIncrease(getChanceFor(DropType.COMMON), percentIncrease)))
                    return itemToDrop = getRandomFor(DropType.COMMON);
            }
        }

        return itemToDrop;
    }

    private boolean contains(DropType d)
    {
        for (NpcDrop entry : possibleDrops)
        {
            if (entry.getDropType() == d)
                return true;
        }
        return false;
    }

    private float getChanceFor(DropType d)
    {
        for (Map.Entry<DropType, Float> entry : dropChances.entrySet())
        {
            if (entry.getKey() == d)
                return entry.getValue();
        }
        return 0;
    }
	
    /**
   	 * @return the possibleDrops
   	 */
   	public List<NpcDrop> getPossibleDrops() {
   		return possibleDrops;
   	}

   	/**
   	 * @param possibleDrops the possibleDrops to set
   	 */
   	public void setPossibleDrops(List<NpcDrop> possibleDrops) {
   		this.possibleDrops = possibleDrops;
   	}

   	/**
   	 * @return the dropChances
   	 */
   	public HashMap<DropType, Float> getDropChances() {
   		return dropChances;
   	}

   	/**
   	 * @param dropChances the dropChances to set
   	 */
   	public void setDropChances(HashMap<DropType, Float> dropChances) {
   		this.dropChances = dropChances;
   	}
    
}//Optimums code
