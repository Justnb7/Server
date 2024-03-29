package com.model.utility.json;

import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.utility.json.definitions.ItemDefinition;

/**
 * The {@link JsonLoader} implementation that loads all item definitions.
 *
 * @author lare96 <http://github.com/lare96>
 */
public final class ItemDefinitionLoader extends JsonLoader {

    /**
     * Creates a new {@link ItemDefinitionLoader}.
     */
    public ItemDefinitionLoader() {
        super("./data/json/item_definitions.json");
    }

    @Override
    public void load(JsonObject reader, Gson builder) {
    	int index = reader.get("id").getAsInt();
        String name = Objects.requireNonNull(reader.get("name").getAsString());
        String description = Objects.requireNonNull(reader.get("examine").getAsString());
        int equipmentSlot = reader.get("equipmentType").getAsInt();
        boolean noteable = reader.get("noteable").getAsBoolean();
        boolean stackable = reader.get("stackable").getAsBoolean();
        int specialPrice = reader.get("specialStorePrice").getAsInt();
        int generalPrice = reader.get("generalStorePrice").getAsInt();
        int highAlchValue = reader.get("highAlchValue").getAsInt();
        int lowAlchValue = reader.get("lowAlchValue").getAsInt();
        double weight = reader.get("weight").getAsDouble();
        int[] bonus = builder.fromJson(reader.get("bonuses").getAsJsonArray(), int[].class);
        boolean twoHanded = reader.get("twoHanded").getAsBoolean();
        boolean platebody = reader.get("platebody").getAsBoolean();
        boolean fullHelm = reader.get("fullHelm").getAsBoolean();
        boolean fullMask = reader.has("fullMask") ? reader.get("fullMask").getAsBoolean() : false;
        boolean tradeable = reader.get("tradeable").getAsBoolean();
        ItemDefinition.DEFINITIONS[index] = new ItemDefinition(index, name, description, equipmentSlot, noteable, stackable, specialPrice,
            generalPrice, lowAlchValue, highAlchValue, weight, bonus, twoHanded, fullHelm, fullMask, platebody, tradeable);

    }
}