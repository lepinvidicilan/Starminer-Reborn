/*     */ package jp.mc.ancientred.starminer.basics.item;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import jp.mc.ancientred.starminer.basics.entity.EntityFallingBlockEx;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.AllowedBlockDictionary;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemBlockGun extends Item {
/*  24 */   private String[] SUB_NAMES = new String[] { "short", "normal", "long", "spread" };
/*     */   
/*  25 */   private double[] SUB_EXCEED_RANGES = new double[] { 2.0D, 4.0D, 6.0D, 0.0D };
/*     */   
/*  26 */   private IIcon[] itemIconPrivate = new IIcon[4];
/*     */   
/*     */   public ItemBlockGun() {
/*  32 */     func_77625_d(1);
/*  33 */     func_77656_e(0);
/*  34 */     func_77627_a(true);
/*  35 */     func_111206_d("starminer:blockgun_long");
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack parItemStack, World parWorld, EntityPlayer parEntityPlayer) {
/*  41 */     Gravity gravity = Gravity.getGravityProp((Entity)parEntityPlayer);
/*  42 */     if (gravity == null)
/*  42 */       return parItemStack; 
/*  44 */     boolean infinity = parEntityPlayer.field_71075_bZ.field_75098_d;
/*  46 */     int damageOfItem = parItemStack.func_77960_j();
/*  47 */     if (damageOfItem < 0 || damageOfItem >= this.SUB_NAMES.length)
/*  49 */       damageOfItem = 0; 
/*  52 */     boolean doSpread = (damageOfItem == 3);
/*  53 */     int spreadBlockCount = doSpread ? 5 : 1;
/*  55 */     ItemStack[] mainInv = parEntityPlayer.field_71071_by.field_70462_a;
/*  57 */     for (int i = 0, count = 0; i < mainInv.length; i++) {
/*     */       Item item;
/*  58 */       if (mainInv[i] != null && (item = mainInv[i].func_77973_b()) != null) {
/*  59 */         Block block = Block.func_149634_a(item);
/*  60 */         if (block != null && block != Blocks.field_150350_a && AllowedBlockDictionary.isAllowed(block))
/*  64 */           while (count++ < spreadBlockCount) {
/*  65 */             if (count == 1)
/*  66 */               parWorld.func_72956_a((Entity)parEntityPlayer, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F)); 
/*  70 */             EntityFallingBlockEx blockEntity = new EntityFallingBlockEx(parWorld, parEntityPlayer.field_70165_t, parEntityPlayer.field_70163_u, parEntityPlayer.field_70161_v, block, mainInv[i].func_77960_j());
/*  71 */             gravity.setGravityFixedPlayerShootVec(parEntityPlayer, (Entity)blockEntity, 1.0F);
/*  73 */             blockEntity.setExceedRange(this.SUB_EXCEED_RANGES[damageOfItem]);
/*  75 */             if (doSpread) {
/*  76 */               blockEntity.field_70159_w += field_77697_d.nextDouble() * 0.8D - field_77697_d.nextDouble() * 0.8D;
/*  77 */               blockEntity.field_70181_x += field_77697_d.nextDouble() * 0.8D - field_77697_d.nextDouble() * 0.8D;
/*  78 */               blockEntity.field_70179_y += field_77697_d.nextDouble() * 0.8D - field_77697_d.nextDouble() * 0.8D;
/*     */             } 
/*  81 */             blockEntity.field_70169_q = blockEntity.spawnPosX = blockEntity.field_70165_t;
/*  82 */             blockEntity.field_70167_r = blockEntity.spawnPosY = blockEntity.field_70163_u;
/*  83 */             blockEntity.field_70166_s = blockEntity.spawnPosZ = blockEntity.field_70161_v;
/*  85 */             if (!infinity)
/*  87 */               parEntityPlayer.field_71071_by.func_146026_a(item); 
/*  90 */             if (!parWorld.field_72995_K)
/*  91 */               parWorld.func_72838_d((Entity)blockEntity); 
/*  94 */             if (mainInv[i] == null || (mainInv[i]).field_77994_a == 0)
/*  94 */               return parItemStack; 
/*     */           }  
/*     */       } 
/*     */     } 
/* 102 */     return parItemStack;
/*     */   }
/*     */   
/*     */   public String func_77667_c(ItemStack itemStack) {
/* 106 */     int i = itemStack.func_77960_j();
/* 108 */     if (i < 0 || i >= this.SUB_NAMES.length)
/* 110 */       i = 0; 
/* 113 */     return func_77658_a() + "." + this.SUB_NAMES[i];
/*     */   }
/*     */   
/*     */   public int func_77619_b() {
/* 117 */     return 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item item, CreativeTabs tab, List list) {
/* 123 */     for (int i = 0; i < this.SUB_NAMES.length; i++)
/* 125 */       list.add(new ItemStack(item, 1, i)); 
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77662_d() {
/* 131 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77629_n_() {
/* 137 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int damage) {
/* 143 */     return this.itemIconPrivate[damage];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister) {
/* 148 */     this.itemIconPrivate[0] = par1IconRegister.func_94245_a("starminer:blockgun_short");
/* 149 */     this.itemIconPrivate[1] = par1IconRegister.func_94245_a("starminer:blockgun_normal");
/* 150 */     this.itemIconPrivate[2] = par1IconRegister.func_94245_a("starminer:blockgun_long");
/* 151 */     this.itemIconPrivate[3] = par1IconRegister.func_94245_a("starminer:blockgun_spread");
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */