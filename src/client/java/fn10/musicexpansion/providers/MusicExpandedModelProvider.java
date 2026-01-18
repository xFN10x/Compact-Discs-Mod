package fn10.musicexpansion.providers;

import fn10.musicexpansion.reg.MusicExpandedItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class MusicExpandedModelProvider extends FabricModelProvider {

    public MusicExpandedModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(MusicExpandedItems.GLASS_DUST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(MusicExpandedItems.CD, ModelTemplates.FLAT_ITEM);
    }

}
