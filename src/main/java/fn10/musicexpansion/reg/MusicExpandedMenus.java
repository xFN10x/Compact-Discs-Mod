package fn10.musicexpansion.reg;

import fn10.musicexpansion.MusicExpanded;
import fn10.musicexpansion.menu.DiscBurnerMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MenuType.MenuSupplier;

public class MusicExpandedMenus {
    public static MenuType<?> register(String id, MenuType<?> menu) {
        ResourceKey<MenuType<?>> key = ResourceKey.create(Registries.MENU,
                Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, id));

        return Registry.register(BuiltInRegistries.MENU, key, menu);
    }

    @SuppressWarnings("unchecked")
    public final static MenuType<DiscBurnerMenu> DISC_BURNER_MENU = (MenuType<DiscBurnerMenu>) register(
            "disc_burner_menu", new MenuType<DiscBurnerMenu>(new MenuSupplier<>() {

                @Override
                public DiscBurnerMenu create(int i, Inventory inventory) {
                    return new DiscBurnerMenu(inventory, i);
                }

            }, FeatureFlagSet.of()));

    public static void init() {

    }
}
