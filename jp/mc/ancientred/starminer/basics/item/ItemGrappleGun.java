/*     */ package jp.mc.ancientred.starminer.basics.item;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.entity.EntityGProjectile;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemGrappleGun extends Item {
/*     */   public static final String G_RAPPLE_NBT_TAG = "stmn.g_rapple";
/*     */   
/*     */   private ItemStack itemStackTemp;
/*     */   
/*     */   private boolean noMessageFlg;
/*     */   
/*     */   public ItemGrappleGun() {
/*  29 */     func_77625_d(1);
/*  30 */     func_77656_e(512);
/*  31 */     func_111206_d("starminer:g-rapplegun_arrow");
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack parItemStack, World parWorld, EntityPlayer parEntityPlayer) {
/*  37 */     if (parEntityPlayer.field_71071_by.func_146028_b(SMModContainer.GHookItem)) {
/*  38 */       boolean infinity = (parEntityPlayer.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, parItemStack) > 0);
/*  40 */       parWorld.func_72956_a((Entity)parEntityPlayer, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/*  44 */       EntityGProjectile entityGProjectile = new EntityGProjectile(parWorld, parEntityPlayer, 2.5F, EntityGProjectile.GProjectileType.gRappleHook);
/*  46 */       parItemStack.func_77972_a(1, (EntityLivingBase)parEntityPlayer);
/*  48 */       if (!infinity)
/*  50 */         parEntityPlayer.field_71071_by.func_146026_a(SMModContainer.GHookItem); 
/*  53 */       if (!parWorld.field_72995_K) {
/*  54 */         parWorld.func_72838_d((Entity)entityGProjectile);
/*  56 */         NBTTagCompound nbttagcompound = parItemStack.func_77978_p();
/*  57 */         if (nbttagcompound == null) {
/*  58 */           nbttagcompound = new NBTTagCompound();
/*  59 */           parItemStack.func_77982_d(nbttagcompound);
/*     */         } 
/*  63 */         if (nbttagcompound.func_74764_b("stmn.g_rapple")) {
/*  64 */           Entity oldGrapple = parWorld.func_73045_a(nbttagcompound.func_74762_e("stmn.g_rapple"));
/*  65 */           if (oldGrapple != null)
/*  66 */             oldGrapple.func_70106_y(); 
/*     */         } 
/*  71 */         nbttagcompound.func_74768_a("stmn.g_rapple", entityGProjectile.func_145782_y());
/*     */       } else {
/*  74 */         this.noMessageFlg = true;
/*  75 */         this.itemStackTemp = parItemStack;
/*  78 */         SMModContainer.proxy.showGrappleGunGuideMessage();
/*     */       } 
/*     */     } 
/*  82 */     return parItemStack;
/*     */   }
/*     */   
/*     */   public int func_77619_b() {
/*  86 */     return 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77662_d() {
/*  91 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77629_n_() {
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public String func_77657_g(ItemStack parItemStack) {
/* 106 */     if (this.itemStackTemp != parItemStack && 
/* 107 */       this.noMessageFlg) {
/* 108 */       this.noMessageFlg = false;
/* 109 */       SMModContainer.proxy.setRemainingHighlightTicksOFF();
/*     */     } 
/* 112 */     return super.func_77657_g(parItemStack);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */