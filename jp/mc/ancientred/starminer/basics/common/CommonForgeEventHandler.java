/*     */ package jp.mc.ancientred.starminer.basics.common;
/*     */ 
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.PlayerEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.basics.Config;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.SMReflectionHelper;
/*     */ import jp.mc.ancientred.starminer.basics.block.bed.BlockStarBedHead;
/*     */ import jp.mc.ancientred.starminer.basics.entity.EntityGProjectile;
/*     */ import jp.mc.ancientred.starminer.basics.packet.SMPacketSender;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.player.ArrowLooseEvent;
/*     */ import net.minecraftforge.event.entity.player.ArrowNockEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
/*     */ import net.minecraftforge.event.entity.player.UseHoeEvent;
/*     */ import net.minecraftforge.event.terraingen.PopulateChunkEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ 
/*     */ public class CommonForgeEventHandler {
/*  44 */   private Random rand = new Random();
/*     */   
/*  46 */   public DimentionTeleportHandler teleportHanlder = new DimentionTeleportHandler();
/*     */   
/*     */   @SubscribeEvent
/*     */   public void handleLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/*  50 */     if (event.entity != null && !event.entity.field_70170_p.field_72995_K && 
/*  51 */       event.entityLiving instanceof EntityPlayerMP && 
/*  52 */       this.teleportHanlder.onUpdateEntityEnd((EntityPlayerMP)event.entityLiving))
/*  54 */       event.setCanceled(true); 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public boolean handlePopulateChunkEvent(PopulateChunkEvent event) {
/*  62 */     if (Config.generateStars && 
/*  63 */       event instanceof PopulateChunkEvent.Post && 
/*  64 */       event.world.field_73011_w instanceof net.minecraft.world.WorldProviderSurface) {
/*  65 */       SMModContainer.starGenerator.func_151539_a(event.chunkProvider, event.world, event.chunkX, event.chunkZ, null);
/*  66 */       SMModContainer.starGenerator.func_75051_a(event.world, event.rand, event.chunkX, event.chunkZ);
/*     */     } 
/*  70 */     return true;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void handlePlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
/*     */     try {
/*  75 */       if (event.player instanceof EntityPlayerMP)
/*  77 */         SMPacketSender.sendSkyMapPacketToPlayer((EntityPlayerMP)event.player); 
/*  79 */     } catch (Exception ex) {
/*  80 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void handleWorldTickEvent(TickEvent.WorldTickEvent event) {
/*  85 */     if (event.phase == TickEvent.Phase.START && 
/*  86 */       event.world instanceof WorldServer) {
/*  88 */       WorldServer worldServer = (WorldServer)event.world;
/*  89 */       SMModContainer.proxy.doWakeupAll(worldServer);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void handleUseHoeEvent(UseHoeEvent event) {
/*  95 */     Block block = event.world.func_147439_a(event.x, event.y, event.z);
/*  96 */     if (block == SMModContainer.DirtGrassExBlock) {
/*  97 */       int meta = event.world.func_72805_g(event.x, event.y, event.z);
/*  98 */       if ((meta & 0x8) == 0) {
/*  99 */         event.world.func_72908_a((double)((float)event.x + 0.5F), (double)((float)event.y + 0.5F), (double)((float)event.z + 0.5F), SMModContainer.DirtGrassExBlock.field_149762_H.func_150498_e(), (SMModContainer.DirtGrassExBlock.field_149762_H.func_150497_c() + 1.0F) / 2.0F, SMModContainer.DirtGrassExBlock.field_149762_H.func_150494_d() * 0.8F);
/* 106 */         if (!event.world.field_72995_K) {
/* 107 */           event.world.func_72921_c(event.x, event.y, event.z, meta | 0x8, 2);
/* 108 */           event.setResult(Event.Result.ALLOW);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void handleWorldLoadEvent(WorldEvent.Load event) {
/* 115 */     SMModContainer.proxy.handleWorldLoadEvent(event);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void handlePlayerSleepInBed(PlayerSleepInBedEvent event) {
/* 119 */     EntityPlayer player = event.entityPlayer;
/* 120 */     int tarX = event.x;
/* 121 */     int tarY = event.y;
/* 122 */     int tarZ = event.z;
/* 124 */     Block block = player.field_70170_p.func_147439_a(tarX, tarY, tarZ);
/* 125 */     if (block != SMModContainer.StarBedHeadBlock)
/*     */       return; 
/* 129 */     EntityPlayer.EnumStatus res = sleepInBedAt(player, tarX, tarY, tarZ);
/* 130 */     event.result = res;
/*     */   }
/*     */   
/*     */   private EntityPlayer.EnumStatus sleepInBedAt(EntityPlayer player, int par1, int par2, int par3) {
/* 134 */     Block block = player.field_70170_p.func_147439_a(par1, par2, par3);
/* 136 */     int gravDir = ((BlockStarBedHead)block).getGravityDirection((IBlockAccess)player.field_70170_p, par1, par2, par3);
/* 137 */     int connDir = ((BlockStarBedHead)block).getConnectionDirection((IBlockAccess)player.field_70170_p, par1, par2, par3);
/* 139 */     if (!player.field_70170_p.field_72995_K) {
/* 141 */       Gravity gravity = Gravity.getGravityProp((Entity)player);
/* 142 */       switch (gravity.gravityDirection) {
/*     */         case northTOsouth_ZP:
/* 144 */           if (gravDir != 5)
/* 144 */             return EntityPlayer.EnumStatus.OTHER_PROBLEM; 
/*     */           break;
/*     */         case southTOnorth_ZN:
/* 147 */           if (gravDir != 4)
/* 147 */             return EntityPlayer.EnumStatus.OTHER_PROBLEM; 
/*     */           break;
/*     */         case westTOeast_XP:
/* 150 */           if (gravDir != 3)
/* 150 */             return EntityPlayer.EnumStatus.OTHER_PROBLEM; 
/*     */           break;
/*     */         case eastTOwest_XN:
/* 153 */           if (gravDir != 2)
/* 153 */             return EntityPlayer.EnumStatus.OTHER_PROBLEM; 
/*     */           break;
/*     */         case downTOup_YP:
/* 156 */           if (gravDir != 1)
/* 156 */             return EntityPlayer.EnumStatus.OTHER_PROBLEM; 
/*     */           break;
/*     */         case upTOdown_YN:
/* 159 */           if (gravDir != 0)
/* 159 */             return EntityPlayer.EnumStatus.OTHER_PROBLEM; 
/*     */           break;
/*     */       } 
/* 163 */       if (player.func_70608_bn() || !player.func_70089_S())
/* 165 */         return EntityPlayer.EnumStatus.OTHER_PROBLEM; 
/* 168 */       if (!player.field_70170_p.field_73011_w.func_76569_d())
/* 170 */         return EntityPlayer.EnumStatus.NOT_POSSIBLE_HERE; 
/* 173 */       if (player.field_70170_p.func_72935_r())
/* 175 */         return EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW; 
/* 178 */       if (Math.abs(player.field_70165_t - (double)par1) > 3.0D || Math.abs(player.field_70163_u - (double)par2) > 3.0D || Math.abs(player.field_70161_v - (double)par3) > 3.0D)
/* 180 */         return EntityPlayer.EnumStatus.TOO_FAR_AWAY; 
/* 183 */       double d0 = 8.0D;
/* 184 */       double d1 = 5.0D;
/* 185 */       List list = player.field_70170_p.func_72872_a(EntityMob.class, AxisAlignedBB.func_72330_a((double)par1 - d0, (double)par2 - d1, (double)par3 - d0, (double)par1 + d0, (double)par2 + d1, (double)par3 + d0));
/* 193 */       if (!list.isEmpty())
/* 195 */         return EntityPlayer.EnumStatus.NOT_SAFE; 
/*     */     } 
/* 199 */     if (player.func_70115_ae())
/* 201 */       player.func_70078_a(null); 
/* 204 */     SMReflectionHelper.setSize(player, 0.2F, 0.2F);
/* 205 */     player.field_70129_M = 0.2F;
/* 207 */     BlockStarBedHead.setPositionForStarBedSleepingPlayer(player, par1, par2, par3, gravDir, connDir);
/* 210 */     SMReflectionHelper.setSleeping(player);
/* 211 */     SMReflectionHelper.setSleepTimer(player, 0);
/* 213 */     player.field_71081_bT = new ChunkCoordinates(par1, par2, par3);
/* 214 */     player.field_70159_w = player.field_70179_y = player.field_70181_x = 0.0D;
/* 216 */     if (!player.field_70170_p.field_72995_K)
/* 218 */       player.field_70170_p.func_72854_c(); 
/* 221 */     return EntityPlayer.EnumStatus.OK;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void handleArrowNockEvent(ArrowNockEvent event) {
/* 225 */     EntityPlayer shooter = event.entityPlayer;
/* 226 */     if (shooter.field_71071_by.func_146028_b(SMModContainer.GArrowItem)) {
/* 228 */       shooter.func_71008_a(event.result, Items.field_151031_f.func_77626_a(event.result));
/* 229 */       event.setCanceled(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void handleArrowLooseEvent(ArrowLooseEvent event) {
/* 235 */     EntityPlayer shooter = event.entityPlayer;
/* 236 */     World wordl = shooter.field_70170_p;
/* 237 */     ItemStack itemStackBow = event.bow;
/* 238 */     int itemUseDuration = event.charge;
/* 240 */     boolean infinity = (shooter.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, itemStackBow) > 0);
/* 242 */     if (shooter.field_71071_by.func_146028_b(SMModContainer.GArrowItem)) {
/* 243 */       float f = (float)itemUseDuration / 20.0F;
/* 244 */       f = (f * f + f * 2.0F) / 3.0F;
/* 246 */       if ((double)f < 0.1D)
/*     */         return; 
/* 251 */       if (f > 1.0F)
/* 253 */         f = 1.0F; 
/* 257 */       EntityGProjectile entityGarrow = new EntityGProjectile(wordl, shooter, f * 2.0F, EntityGProjectile.GProjectileType.gArrow);
/* 259 */       if (f == 1.0F)
/* 261 */         entityGarrow.func_70243_d(true); 
/* 264 */       int k = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, itemStackBow);
/* 266 */       if (k > 0)
/* 268 */         entityGarrow.func_70239_b(entityGarrow.func_70242_d() + (double)k * 0.5D + 0.5D); 
/* 271 */       int l = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, itemStackBow);
/* 273 */       if (l > 0)
/* 275 */         entityGarrow.func_70240_a(l); 
/* 278 */       if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, itemStackBow) > 0)
/* 280 */         entityGarrow.func_70015_d(100); 
/* 283 */       itemStackBow.func_77972_a(1, (EntityLivingBase)shooter);
/* 284 */       wordl.func_72956_a((Entity)shooter, "random.bow", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/* 286 */       if (infinity) {
/* 288 */         entityGarrow.field_70251_a = 2;
/*     */       } else {
/* 292 */         shooter.field_71071_by.func_146026_a(SMModContainer.GArrowItem);
/*     */       } 
/* 295 */       if (!wordl.field_72995_K)
/* 297 */         wordl.func_72838_d((Entity)entityGarrow); 
/* 300 */       event.setCanceled(true);
/*     */     } 
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */