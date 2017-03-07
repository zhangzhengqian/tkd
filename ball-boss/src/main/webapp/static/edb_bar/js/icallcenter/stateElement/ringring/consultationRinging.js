hojo.provide("icallcenter.stateElement.ringring.consultationRinging");

hojo.declare("icallcenter.stateElement.ringring.consultationRinging", null, {
	constructor: function(base) {
		this._base = base;
	},

	//根状态
	_base: null,
	
	_changeState: function() {
	},
	
	_switchState: function(evtJson) {
		if(evtJson.Event == "ChannelStatus") {
			if(evtJson.ChannelStatus == "Hangup") {
				this._base._curCallState = this._base._getInvalid();
			} else if(evtJson.ChannelStatus == "Link") {
				if(evtJson.LinkedChannel.ChannelType == "consultation") {
					this._base._curCallState = this._base._getConsultationLink();
				} 
			}
		}
	},


	_publish:function() {
	}

});