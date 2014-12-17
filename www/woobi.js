function WoobiPlugin() {}

WoobiPlugin.prototype.initialize = function (appKey, userId, success, error) {
	cordova.exec(success, error, 'Woobi', 'initialize', [appKey, userId]);
};

WoobiPlugin.prototype.showOfferwall = function (success, error) {
	cordova.exec(success, error, 'Woobi', 'showOfferwall', []);
};

WoobiPlugin.prototype.showRewardedVideo = function (success, error) {
	cordova.exec(success, error, 'Woobi', 'showRewardedVideo', []);
};

module.exports = new WoobiPlugin();