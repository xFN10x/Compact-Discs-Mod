package fn10.musicexpansion.menu;

import java.util.List;

import fn10.musicexpansion.reg.MusicExpandedItemComponents;
import fn10.musicexpansion.reg.MusicExpandedItems;
import fn10.musicexpansion.reg.MusicExpandedMenus;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DiscBurnerMenu extends AbstractContainerMenu {

    public final Slot INSERTED_DISC_SLOT;
    public final NonNullList<Slot> inputs;

    public DiscBurnerMenu(Inventory plrInventory, int i) {
        this(plrInventory, new SimpleContainer(3), i);
    }

    public DiscBurnerMenu(Inventory plrInventory, Container discBurner, int i) {
        super(MusicExpandedMenus.DISC_BURNER_MENU, i);

        checkContainerSize(discBurner, 3);
        addStandardInventorySlots(plrInventory, 8, 81);
        INSERTED_DISC_SLOT = new DiscSlot(discBurner, 0, 80, 13);
        inputs = NonNullList.of(
            new DiscSlot(discBurner, 1, 62, 53).needsMusic(), 
            new DiscSlot(discBurner, 2, 98, 53).needsMusic()
        );
        addSlot(INSERTED_DISC_SLOT);
        addSlot(inputs.get(0));
        addSlot(inputs.get(1));
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public class DiscSlot extends Slot {

        private boolean needsMusic = false;

        public DiscSlot(Container container, int i, int j, int k) {
            super(container, i, j, k + 2); // idk why this is 2 pixels off
        }

        public DiscSlot needsMusic() {
            needsMusic = true;
            return this;
        }

        public boolean mayPlace(ItemStack itemStack) {
            return itemStack.is(MusicExpandedItems.CD) && (needsMusic ? itemStack.has(MusicExpandedItemComponents.CD_SONGS) : true);
        }

    }

}
