package androiddeveloper.eder.padilla.hackcentrofox;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androiddeveloper.eder.padilla.hackcentrofox.model.PublicarRuta;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ederpadilla on 20/05/17.
 */

public class RouteAdapters extends RecyclerView.Adapter<RouteAdapters.ViewHolderAdapter> implements View.OnClickListener{

    private View.OnClickListener listener;

    private List<PublicarRuta> productosList;

    private Context context;

    private OnRouteSelected onRouteSelected;

    public RouteAdapters(List<PublicarRuta> ProductosList,Context context,OnRouteSelected onRouteSelected) {
        this.productosList = ProductosList;
        this.context=context;
        this.onRouteSelected=onRouteSelected;
    }


    @Override
    public RouteAdapters.ViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_route, parent, false);
        RouteAdapters.ViewHolderAdapter viewholder = new RouteAdapters.ViewHolderAdapter(view);
        return viewholder;
    }
    public void setOnClickListener(View.OnClickListener listener) {
    }
    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }
    @Override
    public void onBindViewHolder(RouteAdapters.ViewHolderAdapter holder, int position) {
        PublicarRuta item = productosList.get(position);
        holder.bindTexts(item);
        //holder.rootView.setOnClickListener(view -> onProductSelected.onProductSelected(productosList.get(position)));
    }

    @Override
    public int getItemCount() {
        return productosList.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_hra_salida)
        TextView tvHraSalida;

        @BindView(R.id.tv_hra_llegada)
        TextView mTvHraLlegada;

        @BindView(R.id.tv_espacio)
        TextView mTvEspacio;

        @BindView(R.id.tv_direccion_salida)
        TextView mTvDireccionSalida;

        @BindView(R.id.tv_direccion_llegada)
        TextView mTvDireccionLlegada;

        @BindView(R.id.tv_costo)
        TextView mTvCosto;

        View rootView;

        public ViewHolderAdapter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            rootView=itemView;

        }
        public  void bindTexts(PublicarRuta publicarRuta){
            tvHraSalida.setText("Salida: "+publicarRuta.getFechaSalida()+" "+publicarRuta.getHoraSalida());
            mTvHraLlegada.setText("Llegada "+publicarRuta.getFechaLlegada()+" "+publicarRuta.getHoraLlegada());
            mTvEspacio.setText("Capaciad "+publicarRuta.getEspacio()+"m2");
            mTvDireccionSalida.setText("Sale de "+publicarRuta.getDireccionSalida());
            mTvDireccionLlegada.setText("Llega a "+publicarRuta.getDireccionLlegada());
            mTvCosto.setText("Costo "+publicarRuta.getCosto()+" $");
        }


    }
}