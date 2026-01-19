package fn10.musicexpansion.blocks;

import java.util.Optional;

import org.jspecify.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import fn10.musicexpansion.blocks.entity.DiscBurnerBlockEntity;
import fn10.musicexpansion.reg.MusicExpandedBlockEntitys;
import fn10.musicexpansion.reg.MusicExpandedItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DiscBurnerBlock extends BaseEntityBlock {

    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LOADED = BooleanProperty.create("loaded");

    public DiscBurnerBlock(Properties properties) {
        super(properties.noOcclusion());
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LOADED, false));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new DiscBurnerBlockEntity(arg0, arg1);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(DiscBurnerBlock::new);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
            CollisionContext collisionContext) {
        return Block.box(0d, 0d, 0d, 16d, 6d, 16d);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
            CollisionContext collisionContext) {
        return getCollisionShape(blockState, blockGetter, blockPos, collisionContext);
    }

    protected BlockState rotate(BlockState blockState, Rotation rotation) {
        return (BlockState) blockState.setValue(FACING, rotation.rotate((Direction) blockState.getValue(FACING)));
    }

    protected BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation((Direction) blockState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(LOADED);
    }

    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(LOADED, false).setValue(FACING,
                blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player,
            BlockHitResult blockHitResult) {
        MenuProvider menuProvider = getMenuProvider(blockState, level, blockPos);
        player.openMenu(menuProvider);
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos,
            Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        Optional<DiscBurnerBlockEntity> entity = level.getBlockEntity(blockPos,
                MusicExpandedBlockEntitys.DISC_BURNER_BENTITY);
        if (!entity.isPresent())
            return InteractionResult.FAIL;
        DiscBurnerBlockEntity realEntity = entity.get();
        if (itemStack.is(MusicExpandedItems.CD) && realEntity.inventory.get(0).isEmpty()) {
            realEntity.inventory.set(0, itemStack.consumeAndReturn(1, player));
            return InteractionResult.CONSUME;
        } else {
            return useWithoutItem(blockState, level, blockPos, player, blockHitResult);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state,
            BlockEntityType<T> type) {
        return createTickerHelper(type, MusicExpandedBlockEntitys.DISC_BURNER_BENTITY, DiscBurnerBlockEntity::tick);
    }
}
