/*     */ package jp.mc.ancientred.starminer.core.obfuscar;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import jp.mc.ancientred.starminer.core.SMPlugin;
/*     */ 
/*     */ public class SMCoreObfuscaHelper {
/*     */   static class Elem {
/*     */     public String name;
/*     */     
/*     */     public String rawName;
/*     */     
/*     */     public String seargeName;
/*     */     
/*     */     public String desc;
/*     */     
/*     */     public String rawDesc;
/*     */     
/*     */     public Elem(String name, String rawName, String seargeName, String desc, String rawDesc) {
/*  11 */       this.name = name;
/*  12 */       this.rawName = rawName;
/*  13 */       this.seargeName = seargeName;
/*  14 */       this.desc = desc;
/*  15 */       this.rawDesc = rawDesc;
/*     */     }
/*     */     
/*     */     public Elem(String name, String rawName, String seargeName, String desc) {
/*  18 */       this.name = name;
/*  19 */       this.rawName = rawName;
/*  20 */       this.seargeName = seargeName;
/*  21 */       this.desc = desc;
/*  22 */       this.rawDesc = desc;
/*     */     }
/*     */     
/*     */     public Elem(String name, String rawName) {
/*  25 */       this.name = name;
/*  26 */       this.rawName = rawName;
/*     */     }
/*     */   }
/*     */   
/*  36 */   public static Map<String, Elem> descMap1710 = new HashMap<String, Elem>();
/*     */   
/*     */   public static boolean isVersion164;
/*     */   
/*     */   public static boolean isVersion17210;
/*     */   
/*     */   public static void putMapping1710(Elem elem) {
/*  70 */     descMap1710.put(elem.name, elem);
/*     */   }
/*     */   
/*     */   public static String getProperName(String name) {
/*  73 */     Map<String, Elem> descMap = descMap1710;
/*  74 */     if (!SMPlugin.RUNTIME_DEOBF)
/*  75 */       return ((Elem)descMap.get(name)).name; 
/*  77 */     return ((Elem)descMap.get(name)).rawName;
/*     */   }
/*     */   
/*     */   public static String getProperDesc(String name) {
/*  81 */     Map<String, Elem> descMap = descMap1710;
/*  82 */     if (!SMPlugin.RUNTIME_DEOBF)
/*  83 */       return ((Elem)descMap.get(name)).desc; 
/*  85 */     return ((Elem)descMap.get(name)).rawDesc;
/*     */   }
/*     */   
/*     */   public static String getSrgName(String name) {
/*  89 */     Map<String, Elem> descMap = descMap1710;
/*  90 */     if (!SMPlugin.RUNTIME_DEOBF)
/*  91 */       return ((Elem)descMap.get(name)).name; 
/*  93 */     return ((Elem)descMap.get(name)).seargeName;
/*     */   }
/*     */   
/*     */   static {
/* 101 */     putMapping1710(new Elem("getIconIndex", "j", "func_77650_f", "(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/util/IIcon;", "(Ladd;)Lrf;"));
/* 103 */     putMapping1710(new Elem("getMovingObjectPositionFromPlayer", "a", "func_77621_a", "(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;Z)Lnet/minecraft/util/MovingObjectPosition;", "(Lahb;Lyz;Z)Lazu;"));
/* 110 */     putMapping1710(new Elem("net/minecraft/entity/Entity", "sa"));
/* 112 */     putMapping1710(new Elem("isCollidedHorizontally", "E", "field_70123_F", "Z"));
/* 114 */     putMapping1710(new Elem("motionY", "w", "field_70181_x", "D"));
/* 116 */     putMapping1710(new Elem("onEntityUpdate", "C", "func_70030_z", "()V"));
/* 119 */     putMapping1710(new Elem("onUpdate", "h", "func_70071_h_", "()V"));
/* 122 */     putMapping1710(new Elem("net/minecraft/entity/EntityLivingBase", "sv"));
/* 125 */     putMapping1710(new Elem("net/minecraft/entity/player/EntityPlayer", "yz"));
/* 127 */     putMapping1710(new Elem("moveEntityWithHeading", "e", "func_70612_e", "(FF)V"));
/* 129 */     putMapping1710(new Elem("isPlayerSleeping", "bm", "func_70608_bn", "()Z"));
/* 131 */     putMapping1710(new Elem("getPosition", "l", "func_70666_h", "(F)Lnet/minecraft/util/Vec3;", "(F)Lazw;"));
/* 134 */     putMapping1710(new Elem("net/minecraft/client/entity/EntityPlayerSP", "blk"));
/* 136 */     putMapping1710(new Elem("onLivingUpdate", "e", "func_70636_d", "()V"));
/* 138 */     putMapping1710(new Elem("isRidingHorse", "u", "func_110317_t", "Z"));
/* 141 */     putMapping1710(new Elem("orientCamera", "h", "func_78467_g", "(F)V"));
/* 143 */     putMapping1710(new Elem("updateFogColor", "j", "func_78466_h", "(F)V"));
/* 145 */     putMapping1710(new Elem("mc", "t", "field_78531_r", null));
/* 148 */     putMapping1710(new Elem("gameSettings", "u", "field_71474_y", null));
/* 151 */     putMapping1710(new Elem("net/minecraft/client/settings/GameSettings", "bbj"));
/* 153 */     putMapping1710(new Elem("debugCamEnable", "aC", "field_74325_U", "Z"));
/* 155 */     putMapping1710(new Elem("thirdPersonView", "aw", "field_74320_O", null));
/* 158 */     putMapping1710(new Elem("net/minecraft/client/renderer/entity/RendererLivingEntity", "boh"));
/* 160 */     putMapping1710(new Elem("rotateCorpse", "a", "func_77043_a", "(Lnet/minecraft/entity/EntityLivingBase;FFF)V", "(Lsv;FFF)V"));
/* 162 */     putMapping1710(new Elem("doRender", "a", "func_76986_a", "(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V", "(Lsv;DDDFF)V"));
/* 166 */     putMapping1710(new Elem("processPlayer", "a", "func_147347_a", "(Lnet/minecraft/network/play/client/C03PacketPlayer;)V", "(Ljd;)V"));
/* 168 */     putMapping1710(new Elem("kickPlayerFromServer", "c", "func_147360_c", "(Ljava/lang/String;)V"));
/* 171 */     putMapping1710(new Elem("readPacketData", "a", "func_148837_a", "(Lnet/minecraft/network/PacketBuffer;)V", "(Let;)V"));
/* 173 */     putMapping1710(new Elem("writePacketData", "b", "func_148840_b", "(Lnet/minecraft/network/PacketBuffer;)V", "(Let;)V"));
/* 175 */     putMapping1710(new Elem("readInt", "readInt", "readInt", "()I", "()I"));
/* 177 */     putMapping1710(new Elem("readByte", "readByte", "readByte", "()B", "()B"));
/* 179 */     putMapping1710(new Elem("writeByte", "writeByte", "writeByte", "(I)Lio/netty/buffer/ByteBuf;", "(I)Lio/netty/buffer/ByteBuf;"));
/* 181 */     putMapping1710(new Elem("writeInt", "writeInt", "writeInt", "(I)Lio/netty/buffer/ByteBuf;", "(I)Lio/netty/buffer/ByteBuf;"));
/* 184 */     putMapping1710(new Elem("onBlockAdded", "b", "func_149726_b", "(Lnet/minecraft/world/World;III)V", "(Lahb;III)V"));
/* 187 */     putMapping1710(new Elem("instance", "a", "field_78398_a", null));
/* 211 */     putMapping1710(new Elem("blockBlocksFlow", "n", "func_72208_o", "(Lnet/minecraft/world/World;III)Z", "(Labw;III)Z"));
/* 212 */     putMapping1710(new Elem("liquidCanDisplaceBlock", "o", "func_72207_p", "(Lnet/minecraft/world/World;III)Z", "(Labw;III)Z"));
/* 213 */     putMapping1710(new Elem("getRenderType", "d", "func_71857_b", "()I"));
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */