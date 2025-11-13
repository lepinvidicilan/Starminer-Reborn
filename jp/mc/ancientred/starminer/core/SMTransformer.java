/*     */ package jp.mc.ancientred.starminer.core;
/*     */ 
/*     */ import jp.mc.ancientred.starminer.core.obfuscar.SMCoreObfuscaHelper;
/*     */ import net.minecraft.launchwrapper.IClassTransformer;
/*     */ import net.minecraft.launchwrapper.LogWrapper;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.objectweb.asm.ClassReader;
/*     */ import org.objectweb.asm.ClassWriter;
/*     */ import org.objectweb.asm.tree.ClassNode;
/*     */ import org.objectweb.asm.tree.FieldInsnNode;
/*     */ import org.objectweb.asm.tree.FieldNode;
/*     */ import org.objectweb.asm.tree.InsnList;
/*     */ import org.objectweb.asm.tree.InsnNode;
/*     */ import org.objectweb.asm.tree.JumpInsnNode;
/*     */ import org.objectweb.asm.tree.LabelNode;
/*     */ import org.objectweb.asm.tree.LdcInsnNode;
/*     */ import org.objectweb.asm.tree.MethodInsnNode;
/*     */ import org.objectweb.asm.tree.MethodNode;
/*     */ import org.objectweb.asm.tree.VarInsnNode;
/*     */ 
/*     */ public class SMTransformer implements IClassTransformer {
/*  34 */   private boolean hasInit = false;
/*     */   
/*  35 */   private boolean hasTweakerLoaded = false;
/*     */   
/*     */   public static final String ROTATECORPSEPUBLIC = "rotateCorpsePublic";
/*     */   
/*     */   public byte[] transform(String name, String transformedName, byte[] bytes) {
/*  50 */     if (!transformedName.startsWith("net.minecraft"))
/*  50 */       return bytes; 
/*  53 */     if (!this.hasInit) {
/*  54 */       checkTweakerLoad();
/*  55 */       this.hasInit = true;
/*     */     } 
/*  59 */     if (this.hasTweakerLoaded)
/*  59 */       return bytes; 
/*  71 */     if (transformedName.equals("net.minecraft.world.World")) {
/*  72 */       ClassReader cr = new ClassReader(bytes);
/*  73 */       ClassNode classNode = new ClassNode();
/*  74 */       cr.accept(classNode, 0);
/*  75 */       String lablelAsTarget = "World.<init>";
/*     */       try {
/*  77 */         String targetMethodName = "<init>";
/*  80 */         for (MethodNode curMnode : classNode.methods) {
/*  81 */           if (targetMethodName.equals(curMnode.name) && curMnode.access == 1)
/*  83 */             for (int i = 0; i < curMnode.instructions.size(); i++) {
/*  84 */               if (curMnode.instructions.get(i).getType() == 5 && ((MethodInsnNode)curMnode.instructions.get(i)).name.equals("<init>") && ((MethodInsnNode)curMnode.instructions.get(i)).owner.equals("java/lang/Object")) {
/*  87 */                 LabelNode labelJumpReturn = new LabelNode();
/*  89 */                 InsnList overrideList = new InsnList();
/*  90 */                 overrideList.add(new VarInsnNode(25, 1));
/*  91 */                 overrideList.add(new JumpInsnNode(199, labelJumpReturn));
/*  92 */                 overrideList.add(new InsnNode(177));
/*  93 */                 overrideList.add(labelJumpReturn);
/*  95 */                 curMnode.instructions.insert(curMnode.instructions.get(i + 1), overrideList);
/*  97 */                 LogSucceed(lablelAsTarget);
/*     */                 break;
/*     */               } 
/*     */             }  
/*     */         } 
/* 103 */       } catch (Exception e) {
/* 104 */         e.printStackTrace();
/* 105 */         return bytes;
/*     */       } 
/* 108 */       ClassWriter cw = new ClassWriter(1);
/* 109 */       classNode.accept(cw);
/* 110 */       bytes = cw.toByteArray();
/*     */     } 
/* 117 */     if (transformedName.equals("net.minecraft.client.renderer.Tessellator")) {
/* 118 */       ClassReader cr = new ClassReader(bytes);
/* 119 */       ClassNode classNode = new ClassNode();
/* 120 */       cr.accept(classNode, 0);
/* 121 */       String lablelAsTarget = "Tessellator.instance";
/*     */       try {
/* 123 */         String targetMethodName = SMCoreObfuscaHelper.getProperName("instance");
/* 125 */         for (FieldNode curFnode : classNode.fields) {
/* 126 */           if (targetMethodName.equals(curFnode.name))
/* 127 */             curFnode.access &= 0xFFFFFFEF; 
/*     */         } 
/* 131 */         LogWrapper.log(Level.INFO, "[Starminer]Removed \"Final\" from Tessellator.instance.", new Object[0]);
/* 132 */       } catch (Exception e) {
/* 133 */         e.printStackTrace();
/* 134 */         return bytes;
/*     */       } 
/* 137 */       ClassWriter cw = new ClassWriter(1);
/* 138 */       classNode.accept(cw);
/* 139 */       bytes = cw.toByteArray();
/*     */     } 
/* 144 */     if (transformedName.equals("net.minecraft.item.Item")) {
/* 145 */       ClassReader cr = new ClassReader(bytes);
/* 146 */       ClassNode classNode = new ClassNode();
/* 147 */       cr.accept(classNode, 0);
/*     */       try {
/* 161 */         String lablelAsTarget = "Item.getMovingObjectPositionFromPlayer";
/* 162 */         String targetMethodName = SMCoreObfuscaHelper.getProperName("getMovingObjectPositionFromPlayer");
/* 163 */         String targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("getMovingObjectPositionFromPlayer");
/* 166 */         for (MethodNode curMnode : classNode.methods) {
/* 167 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 168 */             LogFound(lablelAsTarget);
/* 171 */             InsnList overrideList = new InsnList();
/* 172 */             overrideList.add(new VarInsnNode(25, 1));
/* 173 */             overrideList.add(new VarInsnNode(25, 2));
/* 174 */             overrideList.add(new VarInsnNode(21, 3));
/* 175 */             overrideList.add(new MethodInsnNode(184, "jp/mc/ancientred/starminer/core/TransformServerHelper", "getMovingObjectPositionFromPlayerByGravity", "(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;Z)Lnet/minecraft/util/MovingObjectPosition;"));
/* 179 */             overrideList.add(new InsnNode(176));
/* 181 */             curMnode.instructions.insert(curMnode.instructions.get(1), overrideList);
/* 182 */             LogSucceed(lablelAsTarget);
/*     */             break;
/*     */           } 
/*     */         } 
/* 186 */       } catch (Exception e) {
/* 187 */         e.printStackTrace();
/* 188 */         return bytes;
/*     */       } 
/* 191 */       ClassWriter cw = new ClassWriter(1);
/* 192 */       classNode.accept(cw);
/* 193 */       bytes = cw.toByteArray();
/*     */     } 
/* 198 */     if (transformedName.equals("net.minecraft.block.BlockLiquid")) {
/* 199 */       ClassReader cr = new ClassReader(bytes);
/* 200 */       ClassNode classNode = new ClassNode();
/* 201 */       cr.accept(classNode, 0);
/* 202 */       String lablelAsTarget = "BlockLiquid.onBlockAdded";
/*     */       try {
/* 204 */         String targetMethodName = SMCoreObfuscaHelper.getProperName("onBlockAdded");
/* 205 */         String targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("onBlockAdded");
/* 208 */         for (MethodNode curMnode : classNode.methods) {
/* 209 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 210 */             LogFound(lablelAsTarget);
/* 213 */             InsnList overrideList = new InsnList();
/* 214 */             overrideList.add(new VarInsnNode(25, 0));
/* 215 */             overrideList.add(new VarInsnNode(25, 1));
/* 216 */             overrideList.add(new VarInsnNode(21, 2));
/* 217 */             overrideList.add(new VarInsnNode(21, 3));
/* 218 */             overrideList.add(new VarInsnNode(21, 4));
/* 219 */             overrideList.add(new MethodInsnNode(184, "jp/mc/ancientred/starminer/core/TransformServerHelper", "blockLiquidOnBlockAddedHook", "(Lnet/minecraft/block/Block;Lnet/minecraft/world/World;III)Z"));
/* 223 */             LabelNode labelJumpReturn = new LabelNode();
/* 224 */             overrideList.add(new JumpInsnNode(153, labelJumpReturn));
/* 225 */             overrideList.add(new InsnNode(177));
/* 226 */             overrideList.add(labelJumpReturn);
/* 228 */             curMnode.instructions.insert(curMnode.instructions.get(1), overrideList);
/* 229 */             LogSucceed(lablelAsTarget);
/*     */             break;
/*     */           } 
/*     */         } 
/* 233 */       } catch (Exception e) {
/* 234 */         e.printStackTrace();
/* 235 */         return bytes;
/*     */       } 
/* 238 */       ClassWriter cw = new ClassWriter(1);
/* 239 */       classNode.accept(cw);
/* 240 */       bytes = cw.toByteArray();
/*     */     } 
/* 245 */     if (transformedName.equals("net.minecraft.entity.Entity")) {
/* 246 */       ClassReader cr = new ClassReader(bytes);
/* 247 */       ClassNode classNode = new ClassNode();
/* 248 */       cr.accept(classNode, 0);
/*     */       try {
/* 251 */         String lablelAsTarget = "Entity.onEntityUpdate";
/* 252 */         String targetMethodName = SMCoreObfuscaHelper.getProperName("onEntityUpdate");
/* 253 */         String targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("onEntityUpdate");
/* 256 */         for (MethodNode curMnode : classNode.methods) {
/* 257 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 258 */             LogFound(lablelAsTarget);
/* 259 */             Double cstCheck = new Double("-64.0");
/* 261 */             for (int i = curMnode.instructions.size() - 1; i >= 0; i--) {
/* 262 */               if (curMnode.instructions.get(i).getType() == 9 && ((LdcInsnNode)curMnode.instructions.get(i)).cst.equals(cstCheck)) {
/* 266 */                 ((LdcInsnNode)curMnode.instructions.get(i)).cst = new Double("-512D");
/* 268 */                 LogSucceed(lablelAsTarget);
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 274 */       } catch (Exception e) {
/* 275 */         e.printStackTrace();
/* 276 */         return bytes;
/*     */       } 
/* 279 */       ClassWriter cw = new ClassWriter(1);
/* 280 */       classNode.accept(cw);
/* 281 */       bytes = cw.toByteArray();
/*     */     } 
/* 286 */     if (transformedName.equals("net.minecraft.entity.item.EntityItem")) {
/* 287 */       ClassReader cr = new ClassReader(bytes);
/* 288 */       ClassNode classNode = new ClassNode();
/* 289 */       cr.accept(classNode, 0);
/* 290 */       String lablelAsTarget = "EntityItem.onUpdate";
/*     */       try {
/* 292 */         String targetMethodName = SMCoreObfuscaHelper.getProperName("onUpdate");
/* 293 */         String targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("onUpdate");
/* 296 */         for (MethodNode curMnode : classNode.methods) {
/* 297 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 298 */             LogFound(lablelAsTarget);
/* 300 */             Double cstCheck = new Double("0.03999999910593033");
/* 302 */             for (int i = curMnode.instructions.size() - 1; i >= 0; i--) {
/* 303 */               if (curMnode.instructions.get(i).getType() == 9 && ((LdcInsnNode)curMnode.instructions.get(i)).cst.equals(cstCheck)) {
/* 307 */                 InsnList overrideList = new InsnList();
/* 308 */                 overrideList.add(new VarInsnNode(25, 0));
/* 309 */                 overrideList.add(new MethodInsnNode(184, "jp/mc/ancientred/starminer/core/TransformServerHelper", "pullGravityYInGravity", "(Lnet/minecraft/entity/Entity;)D"));
/* 314 */                 curMnode.instructions.remove(curMnode.instructions.get(i));
/* 315 */                 curMnode.instructions.insert(curMnode.instructions.get(i - 1), overrideList);
/* 316 */                 LogSucceed(lablelAsTarget);
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 322 */       } catch (Exception e) {
/* 323 */         e.printStackTrace();
/* 324 */         LogFailed(lablelAsTarget);
/* 325 */         return bytes;
/*     */       } 
/* 328 */       ClassWriter cw = new ClassWriter(1);
/* 329 */       classNode.accept(cw);
/* 330 */       bytes = cw.toByteArray();
/*     */     } 
/* 335 */     if (transformedName.equals("net.minecraft.entity.player.EntityPlayer")) {
/* 336 */       ClassReader cr = new ClassReader(bytes);
/* 337 */       ClassNode classNode = new ClassNode();
/* 338 */       cr.accept(classNode, 0);
/* 340 */       String lablelAsTarget = "EntityPlayer";
/*     */       try {
/* 342 */         for (MethodNode curMnode : classNode.methods) {
/* 343 */           lablelAsTarget = "EntityPlayer.moveEntityWithHeading";
/* 344 */           String targetMethodName = SMCoreObfuscaHelper.getProperName("moveEntityWithHeading");
/* 345 */           String targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("moveEntityWithHeading");
/* 346 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 347 */             LogFound(lablelAsTarget);
/* 349 */             for (int i = curMnode.instructions.size() - 1; i >= 0; i--) {
/* 350 */               if (curMnode.instructions.get(i).getType() == 4 && ((FieldInsnNode)curMnode.instructions.get(i)).getOpcode() == 181 && ((FieldInsnNode)curMnode.instructions.get(i)).owner.equals(SMCoreObfuscaHelper.getProperName("net/minecraft/entity/player/EntityPlayer")) && ((FieldInsnNode)curMnode.instructions.get(i)).name.equals(SMCoreObfuscaHelper.getProperName("motionY")) && curMnode.instructions.get(i - 1).getOpcode() == 107 && curMnode.instructions.get(i - 2).getType() == 9 && curMnode.instructions.get(i - 3).getOpcode() == 24 && curMnode.instructions.get(i - 4).getOpcode() == 25) {
/* 359 */                 curMnode.instructions.remove(curMnode.instructions.get(i));
/* 360 */                 curMnode.instructions.remove(curMnode.instructions.get(i - 1));
/* 361 */                 curMnode.instructions.remove(curMnode.instructions.get(i - 2));
/* 362 */                 curMnode.instructions.remove(curMnode.instructions.get(i - 3));
/* 363 */                 curMnode.instructions.remove(curMnode.instructions.get(i - 4));
/* 365 */                 LogSucceed(lablelAsTarget);
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/* 371 */           lablelAsTarget = "EntityPlayer.getPosition";
/* 372 */           targetMethodName = SMCoreObfuscaHelper.getProperName("getPosition");
/* 373 */           targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("getPosition");
/* 374 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 375 */             LogFound(lablelAsTarget);
/* 378 */             InsnList overrideList = new InsnList();
/* 379 */             overrideList.add(new VarInsnNode(25, 0));
/* 380 */             overrideList.add(new VarInsnNode(23, 1));
/* 381 */             overrideList.add(new MethodInsnNode(184, "jp/mc/ancientred/starminer/core/TransformClientHelper", "getPositionForgeHook", "(Lnet/minecraft/entity/player/EntityPlayer;F)Lnet/minecraft/util/Vec3;"));
/* 385 */             overrideList.add(new InsnNode(176));
/* 387 */             curMnode.instructions.insert(curMnode.instructions.get(1), overrideList);
/* 388 */             LogSucceed(lablelAsTarget);
/*     */             break;
/*     */           } 
/*     */         } 
/* 393 */       } catch (Exception e) {
/* 394 */         e.printStackTrace();
/* 395 */         LogFailed(lablelAsTarget);
/* 396 */         return bytes;
/*     */       } 
/* 399 */       ClassWriter cw = new ClassWriter(1);
/* 400 */       classNode.accept(cw);
/* 401 */       bytes = cw.toByteArray();
/*     */     } 
/* 406 */     if (transformedName.equals("net.minecraft.client.entity.EntityPlayerSP")) {
/* 407 */       ClassReader cr = new ClassReader(bytes);
/* 408 */       ClassNode classNode = new ClassNode();
/* 409 */       cr.accept(classNode, 0);
/* 410 */       String lablelAsTarget = "EntityPlayerSP.onLivingUpdate";
/*     */       try {
/* 412 */         String targetMethodName = SMCoreObfuscaHelper.getProperName("onLivingUpdate");
/* 413 */         String targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("onLivingUpdate");
/* 416 */         for (MethodNode curMnode : classNode.methods) {
/* 417 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 418 */             LogFound(lablelAsTarget);
/* 421 */             for (int j = curMnode.instructions.size() - 1; j >= 0; j--) {
/* 422 */               if (curMnode.instructions.get(j).getOpcode() == 25 && curMnode.instructions.get(j + 1).getType() == 4 && ((FieldInsnNode)curMnode.instructions.get(j + 1)).getOpcode() == 180 && ((FieldInsnNode)curMnode.instructions.get(j + 1)).owner.equals(SMCoreObfuscaHelper.getProperName("net/minecraft/client/entity/EntityPlayerSP")) && ((FieldInsnNode)curMnode.instructions.get(j + 1)).name.equals(SMCoreObfuscaHelper.getProperName("isCollidedHorizontally")) && curMnode.instructions.get(j + 2).getType() == 7 && ((JumpInsnNode)curMnode.instructions.get(j + 2)).getOpcode() == 154) {
/* 430 */                 curMnode.instructions.remove(curMnode.instructions.get(j + 2));
/* 431 */                 curMnode.instructions.remove(curMnode.instructions.get(j + 1));
/* 432 */                 curMnode.instructions.remove(curMnode.instructions.get(j));
/* 434 */                 LogSucceed(lablelAsTarget + "(A)");
/*     */                 break;
/*     */               } 
/*     */             } 
/* 440 */             for (int i = curMnode.instructions.size() - 1; i >= 0; i--) {
/* 441 */               if (curMnode.instructions.get(i).getOpcode() == 25 && curMnode.instructions.get(i + 1).getType() == 5 && ((MethodInsnNode)curMnode.instructions.get(i + 1)).getOpcode() == 182 && ((MethodInsnNode)curMnode.instructions.get(i + 1)).owner.equals(SMCoreObfuscaHelper.getProperName("net/minecraft/client/entity/EntityPlayerSP")) && ((MethodInsnNode)curMnode.instructions.get(i + 1)).name.equals(SMCoreObfuscaHelper.getProperName("isRidingHorse")) && ((MethodInsnNode)curMnode.instructions.get(i + 1)).desc.equals("()Z")) {
/* 448 */                 InsnList overrideList = new InsnList();
/* 449 */                 overrideList.add(new VarInsnNode(25, 0));
/* 450 */                 overrideList.add(new MethodInsnNode(184, "jp/mc/ancientred/starminer/core/TransformClientHelper", "setFlyMovementByGravity", "(Lnet/minecraft/entity/player/EntityPlayer;)V"));
/* 455 */                 curMnode.instructions.insert(curMnode.instructions.get(i - 1), overrideList);
/* 456 */                 LogSucceed(lablelAsTarget + "(B)");
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 462 */       } catch (Exception e) {
/* 463 */         e.printStackTrace();
/* 464 */         LogFailed(lablelAsTarget);
/* 465 */         return bytes;
/*     */       } 
/* 469 */       ClassWriter cw = new ClassWriter(1);
/* 470 */       classNode.accept(cw);
/* 471 */       bytes = cw.toByteArray();
/*     */     } 
/* 476 */     if (transformedName.equals("net.minecraft.client.renderer.EntityRenderer")) {
/* 477 */       ClassReader cr = new ClassReader(bytes);
/* 478 */       ClassNode classNode = new ClassNode();
/* 479 */       cr.accept(classNode, 0);
/* 480 */       String lablelAsTarget = "EntityRenderer.orientCamera";
/*     */       try {
/* 482 */         String targetMethodName = SMCoreObfuscaHelper.getProperName("orientCamera");
/* 483 */         String targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("orientCamera");
/* 486 */         for (MethodNode curMnode : classNode.methods) {
/* 487 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 488 */             LogFound(lablelAsTarget);
/* 489 */             for (int i = curMnode.instructions.size() - 1; i >= 0; i--) {
/* 490 */               if (curMnode.instructions.get(i).getType() == 7 && curMnode.instructions.get(i - 1).getType() == 4 && ((FieldInsnNode)curMnode.instructions.get(i - 1)).name.equals(SMCoreObfuscaHelper.getProperName("debugCamEnable")) && curMnode.instructions.get(i - 2).getType() == 4 && ((FieldInsnNode)curMnode.instructions.get(i - 2)).name.equals(SMCoreObfuscaHelper.getProperName("gameSettings")) && curMnode.instructions.get(i - 3).getType() == 4 && ((FieldInsnNode)curMnode.instructions.get(i - 3)).name.equals(SMCoreObfuscaHelper.getProperName("mc"))) {
/* 498 */                 InsnList overrideList = new InsnList();
/* 499 */                 overrideList.add(new VarInsnNode(23, 1));
/* 500 */                 overrideList.add(new MethodInsnNode(184, "jp/mc/ancientred/starminer/core/TransformClientHelper", "orientCameraByGravity", "(F)V"));
/* 504 */                 overrideList.add(new InsnNode(4));
/* 506 */                 curMnode.instructions.insert(curMnode.instructions.get(i - 1), overrideList);
/* 507 */                 LogSucceed(lablelAsTarget + "(A)");
/*     */                 break;
/*     */               } 
/*     */             } 
/* 512 */             boolean firstJumpFound = false;
/* 513 */             LabelNode labelJump = null;
/* 514 */             for (int k = 0; k < curMnode.instructions.size(); k++) {
/* 515 */               if (curMnode.instructions.get(k).getType() == 4 && ((FieldInsnNode)curMnode.instructions.get(k)).name.equals(SMCoreObfuscaHelper.getProperName("thirdPersonView")) && ((FieldInsnNode)curMnode.instructions.get(k)).owner.equals(SMCoreObfuscaHelper.getProperName("net/minecraft/client/settings/GameSettings")) && curMnode.instructions.get(k + 1).getType() == 7 && ((JumpInsnNode)curMnode.instructions.get(k + 1)).getOpcode() == 158) {
/* 520 */                 firstJumpFound = true;
/* 521 */                 labelJump = ((JumpInsnNode)curMnode.instructions.get(k + 1)).label;
/* 522 */                 LogSucceed(lablelAsTarget + "(B_1)");
/* 525 */               } else if (firstJumpFound && curMnode.instructions.get(k).getOpcode() == 106 && curMnode.instructions.get(k + 1).getOpcode() == 98 && curMnode.instructions.get(k + 2).getOpcode() == 141 && curMnode.instructions.get(k + 3).getOpcode() == 57 && curMnode.instructions.get(k + 3).getType() == 2) {
/* 531 */                 int d3VarNum = ((VarInsnNode)curMnode.instructions.get(k + 3)).var;
/* 534 */                 InsnList overrideList = new InsnList();
/* 535 */                 overrideList.add(new VarInsnNode(24, d3VarNum));
/* 536 */                 overrideList.add(new VarInsnNode(23, 1));
/* 537 */                 overrideList.add(new MethodInsnNode(184, "jp/mc/ancientred/starminer/core/TransformClientHelper", "roatate3rdPersonViewByGravity", "(DF)Z"));
/* 541 */                 overrideList.add(new JumpInsnNode(154, labelJump));
/* 543 */                 curMnode.instructions.insert(curMnode.instructions.get(k + 3), overrideList);
/* 545 */                 LogSucceed(lablelAsTarget + "(B_2)");
/*     */                 break;
/*     */               } 
/*     */             } 
/* 550 */             for (int j = 0; j < curMnode.instructions.size(); j++) {
/* 551 */               if (curMnode.instructions.get(j).getOpcode() == 25 && curMnode.instructions.get(j + 1).getType() == 5 && ((MethodInsnNode)curMnode.instructions.get(j + 1)).getOpcode() == 182 && ((MethodInsnNode)curMnode.instructions.get(j + 1)).name.equals(SMCoreObfuscaHelper.getProperName("isPlayerSleeping")) && ((MethodInsnNode)curMnode.instructions.get(j + 1)).desc.equals(SMCoreObfuscaHelper.getProperDesc("isPlayerSleeping")) && curMnode.instructions.get(j + 2).getType() == 7 && curMnode.instructions.get(j + 2).getOpcode() == 153 && curMnode.instructions.get(j + 3).getType() == 8) {
/* 560 */                 labelJump = new LabelNode();
/* 561 */                 InsnList overrideList = new InsnList();
/* 562 */                 overrideList.add(new VarInsnNode(23, 1));
/* 563 */                 overrideList.add(new MethodInsnNode(184, "jp/mc/ancientred/starminer/core/TransformClientHelper", "rotateSleepingViewByGravity", "(F)Z"));
/* 567 */                 overrideList.add(new JumpInsnNode(154, labelJump));
/* 568 */                 overrideList.add(new InsnNode(177));
/* 569 */                 overrideList.add(labelJump);
/* 571 */                 curMnode.instructions.insert(curMnode.instructions.get(j + 3), overrideList);
/* 573 */                 LogSucceed(lablelAsTarget + "(B_3)");
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 579 */         lablelAsTarget = "EntityRenderer.updateFogColor";
/* 580 */         targetMethodName = SMCoreObfuscaHelper.getProperName("updateFogColor");
/* 581 */         targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("updateFogColor");
/* 583 */         for (MethodNode curMnode : classNode.methods) {
/* 584 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 585 */             LogFound(lablelAsTarget);
/* 587 */             for (int i = curMnode.instructions.size() - 1; i >= 0; i--) {
/* 588 */               if (curMnode.instructions.get(i).getType() == 2 && curMnode.instructions.get(i).getOpcode() == 24 && curMnode.instructions.get(i + 1).getType() == 2 && curMnode.instructions.get(i + 1).getOpcode() == 24 && ((VarInsnNode)curMnode.instructions.get(i)).var == ((VarInsnNode)curMnode.instructions.get(i + 1)).var && curMnode.instructions.get(i + 2).getOpcode() == 107 && curMnode.instructions.get(i + 3).getOpcode() == 57) {
/* 595 */                 for (int j = i - 1; j >= 0; j--) {
/* 596 */                   if (curMnode.instructions.get(j).getOpcode() == 15 && curMnode.instructions.get(j + 1).getOpcode() == 152) {
/* 598 */                     InsnList overrideList = new InsnList();
/* 599 */                     overrideList.add(new LdcInsnNode(new Double("-999999D")));
/* 600 */                     curMnode.instructions.remove(curMnode.instructions.get(j));
/* 601 */                     curMnode.instructions.insert(curMnode.instructions.get(j - 1), overrideList);
/* 602 */                     LogSucceed(lablelAsTarget);
/*     */                     break;
/*     */                   } 
/*     */                 } 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 611 */       } catch (Exception e) {
/* 612 */         e.printStackTrace();
/* 613 */         LogFailed(lablelAsTarget);
/* 614 */         return bytes;
/*     */       } 
/* 618 */       ClassWriter cw = new ClassWriter(1);
/* 619 */       classNode.accept(cw);
/* 620 */       bytes = cw.toByteArray();
/*     */     } 
/* 625 */     if (transformedName.equals("net.minecraft.client.renderer.entity.RendererLivingEntity")) {
/* 626 */       ClassReader cr = new ClassReader(bytes);
/* 627 */       ClassNode classNode = new ClassNode();
/* 628 */       cr.accept(classNode, 0);
/* 629 */       String lablelAsTarget = "RendererLivingEntity.rotateCorpse";
/*     */       try {
/* 631 */         String targetMethodName = SMCoreObfuscaHelper.getProperName("rotateCorpse");
/* 632 */         String targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("rotateCorpse");
/* 635 */         for (MethodNode curMnode : classNode.methods) {
/* 636 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 637 */             LogFound(lablelAsTarget);
/* 640 */             InsnList overrideList = new InsnList();
/* 641 */             overrideList.add(new VarInsnNode(25, 1));
/* 642 */             overrideList.add(new VarInsnNode(23, 2));
/* 643 */             overrideList.add(new VarInsnNode(23, 3));
/* 644 */             overrideList.add(new VarInsnNode(23, 4));
/* 645 */             overrideList.add(new MethodInsnNode(184, "jp/mc/ancientred/starminer/core/TransformClientHelper", "rotateCorpseByGravity", "(Lnet/minecraft/entity/EntityLivingBase;FFF)V"));
/* 649 */             overrideList.add(new InsnNode(177));
/* 651 */             curMnode.instructions.insert(curMnode.instructions.get(1), overrideList);
/* 652 */             LogSucceed(lablelAsTarget);
/*     */             break;
/*     */           } 
/*     */         } 
/* 657 */         lablelAsTarget = "RendererLivingEntity.doRender";
/* 658 */         targetMethodName = SMCoreObfuscaHelper.getProperName("doRender");
/* 659 */         targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("doRender");
/* 662 */         for (MethodNode curMnode : classNode.methods) {
/* 663 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 664 */             LogFound(lablelAsTarget);
/* 665 */             for (int i = 0; i < curMnode.instructions.size(); i++) {
/* 666 */               if (curMnode.instructions.get(i).getType() == 5 && curMnode.instructions.get(i).getOpcode() == 182 && ((MethodInsnNode)curMnode.instructions.get(i)).owner.equals(SMCoreObfuscaHelper.getProperName("net/minecraft/client/renderer/entity/RendererLivingEntity")) && ((MethodInsnNode)curMnode.instructions.get(i)).name.equals(SMCoreObfuscaHelper.getProperName("rotateCorpse")) && ((MethodInsnNode)curMnode.instructions.get(i)).desc.equals(SMCoreObfuscaHelper.getProperDesc("rotateCorpse"))) {
/* 671 */                 InsnList overrideList = new InsnList();
/* 672 */                 overrideList.add(new MethodInsnNode(184, "jp/mc/ancientred/starminer/core/TransformClientHelper", "rotateCorpseProxy", "(Lnet/minecraft/client/renderer/entity/RendererLivingEntity;Lnet/minecraft/entity/EntityLivingBase;FFF)V"));
/* 677 */                 curMnode.instructions.remove(curMnode.instructions.get(i));
/* 678 */                 curMnode.instructions.insert(curMnode.instructions.get(i - 1), overrideList);
/* 679 */                 LogSucceed(lablelAsTarget);
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 688 */         boolean hasRotateCorpsePublic = false;
/* 689 */         for (MethodNode curMnode : classNode.methods) {
/* 690 */           if (curMnode.name.equals("rotateCorpsePublic")) {
/* 691 */             hasRotateCorpsePublic = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 695 */         if (!hasRotateCorpsePublic)
/* 696 */           classNode.methods.add(createRotateCorpsePublic()); 
/* 698 */       } catch (Exception e) {
/* 699 */         e.printStackTrace();
/* 700 */         LogFailed(lablelAsTarget);
/* 701 */         return bytes;
/*     */       } 
/* 705 */       ClassWriter cw = new ClassWriter(1);
/* 706 */       classNode.accept(cw);
/* 707 */       bytes = cw.toByteArray();
/*     */     } 
/* 713 */     if (transformedName.equals("net.minecraft.network.NetHandlerPlayServer")) {
/* 714 */       ClassReader cr = new ClassReader(bytes);
/* 715 */       ClassNode classNode = new ClassNode();
/* 716 */       cr.accept(classNode, 0);
/* 718 */       String lablelAsTarget = "NetHandlerPlayServer.processPlayer";
/*     */       try {
/* 721 */         String targetMethodName = SMCoreObfuscaHelper.getProperName("processPlayer");
/* 722 */         String targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("processPlayer");
/* 725 */         for (MethodNode curMnode : classNode.methods) {
/* 726 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 727 */             LogFound(lablelAsTarget);
/* 730 */             for (int i = 0; i < curMnode.instructions.size(); i++) {
/* 731 */               if (curMnode.instructions.get(i).getOpcode() == 25 && curMnode.instructions.get(i + 1).getType() == 9 && ((LdcInsnNode)curMnode.instructions.get(i + 1)).cst.equals("Illegal stance") && curMnode.instructions.get(i + 2).getType() == 5 && ((MethodInsnNode)curMnode.instructions.get(i + 2)).name.equals(SMCoreObfuscaHelper.getProperName("kickPlayerFromServer")) && ((MethodInsnNode)curMnode.instructions.get(i + 2)).desc.equals(SMCoreObfuscaHelper.getProperDesc("kickPlayerFromServer"))) {
/* 738 */                 LabelNode labelJump = null;
/* 739 */                 for (int k = i - 1; k >= 0; k--) {
/* 740 */                   if (curMnode.instructions.get(k).getType() == 7)
/* 741 */                     labelJump = ((JumpInsnNode)curMnode.instructions.get(k)).label; 
/*     */                 } 
/* 745 */                 if (labelJump != null) {
/* 747 */                   InsnList overrideList = new InsnList();
/* 748 */                   overrideList.add(new MethodInsnNode(184, "jp/mc/ancientred/starminer/core/TransformServerHelper", "jumpOverKickIllegalStance", "()Z"));
/* 752 */                   overrideList.add(new JumpInsnNode(154, labelJump));
/* 754 */                   curMnode.instructions.insert(curMnode.instructions.get(i - 1), overrideList);
/* 755 */                   LogSucceed(lablelAsTarget + "(A)");
/*     */                   break;
/*     */                 } 
/*     */                 break;
/*     */               } 
/*     */             } 
/* 762 */             Double tgtCst = new Double("-0.03125");
/* 763 */             for (int j = 0; j < curMnode.instructions.size(); j++) {
/* 764 */               if (curMnode.instructions.get(j).getType() == 2 && curMnode.instructions.get(j + 1).getType() == 9 && ((LdcInsnNode)curMnode.instructions.get(j + 1)).cst.equals(tgtCst) && curMnode.instructions.get(j + 2).getOpcode() == 151 && curMnode.instructions.get(j + 3).getType() == 7) {
/* 769 */                 LabelNode labelJump = ((JumpInsnNode)curMnode.instructions.get(j + 3)).label;
/* 772 */                 InsnList overrideList = new InsnList();
/* 773 */                 overrideList.add(new VarInsnNode(25, 0));
/* 774 */                 overrideList.add(new MethodInsnNode(184, "jp/mc/ancientred/starminer/core/TransformServerHelper", "jumpOverKickFloatTooLong", "(Lnet/minecraft/network/NetHandlerPlayServer;)Z"));
/* 778 */                 overrideList.add(new JumpInsnNode(154, labelJump));
/* 780 */                 curMnode.instructions.insert(curMnode.instructions.get(j + 3), overrideList);
/* 781 */                 LogSucceed(lablelAsTarget + "(B)");
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 787 */       } catch (Exception e) {
/* 788 */         e.printStackTrace();
/* 789 */         LogFailed(lablelAsTarget);
/* 790 */         return bytes;
/*     */       } 
/* 794 */       ClassWriter cw = new ClassWriter(1);
/* 795 */       classNode.accept(cw);
/* 796 */       bytes = cw.toByteArray();
/*     */     } 
/* 801 */     if (transformedName.equals("net.minecraft.network.play.server.S0APacketUseBed")) {
/* 802 */       ClassReader cr = new ClassReader(bytes);
/* 803 */       ClassNode classNode = new ClassNode();
/* 804 */       cr.accept(classNode, 0);
/* 806 */       String lablelAsTarget = "S0APacketUseBed.readPacketData";
/*     */       try {
/* 809 */         String targetMethodName = SMCoreObfuscaHelper.getProperName("readPacketData");
/* 810 */         String targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("readPacketData");
/* 813 */         for (MethodNode curMnode : classNode.methods) {
/* 814 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 815 */             LogFound(lablelAsTarget);
/* 816 */             for (int i = curMnode.instructions.size() - 1; i >= 0; i--) {
/* 817 */               if (curMnode.instructions.get(i).getType() == 5 && ((MethodInsnNode)curMnode.instructions.get(i)).name.equals(SMCoreObfuscaHelper.getProperName("readByte")) && ((MethodInsnNode)curMnode.instructions.get(i)).desc.equals(SMCoreObfuscaHelper.getProperDesc("readByte"))) {
/* 821 */                 ((MethodInsnNode)curMnode.instructions.get(i)).name = SMCoreObfuscaHelper.getProperName("readInt");
/* 822 */                 ((MethodInsnNode)curMnode.instructions.get(i)).desc = SMCoreObfuscaHelper.getProperDesc("readInt");
/* 824 */                 LogSucceed(lablelAsTarget);
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 830 */         lablelAsTarget = "S0APacketUseBed.writePacketData";
/* 831 */         targetMethodName = SMCoreObfuscaHelper.getProperName("writePacketData");
/* 832 */         targetMethoddesc = SMCoreObfuscaHelper.getProperDesc("writePacketData");
/* 835 */         for (MethodNode curMnode : classNode.methods) {
/* 836 */           if (targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) {
/* 837 */             LogFound(lablelAsTarget);
/* 838 */             for (int i = curMnode.instructions.size() - 1; i >= 0; i--) {
/* 839 */               if (curMnode.instructions.get(i).getType() == 5 && ((MethodInsnNode)curMnode.instructions.get(i)).name.equals(SMCoreObfuscaHelper.getProperName("writeByte")) && ((MethodInsnNode)curMnode.instructions.get(i)).desc.equals(SMCoreObfuscaHelper.getProperDesc("writeByte"))) {
/* 843 */                 ((MethodInsnNode)curMnode.instructions.get(i)).name = SMCoreObfuscaHelper.getProperName("writeInt");
/* 844 */                 ((MethodInsnNode)curMnode.instructions.get(i)).desc = SMCoreObfuscaHelper.getProperDesc("writeInt");
/* 846 */                 LogSucceed(lablelAsTarget);
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 852 */       } catch (Exception e) {
/* 853 */         e.printStackTrace();
/* 854 */         LogFailed(lablelAsTarget);
/* 855 */         return bytes;
/*     */       } 
/* 859 */       ClassWriter cw = new ClassWriter(1);
/* 860 */       classNode.accept(cw);
/* 861 */       bytes = cw.toByteArray();
/*     */     } 
/* 864 */     return bytes;
/*     */   }
/*     */   
/*     */   private void checkTweakerLoad() {
/*     */     try {
/* 869 */       Class.forName("jp.mc.ancientred.starminer.tweaker.SMAvoidOptiFineOverwriteTweaker");
/* 870 */       this.hasTweakerLoaded = true;
/* 871 */     } catch (ClassNotFoundException e) {
/* 872 */       this.hasTweakerLoaded = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static MethodNode createRotateCorpsePublic() {
/* 878 */     MethodNode methodNode = new MethodNode(262144, 1, "rotateCorpsePublic", "(Lnet/minecraft/entity/EntityLivingBase;[F)V", null, null);
/* 880 */     InsnList list = methodNode.instructions;
/* 882 */     list.add(new VarInsnNode(25, 0));
/* 883 */     list.add(new VarInsnNode(25, 1));
/* 884 */     list.add(new VarInsnNode(25, 2));
/* 885 */     list.add(new InsnNode(3));
/* 886 */     list.add(new InsnNode(48));
/* 887 */     list.add(new VarInsnNode(25, 2));
/* 888 */     list.add(new InsnNode(4));
/* 889 */     list.add(new InsnNode(48));
/* 890 */     list.add(new VarInsnNode(25, 2));
/* 891 */     list.add(new InsnNode(5));
/* 892 */     list.add(new InsnNode(48));
/* 893 */     list.add(new MethodInsnNode(182, "net/minecraft/client/renderer/entity/RendererLivingEntity", SMCoreObfuscaHelper.getSrgName("rotateCorpse"), "(Lnet/minecraft/entity/EntityLivingBase;FFF)V"));
/* 894 */     list.add(new InsnNode(177));
/* 896 */     return methodNode;
/*     */   }
/*     */   
/*     */   public static void LogSucceed(String targetClassAndMethodName) {
/* 900 */     LogWrapper.log(Level.INFO, "[Starminer]Injected additional code to " + targetClassAndMethodName, new Object[0]);
/*     */   }
/*     */   
/*     */   public static void LogFailed(String targetClassAndMethodName) {
/* 903 */     LogWrapper.log(Level.INFO, "[Starminer]!!!!Logic injection failed on " + targetClassAndMethodName, new Object[0]);
/*     */   }
/*     */   
/*     */   public static void LogFound(String targetClassAndMethodName) {
/* 906 */     LogWrapper.log(Level.INFO, "[Starminer]Target method found : " + targetClassAndMethodName, new Object[0]);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */