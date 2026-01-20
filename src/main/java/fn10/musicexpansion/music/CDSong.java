package fn10.musicexpansion.music;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.resources.Identifier;

public record CDSong(String id) {
    public static final Codec<CDSong> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                Codec.STRING.fieldOf("id").forGetter(CDSong::id))
                .apply(builder, CDSong::new);
    });

    public Identifier getIdAsIdentifier() {
        return Identifier.parse(id);
    }
}
