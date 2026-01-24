package fn10.musicexpansion.reg;

import fn10.musicexpansion.MusicExpanded;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class MusicExpandedAudio {

    private static SoundEvent register(String id) {
		Identifier identifier = Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, id);
		return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
	}

    public static final SoundEvent DISC_BURNER_START = register("disc_burner_start");

	public static void init() {
		
	}
}