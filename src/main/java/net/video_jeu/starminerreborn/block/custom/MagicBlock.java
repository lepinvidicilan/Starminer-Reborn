package net.video_jeu.starminerreborn.block.custom;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.video_jeu.starminerreborn.block.ModBlocks;
import net.video_jeu.starminerreborn.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class MagicBlock extends Block {

  public MagicBlock(Properties properties) {
    super(properties);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
      BlockHitResult hitResult) {

    level.playSound(player, pos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1f, 1f);

    return InteractionResult.SUCCESS;
  }

  @Override
  public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
    if (entity instanceof ItemEntity itemEntity) {
      if (itemEntity.getItem().getItem() == ModItems.CROWN.get()) {
        itemEntity.setItem(new ItemStack(ModBlocks.ROSE, itemEntity.getItem().getCount()));
        ItemEntity itemEntityCamelia = itemEntity.copy();
        itemEntityCamelia.setItem(new ItemStack(ModBlocks.CAMELIA, itemEntity.getItem().getCount()));
        level.addFreshEntity(itemEntityCamelia);
      }
    }

    super.stepOn(level, pos, state, entity);
  }
}
