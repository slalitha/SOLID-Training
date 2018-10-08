package com.trimble.notepadapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trimble.notepadapp.R;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.CustomViewHolder> {

    private List<File> mListOfFiles;
    private IFileAdapterCallBack mIFileAdapterCallBack;

    public interface IFileAdapterCallBack {
        void startNewActivity(String filepath);
    }

    public FileAdapter(List<File> listOfFiles, IFileAdapterCallBack iFileAdapterCallBack) {
        mListOfFiles = listOfFiles;
        mIFileAdapterCallBack = iFileAdapterCallBack;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_files, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.fileName.setText(mListOfFiles.get(position).getName());
        long times = mListOfFiles.get(position).lastModified();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(times);
        holder.modifiedTime.setText(String.valueOf("Modified Date Time : " + new Date(times) + " " + calendar.get(Calendar.HOUR) + " " +
                calendar.get(calendar.MINUTE) + " " +
                calendar.get(calendar.SECOND)));
        holder.filePath.setText(String.valueOf("FilePath : " + mListOfFiles.get(position).getAbsolutePath()));
    }

    @Override
    public int getItemCount() {
        return mListOfFiles.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView fileName;
        LinearLayout fileLayout;
        TextView modifiedTime;
        TextView filePath;

        public CustomViewHolder(final View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.tvFileName);
            modifiedTime = itemView.findViewById(R.id.tvLastModifiedTime);
            filePath = itemView.findViewById(R.id.tvFilePath);
            fileLayout = itemView.findViewById(R.id.llfileview);
            fileLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIFileAdapterCallBack.startNewActivity(mListOfFiles.get(getAdapterPosition()).getAbsolutePath());
                }
            });
        }
    }
}
