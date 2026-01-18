package fn10.musicexpansion;

import fn10.musicexpansion.datagen.providers.MusicExapandedTagProvider;
import fn10.musicexpansion.datagen.providers.MusicExpandedRecipeProvider;
import fn10.musicexpansion.providers.MusicExpandedModelProvider;
import fn10.musicexpansion.providers.MusicExpandedTranslationProviderENG;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack;

public class MusicExpandedDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(MusicExpandedRecipeProvider::new);
		pack.addProvider(MusicExapandedTagProvider::new);
		pack.addProvider(MusicExpandedModelProvider::new);
		pack.addProvider(MusicExpandedTranslationProviderENG::new);
	}
}
