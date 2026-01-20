package fn10.musicexpansion.menu;

import fn10.musicexpansion.reg.MusicExpandedItems;
import fn10.musicexpansion.reg.MusicExpandedMenus;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DiscBurnerMenu extends AbstractContainerMenu {

    public final Slot INSERTED_DISC_SLOT;

    public DiscBurnerMenu(Inventory plrInventory, int i) {
        this(plrInventory, new SimpleContainer(3), i);
    }

    public DiscBurnerMenu(Inventory plrInventory, Container discBurner, int i) {
        super(MusicExpandedMenus.DISC_BURNER_MENU, i);

        checkContainerSize(discBurner, 3);
        addStandardInventorySlots(plrInventory, 8, 81);
        INSERTED_DISC_SLOT = new DiscSlot(discBurner, 0, 80, 15);

        addSlot(INSERTED_DISC_SLOT);
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

        public DiscSlot(Container container, int i, int j, int k) {
            super(container, i, j, k);
        }

        public boolean mayPlace(ItemStack itemStack) {
            return itemStack.is(MusicExpandedItems.CD);
        }

    }

}
