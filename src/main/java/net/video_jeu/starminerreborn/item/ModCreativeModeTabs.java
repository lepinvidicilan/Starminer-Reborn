package net.video_jeu.starminerreborn.item;

import net.video_jeu.starminerreborn.block.ModBlocks;

import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.IEventBus;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.video_jeu.starminerreborn.StarminerReborn;

public class ModCreativeModeTabs {
  public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister
      .create(Registries.CREATIVE_MODE_TAB, StarminerReborn.MOD_ID);

  public static final Supplier<CreativeModeTab> STARMINER_REBORN_TAB = CREATIVE_MODE_TAB.register(
      "starminer_reborn_tab",
      () -> CreativeModeTab.builder()
          .icon(() -> new ItemStack(ModItems.FEUR.get()))
          .title(Component.translatable("creativetab.starminerreborn.starminer_reborn_items"))
          .displayItems((itemDisplayParameters, output) -> {
            output.accept(ModItems.FEUR);
            output.accept(ModItems.CROWN);
            output.accept(ModItems.CHISEL);
            output.accept(ModBlocks.MAGIC_BLOCK);
            output.accept(ModBlocks.CAMELIA);
            output.accept(ModBlocks.ROSE);
            output.accept(ModBlocks.MOGUS);
          }).build());

  public static void register(IEventBus eventBus) {
    CREATIVE_MODE_TAB.register(eventBus);
  }
}
