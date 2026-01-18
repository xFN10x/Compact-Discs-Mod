package fn10.musicexpansion.reg;

import fn10.musicexpansion.MusicExpanded;
import fn10.musicexpansion.blocks.entity.DiscBurnerBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MusicExpandedBlockEntitys {
    public static final BlockEntityType<DiscBurnerBlockEntity> DISC_BURNER_BENTITY = register("counter",
            DiscBurnerBlockEntity::new, MusicExpandedBlocks.DISC_BURNER_BLOCK);

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks) {
        Identifier id = Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id,
                FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void init() {

    }
}
