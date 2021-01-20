package com.example.aplikasiperekamsuara.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aplikasiperekamsuara.Database.DBHelper;
import com.example.aplikasiperekamsuara.Fragments.PlaybackFragment;
import com.example.aplikasiperekamsuara.Interfaces.OnDatabaseChangedListener;
import com.example.aplikasiperekamsuara.Models.RecordingItem;
import com.example.aplikasiperekamsuara.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileViewerAdapter extends RecyclerView.Adapter<FileViewerAdapter.FileViewerViewHolder> implements OnDatabaseChangedListener
{
    Context context;
    ArrayList<RecordingItem>arrayList;
    LinearLayoutManager llm;

    DBHelper dbHelper;

    public FileViewerAdapter(Context context, ArrayList<RecordingItem>arrayList, LinearLayoutManager llm)
    {
        this.context = context;
        this.arrayList = arrayList;
        this.llm = llm;
        dbHelper= new DBHelper(context);

        dbHelper.setOnDatabaseChangedListener(this);


    }
    @NonNull
    @Override
    public FileViewerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_view,viewGroup,false);
        return new FileViewerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewerViewHolder holder, int i) {
    RecordingItem recordingItem = arrayList.get(i);

    long minutes = TimeUnit.MILLISECONDS.toMinutes(recordingItem.getLength());
    long seconds = TimeUnit.MILLISECONDS.toSeconds(recordingItem.getLength()) - TimeUnit.MINUTES.toSeconds(minutes);

    holder.vName.setText(recordingItem.getName());
    holder.vLength.setText(String.format("%02d:%02d",minutes,seconds));

    holder.vTimeAdded.setText(DateUtils.formatDateTime(context,recordingItem.getTime_added(),
            DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_TIME
              | DateUtils.FORMAT_SHOW_YEAR
                ));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onNewDatabaseEntryAdded(RecordingItem recordingItem) {
        arrayList.add(recordingItem);
        notifyItemInserted(arrayList.size()-1);
    }

    public class FileViewerViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.file_name_text)TextView vName;
        @BindView(R.id.file_length_text) TextView vLength;
        @BindView(R.id.file_time_added) TextView vTimeAdded;
        @BindView(R.id.card_view) View card_view;

        public FileViewerViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this,itemView);

            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlaybackFragment playbackFragment = new PlaybackFragment();
                    Bundle b = new Bundle();

                    b.putSerializable("item",arrayList.get(getAdapterPosition()));
                    playbackFragment.setArguments(b);

                    FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();

                    playbackFragment.show(fragmentTransaction,"dialog_playback");
                }
            });
    }   }

}
