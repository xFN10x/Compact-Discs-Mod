package fn10.musicexpansion;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fn10.musicexpansion.reg.MusicExpandedItems;

public class MusicExpanded implements ModInitializer {
	public static final String MOD_ID = "compactdiscs";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MusicExpandedItems.init();
	}
}