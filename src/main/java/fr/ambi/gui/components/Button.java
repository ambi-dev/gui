package fr.ambi.gui.components;

import fr.ambi.gui.utils.ClickContext;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class Button {

    private final ItemStack itemstack;
    private final Consumer<ClickContext> action;

    public Button(ItemStack itemStack, Consumer<ClickContext> action) {
        this.itemstack = itemStack;
        this.action = action;
    }

    public ItemStack getItemStack() {return this.itemstack;}
    public void click(ClickContext context) {if (action != null) action.accept(context);}
    public static Button of(ItemStack itemStack, Consumer<ClickContext> action) {
        return new Button(itemStack, action);
    }

}
