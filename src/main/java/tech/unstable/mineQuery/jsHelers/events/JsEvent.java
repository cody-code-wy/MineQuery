package tech.unstable.mineQuery.jsHelers.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class JsEvent{

  public Event _event;

  public String _eventName;

  public JsEvent(String eventName, Event event){
    _event = event;
    _eventName = eventName;
  }

  public void cancel(){
    if(_event.isCancelable()){
      _event.setCanceled(true);
    }
  }

  public void uncancel(){
    if(_event.isCancelable()){
      _event.setCanceled(false);
    }
  }

  public boolean evalSelector(String selector){
    return selector.matches("\\*");
  }

  public Object getValidSelector(){
    return "";
  }

}
