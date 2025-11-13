/*     */ package jp.mc.ancientred.starminer.basics.entity;
/*     */ 
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.passive.EntityAmbientCreature;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityStarSquid extends EntityAmbientCreature {
/*     */   public float squidPitch;
/*     */   
/*     */   public float prevSquidPitch;
/*     */   
/*     */   public float squidYaw;
/*     */   
/*     */   public float prevSquidYaw;
/*     */   
/*     */   public float squidRotation;
/*     */   
/*     */   public float prevSquidRotation;
/*     */   
/*     */   public float tentacleAngle;
/*     */   
/*     */   public float prevTentacleAngle;
/*     */   
/*     */   private float randomMotionSpeed;
/*     */   
/*     */   private float rotationVelocity;
/*     */   
/*     */   private float field_70871_bB;
/*     */   
/*     */   private float randomMotionVecX;
/*     */   
/*     */   private float randomMotionVecY;
/*     */   
/*     */   private float randomMotionVecZ;
/*     */   
/*  43 */   private boolean isBazookaSquid = false;
/*     */   
/*     */   public EntityStarSquid(World par1World) {
/*  46 */     super(par1World);
/*  47 */     func_70105_a(0.95F, 0.95F);
/*  48 */     this.rotationVelocity = 1.0F / (this.field_70146_Z.nextFloat() + 1.0F) * 0.2F;
/*  49 */     this.field_70178_ae = true;
/*     */   }
/*     */   
/*     */   public EntityStarSquid(World par1World, boolean isBazookaSquid) {
/*  53 */     this(par1World);
/*  54 */     this.isBazookaSquid = true;
/*     */   }
/*     */   
/*     */   protected void func_70088_a() {
/*  58 */     super.func_70088_a();
/*  59 */     this.field_70180_af.func_75682_a(16, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   protected void func_110147_ax() {
/*  62 */     super.func_110147_ax();
/*  63 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ() {
/*  70 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70621_aR() {
/*  77 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70673_aS() {
/*  84 */     return null;
/*     */   }
/*     */   
/*     */   protected float func_70599_aP() {
/*  91 */     return 0.4F;
/*     */   }
/*     */   
/*     */   protected int getDropItemId() {
/*  98 */     return 0;
/*     */   }
/*     */   
/*     */   protected boolean func_70041_e_() {
/* 106 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2) {
/* 115 */     int j = this.field_70146_Z.nextInt(3 + par2) + 1;
/* 117 */     for (int k = 0; k < j; k++)
/* 118 */       func_70099_a(new ItemStack(Items.field_151016_H, 1, 0), 0.0F); 
/*     */   }
/*     */   
/*     */   public void setToBeRidByEntityIdTemp(int entityId) {
/* 123 */     this.field_70180_af.func_75692_b(16, Integer.valueOf(entityId));
/*     */   }
/*     */   
/*     */   public void func_70636_d() {
/* 131 */     if (this.field_70170_p.field_72995_K) {
/* 132 */       int toBeRidByEntityId = this.field_70180_af.func_75679_c(16);
/* 133 */       if (toBeRidByEntityId != 0) {
/* 134 */         Entity toBeRidByEntity = this.field_70170_p.func_73045_a(toBeRidByEntityId);
/* 135 */         if (toBeRidByEntity != null)
/* 136 */           if (toBeRidByEntity.field_70154_o == this) {
/* 137 */             this.field_70180_af.func_75692_b(16, Integer.valueOf(0));
/* 138 */           } else if (Math.abs(toBeRidByEntity.field_70165_t - this.field_70165_t) < 16.0D && Math.abs(toBeRidByEntity.field_70161_v - this.field_70161_v) < 16.0D) {
/* 140 */             toBeRidByEntity.func_70078_a((Entity)this);
/*     */           }  
/*     */         return;
/*     */       } 
/*     */     } 
/* 146 */     if (this.isBazookaSquid) {
/* 147 */       if (this.field_70153_n != null) {
/* 148 */         this.field_70153_n.field_70143_R = 0.0F;
/* 149 */         if (!this.field_70170_p.field_72995_K) {
/* 151 */           Gravity gravity = Gravity.getGravityProp(this.field_70153_n);
/* 152 */           if (gravity != null)
/* 152 */             gravity.setResistInOpaqueBlockDamegeTick(20); 
/*     */         } 
/*     */       } 
/* 155 */       super.func_70636_d();
/* 157 */       if (!this.field_70170_p.field_72995_K) {
/* 158 */         if (this.field_70132_H || this.field_70153_n == null) {
/* 159 */           func_70106_y();
/*     */           return;
/*     */         } 
/* 163 */         if (!(this.field_70170_p.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider)) {
/* 164 */           this.field_70159_w *= 0.9870000190734863D;
/* 165 */           this.field_70181_x *= 0.9870000190734863D;
/* 166 */           this.field_70179_y *= 0.9870000190734863D;
/* 167 */           double totalSpeed = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/* 168 */           if (totalSpeed < 0.6D)
/* 169 */             this.field_70181_x -= 0.4D; 
/*     */         } 
/*     */       } 
/* 174 */       this.prevSquidPitch = this.squidPitch;
/* 175 */       this.prevSquidYaw = this.squidYaw;
/* 176 */       this.prevSquidRotation = this.squidRotation;
/* 177 */       this.prevTentacleAngle = this.tentacleAngle;
/* 178 */       this.squidRotation += this.rotationVelocity;
/* 180 */       if (this.squidRotation > 6.2831855F) {
/* 181 */         this.squidRotation -= 6.2831855F;
/* 183 */         if (this.field_70146_Z.nextInt(10) == 0)
/* 184 */           this.rotationVelocity = 1.0F / (this.field_70146_Z.nextFloat() + 1.0F) * 0.2F; 
/*     */       } 
/* 188 */       float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 189 */       this.field_70761_aq += (-((float)Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0F / 3.1415927F - this.field_70761_aq) * 0.1F;
/* 190 */       this.field_70177_z = this.field_70761_aq;
/* 191 */       this.squidPitch += (-((float)Math.atan2((double)f, this.field_70181_x)) * 180.0F / 3.1415927F - this.squidPitch) * 0.5F;
/*     */     } else {
/* 193 */       super.func_70636_d();
/* 194 */       this.prevSquidPitch = this.squidPitch;
/* 195 */       this.prevSquidYaw = this.squidYaw;
/* 196 */       this.prevSquidRotation = this.squidRotation;
/* 197 */       this.prevTentacleAngle = this.tentacleAngle;
/* 198 */       this.squidRotation += this.rotationVelocity;
/* 200 */       if (this.squidRotation > 6.2831855F) {
/* 202 */         this.squidRotation -= 6.2831855F;
/* 204 */         if (this.field_70146_Z.nextInt(10) == 0)
/* 206 */           this.rotationVelocity = 1.0F / (this.field_70146_Z.nextFloat() + 1.0F) * 0.2F; 
/*     */       } 
/* 212 */       if (this.squidRotation < 3.1415927F) {
/* 214 */         float f1 = this.squidRotation / 3.1415927F;
/* 215 */         this.tentacleAngle = MathHelper.func_76126_a(f1 * f1 * 3.1415927F) * 3.1415927F * 0.25F;
/* 217 */         if ((double)f1 > 0.75D) {
/* 219 */           this.randomMotionSpeed = 1.0F;
/* 220 */           this.field_70871_bB = 1.0F;
/*     */         } else {
/* 224 */           this.field_70871_bB *= 0.8F;
/*     */         } 
/*     */       } else {
/* 229 */         this.tentacleAngle = 0.0F;
/* 230 */         this.randomMotionSpeed *= 0.9F;
/* 231 */         this.field_70871_bB *= 0.99F;
/*     */       } 
/* 234 */       if (!this.field_70170_p.field_72995_K) {
/* 236 */         this.field_70159_w = (double)(this.randomMotionVecX * this.randomMotionSpeed);
/* 237 */         this.field_70181_x = (double)(this.randomMotionVecY * this.randomMotionSpeed);
/* 238 */         this.field_70179_y = (double)(this.randomMotionVecZ * this.randomMotionSpeed);
/*     */       } 
/* 241 */       float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 242 */       this.field_70761_aq += (-((float)Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0F / 3.1415927F - this.field_70761_aq) * 0.1F;
/* 243 */       this.field_70177_z = this.field_70761_aq;
/* 244 */       this.squidYaw += 3.1415927F * this.field_70871_bB * 1.5F;
/* 245 */       this.squidPitch += (-((float)Math.atan2((double)f, this.field_70181_x)) * 180.0F / 3.1415927F - this.squidPitch) * 0.1F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_70645_a(DamageSource par1DamageSource) {
/* 250 */     if (this.field_70153_n != null)
/* 251 */       this.field_70153_n.field_70143_R = 0.0F; 
/* 253 */     super.func_70645_a(par1DamageSource);
/*     */   }
/*     */   
/*     */   protected void func_70069_a(float par1) {}
/*     */   
/*     */   public void func_70612_e(float par1, float par2) {
/* 263 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */   }
/*     */   
/*     */   protected void func_70626_be() {
/* 267 */     if (this.isBazookaSquid) {
/* 268 */       super.func_70626_be();
/*     */     } else {
/* 270 */       this.field_70708_bq++;
/* 272 */       if (this.field_70708_bq > 100) {
/* 274 */         this.randomMotionVecX = this.randomMotionVecY = this.randomMotionVecZ = 0.0F;
/* 276 */       } else if (this.field_70146_Z.nextInt(50) == 0 || !this.field_70171_ac || (this.randomMotionVecX == 0.0F && this.randomMotionVecY == 0.0F && this.randomMotionVecZ == 0.0F)) {
/* 278 */         float f = this.field_70146_Z.nextFloat() * 3.1415927F * 2.0F;
/* 279 */         this.randomMotionVecX = MathHelper.func_76134_b(f) * 0.2F;
/* 280 */         this.randomMotionVecY = -0.1F + this.field_70146_Z.nextFloat() * 0.2F;
/* 281 */         this.randomMotionVecZ = MathHelper.func_76126_a(f) * 0.2F;
/*     */       } 
/* 284 */       func_70623_bb();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean func_70601_bi() {
/* 292 */     if (this.isBazookaSquid)
/* 292 */       return true; 
/* 293 */     return this.field_70170_p.func_72855_b(this.field_70121_D);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */