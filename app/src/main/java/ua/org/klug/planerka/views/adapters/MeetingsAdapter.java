package ua.org.klug.planerka.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ua.org.klug.planerka.R;
import ua.org.klug.planerka.model.Meeting;

public class MeetingsAdapter extends RecyclerView.Adapter<MeetingsAdapter.MeetingsViewHolder> {
    private List<Meeting> meetings;


    public MeetingsAdapter() {
        meetings = new ArrayList<>();
    }

    @Override
    public MeetingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meeting, parent, false);
        return new MeetingsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MeetingsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
        notifyDataSetChanged();
    }

    class MeetingsViewHolder extends RecyclerView.ViewHolder {
        TextView whenText;
        TextView whereText;

        MeetingsViewHolder(View itemView) {
            super(itemView);
            whenText = (TextView) itemView.findViewById(R.id.text_when);
            whereText = (TextView) itemView.findViewById(R.id.text_where);
        }

        void bind(int position) {
            Meeting meeting = meetings.get(position);

            whenText.setText(meeting.getWere());
        }
    }
}
