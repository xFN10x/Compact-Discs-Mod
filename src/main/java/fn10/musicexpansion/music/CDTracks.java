package fn10.musicexpansion.music;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.random.RandomGenerator;

import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;

public class CDTracks {

    public static final CDTrack C418_CAT = new CDTrack(SoundEvents.MUSIC_DISC_CAT.value());
    public static final CDTrack C418_13 = new CDTrack(SoundEvents.MUSIC_DISC_13.value());
    public static final CDTrack C418_11 = new CDTrack(SoundEvents.MUSIC_DISC_11.value());

    private static final Map<String, CDTrack> TRACK_IDS = Map.of(
            "cat", C418_CAT,
            "13", C418_13,
            "11", C418_11);

    protected static final NonNullList<Integer> ACTIVE_CD_TRACK_IDS = NonNullList.create();

    public static Integer createNewCDTrackId() {
        Integer canadite = Random.from(RandomGenerator.getDefault()).nextInt(0, Integer.MAX_VALUE);
        if (ACTIVE_CD_TRACK_IDS.contains(canadite))
            return createNewCDTrackId();
        else
            return createNewCDTrackId();
    }

    public static CDTrack getTrackFromId(String id) {
        return TRACK_IDS.get(id);
    }

    public static String getIdFromTrack(CDTrack track) {
        for (Entry<String, CDTrack> set : TRACK_IDS.entrySet()) {
            if (set.getValue().equals(track)) {
                return set.getKey();
            }
        }
        return null;
    }

}
