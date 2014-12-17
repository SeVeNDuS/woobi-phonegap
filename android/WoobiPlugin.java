package uk.mondosports.plugins.woobi;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.woobi.Woobi;
import com.woobi.WoobiCountListener;
import com.woobi.WoobiEventListener;
import com.woobi.WoobiGetPointsListener;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WoobiPlugin extends CordovaPlugin  {

    private static final String LOGTAG = "WoobiPlugin";
    private static final String DEFAULT_APP_KEY = "32c9d67d";
    private static final boolean VERBOSE = true;

    private static final String ACTION_INITIALIZE = "initialize";
    private static final String ACTION_SHOW_OFFERWALL = "showOfferwall";
    private static final String ACTION_SHOW_REWARDEDVIDEO = "showRewardedVideo";
    private static final String OPT_APPLICATION_KEY = "appKey";
    private static final String OPT_APPLICATION_SECRET = "secretKey";
    private static final String OPT_USER_ID = "userId";

    private String appKey = DEFAULT_APP_KEY;
    private String secretKey = "5043b715c3bd823b760000ff";
    private String userId = "5043b715c3bd823b760000ff";
    private SSAPublisher ssaPub; 

    private WoobiEventListener woobiEventlistener = new MyEventListener(this);
    private WoobiCountListener woobiCountListener = new MyCountListener(this);
    private WoobiGetPointsListener woobigetPointsListener = new MyGetPointsListener(this);

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        PluginResult result = null;
        
        if (ACTION_INITIALIZE.equals(action)) {
            JSONObject options = args.optJSONObject(0);
            result = executeInitialize(options, callbackContext);
        } else if (ACTION_SHOW_OFFERWALL.equals(action)) {
            JSONObject options = args.optJSONObject(0);
            result = executeShowOfferwall(options, callbackContext);
        } else if (ACTION_SHOW_REWARDEDVIDEO.equals(action)) {
            JSONObject options = args.optJSONObject(0);
            result = executeShowRewardedVideo(options, callbackContext);
        }

        if (result != null) callbackContext.sendPluginResult( result );

        return true;
    }

    private PluginResult executeInitialize(JSONObject options, CallbackContext callbackContext) {
        Log.w(LOGTAG, "executeInitialize");
        
        this.initialize( options );
        
        callbackContext.success();

        return null;
    }

    private void initialize( JSONObject options ) {
        if(options == null) return;
        
        if(options.has(OPT_APPLICATION_KEY)) this.appKey = options.optString( OPT_APPLICATION_KEY );
        if(options.has(OPT_APPLICATION_SECRET)) this.secretKey = options.optString( OPT_APPLICATION_SECRET );
        if(options.has(OPT_USER_ID)) this.userId = options.optString( OPT_USER_ID );

        Woobi.staging = false;
        Woobi.verbose = VERBOSE;
        Woobi.init(this, woobiEventlistener);
    }

    private PluginResult executeShowOfferwall(JSONObject options, CallbackContext callbackContext) {
        Log.w(LOGTAG, "executeShowOfferwall");
        
        this.showOfferWall( options );
        
        callbackContext.success();

        return null;
    }

    private void showOfferWall(JSONObject options) {
        Woobi.showOffers(this, this.appKey, this.userId);
    }
    
    private PluginResult executeShowRewardedVideo(JSONObject options, CallbackContext callbackContext) {
        Log.w(LOGTAG, "executeShowRewardedVideo");
        
        this.showRewardedVideo( );
        
        callbackContext.success();

        return null;
    }

    private void showRewardedVideo() {
        ssaPub.showRewardedVideo();
    }
}