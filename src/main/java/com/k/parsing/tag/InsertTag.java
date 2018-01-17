package com.k.parsing.tag;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 
 * @author Fren
 *
 */
@XStreamAlias(value = "insert")
public class InsertTag extends CommTag {
	@XStreamAsAttribute
	private String sequence;
	@XStreamAsAttribute
	private String primaryKey;

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

}
