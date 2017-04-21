hojo.provide("icallcenter.stateElement.link.threeWayCallLink");

hojo.declare("icallcenter.stateElement.link.threeWayCallLink", null, {
	constructor: function(base) {
		this._base = base;
	},
	
	//根状态
	_base: null,
	
	//状态名称
	_callState: "stThreeWayTalking",
	
	_changeToolBarState:function(obj) {
		hojo.publish("EvtCallToolBarChange",[obj._callState]);
	},
	
	_switchCallState:function(evtJson) {
		if(evtJson.Event == "ChannelStatus") {
			if(evtJson.Exten == this._base._phone.sipNo) {
				if(evtJson.ChannelStatus == "Hangup") {
					this._base._curCallState = this._base._getInvalid();
					this._changeToolBarState(this._base._curCallState);
				} 
			}
		}
	},

	_publish:function() {
	}

});