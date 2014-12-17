function WoobiPlugin() {}

SupersonicPlugin.prototype.initialize = function (appKey, userId, success, error) {
	cordova.exec(success, error, 'Woobi', 'initialize', [appKey, userId]);
};

SupersonicPlugin.prototype.showOfferwall = function (success, error) {
	cordova.exec(success, error, 'Woobi', 'showOfferwall', []);
};

SupersonicPlugin.prototype.showRewardedVideo = function (success, error) {
	cordova.exec(success, error, 'Woobi', 'showRewardedVideo', []);
};

module.exports = new WoobiPlugin();