/*    */ package jp.mc.ancientred.starminer.basics.structure;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*    */ import net.minecraft.world.gen.structure.StructureStart;
/*    */ 
/*    */ public class StructureStarStart extends StructureStart {
/*    */   public StructureStarStart() {}
/*    */   
/*    */   public StructureStarStart(World par1World, Random par2Random, int par3, int par4) {
/* 19 */     super(par3, par4);
/* 21 */     ComponentStar componentStar = new ComponentStar(0, par2Random, (par3 << 4) + 2, (par4 << 4) + 2, par1World);
/* 22 */     this.field_75075_a.add(componentStar);
/* 23 */     componentStar.func_74861_a(componentStar, this.field_75075_a, par2Random);
/* 24 */     func_75072_c();
/* 25 */     func_75067_a(par1World, par2Random, 68);
/*    */   }
/*    */   
/*    */   public void generateStructureImmidiate(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox, Block[] blocksData, byte[] blockMetas) {
/* 32 */     Iterator<ComponentStar> iterator = this.field_75075_a.iterator();
/* 34 */     while (iterator.hasNext()) {
/* 36 */       ComponentStar structurecomponent = iterator.next();
/* 38 */       if (structurecomponent.func_74874_b().func_78884_a(par3StructureBoundingBox) && !structurecomponent.addComponentParts(par1World, par2Random, par3StructureBoundingBox, blocksData, blockMetas))
/* 41 */         iterator.remove(); 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void func_143022_a(NBTTagCompound par1NBTTagCompound) {}
/*    */   
/*    */   public void func_143017_b(NBTTagCompound par1NBTTagCompound) {}
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */