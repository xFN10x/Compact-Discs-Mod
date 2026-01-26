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
				.plainVariant(Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID,
						"block/disc_burner"));
		MultiVariant hasdisc = BlockModelGenerators
				.plainVariant(Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID,
						"block/disc_burner_loaded"));
		MultiVariant spinningdisc = BlockModelGenerators
				.plainVariant(Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID,
						"block/disc_burner_burning"));
		blockStateModelGenerator.blockStateOutput
				.accept(MultiVariantGenerator.dispatch(MusicExpandedBlocks.DISC_BURNER_BLOCK)
						.with(PropertyDispatch
								.initial(DiscBurnerBlock.FACING, DiscBurnerBlock.LOADED, DiscBurnerBlock.BURNING)
								/// not burning
								.select(Direction.NORTH, false, false, nodisc)
								.select(Direction.EAST, false, false, nodisc
										.with(BlockModelGenerators.Y_ROT_90))
								.select(Direction.SOUTH, false, false, nodisc
										.with(BlockModelGenerators.Y_ROT_180))
								.select(Direction.WEST, false, false, nodisc
										.with(BlockModelGenerators.Y_ROT_270))
								// loaded
								.select(Direction.NORTH, true, false, hasdisc)
								.select(Direction.EAST, true, false, hasdisc
										.with(BlockModelGenerators.Y_ROT_90))
								.select(Direction.SOUTH, true, false, hasdisc
										.with(BlockModelGenerators.Y_ROT_180))
								.select(Direction.WEST, true, false, hasdisc.with(
										BlockModelGenerators.Y_ROT_270))
								/// burning
								.select(Direction.NORTH, false, true, spinningdisc)
								.select(Direction.EAST, false, true, spinningdisc
										.with(BlockModelGenerators.Y_ROT_90))
								.select(Direction.SOUTH, false, true, spinningdisc
										.with(BlockModelGenerators.Y_ROT_180))
								.select(Direction.WEST, false, true, spinningdisc
										.with(BlockModelGenerators.Y_ROT_270))
								// loaded
								.select(Direction.NORTH, true, true, spinningdisc)
								.select(Direction.EAST, true, true, spinningdisc
										.with(BlockModelGenerators.Y_ROT_90))
								.select(Direction.SOUTH, true, true, spinningdisc
										.with(BlockModelGenerators.Y_ROT_180))
								.select(Direction.WEST, true, true, spinningdisc.with(
										BlockModelGenerators.Y_ROT_270))));
		blockStateModelGenerator.registerSimpleItemModel(MusicExpandedBlocks.DISC_BURNER_BLOCK,
				Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "block/disc_burner"));
		// Stereo
		MultiVariant snodisc = BlockModelGenerators
				.plainVariant(Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "block/stereo"));
		MultiVariant shasdisc = BlockModelGenerators
				.plainVariant(Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID,
						"block/stereo_loaded"));
		MultiVariant splaying = BlockModelGenerators
				.plainVariant(Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID,
						"block/stereo_playing"));
		blockStateModelGenerator.blockStateOutput
				.accept(MultiVariantGenerator.dispatch(MusicExpandedBlocks.STEREO_BLOCK)
						.with(PropertyDispatch.initial(StereoBlock.FACING, StereoBlock.LOADED, StereoBlock.PLAYING)
								/// not playing
								.select(Direction.NORTH, false, false, snodisc)
								.select(Direction.EAST, false, false, snodisc
										.with(BlockModelGenerators.Y_ROT_90))
								.select(Direction.SOUTH, false, false, snodisc
										.with(BlockModelGenerators.Y_ROT_180))
								.select(Direction.WEST, false, false, snodisc
										.with(BlockModelGenerators.Y_ROT_270))
								// loaded
								.select(Direction.NORTH, true, false, shasdisc)
								.select(Direction.EAST, true, false, shasdisc
										.with(BlockModelGenerators.Y_ROT_90))
								.select(Direction.SOUTH, true, false, shasdisc
										.with(BlockModelGenerators.Y_ROT_180))
								.select(Direction.WEST, true, false, shasdisc.with(
										BlockModelGenerators.Y_ROT_270))
								/// playing
								.select(Direction.NORTH, false, true, splaying)
								.select(Direction.EAST, false, true, splaying
										.with(BlockModelGenerators.Y_ROT_90))
								.select(Direction.SOUTH, false, true, splaying
										.with(BlockModelGenerators.Y_ROT_180))
								.select(Direction.WEST, false, true, splaying
										.with(BlockModelGenerators.Y_ROT_270))
								// loaded
								.select(Direction.NORTH, true, true, splaying)
								.select(Direction.EAST, true, true, splaying
										.with(BlockModelGenerators.Y_ROT_90))
								.select(Direction.SOUTH, true, true, splaying
										.with(BlockModelGenerators.Y_ROT_180))
								.select(Direction.WEST, true, true, splaying.with(
										BlockModelGenerators.Y_ROT_270))));
		blockStateModelGenerator.registerSimpleItemModel(MusicExpandedBlocks.STEREO_BLOCK,
				Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "block/stereo"));

	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		itemModelGenerator.generateFlatItem(MusicExpandedItems.GLASS_DUST, ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(MusicExpandedItems.CD, ModelTemplates.FLAT_ITEM);
	}

}
