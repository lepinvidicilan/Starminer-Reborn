/*     */ package jp.mc.ancientred.starminer.basics.tileentity.render;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockRotator;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityBlockRotator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileEnityRenderBlockRotator extends TileEntitySpecialRenderer {
/*     */   private RenderBlocks renderBlocks;
/*     */   
/*     */   public void renderTileEntityAt(TileEntityBlockRotator tileEntity, double x, double y, double z, float partialTick) {
/*  35 */     Block storedBlock = tileEntity.getStoredBlock();
/*  38 */     if (storedBlock == null || storedBlock == Blocks.field_150350_a)
/*     */       return; 
/*  43 */     EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
/*  44 */     ItemStack onHandItemStack = ((EntityPlayer)entityClientPlayerMP).field_71071_by.func_70448_g();
/*  45 */     if (onHandItemStack == null || !(onHandItemStack.func_77973_b() instanceof jp.mc.ancientred.starminer.basics.item.block.ItemBlockForRotator))
/*     */       return; 
/*  49 */     Tessellator tessellatorOrg = Tessellator.field_78398_a;
/*  50 */     IBlockAccess blockAccessOrg = this.renderBlocks.field_147845_a;
/*  52 */     GL11.glPushMatrix();
/*  55 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  56 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*  57 */     GravityDirection gravityDirection = tileEntity.getGravityDirection();
/*  58 */     GL11.glRotatef(-180.0F * gravityDirection.rotX, 1.0F, 0.0F, 0.0F);
/*  59 */     GL11.glRotatef(-180.0F * gravityDirection.rotZ, 0.0F, 0.0F, 1.0F);
/*  60 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  63 */     func_147499_a(TextureMap.field_110575_b);
/*  64 */     RenderHelper.func_74518_a();
/*  65 */     GL11.glBlendFunc(770, 771);
/*  66 */     GL11.glEnable(3042);
/*  68 */     if (Minecraft.func_71379_u()) {
/*  69 */       GL11.glShadeModel(7425);
/*     */     } else {
/*  71 */       GL11.glShadeModel(7424);
/*     */     } 
/*  73 */     tessellatorOrg.func_78382_b();
/*  74 */     tessellatorOrg.func_78386_a(1.0F, 1.0F, 1.0F);
/*  78 */     tessellatorOrg.func_78373_b((double)-tileEntity.field_145851_c, (double)-tileEntity.field_145848_d, (double)-tileEntity.field_145849_e);
/*     */     try {
/*  85 */       this.renderBlocks.func_147782_a(-0.009999999776482582D, -0.009999999776482582D, -0.009999999776482582D, 1.0099999904632568D, 1.0099999904632568D, 1.0099999904632568D);
/*  91 */       BlockRotator blockSelf = (BlockRotator)tileEntity.func_145838_q();
/*  92 */       blockSelf.rotateBlockSelfFlg = true;
/*  93 */       this.renderBlocks.field_147838_g = true;
/*  94 */       this.renderBlocks.func_147784_q(blockSelf, tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/*  95 */       this.renderBlocks.field_147838_g = false;
/*  96 */       this.renderBlocks.func_147784_q(blockSelf, tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/*  97 */       blockSelf.rotateBlockSelfFlg = false;
/*     */     } finally {
/* 101 */       this.renderBlocks.field_147845_a = blockAccessOrg;
/* 102 */       tessellatorOrg.func_78373_b(0.0D, 0.0D, 0.0D);
/*     */     } 
/* 105 */     tessellatorOrg.func_78381_a();
/* 106 */     RenderHelper.func_74519_b();
/* 107 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void func_147496_a(World world) {
/* 112 */     this.renderBlocks = new RenderBlocks((IBlockAccess)world);
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float partialTick) {
/* 117 */     renderTileEntityAt((TileEntityBlockRotator)tileEntity, x, y, z, partialTick);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */