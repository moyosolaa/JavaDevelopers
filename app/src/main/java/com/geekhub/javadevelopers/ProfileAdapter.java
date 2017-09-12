package com.geekhub.javadevelopers;

        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.squareup.picasso.Picasso;

        import java.util.List;


public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private List<eachProfileView> profileList;
    private Context context;

    public ProfileAdapter(List<eachProfileView> profileList, Context context) {
        this.profileList = profileList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_activity, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final eachProfileView profiles = profileList.get(position);

        holder.username.setText(profiles.getUsername());


        // parsing image with picasso
        Picasso.with(context).load(profiles.getPhoto()).into(holder.photo);

        holder.rowLayout.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view)  {
                Toast.makeText(context,"You're viewing " + profiles.getUsername() + "'s profile",Toast.LENGTH_LONG).show();

                eachProfileView profiles = profileList.get(position);

                Intent intent = new Intent(context, ProfilePage.class);
                intent.putExtra("avatar", profiles.getPhoto());
                intent.putExtra("username", profiles.getUsername());
                intent.putExtra("profileLink",profiles.getProfileLink());

                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView photo;
        public TextView username;
        public LinearLayout rowLayout;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            photo = (ImageView) itemView.findViewById(R.id.photo);
            username = (TextView) itemView.findViewById(R.id.username);
            rowLayout = (LinearLayout) itemView.findViewById(R.id.rowLayout);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }

    }
}
