package fn10.musicexpansion.music;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;

public class CDTracks {

    public static final CDTrack C418_CAT = new CDTrack(SoundEvents.MUSIC_DISC_CAT.value());
    public static final CDTrack C418_13 = new CDTrack(SoundEvents.MUSIC_DISC_13.value());
    public static final CDTrack C418_11 = new CDTrack(SoundEvents.MUSIC_DISC_11.value());

    private static final Map<String, CDTrack> TRACK_IDS = Map.of(
        "cat", C418_CAT,
        "13", C418_13,
        "11", C418_11
    );

    public static CDTrack getTrackFromId(String id) {
        return TRACK_IDS.get(id);
    }

    public static String getIdFromTrack(CDTrack track) {
        for (Entry<String,CDTrack> set : TRACK_IDS.entrySet()) {
            if (set.getValue().equals(track)) {
                return set.getKey();
            }
        }
        return null;
    }

}
