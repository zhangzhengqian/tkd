hojo.provide("icallcenter.stateElement.link.innerLink");

hojo.declare("icallcenter.stateElement.link.innerLink", null, {
	constructor: function(base) {
		this._base = base;
	},
	
	//根状态
	_base: null,
	
	//状态名称
	_callState: "stInnerTalking",
	
	_changeToolBarState:function(obj) {
		hojo.publish("EvtCallToolBarChange",[obj._callState]);
	},
	
	_switchCallState:function(evtJson) {
		if(evtJson.Event == "ChannelStatus") {
			if(evtJson.Exten == this._base._phone.sipNo) {
				if(evtJson.ChannelStatus == "Hangup") {
					this._base._curCallState = this._base._getInvalid();
					this._changeToolBarState(this._base._curCallState);
				} else if(evtJson.ChannelStatus == "Link") {
					if(evtJson.LinkedChannel.ChannelType == "consultation") {
						this._base._curCallState = this._base._getConsultationLink();
						this._changeToolBarState(this._base._curCallState);
					} 
					//这个版本用的
					if(evtJson.LinkedChannel.ChannelType == "transfer") {
						//alert("transfer");
					}

				} else if(evtJson.ChannelStatus == "hold") {
					this._base._curCallState = this._base._getHold();
					this._changeToolBarState(this._base._curCallState);
				} 
			}
		}
	},

	_publish:function() {
	}

});