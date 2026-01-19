package fn10.musicexpansion.providers;

import fn10.musicexpansion.MusicExpanded;
import fn10.musicexpansion.blocks.DiscBurnerBlock;
import fn10.musicexpansion.reg.MusicExpandedBlocks;
import fn10.musicexpansion.reg.MusicExpandedItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;

public class MusicExpandedModelProvider extends FabricModelProvider {

    public MusicExpandedModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        // Disc Burner
        MultiVariant basic = BlockModelGenerators
                .plainVariant(Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "block/disc_burner"));
        blockStateModelGenerator.blockStateOutput
                .accept(MultiVariantGenerator.dispatch(MusicExpandedBlocks.DISC_BURNER_BLOCK)
                        .with(PropertyDispatch.initial(DiscBurnerBlock.FACING)
                                .select(Direction.NORTH, basic)
                                .select(Direction.EAST, basic.with(BlockModelGenerators.Y_ROT_90))
                                .select(Direction.SOUTH, basic.with(BlockModelGenerators.Y_ROT_180))
                                .select(Direction.WEST, basic.with(BlockModelGenerators.Y_ROT_270))));
        blockStateModelGenerator.registerSimpleItemModel(MusicExpandedBlocks.DISC_BURNER_BLOCK,
                Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "block/disc_burner"));
                
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(MusicExpandedItems.GLASS_DUST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(MusicExpandedItems.CD, ModelTemplates.FLAT_ITEM);
    }

}
