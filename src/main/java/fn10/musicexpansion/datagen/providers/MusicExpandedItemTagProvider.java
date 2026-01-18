package fn10.musicexpansion.datagen.providers;

import java.util.concurrent.CompletableFuture;

import fn10.musicexpansion.MusicExpanded;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class MusicExpandedItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public MusicExpandedItemTagProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final TagKey<Item> GLASS = TagKey.create(Registries.ITEM,
            Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "glass_blocks"));

    @Override
    protected void addTags(Provider wrapperLookup) {
        valueLookupBuilder(GLASS)
                .add(net.minecraft.world.item.Items.GLASS)
                .add(net.minecraft.world.item.Items.TINTED_GLASS)
                .add(net.minecraft.world.item.Items.RED_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.GRAY_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.BLUE_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.CYAN_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.LIME_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.PINK_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.GREEN_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.BLACK_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.BROWN_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.WHITE_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.ORANGE_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.PURPLE_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.YELLOW_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.MAGENTA_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.LIGHT_GRAY_STAINED_GLASS)
                .add(net.minecraft.world.item.Items.LIGHT_BLUE_STAINED_GLASS);
    }

}
