/*     */ package jp.mc.ancientred.starminer.basics.entity;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockAnvil;
/*     */ import net.minecraft.block.BlockDragonEgg;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderFallingBlockEx extends Render {
/*  26 */   private final RenderBlocks renderBlocks = new RenderBlocks();
/*     */   
/*     */   public RenderFallingBlockEx() {
/*  30 */     this.field_76989_e = 0.5F;
/*     */   }
/*     */   
/*     */   public void doRender(EntityFallingBlockEx entityFallingBlockEx, double posX, double poxY, double poxZ, float p_76986_8_, float partialTick) {
/*  41 */     World world = entityFallingBlockEx.func_145807_e();
/*  42 */     Block block = entityFallingBlockEx.func_145805_f();
/*  43 */     int i = MathHelper.func_76128_c(entityFallingBlockEx.field_70165_t);
/*  44 */     int j = MathHelper.func_76128_c(entityFallingBlockEx.field_70163_u);
/*  45 */     int k = MathHelper.func_76128_c(entityFallingBlockEx.field_70161_v);
/*  47 */     if (block != null && block != world.func_147439_a(i, j, k)) {
/*  49 */       GL11.glPushMatrix();
/*  50 */       GL11.glTranslatef((float)posX, (float)poxY, (float)poxZ);
/*  51 */       func_110777_b((Entity)entityFallingBlockEx);
/*  52 */       GL11.glDisable(2896);
/*  55 */       if (block instanceof BlockAnvil) {
/*  57 */         this.renderBlocks.field_147845_a = (IBlockAccess)world;
/*  58 */         Tessellator tessellator = Tessellator.field_78398_a;
/*  59 */         tessellator.func_78382_b();
/*  60 */         tessellator.func_78373_b((double)((float)-i - 0.5F), (double)((float)-j - 0.5F), (double)((float)-k - 0.5F));
/*  61 */         this.renderBlocks.func_147780_a((BlockAnvil)block, i, j, k, entityFallingBlockEx.blockMeta);
/*  62 */         tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
/*  63 */         tessellator.func_78381_a();
/*  65 */       } else if (block instanceof BlockDragonEgg) {
/*  67 */         this.renderBlocks.field_147845_a = (IBlockAccess)world;
/*  68 */         Tessellator tessellator = Tessellator.field_78398_a;
/*  69 */         tessellator.func_78382_b();
/*  70 */         tessellator.func_78373_b((double)((float)-i - 0.5F), (double)((float)-j - 0.5F), (double)((float)-k - 0.5F));
/*  71 */         this.renderBlocks.func_147802_a((BlockDragonEgg)block, i, j, k);
/*  72 */         tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
/*  73 */         tessellator.func_78381_a();
/*  75 */       } else if (block instanceof jp.mc.ancientred.starminer.basics.block.BlockDirtGrassEx) {
/*  77 */         this.renderBlocks.func_147775_a(block);
/*  78 */         renderBlockDirtEx(block, world, i, j, k, entityFallingBlockEx.blockMeta);
/*     */       } else {
/*  82 */         this.renderBlocks.func_147775_a(block);
/*  83 */         this.renderBlocks.func_147749_a(block, world, i, j, k, entityFallingBlockEx.blockMeta);
/*     */       } 
/*  86 */       GL11.glEnable(2896);
/*  87 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void renderBlockDirtEx(Block block, World world, int x, int y, int z, int meta) {
/*     */     int l;
/*  93 */     if ((meta & 0x8) == 0) {
/*  94 */       l = Blocks.field_150349_c.func_149720_d((IBlockAccess)world, x, y, z);
/*     */     } else {
/*  96 */       l = block.func_149720_d((IBlockAccess)world, x, y, z);
/*     */     } 
/*  98 */     float cf = (float)(l >> 16 & 0xFF) / 255.0F;
/*  99 */     float cf1 = (float)(l >> 8 & 0xFF) / 255.0F;
/* 100 */     float cf2 = (float)(l & 0xFF) / 255.0F;
/* 102 */     float f = 0.5F;
/* 103 */     float f1 = 1.0F;
/* 104 */     float f2 = 0.8F;
/* 105 */     float f3 = 0.6F;
/* 106 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 107 */     tessellator.func_78382_b();
/* 108 */     tessellator.func_78380_c(block.func_149677_c((IBlockAccess)world, x, y, z));
/* 109 */     tessellator.func_78386_a(cf, cf1, cf2);
/* 110 */     this.renderBlocks.func_147768_a(block, -0.5D, -0.5D, -0.5D, this.renderBlocks.func_147787_a(block, 0, meta));
/* 111 */     tessellator.func_78386_a(cf, cf1, cf2);
/* 112 */     this.renderBlocks.func_147806_b(block, -0.5D, -0.5D, -0.5D, this.renderBlocks.func_147787_a(block, 1, meta));
/* 113 */     tessellator.func_78386_a(cf, cf1, cf2);
/* 114 */     this.renderBlocks.func_147761_c(block, -0.5D, -0.5D, -0.5D, this.renderBlocks.func_147787_a(block, 2, meta));
/* 115 */     tessellator.func_78386_a(cf, cf1, cf2);
/* 116 */     this.renderBlocks.func_147734_d(block, -0.5D, -0.5D, -0.5D, this.renderBlocks.func_147787_a(block, 3, meta));
/* 117 */     tessellator.func_78386_a(cf, cf1, cf2);
/* 118 */     this.renderBlocks.func_147798_e(block, -0.5D, -0.5D, -0.5D, this.renderBlocks.func_147787_a(block, 4, meta));
/* 119 */     tessellator.func_78386_a(cf, cf1, cf2);
/* 120 */     this.renderBlocks.func_147764_f(block, -0.5D, -0.5D, -0.5D, this.renderBlocks.func_147787_a(block, 5, meta));
/* 121 */     tessellator.func_78381_a();
/*     */   }
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityFallingBlockEx p_110775_1_) {
/* 128 */     return TextureMap.field_110575_b;
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity p_110775_1_) {
/* 136 */     return getEntityTexture((EntityFallingBlockEx)p_110775_1_);
/*     */   }
/*     */   
/*     */   public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 147 */     doRender((EntityFallingBlockEx)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */