package fn10.musicexpansion.blocks.entity;

import java.util.Iterator;
import java.util.List;

import fn10.musicexpansion.blocks.DiscBurnerBlock;
import fn10.musicexpansion.blocks.StereoBlock;
import fn10.musicexpansion.music.CDTrack;
import fn10.musicexpansion.music.CDTracks;
import fn10.musicexpansion.reg.MusicExpandedBlockEntitys;
import fn10.musicexpansion.reg.MusicExpandedItemComponents;
import fn10.musicexpansion.reg.MusicExpandedItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.ValueOutput.TypedOutputList;

public class StereoBlockEntity extends BaseContainerBlockEntity {
    public NonNullList<ItemStack> inventory;
    public boolean playing = false;
    private Integer currentlyPlayingId = -1;

    public StereoBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MusicExpandedBlockEntitys.STEREO_BENTITY, blockPos, blockState);
        inventory = NonNullList.withSize(3, ItemStack.EMPTY);
    }

    public void putInCD(ItemStack stack) {
        if (!stack.is(MusicExpandedItems.CD)) return;
        inventory.set(0, stack);
        play(0);
    }

    public void play(int tracki) {
        if (currentlyPlayingId != -1) return;
        List<String> songList = inventory.get(0).get(MusicExpandedItemComponents.CD_SONGS);
        CDTrack track = CDTracks.getTrackFromId(songList.get(tracki));
        currentlyPlayingId = track.play(level, worldPosition);
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

    @Override
    protected void saveAdditional(ValueOutput output) {
        output.putBoolean("playing", playing);
        output.putInt("currentlyPlayingId", currentlyPlayingId);
        ContainerHelper.saveAllItems(output, inventory);

        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        playing = input.getBooleanOr("playing", false);
        currentlyPlayingId = input.getIntOr("currentlyPlayingId", -1);
        ContainerHelper.loadAllItems(input, inventory);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }

    public static void tick(Level world, BlockPos blockPos, BlockState blockState, StereoBlockEntity entity) {
        if (world.isClientSide())
            return;
        boolean bool = !entity.inventory.get(0).isEmpty();
        if (blockState.getValue(StereoBlock.LOADED) != bool)
            world.setBlockAndUpdate(blockPos,
                    blockState.setValue(StereoBlock.LOADED, bool));
    }
}
