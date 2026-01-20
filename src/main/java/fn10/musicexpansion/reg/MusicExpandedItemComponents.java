package fn10.musicexpansion.reg;

import java.util.List;

import com.mojang.serialization.Codec;

import fn10.musicexpansion.MusicExpanded;
import fn10.musicexpansion.music.CDSong;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class MusicExpandedItemComponents {
    public static final DataComponentType<List<CDSong>> CD_SONGS = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID, "cd_songs"),
            DataComponentType.<List<CDSong>>builder().persistent(Codec.list(CDSong.CODEC)).build());

    public static void init() {
    }
}
