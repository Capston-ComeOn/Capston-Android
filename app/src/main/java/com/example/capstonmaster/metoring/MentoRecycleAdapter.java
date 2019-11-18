package com.example.capstonmaster.metoring;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstonmaster.MainActivity;
import com.example.capstonmaster.R;
import com.example.capstonmaster.dto.MentoResponseVO;

import java.util.ArrayList;

public class MentoRecycleAdapter extends RecyclerView.Adapter<MentoRecycleAdapter.ViewHolder>{
    private ArrayList<MentoResponseVO> mento=null;
    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    private Context context;
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView name;
        TextView content;
        TextView start;
        TextView end;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.mento_title);
            name=itemView.findViewById(R.id.mento_name);
            content=itemView.findViewById(R.id.mento_content);
            start=itemView.findViewById(R.id.mento_start);
            end=itemView.findViewById(R.id.mento_end);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // TODO : use pos.
                        Intent intent = new Intent(context, MentoDetailActivity.class);
                        intent.putExtra("detail", mento.get(pos));

                        context.startActivity(intent);
                    }
                }
            });
        }
    }
    // 생성자에서 데이터 리스트 객체를 전달받음.
    public MentoRecycleAdapter(ArrayList<MentoResponseVO> mento) {
        this.mento = mento;
        System.out.println("생성자에서 데이터 리스트 객체를 전달받음");
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_mentoring, parent, false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        context =parent.getContext();
        return viewHolder;

    }
    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title=mento.get(position).getTitle();
        System.out.println("onBindViewHolder()"+title+" 제목찍어보기");
        holder.title.setText(title);
//         String name=mento.get(position).getIntroduceRequestDto().getMento();
        holder.name.setText(MainActivity.userName);
        String content=mento.get(position).getContent();
        holder.content.setText(content);
        holder.start.setText(""+mento.get(position).getStartTime());
        holder.end.setText(""+mento.get(position).getEndTime());
        System.out.println(mento.get(position).getEndTime());
    }

    @Override
    public int getItemCount() {
        return mento.size();
    }

}
