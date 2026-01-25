package fn10.musicexpansion;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.commands.Commands;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fn10.musicexpansion.reg.MusicExpandedAudio;
import fn10.musicexpansion.reg.MusicExpandedBlockEntitys;
import fn10.musicexpansion.reg.MusicExpandedBlocks;
import fn10.musicexpansion.reg.MusicExpandedItemComponents;
import fn10.musicexpansion.reg.MusicExpandedItems;
import fn10.musicexpansion.reg.MusicExpandedMenus;

public class MusicExpanded implements ModInitializer {
	public static final String MOD_ID = "compactdiscs";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ResourceKey<CreativeModeTab> CD_ITEM_GROUP_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(),
			Identifier.fromNamespaceAndPath(MOD_ID, "item_group"));
	public static final CreativeModeTab COMPACTDISCS_ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(MusicExpandedItems.CD))
			.title(Component.translatable("itemGroup.compactdiscs"))
			.build();

	@Override
	public void onInitialize() {

		// Register the group.
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CD_ITEM_GROUP_KEY, COMPACTDISCS_ITEM_GROUP);

		// Register items to the custom item group.
		ItemGroupEvents.modifyEntriesEvent(CD_ITEM_GROUP_KEY).register(itemGroup -> {
			itemGroup.accept(MusicExpandedItems.CD);
			itemGroup.accept(MusicExpandedItems.GLASS_DUST);
			itemGroup.accept(MusicExpandedBlocks.DISC_BURNER_BLOCK);
			itemGroup.accept(MusicExpandedBlocks.STEREO_BLOCK);
		});

		CommandRegistrationCallback.EVENT.register((dis, reg, enviro) -> {
			dis.register(Commands.literal("compactdiscs")
					.then(
							Commands.literal("give_example_discs")
									.requires(source -> source.permissions()
											.hasPermission(Permissions.COMMANDS_MODERATOR))
									.executes(MusicExpandedCommands::giveExampleDiscs)));
		});

		MusicExpandedItems.init();
		MusicExpandedBlockEntitys.init();
		MusicExpandedBlocks.init();
		MusicExpandedMenus.init();
		MusicExpandedItemComponents.init();
		MusicExpandedAudio.init();
	}
}