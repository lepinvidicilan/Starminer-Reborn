/*    */ package jp.mc.ancientred.starminer.basics.block.render;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.EntityRenderer;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class BlockDirtExRenderHelper implements ISimpleBlockRenderingHandler {
/*    */   public static final int RENDER_TYPE = 4341802;
/*    */   
/*    */   public int getRenderId() {
/* 22 */     return 4341802;
/*    */   }
/*    */   
/*    */   public void renderInventoryBlock(Block par1Block, int metadata, int modelID, RenderBlocks renderer) {
/* 26 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 27 */     par1Block.func_149683_g();
/* 28 */     renderer.func_147775_a(par1Block);
/* 29 */     GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 30 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 31 */     tessellator.func_78382_b();
/* 32 */     tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
/* 33 */     renderer.func_147768_a(par1Block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(par1Block, 0, metadata));
/* 34 */     tessellator.func_78381_a();
/* 35 */     tessellator.func_78382_b();
/* 36 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 37 */     renderer.func_147806_b(par1Block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(par1Block, 1, metadata));
/* 38 */     tessellator.func_78381_a();
/* 39 */     tessellator.func_78382_b();
/* 40 */     tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/* 41 */     renderer.func_147761_c(par1Block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(par1Block, 2, metadata));
/* 42 */     tessellator.func_78381_a();
/* 43 */     tessellator.func_78382_b();
/* 44 */     tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/* 45 */     renderer.func_147734_d(par1Block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(par1Block, 3, metadata));
/* 46 */     tessellator.func_78381_a();
/* 47 */     tessellator.func_78382_b();
/* 48 */     tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 49 */     renderer.func_147798_e(par1Block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(par1Block, 4, metadata));
/* 50 */     tessellator.func_78381_a();
/* 51 */     tessellator.func_78382_b();
/* 52 */     tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
/* 53 */     renderer.func_147764_f(par1Block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(par1Block, 5, metadata));
/* 54 */     tessellator.func_78381_a();
/* 55 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */   }
/*    */   
/* 57 */   private static Vec3[] vec = new Vec3[60];
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*    */     int l;
/* 60 */     int meta = world.func_72805_g(x, y, z);
/* 62 */     if ((meta & 0x8) == 0) {
/* 63 */       l = Blocks.field_150349_c.func_149720_d(renderer.field_147845_a, x, y, z);
/*    */     } else {
/* 65 */       l = block.func_149720_d(renderer.field_147845_a, x, y, z);
/*    */     } 
/* 67 */     float f = (float)(l >> 16 & 0xFF) / 255.0F;
/* 68 */     float f1 = (float)(l >> 8 & 0xFF) / 255.0F;
/* 69 */     float f2 = (float)(l & 0xFF) / 255.0F;
/* 71 */     if (EntityRenderer.field_78517_a) {
/* 73 */       float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
/* 74 */       float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
/* 75 */       float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
/* 76 */       f = f3;
/* 77 */       f1 = f4;
/* 78 */       f2 = f5;
/*    */     } 
/* 81 */     return renderer.func_147736_d(block, x, y, z, f, f1, f2);
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId) {
/* 87 */     return true;
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */