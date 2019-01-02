package com.wi4solutions.asterisk;

import java.util.List;

import com.wi4solutions.domain.ActiveCall;

public class ActiveCallsReponse {
	
	List<ActiveCall> activeCalls;
	
	Integer activeCallsQty;
	
	Integer activeChannelQty;

	public List<ActiveCall> getActiveCalls() {
		return activeCalls;
	}

	public void setActiveCalls(List<ActiveCall> activeCalls) {
		this.activeCalls = activeCalls;
	}

	public Integer getActiveCallsQty() {
		return activeCallsQty;
	}

	public void setActiveCallsQty(Integer activeCallsQty) {
		this.activeCallsQty = activeCallsQty;
	}

	public Integer getActiveChannelQty() {
		return activeChannelQty;
	}

	public void setActiveChannelQty(Integer activeChannelQty) {
		this.activeChannelQty = activeChannelQty;
	}

	
}
