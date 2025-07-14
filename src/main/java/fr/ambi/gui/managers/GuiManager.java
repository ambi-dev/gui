package fr.ambi.gui.managers;

import fr.ambi.gui.Gui;
import fr.ambi.gui.components.Button;
import fr.ambi.gui.utils.ClickContext;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;

public class GuiManager implements Listener {

    private static final Map<Player, Gui> openGuis = new HashMap<>();

    public static void register(Player player, Gui gui) {
        openGuis.put(player, gui);
    }

    public static void unregister(Player player) {
        openGuis.remove(player);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        Gui gui = openGuis.get(player);
        if (gui == null) return;

        if (!event.getView().getTopInventory().equals(gui.getInventory())) return;

        event.setCancelled(true);

        int rawSlot = event.getRawSlot();
        Button button = gui.getButton(rawSlot);
        if (button != null) {
            ClickContext ctx = new ClickContext(player, event.getClick(), event);
            button.click(ctx);
        }
    }
}
