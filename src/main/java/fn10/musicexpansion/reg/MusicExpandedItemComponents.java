package fn10.musicexpansion.reg;

import java.util.List;

import com.mojang.serialization.Codec;

import fn10.musicexpansion.MusicExpanded;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class MusicExpandedItemComponents {
    public static final DataComponentType<List<String>> CD_SONGS = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "cd_songs"),
            DataComponentType.<List<String>>builder().persistent(Codec.list(Codec.STRING)).build());

    public static void init() {
    }
}
