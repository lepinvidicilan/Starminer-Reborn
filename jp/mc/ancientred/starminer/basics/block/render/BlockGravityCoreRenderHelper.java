/*     */ package jp.mc.ancientred.starminer.basics.block.render;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockGravityCore;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class BlockGravityCoreRenderHelper implements ISimpleBlockRenderingHandler {
/*     */   public static final int RENDER_TYPE = 4341803;
/*     */   
/*     */   public int getRenderId() {
/*  22 */     return 4341803;
/*     */   }
/*     */   
/*     */   public void renderInventoryBlock(Block par1Block, int metadata, int modelID, RenderBlocks renderer) {
/*  26 */     IIcon coreItemIcon = ((BlockGravityCore)par1Block).func_149691_a(0, 0);
/*  27 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  28 */     double lb = 0.40625D;
/*  29 */     double hb = 0.59375D;
/*  30 */     renderer.func_147757_a(coreItemIcon);
/*  31 */     for (int k = 0; k < 8; k++) {
/*  33 */       if (k == 0) {
/*  35 */         renderer.func_147782_a(0.0D, 0.0D, 0.0D, lb, lb, lb);
/*  37 */       } else if (k == 1) {
/*  39 */         renderer.func_147782_a(hb, 0.0D, 0.0D, 1.0D, lb, lb);
/*  41 */       } else if (k == 2) {
/*  43 */         renderer.func_147782_a(0.0D, hb, 0.0D, lb, 1.0D, lb);
/*  45 */       } else if (k == 3) {
/*  47 */         renderer.func_147782_a(0.0D, 0.0D, hb, lb, lb, 1.0D);
/*  49 */       } else if (k == 4) {
/*  51 */         renderer.func_147782_a(hb, hb, 0.0D, 1.0D, 1.0D, lb);
/*  54 */       } else if (k == 5) {
/*  56 */         renderer.func_147782_a(hb, 0.0D, hb, 1.0D, lb, 1.0D);
/*  58 */       } else if (k == 6) {
/*  60 */         renderer.func_147782_a(0.0D, hb, hb, lb, 1.0D, 1.0D);
/*     */       } else {
/*  63 */         renderer.func_147782_a(hb, hb, hb, 1.0D, 1.0D, 1.0D);
/*     */       } 
/*  66 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  67 */       tessellator.func_78382_b();
/*  68 */       tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
/*  69 */       renderer.func_147768_a(par1Block, 0.0D, 0.0D, 0.0D, coreItemIcon);
/*  70 */       tessellator.func_78381_a();
/*  71 */       tessellator.func_78382_b();
/*  72 */       tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/*  73 */       renderer.func_147806_b(par1Block, 0.0D, 0.0D, 0.0D, coreItemIcon);
/*  74 */       tessellator.func_78381_a();
/*  75 */       tessellator.func_78382_b();
/*  76 */       tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*  77 */       renderer.func_147761_c(par1Block, 0.0D, 0.0D, 0.0D, coreItemIcon);
/*  78 */       tessellator.func_78381_a();
/*  79 */       tessellator.func_78382_b();
/*  80 */       tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/*  81 */       renderer.func_147734_d(par1Block, 0.0D, 0.0D, 0.0D, coreItemIcon);
/*  82 */       tessellator.func_78381_a();
/*  83 */       tessellator.func_78382_b();
/*  84 */       tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
/*  85 */       renderer.func_147798_e(par1Block, 0.0D, 0.0D, 0.0D, coreItemIcon);
/*  86 */       tessellator.func_78381_a();
/*  87 */       tessellator.func_78382_b();
/*  88 */       tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
/*  89 */       renderer.func_147764_f(par1Block, 0.0D, 0.0D, 0.0D, coreItemIcon);
/*  90 */       tessellator.func_78381_a();
/*  91 */       GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */     } 
/*  94 */     renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  95 */     renderer.func_147771_a();
/*     */   }
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/* 100 */     renderer.field_147837_f = true;
/* 101 */     double lb = 0.40625D;
/* 102 */     double hb = 0.59375D;
/* 103 */     renderer.field_147837_f = false;
/* 104 */     renderer.func_147757_a(((BlockGravityCore)block).func_149691_a(0, 0));
/* 105 */     renderer.func_147782_a(0.0D, 0.0D, 0.0D, lb, lb, lb);
/* 106 */     renderer.func_147784_q(block, x, y, z);
/* 108 */     renderer.func_147782_a(hb, 0.0D, 0.0D, 1.0D, lb, lb);
/* 109 */     renderer.func_147784_q(block, x, y, z);
/* 111 */     renderer.func_147782_a(0.0D, hb, 0.0D, lb, 1.0D, lb);
/* 112 */     renderer.func_147784_q(block, x, y, z);
/* 114 */     renderer.func_147782_a(0.0D, 0.0D, hb, lb, lb, 1.0D);
/* 115 */     renderer.func_147784_q(block, x, y, z);
/* 117 */     renderer.func_147782_a(hb, hb, 0.0D, 1.0D, 1.0D, lb);
/* 118 */     renderer.func_147784_q(block, x, y, z);
/* 120 */     renderer.func_147782_a(hb, 0.0D, hb, 1.0D, lb, 1.0D);
/* 121 */     renderer.func_147784_q(block, x, y, z);
/* 123 */     renderer.func_147782_a(0.0D, hb, hb, lb, 1.0D, 1.0D);
/* 124 */     renderer.func_147784_q(block, x, y, z);
/* 126 */     renderer.func_147782_a(hb, hb, hb, 1.0D, 1.0D, 1.0D);
/* 127 */     renderer.func_147784_q(block, x, y, z);
/* 129 */     renderer.func_147757_a(Blocks.field_150353_l.func_149691_a(0, 0));
/* 130 */     renderer.func_147782_a(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
/* 131 */     renderer.func_147736_d(block, x, y, z, 0.2F, 0.3F, 1.0F);
/* 137 */     renderer.field_147837_f = false;
/* 138 */     renderer.func_147771_a();
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 144 */     return true;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */