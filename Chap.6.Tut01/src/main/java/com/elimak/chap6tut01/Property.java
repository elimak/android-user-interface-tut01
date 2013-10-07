package com.elimak.chap6tut01;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by elimak on 10/4/13.
 */
public class Property implements Parcelable{

    private static final String TAG = "Property";

    public static final String JSON_KEY_BATHROOMS   = "bathroomCount";
    public static final String JSON_KEY_BEDROOMS    = "bedroomCount";
    public static final String JSON_KEY_CITY        = "city";
    public static final String JSON_KEY_FOOTAGE     = "footage";
    public static final String JSON_KEY_PRICE       = "displayPrice";
    public static final String JSON_KEY_STATE       = "state";
    public static final String JSON_KEY_STREET_ADDRESS  = "address";

    private final float mBathroomCount;

    private final int mBedroomCount;

    private final String mCity;

    private final int mFootage;

    private final String mPrice;

    private final String mRawJson;

    private final String mState;

    private final String mStreetAddress;

    public Property(JSONObject json) throws JSONException{
        mBathroomCount = (float) json.getDouble(JSON_KEY_BATHROOMS);
        mBedroomCount = json.getInt(JSON_KEY_BEDROOMS);
        mCity = json.getString(JSON_KEY_CITY);
        mFootage = json.getInt(JSON_KEY_FOOTAGE);
        mPrice = json.getString(JSON_KEY_PRICE);
        mState = json.getString(JSON_KEY_STATE);
        mStreetAddress = json.getString(JSON_KEY_STREET_ADDRESS);
        mRawJson = json.toString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mRawJson);
    }

    public float getBathroomCount() {
        return mBathroomCount;
    }

    public int getBedroomCount() {
        return mBedroomCount;
    }

    public String getCity() {
        return mCity;
    }

    public int getFootage() {
        return mFootage;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getRawJson() {
        return mRawJson;
    }

    public String getState() {
        return mState;
    }

    public String getStreetAddress() {
        return mStreetAddress;
    }

    public static final Creator<Property> CREATOR = new Creator<Property>() {
        @Override
        public Property createFromParcel(Parcel source) {
            final String rawJson = source.readString();
            try{
                final JSONObject jsonObject = new JSONObject(rawJson);
                return new Property(jsonObject);
            }
            catch (JSONException e){
                Log.e(TAG, "Failed to create Property from Json String: " + e.getMessage());
                return null;
            }
        }

        // ??
        @Override
        public Property[] newArray(int size) {
            return new Property[size];
        }
    };
}
