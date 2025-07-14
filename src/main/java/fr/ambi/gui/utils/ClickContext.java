package fr.ambi.gui.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickContext {

    private final Player player;
    private final ClickType type;
    private final InventoryClickEvent event;

    public ClickContext(Player player, ClickType type, InventoryClickEvent event) {
        this.player = player;
        this.type = type;
        this.event = event;
    }

    public Player getPlayer() {
        return player;
    }

    public ClickType getType() {
        return type;
    }

    public InventoryClickEvent getEvent() {
        return event;
    }
}
