package tech.unstable.mineQuery.eventHandlers;

import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by cody on 2016-09-24.
 */
public class CommonHandlers {

  @SubscribeEvent
  public void onBlockBreak(BreakEvent event){
  }

  @SubscribeEvent
  public void onBlockPlace(PlaceEvent event){
  }

}
