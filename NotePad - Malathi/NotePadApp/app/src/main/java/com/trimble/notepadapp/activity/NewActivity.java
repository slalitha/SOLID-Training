package com.trimble.notepadapp.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.trimble.notepadapp.R;
import com.trimble.notepadapp.convertor.Converter;
import com.trimble.notepadapp.dialog.SearchDialogFragment;
import com.trimble.notepadapp.util.AlertUtil;
import com.trimble.notepadapp.util.CustomClipBoardManager;
import com.trimble.notepadapp.util.FileUtil;
import com.trimble.notepadapp.util.StringUtil;

import java.io.File;

public class NewActivity extends Activity implements AlertUtil.IAlertCallbacks,
        View.OnClickListener, SearchDialogFragment.ISearchDialogFragment, DialogInterface.OnClickListener {


    private EditText etContent;
    private boolean isEdit = false;
    private String mCurrentFilePath;
    private RelativeLayout llToolBar;
    private LinearLayout llSearch;
    private SearchDialogFragment searchDialogFragment;
    private String[] mConvertFormats;
    private FileType mCurrentFileFormat;
    private String[] mFormatText;

    public enum FileType {
        PDF,
        DOC,
        TXT,
        XML,
        HTML
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        mCurrentFilePath = null;
        isEdit = false;

        mConvertFormats = new String[]{getString(R.string.pdf), getString(R.string.doc),
                getString(R.string.xml), getString(R.string.html)};
        mFormatText = new String[]{
                getString(R.string.leading_space), getString(R.string.trailing_space), getString(R.string.leading_trailing_space),
                getString(R.string.split_lines)};

        etContent = findViewById(R.id.etContent);
        llToolBar = findViewById(R.id.rltoolbar);
        llSearch = findViewById(R.id.searchHeader);

        ImageView saveButton = findViewById(R.id.ivDone);
        saveButton.setOnClickListener(this);

        ImageView copyButton = findViewById(R.id.ivCopy);
        copyButton.setOnClickListener(this);

        ImageView pasteButton = findViewById(R.id.ivPaste);
        pasteButton.setOnClickListener(this);

        ImageView searchButton = findViewById(R.id.ivSearch);
        searchButton.setOnClickListener(this);

        ImageView exportButton = findViewById(R.id.ivExport);
        exportButton.setOnClickListener(this);

        ImageView formatButton = findViewById(R.id.ivFormat);
        formatButton.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isEdit = true;
            mCurrentFilePath = bundle.getString(MainActivity.FILE_PATH);
            String fileContent = FileUtil.readFile(mCurrentFilePath);
            etContent.setText(fileContent);
        }
    }


    @Override
    public void sendFilePath(String fileName) {

        if (mCurrentFileFormat == FileType.TXT) {
            fileName = fileName + ".txt";
        } else if (mCurrentFileFormat == FileType.PDF) {
            fileName = fileName + ".pdf";
        } else if (mCurrentFileFormat == FileType.XML) {
            fileName = fileName + ".xml";
        } else if (mCurrentFileFormat == FileType.HTML) {
            fileName = fileName + ".html";
        } else if (mCurrentFileFormat == FileType.DOC) {
            fileName = fileName + ".doc";
        }
        saveToInternalStorage(fileName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivDone:
                mCurrentFileFormat = FileType.TXT;
                saveFile();
                break;
            case R.id.ivCopy:
                CustomClipBoardManager.copyToClipBoard(etContent, this);
                Snackbar.make(etContent, getString(R.string.copy_success_message), Snackbar.LENGTH_LONG).show();
                break;

            case R.id.ivPaste:
                boolean isSuccess = CustomClipBoardManager.pasteClipBoardContent(etContent, this);
                if (isSuccess) {
                    Snackbar.make(etContent, getString(R.string.no_paste_message), Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(etContent, getString(R.string.paste_success_message), Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.ivSearch:
                searchDialogFragment = SearchDialogFragment.newInstance(this);
                FragmentManager fragmentManager = getFragmentManager();
                searchDialogFragment.show(fragmentManager, SearchDialogFragment.TAG);
                break;
            case R.id.ivBack:
                llSearch.setVisibility(View.GONE);
                llToolBar.setVisibility(View.VISIBLE);
                etContent.setText(etContent.getText().toString());
                break;
            case R.id.ivFormat:
                StringUtil.removeTrailingSpace(etContent.getText().toString());
                AlertUtil.CreateAlertDialogWithRadioButtonGroup(NewActivity.this, getString(R.string.format_text),
                        mFormatText, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                format(item);
                                dialog.dismiss();
                            }
                        });
                break;
            case R.id.ivExport:
                AlertUtil.CreateAlertDialogWithRadioButtonGroup(NewActivity.this, getString(R.string.export_formats), mConvertFormats, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                mCurrentFileFormat = FileType.PDF;
                                break;
                            case 1:
                                mCurrentFileFormat = FileType.DOC;
                                break;
                            case 2:
                                mCurrentFileFormat = FileType.XML;
                                break;
                            case 3:
                                mCurrentFileFormat = FileType.HTML;
                                break;
                        }
                        showAlertDialog();
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.ivPrevious:
                position--;
                break;
            case R.id.ivNext:
                position++;
                break;
            default:
                break;
        }
    }

    private void format(int item) {
        switch (item) {
            case 0:
                etContent.setText(StringUtil.removeLeadingSpace(etContent.getText().toString()));
                break;
            case 1:
                etContent.setText(StringUtil.removeTrailingSpace(etContent.getText().toString()));
                break;
            case 2:
                etContent.setText(StringUtil.removeLeadingAndTrailingSpace(etContent.getText().toString()));
                break;
            case 3:
                etContent.setText(StringUtil.splitByLine(etContent.getText().toString()));
                break;
        }
    }

    private int position = -1;

    @Override
    public void search(String text, String replaceText, SearchDialogFragment.SearchType type) {
        searchDialogFragment.dismiss();
        int NumberOfStrings = 0;
        if (type == SearchDialogFragment.SearchType.SEARCH) {
            NumberOfStrings = changeTextView(text, 1);
        } else if (type == SearchDialogFragment.SearchType.REPLACE) {
            etContent.setText(etContent.getText().toString().replace(text, replaceText));
        } else if (type == SearchDialogFragment.SearchType.REPLACE_ALL) {
            etContent.setText(etContent.getText().toString().replaceAll(text, replaceText));
        }

        if (NumberOfStrings != -1) {
            llSearch.setVisibility(View.VISIBLE);
            llToolBar.setVisibility(View.GONE);
            etContent.setVisibility(View.VISIBLE);

            ImageView back = findViewById(R.id.ivBack);
            back.setOnClickListener(this);
            ImageView next = findViewById(R.id.ivNext);
            next.setOnClickListener(this);
            ImageView prevoius = findViewById(R.id.ivPrevious);
            prevoius.setOnClickListener(this);
            ImageView replace = findViewById(R.id.ivReplace);
            replace.setOnClickListener(this);
        }
    }

    private int changeTextView(String target, int selected) {
        if (selected < 0) {
            selected = 0;
        }
        String bString = etContent.getText().toString();
        int startSpan = 0, endSpan = 0;
        Spannable spanRange = new SpannableString(bString);

        int currentIndex = 0;

        while (true) {
            startSpan = bString.indexOf(target, endSpan);
            endSpan = startSpan + target.length();

            boolean isLast = bString.indexOf(target, endSpan) < 0;
            if (startSpan < 0)
                break;

            ParcelableSpan span;
            if (currentIndex == selected || isLast && selected > currentIndex) {
                // selected span
                span = new StyleSpan(Typeface.BOLD_ITALIC);
                if (isLast && selected > currentIndex) {
                    // normalize if selected out of bounds
                    selected = currentIndex;
                }
            } else {
                // deselected span
                span = new StyleSpan(Typeface.BOLD);
            }

            currentIndex++;
            spanRange.setSpan(
                    span,
                    startSpan,
                    endSpan,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        etContent.setText(spanRange);

        if (currentIndex == 0) {
            // text not found
            return -1;
        } else {
            return selected;
        }
    }

    @Override
    public void onBackPressed() {
        AlertUtil.showConfirmationAlertDialog(this, this);
        //super.onBackPressed();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            dialog.dismiss();
            mCurrentFileFormat = FileType.TXT;
            saveFile();
        } else {
            dialog.dismiss();
            finish();
        }
    }

    private void saveFile() {
        if (isEdit) {
            saveToInternalStorage(mCurrentFilePath);
        } else {
            showAlertDialog();
        }
    }

    private void saveToInternalStorage(String fileName) {
        boolean isSaved = Converter.getInstance(mCurrentFileFormat).convert(etContent.getText().toString(), fileName);
        if (isSaved) {
            Snackbar.make(etContent, getString(R.string.success_save_message), Snackbar.LENGTH_LONG).show();
            if (mCurrentFileFormat == FileType.TXT)
                finish();
        } else {
            Snackbar.make(etContent, getString(R.string.fail_save_message), Snackbar.LENGTH_LONG).show();
        }
    }

    private void showAlertDialog() {
        AlertUtil.showCustomAlertDialog(this, this);
    }
}

