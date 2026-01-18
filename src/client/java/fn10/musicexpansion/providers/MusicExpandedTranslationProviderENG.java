package fn10.musicexpansion.providers;

import java.util.concurrent.CompletableFuture;

import fn10.musicexpansion.reg.MusicExpandedItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup.Provider;

public class MusicExpandedTranslationProviderENG extends FabricLanguageProvider {

    public MusicExpandedTranslationProviderENG(FabricDataOutput dataOutput,
            CompletableFuture<Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(MusicExpandedItems.GLASS_DUST, "Glass Dust");
        translationBuilder.add(MusicExpandedItems.CD, "Compact-Disc");
        translationBuilder.add("itemGroup.compactdiscs", "Compact Discs");
    }

}
