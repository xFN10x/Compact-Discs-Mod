package fn10.musicexpansion.blocks.entity;

import java.util.Iterator;
import java.util.Map;

import fn10.musicexpansion.blocks.DiscBurnerBlock;
import fn10.musicexpansion.menu.DiscBurnerMenu;
import fn10.musicexpansion.reg.MusicExpandedAudio;
import fn10.musicexpansion.reg.MusicExpandedBlockEntitys;
import fn10.musicexpansion.reg.MusicExpandedItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.ValueOutput.TypedOutputList;

public class DiscBurnerBlockEntity extends BaseContainerBlockEntity {

    public NonNullList<ItemStack> inventory;
    public int burnTime = 700;
    public boolean isBurning = false;
    public boolean stoppedSound = true;
    public NonNullList<ItemStack> burningCurrently = NonNullList.withSize(2, ItemStack.EMPTY);
    public ContainerData data = new SimpleContainerData(1);

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
        return new DiscBurnerMenu(inventory, this, data, i);
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

    @Override
    protected void saveAdditional(ValueOutput output) {
        output.putBoolean("isBurning", isBurning);
        output.putInt("burnTime", burnTime);
        ContainerHelper.saveAllItems(output, inventory);
        TypedOutputList<ItemStack> list = output.list("burningCurrently", ItemStack.CODEC);
        burningCurrently.forEach(stack -> {
            list.add(stack);
        });

        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        isBurning = input.getBooleanOr("isBurning", false);
        burnTime = input.getIntOr("burnTime", 700);
        data.set(0, burnTime);
        ContainerHelper.loadAllItems(input, inventory);
        int i = 0;
        Iterator<ItemStack> list = input.list("burningCurrently", ItemStack.CODEC).get().iterator();
        while (list.hasNext()) {
            inventory.set(i, list.next());
            i++;
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public static void tick(Level world, BlockPos blockPos, BlockState blockState, DiscBurnerBlockEntity entity) {
        if (world.isClientSide())
            return;
        boolean loadedCurrently = !entity.inventory.get(0).isEmpty();
        boolean isMarkedLoaded = blockState.getValue(DiscBurnerBlock.LOADED);
        if (loadedCurrently != isMarkedLoaded) {
            world.setBlockAndUpdate(blockPos,
                    blockState.setValue(DiscBurnerBlock.LOADED, loadedCurrently));
        }

        boolean isMarkedBurning = blockState.getValue(DiscBurnerBlock.BURNING);
        if (entity.isBurning != isMarkedBurning) {
            world.setBlockAndUpdate(blockPos,
                    blockState.setValue(DiscBurnerBlock.BURNING, entity.isBurning));
        }

        ItemStack input1 = entity.inventory.get(1);
        ItemStack input2 = entity.inventory.get(2);
        if (!entity.stoppedSound) {
            entity.stoppedSound = true;
            ClientboundStopSoundPacket packet = new ClientboundStopSoundPacket(
                    MusicExpandedAudio.DISC_BURNER_START.location(), SoundSource.BLOCKS);
            for (Player plr : world.players()) {
                if (plr instanceof ServerPlayer) {
                    ((ServerPlayer) plr).connection.send(packet);
                }
            }
        }
        if ((input1.is(MusicExpandedItems.CD) || input2.is(MusicExpandedItems.CD))
                && entity.inventory.get(0).is(MusicExpandedItems.CD)) {
            if (!entity.isBurning) {
                entity.burnTime = 700;
                entity.burningCurrently.set(0, input1);
                entity.burningCurrently.set(1, input2);
                entity.isBurning = true;
                world.playSound(null, blockPos, MusicExpandedAudio.DISC_BURNER_START, SoundSource.BLOCKS);
            } else {
                // MusicExpanded.LOGGER.info("Lets see if " + input1.toString() + " is " +
                // entity.burningCurrently.get(0).toString());
                // MusicExpanded.LOGGER.info("Lets see if " + input2.toString() + " is " +
                // entity.burningCurrently.get(1).toString());
                if (!(ItemStack.matches(entity.burningCurrently.get(0), input1)
                        && ItemStack.matches(entity.burningCurrently.get(1), input2))) {
                    entity.isBurning = false;
                    entity.stoppedSound = false;
                }
                entity.burnTime--;
                entity.data.set(0, entity.burnTime);
            }
        } else if (entity.isBurning) {
            entity.stoppedSound = false;
        }
    }
}
