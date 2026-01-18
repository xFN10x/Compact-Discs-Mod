package fn10.musicexpansion.blocks.entity;

import org.jspecify.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DiscBurnerBlock extends BaseEntityBlock {

    public DiscBurnerBlock(Properties properties) {
        super(properties.noOcclusion());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new DiscBurnerBlockEntity(arg0, arg1);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(DiscBurnerBlock::new);
    }

}
