package classes;

import java.util.List;

public class UniqueValueStyle {
	private static final long serialVersionUID = 1L;
	private String type;
	private String field1;
	private String field2;
	private String field3;
	private String fieldDelimiter;
	private DefaultSymbol defaultSymbol;
	private String defaultLabel;
	private List<UniqueValueInfo> uniqueValueInfos;
	
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getField1() {
		return this.field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	public String getField2() {
		return this.field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	public String getField3() {
		return this.field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	public String getFieldDelimiter() {
		return this.fieldDelimiter;
	}

	public void setFieldDelimiter(String fieldDelimiter) {
		this.fieldDelimiter = fieldDelimiter;
	}
	
	public DefaultSymbol getDefaultSymbol() {
		return this.defaultSymbol;
	}

	public void setDefaultSymbol(DefaultSymbol defaultSymbol) {
		this.defaultSymbol = defaultSymbol;
	}
	
	public String getDefaultLabel() {
		return this.defaultLabel;
	}

	public void setDefaultLabel(String defaultLabel) {
		this.defaultLabel = defaultLabel;
	}
	
	public List<UniqueValueInfo> getUniqueValueInfos() {
		return this.uniqueValueInfos;
	}

	public void setUniqueValueInfos(List<UniqueValueInfo> uniqueValueInfos) {
		this.uniqueValueInfos = uniqueValueInfos;
	}
	
}
