package fn10.musicexpansion;

import java.util.List;

import com.mojang.brigadier.context.CommandContext;

import fn10.musicexpansion.music.CDTrack;
import fn10.musicexpansion.music.CDTracks;
import fn10.musicexpansion.reg.MusicExpandedItemComponents;
import fn10.musicexpansion.reg.MusicExpandedItems;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class MusicExpandedCommands {

    public static int giveExampleDiscs(CommandContext<CommandSourceStack> context) {
        try {
            Inventory inventory = context.getSource().getPlayer().getInventory();

            CDTrack song1 = CDTracks.C418_11;
            CDTrack song2 = CDTracks.C418_13;
            CDTrack song3 = CDTracks.C418_CAT;
            //CDTrack song4 = new CDSong("dog");

            ItemStack exampleDisc1 = makeExampleDisc(CDTracks.getIdFromTrack(song1));
            ItemStack exampleDisc2 = makeExampleDisc(CDTracks.getIdFromTrack(song2));
            ItemStack exampleDisc3 = makeExampleDisc(CDTracks.getIdFromTrack(song3));

            inventory.add(exampleDisc1);
            inventory.add(exampleDisc2);
            inventory.add(exampleDisc3);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    protected static ItemStack makeExampleDisc(String... songs) {
        ItemStack stack = new ItemStack(MusicExpandedItems.CD);
        stack.set(MusicExpandedItemComponents.CD_SONGS, List.of(songs));
        return stack;
    }

}
