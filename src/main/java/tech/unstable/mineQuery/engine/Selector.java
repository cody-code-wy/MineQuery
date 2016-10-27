package tech.unstable.mineQuery.engine;

import tech.unstable.mineQuery.jsHelers.events.JsEvent;

public class Selector{

  private final Selector nestedSelector;
  private final String selector;

  public Selector(String selector){
    String[] selectors = selector.split(" ", 2);
    this.selector = selectors[0];
    if (selectors.length > 1){
      this.nestedSelector = new Selector(selectors[1]);
    } else {
      this.nestedSelector = null;
    }
  }

  public boolean matches(JsEvent event){
    return event.evalSelector(selector);
  }

  public boolean eval(JsEvent event){
    if(matches(event)){
      if(nestedSelector != null){
        return nestedSelector.eval(event);
      } else {
        return true;
      }
    }
    return false;
  }

}
