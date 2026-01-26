package fn10.musicexpansion.music;

import fn10.musicexpansion.music.network.ClientBoundCDTrackPlayPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public class CDTrack {

    private final SoundEvent event;
    private final String translation;
    private final Integer length;

    public CDTrack(Integer length, SoundEvent sound, String translationString) {
        this.event = sound;
        this.translation = translationString;
        this.length = length;
    }

    /**
     * Plays this track in the given level and pos. This then sends packets to all
     * clients.
     * 
     * @param level The level this is happening in.
     * @param pos   The position this track should play
     * @return An Either, with left being the id of this playing track, and right
     *         being the length of this track. The ID is used for keeping track of
     *         playing tracks on both client and server.
     */
    public ActiveCDTrackInfo play(Level level, BlockPos pos) {
        try {
            if (!(level instanceof ServerLevel))
                return new ActiveCDTrackInfo(-1, 20);
            Integer id = CDTracks.createNewCDTrackId();
            for (ServerPlayer plr : ((ServerLevel) level).players()) {
                ClientBoundCDTrackPlayPayload payload = new ClientBoundCDTrackPlayPayload(pos, Holder.direct(event),
                        id, translation);
                ServerPlayNetworking.send(plr, payload);
            }
            CDTracks.ACTIVE_CD_TRACK_IDS.add(id);
            return new ActiveCDTrackInfo(id, length);
        } catch (Exception e) {
            e.printStackTrace();
            return new ActiveCDTrackInfo(-1, 20);
        }
    }

    public Component getTranslation() {
        return Component.translatable(translation);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof CDTrack)
            return event.location().toString().equals(((CDTrack) other).event.location().toString());
        else
            return other.equals(this);
    }

}
