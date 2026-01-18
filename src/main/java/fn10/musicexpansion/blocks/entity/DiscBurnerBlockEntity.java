package fn10.musicexpansion.blocks.entity;

import fn10.musicexpansion.reg.MusicExpandedBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DiscBurnerBlockEntity extends BlockEntity {

    public DiscBurnerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MusicExpandedBlockEntitys.DISC_BURNER_BENTITY, blockPos, blockState);
    }
}
