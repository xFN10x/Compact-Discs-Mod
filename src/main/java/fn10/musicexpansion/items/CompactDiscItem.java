package fn10.musicexpansion.items;

import java.util.ArrayList;
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

    public static List<Component> getTooltip(TooltipContext context, ItemStack stack, TooltipFlag flags) {

        ArrayList<Component> list = new ArrayList<>();
        if (stack.has(MusicExpandedItemComponents.CD_SONGS)) {
            list.add(Component.translatable("text.cd.tooltip.cdrw").withColor(0xAAAAAA));
            for (CDSong song : stack.get(MusicExpandedItemComponents.CD_SONGS)) {
                list.add(addToFront("- ", Component.translatable(song.id())).withColor(0xAAAAAA));
            }
        } else {
            list.add(Component.translatable("text.cd.tooltip.nosongs").withColor(0xAAAAAA));
        }
        return list;
    }
}
