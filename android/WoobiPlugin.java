package uk.mondosports.plugins.woobi;

import android.util.Log;
import android.widget.Toast;

import com.woobi.Woobi;
import com.woobi.WoobiCountListener;
import com.woobi.WoobiError;
import com.woobi.WoobiEventListener;
import com.woobi.WoobiGetPointsListener;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WoobiPlugin extends CordovaPlugin implements 
    WoobiEventListener {

    private static final String LOGTAG = "WoobiPlugin";
    private static final String DEFAULT_APP_KEY = "5015";
    private static final boolean VERBOSE = true;

    private static final String ACTION_INITIALIZE = "initialize";
    private static final String ACTION_SHOW_OFFERWALL = "showOfferwall";
    private static final String ACTION_SHOW_REWARDEDVIDEO = "showRewardedVideo";
    private static final String OPT_APPLICATION_KEY = "appKey";
    private static final String OPT_USER_ID = "userId";

    private String appKey = DEFAULT_APP_KEY;
    private String userId = "5043b715c3bd823b760000ff";

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
        if(options.has(OPT_USER_ID)) this.userId = options.optString( OPT_USER_ID );

        Woobi.staging = false;
        Woobi.verbose = VERBOSE;
        Woobi.init(this.webView.getContext(), this);
    }

    private PluginResult executeShowOfferwall(JSONObject options, CallbackContext callbackContext) {
        Log.w(LOGTAG, "executeShowOfferwall");
        
        this.showOfferWall( options );
        
        callbackContext.success();

        return null;
    }

    private void showOfferWall(JSONObject options) {
        Woobi.showOffers(this.cordova.getActivity(), this.appKey, this.userId);
    }
    
    private PluginResult executeShowRewardedVideo(JSONObject options, CallbackContext callbackContext) {
        Log.w(LOGTAG, "executeShowRewardedVideo");
        
        this.showRewardedVideo( );
        
        callbackContext.success();

        return null;
    }

    private void showRewardedVideo() {
        //ssaPub.showRewardedVideo();
    }

    @Override
    public void onInitialized() {
        Toast.makeText(this.webView.getContext(), "Initialized Woobi", Toast.LENGTH_SHORT).show();
        
    }
    @Override
    public void onError(WoobiError error) {
        Toast.makeText(this.webView.getContext(), error.toString(), Toast.LENGTH_SHORT).show();
        
    }
    @Override
    public void onShowOffers() {
        Toast.makeText(this.webView.getContext(), "onShowOffers", Toast.LENGTH_SHORT).show();
        
    }
    @Override
    public void onCloseOffers() {
        Toast.makeText(this.webView.getContext(), "onCloseOffers", Toast.LENGTH_SHORT).show();
        
    }
    @Override
    public void onShowPopup() {
        Toast.makeText(this.webView.getContext(), "onShowPopup", Toast.LENGTH_SHORT).show();
        
    }
    @Override
    public void onClosePopup() {
        Toast.makeText(this.webView.getContext(), "onClosePopup", Toast.LENGTH_SHORT).show();
        
    }
}