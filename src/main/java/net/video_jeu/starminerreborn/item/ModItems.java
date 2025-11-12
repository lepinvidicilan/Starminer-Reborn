package net.video_jeu.starminerreborn.item;

import net.neoforged.bus.api.IEventBus;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.video_jeu.starminerreborn.StarminerReborn;
import net.video_jeu.starminerreborn.item.custom.ChiselItem;

public class ModItems {
  public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(StarminerReborn.MOD_ID);

  public static final DeferredItem<Item> FEUR = ITEMS.register("feur", () -> new Item(new Item.Properties()));

  public static final DeferredItem<Item> CROWN = ITEMS.register("crown", () -> new Item(new Item.Properties()));

  public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
      () -> new ChiselItem(new Item.Properties().durability(32)));

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
