hojo.provide("icallcenter.stateElement.ring.listenRing");

hojo.declare("icallcenter.stateElement.ring.listenRing", null, {
	constructor: function(base) {
		this._base = base;
	},
	
	//根状态
	_base: null,
	
	//状态名称
	_callState: "stListening",
	
	_changeToolBarState: function(obj) {
		hojo.publish("EvtCallToolBarChange",[obj._callState]);
	},
	
	_switchCallState: function(evtJson) {
		if(evtJson.Event == "ChannelStatus") {
			if(evtJson.Exten == this._base._phone.sipNo) {
				if(evtJson.ChannelStatus == "Hangup") {
					this._base._curCallState = this._base._getInvalid();
					this._changeToolBarState(this._base._curCallState);
					hojo.publish("EvtEndListen", []);
				} else if(evtJson.ChannelStatus == "Up") {
					if(evtJson.ChannelType == "listen") {
						this._base._curCallState = this._base._getListenLink();
						this._changeToolBarState(this._base._curCallState);
					} 
				}
			}
		}
	},

	_publish:function() {
	}

});