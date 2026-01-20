package fn10.musicexpansion.blocks.entity;

import fn10.musicexpansion.blocks.DiscBurnerBlock;
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

public class StereoBlockEntity extends BaseContainerBlockEntity {
    public NonNullList<ItemStack> inventory;

    public StereoBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MusicExpandedBlockEntitys.STEREO_BENTITY, blockPos, blockState);
        inventory = NonNullList.withSize(3, ItemStack.EMPTY);
    }

    @Override
    public int getContainerSize() {
        return inventory.size();
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createMenu'");
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("menu.container.stereo");
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
        world.setBlockAndUpdate(blockPos,
                blockState.setValue(DiscBurnerBlock.LOADED, !entity.inventory.get(0).isEmpty()));
    }
}
