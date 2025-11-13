/*     */ package jp.mc.ancientred.starminer.basics.block.gravitized;
/*     */ 
/*     */ import java.util.Random;
/*     */ import jp.mc.ancientred.starminer.basics.block.DirectionConst;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ 
/*     */ public class WorldGenAbstractTreeEx extends WorldGenAbstractTree {
/*     */   public int dir;
/*     */   
/*     */   public WorldGenAbstractTreeEx(boolean p_i45448_1_) {
/*  16 */     super(p_i45448_1_);
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
/*  21 */     return false;
/*     */   }
/*     */   
/*     */   protected class TranslatedVec {
/*     */     public int x;
/*     */     
/*     */     public int y;
/*     */     
/*     */     public int z;
/*     */   }
/*     */   
/*  31 */   protected TranslatedVec saved = new TranslatedVec();
/*     */   
/*  32 */   protected TranslatedVec trans = new TranslatedVec();
/*     */   
/*     */   private StructureBoundingBox chunkBoundingBox;
/*     */   
/*     */   private Block[] blocksData;
/*     */   
/*     */   private byte[] blockMetas;
/*     */   
/*     */   protected int convertWoodMeta(int meta) {
/*  35 */     int typeMetaa = meta & 0x3;
/*  36 */     switch (this.dir) {
/*     */       case 2:
/*     */       case 3:
/*  39 */         return typeMetaa | 0x4;
/*     */       case 4:
/*     */       case 5:
/*  42 */         return typeMetaa | 0x8;
/*     */     } 
/*  44 */     return meta;
/*     */   }
/*     */   
/*     */   protected void translateXYZ(int argX, int argY, int argZ) {
/*  47 */     switch (this.dir) {
/*     */       case 1:
/*  49 */         this.saved.x += argX - this.saved.x;
/*  50 */         this.saved.y -= argY - this.saved.y;
/*  51 */         this.saved.z += argZ - this.saved.z;
/*     */         break;
/*     */       case 0:
/*  54 */         this.saved.x += argX - this.saved.x;
/*  55 */         this.saved.y += argY - this.saved.y;
/*  56 */         this.saved.z += argZ - this.saved.z;
/*     */         break;
/*     */       case 3:
/*  59 */         this.saved.x -= argY - this.saved.y;
/*  60 */         this.saved.y += argZ - this.saved.z;
/*  61 */         this.saved.z += argX - this.saved.x;
/*     */         break;
/*     */       case 2:
/*  64 */         this.saved.x += argY - this.saved.y;
/*  65 */         this.saved.y += argZ - this.saved.z;
/*  66 */         this.saved.z += argX - this.saved.x;
/*     */         break;
/*     */       case 5:
/*  69 */         this.saved.x += argZ - this.saved.z;
/*  70 */         this.saved.y += argX - this.saved.x;
/*  71 */         this.saved.z -= argY - this.saved.y;
/*     */         break;
/*     */       case 4:
/*  74 */         this.saved.x += argZ - this.saved.z;
/*  75 */         this.saved.y += argX - this.saved.x;
/*  76 */         this.saved.z += argY - this.saved.y;
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setChunkProviderSupportData(StructureBoundingBox parChunkBoundingBox, Block[] parBlocksData, byte[] parBlockMetas) {
/*  87 */     this.chunkBoundingBox = parChunkBoundingBox;
/*  88 */     this.blocksData = parBlocksData;
/*  89 */     this.blockMetas = parBlockMetas;
/*     */   }
/*     */   
/*     */   protected void setBlockForChunkProvide(int posX, int posY, int posZ, Block parBlock, int meta) {
/*  96 */     if (posY >= 0 && posY <= 255 && posX >= this.chunkBoundingBox.field_78897_a && posX <= this.chunkBoundingBox.field_78893_d && posZ >= this.chunkBoundingBox.field_78896_c && posZ <= this.chunkBoundingBox.field_78892_f) {
/* 101 */       int targetIdIndex = posY << 8 | posZ - this.chunkBoundingBox.field_78896_c << 4 | posX - this.chunkBoundingBox.field_78897_a;
/* 104 */       if (this.blocksData[targetIdIndex] == Blocks.field_150350_a) {
/* 105 */         int airDir = this.blockMetas[targetIdIndex] - 1;
/* 106 */         if (airDir >= 0 && airDir < DirectionConst.OPPOSITE_CNV.length) {
/* 107 */           int plantPosX = posX + DirectionConst.CHECKNEIGHBOR_LIST[DirectionConst.OPPOSITE_CNV[airDir]][0];
/* 108 */           int plantPosY = posY + DirectionConst.CHECKNEIGHBOR_LIST[DirectionConst.OPPOSITE_CNV[airDir]][1];
/* 109 */           int plantPosZ = posZ + DirectionConst.CHECKNEIGHBOR_LIST[DirectionConst.OPPOSITE_CNV[airDir]][2];
/* 112 */           if (plantPosY >= 0 && plantPosY <= 255 && plantPosX >= this.chunkBoundingBox.field_78897_a && plantPosX <= this.chunkBoundingBox.field_78893_d && plantPosZ >= this.chunkBoundingBox.field_78896_c && posZ <= this.chunkBoundingBox.field_78892_f) {
/* 117 */             int plantIdIndex = plantPosY << 8 | plantPosZ - this.chunkBoundingBox.field_78896_c << 4 | plantPosX - this.chunkBoundingBox.field_78897_a;
/* 118 */             if (this.blocksData[plantIdIndex] instanceof IGravitizedPlants)
/* 119 */               this.blocksData[plantIdIndex] = Blocks.field_150350_a; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 125 */       this.blocksData[targetIdIndex] = parBlock;
/* 126 */       this.blockMetas[targetIdIndex] = (byte)meta;
/*     */     } 
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */