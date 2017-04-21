hojo.provide("icallcenter.stateElement.ring.normalRing");

hojo.declare("icallcenter.stateElement.ring.normalRing", null, {
	constructor: function(base) {
		this._base = base;
	},
	
	//根状态
	_base: null,
	
	//状态名称
	_callState: "stDialing",
	
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
					if(evtJson.LinkedChannel.ChannelType == "dialout") {
						this._base._curCallState = this._base._getDialoutLink();
						this._changeToolBarState(this._base._curCallState);
					} else if(evtJson.LinkedChannel.ChannelType == "dialTransfer") {
						this._base._curCallState = this._base._getDialoutLink();
						this._changeToolBarState(this._base._curCallState);
					}

				} 
			}
		}
		
	},
	
	_publish:function() {
	}

});