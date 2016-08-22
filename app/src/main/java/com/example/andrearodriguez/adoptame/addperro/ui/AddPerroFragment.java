package com.example.andrearodriguez.adoptame.addperro.ui;


import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.andrearodriguez.adoptame.BebeAdoptaApp;
import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.addperro.AddPerroPresenter;
import com.example.andrearodriguez.adoptame.addperro.event.AddPerroEvent;
import com.example.andrearodriguez.adoptame.domain.FirebaseAPI;
import com.example.andrearodriguez.adoptame.entities.Bebe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPerroFragment extends DialogFragment implements DialogInterface.OnShowListener, AddPerroView {


    @Bind(R.id.imgDog)
    ImageButton imgDog;
    @Bind(R.id.txtNombre)
    EditText txtNombre;
    @Bind(R.id.wrapernameperro)
    TextInputLayout wrapernameperro;
    @Bind(R.id.txtSexo)
    EditText txtSexo;
    @Bind(R.id.wrapersexo)
    TextInputLayout wrapersexo;
    @Bind(R.id.txtEdad)
    EditText txtEdad;
    @Bind(R.id.wraperedad)
    TextInputLayout wraperedad;
    @Bind(R.id.txtTamano)
    EditText txtTamano;
    @Bind(R.id.wrapertamano)
    TextInputLayout wrapertamano;
    @Bind(R.id.containerAddP)
    FrameLayout containerAddP;
    @Bind(R.id.imgPrueba)
    ImageView imgPrueba;

    private String photoPath;
    private Bebe bebe;
    private BebeAdoptaApp app;



    @Inject
    AddPerroPresenter addPerroPresenter;


    private static final int REQUEST_PICTURE = 1;

    public AddPerroFragment() {

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.addcontact_messagge_title)
                .setPositiveButton(R.string.addcontact_messagge_add, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })

                .setNegativeButton(R.string.addcontact_messagge_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_row_perro_get_data_, null);
        ButterKnife.bind(this, view);



        app = (BebeAdoptaApp) getActivity().getApplication();
        setupinjection();
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);
        return dialog;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        addPerroPresenter.onCreate();
        super.onActivityCreated(savedInstanceState);
    }

    private void setupinjection() {

        app.getAddPerroComponent(this).inject(this);
    }


    @Override
    public void onDestroy() {
        addPerroPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        final AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) {
            Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
            Button negativeButton = dialog.getButton(Dialog.BUTTON_NEGATIVE);

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = txtNombre.getText().toString().trim();
                    String edad = txtEdad.getText().toString().trim();

                    addPerroPresenter.uploadPhoto(name, edad, photoPath);

                    dismiss();
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICTURE) {
            if(resultCode == getActivity().RESULT_OK){
                boolean fromCamera = (data == null || data.getData() == null );
                if(fromCamera){
                    addToGallery();
                }else{
                    photoPath = getRealPathFromURI(data.getData());
                }

            }

        }
    }


    @OnClick(R.id.imgDog)
    public void takePicture(){
        Intent chooserIntent = null;

        List<Intent> intentList = new ArrayList<>();
        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        Intent cameraInten = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraInten.putExtra("return-data", true);

        File photoFile = getFile();
        if(photoFile != null){
            cameraInten.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));

            if(cameraInten.resolveActivity(getActivity().getPackageManager()) != null){
                intentList = addIntentsToList(intentList, cameraInten);
            }
        }
        if(pickIntent.resolveActivity(getActivity().getPackageManager()) != null){
            intentList = addIntentsToList(intentList, pickIntent);
        }
        if(intentList.size() > 0){
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    getString(R.string.main_message_picture_source));
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }
        if(chooserIntent!=null){
            startActivityForResult(chooserIntent, REQUEST_PICTURE);
        }

    }
    private File getFile(){
        File photoFile = null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        try {
            photoFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            Snackbar.make(containerAddP, R.string.main_error_dispatch_camera, Snackbar.LENGTH_SHORT).show();
        }
        photoPath = photoFile.getAbsolutePath();
        return photoFile;
    }

    private List<Intent> addIntentsToList(List<Intent> list, Intent intent){
        List<ResolveInfo> resInfo = getActivity().getPackageManager().queryIntentActivities(intent, 0);
        for(ResolveInfo resolveInfo: resInfo){
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetIntent = new Intent(intent);
            targetIntent.setPackage(packageName);
            list.add(targetIntent);
        }
        return list;
    }

    private void showSnackbar(String msg){
        Snackbar.make(containerAddP, msg, Snackbar.LENGTH_SHORT).show();
    }
    private void showSnackbar(int strResult){
        Snackbar.make(containerAddP, strResult, Snackbar.LENGTH_SHORT).show();
    }
    private void addToGallery(){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(photoPath);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);

    }

    private String getRealPathFromURI(Uri contentURI) {
        String result = null;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            if (contentURI.toString().contains("mediaKey")){
                cursor.close();

                try {
                    File file = File.createTempFile("tempImg", ".jpg", getActivity().getCacheDir());
                    InputStream input = getActivity().getContentResolver().openInputStream(contentURI);
                    OutputStream output = new FileOutputStream(file);

                    try {
                        byte[] buffer = new byte[4 * 1024];
                        int read;

                        while ((read = input.read(buffer)) != -1) {
                            output.write(buffer, 0, read);
                        }
                        output.flush();
                        result = file.getAbsolutePath();
                    } finally {
                        output.close();
                        input.close();
                    }

                } catch (Exception e) {
                }
            } else {
                cursor.moveToFirst();
                int dataColumn = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(dataColumn);
                cursor.close();
            }

        }
        return result;
    }

    @Override
    public void onUploadInit() {
        showSnackbar(R.string.main_notice_upload_init);
    }

    @Override
    public void onUploadComplete() {
        showSnackbar(R.string.main_notice_upload_complete);
    }

    @Override
    public void onUploadError(String error) {
        showSnackbar(error);
    }
}