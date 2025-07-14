package fr.ambi.gui;

import fr.ambi.gui.components.Button;
import fr.ambi.gui.utils.ClickContext;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PaginatedGui extends Gui {

    private final List<Button> elements = new ArrayList<>();
    private final List<Integer> displaySlots = new ArrayList<>();
    private int currentPage = 0;
    private int pageSize;

    private Button nextPageButton;
    private Button previousPageButton;
    private int nextPageSlot = -1;
    private int previousPageSlot = -1;

    private PaginatedGui(Component title, int rows, int pageSize) {
        super(title, rows);
        this.pageSize = pageSize > 0 ? pageSize : rows * 9;
    }

    public static PaginatedGuiBuilder paginated() {
        return new PaginatedGuiBuilder();
    }

    public void addElement(Button button) {
        elements.add(button);
    }

    public void addElement(ItemStack item, java.util.function.Consumer<ClickContext> action) {
        elements.add(Button.of(item, action));
    }

    public void setDisplaySlots(Integer... slots) {
        displaySlots.clear();
        for (Integer s : slots) displaySlots.add(s);
    }

    public void setNextPageButton(ItemStack item, int slot) {
        this.nextPageButton = Button.of(item, ctx -> nextPage(ctx.getPlayer()));
        this.nextPageSlot = slot;
        setButton(slot, nextPageButton);
    }

    public void setPreviousPageButton(ItemStack item, int slot) {
        this.previousPageButton = Button.of(item, ctx -> previousPage(ctx.getPlayer()));
        this.previousPageSlot = slot;
        setButton(slot, previousPageButton);
    }

    @Override
    public void open(Player player) {
        showPage(player);
    }

    private void showPage(Player player) {
        for (int slot : displaySlots) setButton(slot, null);

        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, elements.size());

        for (int i = start; i < end; i++) {
            int slot = displaySlots.size() > 0
                    ? displaySlots.get(i - start)
                    : i - start; // si pas de slots définis, juste de 0 à pageSize -1
            setButton(slot, elements.get(i));
        }

        if (nextPageButton != null) setButton(nextPageSlot, nextPageButton);
        if (previousPageButton != null) setButton(previousPageSlot, previousPageButton);

        super.open(player);
    }

    private void nextPage(Player player) {
        if ((currentPage + 1) * pageSize < elements.size()) {
            currentPage++;
            showPage(player);
        }
    }

    private void previousPage(Player player) {
        if (currentPage > 0) {
            currentPage--;
            showPage(player);
        }
    }

    // Builder interne
    public static class PaginatedGuiBuilder {
        private Component title;
        private int rows = 6;
        private int pageSize = -1;

        public PaginatedGuiBuilder title(Component title) {
            this.title = title;
            return this;
        }

        public PaginatedGuiBuilder rows(int rows) {
            this.rows = rows;
            return this;
        }

        public PaginatedGuiBuilder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PaginatedGui create() {
            int size = pageSize > 0 ? pageSize : rows * 9;
            return new PaginatedGui(title, rows, size);
        }
    }
}
