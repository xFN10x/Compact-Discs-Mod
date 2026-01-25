package fn10.musicexpansion;

import java.util.List;

import com.mojang.brigadier.context.CommandContext;

import fn10.musicexpansion.music.CDSong;
import fn10.musicexpansion.reg.MusicExpandedItemComponents;
import fn10.musicexpansion.reg.MusicExpandedItems;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class MusicExpandedCommands {

    public static int giveExampleDiscs(CommandContext<CommandSourceStack> context) {
        try {
            Inventory inventory = context.getSource().getPlayer().getInventory();

            CDSong song1 = new CDSong("13");
            CDSong song2 = new CDSong("11");
            CDSong song3 = new CDSong("cat");
            CDSong song4 = new CDSong("dog");

            ItemStack exampleDisc1 = makeExampleDisc(song1, song2);
            ItemStack exampleDisc2 = makeExampleDisc(song3);
            ItemStack exampleDisc3 = makeExampleDisc(song4);

            inventory.add(exampleDisc1);
            inventory.add(exampleDisc2);
            inventory.add(exampleDisc3);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    protected static ItemStack makeExampleDisc(CDSong... songs) {
        ItemStack stack = new ItemStack(MusicExpandedItems.CD);
        stack.set(MusicExpandedItemComponents.CD_SONGS, List.of(songs));
        return stack;
    }

}
