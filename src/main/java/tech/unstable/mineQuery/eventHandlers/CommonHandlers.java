package tech.unstable.mineQuery.eventHandlers;

import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import tech.unstable.mineQuery.engine.Registery;
import tech.unstable.mineQuery.jsHelers.events.JsEvent;
import tech.unstable.mineQuery.jsHelers.events.world.JsBlockEvent;

/**
 * Created by cody on 2016-09-24.
 */
public class CommonHandlers {

  @SubscribeEvent
  public void onBlockBreak(BreakEvent event){
    JsEvent jsevent = new JsBlockEvent(event).new JsBreakEvent(event);
    Registery.getSingleton().fireEvent(jsevent);
  }

  @SubscribeEvent
  public void onBlockPlace(PlaceEvent event){
    JsEvent jsevent = new JsBlockEvent(event).new JsPlaceEvent(event);
    Registery.getSingleton().fireEvent(jsevent);
  }

}
