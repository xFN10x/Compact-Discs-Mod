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

public record CDTrackPlayPayloadS2C(BlockPos pos, Holder<SoundEvent> event, Integer id, String translationKey)
		implements CustomPacketPayload {
	public static final Identifier CDTRACK_PLAY_PAYLOAD_ID = Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID,
			"play_cd_track");
	public static final CustomPacketPayload.Type<CDTrackPlayPayloadS2C> ID = new CustomPacketPayload.Type<>(
			CDTRACK_PLAY_PAYLOAD_ID);
	public static final StreamCodec<RegistryFriendlyByteBuf, CDTrackPlayPayloadS2C> CODEC = StreamCodec
			.composite(BlockPos.STREAM_CODEC, CDTrackPlayPayloadS2C::pos, SoundEvent.STREAM_CODEC,
					CDTrackPlayPayloadS2C::event, ByteBufCodecs.INT, CDTrackPlayPayloadS2C::id,
					ByteBufCodecs.STRING_UTF8, CDTrackPlayPayloadS2C::translationKey,
					CDTrackPlayPayloadS2C::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return ID;
	}

}
