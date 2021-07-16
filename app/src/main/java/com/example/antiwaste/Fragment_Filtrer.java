package com.example.antiwaste;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Filtrer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Filtrer extends DialogFragment {

    GridView simpleList1,simpleList2,simpleList3,simpleList4;
    ArrayList<String> typeList1,typeList2,typeList3,typeList4;




    public Fragment_Filtrer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provid0ed parameters.
     *
     * @return A new instance of fragment Fragment_Filtrer.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Filtrer newInstance() {
        Fragment_Filtrer fragment = new Fragment_Filtrer();

        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        simpleList1 = (GridView) view.findViewById(R.id.simpleGridViewtype1);
        simpleList2 = (GridView) view.findViewById(R.id.simpleGridViewtype1);
        simpleList3 = (GridView) view.findViewById(R.id.simpleGridViewtype1);
        simpleList4 = (GridView) view.findViewById(R.id.simpleGridViewtype1);

        typeList1 =new ArrayList<>();
        typeList1.add("Vienoiserie");
        typeList1.add("Pain");
        typeList1.add("Gateaux");
        typeList1.add("Halawiyat");
        AdapterItemFiltrer myAdapter=new AdapterItemFiltrer(getActivity(),R.layout.item_filtrer,typeList1);
        simpleList1.setAdapter(myAdapter);

        typeList2 =new ArrayList<>();
        typeList2.add("fruits");
        typeList2.add("Légumes");

        AdapterItemFiltrer myAdapter2=new AdapterItemFiltrer(getActivity(),R.layout.item_filtrer,typeList2);
        simpleList2.setAdapter(myAdapter2);

        typeList3 =new ArrayList<>();
        typeList3.add("Viande");
        typeList3.add("Poulet");

        AdapterItemFiltrer myAdapter3=new AdapterItemFiltrer(getActivity(),R.layout.item_filtrer,typeList3);
        simpleList3.setAdapter(myAdapter3);

        typeList4 =new ArrayList<>();
        typeList4.add("Légumes secs");
        typeList4.add("Boissons");
        typeList4.add("zmer");

        AdapterItemFiltrer myAdapter4=new AdapterItemFiltrer(getActivity(),R.layout.item_filtrer,typeList4);
        simpleList4.setAdapter(myAdapter4);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filtrer, container, false);

        return view;
    }
}