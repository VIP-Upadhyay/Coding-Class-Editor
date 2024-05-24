package com.vip.CodingClassEditorStudent.model;

public class TreeFileModel {
	
	String name;
	String id;
	String type;
	long noOfFiles;
	String prettyFileSize;
	
	public TreeFileModel(String name, String id,String type,long noOfFiles) {
		super();
		this.name = name;
		this.id = id;
		this.type = type;
		this.noOfFiles = noOfFiles;
	}
	public TreeFileModel(String name, String id,String type,String prettyFileSize) {
		super();
		this.name = name;
		this.id = id;
		this.type = type;
		this.prettyFileSize =prettyFileSize;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public long getNoOfFiles() {
		return noOfFiles;
	}
	public void setNoOfFiles(long noOfFiles) {
		this.noOfFiles = noOfFiles;
	}
	public String getPrettyFileSize() {
		return prettyFileSize;
	}
	public void setPrettyFileSize(String prettyFileSize) {
		this.prettyFileSize = prettyFileSize;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (this.type=="FOLDER") {
			return getName() + " ( " + this.noOfFiles + " items )";
		}else {
			if (this.type=="FILE") {
				return getName() + " ( " + this.prettyFileSize + " )";
			}
		}
		return getName();
	}
}
