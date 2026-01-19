package fn10.musicexpansion.screens;

import fn10.musicexpansion.menu.DiscBurnerMenu;
import net.minecraft.client.gui.screens.MenuScreens.ScreenConstructor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class DiscBurnerScreenConstructor implements ScreenConstructor<DiscBurnerMenu, DiscBurnerScreen> {

    @Override
    public DiscBurnerScreen create(DiscBurnerMenu abstractContainerMenu, Inventory inventory, Component component) {
        return new DiscBurnerScreen(abstractContainerMenu, inventory, component);
    }

}
