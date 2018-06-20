package com.eg.ahzam.bottomsheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.btn_bottom_sheet)
    Button btnBottomSheet;


    @BindView(R.id.bottom_sheet)
    LinearLayout layoutBottomSheet;

    BottomSheetBehavior sheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // BottomSheetBehavior function is associated with the LinearLayout view
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);

        /* Change Button Text when the bottom sheet state is changed */
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    // When the sheet is not visible
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    // When the sheet is in full view
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        // Button shows text
                        btnBottomSheet.setText(R.string.close_sheet);
                    }
                    break;
                    // When the sheet is gone
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        btnBottomSheet.setText(R.string.expand_sheet);
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }

    // Manually opening / closing bottom sheet on a button click
    @OnClick(R.id.btn_bottom_sheet)
    public void toggleBottomSheet() {
        if(sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            btnBottomSheet.setText(R.string.close_sheet);
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            btnBottomSheet.setText(R.string.expand_sheet);
        }
    }

    @OnClick(R.id.btn_bottom_sheet_dialog)
    public void showBottomSheetDialog() {
        // Inflate the fragment and store the inflation in the View object
        View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);

        // Create a new BottomSheetDialog object
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);                    // Allow the content to display the fragment
        dialog.show();                                  // show the fragment
    }

    @OnClick(R.id.btn_bottom_sheet_dialog_fragment)
    public void showBottomSheetDialogFragment() {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(); // Create new Sheet Fragment
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
}
