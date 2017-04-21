hojo.provide("icallcenter.stateElement.abate");

hojo.declare("icallcenter.stateElement.abate", null, {
	constructor: function(base) {
		this._base = base;
	},
	
	//根状态
	_base: null,
	
	//状态名称
	_callState: "stAbate",
	
	_changeToolBarState: function(obj) {
		hojo.publish("EvtCallToolBarChange",[obj._callState]);
	},
	
	_switchCallState:function(evtJson) {
		if(evtJson.Event == "PeerStatus") {
			if(evtJson.Exten == this._base._phone.sipNo) {
				var isRegistered = false;
        		if(evtJson.PeerStatus == "Registered") {
        			isRegistered = true;
        		}
        		if (isRegistered && this._base._curCallState._callState == "stAbate"){
        			this._base._curCallState = this._base._getInvalid();
					this._changeToolBarState(this._base._curCallState);
        		}
			}
		}	
	},


	_publish:function() {
	}

});