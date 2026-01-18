package fn10.musicexpansion.datagen.providers;

import java.util.concurrent.CompletableFuture;

import fn10.musicexpansion.reg.MusicExpandedItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

public class MusicExpandedRecipeProvider extends FabricRecipeProvider {

    public MusicExpandedRecipeProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    protected RecipeProvider createRecipeProvider(Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {

            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, MusicExpandedItems.GLASS_DUST)
                        .pattern("AA")
                        .pattern("AA")
                        .define(Character.valueOf('A'), MusicExpandedItemTagProvider.GLASS)
                        .unlockedBy(getHasName(Items.GLASS), has(MusicExpandedItemTagProvider.GLASS))
                        .save(exporter);
            }

        };
    }

}
