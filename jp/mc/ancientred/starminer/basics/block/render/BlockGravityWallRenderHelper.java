/*     */ package jp.mc.ancientred.starminer.basics.block.render;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class BlockGravityWallRenderHelper implements ISimpleBlockRenderingHandler {
/*     */   public static final int RENDER_TYPE = 4341804;
/*     */   
/*     */   public int getRenderId() {
/*  22 */     return 4341804;
/*     */   }
/*     */   
/*     */   public void renderInventoryBlock(Block par1Block, int metadata, int modelID, RenderBlocks renderer) {
/*  26 */     IIcon icon = par1Block.func_149691_a(0, 0);
/*  27 */     GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  28 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  29 */     double lw = 0.0625D;
/*  30 */     renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, lw, 1.0D);
/*  31 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  32 */     tessellator.func_78382_b();
/*  33 */     tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
/*  34 */     renderer.func_147768_a(par1Block, 0.0D, 0.0D, 0.0D, icon);
/*  35 */     tessellator.func_78381_a();
/*  36 */     tessellator.func_78382_b();
/*  37 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/*  38 */     renderer.func_147806_b(par1Block, 0.0D, 0.0D, 0.0D, icon);
/*  39 */     tessellator.func_78381_a();
/*  40 */     tessellator.func_78382_b();
/*  41 */     tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*  42 */     renderer.func_147761_c(par1Block, 0.0D, 0.0D, 0.0D, icon);
/*  43 */     tessellator.func_78381_a();
/*  44 */     tessellator.func_78382_b();
/*  45 */     tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/*  46 */     renderer.func_147734_d(par1Block, 0.0D, 0.0D, 0.0D, icon);
/*  47 */     tessellator.func_78381_a();
/*  48 */     tessellator.func_78382_b();
/*  49 */     tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
/*  50 */     renderer.func_147798_e(par1Block, 0.0D, 0.0D, 0.0D, icon);
/*  51 */     tessellator.func_78381_a();
/*  52 */     tessellator.func_78382_b();
/*  53 */     tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
/*  54 */     renderer.func_147764_f(par1Block, 0.0D, 0.0D, 0.0D, icon);
/*  55 */     tessellator.func_78381_a();
/*  56 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*  61 */     double lw = 0.0625D;
/*  62 */     double hi = 0.9375D;
/*  63 */     int meta = world.func_72805_g(x, y, z);
/*  68 */     switch (meta) {
/*     */       case 0:
/*  70 */         renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, lw, 1.0D);
/*  71 */         renderer.func_147784_q(block, x, y, z);
/*     */         break;
/*     */       case 1:
/*  74 */         renderer.func_147782_a(0.0D, hi, 0.0D, 1.0D, 1.0D, 1.0D);
/*  75 */         renderer.func_147784_q(block, x, y, z);
/*     */         break;
/*     */       case 2:
/*  78 */         renderer.func_147782_a(0.0D, 0.0D, 0.0D, lw, 1.0D, 1.0D);
/*  79 */         renderer.func_147784_q(block, x, y, z);
/*     */         break;
/*     */       case 3:
/*  82 */         renderer.func_147782_a(hi, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  83 */         renderer.func_147784_q(block, x, y, z);
/*     */         break;
/*     */       case 4:
/*  86 */         renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, lw);
/*  87 */         renderer.func_147784_q(block, x, y, z);
/*     */         break;
/*     */       case 5:
/*  90 */         renderer.func_147782_a(0.0D, 0.0D, hi, 1.0D, 1.0D, 1.0D);
/*  91 */         renderer.func_147784_q(block, x, y, z);
/*     */         break;
/*     */     } 
/*  96 */     renderer.func_147771_a();
/*  97 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 102 */     return true;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */