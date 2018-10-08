package com.trimble.notepadapp.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.trimble.notepadapp.R;

public class SearchDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String TAG = "Search Dialog Fragment";
    private static ISearchDialogFragment mISearchDialogFragment;
    private EditText etSearchText;
    private EditText etReplaceText;

    public enum SearchType {
        SEARCH,
        REPLACE,
        REPLACE_ALL
    }

    public interface ISearchDialogFragment {
        void search(String findText, String replaceText, SearchType type);
    }

    public static SearchDialogFragment newInstance(ISearchDialogFragment iSearchDialogFragment) {
        mISearchDialogFragment = iSearchDialogFragment;
        return new SearchDialogFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_search, null);
        etReplaceText = view.findViewById(R.id.etReplaceText);
        etSearchText = view.findViewById(R.id.etFindText);
        Button btFind = view.findViewById(R.id.btFind);
        btFind.setOnClickListener(this);
        Button btReplace = view.findViewById(R.id.btReplace);
        btReplace.setOnClickListener(this);
        Button btReplacecAll = view.findViewById(R.id.btReplaceall);
        btReplacecAll.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        SearchType searchType = SearchType.SEARCH;
        switch (v.getId()) {
            case R.id.btFind:
                searchType = SearchType.SEARCH;
                break;
            case R.id.btReplace:
                searchType = SearchType.REPLACE;
                break;
            case R.id.btReplaceall:
                searchType = SearchType.REPLACE_ALL;
                break;
        }
        mISearchDialogFragment.search(etSearchText.getText().toString(), etReplaceText.getText().toString(), searchType);
    }
}
