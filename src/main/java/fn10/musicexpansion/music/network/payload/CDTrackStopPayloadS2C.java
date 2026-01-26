package fn10.musicexpansion.music.network.payload;

import fn10.musicexpansion.MusicExpanded;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record CDTrackStopPayloadS2C(Integer id) implements CustomPacketPayload {
    public static final Identifier CDTRACK_STOP_PAYLOAD_ID = Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID,
            "stop_cd_track");
    public static final CustomPacketPayload.Type<CDTrackStopPayloadS2C> ID = new CustomPacketPayload.Type<>(
            CDTRACK_STOP_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, CDTrackStopPayloadS2C> CODEC = StreamCodec
            .composite(ByteBufCodecs.INT, CDTrackStopPayloadS2C::id,
                    CDTrackStopPayloadS2C::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
