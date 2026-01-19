package fn10.musicexpansion.blocks.entity;

import fn10.musicexpansion.MusicExpanded;
import fn10.musicexpansion.blocks.DiscBurnerBlock;
import fn10.musicexpansion.menu.DiscBurnerMenu;
import fn10.musicexpansion.reg.MusicExpandedBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DiscBurnerBlockEntity extends BaseContainerBlockEntity {

    public NonNullList<ItemStack> inventory;

    public DiscBurnerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MusicExpandedBlockEntitys.DISC_BURNER_BENTITY, blockPos, blockState);
        inventory = NonNullList.withSize(3, ItemStack.EMPTY);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("test");
    }

    @Override
    public int getContainerSize() {
        return inventory.size();
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new DiscBurnerMenu(inventory, this, i);

    }

    @Override
    protected Component getDefaultName() {
        return getDisplayName();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        inventory = nonNullList;
    }

    public static void tick(Level world, BlockPos blockPos, BlockState blockState, DiscBurnerBlockEntity entity) {
        MusicExpanded.LOGGER.info("tick " + !entity.inventory.get(0).isEmpty());
        world.setBlockAndUpdate(blockPos, blockState.setValue(DiscBurnerBlock.LOADED, !entity.inventory.get(0).isEmpty()));
    }
}
