package fn10.musicexpansion.blocks.entity;

import java.util.List;

import fn10.musicexpansion.blocks.StereoBlock;
import fn10.musicexpansion.music.ActiveCDTrackInfo;
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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class StereoBlockEntity extends BaseContainerBlockEntity {
    public NonNullList<ItemStack> inventory;
    private boolean playing = false;
    private ActiveCDTrackInfo currentlyPlayingInfo = new ActiveCDTrackInfo(-1, -1);
    private Integer nextTrackTime = -1;
    private Integer trackIndex = -1;

    public StereoBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MusicExpandedBlockEntitys.STEREO_BENTITY, blockPos, blockState);
        inventory = NonNullList.withSize(3, ItemStack.EMPTY);
    }

    public void ejectCD() {
        if (!(level instanceof ServerLevel))
            return;
        playing = false;
        stopCurrentTrack();
        nextTrackTime = -1;
        trackIndex = -1;
        currentlyPlayingInfo = new ActiveCDTrackInfo(-1, -1);
        Block.popResource(level, worldPosition, inventory.get(0));
        inventory.set(0, ItemStack.EMPTY);
    }

    public void stopCurrentTrack() {
        CDTrack track = CDTracks.getCDTrackFromPlayingID(currentlyPlayingInfo.id());
        track.stop(((ServerLevel) level));
    }

    public void nextTrack() {
        if (playing) stopCurrentTrack();
        trackIndex++;
        play();
    }

    public void putInCD(ItemStack stack) {
        if (!stack.is(MusicExpandedItems.CD))
            return;
        inventory.set(0, stack);
    }

    protected void play() {
        play(trackIndex);
    }

    protected void play(int tracki) {
        if (!(level instanceof ServerLevel))
            return;
        if (playing)
            return;
        List<String> songList = inventory.get(0).get(MusicExpandedItemComponents.CD_SONGS);
        if (songList.size() <= tracki) {
            tracki = 0;
            trackIndex = 0;
        }
        CDTrack track = CDTracks.getTrackFromId(songList.get(tracki));
        playing = true;
        currentlyPlayingInfo = track.play(((ServerLevel) level), worldPosition);
        nextTrackTime = currentlyPlayingInfo.length();
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
        output.putInt("currentlyPlayingId", currentlyPlayingInfo.id());
        output.putInt("currentlyPlayingLength", currentlyPlayingInfo.length());
        output.putInt("nextTrackTime", nextTrackTime);
        ContainerHelper.saveAllItems(output, inventory);

        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        playing = input.getBooleanOr("playing", false);
        currentlyPlayingInfo = new ActiveCDTrackInfo(input.getIntOr("currentlyPlayingId", -1),
                input.getIntOr("currentlyPlayingLength", 20));
        nextTrackTime = input.getIntOr("nextTrackTime", 20);
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

        if (blockState.getValue(StereoBlock.PLAYING) != entity.playing)
            world.setBlockAndUpdate(blockPos,
                    blockState.setValue(StereoBlock.PLAYING, entity.playing));

        if (entity.inventory.get(0).is(MusicExpandedItems.CD)) {
            if (entity.nextTrackTime > 0) {
                entity.nextTrackTime--;
            } else {
                entity.playing = false;
                entity.nextTrack();
            }
        }
    }
}
