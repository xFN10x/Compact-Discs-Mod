package fn10.musicexpansion.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fn10.musicexpansion.music.CDSong;
import fn10.musicexpansion.reg.MusicExpandedItemComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class CompactDiscItem extends Item {

    public CompactDiscItem(Properties properties) {
        super(properties);
    }

    public static MutableComponent addToFront(String string, Component comp) {
        return Component.literal(string).append(comp);
    }

    /**
     * Combines all the songs on all the cds specified to one.
     * 
     * @param to  The cd to all all the songs too.
     * @param cds Every other cd to add the songs too.
     * @return {@code to}, but it has all the songs
     */
    public static ItemStack combineAllSongs(ItemStack to, ItemStack... cds) {
        ArrayList<CDSong> allSongs = new ArrayList<>();
        ArrayList<ItemStack> allCds = new ArrayList<>();
        allCds.add(to);
        allCds.addAll(List.of(cds));

        for (ItemStack CD : allCds) {
            if (!CD.has(MusicExpandedItemComponents.CD_SONGS) || CD.isEmpty())
                continue;
            for (CDSong cdsong : CD.get(MusicExpandedItemComponents.CD_SONGS)) {
                if (!allSongs.contains(cdsong))
                    allSongs.add(cdsong);
            }
        }
        to.set(MusicExpandedItemComponents.CD_SONGS, allSongs);
        return to;
    }

    public static List<Component> getTooltip(TooltipContext context, ItemStack stack, TooltipFlag flags) {
        ArrayList<Component> list = new ArrayList<>();
        if (stack.has(MusicExpandedItemComponents.CD_SONGS)) {
            list.add(Component.translatable("text.cd.tooltip.cdrw").withColor(0xAAAAAA));
            for (CDSong song : stack.get(MusicExpandedItemComponents.CD_SONGS)) {
                list.add(addToFront("- ", Component.translatable(song.id())).withColor(0xAAAAAA));
            }
            //list.set(0, ((MutableComponent)list.get(0)).withColor(0xff58ff));
        } else {
            list.add(Component.translatable("text.cd.tooltip.nosongs").withColor(0xAAAAAA));
        }
        return list;
    }
}
