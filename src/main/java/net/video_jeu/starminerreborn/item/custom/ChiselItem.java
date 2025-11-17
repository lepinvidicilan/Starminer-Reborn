package net.video_jeu.starminerreborn.item.custom;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

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

    System.out.println(player.getGravity());

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
