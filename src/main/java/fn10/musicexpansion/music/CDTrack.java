package fn10.musicexpansion.music;

import fn10.musicexpansion.music.network.ClientBoundCDTrackPlayPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.resource.v1.reloader.ResourceReloaderKeys.Server;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public class CDTrack {

    private final SoundEvent event;

    public CDTrack(SoundEvent sound) {
        this.event = sound;
    }

    /**
     * Plays this track in the given level and pos. This then sends packets to all
     * clients.
     * 
     * @param level The level this is happening in.
     * @param pos   The position this track should play
     * @return The id of the playing track. This is how we keep track of the playing
     *         tracks. This value is -1 if this function fails
     */
    public Integer play(Level level, BlockPos pos) {
        try {
            if (!(level instanceof ServerLevel))
                return -1;
            Integer id = CDTracks.createNewCDTrackId();
            for (ServerPlayer plr : ((ServerLevel) level).players()) {
                ClientBoundCDTrackPlayPayload payload = new ClientBoundCDTrackPlayPayload(pos, Holder.direct(event),
                        id);
                ServerPlayNetworking.send(plr, payload);
            }
            CDTracks.ACTIVE_CD_TRACK_IDS.add(id);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean equals(CDTrack other) {
        return event.location().toString().equals(other.event.location().toString());
    }

}
