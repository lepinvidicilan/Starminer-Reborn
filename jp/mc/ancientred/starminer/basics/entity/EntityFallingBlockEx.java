/*     */ package jp.mc.ancientred.starminer.basics.entity;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFalling;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityFallingBlockEx extends EntityArrow implements IEntityAdditionalSpawnData {
/*     */   private Block block;
/*     */   
/*     */   public int blockMeta;
/*     */   
/*     */   public int fallTime;
/*     */   
/*     */   public double exceedRange;
/*     */   
/*     */   public double spawnPosX;
/*     */   
/*     */   public double spawnPosY;
/*     */   
/*     */   public double spawnPosZ;
/*     */   
/*     */   public boolean shouldDropItem;
/*     */   
/*     */   private boolean isBreakingAnvil;
/*     */   
/*     */   private boolean isAnvil;
/*     */   
/*     */   private int fallHurtMax;
/*     */   
/*     */   private float fallHurtAmount;
/*     */   
/*     */   public EntityFallingBlockEx(World p_i1706_1_) {
/*  35 */     super(p_i1706_1_);
/*  36 */     this.shouldDropItem = true;
/*  37 */     this.fallHurtMax = 40;
/*  38 */     this.fallHurtAmount = 2.0F;
/*     */   }
/*     */   
/*     */   public EntityFallingBlockEx(World world, double posX, double posY, double posZ, Block block) {
/*  43 */     this(world, posX, posY, posZ, block, 0);
/*     */   }
/*     */   
/*     */   public EntityFallingBlockEx(World world, double posX, double poxY, double posZ, Block block, int meta) {
/*  48 */     super(world);
/*  49 */     this.shouldDropItem = true;
/*  50 */     this.exceedRange = 3.0D;
/*  51 */     this.fallHurtMax = 40;
/*  52 */     this.fallHurtAmount = 2.0F;
/*  53 */     this.block = block;
/*  54 */     this.blockMeta = meta;
/*  55 */     this.field_70156_m = true;
/*  56 */     func_70105_a(0.2F, 0.2F);
/*  57 */     this.field_70129_M = this.field_70131_O / 2.0F;
/*  58 */     func_70107_b(posX, poxY, posZ);
/*  59 */     this.field_70159_w = 0.0D;
/*  60 */     this.field_70181_x = 0.0D;
/*  61 */     this.field_70179_y = 0.0D;
/*  62 */     this.spawnPosX = this.field_70169_q = posX;
/*  63 */     this.spawnPosY = this.field_70167_r = poxY;
/*  64 */     this.spawnPosZ = this.field_70166_s = posZ;
/*     */   }
/*     */   
/*     */   public void setExceedRange(double distance) {
/*  68 */     this.exceedRange = distance;
/*  69 */     if (this.exceedRange == 0.0D)
/*  70 */       this.exceedRange = 5.0D + this.field_70170_p.field_73012_v.nextDouble() * 5.0D; 
/*     */   }
/*     */   
/*     */   protected boolean func_70041_e_() {
/*  79 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_70088_a() {}
/*     */   
/*     */   public boolean func_70067_L() {
/*  89 */     return !this.field_70128_L;
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/*  97 */     if (this.block.func_149688_o() == Material.field_151579_a) {
/*  99 */       func_70106_y();
/*     */     } else {
/* 103 */       this.field_70169_q = this.field_70165_t;
/* 104 */       this.field_70167_r = this.field_70163_u;
/* 105 */       this.field_70166_s = this.field_70161_v;
/* 106 */       this.fallTime++;
/* 108 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 109 */       this.field_70159_w *= 0.9800000190734863D;
/* 110 */       this.field_70181_x *= 0.9800000190734863D;
/* 111 */       this.field_70179_y *= 0.9800000190734863D;
/* 113 */       if (!this.field_70170_p.field_72995_K) {
/* 115 */         int i = MathHelper.func_76128_c(this.field_70165_t);
/* 116 */         int j = MathHelper.func_76128_c(this.field_70163_u);
/* 117 */         int k = MathHelper.func_76128_c(this.field_70161_v);
/* 119 */         double farFromSpawn = (double)MathHelper.func_76133_a(func_70092_e(this.spawnPosX, this.spawnPosY, this.spawnPosZ));
/* 120 */         boolean exceedRange = (farFromSpawn > this.exceedRange);
/* 122 */         if (exceedRange || this.field_70122_E || this.field_70123_F || this.field_70124_G) {
/* 124 */           this.field_70159_w *= 0.699999988079071D;
/* 125 */           this.field_70179_y *= 0.699999988079071D;
/* 126 */           this.field_70181_x *= -0.5D;
/* 128 */           if (this.field_70170_p.func_147439_a(i, j, k) != Blocks.field_150326_M) {
/* 130 */             func_70106_y();
/* 132 */             if (this.field_70170_p.func_147465_d(i, j, k, this.block, this.blockMeta, 3)) {
/* 134 */               if (this.block instanceof BlockFalling)
/* 136 */                 ((BlockFalling)this.block).func_149828_a(this.field_70170_p, i, j, k, this.blockMeta); 
/* 139 */             } else if (this.shouldDropItem && !this.isBreakingAnvil) {
/* 141 */               func_70099_a(new ItemStack(this.block, 1, this.block.func_149692_a(this.blockMeta)), 0.0F);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound p_70014_1_) {
/* 154 */     p_70014_1_.func_74780_a("exceedR", this.exceedRange);
/* 155 */     p_70014_1_.func_74780_a("spwPosX", this.spawnPosX);
/* 156 */     p_70014_1_.func_74780_a("spwPosY", this.spawnPosY);
/* 157 */     p_70014_1_.func_74780_a("spwPosZ", this.spawnPosZ);
/* 158 */     p_70014_1_.func_74774_a("Tile", (byte)Block.func_149682_b(this.block));
/* 159 */     p_70014_1_.func_74768_a("TileID", Block.func_149682_b(this.block));
/* 160 */     p_70014_1_.func_74774_a("Data", (byte)this.blockMeta);
/* 161 */     p_70014_1_.func_74774_a("Time", (byte)this.fallTime);
/* 162 */     p_70014_1_.func_74757_a("DropItem", this.shouldDropItem);
/* 163 */     p_70014_1_.func_74757_a("HurtEntities", this.isAnvil);
/* 164 */     p_70014_1_.func_74776_a("FallHurtAmount", this.fallHurtAmount);
/* 165 */     p_70014_1_.func_74768_a("FallHurtMax", this.fallHurtMax);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound p_70037_1_) {
/* 173 */     this.exceedRange = p_70037_1_.func_74769_h("exceedR");
/* 174 */     this.spawnPosX = p_70037_1_.func_74769_h("spwPosX");
/* 175 */     this.spawnPosY = p_70037_1_.func_74769_h("spwPosY");
/* 176 */     this.spawnPosZ = p_70037_1_.func_74769_h("spwPosZ");
/* 178 */     if (p_70037_1_.func_150297_b("TileID", 99)) {
/* 180 */       this.block = Block.func_149729_e(p_70037_1_.func_74762_e("TileID"));
/*     */     } else {
/* 184 */       this.block = Block.func_149729_e(p_70037_1_.func_74771_c("Tile") & 0xFF);
/*     */     } 
/* 187 */     this.blockMeta = p_70037_1_.func_74771_c("Data") & 0xFF;
/* 188 */     this.fallTime = p_70037_1_.func_74771_c("Time") & 0xFF;
/* 190 */     if (p_70037_1_.func_150297_b("HurtEntities", 99)) {
/* 192 */       this.isAnvil = p_70037_1_.func_74767_n("HurtEntities");
/* 193 */       this.fallHurtAmount = p_70037_1_.func_74760_g("FallHurtAmount");
/* 194 */       this.fallHurtMax = p_70037_1_.func_74762_e("FallHurtMax");
/* 196 */     } else if (this.block == Blocks.field_150467_bQ) {
/* 198 */       this.isAnvil = true;
/*     */     } 
/* 201 */     if (p_70037_1_.func_150297_b("DropItem", 99))
/* 203 */       this.shouldDropItem = p_70037_1_.func_74767_n("DropItem"); 
/*     */   }
/*     */   
/*     */   public void func_145806_a(boolean p_145806_1_) {
/* 209 */     this.isAnvil = p_145806_1_;
/*     */   }
/*     */   
/*     */   public void func_85029_a(CrashReportCategory p_85029_1_) {
/* 214 */     super.func_85029_a(p_85029_1_);
/* 215 */     p_85029_1_.func_71507_a("Immitating block ID", Integer.valueOf(Block.func_149682_b(this.block)));
/* 216 */     p_85029_1_.func_71507_a("Immitating block data", Integer.valueOf(this.blockMeta));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R() {
/* 222 */     return 0.0F;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public World func_145807_e() {
/* 228 */     return this.field_70170_p;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_90999_ad() {
/* 237 */     return false;
/*     */   }
/*     */   
/*     */   public Block func_145805_f() {
/* 242 */     return this.block;
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf buffer) {
/* 247 */     buffer.writeDouble(this.exceedRange);
/* 248 */     buffer.writeDouble(this.spawnPosX);
/* 249 */     buffer.writeDouble(this.spawnPosY);
/* 250 */     buffer.writeDouble(this.spawnPosZ);
/* 251 */     buffer.writeInt(Block.func_149682_b(this.block));
/* 252 */     buffer.writeInt(this.blockMeta);
/* 253 */     buffer.writeInt(this.fallTime);
/* 254 */     buffer.writeBoolean(this.shouldDropItem);
/* 255 */     buffer.writeBoolean(this.isAnvil);
/* 256 */     buffer.writeFloat(this.fallHurtAmount);
/* 257 */     buffer.writeInt(this.fallHurtMax);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf additionalData) {
/* 262 */     this.exceedRange = additionalData.readDouble();
/* 263 */     this.spawnPosX = additionalData.readDouble();
/* 264 */     this.spawnPosY = additionalData.readDouble();
/* 265 */     this.spawnPosZ = additionalData.readDouble();
/* 266 */     this.block = Block.func_149729_e(additionalData.readInt());
/* 267 */     this.blockMeta = additionalData.readInt();
/* 268 */     this.fallTime = additionalData.readInt();
/* 269 */     this.shouldDropItem = additionalData.readBoolean();
/* 270 */     this.isAnvil = additionalData.readBoolean();
/* 271 */     this.fallHurtAmount = additionalData.readFloat();
/* 272 */     this.fallHurtMax = additionalData.readInt();
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */