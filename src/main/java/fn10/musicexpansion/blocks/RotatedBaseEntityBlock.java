package fn10.musicexpansion.blocks;

import org.jspecify.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public abstract class RotatedBaseEntityBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected RotatedBaseEntityBlock(Properties properties) {
        super(properties);
    }

    public abstract BlockState getBaseStateForPlacement(BlockPlaceContext blockPlaceContext);
    public abstract StateDefinition.Builder<Block, BlockState> addBlockStateDefinitions(StateDefinition.Builder<Block, BlockState> builder);

    /**
     * DO NOT OVERRIDE
     */
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return getBaseStateForPlacement(blockPlaceContext).setValue(FACING,
                blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        addBlockStateDefinitions(builder).add(FACING);
    }

    protected BlockState rotate(BlockState blockState, Rotation rotation) {
        return (BlockState) blockState.setValue(FACING, rotation.rotate((Direction) blockState.getValue(FACING)));
    }

    protected BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation((Direction) blockState.getValue(FACING)));
    }

}
