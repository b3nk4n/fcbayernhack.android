package fcb.de.fcbayernemotions.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fcb.de.fcbayernemotions.R;
import fcb.de.fcbayernemotions.model.EmotionModel;

/**
 * A simple card fragment for the fan emotions.
 */
public class CardFragment extends Fragment {
    ArrayList<EmotionModel> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
        getActivity().setTitle("Fan Emotion Feed");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card, container, false);
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<EmotionModel> list;

        public MyAdapter(ArrayList<EmotionModel> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            EmotionModel model = list.get(position);
            holder.titleTextView.setText(model.getTimeString());
            holder.extraTextView.setText(model.getExtraText());
            if (!model.hasExtraText()) {
                holder.extraTextView.setVisibility(View.GONE);
            }
            if (model.getResourceType() == EmotionModel.ResourceType.IMG) {
                holder.coverImageView.setImageResource(model.getResourceId());
                holder.coverImageView.setTag(model.getResourceId());
            } else {
                Glide.with(getActivity()).asGif().load(model.getResourceId())
                        .into(holder.coverImageView);
            }

            holder.likeImageView.setTag(R.drawable.ic_favorite_border_black_24dp);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView extraTextView;
        public ImageView coverImageView;
        public ImageView likeImageView;
        public ImageView shareImageView;
        public ImageView commentImageView;

        public MyViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            extraTextView = (TextView) v.findViewById(R.id.extraTextView);
            coverImageView = (ImageView) v.findViewById(R.id.coverImageView);
            likeImageView = (ImageView) v.findViewById(R.id.likeImageView);
            shareImageView = (ImageView) v.findViewById(R.id.shareImageView);
            commentImageView = (ImageView) v.findViewById(R.id.commentImageView);
            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (int)likeImageView.getTag();
                    if( id == R.drawable.ic_favorite_border_black_24dp){
                        likeImageView.setTag(R.drawable.ic_favorite_black_24dp);
                        likeImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                    }else{
                        likeImageView.setTag(R.drawable.ic_favorite_border_black_24dp);
                        likeImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    }
                }
            });

            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                            "://" + getResources().getResourcePackageName(coverImageView.getId())
                            + '/' + "drawable" + '/' + getResources().getResourceEntryName((int)coverImageView.getTag()));

                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
                    shareIntent.setType("image/jpeg");
                    startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
                }
            });

            shareImageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // TODO implement Comment dialog/activity
                }
            });
        }
    }

    public void initializeList() {
        listitems.clear();

        listitems.add(new EmotionModel("13'", "#MiaSanMia super Bayern!",
                R.drawable.fan_background, EmotionModel.ResourceType.IMG));
        listitems.add(new EmotionModel("42'", "",
                R.raw.test, EmotionModel.ResourceType.GIF));
        listitems.add(new EmotionModel("64'", "Awesome goal at #AllianzArena in #Munich by Müller #FCB #FCBayern",
                R.drawable.fan_background, EmotionModel.ResourceType.IMG));
    }
}
