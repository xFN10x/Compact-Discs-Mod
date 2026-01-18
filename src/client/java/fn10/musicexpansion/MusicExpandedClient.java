package fn10.musicexpansion;

import fn10.musicexpansion.reg.MusicExpandedBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class MusicExpandedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.putBlock(MusicExpandedBlocks.DISC_BURNER_BLOCK, ChunkSectionLayer.TRANSLUCENT);
	}
}