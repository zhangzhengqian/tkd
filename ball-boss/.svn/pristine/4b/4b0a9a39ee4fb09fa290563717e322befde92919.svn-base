hojo.provide("icallcenter.stateElement.ring.innerRing");

hojo.declare("icallcenter.stateElement.ring.innerRing", null, {
	constructor: function(base) {
		this._base = base;
	},
	
	//根状态
	_base: null,
	
	//状态名称
	_callState: "stInnerDialing",
	
	_changeToolBarState: function(obj) {
		hojo.publish("EvtCallToolBarChange",[obj._callState]);
	},
	
	_switchCallState: function(evtJson) {
		if(evtJson.Event == "ChannelStatus") {
			if(evtJson.Exten == this._base._phone.sipNo) {
				if(evtJson.ChannelStatus == "Hangup") {
					this._base._curCallState = this._base._getInvalid();
					this._changeToolBarState(this._base._curCallState);
				} else if(evtJson.ChannelStatus == "Link") {
					if(evtJson.LinkedChannel.ChannelType == "inner") {
						this._base._curCallState = this._base._getInnerLink();
						this._changeToolBarState(this._base._curCallState);
					} 
				}
			}
		}
	},


	_publish:function() {
	}

});