/*    */ package jp.mc.ancientred.starminer.basics.dimention;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.chunk.Chunk;
/*    */ import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
/*    */ 
/*    */ public class ChunkHelper {
/*    */   public static final void fillinChunk(Chunk chunk, World world, Block[] blocksData, byte[] metadata, int chunkX, int chunkZ) {
/* 14 */     ExtendedBlockStorage[] storageArrays = chunk.func_76587_i();
/* 15 */     int max = blocksData.length / 256;
/* 17 */     for (int y = 0; y < max; y++) {
/* 19 */       for (int z = 0; z < 16; z++) {
/* 21 */         for (int x = 0; x < 16; x++) {
/* 23 */           int idx = y << 8 | z << 4 | x;
/* 24 */           Block id = blocksData[idx];
/* 25 */           int meta = metadata[idx];
/* 27 */           if (id != null || meta != 0) {
/* 29 */             int storageBlock = y >> 4;
/* 31 */             if (storageArrays[storageBlock] == null)
/* 33 */               storageArrays[storageBlock] = new ExtendedBlockStorage(storageBlock << 4, !world.field_73011_w.field_76576_e); 
/* 36 */             storageArrays[storageBlock].func_150818_a(x, y & 0xF, z, id);
/* 37 */             if (id == Blocks.field_150426_aN)
/* 38 */               storageArrays[storageBlock].func_76677_d(x, y & 0xF, z, 15); 
/* 40 */             storageArrays[storageBlock].func_76654_b(x, y & 0xF, z, meta);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */