package fn10.musicexpansion.providers;

import fn10.musicexpansion.MusicExpanded;
import fn10.musicexpansion.blocks.DiscBurnerBlock;
import fn10.musicexpansion.blocks.StereoBlock;
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
        MultiVariant nodisc = BlockModelGenerators
                .plainVariant(Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "block/disc_burner"));
        MultiVariant hasdisc = BlockModelGenerators
                .plainVariant(Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "block/disc_burner_loaded"));
        blockStateModelGenerator.blockStateOutput
                .accept(MultiVariantGenerator.dispatch(MusicExpandedBlocks.DISC_BURNER_BLOCK)
                        .with(PropertyDispatch.initial(DiscBurnerBlock.FACING, DiscBurnerBlock.LOADED)
                                .select(Direction.NORTH, false, nodisc)
                                .select(Direction.EAST, false, nodisc.with(BlockModelGenerators.Y_ROT_90))
                                .select(Direction.SOUTH, false, nodisc.with(BlockModelGenerators.Y_ROT_180))
                                .select(Direction.WEST, false, nodisc.with(BlockModelGenerators.Y_ROT_270))
                                .select(Direction.NORTH, true, hasdisc)
                                .select(Direction.EAST, true, hasdisc.with(BlockModelGenerators.Y_ROT_90))
                                .select(Direction.SOUTH, true, hasdisc.with(BlockModelGenerators.Y_ROT_180))
                                .select(Direction.WEST, true, hasdisc.with(BlockModelGenerators.Y_ROT_270))));
        blockStateModelGenerator.registerSimpleItemModel(MusicExpandedBlocks.DISC_BURNER_BLOCK,
                Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "block/disc_burner"));
                // Stereo
        MultiVariant snodisc = BlockModelGenerators
                .plainVariant(Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "block/stereo"));
        MultiVariant shasdisc = BlockModelGenerators
                .plainVariant(Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "block/stereo_loaded"));
        blockStateModelGenerator.blockStateOutput
                .accept(MultiVariantGenerator.dispatch(MusicExpandedBlocks.STEREO_BLOCK)
                        .with(PropertyDispatch.initial(StereoBlock.FACING, StereoBlock.LOADED)
                                .select(Direction.NORTH, false, snodisc)
                                .select(Direction.EAST, false, snodisc.with(BlockModelGenerators.Y_ROT_90))
                                .select(Direction.SOUTH, false, snodisc.with(BlockModelGenerators.Y_ROT_180))
                                .select(Direction.WEST, false, snodisc.with(BlockModelGenerators.Y_ROT_270))
                                .select(Direction.NORTH, true, shasdisc)
                                .select(Direction.EAST, true, shasdisc.with(BlockModelGenerators.Y_ROT_90))
                                .select(Direction.SOUTH, true, shasdisc.with(BlockModelGenerators.Y_ROT_180))
                                .select(Direction.WEST, true, shasdisc.with(BlockModelGenerators.Y_ROT_270))));
        blockStateModelGenerator.registerSimpleItemModel(MusicExpandedBlocks.STEREO_BLOCK,
                Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "block/stereo"));

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(MusicExpandedItems.GLASS_DUST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(MusicExpandedItems.CD, ModelTemplates.FLAT_ITEM);
    }

}
