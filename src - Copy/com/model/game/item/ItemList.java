package com.model.game.item;

public class ItemList {
	public int itemId;
	public String itemName;
	public String itemDescription;
	public double ShopValue;
	public double LowAlch;
	public double HighAlch;
	public int[] Bonuses = new int[100];

	/**
	 * Gets the item ID.
	 * 
	 * @param _itemId
	 */
	public ItemList(int _itemId) {
		itemId = _itemId;
	}
}