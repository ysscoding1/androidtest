package dnr.capitalone.com.dealandreward;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by RichardYan on 6/28/15.
 */
public class LocationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    MapView mMapView;
    private GoogleMap googleMap;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationFragment newInstance(String param1, String param2) {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    public LocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // latitude and longitude
        double latitude = 42.114525;
        double longitude = -88.036537;

        //Toast.makeText(getActivity(), "LOcation Frag", Toast.LENGTH_SHORT).show();

        // get lag ad latit
        final Geocoder geocoder = new Geocoder(getActivity());

        if (savedInstanceState!= null && savedInstanceState.containsKey("zipCode")) {
            String code = savedInstanceState.getString("zipCode");
            //  Toast.makeText(getActivity(), "ZipCode is:" + code, Toast.LENGTH_SHORT).show();

        }

        RestaurantCouponActivity restaurantCouponActivity = (RestaurantCouponActivity) getActivity();
        String zipCode = restaurantCouponActivity.getZipCode();
        if (zipCode == null)
            zipCode= "60173";


        //   Toast.makeText(getActivity(), "ZipCode:"+zipCode, Toast.LENGTH_SHORT).show();

        try {
            List<Address> addresses = geocoder.getFromLocationName(zipCode, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                // Use the address as needed
                String message = String.format("Latitude: %f, Longitude: %f",
                        address.getLatitude(), address.getLongitude());
                // Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                latitude = address.getLatitude();
                longitude = address.getLongitude();
            } else {
                // Display appropriate message when Geocoder services are not available
                Toast.makeText(getActivity(), "Unable to geocode zipcode", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            // handle exception
        }
        //}



        // Create Inner Thread Class
        //Thread background = new Thread(new UIMapProcess(inflater,zipCode, savedInstanceState, container));
        // Start Thread
        //background.start();  //After call start method thread called run Method


       /* Uri gmmIntentUri = Uri.parse("geo:0,0?q=restaurants");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivityForResult(mapIntent, 1);*/


        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_location, container,
                false);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();


        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title( zipCode +" Location");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
/*

        String SetServerString = "";
        try {
            String urlString = "http://52.5.81.122:8080/retreive/coupon/restuarent/"+60007;
            InputStream in = null;
            java.net.URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


            in = new BufferedInputStream(urlConnection.getInputStream());
            // convert inputstream to string
            if(in != null)
                SetServerString = convertInputStreamToString(in);
            else
                SetServerString = "Did not work!";

        } catch (Throwable t) {
            // just end the background thread
            Log.i("Animation", "Thread  exception " + t);
        }

        Log.d("SetServerString", SetServerString);
        if ((null != SetServerString)) {

                        */
/*Toast.makeText(
                                getBaseContext(),
                                "Server Response: "+aResponse,
                                Toast.LENGTH_SHORT).show();
                        Log.i("msg:", aResponse);*//*


            // Button button =(Button) findViewById(R.id.tgif);
            Type listType = new TypeToken<List<CouponDetails>>() {
            }.getType();
            ArrayList<CouponDetails> list = new Gson().fromJson(SetServerString, listType);
*/

            // adding marker
            googleMap.addMarker(marker);

           /* for (int i = 0; i < list.size(); i++) {
                Double lat = Double.valueOf(list.get(i).getLat()) == null ? 42.050123 : Double.valueOf(list.get(i).getLat());
                Double log = Double.valueOf(list.get(i).getLng())  == null ? -88.042236 : Double.valueOf(list.get(i).getLng()) ;
                googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,log)).title(list.get(i).getMerchant()).snippet(list.get(i).getMerchant()+ " \n "+ list.get(i).getAddress()));
            }
       }*/

        // googleMap.addMarker(new MarkerOptions().position(new LatLng(42.050123, -88.042236)).title("tgif").snippet("TGIF \n 1695 E Golf Rd, Schaumburg, IL 60173"));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        // Perform any camera updates here
        return v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            Uri dataValue = data.getData();
            Toast.makeText(getActivity(), dataValue.toString(),Toast.LENGTH_LONG);

        }
    }*/

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }


    /*public class UIMapProcess implements Runnable
    {
        private LayoutInflater inflater;
        private String zipCode;
        private Bundle savedInstanceState;
        private ViewGroup container;
        public UIMapProcess(LayoutInflater _inflater, String _zipCode, Bundle _savedInstance, ViewGroup _container) {
            this.inflater = _inflater;
            this.zipCode = _zipCode;
            savedInstanceState = _savedInstance;
            container = _container;
        }

        private String urlString = "http://52.5.81.122:8080/retreive/coupon/restuarent/"+zipCode;

        // After call for background.start this run method call
        public void run() {
            try {

                InputStream in = null;
                java.net.URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                String SetServerString = "";
                in = new BufferedInputStream(urlConnection.getInputStream());
                // convert inputstream to string
                if(in != null)
                    SetServerString = convertInputStreamToString(in);
                else
                    SetServerString = "Did not work!";
                threadMsg(SetServerString);

            } catch (Throwable t) {
                // just end the background thread
                Log.i("Animation", "Thread  exception " + t);
            }
        }

        private void threadMsg(String msg) {

            if (!msg.equals(null) && !msg.equals("")) {
                Message msgObj = handler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("message", msg);
                msgObj.setData(b);
                handler.sendMessage(msgObj);
            }
        }

        // Define the Handler that receives messages from the thread and update the progress
        private final Handler handler = new Handler() {

            public void handleMessage(Message msg) {

                String aResponse = msg.getData().getString("message");

                if ((null != aResponse)) {

                        *//*Toast.makeText(
                                getBaseContext(),
                                "Server Response: "+aResponse,
                                Toast.LENGTH_SHORT).show();
                        Log.i("msg:", aResponse);*//*

                    // Button button =(Button) findViewById(R.id.tgif);
                    Type listType = new TypeToken<List<CouponDetails>>() {
                    }.getType();
                    ArrayList<CouponDetails> list = new Gson().fromJson(aResponse, listType);

                    //LinearLayout[] lLayout = new LinearLayout[list.size()];
                    googleMap = mMapView.getMap();
                    View v = inflater.inflate(R.layout.fragment_location, container,
                            false);
                    for (int i = 0; i < list.size(); i++) {

                        mMapView = (MapView) v.findViewById(R.id.mapView);
                        mMapView.onCreate(savedInstanceState);

                        mMapView.onResume();// needed to get the map to display immediately

                        try {
                            MapsInitializer.initialize(getActivity().getApplicationContext());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                            // create marker
                            MarkerOptions marker = new MarkerOptions().position(
                                    new LatLng(Double.valueOf(list.get(i).getLat()), Double.valueOf(list.get(i).getLng()))).title(zipCode + " Location");

                            // Changing marker icon
                            marker.icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
*//*
                        // adding marker
                        //                          googleMap.addMarker(marker);

                        Double lat = Double.valueOf(list.get(i).getLat()) == null ? 42.050123 : Double.valueOf(list.get(i).getLat());
                        Double log = Double.valueOf(list.get(i).getLng())  == null ? -88.042236 : Double.valueOf(list.get(i).getLng()) ;
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(list.get(i).getLat()), Double.valueOf(list.get(i).getLng()))).title(list.get(i).getMerchant()).snippet(list.get(i).getMerchant()+ " \n "+ list.get(i).getAddress()));

                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(new LatLng(Double.valueOf(list.get(i).getLat()), Double.valueOf(list.get(i).getLng()))).zoom(12).build();
                        googleMap.animateCamera(CameraUpdateFactory
                                .newCameraPosition(cameraPosition));

                    }
                }
            }
        };
    }*/
}
