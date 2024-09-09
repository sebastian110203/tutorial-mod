package net.sebastian.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sebastian.tutorialmod.TutorialMod;
import net.sebastian.tutorialmod.item.custom.BoomerangItem;
import net.sebastian.tutorialmod.item.custom.ConfettiGrenadeItem;
import net.sebastian.tutorialmod.item.custom.MetalDetectorItem;
import net.sebastian.tutorialmod.item.custom.NetherWandItem;

/*
This class. ModItems, contains all the ModItems as member variables, and registers the items
through its methods
 */


public class ModItems {

    public static final Item RUBY = registerItem("ruby", new Item(new Item.Settings())); //check usage of settings
    public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new Item.Settings()));
    public static final Item METAL_DETECTOR = registerItem("metal_detector", new MetalDetectorItem(new Item.Settings().maxDamage(64)));
    public static final Item CONFETTI_GRENADE    = registerItem("confetti_grenade", new ConfettiGrenadeItem(new Item.Settings()));
    public static final Item NetherWand = registerItem("nether_wand", new NetherWandItem(new Item.Settings()));
    public static final Item BOOMERANG = registerItem("boomerang", new  BoomerangItem(new Item.Settings()));
    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(RUBY); // adds item to creative mod tab, any other items would be added here
        entries.add(RAW_RUBY);
        entries.add(METAL_DETECTOR);
        entries.add(CONFETTI_GRENADE);
        entries.add(NetherWand);
        entries.add(BOOMERANG);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item);
    }
    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for" + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup); // if you want something in a different tab change INGREDIENTS
    }

}
