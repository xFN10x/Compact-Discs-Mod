package fn10.musicexpansion.blocks.entity;

import fn10.musicexpansion.blocks.DiscBurnerBlock;
import fn10.musicexpansion.menu.DiscBurnerMenu;
import fn10.musicexpansion.reg.MusicExpandedAudio;
import fn10.musicexpansion.reg.MusicExpandedBlockEntitys;
import fn10.musicexpansion.reg.MusicExpandedItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DiscBurnerBlockEntity extends BaseContainerBlockEntity {

    public NonNullList<ItemStack> inventory;
    public int burnTime = 700;
    public boolean isBurning = false;
    //public SimpleContainerData data = new SimpleContainerData(2);

    public DiscBurnerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MusicExpandedBlockEntitys.DISC_BURNER_BENTITY, blockPos, blockState);
        inventory = NonNullList.withSize(3, ItemStack.EMPTY);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("menu.container.disc_burner");
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
        world.setBlockAndUpdate(blockPos,
                blockState.setValue(DiscBurnerBlock.LOADED, !entity.inventory.get(0).isEmpty()));
        ItemStack input1 = entity.inventory.get(1);
        ItemStack input2 = entity.inventory.get(2);
        if (input1.is(MusicExpandedItems.CD) && input2.is(MusicExpandedItems.CD)) {
            if (!entity.isBurning) {
                entity.isBurning = true;
                world.playSound(null, blockPos, MusicExpandedAudio.DISC_BURNER_START, SoundSource.BLOCKS);

            } else {
                entity.burnTime--;

            }
        }
    }
}
