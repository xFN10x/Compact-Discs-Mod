package fn10.musicexpansion.providers;

import java.util.concurrent.CompletableFuture;

import fn10.musicexpansion.reg.MusicExpandedBlocks;
import fn10.musicexpansion.reg.MusicExpandedItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup.Provider;

public class MusicExpandedTranslationProviderEN extends FabricLanguageProvider {

    public MusicExpandedTranslationProviderEN(FabricDataOutput dataOutput,
            CompletableFuture<Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(MusicExpandedItems.GLASS_DUST, "Glass Dust");
        translationBuilder.add(MusicExpandedItems.CD, "Compact-Disc");
        translationBuilder.add(MusicExpandedBlocks.DISC_BURNER_BLOCK, "Disc Burner");
        translationBuilder.add(MusicExpandedBlocks.STEREO_BLOCK, "Stereo");

        translationBuilder.add("menu.container.disc_burner", "Disc Burner");
        translationBuilder.add("menu.container.stereo", "Stereo");

        translationBuilder.add("itemGroup.compactdiscs", "Compact Discs");
        
        translationBuilder.add("text.cd.tooltip.nosongs", "No Songs");
        translationBuilder.add("text.cd.tooltip.cdrw", "CD-RW");

        translationBuilder.add("subtitle.compactdiscs.block.discburner", "Disc Burner Starts...");

        translationBuilder.add("song.compactdiscs.c418.cat", "C418 - Cat");
        translationBuilder.add("song.compactdiscs.c418.13", "C418 - 13");
        translationBuilder.add("song.compactdiscs.c418.11", "C418 - 11");
    }

}
