package com.xviable.PuzzleDev;

import net.minecraft.server.Block;
import net.minecraft.server.BlockSand;
import net.minecraft.server.EntityFallingBlock;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MathHelper;
import net.minecraft.server.World;

public class EntityPushedSand extends EntityFallingBlock {
	
	
	 public EntityPushedSand(World arg0) {
			super(arg0);
		}
		 
	 public EntityPushedSand(World arg0, double arg1, double arg2, double arg3,
				int arg4, double pushVelocityX, double pushVelocityZ) {
			super(arg0, arg1, arg2, arg3, arg4);
		}
		 
	 public EntityPushedSand(World arg0, double arg1, double arg2, double arg3,
				int arg4, int arg5, double pushVelocityX, double pushVelocityZ) {
			super(arg0, arg1, arg2, arg3, arg4, arg5);
		}

	 
	public void j_()
	  {
	    if (this.id == 0) {
	      die();
	      return;
	    }

	    this.lastX = this.locX;
	    this.lastY = this.locY;
	    this.lastZ = this.locZ;
	    this.c += 1;

	    this.motY -= 0.03999999910593033D;
	    move(this.motX, this.motY, this.motZ);
	    this.motX *= 0.99D;
	    this.motY *= 0.9800000190734863D;
	    this.motZ *= 0.99D;

	    if (!this.world.isStatic) {
	      int i = MathHelper.floor(this.locX);
	      int j = MathHelper.floor(this.locY);
	      int k = MathHelper.floor(this.locZ);

	      if (this.c == 1) {
	        if ((this.c == 1) && (this.world.getTypeId(i, j, k) == this.id))
	          this.world.setTypeId(i, j, k, 0);
	        else {
	          die();
	        }
	      }

	      if (this.onGround && motX < .0001 && motZ < .0001) {
	        this.motX *= 0.699999988079071D;
	        this.motZ *= 0.699999988079071D;
	        this.motY *= -0.5D;

	        if (this.world.getTypeId(i, j, k) != Block.PISTON_MOVING.id) {
	          die();

	          if ((this.world.mayPlace(this.id, i, j, k, true, 1, null)) && (!BlockSand.canFall(this.world, i, j - 1, k)) && (this.world.setTypeIdAndData(i, j, k, this.id, this.data))) {
	            if ((Block.byId[this.id] instanceof BlockSand)) {
	              ((BlockSand)Block.byId[this.id]).a_(this.world, i, j, k, this.data);
	            }
	          }
	          else if ((this.dropItem)) a(new ItemStack(this.id, 1, Block.byId[this.id].getDropData(this.data)), 0.0F);
	        }
	      }
	      else if (((this.c > 100) && (!this.world.isStatic) && ((j < 1) || (j > 256))) || (this.c > 600)) {
	        if (this.dropItem) a(new ItemStack(this.id, 1, Block.byId[this.id].getDropData(this.data)), 0.0F);
	        die();
	      }
	    }
	  }
	

	

	

}
