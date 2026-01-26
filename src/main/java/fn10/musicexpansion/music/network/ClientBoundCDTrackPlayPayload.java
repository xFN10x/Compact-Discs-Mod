package fn10.musicexpansion.music.network;

import fn10.musicexpansion.MusicExpanded;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public record ClientBoundCDTrackPlayPayload(BlockPos pos, Holder<SoundEvent> event, Integer id) implements CustomPacketPayload {
	public static final Identifier CDTRACK_PLAY_PAYLOAD_ID = Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID,
			"play_cd_track");
	public static final CustomPacketPayload.Type<ClientBoundCDTrackPlayPayload> ID = new CustomPacketPayload.Type<>(
			CDTRACK_PLAY_PAYLOAD_ID);
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientBoundCDTrackPlayPayload> CODEC = StreamCodec
			.composite(BlockPos.STREAM_CODEC, ClientBoundCDTrackPlayPayload::pos, SoundEvent.STREAM_CODEC,
					ClientBoundCDTrackPlayPayload::event, ByteBufCodecs.INT, ClientBoundCDTrackPlayPayload::id,  ClientBoundCDTrackPlayPayload::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return ID;
	}

}
