package fn10.musicexpansion.reg;

import com.google.common.base.Function;

import fn10.musicexpansion.MusicExpanded;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class MusicExpandedItems {
    public static <GenericItem extends Item> GenericItem register(String name,
            Function<Item.Properties, GenericItem> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, name));

        GenericItem item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static final Item GLASS_DUST = register("glass_dust", Item::new, new Item.Properties().fireResistant());
    public static final Item CD = register("compact_disc", Item::new, new Item.Properties());

    public static void init() {

    }
}
