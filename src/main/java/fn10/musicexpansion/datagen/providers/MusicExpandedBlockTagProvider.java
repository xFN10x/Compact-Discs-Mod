package fn10.musicexpansion.datagen.providers;

import java.util.concurrent.CompletableFuture;

import fn10.musicexpansion.reg.MusicExpandedBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.BlockTags;

public class MusicExpandedBlockTagProvider extends FabricTagProvider.BlockTagProvider {


    public MusicExpandedBlockTagProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(Provider wrapperLookup) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(MusicExpandedBlocks.DISC_BURNER_BLOCK);
    }

}
