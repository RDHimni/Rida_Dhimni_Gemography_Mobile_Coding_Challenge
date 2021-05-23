package ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.R;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.models.Item;

public class TrendingReposAdapter extends RecyclerView.Adapter<TrendingReposAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Item> model_list;

    private OnItemClickListener mListener;


    public TrendingReposAdapter(Context context) {
        this.context = context;
        model_list = new ArrayList<>();
    }

    public void setModel_list(ArrayList<Item> model_list) {
        this.model_list = model_list;
        notifyDataSetChanged();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onCreateViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.trending_repos_item, parent, false);

        return new MyViewHolder(view, mListener);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onBindViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.repoName.setText(model_list.get(position).getName());
        holder.repoDesc.setText(model_list.get(position).getDescription());
        holder.ownerName.setText(model_list.get(position).getOwner().getLogin());
        Glide.with(context).load(model_list.get(position).getOwner().getAvatarUrl()).into(holder.avatar);

        int NumberOfStar = model_list.get(position).getStargazersCount();
        String NumOfStar ;
        if (NumberOfStar > 1000) {
            NumOfStar =  String.valueOf(NumberOfStar);
            char a = NumOfStar.charAt(0);
            char b = NumOfStar.charAt(1);
            String nS = a+"."+b+"k";
            holder.numberOfStar.setText(nS);
        }
        else {
            holder.numberOfStar.setText(String.valueOf(NumberOfStar));
        }

    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************getItemCount()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int getItemCount() {
        return model_list.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************class MyViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView repoName,repoDesc,ownerName,numberOfStar;
        ImageView avatar;


        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            repoName = itemView.findViewById(R.id.reposNameTv);
            repoDesc = itemView.findViewById(R.id.reposDescTv);
            ownerName = itemView.findViewById(R.id.repoOwnerName);
            numberOfStar = itemView.findViewById(R.id.numberOfStarsTv);
            avatar = itemView.findViewById(R.id.avatarIMv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position, v);
                        }
                    }
                }
            });
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************interface OnItemClickListener()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    public interface OnItemClickListener {
        void onItemClick(int position, View itemView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }



}

