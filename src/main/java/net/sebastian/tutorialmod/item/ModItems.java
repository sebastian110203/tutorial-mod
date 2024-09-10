package net.sebastian.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.sebastian.tutorialmod.TutorialMod;
import net.sebastian.tutorialmod.item.custom.BoomerangItem;
import net.sebastian.tutorialmod.item.custom.ConfettiGrenadeItem;
import net.sebastian.tutorialmod.item.custom.MetalDetectorItem;
import net.sebastian.tutorialmod.item.custom.NetherWandItem;
import net.sebastian.tutorialmod.potion.Potions2;

/*
This class. ModItems, contains all the ModItems as member variables, and registers the items
through its methods
 */


public class ModItems {

    public static final RegistryEntry<Potion> CLOUD_IN_A_BOTTLE = registerPotion("cloud_in_a_bottle", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 3600, 20)));
    public static final RegistryEntry<Potion> LIGHTNING_IN_A_BOTTLE = registerPotion("lightning_in_a_bottle", new Potion(new StatusEffectInstance(StatusEffects.SPEED, 3600, 20)));

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
        //entries.add(PotionContentsComponent.createStack(ModItems.CLOUD_IN_A_BOTTLE, ModPotions.CLOUD_IN_A_BOTTLE));
    }



    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.ofVanilla(name), potion);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item);
    }
    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for" + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup); // if you want something in a different tab change INGREDIENTS
    }

}
