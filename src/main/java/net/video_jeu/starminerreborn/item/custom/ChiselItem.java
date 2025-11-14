package net.video_jeu.starminerreborn.item.custom;

import java.util.Map;

import net.minecraft.client.multiplayer.chat.LoggedChatMessage.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.video_jeu.starminerreborn.block.ModBlocks;

public class ChiselItem extends Item {
  // private static final Map<Block, Block> CHISEL_MAP = Map.of(
  // Blocks.STONE, Blocks.STONE_BRICKS,
  // Blocks.END_STONE, Blocks.END_STONE_BRICKS,
  // Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
  // Blocks.GOLD_BLOCK, Blocks.IRON_BLOCK,
  // Blocks.IRON_BLOCK, ModBlocks.ROSE.get());
  //
  public ChiselItem(Properties properties) {
    super(properties);
    // TODO Auto-generated constructor stub
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level level = context.getLevel();

    Entity player = context.getPlayer();

    player.setXRot(90);
    player.setYRot(90);

    // if (CHISEL_MAP.containsKey(clickedBlock)) {
    if (!level.isClientSide()) {
      // Only on serverside
    }
    if (level.isClientSide()) {
      // Only on clientside
    }
    // }

    return super.useOn(context);
  }

}
