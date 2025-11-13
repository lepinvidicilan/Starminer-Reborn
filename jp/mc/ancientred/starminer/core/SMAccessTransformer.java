/*     */ package jp.mc.ancientred.starminer.core;
/*     */ 
/*     */ import net.minecraft.launchwrapper.IClassTransformer;
/*     */ import org.objectweb.asm.ClassReader;
/*     */ import org.objectweb.asm.ClassWriter;
/*     */ import org.objectweb.asm.tree.ClassNode;
/*     */ import org.objectweb.asm.tree.FieldInsnNode;
/*     */ import org.objectweb.asm.tree.MethodInsnNode;
/*     */ import org.objectweb.asm.tree.MethodNode;
/*     */ import org.objectweb.asm.tree.TypeInsnNode;
/*     */ 
/*     */ public class SMAccessTransformer implements IClassTransformer {
/*     */   public byte[] transform(String name, String transformedName, byte[] bytes) {
/*  31 */     if (!transformedName.startsWith("net.minecraft"))
/*  31 */       return bytes; 
/*  43 */     if (transformedName.equals("net.minecraft.entity.EntityLiving")) {
/*  44 */       ClassReader cr = new ClassReader(bytes);
/*  45 */       ClassNode classNode = new ClassNode();
/*  46 */       cr.accept(classNode, 0);
/*  49 */       classNode.superName = "jp/mc/ancientred/starminer/core/entity/EntityLivingGravitized";
/*  50 */       for (MethodNode curMnode : classNode.methods) {
/*  52 */         for (int i = 0; i < curMnode.instructions.size(); i++) {
/*  53 */           if (curMnode.instructions.get(i).getType() == 3 && ((TypeInsnNode)curMnode.instructions.get(i)).desc.equals("net/minecraft/entity/EntityLivingBase"))
/*  55 */             ((TypeInsnNode)curMnode.instructions.get(i)).desc = "jp/mc/ancientred/starminer/core/entity/EntityLivingGravitized"; 
/*  57 */           if (curMnode.instructions.get(i).getType() == 5 && ((MethodInsnNode)curMnode.instructions.get(i)).owner.equals("net/minecraft/entity/EntityLivingBase"))
/*  59 */             ((MethodInsnNode)curMnode.instructions.get(i)).owner = "jp/mc/ancientred/starminer/core/entity/EntityLivingGravitized"; 
/*  61 */           if (curMnode.instructions.get(i).getType() == 4 && ((FieldInsnNode)curMnode.instructions.get(i)).owner.equals("net/minecraft/entity/EntityLivingBase"))
/*  63 */             ((FieldInsnNode)curMnode.instructions.get(i)).owner = "jp/mc/ancientred/starminer/core/entity/EntityLivingGravitized"; 
/*     */         } 
/*     */       } 
/*  68 */       ClassWriter cw = new ClassWriter(1);
/*  69 */       classNode.accept(cw);
/*  70 */       bytes = cw.toByteArray();
/*     */     } 
/*  75 */     if (transformedName.equals("net.minecraft.entity.player.EntityPlayer")) {
/*  76 */       ClassReader cr = new ClassReader(bytes);
/*  77 */       ClassNode classNode = new ClassNode();
/*  78 */       cr.accept(classNode, 0);
/*  80 */       String lablelAsTarget = "EntityPlayer";
/*     */       try {
/*  83 */         classNode.superName = "jp/mc/ancientred/starminer/core/entity/EntityLivingGravitized";
/*  84 */         for (MethodNode curMnode : classNode.methods) {
/*  86 */           for (int i = 0; i < curMnode.instructions.size(); i++) {
/*  87 */             if (curMnode.instructions.get(i).getType() == 3 && ((TypeInsnNode)curMnode.instructions.get(i)).desc.equals("net/minecraft/entity/EntityLivingBase"))
/*  89 */               ((TypeInsnNode)curMnode.instructions.get(i)).desc = "jp/mc/ancientred/starminer/core/entity/EntityLivingGravitized"; 
/*  91 */             if (curMnode.instructions.get(i).getType() == 5 && ((MethodInsnNode)curMnode.instructions.get(i)).owner.equals("net/minecraft/entity/EntityLivingBase"))
/*  93 */               ((MethodInsnNode)curMnode.instructions.get(i)).owner = "jp/mc/ancientred/starminer/core/entity/EntityLivingGravitized"; 
/*  95 */             if (curMnode.instructions.get(i).getType() == 4 && ((FieldInsnNode)curMnode.instructions.get(i)).owner.equals("net/minecraft/entity/EntityLivingBase"))
/*  97 */               ((FieldInsnNode)curMnode.instructions.get(i)).owner = "jp/mc/ancientred/starminer/core/entity/EntityLivingGravitized"; 
/*     */           } 
/*     */         } 
/* 101 */       } catch (Exception e) {
/* 102 */         e.printStackTrace();
/* 103 */         SMTransformer.LogFailed(lablelAsTarget);
/* 104 */         return bytes;
/*     */       } 
/* 107 */       ClassWriter cw = new ClassWriter(1);
/* 108 */       classNode.accept(cw);
/* 109 */       bytes = cw.toByteArray();
/*     */     } 
/* 112 */     return bytes;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */