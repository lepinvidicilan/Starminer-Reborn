/*    */ package jp.mc.ancientred.starminer.basics.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderStarSquid extends RenderLiving {
/* 18 */   private static final ResourceLocation squidTextures = new ResourceLocation("textures/entity/squid.png");
/*    */   
/*    */   public RenderStarSquid(ModelBase par1ModelBase, float par2) {
/* 22 */     super(par1ModelBase, par2);
/*    */   }
/*    */   
/*    */   public void renderLivingSquid(EntityStarSquid par1EntityMySquid, double par2, double par4, double par6, float par8, float par9) {
/* 30 */     func_76986_a((EntityLiving)par1EntityMySquid, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation getSquidTextures(EntityStarSquid par1EntityMySquid) {
/* 35 */     return squidTextures;
/*    */   }
/*    */   
/*    */   protected void rotateSquidsCorpse(EntityStarSquid par1EntityMySquid, float par2, float par3, float par4) {
/* 43 */     float f3 = par1EntityMySquid.prevSquidPitch + (par1EntityMySquid.squidPitch - par1EntityMySquid.prevSquidPitch) * par4;
/* 44 */     float f4 = par1EntityMySquid.prevSquidYaw + (par1EntityMySquid.squidYaw - par1EntityMySquid.prevSquidYaw) * par4;
/* 45 */     GL11.glTranslatef(0.0F, 0.5F, 0.0F);
/* 46 */     GL11.glRotatef(180.0F - par3, 0.0F, 1.0F, 0.0F);
/* 47 */     GL11.glRotatef(f3, 1.0F, 0.0F, 0.0F);
/* 48 */     GL11.glRotatef(f4, 0.0F, 1.0F, 0.0F);
/* 49 */     GL11.glTranslatef(0.0F, -1.2F, 0.0F);
/*    */   }
/*    */   
/*    */   protected float handleRotationFloat(EntityStarSquid par1EntityMySquid, float par2) {
/* 54 */     return par1EntityMySquid.prevTentacleAngle + (par1EntityMySquid.tentacleAngle - par1EntityMySquid.prevTentacleAngle) * par2;
/*    */   }
/*    */   
/*    */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 59 */     renderLivingSquid((EntityStarSquid)par1EntityLiving, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected float func_77044_a(EntityLivingBase par1EntityLivingBase, float par2) {
/* 67 */     return handleRotationFloat((EntityStarSquid)par1EntityLivingBase, par2);
/*    */   }
/*    */   
/*    */   protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/* 72 */     rotateSquidsCorpse((EntityStarSquid)par1EntityLivingBase, par2, par3, par4);
/*    */   }
/*    */   
/*    */   public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 77 */     renderLivingSquid((EntityStarSquid)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity par1Entity) {
/* 85 */     return getSquidTextures((EntityStarSquid)par1Entity);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 96 */     renderLivingSquid((EntityStarSquid)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */