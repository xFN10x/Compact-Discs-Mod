package fn10.musicexpansion.blocks;

import java.util.Optional;

import org.jspecify.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import fn10.musicexpansion.blocks.entity.StereoBlockEntity;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StereoBlock extends RotatedBaseEntityBlock {

    public static final BooleanProperty LOADED = BooleanProperty.create("loaded");
    public static final BooleanProperty PLAYING = BooleanProperty.create("playing");

    public StereoBlock(Properties properties) {
        super(properties.noOcclusion());
        registerDefaultState(
                defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LOADED, false).setValue(PLAYING, false));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new StereoBlockEntity(arg0, arg1);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player,
            BlockHitResult blockHitResult) {
        Optional<StereoBlockEntity> entity = level.getBlockEntity(blockPos,
                MusicExpandedBlockEntitys.STEREO_BENTITY);
        StereoBlockEntity realEntity = entity.get();
        if (player.isCrouching()) {
            realEntity.nextTrackTime = 1;
        } else {
            realEntity.ejectCD();
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos,
            Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        Optional<StereoBlockEntity> entity = level.getBlockEntity(blockPos,
                MusicExpandedBlockEntitys.STEREO_BENTITY);
        StereoBlockEntity realEntity = entity.get();
        if (itemStack.is(MusicExpandedItems.CD) && realEntity.inventory.get(0).isEmpty()) {
            realEntity.putInCD(itemStack);
            return InteractionResult.CONSUME;
        }
        return useWithoutItem(blockState, level, blockPos, player, blockHitResult);
    }

    @Override
    public BlockState getBaseStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return defaultBlockState().setValue(LOADED, false).setValue(PLAYING, false);
    }

    @Override
    public Builder<Block, BlockState> addBlockStateDefinitions(Builder<Block, BlockState> builder) {
        return builder.add(LOADED).add(PLAYING);
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

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state,
            BlockEntityType<T> type) {
        return createTickerHelper(type, MusicExpandedBlockEntitys.STEREO_BENTITY, StereoBlockEntity::tick);
    }

}
