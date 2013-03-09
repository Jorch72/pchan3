package pchan3.steamship;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class RenderAirship extends Render
{
	   protected ModelBase model;
	   protected ModelBase renderPassModel;
	       
    public RenderAirship(ModelBase modelbase, ModelBase modelbase1, float f)
    {     
        this(modelbase, f);
        setRenderPassModel(modelbase1);         
    }
    
    public RenderAirship(ModelBase modelbase, float f)
    {
    	model = modelbase;
        shadowSize = f;          
    }
    
    public void setRenderPassModel(ModelBase modelbase)
    {
        renderPassModel = modelbase;
    }
    public void renderAirship(EntityAirship entityairship, double d, double d1, double d2, 
            float f, float f1)
    {
    	
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        GL11.glRotatef(180F - f, 0.0F, 1.0F, 0.0F);
        float f2 = (float)entityairship.getTimeSinceHit() - f1;
        float f3 = (float)entityairship.getDamageTaken() - f1;
        if(f3 < 0.0F)
        {
            f3 = 0.0F;
        }
        if(f2 > 0.0F)
        {
            GL11.glRotatef(((MathHelper.sin(f2) * f2 * f3) / 10F) * (float)entityairship.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }
        loadTexture("/terrain.png");
        float f4 = 0.75F;
        GL11.glScalef(f4, f4, f4);
        GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
        
       
        loadTexture("/pchan3/item/airship.png");
        
        GL11.glScalef(-1F, -1F, 1.0F);
        
        model.render(entityairship,0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        	
		loadTexture("/pchan3/item/balloon.png");
		renderPassModel.render(entityairship,0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);	
        GL11.glPopMatrix();
   	 
     
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {	
        this.renderAirship((EntityAirship)entity, d, d1, d2, f, f1);
        
    }
}
