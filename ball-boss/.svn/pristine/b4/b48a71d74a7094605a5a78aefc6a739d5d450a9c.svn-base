hojo.provide("icallcenter.stateElement.ringring.normalRinging");

hojo.declare("icallcenter.stateElement.ringring.normalRinging", null, {
	constructor: function(base) {
		this._base = base;
	},
	
	//根状态
	_base: null,
	
	//状态名称
	_callState: "stBelling",
	
	_changeToolBarState: function(obj) {
		hojo.publish("EvtCallToolBarChange",[obj._callState]);
	},
	
	_switchCallState: function(evtJson) {
		if(evtJson.Event == "ChannelStatus") {
			if(evtJson.Exten == this._base._phone.sipNo) {
				if(evtJson.ChannelStatus == "Hangup") {
					this._base._curCallState = this._base._getInvalid();
					this._changeToolBarState(this._base._curCallState);
					if(this._base._phone._isRing) {
						this._base._phone.stopSound();
						this._base._phone._isRing = false;
					}
				} else if(evtJson.ChannelStatus == "Link") {
					if(evtJson.LinkedChannel.ChannelType == "normal") {
						this._base._curCallState = this._base._getNormalLink();
						this._changeToolBarState(this._base._curCallState);
						if(this._base._phone._isRing) {
							this._base._phone.stopSound();
							this._base._phone._isRing = false;
						}
					} else if(evtJson.LinkedChannel.ChannelType == "threeWayCall") {
						this._base._curCallState = this._base._getThreeWayCallLink();
						this._changeToolBarState(this._base._curCallState);
						if(this._base._phone._isRing) {
							this._base._phone.stopSound();
							this._base._phone._isRing = false;
						}
					}
					
					//旧版本用
					else if(evtJson.LinkedChannel.ChannelType == "transfer") {
						this._base._curCallState = this._base._getNormalLink();
						this._changeToolBarState(this._base._curCallState);
						if(this._base._phone._isRing) {
							this._base._phone.stopSound();
							this._base._phone._isRing = false;
						}
					}
					
				}
			}
		} 
	},

	_publish:function() {
	}

});