package fn10.musicexpansion.datagen.providers;

import java.util.concurrent.CompletableFuture;

import fn10.musicexpansion.reg.MusicExpandedBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup.Provider;

public class MusicExpandedBlockLootTableProvider extends FabricBlockLootTableProvider {

    public MusicExpandedBlockLootTableProvider(FabricDataOutput dataOutput,
            CompletableFuture<Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(MusicExpandedBlocks.DISC_BURNER_BLOCK);
        dropSelf(MusicExpandedBlocks.STEREO_BLOCK);
    }

}
