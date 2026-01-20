package fn10.musicexpansion.blocks;

import org.jspecify.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import fn10.musicexpansion.blocks.entity.StereoBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StereoBlock extends RotatedBaseEntityBlock {

    public static final BooleanProperty LOADED = BooleanProperty.create("loaded");

    public StereoBlock(Properties properties) {
        super(properties.noOcclusion());
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LOADED, false));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new StereoBlockEntity(arg0, arg1);
    }

    @Override
    public BlockState getBaseStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return defaultBlockState().setValue(LOADED, false);
    }

    @Override
    public Builder<Block, BlockState> addBlockStateDefinitions(Builder<Block, BlockState> builder) {
        return builder.add(LOADED);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(StereoBlock::new);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
            CollisionContext collisionContext) {
        return Block.box(0d, 0d, 0d, 16d, 14d, 16d);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
            CollisionContext collisionContext) {
        return getCollisionShape(blockState, blockGetter, blockPos, collisionContext);
    }

}
