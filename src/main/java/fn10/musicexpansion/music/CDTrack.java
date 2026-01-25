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

    public void play(Level level, BlockPos pos) {
        if (level instanceof ServerLevel)
            for (ServerPlayer plr : ((ServerLevel) level).players()) {
                ClientBoundCDTrackPlayPayload payload = new ClientBoundCDTrackPlayPayload(pos, Holder.direct(event));
                ServerPlayNetworking.send(plr, payload);
            }
    }

    public boolean equals(CDTrack other) {
        return event.location().toString().equals(other.event.location().toString());
    }

}
