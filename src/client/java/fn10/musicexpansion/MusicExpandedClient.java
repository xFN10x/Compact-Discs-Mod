package fn10.musicexpansion;

import fn10.musicexpansion.items.CompactDiscItem;
import fn10.musicexpansion.reg.MusicExpandedBlocks;
import fn10.musicexpansion.reg.MusicExpandedItems;
import fn10.musicexpansion.reg.MusicExpandedMenus;
import fn10.musicexpansion.screens.DiscBurnerScreenConstructor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class MusicExpandedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ItemTooltipCallback.EVENT.register((stack, context, tooltipType, lines) -> {
			if (stack.is(MusicExpandedItems.CD)) {
				lines.addAll(CompactDiscItem.getTooltip(context, stack, tooltipType));
			}
		});
		MenuScreens.register(MusicExpandedMenus.DISC_BURNER_MENU, new DiscBurnerScreenConstructor());
		BlockRenderLayerMap.putBlock(MusicExpandedBlocks.DISC_BURNER_BLOCK, ChunkSectionLayer.TRANSLUCENT);
	}
}