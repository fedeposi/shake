package digitalhouse.android.a0317moacns1c_03;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_03.Controller.ControllerShakes;
import digitalhouse.android.a0317moacns1c_03.Model.Pojo.Album;
import digitalhouse.android.a0317moacns1c_03.Model.Pojo.Artista;
import digitalhouse.android.a0317moacns1c_03.Model.Pojo.Tema;
import digitalhouse.android.a0317moacns1c_03.View.AdapterChartsTema;
import digitalhouse.android.a0317moacns1c_03.utils.ResultListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentShakes extends Fragment implements AdapterChartsTema.InformarClickTema{

    private RecyclerView recyclerViewShakes;
    private List<Tema> listaDeTemas;
    private AdapterChartsTema adapterdeShakes;
    private InformarClickFragment escuchadorDelFragment;


    public FragmentShakes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        listaDeTemas = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_shakes, container, false);
        recyclerViewShakes= (RecyclerView)view.findViewById(R.id.recyclerShakes);
        recyclerViewShakes.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        adapterdeShakes = new AdapterChartsTema(getActivity(),listaDeTemas,FragmentShakes.this);
        Bundle unBundle = getArguments();
        recyclerViewShakes.setAdapter(adapterdeShakes);
        cargarRecyclerShakes();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        escuchadorDelFragment = (InformarClickFragment)context;
    }


    private void cargarRecyclerShakes (){
        ControllerShakes controllerShakes = new ControllerShakes(getContext());
        controllerShakes.obtenerShakes(new ResultListener<List<Tema>>() {
            @Override
            public void finish(List<Tema> temaList) {
                listaDeTemas.addAll(temaList);
                adapterdeShakes.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void informarClickTema(Tema tema) {
        escuchadorDelFragment.informarClickenFragment(tema);
    }



    public interface InformarClickFragment{
        public void informarClickenFragment(Tema tema);

    }

}
