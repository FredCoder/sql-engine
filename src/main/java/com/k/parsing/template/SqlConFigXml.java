package com.k.parsing.template;

import java.util.ArrayList;
import java.util.List;

import com.k.parsing.tag.CommTag;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 
 *
 */
@XStreamAlias(value="sqlXml")
public class SqlConFigXml {
	//去掉集合节点
	@XStreamImplicit 
	private List<CommTag> tags;



   public void addTag(CommTag tag){
	   if(tags==null){
		   tags=new ArrayList<CommTag>(0);
	   }
	   tags.add(tag);
   }
}
