package fr.ambi.gui;

import fr.ambi.gui.components.Button;
import fr.ambi.gui.managers.GuiManager;
import fr.ambi.gui.utils.GuiType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class Gui {

    private final Component title;
    private final GuiType type;
    private final int rows;
    private final Map<Integer, Button> buttons = new HashMap<>();
    private final Inventory inventory;

    public static GuiBuilder gui() {
        return new GuiBuilder();
    }

    Gui(Component title, int rows) {
        this.title = title;
        this.rows = Math.max(1, Math.min(6, rows)); // 1 à 6 lignes max
        this.type = GuiType.DOUBLE_CHEST;
        this.inventory = Bukkit.createInventory(null, this.rows * 9, title);
    }

    Gui(Component title, GuiType type) {
        this.title = title;
        this.type = type;
        this.rows = -1;
        this.inventory = Bukkit.createInventory(null, type.getInventoryType(), title);
    }

    public void setButton(int slot, Button button) {
        if (button == null) {
            buttons.remove(slot);
            inventory.setItem(slot, null);
        } else {
            buttons.put(slot, button);
            inventory.setItem(slot, button.getItemStack());
        }
    }

    public void open(Player player) {
        GuiManager.register(player, this);
        player.openInventory(inventory);
    }

    public Button getButton(int slot) {
        return buttons.get(slot);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public static class GuiBuilder {
        private Component title;
        private int rows = 6;
        private GuiType type = null;

        public GuiBuilder title(Component title) {
            this.title = title;
            return this;
        }

        public GuiBuilder rows(int rows) {
            this.rows = rows;
            return this;
        }

        public GuiBuilder type(GuiType type) {
            this.type = type;
            return this;
        }

        public Gui create() {
            return type != null ? new Gui(title, type) : new Gui(title, rows);
        }
    }

    public int getRows() {
        return rows;
    }
}
