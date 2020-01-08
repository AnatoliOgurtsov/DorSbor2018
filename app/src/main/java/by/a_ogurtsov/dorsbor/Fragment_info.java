package by.a_ogurtsov.dorsbor;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_info extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button bt_OK;
    OnClickListener onClickListener;
    private String mTitle;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_info.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_info newInstance(String param1, String param2) {
        Fragment_info fragment = new Fragment_info();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment_info() {
        // Required empty public constructor
    }

    public static interface Ok_clickListener{
        void onNavigationDrawerItemSelected(String name);
    }
    Ok_clickListener ok_clickListener;

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
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.info, container, false);
        bt_OK = (Button)v.findViewById(R.id.button);
        onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                ok_clickListener.onNavigationDrawerItemSelected((((Button) v).getTag()).toString());
            }
        };

        bt_OK.setOnClickListener(onClickListener);



        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            ok_clickListener = (Ok_clickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

}
