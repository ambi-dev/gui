package fr.ambi.gui.utils;

import org.bukkit.event.inventory.InventoryType;

public enum GuiType {
    CHEST(InventoryType.CHEST, 27),
    DOUBLE_CHEST(InventoryType.CHEST, 54),
    DISPENSER(InventoryType.DISPENSER, 9),
    HOPPER(InventoryType.HOPPER, 5),
    ANVIL(InventoryType.ANVIL, 3),
    BREWING(InventoryType.BREWING, 4),
    FURNACE(InventoryType.FURNACE, 3);

    private final InventoryType inventoryType;
    private final int size;

    GuiType(InventoryType type, int size) {
        this.inventoryType = type;
        this.size = size;
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public int getSize() {
        return size;
    }
}
