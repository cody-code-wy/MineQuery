package tech.unstable.mineQuery.jsHelers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class JsConsole {

  final Logger logger;
  final String scriptName;
  final Level logLevel = Level.WARN; // TODO allow scripts to change this

  public JsConsole(Logger logger, String scriptName){
    this.logger = logger;
    this.scriptName = scriptName;
  }

  public void log(){
    log("");
  }

  public void log(Object o){
    logger.log(logLevel, scriptName + ": " + o.toString());
  }

}
