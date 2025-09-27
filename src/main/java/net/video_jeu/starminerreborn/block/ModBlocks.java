package net.video_jeu.starminerreborn.block;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.video_jeu.starminerreborn.StarminerReborn;
import net.video_jeu.starminerreborn.item.ModItems;

public class ModBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(StarminerReborn.MOD_ID);
	
	public static final DeferredBlock<Block> MOGUS = registerBlock("mogus", 
			() -> new Block(BlockBehaviour.Properties.of()
					.strength(4f)
					.requiresCorrectToolForDrops()
					.sound(SoundType.AMETHYST)
					)
			);
	
	public static final DeferredBlock<Block> ROSE = registerBlock("rose",
			() -> new Block(BlockBehaviour.Properties.of()
					.noCollission()
					.instabreak()
					.requiresCorrectToolForDrops()
					.sound(SoundType.GRASS)
					)
			);
	
	public static final DeferredBlock<Block> CAMELIA = registerBlock("camelia",
			() -> new Block(BlockBehaviour.Properties.of()
					.noCollission()
					.instabreak()
					.requiresCorrectToolForDrops()
					.sound(SoundType.GRASS)
					)
			);
	
	private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
		DeferredBlock<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}
	
	private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
		ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}
	
	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
