hojo.provide("icallcenter.stateElement.ringring.innerRinging");

hojo.declare("icallcenter.stateElement.ringring.innerRinging", null, {
	constructor: function(base) {
		this._base = base;
	},
	
	//根状态
	_base: null,
	
	//状态名称
	_callState: "stInnerBelling",
	
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
					if(evtJson.LinkedChannel.ChannelType == "threeWayCall") {
						this._base._curCallState = this._base._getThreeWayCallLink();
						this._changeToolBarState(this._base._curCallState);
						if(this._base._phone._isRing) {
							this._base._phone.stopSound();
							this._base._phone._isRing = false;
						}
					} else if(evtJson.LinkedChannel.ChannelType == "inner") {
						this._base._curCallState = this._base._getInnerLink();
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