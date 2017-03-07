hojo.provide("icallcenter.stateElement.hold");

hojo.declare("icallcenter.stateElement.hold", null, {
	constructor: function(base) {
		this._base = base;
	},
	
	//根状态
	_base: null,
	
	_changeState:function() {
	},
	
	_switchState:function(evtJson) {
		if(evtJson.Event == "ChannelStatus") {
			if(evtJson.ChannelStatus == "Hangup") {
				this._base._curCallState = this._base._getInvalid();
			} else if(evtJson.ChannelStatus == "Link") {
				if(evtJson.LinkedChannel.ChannelType == "normal") {
					this._base._curCallState = this._base._getNormalLink();
				} else if(evtJson.LinkedChannel.ChannelType == "consultation") {
					this._base._curCallState = this._base._getConsultationLink();
				} else if(evtJson.LinkedChannel.ChannelType == "normalInner") {
					this._base._curCallState = this._base._getNormalInnerLink();
				} else if(evtJson.LinkedChannel.ChannelType == "dialout") {
					this._base._curCallState = this._base._getDialoutLink();
				} else if(evtJson.LinkedChannel.ChannelType == "dialoutInner") {
					this._base._curCallState = this._base._getDialoutInnerLink();
				}
			}
		} 
	},


	_publish:function() {
	}

});