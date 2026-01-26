package fn10.musicexpansion.music;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.random.RandomGenerator;

import net.minecraft.sounds.SoundEvents;

public class CDTracks {

    // get these from the wiki
    protected static final Map<String, Integer> TRACK_LENGTHS = Map.of(
            "cat", getTicksFromMinsAndSeconds(3, 6),
            "13", getTicksFromMinsAndSeconds(2, 56),
            "11", getTicksFromMinsAndSeconds(1, 11));

    public static final CDTrack C418_CAT = new CDTrack(lengthOf("cat"), SoundEvents.MUSIC_DISC_CAT.value(),
            "song.compactdiscs.c418.cat");
    public static final CDTrack C418_13 = new CDTrack(lengthOf("13"), SoundEvents.MUSIC_DISC_13.value(),
            "song.compactdiscs.c418.13");
    public static final CDTrack C418_11 = new CDTrack(lengthOf("11"), SoundEvents.MUSIC_DISC_11.value(),
            "song.compactdiscs.c418.11");

    private static final Map<String, CDTrack> TRACK_IDS = Map.of(
            "cat", C418_CAT,
            "13", C418_13,
            "11", C418_11);

    protected static final HashMap<Integer, CDTrack> ACTIVE_CD_TRACKS = new HashMap<>();

    public static CDTrack getCDTrackFromPlayingID(Integer id) {
        return ACTIVE_CD_TRACKS.get(id);
    }

    public static Integer getTicksFromSeconds(Integer seconds) {
        return seconds * 20;
    }

    public static Integer lengthOf(String id) {
        return TRACK_LENGTHS.getOrDefault(id, 20);
    }

    public static Integer getTicksFromMinsAndSeconds(Integer minutes, Integer seconds) {
        return getTicksFromSeconds(seconds) + getTicksFromSeconds(minutes * 60);
    }

    public static Integer createNewCDTrackId() {
        Integer canadite = Random.from(RandomGenerator.getDefault()).nextInt(0, Integer.MAX_VALUE);
        if (ACTIVE_CD_TRACKS.containsKey(canadite))
            return createNewCDTrackId();
        else
            return canadite;
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
