package com.k.parsing.command;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 
 * <p>Title:Foreach</p>
 * <p>Description:   Foreach 指令   </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: XXXX</p>
 * <p>Date:2015年2月11日</p>
 * @author CHEN_JIAN
 * @version 1.0
 */
@XStreamAlias(value="foreach")
//@XStreamConverter(value=ToAttributedValueConverter.class, strings={"text"})
public class Foreach {
    
  
  @XStreamAsAttribute
  private String collection;
  @XStreamAsAttribute
  private String open;
  @XStreamAsAttribute
  private String separator;
  @XStreamAsAttribute
  private String close;
  @XStreamAsAttribute
  private String text;
	
}
