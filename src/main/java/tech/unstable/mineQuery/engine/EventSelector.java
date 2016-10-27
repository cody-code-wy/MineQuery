package tech.unstable.mineQuery.engine;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaObject;

import tech.unstable.mineQuery.jsHelers.events.JsEvent;

public class EventSelector{

  private final Function function;
  private final Selector selector;

  public EventSelector(Selector selector, Function function){
    this.selector = selector;
    this.function = function;
  }


  public void eval(JsEvent event){
    if(selector.eval(event)){
      fire(event);
    }
  }


  public void fire(JsEvent event){
    Context cx = Context.enter();
    NativeJavaObject thiz = new NativeJavaObject(cx.initStandardObjects(), event.getValidSelector(), Object.class);
    function.call(cx, MineQueryLoader.getGlobalScope("FUNCTION"), thiz, new Object[]{event}); // TODO replace with script's name
  }
}
