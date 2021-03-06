package jp.yokomark.remoteview.reader.action;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jp.yokomark.remoteview.reader.ActionMap;

/**
 * @author KeishinYokomaku
 */
public class SetPendingIntentTemplateAction extends RemoteViewsAction {
    public static final String TAG = SetPendingIntentTemplateAction.class.getSimpleName();
    private final PendingIntent pendingIntent;

    public SetPendingIntentTemplateAction(int viewId, PendingIntent pendingIntent) {
        super(ActionMap.SET_PENDING_INTENT_TEMPLATE.getTag(), viewId);
        this.pendingIntent = pendingIntent;
    }

    @Override
    public String getActionName() {
        return "SetPendingIntentTemplateAction";
    }

    public PendingIntent getPendingIntent() {
        return pendingIntent;
    }

    public Intent getContentIntent() {
        try {
            Method getIntent = pendingIntent.getClass().getDeclaredMethod("getIntent", null);
            getIntent.setAccessible(true);
            return (Intent) getIntent.invoke(pendingIntent, null);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "", e);
        } catch (InvocationTargetException e) {
            Log.e(TAG, "", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "", e);
        }
        return null;
    }
}
