/*     */ package jp.mc.ancientred.starminer.api;
/*     */ 
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ public enum GravityDirection {
/*  10 */   upTOdown_YN(1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, GravityConst.matirxRoatUpToDownI, GravityConst.matirxRoatUpToDownD, GravityConst.forgeSideRotUpToDown),
/*  11 */   downTOup_YP(1.0F, 0.0F, 0.0F, -1.0F, 0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, -1.0F, 0.0F, GravityConst.matirxRoatDownTOupI, GravityConst.matirxRoatDownTOupD, GravityConst.forgeSideRotDownTOup),
/*  12 */   eastTOwest_XN(0.0F, -1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.5F, -1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, GravityConst.matirxRoatEastTOwestI, GravityConst.matirxRoatEastTOwestD, GravityConst.forgeSideRotEastTOwest),
/*  13 */   westTOeast_XP(0.0F, 1.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 0.0F, -1.0F, 0.0F, 0.0F, GravityConst.matirxRoatWestTOeastI, GravityConst.matirxRoatWestTOeastD, GravityConst.forgeSideRotWestTOeast),
/*  14 */   northTOsouth_ZP(1.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, -1.0F, GravityConst.matirxRoatNorthTOsouthI, GravityConst.matirxRoatNorthTOsouthD, GravityConst.forgeSideRotNorthTOsouth),
/*  15 */   southTOnorth_ZN(1.0F, 0.0F, 0.0F, 0.0F, 1.0F, -0.5F, 0.0F, 0.0F, 1.0F, -1.0F, 0.0F, 0.0F, 1.0F, GravityConst.matirxRoatSouthTOnorthI, GravityConst.matirxRoatSouthTOnorthD, GravityConst.forgeSideRotSouthTOnorth);
/*     */   
/*     */   public float pitchRotDirX;
/*     */   
/*     */   public float pitchRotDirY;
/*     */   
/*     */   public float yawRotDirX;
/*     */   
/*     */   public float yawRotDirY;
/*     */   
/*     */   public float yawRotDirZ;
/*     */   
/*     */   public float rotX;
/*     */   
/*     */   public float rotZ;
/*     */   
/*     */   public float shiftEyeX;
/*     */   
/*     */   public float shiftEyeY;
/*     */   
/*     */   public float shiftEyeZ;
/*     */   
/*     */   public float shiftSneakX;
/*     */   
/*     */   public float shiftSneakY;
/*     */   
/*     */   public float shiftSneakZ;
/*     */   
/*     */   public int[] matrixRotationI;
/*     */   
/*     */   public double[] matrixRotationD;
/*     */   
/*     */   public int[] forgeSideRot;
/*     */   
/*     */   public int collideCheckExpandX;
/*     */   
/*     */   public int collideCheckExpandY;
/*     */   
/*     */   public int collideCheckExpandZ;
/*     */   
/*     */   GravityDirection(float argPitchRotDirX, float argPitchRotDirY, float argYawRotDirX, float argYawRotDirY, float argYawRotDirZ, float argRotX, float argRotZ, float argShiftEyeX, float argShiftEyeY, float argShiftEyeZ, float argShiftSneakX, float argShiftSneakY, float argShiftSneakZ, int[] argMatrixRotationI, double[] argMatrixRotationD, int[] argForgeSideRot) {
/*  52 */     this.pitchRotDirX = argPitchRotDirX;
/*  53 */     this.pitchRotDirY = argPitchRotDirY;
/*  54 */     this.yawRotDirX = argYawRotDirX;
/*  55 */     this.yawRotDirY = argYawRotDirY;
/*  56 */     this.yawRotDirZ = argYawRotDirZ;
/*  57 */     this.rotX = argRotX;
/*  58 */     this.rotZ = argRotZ;
/*  59 */     this.shiftEyeX = argShiftEyeX;
/*  60 */     this.shiftEyeY = argShiftEyeY;
/*  61 */     this.shiftEyeZ = argShiftEyeZ;
/*  62 */     this.shiftSneakX = argShiftSneakX;
/*  63 */     this.shiftSneakY = argShiftSneakY;
/*  64 */     this.shiftSneakZ = argShiftSneakZ;
/*  65 */     this.matrixRotationI = argMatrixRotationI;
/*  66 */     this.matrixRotationD = argMatrixRotationD;
/*  67 */     this.forgeSideRot = argForgeSideRot;
/*  69 */     this.collideCheckExpandX = -this.matrixRotationI[3];
/*  70 */     this.collideCheckExpandY = -this.matrixRotationI[4];
/*  71 */     this.collideCheckExpandZ = -this.matrixRotationI[5];
/*     */   }
/*     */   
/*     */   public static GravityDirection turnWayForNormal(GravityDirection gDir) {
/*  77 */     switch (gDir) {
/*     */       case downTOup_YP:
/*  79 */         return downTOup_YP;
/*     */       case eastTOwest_XN:
/*  81 */         return westTOeast_XP;
/*     */       case westTOeast_XP:
/*  83 */         return eastTOwest_XN;
/*     */       case northTOsouth_ZP:
/*  85 */         return southTOnorth_ZN;
/*     */       case southTOnorth_ZN:
/*  87 */         return northTOsouth_ZP;
/*     */     } 
/*  89 */     return upTOdown_YN;
/*     */   }
/*     */   
/*     */   public Vec3 rotateVec3(Vec3 vec3) {
/*  94 */     return rotateVec3(this, vec3);
/*     */   }
/*     */   
/*     */   public Vec3 rotateVec3At(Vec3 vec3, double centerX, double centerY, double centerZ) {
/*  97 */     return rotateVec3At(this, vec3, centerX, centerY, centerZ);
/*     */   }
/*     */   
/*     */   public Vec3 rotateVec3At(Vec3 vec3, Vec3 centerVec3) {
/* 100 */     return rotateVec3At(this, vec3, centerVec3);
/*     */   }
/*     */   
/*     */   public static final Vec3 rotateVec3(GravityDirection dir, Vec3 vec3) {
/* 104 */     double x = vec3.field_72450_a;
/* 105 */     double y = vec3.field_72448_b;
/* 106 */     double z = vec3.field_72449_c;
/* 108 */     vec3.field_72450_a = x * dir.matrixRotationD[0] + y * dir.matrixRotationD[3] + z * dir.matrixRotationD[6];
/* 112 */     vec3.field_72448_b = x * dir.matrixRotationD[1] + y * dir.matrixRotationD[4] + z * dir.matrixRotationD[7];
/* 116 */     vec3.field_72449_c = x * dir.matrixRotationD[2] + y * dir.matrixRotationD[5] + z * dir.matrixRotationD[8];
/* 120 */     return vec3;
/*     */   }
/*     */   
/*     */   public static final Vec3 rotateVec3At(GravityDirection dir, Vec3 vec3, Vec3 centerVec3) {
/* 124 */     return rotateVec3At(dir, centerVec3, centerVec3.field_72450_a, centerVec3.field_72448_b, centerVec3.field_72449_c);
/*     */   }
/*     */   
/*     */   public static final Vec3 rotateVec3At(GravityDirection dir, Vec3 vec3, double centerX, double centerY, double centerZ) {
/* 128 */     double x = vec3.field_72450_a - centerX;
/* 129 */     double y = vec3.field_72448_b - centerY;
/* 130 */     double z = vec3.field_72449_c - centerZ;
/* 132 */     vec3.field_72450_a = x * dir.matrixRotationD[0] + y * dir.matrixRotationD[3] + z * dir.matrixRotationD[6] + centerX;
/* 136 */     vec3.field_72448_b = x * dir.matrixRotationD[1] + y * dir.matrixRotationD[4] + z * dir.matrixRotationD[7] + centerY;
/* 140 */     vec3.field_72449_c = x * dir.matrixRotationD[2] + y * dir.matrixRotationD[5] + z * dir.matrixRotationD[8] + centerZ;
/* 144 */     return vec3;
/*     */   }
/*     */   
/*     */   public double[] rotateXYZAt(double[] retVal, double argX, double argY, double argZ, double centerX, double centerY, double centerZ) {
/* 158 */     return rotateXYZAt(this, retVal, argX, argY, argZ, centerX, centerY, centerZ);
/*     */   }
/*     */   
/*     */   public static final double[] rotateXYZAt(GravityDirection dir, double[] retVal, double argX, double argY, double argZ, double centerX, double centerY, double centerZ) {
/* 161 */     double x = argX - centerX;
/* 162 */     double y = argY - centerY;
/* 163 */     double z = argZ - centerZ;
/* 165 */     retVal[0] = x * dir.matrixRotationD[0] + y * dir.matrixRotationD[3] + z * dir.matrixRotationD[6] + centerX;
/* 169 */     retVal[1] = x * dir.matrixRotationD[1] + y * dir.matrixRotationD[4] + z * dir.matrixRotationD[7] + centerY;
/* 173 */     retVal[2] = x * dir.matrixRotationD[2] + y * dir.matrixRotationD[5] + z * dir.matrixRotationD[8] + centerZ;
/* 177 */     return retVal;
/*     */   }
/*     */   
/*     */   public float[] rotateXYZAt(float[] retVal, float argX, float argY, float argZ, float centerX, float centerY, float centerZ) {
/* 180 */     return rotateXYZAt(this, retVal, argX, argY, argZ, centerX, centerY, centerZ);
/*     */   }
/*     */   
/*     */   public static final float[] rotateXYZAt(GravityDirection dir, float[] retVal, float argX, float argY, float argZ, float centerX, float centerY, float centerZ) {
/* 183 */     float x = argX - centerX;
/* 184 */     float y = argY - centerY;
/* 185 */     float z = argZ - centerZ;
/* 187 */     retVal[0] = x * (float)dir.matrixRotationD[0] + y * (float)dir.matrixRotationD[3] + z * (float)dir.matrixRotationD[6] + centerX;
/* 191 */     retVal[1] = x * (float)dir.matrixRotationD[1] + y * (float)dir.matrixRotationD[4] + z * (float)dir.matrixRotationD[7] + centerY;
/* 195 */     retVal[2] = x * (float)dir.matrixRotationD[2] + y * (float)dir.matrixRotationD[5] + z * (float)dir.matrixRotationD[8] + centerZ;
/* 199 */     return retVal;
/*     */   }
/*     */   
/*     */   public int[] rotateXYZAt(int[] retVal, int argX, int argY, int argZ, int centerX, int centerY, int centerZ) {
/* 202 */     return rotateXYZAt(this, retVal, argX, argY, argZ, centerX, centerY, centerZ);
/*     */   }
/*     */   
/*     */   public static final int[] rotateXYZAt(GravityDirection dir, int[] retVal, int argX, int argY, int argZ, int centerX, int centerY, int centerZ) {
/* 205 */     int x = argX - centerX;
/* 206 */     int y = argY - centerY;
/* 207 */     int z = argZ - centerZ;
/* 209 */     retVal[0] = x * dir.matrixRotationI[0] + y * dir.matrixRotationI[3] + z * dir.matrixRotationI[6] + centerX;
/* 213 */     retVal[1] = x * dir.matrixRotationI[1] + y * dir.matrixRotationI[4] + z * dir.matrixRotationI[7] + centerY;
/* 217 */     retVal[2] = x * dir.matrixRotationI[2] + y * dir.matrixRotationI[5] + z * dir.matrixRotationI[8] + centerZ;
/* 221 */     return retVal;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB rotateAABBAt(AxisAlignedBB aabb, int x, int y, int z) {
/* 225 */     return rotateAABBAt(this, aabb, x, y, z);
/*     */   }
/*     */   
/*     */   public static final AxisAlignedBB rotateAABBAt(GravityDirection dir, AxisAlignedBB aabb, int x, int y, int z) {
/* 228 */     return rotateAABBAt(dir, aabb, (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB rotateAABBAt(AxisAlignedBB aabb, double roatCenterX, double roatCenterY, double roatCenterZ) {
/* 231 */     return rotateAABBAt(this, aabb, roatCenterX, roatCenterY, roatCenterZ);
/*     */   }
/*     */   
/*     */   public static final AxisAlignedBB rotateAABBAt(GravityDirection dir, AxisAlignedBB aabb, double roatCenterX, double roatCenterY, double roatCenterZ) {
/* 235 */     double aabbminX = aabb.field_72340_a - roatCenterX;
/* 236 */     double aabbminY = aabb.field_72338_b - roatCenterY;
/* 237 */     double aabbminZ = aabb.field_72339_c - roatCenterZ;
/* 239 */     double aabbmaxX = aabb.field_72336_d - roatCenterX;
/* 240 */     double aabbmaxY = aabb.field_72337_e - roatCenterY;
/* 241 */     double aabbmaxZ = aabb.field_72334_f - roatCenterZ;
/* 243 */     double x1 = aabbminX * dir.matrixRotationD[0] + aabbminY * dir.matrixRotationD[3] + aabbminZ * dir.matrixRotationD[6] + roatCenterX;
/* 247 */     double y1 = aabbminX * dir.matrixRotationD[1] + aabbminY * dir.matrixRotationD[4] + aabbminZ * dir.matrixRotationD[7] + roatCenterY;
/* 251 */     double z1 = aabbminX * dir.matrixRotationD[2] + aabbminY * dir.matrixRotationD[5] + aabbminZ * dir.matrixRotationD[8] + roatCenterZ;
/* 256 */     double x2 = aabbmaxX * dir.matrixRotationD[0] + aabbmaxY * dir.matrixRotationD[3] + aabbmaxZ * dir.matrixRotationD[6] + roatCenterX;
/* 260 */     double y2 = aabbmaxX * dir.matrixRotationD[1] + aabbmaxY * dir.matrixRotationD[4] + aabbmaxZ * dir.matrixRotationD[7] + roatCenterY;
/* 264 */     double z2 = aabbmaxX * dir.matrixRotationD[2] + aabbmaxY * dir.matrixRotationD[5] + aabbmaxZ * dir.matrixRotationD[8] + roatCenterZ;
/* 268 */     aabb.field_72340_a = Math.min(x1, x2);
/* 269 */     aabb.field_72338_b = Math.min(y1, y2);
/* 270 */     aabb.field_72339_c = Math.min(z1, z2);
/* 272 */     aabb.field_72336_d = Math.max(x1, x2);
/* 273 */     aabb.field_72337_e = Math.max(y1, y2);
/* 274 */     aabb.field_72334_f = Math.max(z1, z2);
/* 276 */     return aabb;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */