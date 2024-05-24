package com.vip.CodingClassEditorTeacher.utils;

import com.vip.CodingClassEditorTeacher.model.Master;

public final class MasterHolder {
	private Master master;
	private final static MasterHolder INSTANCE_HOLDER = new MasterHolder();
	
	public static MasterHolder getInstanceHolder() {
		return INSTANCE_HOLDER;
	}

	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}	
	
}
