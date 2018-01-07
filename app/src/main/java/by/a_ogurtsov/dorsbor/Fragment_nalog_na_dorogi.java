package by.a_ogurtsov.dorsbor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import by.a_ogurtsov.MyClass;

public class Fragment_nalog_na_dorogi extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int FIS_UYR, vid_TS, massa_TS, vozrast_TS, pens;


    final Double bazovaya_vel = 24.5;
    SharedPreferences spref;

    Integer b_v;
    String itog_sum, itog_b;
    final String LOG_TAG = "myLogs";
    private Spinner spinner_TS, spinner_massa;
    MySimpleAdapter adapter_temp, adapter_tip_auto, adapter_mas_legk_fiz, adapter_mas_legk_uyr, adapter_mas_gruz, adapter_mas_bus, adapter_mas_pritcep;
    RadioGroup rgroup_fiz_uyr; //rgroup_vozrast;
    TextView itog, itog_baz, fiz, uyr, poloska_fiz, poloska_uyr, do10, posle10, poloska_do10, poloska_posle10, baz_vel;
    CheckBox cbox_pensioner;
    OnClickListener radiolistener;
    OnCheckedChangeListener pensionerlistener;
    final String ATTRIBUTE_TIP_INFO = "tip_info";
    final String ATTRIBUTE_TIP_IMAGE = "tip_image";


    public static Fragment_nalog_na_dorogi newInstance(int param1, int param2) {
        Fragment_nalog_na_dorogi fragment = new Fragment_nalog_na_dorogi();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }
    public Fragment_nalog_na_dorogi() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate_Fragment_nalog_na_dorogi");

        if (savedInstanceState != null){
            FIS_UYR = savedInstanceState.getInt("FIS_UYR");
            vid_TS = savedInstanceState.getInt("VID_TS");
            massa_TS = savedInstanceState.getInt("MASSA_TS");
            vozrast_TS = savedInstanceState.getInt("VOZRAST_TS");
            pens = savedInstanceState.getInt("PENS");

           }
        else {
                FIS_UYR = 0;
                vid_TS = 0;
                massa_TS = 0;
                vozrast_TS = 1;
                pens = 1;
        }
        Log.d(LOG_TAG, "nnd" + FIS_UYR);
        Log.d(LOG_TAG, "nnd" + vid_TS);
        Log.d(LOG_TAG, "nnd" + massa_TS);
        Log.d(LOG_TAG, "nnd" + vozrast_TS);
        Log.d(LOG_TAG, "nnd" + pens);
        //======================================================
        //======================================================
        // данные для спиннеров
        //======================================================
        //======================================================
        // данные для спиннера тип_авто
        String[] tip_Auto = getResources().getStringArray(R.array.tip_auto);
        int[] tip_image = {R.drawable.ic_legk_car, R.drawable.ic_gruz_5_12, R.drawable.ic_bus, R.drawable.ic_pritcep,R.drawable.ic_moto, };
        ArrayList<Map<String, Object>> tip_Auto_data = new ArrayList<Map<String, Object>>(tip_Auto.length);
        Map<String, Object> m;
        for (int i = 0; i < tip_Auto.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_TIP_INFO, tip_Auto[i]);
            m.put(ATTRIBUTE_TIP_IMAGE, tip_image[i]);
            tip_Auto_data.add(m);
        }
        //====================================
        String[] from = {ATTRIBUTE_TIP_INFO, ATTRIBUTE_TIP_IMAGE};
        int[] to = {R.id.tv_tip_info, R.id.tv_tip_image};
        adapter_tip_auto = new MySimpleAdapter(this.getActivity(), tip_Auto_data, R.layout.spinner_item_nnd_category, from, to);
        //====================================
        // конец данные для спиннера тип_авто

        // данные для спиннера выбор массы физ легк
        String[] mas_leg_fiz = getResources().getStringArray(R.array.legkovoi_massa_fiz);
        int[] mas_leg_fiz_image = {R.drawable.ic_krug1, R.drawable.ic_krug2, R.drawable.ic_krug3, R.drawable.ic_krug4,};
        ArrayList<Map<String, Object>> mas_leg_fiz_data = new ArrayList<Map<String, Object>>(mas_leg_fiz.length);
        Map<String, Object> m1;
        for (int i = 0; i < mas_leg_fiz.length; i++) {
            m1 = new HashMap<String, Object>();
            m1.put(ATTRIBUTE_TIP_INFO, mas_leg_fiz[i]);
            m1.put(ATTRIBUTE_TIP_IMAGE, mas_leg_fiz_image[i]);
            mas_leg_fiz_data.add(m1);
        }
        //====================================
        String[] from1 = {ATTRIBUTE_TIP_INFO, ATTRIBUTE_TIP_IMAGE};
        int[] to1 = {R.id.tv_tip_info, R.id.tv_tip_image};
        adapter_mas_legk_fiz = new MySimpleAdapter(this.getActivity(), mas_leg_fiz_data, R.layout.spinner_item_nnd_category, from1, to1);
        //====================================
        // конец данные для спиннера выбор массы физ легк

        // данные для спиннера выбор массы юр легк
        String[] mas_leg_uyr = getResources().getStringArray(R.array.legkovoi_massa_uyr);
        int[] mas_leg_uyr_image = {R.drawable.ic_krug1, R.drawable.ic_krug2, R.drawable.ic_krug3, R.drawable.ic_krug4,};
        ArrayList<Map<String, Object>> mas_leg_uyr_data = new ArrayList<Map<String, Object>>(mas_leg_uyr.length);
        Map<String, Object> m2;
        for (int i = 0; i < mas_leg_uyr.length; i++) {
            m2 = new HashMap<String, Object>();
            m2.put(ATTRIBUTE_TIP_INFO, mas_leg_uyr[i]);
            m2.put(ATTRIBUTE_TIP_IMAGE, mas_leg_uyr_image[i]);
            mas_leg_uyr_data.add(m2);
        }
        //====================================
        String[] from2 = {ATTRIBUTE_TIP_INFO, ATTRIBUTE_TIP_IMAGE};
        int[] to2 = {R.id.tv_tip_info, R.id.tv_tip_image};
        adapter_mas_legk_uyr = new MySimpleAdapter(this.getActivity(), mas_leg_uyr_data, R.layout.spinner_item_nnd_category, from2, to2);
        //====================================
        // конец данные для спиннера выбор массы юр легк

        // данные для спиннера выбор массы груз
        String[] mas_grus = getResources().getStringArray(R.array.gruzovoi_massa);
        int[] mas_grus_image = {R.drawable.ic_krug1, R.drawable.ic_krug2, R.drawable.ic_krug3, R.drawable.ic_krug4,};
        ArrayList<Map<String, Object>> mas_grus_data = new ArrayList<Map<String, Object>>(mas_grus.length);
        Map<String, Object> m3;
        for (int i = 0; i < mas_grus.length; i++) {
            m3 = new HashMap<String, Object>();
            m3.put(ATTRIBUTE_TIP_INFO, mas_grus[i]);
            m3.put(ATTRIBUTE_TIP_IMAGE, mas_grus_image[i]);
            mas_grus_data.add(m3);
        }
        //====================================
        String[] from3 = {ATTRIBUTE_TIP_INFO, ATTRIBUTE_TIP_IMAGE};
        int[] to3 = {R.id.tv_tip_info, R.id.tv_tip_image};
        adapter_mas_gruz = new MySimpleAdapter(this.getActivity(), mas_grus_data, R.layout.spinner_item_nnd_category, from3, to3);
        //====================================
        // конец данные для спиннера выбор массы груз

        // данные для спиннера выбор  автобус
        String[] mas_bus = getResources().getStringArray(R.array.bus);
        int[] mas_bus_image = {R.drawable.ic_krug1, R.drawable.ic_krug2, R.drawable.ic_krug3};
        ArrayList<Map<String, Object>> mas_bus_data = new ArrayList<Map<String, Object>>(mas_bus.length);
        Map<String, Object> m4;
        for (int i = 0; i < mas_bus.length; i++) {
            m4 = new HashMap<String, Object>();
            m4.put(ATTRIBUTE_TIP_INFO, mas_bus[i]);
            m4.put(ATTRIBUTE_TIP_IMAGE, mas_bus_image[i]);
            mas_bus_data.add(m4);
        }
        //====================================
        String[] from4 = {ATTRIBUTE_TIP_INFO, ATTRIBUTE_TIP_IMAGE};
        int[] to4 = {R.id.tv_tip_info, R.id.tv_tip_image};
        adapter_mas_bus = new MySimpleAdapter(this.getActivity(), mas_bus_data, R.layout.spinner_item_nnd_category, from4, to4);
        //====================================
        // конец данные для спиннера выбор  автобус

        // данные для спиннера выбор прицеп
        String[] mas_pritcep = getResources().getStringArray(R.array.pritcep);
        int[] mas_pritcep_image = {R.drawable.ic_krug1, R.drawable.ic_krug2, R.drawable.ic_pritcep_dacha};
        ArrayList<Map<String, Object>> mas_pritcep_data = new ArrayList<Map<String, Object>>(mas_pritcep.length);
        Map<String, Object> m5;
        for (int i = 0; i < mas_pritcep.length; i++) {
            m5 = new HashMap<String, Object>();
            m5.put(ATTRIBUTE_TIP_INFO, mas_pritcep[i]);
            m5.put(ATTRIBUTE_TIP_IMAGE, mas_pritcep_image[i]);
            mas_pritcep_data.add(m5);
        }
        //====================================
        String[] from5 = {ATTRIBUTE_TIP_INFO, ATTRIBUTE_TIP_IMAGE};
        int[] to5 = {R.id.tv_tip_info, R.id.tv_tip_image};
        adapter_mas_pritcep = new MySimpleAdapter(this.getActivity(), mas_pritcep_data, R.layout.spinner_item_nnd_category, from5, to5);
        //====================================
        // конец данные для спиннера выбор массы прицеп


        //======================================================
        //======================================================
        // конец данные для спиннеров
        //======================================================
        //======================================================

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView_Fragment_nalog_na_dorogi");
        View v = inflater.inflate(R.layout.fragment_nalog_na_dorogi, container,false);
        baz_vel = (TextView)v.findViewById(R.id.bazovaya_vel);
        baz_vel.setText(MyClass.check_ruble(bazovaya_vel));



        spinner_TS = (Spinner) v.findViewById(R.id.spinner_tip_auto);
        spinner_TS.setAdapter(adapter_tip_auto);
        spinner_massa = (Spinner) v.findViewById(R.id.spinner2);



        itog = (TextView) v.findViewById(R.id.tv_rezult);
        itog_baz = (TextView) v.findViewById(R.id.tv_rezult_baz);


        rgroup_fiz_uyr = (RadioGroup) v.findViewById(R.id.radioGroup_FizL_UrL);
        fiz = (TextView) v.findViewById(R.id.tv_FizL);
        uyr = (TextView) v.findViewById(R.id.tv_UrL);
        poloska_fiz = (TextView) v.findViewById(R.id.poloska_fiz);
        poloska_uyr = (TextView) v.findViewById(R.id.poloska_uyr);

    //    rgroup_vozrast = (RadioGroup) v.findViewById(R.id.radioGroup_Vozrast);
        do10 = (TextView) v.findViewById(R.id.tv_do10);
        posle10 = (TextView) v.findViewById(R.id.tv_vyshe10);
        poloska_do10 = (TextView) v.findViewById(R.id.poloska_do10);
        poloska_posle10 = (TextView) v.findViewById(R.id.poloska_vyshe10);

        cbox_pensioner = (CheckBox) v.findViewById(R.id.checkBox_pens);
//============================================================================================
        //===================данные при повороте====================
        switch (FIS_UYR){
            case 0:
                poloska_fiz.setBackgroundColor(getResources().getColor(R.color.banner));
                poloska_uyr.setBackgroundColor(getResources().getColor(R.color.whiteblue));
                cbox_pensioner.setVisibility(View.VISIBLE);
            break;
            case 1:
                poloska_fiz.setBackgroundColor(getResources().getColor(R.color.whiteblue));
                poloska_uyr.setBackgroundColor(getResources().getColor(R.color.banner));
                cbox_pensioner.setVisibility(View.GONE);
                break;
        }
        switch (vozrast_TS){
            case 1:
                poloska_do10.setBackgroundColor(getResources().getColor(R.color.banner));
                poloska_posle10.setBackgroundColor(getResources().getColor(R.color.whiteblue));
                break;
            case 2:
                poloska_do10.setBackgroundColor(getResources().getColor(R.color.whiteblue));
                poloska_posle10.setBackgroundColor(getResources().getColor(R.color.banner));
                break;
        }
//============================================================================================

        radiolistener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                switch (v.getId()){
                    case (R.id.tv_FizL):
                        FIS_UYR = 0;

                        poloska_fiz.setBackgroundColor(getResources().getColor(R.color.banner));
                        poloska_uyr.setBackgroundColor(getResources().getColor(R.color.whiteblue));
                        cbox_pensioner.setVisibility(View.VISIBLE);
                        cbox_pensioner.setChecked(false);


                        if (vid_TS == 0) {
                        spinner_massa.setAdapter(adapter_mas_legk_fiz);
                        massa_TS = 0;

                    }

                        itog(FIS_UYR, vid_TS, massa_TS, vozrast_TS, pens);
                        break;
                    case (R.id.tv_UrL):
                        FIS_UYR = 1;
                        poloska_fiz.setBackgroundColor(getResources().getColor(R.color.whiteblue));
                        poloska_uyr.setBackgroundColor(getResources().getColor(R.color.banner));
                        cbox_pensioner.setChecked(false);
                        cbox_pensioner.setVisibility(View.GONE);
                        pens = 1;


                        if (vid_TS == 0) {
                            spinner_massa.setAdapter(adapter_mas_legk_uyr);
                            massa_TS = 0;

                        }
                        itog(FIS_UYR,vid_TS,massa_TS,vozrast_TS,pens);
                        break;

                    case (R.id.tv_vyshe10):
                        Log.d(LOG_TAG, "Good2");
                        vozrast_TS = 1;

                        poloska_do10.setBackgroundColor(getResources().getColor(R.color.banner));
                        poloska_posle10.setBackgroundColor(getResources().getColor(R.color.whiteblue));
                        itog(FIS_UYR,vid_TS,massa_TS,vozrast_TS,pens);

                        break;
                    case (R.id.tv_do10):
                        Log.d(LOG_TAG, "Good1");
                        vozrast_TS = 2;

                        poloska_do10.setBackgroundColor(getResources().getColor(R.color.whiteblue));
                        poloska_posle10.setBackgroundColor(getResources().getColor(R.color.banner));
                        itog(FIS_UYR,vid_TS,massa_TS,vozrast_TS,pens);

                        break;


                        }
            }
        };
        fiz.setOnClickListener(radiolistener);
        uyr.setOnClickListener(radiolistener);

        do10.setOnClickListener(radiolistener);
        posle10.setOnClickListener(radiolistener);
//========================================================================================
        pensionerlistener = new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
             if (isChecked) {
                 pens = 2;

                 itog(FIS_UYR, vid_TS, massa_TS, vozrast_TS, pens);
             }
                 else {
                 pens = 1;

                 itog(FIS_UYR,vid_TS,massa_TS,vozrast_TS,pens);
             }
            }
        };
        cbox_pensioner.setOnCheckedChangeListener(pensionerlistener);
//========================================================================================

        spinner_TS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               switch (position){
                case 0: // легковой
                    Log.d(LOG_TAG, "0");
                    spinner_massa.setEnabled(true);
                    // надо сделать проверку на физ юр
                    if (FIS_UYR == 0)
                    spinner_massa.setAdapter(adapter_mas_legk_fiz);
                    else spinner_massa.setAdapter(adapter_mas_legk_uyr);

                    if (vid_TS == 0)                          // это нужно при повороте экрана, если выбрано легковые ТС
                        spinner_massa.setSelection(massa_TS);     // то устанавливаем спиннер массы в прежнюю позицию

                    break;
                case 1: // грузовой
                    Log.d(LOG_TAG, "1");

                    spinner_massa.setEnabled(true);      // активируем спиннер после мотоциклов
                    spinner_massa.setAdapter(adapter_mas_gruz);  // устанавливаем адаптер грузовых ТС

                    if (vid_TS == 1)                          // это нужно при повороте экрана, если выбрано грузовые ТС
                    spinner_massa.setSelection(massa_TS);     // то устанавливаем спиннер массы в прежнюю позицию

                    break;
                case 2: // автобус
                    Log.d(LOG_TAG, "2");

                    spinner_massa.setEnabled(true);
                    spinner_massa.setAdapter(adapter_mas_bus);

                    if (vid_TS == 2)
                        spinner_massa.setSelection(massa_TS);

                    break;
                case 3: // прицеп
                    Log.d(LOG_TAG, "3");
                    spinner_massa.setEnabled(true);
                    spinner_massa.setAdapter(adapter_mas_pritcep);

                    if (vid_TS == 3)
                        spinner_massa.setSelection(massa_TS);

                    break;
                case 4: // мотоцикл
                    Log.d(LOG_TAG, "4");
                    vid_TS = position;
                    spinner_massa.setAdapter(null);
                    spinner_massa.setEnabled(false);
                    itog(FIS_UYR,vid_TS,massa_TS,vozrast_TS,pens);
                    break;
            }
                vid_TS = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_massa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              massa_TS = position;
                Log.d(LOG_TAG, " spinner_massa.setOnItemSelectedListener posit = " + position);

                itog(FIS_UYR, vid_TS, massa_TS, vozrast_TS, pens);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return v;
    }

    public void itog (int FIS_UYR, int vid_TS, int massa_TS, int vozrast_TS, int pens){
    switch (FIS_UYR) {
        case 0: // физическое лицо
                switch (vid_TS){
                    case 0: //легковой
                        switch (massa_TS){
                            case 0: // до 1.5т
                                b_v = 3;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                            case 1: // 1.5-2т.
                                b_v = 6;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                            case 2: // 2-3т.
                                b_v = 8;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                            case 3: // более 3т.
                                b_v = 11;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                         }

                    break;
                    case 1: //грузовой
                        vozrast_TS = 1;
                        switch (massa_TS){
                            case 0: // до 2.5т
                                b_v = 8;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                            case 1: // 2.5-3.5т.
                                b_v = 17;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                            case 2: // 3.5-12т.
                                b_v = 22;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                            case 3: // более 12т.
                                b_v = 25;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                        }

                        break;
                    case 2: //автобус
                        vozrast_TS = 1;
                        switch (massa_TS){
                            case 0: // до 20 мест
                                b_v = 12;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                            case 1: // 21-40 мест
                                b_v = 17;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                            case 2: // свыше 40 мест
                                b_v = 22;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                        }

                        break;
                    case 3: //прицеп
                        switch (massa_TS){
                            case 0: // не более 0,75т.
                                b_v = 2;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                            case 1: // более 0,75т.
                                b_v = 11;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                            case 2: // прицеп-дача
                                b_v = 5;
                                itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                                itog.setText(itog_sum);

                                itog_b = podschet(b_v, vozrast_TS, pens);
                                itog_baz.setText(itog_b);
                                break;
                        }

                        break;
                    case 4: //мотоцикл
                        b_v = 2;
                        itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                        itog.setText(itog_sum);

                        itog_b = podschet(b_v, vozrast_TS, pens);
                        itog_baz.setText(itog_b);
                        break;
                }

            break;
        case 1://юридическое лицо
            switch (vid_TS){
                case 0: //легковой
                    switch (massa_TS){
                        case 0: // до 1т.
                            b_v = 7;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                        case 1: // 1-2т.
                            b_v = 9;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                        case 2: // 2-3т.
                            b_v = 11;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                        case 3: // более 3т.
                            b_v = 14;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                    }
                break;
                case 1: //грузовой
                    vozrast_TS = 1;
                    switch (massa_TS){
                        case 0: // до 2.5т
                            b_v = 12;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                        case 1: // 2.5-3.5т.
                            b_v = 17;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                        case 2: // 3.5-12т.
                            b_v = 22;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                        case 3: // более 12т.
                            b_v = 25;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                    }
                    break;
                case 2: //автобус
                    vozrast_TS = 1;
                    switch (massa_TS){
                        case 0: // до 20 мест
                            b_v = 12;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                        case 1: // 21-40 мест
                            b_v = 17;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                        case 2: // свыше 40 мест
                            b_v = 22;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                    }
                    break;
                case 3: //прицеп
                    switch (massa_TS){
                        case 0: // не более 0,75т.
                            b_v = 5;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                        case 1: // более 0,75т.
                            b_v = 12;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                        case 2: // прицеп-дача
                            b_v = 7;
                            itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                            itog.setText(itog_sum);

                            itog_b = podschet(b_v, vozrast_TS, pens);
                            itog_baz.setText(itog_b);
                            break;
                    }
                    break;
                case 4: //мотоцикл
                    b_v = 3;
                    itog_sum = podschet(b_v, vozrast_TS, pens, bazovaya_vel);
                    itog.setText(itog_sum);

                    itog_b = podschet(b_v, vozrast_TS, pens);
                    itog_baz.setText(itog_b);
                    break;
            }
            break;
    }

    }
//=========================подсчет суммы в бел.рублях
private String podschet(Integer b_v, Integer vozrast_TS, Integer pens, Double bazovaya_vel){
 String m_podschet = "";
 Double temp;
 temp = (double)b_v * vozrast_TS * bazovaya_vel / pens;
 return m_podschet =  MyClass.check_ruble(temp);   // обращение к подключаемой библиотеке Lib_denom
}
//===================================================
//=========================подсчет количества базовых величин
private String podschet(Integer b_v, Integer vozrast_TS, Integer pens){
    String m_podschet = "";
    Integer temp = b_v * vozrast_TS;
    Integer temp1;
    Float temp2;
    if (temp%pens == 0){
        temp1 = temp/pens;
        return m_podschet = temp1.toString() + "  " + sclonenie(temp1);
    }
    else {
        temp2 = temp.floatValue()/pens;
        return m_podschet = temp2.toString() + " базовой величины";
    }
           }
//===================================================


private String sclonenie (Integer n){
    String s = "";
    switch (n){
        case (1):
            s= " базовая величина";
            break;
        case (2):
        case (3):
        case (4):
        case (22):
        case (23):
        case (24):
        case (34):
        case (44):
            s = " базовых величины";
            break;
        case (5):
        case (6):
        case (7):
        case (8):
        case (9):
        case (10):
        case (11):
        case (12):
        case (13):
        case (14):
        case (15):
        case (16):
        case (17):
        case (18):
        case (19):
        case (20):
        case (25):
        case (28):
        case (50):
        case (45):
        case (55):
            s= " базовых величин";
            break;
        default: s= "б.в.";
            break;
                   }
    return s;}

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy_Fragment_nalog_na_dorogi");
    }

    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause_Fragment_nalog_na_dorogi");
    }


    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume_Fragment_nalog_na_dorogi");
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("FIS_UYR", FIS_UYR);
        outState.putInt("VID_TS", vid_TS);
        outState.putInt("MASSA_TS", massa_TS);
        outState.putInt("VOZRAST_TS", vozrast_TS);
        outState.putInt("PENS", pens);

        Log.d(LOG_TAG, "onSaveInstanceState_Fragment_nalog_na_dorogi");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        Log.d(LOG_TAG, "onViewStateRestored_Fragment_nalog_na_dorogi");
    }

    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart_Fragment_nalog_na_dorogi");
    }

    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop_Fragment_nalog_na_dorogi");
    }

class MySimpleAdapter extends SimpleAdapter{

    public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }
    @Override
    public void setViewText(TextView v, String text) {
        super.setViewText(v, text);
        //v.setTextColor(getResources().getColor(R.color.banner));
    }
    @Override
    public void setViewImage(ImageView v, int value) {
        super.setViewImage(v, value);
    }
}   // MySimpleAdapter


}//main
